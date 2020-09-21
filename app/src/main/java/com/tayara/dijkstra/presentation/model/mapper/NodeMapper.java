package com.tayara.dijkstra.presentation.model.mapper;

import com.tayara.dijkstra.data.model.mapper.Mapper;
import com.tayara.dijkstra.domain.entity.NodeEntity;
import com.tayara.dijkstra.presentation.model.Node;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NodeMapper extends Mapper<NodeEntity, Node> {

    @Inject
    public NodeMapper() {
    }

    @Override
    public Node map(NodeEntity model) {
        return new Node(model.getNumber(), model.getColorHEX());
    }

    @Override
    public NodeEntity unmap(Node model) {
        return new NodeEntity(model.getNumber().get(), model.getColor().get());
    }
}
