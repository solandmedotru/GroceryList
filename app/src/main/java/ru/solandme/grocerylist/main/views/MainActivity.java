package ru.solandme.grocerylist.main.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ru.solandme.grocerylist.R;
import ru.solandme.grocerylist.model.ShoppingList;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference baseRef = database.getReference("shoppingList");

    FirebaseListAdapter<ShoppingList> firebaseListAdapter;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        firebaseListAdapter = new FirebaseListAdapter<ShoppingList>(this, ShoppingList.class, android.R.layout.two_line_list_item, baseRef) {
            @Override
            protected void populateView(View view, ShoppingList shoppingList, int position) {
                ((TextView) view.findViewById(android.R.id.text1)).setText(shoppingList.getName());
                ((TextView) view.findViewById(android.R.id.text2)).setText(shoppingList.getOwner());
            }
        };

        // Set the adapter for the list view
        mDrawerList.setAdapter(firebaseListAdapter);
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Fragment fragment = new MainFragment();
                Bundle args = new Bundle();
                String key = firebaseListAdapter.getRef(position).getKey();
                args.putString("key", key);
                fragment.setArguments(args);

                // Insert the fragment by replacing any existing fragment
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .commit();

                // Highlight the selected item, update the title, and close the drawer
                mDrawerList.setItemChecked(position, true);
                mDrawerLayout.closeDrawer(mDrawerList);
            }
        });
    }
}


