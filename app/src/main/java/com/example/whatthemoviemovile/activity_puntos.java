package com.example.whatthemoviemovile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.firestore.FirebaseFirestore;

public class activity_puntos extends AppCompatActivity {

    FirebaseFirestore db=FirebaseFirestore.getInstance();
    TextView tvP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_puntos);
        tvP=findViewById(R.id.ViewPoints);
        Intent intent=getIntent();
        String UsrEmail=intent.getStringExtra("UsrEmail");
        getPoints(UsrEmail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.pt), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button back=findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(activity_puntos.this,select_gen.class);
                intent.putExtra("UsrEmail",UsrEmail);
                startActivity(intent1);
            }
        });
    }

    public void getPoints(String UsrEmail){
        db.collection("UsrMovies")
                .whereEqualTo("email", UsrEmail)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        String points = queryDocumentSnapshots.getDocuments().get(0).getString("points");
                        tvP.setText(points);
                    }
                });
    }
}
