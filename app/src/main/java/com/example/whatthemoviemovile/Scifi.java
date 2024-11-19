package com.example.whatthemoviemovile;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

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

        //lamo a la api

        //Consigo la pelicula
        //Movie movie=new Movie(ans.id,ans.title);

        //Consigo las imagenes

        //Las meto en la movie
        //movie.addImages(img.backdrops[0].file_path);

        //Cambio la imagen
        Glide.with(this).load("https://image.tmdb.org/t/p/original/3sh2UA2Q2l7fihgoj1LhFFPTlIM.jpg?api_key=ada074b6a5691631b70bfbcaf68ebad9").into(display);
    }
}