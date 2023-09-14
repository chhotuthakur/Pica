package com.amni.pica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private AdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        MobileAds.initialize(this, initializationStatus -> {
            // Initialization completed. You can now load ads.
        });
        adView = findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        // Initialize Firebase
        FirebaseApp.initializeApp(this);

// Reference to Firebase Database
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("categories");

// Reference to the ChipGroup
        ChipGroup chipGroup = findViewById(R.id.chipGroup);

// Read categories from Firebase
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                chipGroup.removeAllViews();
                for (DataSnapshot categorySnapshot : dataSnapshot.getChildren()) {
                    String categoryName = categorySnapshot.getKey();
                    Chip chip = new Chip(HomeActivity.this);
                    chip.setText(categoryName);
                    chip.setClickable(true);
                    chip.setOnClickListener(view -> {
                        // Handle chip click, filter and display images of the selected category
                        String selectedCategory = ((Chip) view).getText().toString();
                        fetchImagesByCategory(selectedCategory);
                    });
                    chipGroup.addView(chip);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
            }
        });

    }

    private void fetchImagesByCategory(String category) {
        DatabaseReference imagesRef = FirebaseDatabase.getInstance().getReference("images");
        Query query = imagesRef.orderByChild("category").equalTo(category);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<ImageModel> imageList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ImageModel imageModel = snapshot.getValue(ImageModel.class);
                    imageList.add(imageModel);
                }
                // Create a RecyclerView adapter and set it with the imageList
                RecyclerView recyclerView = findViewById(R.id.img_rec);
                ImAdapter imageAdapter = new ImAdapter(imageList);
                recyclerView.setAdapter(imageAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
            }
        });
    }

}