package com.tayara.dijkstra.data.model.mapper;

import com.tayara.dijkstra.data.model.NodeModel;
import com.tayara.dijkstra.domain.entity.NodeEntity;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NodeEntityMapper extends Mapper<NodeModel, NodeEntity> {

    @Inject
    public NodeEntityMapper() {
    }

    @Override
    public NodeEntity map(NodeModel model) {
        return new NodeEntity(model.getNumber(), model.getColor());
    }

    @Override
    public NodeModel unmap(NodeEntity model) {
        return new NodeModel(model.getNumber(), model.getColorHEX());
    }
}
