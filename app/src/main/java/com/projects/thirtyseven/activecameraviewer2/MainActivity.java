package com.projects.thirtyseven.activecameraviewer2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    GridLayout cameraGridLayout;
    ArrayList<FrameLayout> cameraFrameLayoutButtons;
    ArrayList<ImageView> imageStatusCameras;

    View.OnClickListener onClickListener;

    private CameraController cameraController;
    private CameraView cameraView;
    private CameraModel cameraModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

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

    private void init() {
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Camera");

        cameraGridLayout = (GridLayout) findViewById(R.id.cameraGridLayout);
        for (int i = 0; i < cameraGridLayout.getChildCount(); i++){
            FrameLayout button = (FrameLayout) cameraGridLayout.getChildAt(i);
            cameraFrameLayoutButtons.add(button);
            button.setOnClickListener(onClickListener);
            imageStatusCameras.add((ImageView) button.getChildAt(0));
        }

        cameraModel = new CameraModel();
        cameraController = new CameraController(cameraModel);
        cameraView = new CameraView();
    }
}
