package com.example.whatthemoviemovile;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Scifi extends AppCompatActivity {

    private TextView tv;
    private Context context;
    private List<Movie> movies=new ArrayList<>();
    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_scifi);
        init();
        requestMovieData();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.scifi), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView display=findViewById(R.id.imgDisplay);
        Glide.with(this).load("https://image.tmdb.org/t/p/original/3sh2UA2Q2l7fihgoj1LhFFPTlIM.jpg?api_key=ada074b6a5691631b70bfbcaf68ebad9").into(display);
    }
    public void init(){

        tv=findViewById(R.id.tv12);
        context=Scifi.this;
    }
    public void requestMovieData(){
        requestQueue= Volley.newRequestQueue(context);
        stringRequest=new StringRequest("https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc&with_genres=878&api_key=ada074b6a5691631b70bfbcaf68ebad9", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONArray jsonArray=jsonObject.getJSONArray("results");
                    fetchData(jsonArray);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showToast("API call error");
            }
        });
        requestQueue.add(stringRequest);
    }

    private void fetchData(JSONArray jsonArray){
        try {
            JSONObject movie=jsonArray.getJSONObject(0);
            movies.add(new Movie(
                    movie.getInt("id"),
                    movie.getString("title")
            ));
            if(!movies.isEmpty()){
                tv.setText(movies.get(0).getTitle());
            }else{
                tv.setText("xd");
            }
        }catch (Exception e){
            e.printStackTrace();
            showToast("Mobile detail error");
        }

    }

    public void showToast(String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}