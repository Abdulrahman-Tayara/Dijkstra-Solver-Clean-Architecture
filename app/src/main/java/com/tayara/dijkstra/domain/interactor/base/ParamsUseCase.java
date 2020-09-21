package com.tayara.dijkstra.domain.interactor.base;

import com.tayara.dijkstra.domain.executor.ThreadExecutor;
import com.tayara.dijkstra.domain.executor.UIExecutor;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

/**
 * Abstract use case with params
 *
 * @param <T>     return type
 * @param <Param> params type
 */
abstract public class ParamsUseCase<T, Param> extends BaseUseCase {

    public ParamsUseCase(UIExecutor uiExecutor, ThreadExecutor threadExecutor) {
        super(uiExecutor, threadExecutor);
    }

    protected abstract Observable<T> buildObservable(Param param);

    public void execute(Param param, DisposableObserver<T> observer) {
        addDisposable(
                buildObservable(param)
                        .doOnError(Throwable::printStackTrace)
                        .subscribeOn(threadExecutor.getSchedulers())
                        .observeOn(uiExecutor.getSchedulers())
                        .subscribeWith(observer)
        );
    }
}
