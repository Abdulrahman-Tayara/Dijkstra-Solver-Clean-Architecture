package com.tayara.dijkstra.domain.repository;

import com.tayara.dijkstra.domain.entity.EdgeEntity;
import com.tayara.dijkstra.domain.entity.NodeEntity;
import com.tayara.dijkstra.domain.entity.PathEntity;

import java.util.List;

import io.reactivex.Observable;

public interface MinimumPathRepository {

    Observable<NodeEntity> createNewNode();

    Observable<EdgeEntity> createNewEdge(NodeEntity startNode, NodeEntity endNode, double weight);

    Observable<PathEntity> findMinimumPath(NodeEntity startNode, NodeEntity endNode, List<EdgeEntity> edges);
}
