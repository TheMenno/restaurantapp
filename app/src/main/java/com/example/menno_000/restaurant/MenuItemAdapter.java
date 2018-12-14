package com.example.menno_000.restaurant;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MenuItemAdapter extends ArrayAdapter<MenuItem> {

    public Context context;
    ArrayList item_list;

    // Constructor
    public MenuItemAdapter(Context context, ArrayList<MenuItem> menuItems) {
        super(context,0 ,menuItems);
        this.context = context;
        this.item_list = menuItems;
    }


    // Adapter for the menu items
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent){

        // Set the corresponding layout file
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, parent,
                    false);
        }

        // Retrieve menu item
        MenuItem item = getItem(position);

        // Find Views
        TextView nameView = convertView.findViewById(R.id.name);
        ImageView imageView = convertView.findViewById(R.id.image);

        // Set data
        String name = item.getName();
        nameView.setText(name);

        // Download image and set it
        Picasso.get().load(item.getImageurl()).into(imageView);

        return convertView;
    };
}
