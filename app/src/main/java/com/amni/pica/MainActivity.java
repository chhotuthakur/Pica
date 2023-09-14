package com.amni.pica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private ImageAdapter adapter;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= findViewById(R.id.img_rec);

        List<ImageItem> imageList = new ArrayList<>();

        adapter = new ImageAdapter(this, imageList, item -> {
            // Handle item click here, e.g., open a new activity to display the full image.
            // You can pass the clicked item's data (e.g., image URL) to the new activity.
        });

        recyclerView.setAdapter(adapter);

        // Initialize Firebase and set up a database reference
        FirebaseApp.initializeApp(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("wallpapers/images");

        // Add a ValueEventListener to fetch data from Firebase and update the RecyclerView
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                imageList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ImageItem imageItem = snapshot.getValue(ImageItem.class);
                    if (imageItem != null) {
                        imageList.add(imageItem);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

    }
}