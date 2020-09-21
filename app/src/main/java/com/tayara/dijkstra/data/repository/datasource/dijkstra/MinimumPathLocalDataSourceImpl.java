package com.tayara.dijkstra.data.repository.datasource.dijkstra;

import com.tayara.dijkstra.data.exception.NoPathFoundException;
import com.tayara.dijkstra.data.minimumpath.IMinimumPathSolver;
import com.tayara.dijkstra.data.model.EdgeModel;
import com.tayara.dijkstra.data.model.GraphBuilder;
import com.tayara.dijkstra.data.model.NodeModel;
import com.tayara.dijkstra.data.model.PathModel;
import com.tayara.dijkstra.util.ColorUtil;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class MinimumPathLocalDataSourceImpl implements MinimumPathLocalDataSource {

    private int NODE_ID = 1; // autoincrement id
    private IMinimumPathSolver minimumPathSolver;

    @Inject
    public MinimumPathLocalDataSourceImpl(@Named("dijkstra") IMinimumPathSolver minimumPathSolver) {
        this.minimumPathSolver = minimumPathSolver;
    }

    @Override
    public Single<NodeModel> createNewNode() {
        return Single.just(new NodeModel(NODE_ID++, ColorUtil.generateRandomColor()));
    }

    @Override
    public Single<EdgeModel> createNewEdge(NodeModel startNode, NodeModel endNode, double weight) {
        return Single.just(new EdgeModel(startNode, endNode, weight));
    }

    @Override
    public Single<PathModel> findMinimumPath(NodeModel startNode, NodeModel endNode, List<EdgeModel> edges) {
        return Single.create(emitter -> {
            GraphBuilder graphBuilder = new GraphBuilder(1, edges);
            PathModel pathModel = minimumPathSolver.findMinimumPath(graphBuilder.build(), startNode, endNode);
            if (pathModel == null)
                emitter.onError(new NoPathFoundException("There is no path between " + startNode + " and " + endNode));
            else
                emitter.onSuccess(pathModel);
        });

    }
}
