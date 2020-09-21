package com.tayara.dijkstra.data.model;

public class EdgeModel {
    private NodeModel node1;
    private NodeModel node2;
    private double weight;

    public EdgeModel(NodeModel node1, NodeModel node2, double weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }

    public NodeModel getNode1() {
        return node1;
    }

    public NodeModel getNode2() {
        return node2;
    }

    public double getWeight() {
        return weight;
    }
}
