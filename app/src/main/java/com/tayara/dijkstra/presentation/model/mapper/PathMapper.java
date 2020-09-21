package com.tayara.dijkstra.presentation.model.mapper;

import com.tayara.dijkstra.data.model.mapper.Mapper;
import com.tayara.dijkstra.domain.entity.PathEntity;
import com.tayara.dijkstra.presentation.model.Path;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PathMapper extends Mapper<PathEntity, Path> {

    private NodeMapper nodeMapper;

    @Inject
    public PathMapper(NodeMapper nodeMapper) {
        this.nodeMapper = nodeMapper;
    }

    @Override
    public Path map(PathEntity model) {
        if (model == null)
            return null;
        return new Path(model.getCost(), nodeMapper.map(model.getNodes()));
    }
}
