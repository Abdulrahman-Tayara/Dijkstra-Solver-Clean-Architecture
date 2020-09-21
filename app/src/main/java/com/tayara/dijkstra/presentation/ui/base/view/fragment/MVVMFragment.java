package com.tayara.dijkstra.presentation.ui.base.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;


import com.tayara.dijkstra.presentation.ui.base.view.BaseViewModel;
import com.tayara.dijkstra.util.lifecycle.EventObserver;

abstract public class MVVMFragment<VM extends BaseViewModel, DB extends ViewDataBinding> extends BaseFragment {

    protected VM viewModel;

    abstract protected VM provideViewModel();

    abstract protected int getViewModelId();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = provideViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setupBaseObservers();
        return rootView;
    }

    protected void setupBaseObservers() {
        if (binding != null && getViewModelId() > 0)
            binding.setVariable(getViewModelId(), viewModel);
        LifecycleOwner owner = getViewLifecycleOwner();
        viewModel.toastMessageResource.observe(owner, new EventObserver<>(this::showMessage));
        viewModel.toastMessage.observe(owner, new EventObserver<>(this::showMessage));
        viewModel.hideKeyboard.observe(owner, new EventObserver<>(ignored -> hideKeyboard()));
    }
}
