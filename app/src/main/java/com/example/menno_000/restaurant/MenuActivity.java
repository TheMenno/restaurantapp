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
            MenuItem selected_menu = (MenuItem) menu_list.getItemAtPosition(i);
            String menu_name = selected_menu.getName();

            Intent intent = new Intent(MenuActivity.this, MenuActivity.class);
            intent.putExtra("menu", menu_name);

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
