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
    private CameraController cameraController;
    private CameraView cameraView;
    private CameraModel cameraModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setListeners();

        cameraView.update(cameraModel);

        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(v);
            }
        };
    }


    private void update(View v) {
        int id = v.getId();
        cameraController.clickCamera(id);
        cameraView.update(cameraModel);
        cameraImageChange();
    }

    private void cameraImageChange(ImageView v) {
        if ( == 1) {
            v.setImageResource(R.drawable.grey_status);
        } else if ( == 2) {
            v.setImageResource(R.drawable.yellow_status);
        } else if ( == 3) {
            v.setImageResource(R.drawable.red_status);
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
        imageStatusCamera1 = (ImageView) findViewById(R.id.imageStatusCamera1);
        imageStatusCamera2 = (ImageView) findViewById(R.id.imageStatusCamera2);
        imageStatusCamera3 = (ImageView) findViewById(R.id.imageStatusCamera3);
        imageStatusCamera4 = (ImageView) findViewById(R.id.imageStatusCamera4);
        imageStatusCamera5 = (ImageView) findViewById(R.id.imageStatusCamera5);
        imageStatusCamera6 = (ImageView) findViewById(R.id.imageStatusCamera6);
        cameraFrameLayoutButton1 = (FrameLayout) findViewById(R.id.cameraFrameLayoutButton1);
        cameraFrameLayoutButton2 = (FrameLayout) findViewById(R.id.cameraFrameLayoutButton2);
        cameraFrameLayoutButton3 = (FrameLayout) findViewById(R.id.cameraFrameLayoutButton3);
        cameraFrameLayoutButton4 = (FrameLayout) findViewById(R.id.cameraFrameLayoutButton4);
        cameraFrameLayoutButton5 = (FrameLayout) findViewById(R.id.cameraFrameLayoutButton5);
        cameraFrameLayoutButton6 = (FrameLayout) findViewById(R.id.cameraFrameLayoutButton6);

        cameraModel = new CameraModel();
        cameraController = new CameraController(cameraModel);
        cameraView = new CameraView();
    }
}
