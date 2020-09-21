package com.tayara.dijkstra.presentation.ui.base.adapter;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

abstract public class BaseViewHolder<T, DB extends ViewDataBinding> extends RecyclerView.ViewHolder {

    protected DB binding;

    public BaseViewHolder(@NonNull DB itemView) {
        super(itemView.getRoot());
        this.binding = itemView;
    }

    abstract public void onBind(T item, int position);
}
