package com.tayara.dijkstra.presentation.ui.base.view.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.tayara.dijkstra.presentation.ui.base.view.BaseViewModel;
import com.tayara.dijkstra.util.lifecycle.EventObserver;


/**
 * @param <VM> : ViewModel
 * @param <DB> : ViewDataBinding
 */
abstract public class MVVMActivity<VM extends BaseViewModel, DB extends ViewDataBinding> extends BaseActivity<DB> {

    protected VM viewModel;

    abstract protected int getViewModelId();

    abstract protected Class<VM> getViewModelClass();

    protected VM provideViewModel() {
        return new ViewModelProvider(getViewModelOwner()).get(getViewModelClass());
    }

    protected ViewModelStoreOwner getViewModelOwner() {
        return this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupBaseObservers();
    }

    protected void setupBaseObservers() {
        viewModel = provideViewModel();
        if (getViewModelId() > 0 && binding != null)
            binding.setVariable(getViewModelId(), viewModel);
        viewModel.toastMessage.observe(this, new EventObserver<>(this::showMessage));
        viewModel.toastMessageResource.observe(this, new EventObserver<>(this::showMessage));
        viewModel.hideKeyboard.observe(this, new EventObserver<>(ignored -> hideKeyboard()));
    }
}