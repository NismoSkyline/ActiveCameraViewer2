package com.projects.thirtyseven.activecameraviewer2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    FrameLayout cameraLinear1, cameraLinear2, cameraLinear3, cameraLinear4, cameraLinear5, cameraLinear6;
    ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6;
    View.OnClickListener onClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setListeners();
    }

    private void setListeners() {
        cameraLinear1.setOnClickListener(onClickListener);
        cameraLinear2.setOnClickListener(onClickListener);
        cameraLinear3.setOnClickListener(onClickListener);
        cameraLinear4.setOnClickListener(onClickListener);
        cameraLinear5.setOnClickListener(onClickListener);
        cameraLinear6.setOnClickListener(onClickListener);
    }

    private void init() {
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Camera");
    }
}
