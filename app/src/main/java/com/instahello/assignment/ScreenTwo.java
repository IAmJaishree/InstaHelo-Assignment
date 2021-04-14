package com.instahello.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.UUID;

import javax.microedition.khronos.egl.EGLDisplay;

public class ScreenTwo extends AppCompatActivity {


    EditText titleEditText;
    EditText descriptionEditText;

    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarColor(getResources().getColor(R.color.primary));
        setContentView(R.layout.activity_screen_two);
        FirebaseApp.initializeApp(ScreenTwo.this);


        titleEditText = findViewById(R.id.title);
        descriptionEditText = findViewById(R.id.description);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(v->{
            try{
                DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference("songs");
                String uid = UUID.randomUUID().toString();

                HashMap<String, String> map = new HashMap<>();
                map.put("title",titleEditText.getText().toString());
                map.put("description",descriptionEditText.getText().toString());

                firebaseDatabase.child(uid).setValue(map);

//                firebaseDatabase.child(uid)
//                        .child("title")
//                        .setValue(titleEditText.getText().toString());
//                firebaseDatabase.child(uid)
//                        .child("description")
//                        .setValue(descriptionEditText.getText().toString());
                Toast.makeText(ScreenTwo.this, "Details successfully saved...", Toast.LENGTH_SHORT).show();

                finish();
            }catch (Exception e){
                Toast.makeText(ScreenTwo.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }
    private void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(color);
        }
    }



}