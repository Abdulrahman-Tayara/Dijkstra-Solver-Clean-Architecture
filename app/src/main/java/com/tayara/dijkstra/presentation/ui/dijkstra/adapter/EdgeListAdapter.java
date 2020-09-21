package com.tayara.dijkstra.presentation.ui.dijkstra.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.tayara.dijkstra.presentation.model.Edge;
import com.tayara.dijkstra.presentation.model.Node;
import com.tayara.dijkstra.presentation.ui.base.adapter.BaseListAdapter;
import com.tayara.dijkstra.presentation.ui.base.adapter.BaseViewHolder;
import com.tayara.dijkstra.presentation.ui.common.OnItemClick;
import com.tayara.dijkstraapplication.R;
import com.tayara.dijkstraapplication.databinding.ListItemEdgeBinding;

public class EdgeListAdapter extends BaseListAdapter<Edge, EdgeListAdapter.EdgeViewHolder> {

    private OnItemClick<Node> onNodeClick;

    public EdgeListAdapter(Context context, OnItemClick<Node> onNodeClick) {
        super(context);
        this.onNodeClick = onNodeClick;
    }

    @NonNull
    @Override
    public EdgeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemEdgeBinding binding = DataBindingUtil.inflate(provideLayoutInflater(), R.layout.list_item_edge, parent, false);

        return new EdgeViewHolder(binding);
    }

    class EdgeViewHolder extends BaseViewHolder<Edge, ListItemEdgeBinding> {

        public EdgeViewHolder(@NonNull ListItemEdgeBinding itemView) {
            super(itemView);
            setIsRecyclable(false);
        }

        @Override
        public void onBind(Edge item, int position) {
            binding.setEdge(item);
            binding.startNode.getRoot().setOnClickListener(view -> {
                onNodeClick.onClick(item.getStartNode(), position);
            });
            binding.endNode.getRoot().setOnClickListener(view -> {
                onNodeClick.onClick(item.getEndNode(), position);
            });
        }
    }
}
