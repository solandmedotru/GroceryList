package ru.solandme.grocerylist.main.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import ru.solandme.grocerylist.R;
import ru.solandme.grocerylist.model.ShoppingList;

public class AddListDialog extends DialogFragment implements TextView.OnEditorActionListener {

    private EditText addName;

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
        // Get field from view
        addName = (EditText) view.findViewById(R.id.txt_list_name);
        addName.setOnEditorActionListener(this);
        // Fetch arguments from bundle and set title
        String name = getArguments().getString("name", "Enter Name");
        getDialog().setTitle(name);
        // Show soft keyboard automatically and request focus to field
        addName.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text back to activity through the implemented listener
            AddListDialogListener listener = (AddListDialogListener) getActivity();

            ShoppingList shoppingList = new ShoppingList();
            shoppingList.setName(addName.getText().toString());
            shoppingList.setOwner("Unauthorised user");

            listener.onFinishAddListDialog(shoppingList);
            // Close the dialog and return back to the parent activity
            dismiss();
            return true;
        }
        return false;
    }
}
