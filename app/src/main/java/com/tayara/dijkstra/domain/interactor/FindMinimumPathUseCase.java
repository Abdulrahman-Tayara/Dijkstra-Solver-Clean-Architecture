package com.tayara.dijkstra.domain.interactor;

import com.tayara.dijkstra.domain.entity.EdgeEntity;
import com.tayara.dijkstra.domain.entity.NodeEntity;
import com.tayara.dijkstra.domain.entity.PathEntity;
import com.tayara.dijkstra.domain.executor.ThreadExecutor;
import com.tayara.dijkstra.domain.executor.UIExecutor;
import com.tayara.dijkstra.domain.interactor.base.ParamsUseCase;
import com.tayara.dijkstra.domain.repository.MinimumPathRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class FindMinimumPathUseCase extends ParamsUseCase<PathEntity, FindMinimumPathUseCase.FindMinimumPathParams> {

    private MinimumPathRepository repository;

    @Inject
    public FindMinimumPathUseCase(UIExecutor uiExecutor, ThreadExecutor threadExecutor, MinimumPathRepository repository) {
        super(uiExecutor, threadExecutor);
        this.repository = repository;
    }

    @Override
    protected Observable<PathEntity> buildObservable(FindMinimumPathParams params) {
        return repository.findMinimumPath(params.startNode, params.endNode, params.edges);
    }

    public static class FindMinimumPathParams {
        private NodeEntity startNode;
        private NodeEntity endNode;
        private List<EdgeEntity> edges;

        private FindMinimumPathParams(NodeEntity startNode, NodeEntity endNode, List<EdgeEntity> edges) {
            this.startNode = startNode;
            this.endNode = endNode;
            this.edges = edges;
        }

        public static FindMinimumPathParams create(NodeEntity startNode, NodeEntity endNode, List<EdgeEntity> edges) {
            return new FindMinimumPathParams(startNode, endNode, edges);
        }
    }
}
