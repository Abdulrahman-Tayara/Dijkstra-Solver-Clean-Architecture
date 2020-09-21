package com.tayara.dijkstra.data.model;

import androidx.core.util.Pair;

import java.util.List;

public class GraphModel {

    private List<Pair<Double, NodeModel>>[] adjacents; // Pair of (weight, nodeNumber) , index : nodeNumber

    public GraphModel(List<Pair<Double, NodeModel>>[] adjacents) {
        this.adjacents = adjacents;
    }

    public List<Pair<Double, NodeModel>> getChildrenOf(int nodeNumber) {
        return adjacents[nodeNumber];
    }

    public List<Pair<Double, NodeModel>>[] getAdjacents() {
        return adjacents;
    }
}
