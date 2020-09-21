package com.tayara.dijkstra.data.model.mapper;

import java.util.List;
import java.util.stream.Collectors;

abstract public class Mapper<FROM, TO> {

    public abstract TO map(FROM model);

    public FROM unmap(TO model) {
        return null;
    }

    public List<TO> map(List<FROM> models) {
        if (models == null)
            return null;
        return models.stream().map(this::map).collect(Collectors.toList());
    }

    public List<FROM> unmap(List<TO> models) {
        if (models == null)
            return null;
        return models.stream().map(this::unmap).collect(Collectors.toList());
    }

}
