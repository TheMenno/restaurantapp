package com.example.menno_000.restaurant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuItemActivity extends AppCompatActivity {

    MenuItem item;
    TextView name;
    TextView description;
    TextView price;
    ImageView image;

    String n;
    String d;
    Float p;
    String i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuitem);

        n = (String) getIntent().getSerializableExtra("name");
        d = (String) getIntent().getSerializableExtra("description");
        p = (Float) getIntent().getSerializableExtra("price");
        i = (String) getIntent().getSerializableExtra("image");

        enterData();
    }

    private void enterData() {

        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        price = findViewById(R.id.price);
        image = findViewById(R.id.image);

        name.setText(n);
        description.setText(d);
        price.setText("$"+p+"0");
    }

}
