package com.instahello.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    Button actionButton;

    SongsAdapter songsAdapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarColor(getResources().getColor(R.color.primary));

        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(MainActivity.this);
        actionButton = findViewById(R.id.addButton);

        recyclerView = findViewById(R.id.recyclerView);

        songsAdapter = new SongsAdapter(MainActivity.this, new ArrayList<>());

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(songsAdapter);

        actionButton.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, ScreenTwo.class);
            startActivity(intent);
        });


        setStatusBarColor(getResources().getColor(R.color.primary));

        DatabaseReference db = FirebaseDatabase.getInstance().getReference("songs");


        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Song> songs = new ArrayList<>();
                for (DataSnapshot s:snapshot.getChildren()) {
                    Song song = new Song(s.child("title").getValue().toString(), s.child("description").getValue().toString());
                    songs.add(song);
                }
                songsAdapter.addItems(songs);
                songsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }

    private void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(color);
        }
    }



}