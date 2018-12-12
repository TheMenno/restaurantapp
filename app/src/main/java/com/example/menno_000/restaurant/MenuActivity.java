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

        Intent intent = getIntent();
        chosen_category = intent.getStringExtra("category");

        MenuRequest request = new MenuRequest(this);
        request.getMenu(this);

        menu_list = findViewById(R.id.menu_list);
        menu_list.setOnItemClickListener(new toNext());
    }

    private class toNext implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int i, long id) {

            selected_menu = (MenuItem) menu_list.getItemAtPosition(i);

            String name = selected_menu.getName();
            String description = selected_menu.getDescription();
            Float price = selected_menu.getPrice();
            String image = selected_menu.getImageurl();

            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("description", description);
            intent.putExtra("price", price);
            intent.putExtra("image", image);

            startActivity(intent);
        }
    }

    @Override
    public void gotMenu(ArrayList<MenuItem> menuItems) {
        MenuItemAdapter adapter = new MenuItemAdapter(this, menuItems);
        menu_list = findViewById(R.id.menu_list);
        menu_list.setAdapter(adapter);
    }

    @Override
    public void gotMenuError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
