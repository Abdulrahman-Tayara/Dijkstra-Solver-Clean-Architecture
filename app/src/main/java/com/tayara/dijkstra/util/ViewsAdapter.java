package com.tayara.dijkstra.util;

import android.graphics.Color;
import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.databinding.BindingAdapter;
import androidx.databinding.BindingConversion;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.tayara.dijkstra.presentation.model.Node;
import com.tayara.dijkstra.presentation.ui.base.adapter.BaseListAdapter;

import java.util.List;

public class ViewsAdapter {


    @BindingAdapter("loadData")
    public static void loadData(RecyclerView recyclerView, List<?> data) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter instanceof BaseListAdapter) {
            ((BaseListAdapter) adapter).submitData(data);
        }
    }

    @BindingAdapter("cardParseColor")
    public static void parseColor(CardView cardView, String color) {
        if (color != null && !color.isEmpty()) {
            cardView.setCardBackgroundColor(Color.parseColor(color));
        }
    }

    @BindingAdapter("android:visibility")
    public static void visibility(View view, boolean isVisible) {
        view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }
}
