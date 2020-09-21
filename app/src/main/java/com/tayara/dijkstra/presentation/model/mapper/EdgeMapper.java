package com.tayara.dijkstra.presentation.model.mapper;

import com.tayara.dijkstra.data.model.mapper.Mapper;
import com.tayara.dijkstra.domain.entity.EdgeEntity;
import com.tayara.dijkstra.presentation.model.Edge;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class EdgeMapper extends Mapper<EdgeEntity, Edge> {

    private NodeMapper nodeMapper;

    @Inject
    public EdgeMapper(NodeMapper nodeMapper) {
        this.nodeMapper = nodeMapper;
    }

    @Override
    public Edge map(EdgeEntity model) {
        return new Edge(nodeMapper.map(model.getNode1()), nodeMapper.map(model.getNode2()), model.getWeight());
    }

    @Override
    public EdgeEntity unmap(Edge model) {
        return new EdgeEntity(nodeMapper.unmap(model.getStartNode()), nodeMapper.unmap(model.getEndNode()), model.getWeight());
    }
}
