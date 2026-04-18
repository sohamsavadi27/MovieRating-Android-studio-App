package com.example.movierating1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText etMovieName;
    RatingBar rabRating;
    Button btnSubmit, btnView;
    TextView tvMsg;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etMovieName = findViewById(R.id.etMovieName);
        rabRating = findViewById(R.id.rabRating);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnView = findViewById(R.id.btnView);
        tvMsg = findViewById(R.id.tvMsg);

        dbRef = FirebaseDatabase.getInstance().getReference("movies");

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mn = etMovieName.getText().toString();

                if (mn.isBlank()) {
                    Toast.makeText(MainActivity.this,
                            "movie name is empty", Toast.LENGTH_SHORT).show();
                    etMovieName.requestFocus();
                    return;
                }

                String rating = String.valueOf(rabRating.getRating());
                String id = dbRef.push().getKey();

                dbRef.child(id).child("mn").setValue(mn);
                dbRef.child(id).child("rating").setValue(rating)
                        .addOnSuccessListener(a -> {
                            Toast.makeText(MainActivity.this,
                                    "Saved!", Toast.LENGTH_SHORT).show();
                            etMovieName.setText("");
                            etMovieName.requestFocus();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(MainActivity.this,
                                    "Error", Toast.LENGTH_SHORT).show();
                        });
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String data = "";

                        for (DataSnapshot ds : snapshot.getChildren()) {
                            String mn = ds.child("mn").getValue(String.class);
                            String rating = ds.child("rating").getValue(String.class);

                            data += "Movie: " + mn + "\nRating: " + rating + "\n\n";
                        }

                        tvMsg.setText(data);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this,
                                "issue " + error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}