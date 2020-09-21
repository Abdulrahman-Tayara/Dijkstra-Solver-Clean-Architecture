package com.tayara.dijkstra.data.executor;

import com.tayara.dijkstra.domain.executor.ThreadExecutor;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
@Singleton
public class ThreadExecutorImpl implements ThreadExecutor {

    @Inject
    public ThreadExecutorImpl() {
    }

    @Override
    public Scheduler getSchedulers() {
        return Schedulers.io();
    }
}
