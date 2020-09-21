package com.tayara.dijkstra.data.model;

import androidx.core.util.Pair;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class GraphBuilder {
    private int firstNodesIndex;
    private List<EdgeModel> edges;

    private List<Pair<Double, NodeModel>>[] adjacents;

    public GraphBuilder(int firstNodesIndex, List<EdgeModel> edges) {
        this.firstNodesIndex = firstNodesIndex;
        this.edges = edges;
    }

    private int maxNumberOfNodes() {
        return (edges.size() * 2) + firstNodesIndex;
    }

    public GraphModel build() {
        if (adjacents != null)
            return new GraphModel(adjacents);

        adjacents = new List[maxNumberOfNodes()];
        edges.stream().forEach(edge -> {
            NodeModel node1 = edge.getNode1();
            NodeModel node2 = edge.getNode2();
            double weight = edge.getWeight();
            link(node1, node2, weight);
            link(node2, node1, weight);
        });
        return new GraphModel(adjacents);
    }

    private void link(NodeModel node1, NodeModel node2, double weight) {
        if (adjacents[node1.getNumber()] == null) {
            adjacents[node1.getNumber()] = new ArrayList<>();
        }
        adjacents[node1.getNumber()].add(new Pair<>(weight, node2));
    }
}
