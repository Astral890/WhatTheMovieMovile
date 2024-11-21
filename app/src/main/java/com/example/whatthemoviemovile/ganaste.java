package com.example.whatthemoviemovile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.firestore.FirebaseFirestore;

public class ganaste extends AppCompatActivity {

    FirebaseFirestore db= FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ganaste);
        Intent intent=getIntent();
        String UsrEmail=intent.getStringExtra("UsrEmail");
        addPoints(UsrEmail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.win), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ImageView bt1=findViewById(R.id.bt1);
        TextView gan=findViewById(R.id.gan);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ganaste.this,select_gen.class);
                intent.putExtra("UsrEmail",UsrEmail);
                startActivity(intent);
            }
        });
    }
    public void addPoints(String UsrEmail){
        db.collection("UsrMovies")
                .whereEqualTo("email", UsrEmail)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        String documentId = queryDocumentSnapshots.getDocuments().get(0).getId();
                        int currentPoints = Integer.parseInt(queryDocumentSnapshots.getDocuments().get(0).getString("points"));
                        int updatedPoints = currentPoints + 5;
                        db.collection("UsrMovies").document(documentId)
                                .update("points", String.valueOf(updatedPoints))
                                .addOnSuccessListener(aVoid -> {
                                    showToast("Puntos actualizados");
                                })
                                .addOnFailureListener(e -> {
                                    showToast("Error al actualizar");
                                });
                    } else {
                        showToast("No usario.");
                    }
                })
                .addOnFailureListener(e -> {
                    showToast("Error al obtener");
                });
    }
    public void showToast(String msg){
        Toast.makeText(ganaste.this,msg,Toast.LENGTH_LONG).show();
    }
}