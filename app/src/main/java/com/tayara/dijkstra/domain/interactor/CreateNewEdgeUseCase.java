package com.tayara.dijkstra.domain.interactor;

import com.tayara.dijkstra.domain.entity.EdgeEntity;
import com.tayara.dijkstra.domain.entity.NodeEntity;
import com.tayara.dijkstra.domain.executor.ThreadExecutor;
import com.tayara.dijkstra.domain.executor.UIExecutor;
import com.tayara.dijkstra.domain.interactor.base.ParamsUseCase;
import com.tayara.dijkstra.domain.repository.MinimumPathRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CreateNewEdgeUseCase extends ParamsUseCase<EdgeEntity, CreateNewEdgeUseCase.CreateEdgeParams> {

    private MinimumPathRepository repository;

    @Inject
    public CreateNewEdgeUseCase(UIExecutor uiExecutor, ThreadExecutor threadExecutor, MinimumPathRepository repository) {
        super(uiExecutor, threadExecutor);
        this.repository = repository;
    }

    @Override
    protected Observable<EdgeEntity> buildObservable(CreateEdgeParams params) {
        return repository.createNewEdge(params.startNode, params.endNode, params.weight);
    }

    public static class CreateEdgeParams {
        private NodeEntity startNode, endNode;
        private double weight;

        private CreateEdgeParams(NodeEntity startNode, NodeEntity endNode, double weight) {
            this.startNode = startNode;
            this.endNode = endNode;
            this.weight = weight;
        }

        public static CreateEdgeParams create(NodeEntity startNode, NodeEntity endNode, double weight) {
            return new CreateEdgeParams(startNode, endNode, weight);
        }
    }
}
