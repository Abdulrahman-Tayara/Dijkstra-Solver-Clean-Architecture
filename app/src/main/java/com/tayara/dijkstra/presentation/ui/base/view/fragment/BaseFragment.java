package com.tayara.dijkstra.presentation.ui.base.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.tayara.dijkstra.presentation.ui.base.view.IBaseView;
import com.tayara.dijkstra.presentation.ui.base.view.activity.BaseActivity;


abstract public class BaseFragment<DB extends ViewDataBinding> extends Fragment implements IBaseView {

    protected View rootView;
    protected DB binding;
    protected BaseActivity activity;

    protected abstract int getLayoutId();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.activity = (BaseActivity) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
            binding.setLifecycleOwner(this);
            binding.executePendingBindings();
        } catch (Exception e) {
            rootView = inflater.inflate(getLayoutId(), container, false);
        }
        return rootView;
    }


    // Base methods

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(int stringId) {
        showMessage(getString(stringId));
    }

    @Override
    public void showDialogMessage(String title, String message) {
        new AlertDialog.Builder(getContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, (dialog, id) -> dialog.dismiss())
                .setNegativeButton(android.R.string.cancel, (dialog, id) -> dialog.dismiss())
                .create().show();
    }

    @Override
    public void showDialogMessage(int title, int message) {
        showDialogMessage(getString(title), getString(message));
    }

    @Override
    public void showDialogMessage(String message) {
        showDialogMessage("", message);
    }

    @Override
    public void showDialogMessage(int message) {
        showDialogMessage("", getString(message));
    }

    @Override
    public void hideKeyboard() {
        View currentFocus = getActivity().getCurrentFocus();
        if (currentFocus == null)
            currentFocus = new View(getContext());
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
    }

    public void changeToolbarTitle(String title) {
        if (activity != null)
            activity.changeToolbarTitle(title);
    }
}
