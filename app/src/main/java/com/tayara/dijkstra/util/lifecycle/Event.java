package com.tayara.dijkstra.util.lifecycle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Data Wrapper that can be handled only once
 *
 * @param <T> data type
 */
public class Event<T> {
    @NonNull
    private T data;

    private boolean isHandled = false;

    public Event(@NonNull T data) {
        this.data = data;
    }

    @Nullable
    T getDataOrNull() {
        if (isHandled)
            return null;
        isHandled = true;
        return data;
    }
}
