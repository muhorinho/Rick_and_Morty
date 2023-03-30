package com.muhammedbuga.rickandmorty;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
public class DetailsActivity extends AppCompatActivity{
    LinearLayout mainLinearLayout;
    final String CHARACTER= "character";
    final String LOCATION= "location";
    AppManager appManager = new AppManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_main);
        String type = getIntent().getExtras().getString("type");
        String value = "";
        mainLinearLayout = findViewById(R.id.detail_main_layout);
        JSONObject json = null;

        switch(type) {
            case CHARACTER:
                value = getIntent().getExtras().getString(CHARACTER);
                try {
                    json = new JSONObject(value);
                    AddCharacter(json, 1000);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case LOCATION:
                value = getIntent().getExtras().getString(LOCATION);
                try {
                    json = new JSONObject(value);
                    AddLocation(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    void AddCharacter(JSONObject character, int imageSize)
    {
        try {
            ImageView imageView = appManager.CreateImageView(imageSize, this, mainLinearLayout);
            String image = character.getString("image");
            Picasso.get().load(image).into(imageView);
            imageView.getLayoutParams().height = imageSize;
            TextView textView = appManager.CreateTextView(this, mainLinearLayout);
            int id = character.getInt("id");
            String name = character.getString("name");
            String status = character.getString("status");
            String species = character.getString("species");
            String char_type = character.getString("type");
            String gender = character.getString("gender");
            String text = "ID: " + id + "\n" +
                    "Name: " + name + "\n" +
                    "Status: " + status + "\n" +
                    "Species: " + species + "\n" +
                    "Type: " + char_type + "\n" +
                    "Gender: " + gender + "\n";
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(30.0f);
            textView.setText(text);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void AddLocation(JSONObject location)
    {
        try {
            TextView textView = appManager.CreateTextView(this, mainLinearLayout);
            int id = location.getInt("id");
            String name = location.getString("name");
            String location_type = location.getString("type");
            String dimension = location.getString("dimension");
            JSONArray residents = location.getJSONArray("residents");

            String text = "ID: " + id + "\n" +
                    "Name: " + name + "\n" +
                    "Type: " + location_type + "\n" +
                    "Dimension: " + dimension + "\n" +
                    "Residents: " + "\n";

            for(int i = 0; i < residents.length(); i++) {
                MakeRequest(residents.getString(i));
            }

            textView.setTextColor(Color.WHITE);
            textView.setTextSize(30.0f);
            textView.setText(text);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    void MakeRequest(String url)
    {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONObject reader = new JSONObject(response);
                        AddCharacter(reader, 600);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {

        });

        queue.add(stringRequest);
    }

}
