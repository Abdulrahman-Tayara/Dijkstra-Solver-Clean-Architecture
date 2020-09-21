package com.tayara.dijkstra.data.repository.datasource;

import com.tayara.dijkstra.data.minimumpath.IMinimumPathSolver;
import com.tayara.dijkstra.data.model.EdgeModel;
import com.tayara.dijkstra.data.model.GraphBuilder;
import com.tayara.dijkstra.data.model.NodeModel;
import com.tayara.dijkstra.data.model.PathModel;
import com.tayara.dijkstra.data.repository.datasource.dijkstra.MinimumPathLocalDataSourceImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MinimumPathLocalDataSourceTest {

    private MinimumPathLocalDataSourceImpl localDataSource;

    @Mock
    private IMinimumPathSolver minimumPathSolver;

    private NodeModel fakeNode = mock(NodeModel.class);
    private NodeModel fakeNode2 = mock(NodeModel.class);
    private List<EdgeModel> fakeEdges = mock(List.class);

    @Before
    public void init() {
        localDataSource = new MinimumPathLocalDataSourceImpl(minimumPathSolver);
    }

    @Test
    public void findMinimumPath() {
        GraphBuilder graphBuilder = new GraphBuilder(1, fakeEdges);

        when(minimumPathSolver.findMinimumPath(graphBuilder.build(), fakeNode, fakeNode2))
                .thenReturn(mock(PathModel.class));

        TestObserver<PathModel> testObserver = localDataSource.findMinimumPath(fakeNode, fakeNode2, fakeEdges).test();

        testObserver.assertSubscribed();

        
        verify(minimumPathSolver).findMinimumPath(graphBuilder.build(), fakeNode, fakeNode2);
    }
}
