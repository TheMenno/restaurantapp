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

    public Context context;
    ArrayList item_list;

    public MenuItemAdapter(Context context, ArrayList<MenuItem> menuItems) {
        super(context,0 ,menuItems);
        this.context = context;
        this.item_list = menuItems;
    }

    TextView nameView;
    ImageView imageView;

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent){

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, parent,
                    false);
        }

        MenuItem item = getItem(position);

        nameView = convertView.findViewById(R.id.name);
        imageView = convertView.findViewById(R.id.image);

        String name = item.getName();
        nameView.setText(name);

        /*try {
            URL url = new URL(item.getImageurl());

            try {
                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                imageView.setImageBitmap(bmp);
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }*/

        return convertView;
    };
}
