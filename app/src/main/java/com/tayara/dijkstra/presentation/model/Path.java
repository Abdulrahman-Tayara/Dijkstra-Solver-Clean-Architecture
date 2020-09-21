package com.tayara.dijkstra.presentation.model;

import androidx.annotation.NonNull;

import java.util.List;

public class Path {
    private double cost;
    private List<Node> nodes;

    public Path(double cost, List<Node> nodes) {
        this.cost = cost;
        this.nodes = nodes;
    }

    public double getCost() {
        return cost;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < nodes.size(); ++i) {
            builder = builder.append(nodes.get(i).getNumber().get());
            if (i < nodes.size() - 1)
                builder = builder.append(" -> ");
        }
        return builder.toString();
    }
}
