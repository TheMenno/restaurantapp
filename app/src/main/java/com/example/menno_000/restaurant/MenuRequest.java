package com.example.menno_000.restaurant;

import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    Context context;
    Callback callback;

    // Callback to functions from MenuActivity
    public interface Callback {
        void gotMenu(ArrayList<MenuItem> menuItems);
        void gotMenuError(String message);
    }


    // Constructor
    public MenuRequest(Context context) {
        this.context = context;
    }


    // Retrieve the menu items
    void getMenu(Callback cb) {

        // Set up the queue for JSON requests
        RequestQueue queue = Volley.newRequestQueue(context);

        // Send a JSON request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                "https://resto.mprog.nl/menu", null, this,
                this);
        queue.add(jsonObjectRequest);

        // Set up the callback to previous screen
        callback = cb;
    }


    // Error message
    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotMenuError(error.getMessage());
    }


    // Reacts when a response arrives from the target website
    @Override
    public void onResponse(JSONObject response) {

        // Get the chosen category from previous screen
        String chosen_category = MenuActivity.chosen_category;

        try {
            JSONArray raw_menu = response.getJSONArray("items");
            ArrayList<MenuItem> menus = new ArrayList<>();

            // Iterate through all items in the dataset
            for (int i = 0; i < raw_menu.length(); i++) {
                JSONObject menu = raw_menu.getJSONObject(i);

                // Only pick menu items with the chosen category
                if (menu.getString("category").equals(chosen_category)) {

                    // Get data from the dataset
                    String name = menu.getString("name");
                    String description = menu.getString("description");
                    String imageurl = menu.getString("image_url");
                    Integer price = menu.getInt("price");
                    String category = chosen_category;

                    // Combine the data into a MenuItem
                    MenuItem menuItem = new MenuItem(name, description, imageurl, price, category);
                    menus.add(menuItem);

                    // Return to the Activity
                    callback.gotMenu(menus);
                }
            }

        } catch (JSONException e) {

            // Error message
            e.printStackTrace();
            callback.gotMenuError("JSONException");
        }

    }
}
