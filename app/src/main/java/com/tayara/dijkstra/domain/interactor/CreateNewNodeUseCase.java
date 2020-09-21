package com.tayara.dijkstra.domain.interactor;

import com.tayara.dijkstra.domain.entity.NodeEntity;
import com.tayara.dijkstra.domain.executor.ThreadExecutor;
import com.tayara.dijkstra.domain.executor.UIExecutor;
import com.tayara.dijkstra.domain.interactor.base.UseCase;
import com.tayara.dijkstra.domain.repository.MinimumPathRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CreateNewNodeUseCase extends UseCase<NodeEntity> {

    private MinimumPathRepository repository;

    @Inject
    public CreateNewNodeUseCase(UIExecutor uiExecutor, ThreadExecutor threadExecutor, MinimumPathRepository repository) {
        super(uiExecutor, threadExecutor);
        this.repository = repository;
    }

    @Override
    protected Observable<NodeEntity> buildObservable() {
        return repository.createNewNode();
    }
}
