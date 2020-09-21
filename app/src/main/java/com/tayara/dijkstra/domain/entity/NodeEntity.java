package com.tayara.dijkstra.domain.entity;

public class NodeEntity {
    private int number;
    private String colorHEX;

    public NodeEntity(int number, String colorHEX) {
        this.number = number;
        this.colorHEX = colorHEX;
    }

    public int getNumber() {
        return number;
    }

    public String getColorHEX() {
        return colorHEX;
    }
}
