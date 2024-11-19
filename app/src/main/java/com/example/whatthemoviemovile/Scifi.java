package com.example.whatthemoviemovile;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Scifi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_scifi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.scifi), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView display=findViewById(R.id.imgDisplay);

        GetData getData=new GetData();
        getData.execute();
        //lamo a la api

        //Consigo la pelicula
        //Movie movie=new Movie(ans.id,ans.title);

        //Consigo las imagenes

        //Las meto en la movie
        //movie.addImages(img.backdrops[0].file_path);

        //Cambio la imagen
        Glide.with(this).load("https://image.tmdb.org/t/p/original/3sh2UA2Q2l7fihgoj1LhFFPTlIM.jpg?api_key=ada074b6a5691631b70bfbcaf68ebad9").into(display);
    }
    public class GetData extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... strings) {
            String current="";
            try{
                URL url;
                HttpURLConnection urlConnection=null;
                try {
                    url=new URL("https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc&with_genres=878");
                    urlConnection=(HttpURLConnection) url.openConnection();
                    InputStream is=urlConnection.getInputStream();
                    InputStreamReader isr=new InputStreamReader(is);

                    int data=isr.read();
                    while (data!=-1){
                        current+=(char) data;
                        data=isr.read();
                    }
                    return current;

                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }finally {
                    if(urlConnection != null){
                        urlConnection.disconnect();
                    }
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(String s) {
            try{
                JSONObject jsonObject=new JSONObject(s);
                JSONArray jsonArray=jsonObject.getJSONArray("results");
                for( )
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }
}