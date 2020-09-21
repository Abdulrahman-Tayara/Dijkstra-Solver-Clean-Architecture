package com.tayara.dijkstra.data.model.mapper;

import com.tayara.dijkstra.data.model.EdgeModel;
import com.tayara.dijkstra.domain.entity.EdgeEntity;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class EdgeEntityMapper extends Mapper<EdgeModel, EdgeEntity> {

    private NodeEntityMapper nodeMapper;

    @Inject
    public EdgeEntityMapper(NodeEntityMapper nodeMapper) {
        this.nodeMapper = nodeMapper;
    }

    @Override
    public EdgeEntity map(EdgeModel model) {
        return new EdgeEntity(nodeMapper.map(model.getNode1()), nodeMapper.map(model.getNode2()), model.getWeight());
    }

    @Override
    public EdgeModel unmap(EdgeEntity model) {
        return new EdgeModel(nodeMapper.unmap(model.getNode1()), nodeMapper.unmap(model.getNode2()), model.getWeight());
    }
}
