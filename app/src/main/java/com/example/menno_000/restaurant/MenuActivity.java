package com.example.menno_000.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback {

    ListView menu_list;
    MenuItem selected_menu;
    static String chosen_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Get data from previous screen
        Intent intent = getIntent();
        chosen_category = intent.getStringExtra("category");

        // Get the menu through MenuRequest
        MenuRequest request = new MenuRequest(this);
        request.getMenu(this);

        // Set listener on the list
        menu_list = findViewById(R.id.menu_list);
        menu_list.setOnItemClickListener(new toNext());
    }

    private class toNext implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int i, long id) {

            // Retrieve the chosen menu item
            selected_menu = (MenuItem) menu_list.getItemAtPosition(i);

            // Get data about the menu item
            // (I could not pass an JournalEntry through intent)
            String name = selected_menu.getName();
            String description = selected_menu.getDescription();
            Float price = selected_menu.getPrice();
            String image = selected_menu.getImageurl();

            // Pass the data to the next screen
            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("description", description);
            intent.putExtra("price", price);
            intent.putExtra("image", image);

            // Go to the next screen
            startActivity(intent);
        }
    }

    // Set the adapter for the menu items
    @Override
    public void gotMenu(ArrayList<MenuItem> menuItems) {
        MenuItemAdapter adapter = new MenuItemAdapter(this, menuItems);
        menu_list = findViewById(R.id.menu_list);
        menu_list.setAdapter(adapter);
    }

    // Error message
    @Override
    public void gotMenuError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
