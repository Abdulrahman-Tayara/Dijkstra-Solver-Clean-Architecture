package com.tayara.dijkstra.presentation.di.module;

import com.tayara.dijkstra.data.repository.datasource.dijkstra.MinimumPathLocalDataSource;
import com.tayara.dijkstra.data.repository.datasource.dijkstra.MinimumPathLocalDataSourceImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
abstract public class DataSourceModule {

    @Binds
    @Singleton
    abstract MinimumPathLocalDataSource bindDijkstraLocalDataSource(MinimumPathLocalDataSourceImpl dataSource);
}
