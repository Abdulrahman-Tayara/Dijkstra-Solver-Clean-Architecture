package com.tayara.dijkstra.presentation.ui.common;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class SimpleSwipeToDelete {
    private RecyclerView recyclerView;
    private OnSwiped onSwiped;

    private ItemTouchHelper itemTouchHelper;
    private ItemTouchHelper.SimpleCallback callback;

    public SimpleSwipeToDelete(RecyclerView recyclerView, OnSwiped onSwiped) {
        this.recyclerView = recyclerView;
        this.onSwiped = onSwiped;
        init();
    }

    private void init() {
        callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                onSwiped.onSwiped(viewHolder.getAdapterPosition(), direction);
            }
        };
        itemTouchHelper = new ItemTouchHelper(callback);
    }

    public void enable() {
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public void disable() {
        itemTouchHelper.attachToRecyclerView(null);
    }

    public interface OnSwiped {
        void onSwiped(int position, int direction);
    }
}
