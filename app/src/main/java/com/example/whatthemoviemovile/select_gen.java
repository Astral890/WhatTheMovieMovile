package com.example.whatthemoviemovile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class select_gen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_select_gen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.select), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ImageView scifi=findViewById(R.id.scifi);
        ImageView comedy=findViewById(R.id.comedy);
        ImageView animated=findViewById(R.id.animated);
        ImageView horror=findViewById(R.id.horror);
        scifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(select_gen.this, Scifi.class);
                startActivity(intent);
            }
        });
        comedy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(select_gen.this, comedy.class);
                startActivity(intent);
            }
        });
        horror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(select_gen.this, horror.class);
                startActivity(intent);
            }
        });
        animated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(select_gen.this, animated.class);
                startActivity(intent);
            }
        });

    }
}