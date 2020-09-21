package com.tayara.dijkstra.presentation.di.module;

import com.tayara.dijkstra.data.minimumpath.DijkstraMinimumPath;
import com.tayara.dijkstra.data.minimumpath.IMinimumPathSolver;
import com.tayara.dijkstra.data.executor.ThreadExecutorImpl;
import com.tayara.dijkstra.domain.executor.ThreadExecutor;
import com.tayara.dijkstra.domain.executor.UIExecutor;
import com.tayara.dijkstra.presentation.executor.UIExecutorImpl;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class AppModule {

    @Provides
    @Singleton
    @Named("dijkstra")
    IMinimumPathSolver provideDijkstraSolver(DijkstraMinimumPath dijkstraSolver) {
        return dijkstraSolver;
    }

    @Provides
    @Singleton
    UIExecutor provideUIExecutor(UIExecutorImpl uiExecutor) {
        return uiExecutor;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(ThreadExecutorImpl threadExecutor) {
        return threadExecutor;
    }
}
