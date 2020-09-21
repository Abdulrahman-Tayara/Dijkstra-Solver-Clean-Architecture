package com.tayara.dijkstra.presentation.ui.dijkstra;

import com.tayara.dijkstra.presentation.model.Path;

public enum DijkstraResult {
    SUCCESS, NO_PATH;

    private Path result;

    public Path getResult() {
        return result;
    }

    public DijkstraResult setResult(Path result) {
        this.result = result;
        return this;
    }
}
