package com.tayara.dijkstra.presentation.ui.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

abstract public class BaseListAdapter<T, VH extends BaseViewHolder<T, ?>>
        extends RecyclerView.Adapter<VH> {

    protected Context context;
    private List<T> data;

    public BaseListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        T item = data != null && data.size() > position ? data.get(position) : null;
        holder.onBind(item, position);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    protected LayoutInflater provideLayoutInflater() {
        return LayoutInflater.from(context);
    }

    public List<T> getData() {
        return data;
    }


    // Clear previous data, then add the new data
    public void submitData(List<T> data) {
        if (data == null)
            return;
        if (this.data == null)
            this.data = new ArrayList<>();
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    // Add the data to old data
    public void insertData(List<T> data) {
        if (data == null)
            return;
        if (this.data == null)
            this.data = new ArrayList<>();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void insertItem(T item) {
        if (this.data == null)
            this.data = new ArrayList<>();
        this.data.add(item);
        notifyItemInserted(data.size() - 1);
    }

    public void removeItem(T item) {
        if (item == null || data == null) return;

        int index = data.indexOf(item);

        if (index == -1) return; // not found
        data.remove(index);
        notifyItemRemoved(index);
        notifyItemRangeChanged(index, data.size() - 1);
    }

    public boolean isEmptyData() {
        return data == null || data.isEmpty();
    }

}







