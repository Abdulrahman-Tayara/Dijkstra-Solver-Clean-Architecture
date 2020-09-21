package com.tayara.dijkstra.domain.interactor.usecase;

import com.tayara.dijkstra.domain.executor.ThreadExecutor;
import com.tayara.dijkstra.domain.executor.UIExecutor;
import com.tayara.dijkstra.domain.interactor.CreateNewNodeUseCase;
import com.tayara.dijkstra.domain.repository.MinimumPathRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class CreateNewNodeUseCaseTest {

    private CreateNewNodeUseCase createNewNodeUseCase;

    @Mock private UIExecutor uiExecutor;
    @Mock private ThreadExecutor threadExecutor;
    @Mock private MinimumPathRepository repo;


    @Before
    public void setUp() throws Exception {
        createNewNodeUseCase = new CreateNewNodeUseCase(uiExecutor, threadExecutor, repo);
    }

    @Test
    public void buildObservable() {
        createNewNodeUseCase.buildObservable();
        verify(repo).createNewNode();
        verifyNoMoreInteractions(repo);
        verify(uiExecutor, never()).getSchedulers();
        verify(threadExecutor, never()).getSchedulers();
    }
}