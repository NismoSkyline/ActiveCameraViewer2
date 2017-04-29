package com.projects.thirtyseven.activecameraviewer2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setListeners();
    }

    private void setListeners() {

    }

    private void init() {
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Camera");
    }
}
