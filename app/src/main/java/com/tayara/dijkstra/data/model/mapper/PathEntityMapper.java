package com.tayara.dijkstra.data.model.mapper;

import com.tayara.dijkstra.data.model.PathModel;
import com.tayara.dijkstra.domain.entity.PathEntity;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PathEntityMapper extends Mapper<PathModel, PathEntity> {

    private NodeEntityMapper nodeEntityMapper;

    @Inject
    public PathEntityMapper(NodeEntityMapper nodeEntityMapper) {
        this.nodeEntityMapper = nodeEntityMapper;
    }

    @Override
    public PathEntity map(PathModel model) {
        if (model == null)
            return null;
        return new PathEntity(model.getCost(), nodeEntityMapper.map(model.getNodes()));
    }
}
