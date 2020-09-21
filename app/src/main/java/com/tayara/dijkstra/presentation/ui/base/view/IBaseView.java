package com.tayara.dijkstra.presentation.ui.base.view;

import androidx.annotation.StringRes;

public interface IBaseView {
    void showMessage(String message);

    void showMessage(@StringRes int stringId);

    void showDialogMessage(String title, String message);

    void showDialogMessage(@StringRes int title, @StringRes int message);

    void showDialogMessage(String message);

    void showDialogMessage(@StringRes int message);

    void hideKeyboard();
}
