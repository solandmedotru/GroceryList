package ru.solandme.grocerylist.main.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ru.solandme.grocerylist.R;

public class GroceryViewHolder extends RecyclerView.ViewHolder {
    View itemView;
    public TextView groceryNameField;

    public GroceryViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;

        groceryNameField = (TextView) itemView.findViewById(R.id.grocery_name);
    }

    public void setGroceryName(String name) {
        groceryNameField.setText(name);
    }
}