package com.tayara.dijkstra.domain.executor;

import io.reactivex.Scheduler;

public interface UIExecutor {
    Scheduler getSchedulers();
}
