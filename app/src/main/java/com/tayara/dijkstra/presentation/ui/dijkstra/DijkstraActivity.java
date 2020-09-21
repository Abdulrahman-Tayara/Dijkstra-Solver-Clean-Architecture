package com.tayara.dijkstra.presentation.ui.dijkstra;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.tayara.dijkstra.presentation.model.Node;
import com.tayara.dijkstra.presentation.model.Path;
import com.tayara.dijkstra.presentation.ui.base.view.activity.MVVMActivity;
import com.tayara.dijkstra.presentation.ui.common.OnItemClick;
import com.tayara.dijkstra.presentation.ui.common.SimpleSwipeToDelete;
import com.tayara.dijkstra.presentation.ui.dijkstra.adapter.EdgeListAdapter;
import com.tayara.dijkstra.presentation.ui.dijkstra.adapter.NodeListAdapter;
import com.tayara.dijkstra.util.lifecycle.EventObserver;
import com.tayara.dijkstraapplication.BR;
import com.tayara.dijkstraapplication.R;
import com.tayara.dijkstraapplication.databinding.ActivityDijkstraBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DijkstraActivity extends MVVMActivity<DijkstraViewModel, ActivityDijkstraBinding> {

    private NodeListAdapter nodeListAdapter;
    private EdgeListAdapter edgeListAdapter;

    private SimpleSwipeToDelete simpleSwipeToDelete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupLists();
        setupObservers();
    }

    private void setupLists() {
        // NodeListAdapter
        nodeListAdapter = new NodeListAdapter(this, onNodeClick);
        binding.nodesList.setAdapter(nodeListAdapter);

        // EdgeListAdapter
        edgeListAdapter = new EdgeListAdapter(this, onNodeInEdgeClick);
        binding.edgesList.setAdapter(edgeListAdapter);

        // Add swipe to delete in EdgesList.
        simpleSwipeToDelete = new SimpleSwipeToDelete(binding.edgesList, ((position, direction) -> viewModel.deleteEdge(position)));
        simpleSwipeToDelete.enable();
    }

    private OnItemClick<Node> onNodeClick = (node, position) -> {
        // If there is node on stash then swap it, else put the node as start or end node
        if (viewModel.hasStash()) {
            viewModel.swapNodeWithStash(node);
        } else
            viewModel.setNode(node);
    };

    // When click on node from edge add it to stash
    private OnItemClick<Node> onNodeInEdgeClick = (node, position) -> viewModel.addToStash(node);

    private void setupObservers() {
        viewModel.result.observe(this, new EventObserver<>(result -> {
            if (result == DijkstraResult.SUCCESS) {
                showPathFound(result.getResult());
            } else if (result == DijkstraResult.NO_PATH) {
                showNoPathFound();
            }
        }));
    }

    private void showPathFound(Path path) {
        StringBuilder builder = new StringBuilder();
        builder = builder.append(getString(R.string.cost)).append(": ").append(path.getCost())
                .append("\n")
                .append(getString(R.string.path)).append(": ").append(path.toString());
        showDialogMessage(getString(R.string.result), builder.toString());
    }

    private void showNoPathFound() {
        showDialogMessage(R.string.result, R.string.no_path_found);
    }

    @Override
    protected Class<DijkstraViewModel> getViewModelClass() {
        return DijkstraViewModel.class;
    }

    @Override
    protected int getViewModelId() {
        return BR.viewModel;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dijkstra;
    }
}
