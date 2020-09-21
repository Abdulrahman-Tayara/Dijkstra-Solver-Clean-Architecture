package com.tayara.dijkstra.data.minimumpath;

import com.tayara.dijkstra.data.model.GraphModel;
import com.tayara.dijkstra.data.model.NodeModel;
import com.tayara.dijkstra.data.model.PathModel;

public interface IMinimumPathSolver {

    PathModel findMinimumPath(GraphModel graphModel, NodeModel startNode, NodeModel endNode);
}
