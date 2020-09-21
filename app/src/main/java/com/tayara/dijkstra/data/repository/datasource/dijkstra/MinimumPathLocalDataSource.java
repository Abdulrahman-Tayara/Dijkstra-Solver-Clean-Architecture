package com.tayara.dijkstra.data.repository.datasource.dijkstra;

import com.tayara.dijkstra.data.model.EdgeModel;
import com.tayara.dijkstra.data.model.NodeModel;
import com.tayara.dijkstra.data.model.PathModel;

import java.util.List;

import io.reactivex.Single;

public interface MinimumPathLocalDataSource {
    Single<NodeModel> createNewNode();

    Single<EdgeModel> createNewEdge(NodeModel startNode, NodeModel endNode, double weight);

    Single<PathModel> findMinimumPath(NodeModel startNode, NodeModel endNode, List<EdgeModel> edges);
}
