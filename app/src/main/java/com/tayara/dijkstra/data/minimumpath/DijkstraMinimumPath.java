package com.tayara.dijkstra.data.minimumpath;

import androidx.core.util.Pair;

import com.tayara.dijkstra.data.model.GraphModel;
import com.tayara.dijkstra.data.model.NodeModel;
import com.tayara.dijkstra.data.model.PathModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.inject.Inject;
import javax.inject.Singleton;

@SuppressWarnings("ConstantConditions")
@Singleton
public class DijkstraMinimumPath implements IMinimumPathSolver {

    private double distances[];
    private NodeModel parent[];
    private Queue<Pair<Double, NodeModel>> queue; // (weight, nodeNumber)

    private GraphModel graphModel;
    private NodeModel start, end;

    private List<NodeModel> path;

    @Inject
    public DijkstraMinimumPath() {
    }

    @Override
    public PathModel findMinimumPath(GraphModel graphModel, NodeModel startNode, NodeModel endNode) {
        this.graphModel = graphModel;
        this.start = startNode;
        this.end = endNode;
        init();
        findMinimumCost();
        if (!(distances[end.getNumber()] < Double.MAX_VALUE))
            return null; // no path between startNode and endNode
        findPath(endNode);
        Collections.reverse(path);
        return new PathModel(distances[endNode.getNumber()], path);
    }

    private void init() {
        distances = new double[graphModel.getAdjacents().length];
        Arrays.fill(distances, Double.MAX_VALUE);
        parent = new NodeModel[graphModel.getAdjacents().length];
//        for (int i = 0; i < parent.length; ++i)
//            parent[i] = i;
        queue = new PriorityQueue<>((ob1, ob2) -> (int) (ob1.first - ob2.first));
        path = new ArrayList<>();
    }

    private void findMinimumCost() {
        queue.add(new Pair<>(0.0, start));
        distances[start.getNumber()] = 0.0;
        while (!queue.isEmpty()) {
            Pair<Double, NodeModel> top = queue.remove();
            for (Pair<Double, NodeModel> child : graphModel.getChildrenOf(top.second.getNumber())) {
                int destination = child.second.getNumber();
                double cost = child.first;
                if (distances[destination] > distances[top.second.getNumber()] + cost) {
                    distances[destination] = distances[top.second.getNumber()] + cost;
                    parent[destination] = top.second;
                    queue.add(child);
                }
            }
        }
    }

    private void findPath(NodeModel node) {
        path.add(node);
        if (node.getNumber() == start.getNumber()) {
            return;
        }
        findPath(parent[node.getNumber()]);
    }
}
