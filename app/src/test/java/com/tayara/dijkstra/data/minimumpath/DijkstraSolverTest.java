package com.tayara.dijkstra.data.minimumpath;


import com.tayara.dijkstra.data.model.EdgeModel;
import com.tayara.dijkstra.data.model.GraphBuilder;
import com.tayara.dijkstra.data.model.GraphModel;
import com.tayara.dijkstra.data.model.NodeModel;
import com.tayara.dijkstra.data.model.PathModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

// Real test
@RunWith(MockitoJUnitRunner.class)
public class DijkstraSolverTest {

    private DijkstraMinimumPath dijkstraSolver;

    private GraphModel graphModel;

    private NodeModel node1;
    private NodeModel node2;
    private NodeModel node3;
    private NodeModel node4;
    private NodeModel node5;

    @Before
    public void setup() {
        node1 = new NodeModel(1);
        node2 = new NodeModel(2);
        node3 = new NodeModel(3);
        node4 = new NodeModel(4);
        node5 = new NodeModel(5);

        dijkstraSolver = new DijkstraMinimumPath();
    }

    @Test
    public void path() {
        List<EdgeModel> edges = new ArrayList<>();
        edges.add(new EdgeModel(node1, node2, 3.0));
        edges.add(new EdgeModel(node2, node3, 4.0));
        edges.add(new EdgeModel(node3, node4, 1.0));

        GraphBuilder graphBuilder = new GraphBuilder(1, edges);

        PathModel minimumPath = dijkstraSolver.findMinimumPath(graphBuilder.build(), node1, node4);
        System.out.println("min = " + minimumPath.getCost());
        assertThat(minimumPath.getCost()).isEqualTo(8.0);
    }

    @Test
    public void pathWithCycle() {
        List<EdgeModel> edges = new ArrayList<>();
        edges.add(new EdgeModel(node1, node2, 3.0));
        edges.add(new EdgeModel(node1, node1, 3.0));
        edges.add(new EdgeModel(node2, node3, 4.0));
        edges.add(new EdgeModel(node3, node1, 1.0));
        edges.add(new EdgeModel(node3, node4, 2.0));

        GraphBuilder graphBuilder = new GraphBuilder(1, edges);

        PathModel minimumPath = dijkstraSolver.findMinimumPath(graphBuilder.build(), node1, node4);
        assertThat(minimumPath.getCost()).isEqualTo(3.0);
    }

    @Test
    public void noPath() {
        List<EdgeModel> edges = new ArrayList<>();
        edges.add(new EdgeModel(node1, node2, 3.0));
        edges.add(new EdgeModel(node2, node4, 3.0));
        edges.add(new EdgeModel(node3, node5, 4.0));


        GraphBuilder graphBuilder = new GraphBuilder(1, edges);

        PathModel minimumPath = dijkstraSolver.findMinimumPath(graphBuilder.build(), node1, node5);
        assertThat(minimumPath).isEqualTo(null);
    }
}
