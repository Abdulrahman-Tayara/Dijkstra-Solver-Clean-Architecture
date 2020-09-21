package com.tayara.dijkstra.presentation.di.module;

import com.tayara.dijkstra.data.repository.MinimumPathRepositoryImpl;
import com.tayara.dijkstra.domain.repository.MinimumPathRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
abstract public class RepositoryModule {

    @Binds
    @Singleton
    abstract MinimumPathRepository bindDijkstraRepository(MinimumPathRepositoryImpl repository);
}
