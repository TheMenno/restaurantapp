package com.example.menno_000.restaurant;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;

public class MenuItemActivity extends AppCompatActivity {

    String name;
    String description;
    Float price;
    String image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuitem);

        // Get data from previous screen
        name = (String) getIntent().getSerializableExtra("name");
        description = (String) getIntent().getSerializableExtra("description");
        price = (Float) getIntent().getSerializableExtra("price");
        image = (String) getIntent().getSerializableExtra("image");

        // Find Views
        TextView nameView = findViewById(R.id.name);
        TextView descriptionView = findViewById(R.id.description);
        TextView priceView = findViewById(R.id.price);
        ImageView imageView = findViewById(R.id.image);

        // Set data in the Views
        nameView.setText(name);
        descriptionView.setText(description);
        priceView.setText("$" + price + "0");
        Picasso.get().load(image).into(imageView);
    }
}
