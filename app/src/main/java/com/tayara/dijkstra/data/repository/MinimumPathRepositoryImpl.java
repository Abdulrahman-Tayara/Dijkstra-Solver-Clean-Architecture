package com.tayara.dijkstra.data.repository;

import com.tayara.dijkstra.data.model.mapper.EdgeEntityMapper;
import com.tayara.dijkstra.data.model.mapper.NodeEntityMapper;
import com.tayara.dijkstra.data.model.mapper.PathEntityMapper;
import com.tayara.dijkstra.data.repository.datasource.dijkstra.MinimumPathLocalDataSource;
import com.tayara.dijkstra.domain.entity.EdgeEntity;
import com.tayara.dijkstra.domain.entity.NodeEntity;
import com.tayara.dijkstra.domain.entity.PathEntity;
import com.tayara.dijkstra.domain.repository.MinimumPathRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class MinimumPathRepositoryImpl implements MinimumPathRepository {

    private MinimumPathLocalDataSource localDataSource;
    private NodeEntityMapper nodeEntityMapper;
    private EdgeEntityMapper edgeEntityMapper;
    private PathEntityMapper pathEntityMapper;

    @Inject
    public MinimumPathRepositoryImpl(MinimumPathLocalDataSource localDataSource, NodeEntityMapper nodeEntityMapper, EdgeEntityMapper edgeEntityMapper, PathEntityMapper pathEntityMapper) {
        this.localDataSource = localDataSource;
        this.nodeEntityMapper = nodeEntityMapper;
        this.edgeEntityMapper = edgeEntityMapper;
        this.pathEntityMapper = pathEntityMapper;
    }

    @Override
    public Observable<NodeEntity> createNewNode() {
        return localDataSource.createNewNode().map(nodeEntityMapper::map).toObservable();
    }

    @Override
    public Observable<EdgeEntity> createNewEdge(NodeEntity startNode, NodeEntity endNode, double weight) {
        return localDataSource.createNewEdge(nodeEntityMapper.unmap(startNode), nodeEntityMapper.unmap(endNode), weight).map(edgeEntityMapper::map).toObservable();
    }

    @Override
    public Observable<PathEntity> findMinimumPath(NodeEntity startNode, NodeEntity endNode, List<EdgeEntity> edges) {
        return localDataSource.findMinimumPath(nodeEntityMapper.unmap(startNode), nodeEntityMapper.unmap(endNode), edgeEntityMapper.unmap(edges))
                .map(pathEntityMapper::map).toObservable();
    }
}
