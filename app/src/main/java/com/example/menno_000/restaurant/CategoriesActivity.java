package com.example.menno_000.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    ListView category_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        // Retrieve the data from the request function
        CategoriesRequest request = new CategoriesRequest(this);
        request.getCategories(this);

        // Set a listener for the list
        category_list = findViewById(R.id.category_list);
        category_list.setOnItemClickListener(new toNext());
    }

    // Give the chosen category to the next screen
    private class toNext implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapter, View v, int i, long l){
            String chosenCategory = category_list.getItemAtPosition(i).toString();

            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("category", chosenCategory);

            startActivity(intent);
        }
    }

    // Set up an adapter and connect it to the category list
    @Override
    public void gotCategories(ArrayList<String> categories) {

        // Set up a simple adapter for the category item list
        ArrayAdapter categoriesAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, categories);

        // Set the adapter to the category list
        ListView category_list = findViewById(R.id.category_list);
        category_list.setAdapter(categoriesAdapter);
    }

    // Error message
    @Override
    public void gotCategoriesError(String message) {

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
