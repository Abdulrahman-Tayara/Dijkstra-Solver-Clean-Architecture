package com.tayara.dijkstra.data.model;

import androidx.annotation.NonNull;

public class NodeModel {

    private int number;
    private String color;

    public NodeModel(int number, String color) {
        this.number = number;
        this.color = color;
    }

    public NodeModel(int number) {
        this(number, "");
    }

    public int getNumber() {
        return number;
    }

    public String getColor() {
        return color;
    }

    @NonNull
    @Override
    public String toString() {
        return "" + number;
    }
}
