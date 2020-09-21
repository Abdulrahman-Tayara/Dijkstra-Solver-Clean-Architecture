package com.tayara.dijkstra.presentation.ui.dijkstra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.tayara.dijkstraapplication.R;
import com.tayara.dijkstraapplication.databinding.ListItemNodeBinding;
import com.tayara.dijkstra.presentation.model.Node;
import com.tayara.dijkstra.presentation.ui.base.adapter.BaseListAdapter;
import com.tayara.dijkstra.presentation.ui.base.adapter.BaseViewHolder;
import com.tayara.dijkstra.presentation.ui.common.OnItemClick;

public class NodeListAdapter extends BaseListAdapter<Node, NodeListAdapter.NodeViewHolder> {

    private OnItemClick<Node> onItemClick;
    public NodeListAdapter(Context context, OnItemClick<Node> onItemClick) {
        super(context);
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public NodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemNodeBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.list_item_node,
                parent,
                false
        );
        return new NodeViewHolder(binding);
    }

    public class NodeViewHolder extends BaseViewHolder<Node, ListItemNodeBinding> {
        public NodeViewHolder(@NonNull ListItemNodeBinding itemView) {
            super(itemView);
        }

        @Override
        public void onBind(Node item, int position) {
            binding.setNode(item);
            itemView.setOnClickListener(view -> onItemClick.onClick(item, getAdapterPosition()));
        }
    }
}
