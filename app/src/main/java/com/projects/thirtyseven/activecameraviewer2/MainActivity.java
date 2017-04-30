package com.projects.thirtyseven.activecameraviewer2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    FrameLayout cameraFrameLayoutButton1,
            cameraFrameLayoutButton2,
            cameraFrameLayoutButton3,
            cameraFrameLayoutButton4,
            cameraFrameLayoutButton5,
            cameraFrameLayoutButton6;
    ImageView imageStatusCamera1,
            imageStatusCamera2,
            imageStatusCamera3,
            imageStatusCamera4,
            imageStatusCamera5,
            imageStatusCamera6;
    View.OnClickListener onClickListener;
    Camera camera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setListeners();

        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                switch (id) {
                    case R.id.cameraFrameLayoutButton1:
                        setImageStatus(getStatusInfo(v));
                        break;
                    case R.id.cameraFrameLayoutButton2:
                        setImageStatus(getStatusInfo(v));
                        break;
                    case R.id.cameraFrameLayoutButton3:
                        setImageStatus(getStatusInfo(v));
                        break;
                    case R.id.cameraFrameLayoutButton4:
                        setImageStatus(getStatusInfo(v));
                        break;
                    case R.id.cameraFrameLayoutButton5:
                        setImageStatus(getStatusInfo(v));
                        break;
                    case R.id.cameraFrameLayoutButton6:
                        setImageStatus(getStatusInfo(v));
                        break;
                }
            }
        };
    }

    private void getStatusInfo(View v) {
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        switch (v.getId()) {
            case R.id.cameraFrameLayoutButton1:
                break;
            case R.id.cameraFrameLayoutButton2:
                break;
            case R.id.cameraFrameLayoutButton3:
                break;
            case R.id.cameraFrameLayoutButton4:
                break;
            case R.id.cameraFrameLayoutButton5:
                break;
            case R.id.cameraFrameLayoutButton6:
                break;
        }
    }

    private void setListeners() {
        cameraFrameLayoutButton1.setOnClickListener(onClickListener);
        cameraFrameLayoutButton2.setOnClickListener(onClickListener);
        cameraFrameLayoutButton3.setOnClickListener(onClickListener);
        cameraFrameLayoutButton4.setOnClickListener(onClickListener);
        cameraFrameLayoutButton5.setOnClickListener(onClickListener);
        cameraFrameLayoutButton6.setOnClickListener(onClickListener);
    }

    private void init() {
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Camera");
        camera = new Camera();
        imageStatusCamera1 = (ImageView) findViewById(R.id.imageStatusCamera1);
        imageStatusCamera2 = (ImageView) findViewById(R.id.imageStatusCamera2);
        imageStatusCamera3 = (ImageView) findViewById(R.id.imageStatusCamera3);
        imageStatusCamera4 = (ImageView) findViewById(R.id.imageStatusCamera4);
        imageStatusCamera5 = (ImageView) findViewById(R.id.imageStatusCamera5);
        imageStatusCamera6 = (ImageView) findViewById(R.id.imageStatusCamera6);
    }
}
