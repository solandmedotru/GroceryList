package ru.solandme.grocerylist.main.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.solandme.grocerylist.R;
import ru.solandme.grocerylist.model.Grocery;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.ViewHolder> {
    private List<Grocery> groceryList;

    public GroceryAdapter(List<Grocery> groceryList) {
        this.groceryList = groceryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.groceryNameField.setText(groceryList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return groceryList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView groceryNameField;

        ViewHolder(View itemView) {
            super(itemView);
            groceryNameField = (TextView) itemView.findViewById(R.id.grocery_name);
        }
    }
}
