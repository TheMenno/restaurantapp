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
    Callback active;

    public interface Callback {
        void gotMenu(ArrayList<MenuItem> menuItems);
        void gotMenuError(String message);
    }

    public MenuRequest(Context context) {
        this.context = context;
    }

    void getMenu(Callback activity) {
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                "https://resto.mprog.nl/menu", null, this,
                this);
        queue.add(jsonObjectRequest);

        active = activity;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        active.gotMenuError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {

        String chosen_category = MenuActivity.chosen_category;

        try {
            JSONArray raw_menu = response.getJSONArray("items");
            ArrayList<MenuItem> menus = new ArrayList<>();

            for (int i = 0; i < raw_menu.length(); i++) {
                JSONObject menu = raw_menu.getJSONObject(i);

                if (menu.getString("category").equals(chosen_category)) {
                    String name = menu.getString("name");
                    String description = menu.getString("description");
                    String imageurl = menu.getString("image_url");
                    Integer price = menu.getInt("price");
                    String category = chosen_category;

                    MenuItem menuItem = new MenuItem(name, description, imageurl, price, category);
                    menus.add(menuItem);

                    active.gotMenu(menus);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
            active.gotMenuError("JSONException");
        }

    }
}
