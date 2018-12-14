package com.example.menno_000.restaurant;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    Context context;
    Callback callback;

    // A callback to the main activity
    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    // Constructor
    public CategoriesRequest(Context context) {
        this.context = context;
    }

    // Get the categories from the 'database'
    void getCategories(Callback activity) {

        // Set up a queue
        RequestQueue queue = Volley.newRequestQueue(context);

        // Get the right source and add it to the queue
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                "https://resto.mprog.nl/categories", null, this,
                this);
        queue.add(jsonObjectRequest);

        callback = activity;
    }

    // Error message
    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotCategoriesError(error.getMessage());
    }

    // Get the data when the 'database' can be accessed
    @Override
    public void onResponse(JSONObject response) {
        try {
            // Get the right subfile
            JSONArray raw_categories = response.getJSONArray("categories");
            // Set up a container
            ArrayList<String> categories = new ArrayList<>();

            // Iterate through the categories and add them to the container
            for (int i = 0; i < raw_categories.length(); i++){
                String category = raw_categories.getString(i);
                categories.add(category);

                callback.gotCategories(categories);
            }

        } catch (JSONException e) {
            // Error message
            e.printStackTrace();
            callback.gotCategoriesError("JSONException");
        }
    }

}
