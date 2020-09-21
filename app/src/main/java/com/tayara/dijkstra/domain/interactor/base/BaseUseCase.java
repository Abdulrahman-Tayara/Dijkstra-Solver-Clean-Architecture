package com.tayara.dijkstra.domain.interactor.base;

import com.tayara.dijkstra.domain.executor.ThreadExecutor;
import com.tayara.dijkstra.domain.executor.UIExecutor;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

abstract public class BaseUseCase {
    protected UIExecutor uiExecutor;
    protected ThreadExecutor threadExecutor;
    private CompositeDisposable compositeDisposable;

    public BaseUseCase(UIExecutor uiExecutor, ThreadExecutor threadExecutor) {
        this.uiExecutor = uiExecutor;
        this.threadExecutor = threadExecutor;
        compositeDisposable = new CompositeDisposable();
    }

    protected void addDisposable(Disposable disposable) {
        if (disposable != null)
            compositeDisposable.add(disposable);
    }

    public void dispose() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }
}
