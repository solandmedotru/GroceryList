package ru.solandme.grocerylist.main.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ru.solandme.grocerylist.R;
import ru.solandme.grocerylist.model.ShoppingList;

public class AddListDialog extends DialogFragment implements View.OnClickListener {

    private EditText addName;
    private Button btnAdd;
    private Button btnCancel;

    public AddListDialog() {
    }

    public interface AddListDialogListener {
        void onFinishAddListDialog(ShoppingList shoppingList);
    }

    public static AddListDialog newInstance(String name) {
        AddListDialog frag = new AddListDialog();
        Bundle args = new Bundle();
        args.putString("name", name);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return getActivity().getLayoutInflater().inflate(R.layout.add_list_dialog, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addName = (EditText) view.findViewById(R.id.txt_list_name);
        btnAdd = (Button) view.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);
        btnCancel = (Button) view.findViewById(R.id.btn_cancel);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                String name = addName.getText().toString();
                onShoppingListCreated(name);
                dismiss();
                break;
            default:
                dismiss();
        }
    }

    private void onShoppingListCreated(String name) {
        AddListDialogListener listener = (AddListDialogListener) getActivity();
        ShoppingList shoppingList = new ShoppingList(name);
        listener.onFinishAddListDialog(shoppingList);
    }
}
