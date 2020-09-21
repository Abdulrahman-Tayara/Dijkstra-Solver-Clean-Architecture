package com.tayara.dijkstra.presentation.model;

public class Edge {
    private Node startNode;
    private Node endNode;
    private double weight;

    public Edge(Node startNode, Node endNode, double weight) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.weight = weight;
    }

    public Node getStartNode() {
        return startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public double getWeight() {
        return weight;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public void setEndNode(Node endNode) {
        this.endNode = endNode;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
