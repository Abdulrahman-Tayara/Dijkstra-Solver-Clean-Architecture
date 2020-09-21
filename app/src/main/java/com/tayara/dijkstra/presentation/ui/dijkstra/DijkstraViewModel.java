package com.tayara.dijkstra.presentation.ui.dijkstra;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.tayara.dijkstra.data.exception.NoPathFoundException;
import com.tayara.dijkstra.domain.util.BaseObserver;
import com.tayara.dijkstra.domain.interactor.CreateNewEdgeUseCase;
import com.tayara.dijkstra.domain.interactor.CreateNewNodeUseCase;
import com.tayara.dijkstra.domain.interactor.FindMinimumPathUseCase;
import com.tayara.dijkstra.presentation.model.Edge;
import com.tayara.dijkstra.presentation.model.Node;
import com.tayara.dijkstra.presentation.model.Path;
import com.tayara.dijkstra.presentation.model.mapper.EdgeMapper;
import com.tayara.dijkstra.presentation.model.mapper.NodeMapper;
import com.tayara.dijkstra.presentation.model.mapper.PathMapper;
import com.tayara.dijkstra.presentation.ui.base.view.BaseViewModel;
import com.tayara.dijkstra.util.lifecycle.Event;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("ALL")
public class DijkstraViewModel extends BaseViewModel {

    // UseCases
    private CreateNewNodeUseCase createNewNodeUseCase;
    private CreateNewEdgeUseCase createNewEdgeUseCase;
    private FindMinimumPathUseCase findMinimumPathUseCase;

    // Data
    private NodeMapper nodeMapper;
    private MutableLiveData<List<Node>> _nodes = new MutableLiveData<>(Collections.emptyList());
    public ObservableList<Node> nodes = new ObservableArrayList<>();

    public MutableLiveData<Node> startNode = new MutableLiveData<>();
    public MutableLiveData<Node> endNode = new MutableLiveData<>();

    private EdgeMapper edgeMapper;
    private MutableLiveData<List<Edge>> _edges = new MutableLiveData<>(Collections.emptyList());
    public ObservableList<Edge> edges = new ObservableArrayList<>();

    private PathMapper pathMapper;
    public MutableLiveData<Event<DijkstraResult>> result = new MutableLiveData<>();

    public MediatorLiveData<Boolean> canStart = new MediatorLiveData<>();

    // Used to swap between nodes list items and edges list items
    private Node stashNode;

    @ViewModelInject
    public DijkstraViewModel(CreateNewNodeUseCase createNewNodeUseCase,
                             CreateNewEdgeUseCase createNewEdgeUseCase,
                             FindMinimumPathUseCase findMinimumPathUseCase,
                             NodeMapper nodeMapper,
                             EdgeMapper edgeMapper,
                             PathMapper pathMapper) {
        this.createNewNodeUseCase = createNewNodeUseCase;
        this.createNewEdgeUseCase = createNewEdgeUseCase;
        this.findMinimumPathUseCase = findMinimumPathUseCase;
        this.nodeMapper = nodeMapper;
        this.edgeMapper = edgeMapper;
        this.pathMapper = pathMapper;
        registerUseCases(createNewNodeUseCase, createNewEdgeUseCase, findMinimumPathUseCase);
        init();
    }

    private void init() {
        Observer<Node> onStartEndNodeChanged = node -> {
            canStart.setValue(startNode.getValue() != null && endNode.getValue() != null);
        };
        canStart.addSource(startNode, onStartEndNodeChanged);
        canStart.addSource(endNode, onStartEndNodeChanged);
    }

    public void addNewNode() {
        createNewNodeUseCase.execute(new BaseObserver<>(
                nodeEntity -> {
                    nodes.add(nodeMapper.map(nodeEntity));
                    _nodes.setValue(nodes);
                }
        ));
    }

    public void addNewEdge() {
        if (_nodes.getValue().size() < 2) {
            showMessage("You should add 2 nodes at least");
            return;
        }
        List<Node> nodes = _nodes.getValue();
        createNewEdgeUseCase.execute(
                CreateNewEdgeUseCase.CreateEdgeParams.create(nodeMapper.unmap(nodes.get(0)), nodeMapper.unmap(nodes.get(1)), 1),
                new BaseObserver<>(edgeEntity -> {
                    edges.add(edgeMapper.map(edgeEntity));
                    _edges.setValue(edges);
                })
        );
    }

    public void deleteEdge(int position) {
        Edge edgeToDelete = edges.get(position);

        // Check if the delelted nodes is same the stash node
        if (edgeToDelete.getStartNode() == stashNode || edgeToDelete.getEndNode() == stashNode) {
            stashNode = null;
        }

        edges.remove(edgeToDelete);
        _edges.setValue(edges);
    }

    public void startSolve() {
        if (isLoading())
            return;
        if (!canStart.getValue())
            return;
        startLoading();
        findMinimumPathUseCase.execute(
                FindMinimumPathUseCase.FindMinimumPathParams.create(
                        nodeMapper.unmap(startNode.getValue()),
                        nodeMapper.unmap(endNode.getValue()),
                        edgeMapper.unmap(_edges.getValue())
                ),
                new BaseObserver<>(
                        pathEntity -> {
                            stopLoading();
                            result.setValue(new Event<>(DijkstraResult.SUCCESS.setResult(pathMapper.map(pathEntity))));
                        },
                        error -> {
                            stopLoading();
                            if (error instanceof NoPathFoundException) {
                                result.setValue(new Event<>(DijkstraResult.NO_PATH));
                            } else
                                showMessage(error);
                        }
                )
        );
    }

    public void clearStartNode() {
        startNode.setValue(null);
        canStart.setValue(false);
    }

    public void clearEndNode() {
        endNode.setValue(null);
        canStart.setValue(false);
    }

    // Set the node in startNode or endNode
    public void setNode(Node node) {
        if (startNode.getValue() == null)
            startNode.setValue(node);
        else if (endNode.getValue() == null)
            endNode.setValue(node);
    }

    public boolean hasStash() {
        return stashNode != null;
    }

    // Swapping the stash node with passed node.
    public void swapNodeWithStash(Node node) {
        stashNode.copyValues(node);
        stashNode = null;
    }

    public void addToStash(Node node) {
        stashNode = node;
    }
}
