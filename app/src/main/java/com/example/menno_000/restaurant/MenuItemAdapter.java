package com.example.menno_000.restaurant;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MenuItemAdapter extends ArrayAdapter<MenuItem> {

    Context context;
    ArrayList item_list;

    TextView name;
    ImageView image;

    public MenuItemAdapter(Context context, ArrayList<MenuItem> menuItems) {
        super(context,0 ,menuItems);
        this.context = context;
        this.item_list = menuItems;
    }

    @Override
    public View getView(int i, View v, @NonNull ViewGroup p){
        MenuItem item = getItem(i);

        if(v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, p, false);
        }

        name = v.findViewById(R.id.title);
        name.setText(item.getName());

        image = v.findViewById(R.id.image);
        try {
            URL url = new URL(item.getImageurl());

            try {
                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                image.setImageBitmap(bmp);
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return v;
    };
}
