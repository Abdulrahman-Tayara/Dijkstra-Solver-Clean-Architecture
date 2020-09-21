package com.tayara.dijkstra.data.model;

import java.util.List;

public class PathModel {
    private double cost; // minimum cost between start and end
    private List<NodeModel> nodes;

    public PathModel(double cost, List<NodeModel> nodes) {
        this.cost = cost;
        this.nodes = nodes;
    }

    public double getCost() {
        return cost;
    }

    public List<NodeModel> getNodes() {
        return nodes;
    }
}
