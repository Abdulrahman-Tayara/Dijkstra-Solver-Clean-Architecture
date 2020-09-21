package com.tayara.dijkstra.presentation.model;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

public class Node {
    private ObservableInt number = new ObservableInt(0);
    private ObservableField<String> color = new ObservableField<>("");
    private ObservableBoolean selected = new ObservableBoolean(false);

    public Node(int number, String color) {
        this.number.set(number);
        this.color.set(color);
    }

    public ObservableInt getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number.set(number);
    }

    public ObservableField<String> getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color.set(color);
    }

    public ObservableBoolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

    public void copyValues(Node node) {
        setNumber(node.getNumber().get());
        setColor(node.getColor().get());
        setSelected(false);
    }
}
