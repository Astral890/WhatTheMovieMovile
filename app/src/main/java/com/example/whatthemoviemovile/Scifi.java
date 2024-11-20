package com.example.whatthemoviemovile;

import static com.example.whatthemoviemovile.utilis.Utils.rand;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
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
    private ImageView display;
    private Button op1, op2, op3;
    private Context context;
    private List<Movie> movies=new ArrayList<>();
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private final String url_api="https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc&with_genres=878&api_key=ada074b6a5691631b70bfbcaf68ebad9";
    private final int win=rand();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_scifi);
        init();
        requestMovieData();
        new Thread(() -> {
            try {
                Thread.sleep(50);
                runOnUiThread(() -> {
                    requestImages(win);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.scifi), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void init(){
        tv=findViewById(R.id.tv12);
        op1=findViewById(R.id.op1);
        op2=findViewById(R.id.op2);
        op3=findViewById(R.id.op3);
        display=findViewById(R.id.imgDisplay);
        context=Scifi.this;
    }
    public void requestMovieData(){
        requestQueue= Volley.newRequestQueue(context);
        stringRequest=new StringRequest(url_api, new Response.Listener<String>() {
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
            for (int i=0;i<3;i++) {
                JSONObject movieData = jsonArray.getJSONObject(i);
                movies.add(new Movie(
                        movieData.getInt("id"),
                        movieData.getString("title")
                ));
            }
            if(!movies.isEmpty()){
                op1.setText(movies.get(0).getTitle());
                op2.setText(movies.get(1).getTitle());
                op3.setText(movies.get(2).getTitle());
            }else{
                tv.setText("xd");
            }
        }catch (Exception e){
            e.printStackTrace();
            showToast("Mobile detail error");
        }

    }

    public void requestImages(int pos){
        int id=movies.get(win).getId();
        requestQueue= Volley.newRequestQueue(context);
        stringRequest=new StringRequest("https://api.themoviedb.org/3/movie/"+id+"/images?api_key=ada074b6a5691631b70bfbcaf68ebad9", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONArray jsonArray=jsonObject.getJSONArray("backdrops");
                    fillImages(jsonArray);
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

    public void fillImages(JSONArray jsonArray){
        try {
            for (int i=0;i<4;i++) {
                JSONObject movieData = jsonArray.getJSONObject(i);
                movies.get(win).addImages(movieData.getString("file_path"));
            }
            if(!movies.isEmpty()){
                Glide.with(this).load(movies.get(win).getImages(0)).into(display);
            }
        }catch (Exception e){
            e.printStackTrace();
            showToast("Images detail error");
        }
    }

    public void showToast(String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}