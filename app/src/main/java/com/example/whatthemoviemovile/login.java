package com.example.whatthemoviemovile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class login extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText correo=findViewById(R.id.editTextUsername);
        EditText pass=findViewById(R.id.editTextPassword);
        Button login=findViewById(R.id.crear);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("UsrMovies").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document: task.getResult()){
                                String UsrEmail=document.getString("email");
                                String UsrPass=document.getString("pass");
                                if(UsrEmail.equals(correo.getText().toString())&&UsrPass.equals(pass.getText().toString())){
                                    showToast("Login exitoso");
                                    return;
                                }
                            }
                            showToast("Login fallido");
                        }
                    }
                });
            }
        });
    }
    public void showToast(String msg){
        Toast.makeText(login.this,msg,Toast.LENGTH_LONG).show();
    }
}