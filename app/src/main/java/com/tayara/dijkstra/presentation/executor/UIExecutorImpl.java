package com.tayara.dijkstra.presentation.executor;

import com.tayara.dijkstra.domain.executor.UIExecutor;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

@Singleton
public class UIExecutorImpl implements UIExecutor {

    @Inject
    public UIExecutorImpl() {
    }

    @Override
    public Scheduler getSchedulers() {
        return AndroidSchedulers.mainThread();
    }
}
