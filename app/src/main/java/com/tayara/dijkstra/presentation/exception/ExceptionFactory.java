package com.tayara.dijkstra.presentation.exception;

import androidx.annotation.StringRes;



public class ExceptionFactory {

    private String UNKNOWN = "Unknown error";

    @StringRes
    public static int getString(Exception e) {
        return 0;
    }

    @StringRes
    public static int getString(Throwable throwable) {
        return getString((Exception) throwable);
    }
}
