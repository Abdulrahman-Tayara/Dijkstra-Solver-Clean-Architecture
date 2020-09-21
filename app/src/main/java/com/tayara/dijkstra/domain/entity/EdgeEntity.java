package com.tayara.dijkstra.domain.entity;

public class EdgeEntity {
    private NodeEntity node1;
    private NodeEntity node2;
    private double weight;

    public EdgeEntity(NodeEntity node1, NodeEntity node2, double weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }

    public NodeEntity getNode1() {
        return node1;
    }

    public NodeEntity getNode2() {
        return node2;
    }

    public double getWeight() {
        return weight;
    }
}
