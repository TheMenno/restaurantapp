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
    Callback active;

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    public CategoriesRequest(Context context) {
        this.context = context;
    }

    void getCategories(Callback activity) {
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                "https://resto.mprog.nl/categories", null, this,
                this);
        queue.add(jsonObjectRequest);

        active = activity;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        active.gotCategoriesError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray raw_categories = response.getJSONArray("categories");
            ArrayList<String> categories = new ArrayList<>();

            for (int i = 0; i < raw_categories.length(); i++){
                String category = raw_categories.getString(i);
                categories.add(category);

                active.gotCategories(categories);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            active.gotCategoriesError("JSONException");
        }
    }

}
