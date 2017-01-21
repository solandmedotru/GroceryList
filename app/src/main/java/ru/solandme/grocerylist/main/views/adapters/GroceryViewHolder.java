package ru.solandme.grocerylist.main.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ru.solandme.grocerylist.R;


public class GroceryViewHolder extends RecyclerView.ViewHolder {
    public TextView groceryNameField;

    public GroceryViewHolder(View itemView) {
        super(itemView);
        groceryNameField = (TextView) itemView.findViewById(R.id.grocery_name);
    }
}