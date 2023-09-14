package com.amni.pica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.tabs.TabLayout;
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
    Context context;
    private AdView adView;
    private DatabaseReference databaseReference;

    ChipGroup chipGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        MobileAds.initialize(this, initializationStatus -> {
//            // Initialization completed. You can now load ads.
//        });
//        adView = findViewById(R.id.adView);
//
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adView.loadAd(adRequest);
//
//        recyclerView= findViewById(R.id.img_rec);
//        recyclerView.setAdapter(adapter);
//        chipGroup = findViewById(R.id.chipGroup);
//        databaseReference = FirebaseDatabase.getInstance().getReference("wallpapers/categories/");
//
//
//
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    String key = snapshot.getKey();
//
//                    Chip chip = new Chip(MainActivity.this);
//                    chip.setText(key);
//                    chip.setClickable(true);
//                    chip.setCheckable(true);
//                    chipGroup.addView(chip);
//
//                    // Set an OnClickListener for the chip to handle chip selection
//                    chip.setOnClickListener(v -> {
//
//                        String selectedKey = chip.getText().toString();
//                        updateRecyclerView(selectedKey);
//                    });
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Handle error
//            }
//        });
//    }
//    private void updateRecyclerView(String selectedKey) {
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("wallpapers/category/");
//
//        databaseReference.orderByChild("category").equalTo(selectedKey).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // Parse dataSnapshot to create/update your data list
//                // For example, if you have a list of items, update it here
//                List<ImageItem> filteredData = new ArrayList<>();
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    ImageItem item = snapshot.getValue(ImageItem.class);
//                    filteredData.add(item);
//                }
//
//                // Update your RecyclerView adapter with the filtered data
//                adapter.setData(filteredData);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Handle error
//            }
//        });
    }

        @Override
        protected void onPause() {
            if (adView != null) {
                adView.pause();
            }
            super.onPause();
        }

        @Override
        protected void onResume() {
            super.onResume();
            if (adView != null) {
                adView.resume();
            }
        }

        @Override
        protected void onDestroy() {
            if (adView != null) {
                adView.destroy();
            }
            super.onDestroy();
        }
    }

