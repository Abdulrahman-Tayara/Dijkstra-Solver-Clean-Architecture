package com.tayara.dijkstra.presentation.ui.base.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.tayara.dijkstra.domain.interactor.base.BaseUseCase;
import com.tayara.dijkstra.presentation.exception.ExceptionFactory;
import com.tayara.dijkstra.util.lifecycle.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseViewModel extends ViewModel {

    private List<BaseUseCase> useCases = new ArrayList<>();

    private MutableLiveData<Boolean> _isLoading = new MutableLiveData<>(false);

    private MutableLiveData<Event<String>> _toastMessage = new MutableLiveData<>();
    public LiveData<Event<String>> toastMessage = _toastMessage;

    private MutableLiveData<Event<Integer>> _toastMessageResource = new MutableLiveData<>();
    public LiveData<Event<Integer>> toastMessageResource = _toastMessageResource;

    private MutableLiveData<Event<Boolean>> _hideKeyboard = new MutableLiveData<>();
    public LiveData<Event<Boolean>> hideKeyboard = _hideKeyboard;

    protected void showMessage(String message) {
        _toastMessage.setValue(new Event<>(message));
    }

    protected void showMessage(int stringId) {
        _toastMessageResource.setValue(new Event<>(stringId));
    }

    protected void showMessage(Throwable error) {
        if (error.getMessage() != null)
            showMessage(error.getMessage());
        else
            showMessage(ExceptionFactory.getString(error));
    }

    protected void hideKeyboard() {
        _hideKeyboard.setValue(new Event<>(true));
    }

    protected void startLoading() {
        _isLoading.setValue(true);
    }

    protected void stopLoading() {
        _isLoading.setValue(false);
    }

    protected boolean isLoading() {
        return _isLoading.getValue() != null && _isLoading.getValue();
    }

    protected void registerUseCases(BaseUseCase... useCases) {
        this.useCases.addAll(Arrays.asList(useCases));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        for (BaseUseCase useCase : useCases)
            useCase.dispose();
    }
}
