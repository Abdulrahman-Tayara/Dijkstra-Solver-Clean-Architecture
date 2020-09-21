package com.tayara.dijkstra.domain.entity;

import java.util.List;

public class PathEntity {
    private double cost;
    private List<NodeEntity> nodes;

    public PathEntity(double cost, List<NodeEntity> nodes) {
        this.cost = cost;
        this.nodes = nodes;
    }

    public double getCost() {
        return cost;
    }

    public List<NodeEntity> getNodes() {
        return nodes;
    }
}
