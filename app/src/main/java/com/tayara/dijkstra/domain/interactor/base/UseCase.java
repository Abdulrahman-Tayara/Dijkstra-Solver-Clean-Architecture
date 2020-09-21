package com.tayara.dijkstra.domain.interactor.base;

import com.tayara.dijkstra.domain.executor.ThreadExecutor;
import com.tayara.dijkstra.domain.executor.UIExecutor;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

/**
 * abstract use case
 *
 * @param <T> return type
 */
abstract public class UseCase<T> extends BaseUseCase {

    public UseCase(UIExecutor uiExecutor, ThreadExecutor threadExecutor) {
        super(uiExecutor, threadExecutor);
    }

    protected abstract Observable<T> buildObservable();

    public void execute(DisposableObserver<T> observer) {
        addDisposable(
                buildObservable()
                        .doOnError(Throwable::printStackTrace)
                        .subscribeOn(threadExecutor.getSchedulers())
                        .observeOn(uiExecutor.getSchedulers())
                        .subscribeWith(observer)
        );
    }
}
