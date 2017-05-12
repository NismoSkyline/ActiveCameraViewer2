package com.projects.thirtyseven.activecameraviewer2;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridLayout cameraGridLayout;
    ArrayList<FrameLayout> cameraFrameLayoutButtons;
    ArrayList<String> listOfCameraNames;
    ArrayList<String> normalCameraNames;
    Camera[] camList;
    View.OnClickListener onClickListener;
    Button toViewerActivity;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    Camera camera;
    ProgressBar progressBar;
    TextView textViewProgress;
    Camera newCam;
    ImageView image;

    private CameraController cameraController;
    private CameraModel cameraModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraController.getId(v.getId());
                for (FrameLayout button : cameraFrameLayoutButtons) {
                    if (button.getId() == cameraModel.getCameraID()) {
                        for (int i = 0; i < cameraFrameLayoutButtons.size(); i++) {
                            if (cameraFrameLayoutButtons.get(i).getId() == button.getId()) {
                                newCam = camList[i];
                                cameraController.clickCamera(Integer.valueOf(i + 1), newCam.getStatus(), camList);
                                Toast.makeText(MainActivity.this, String.valueOf(i + 1), Toast.LENGTH_SHORT).show();
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        };
        init();

        for (int i = 0; i < cameraGridLayout.getChildCount(); i++) {
            FrameLayout button = (FrameLayout) cameraGridLayout.getChildAt(i);
            button.setClickable(false);
        }
        camList = new Camera[cameraGridLayout.getChildCount()];
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                progressBar.setVisibility(ProgressBar.INVISIBLE);
                textViewProgress.setVisibility(View.INVISIBLE);
                for (int i = 0; i < cameraGridLayout.getChildCount(); i++) {
                    FrameLayout button = (FrameLayout) cameraGridLayout.getChildAt(i);
                    button.setClickable(true);
                }
                camera = dataSnapshot.getValue(Camera.class);
                camera.getCameraName();
                camera.getStatus();
                int position = camera.getCameraName() - 1;
                camList[position] = new Camera(camera.getStatus(), camera.getCameraName());
                refresh();
            }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                camera = dataSnapshot.getValue(Camera.class);
                camera.getCameraName();
                camera.getStatus();
                camList[camera.getCameraName()-1] = new Camera(camera.getStatus(), camera.getCameraName());
                refresh();

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

        toViewerActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ViewerActivity.class);
                i.putStringArrayListExtra("normalCameraNames", normalCameraNames);
                startActivity(i);
            }
        });
    }

    private int getStatus(Integer integer) {
        int camStatus = 0;
        for (int i = 0; i < cameraGridLayout.getChildCount(); i++) {
            if (i+1 == integer && camera.getCameraName() == integer) {
                camStatus = camera.getStatus();
            }
        }
        return camStatus;
    }

    private void refresh() {
        for (int i = 0; i < cameraGridLayout.getChildCount(); i++) {
            FrameLayout button = (FrameLayout) cameraGridLayout.getChildAt(i);
            button.getChildAt(0);
            if (camera.getCameraName() == Integer.valueOf(i + 1)) {
                camera.getCameraName();
                camera.getStatus();
                image = (ImageView) button.getChildAt(0);
                if (camera.getStatus() == 1) image.setImageResource(R.drawable.grey_status);
                else if (camera.getStatus() == 2)
                    image.setImageResource(R.drawable.yellow_status);
                else if (camera.getStatus() == 3)
                    image.setImageResource(R.drawable.red_status);

            }
        }
    }

    private void init() {
        cameraFrameLayoutButtons = new ArrayList<>();
        listOfCameraNames = new ArrayList<>();
        normalCameraNames = new ArrayList<>();
        cameraGridLayout = (GridLayout) findViewById(R.id.cameraGridLayout);
        for (int i = 0; i < cameraGridLayout.getChildCount(); i++) {
            FrameLayout button = (FrameLayout) cameraGridLayout.getChildAt(i);
            cameraFrameLayoutButtons.add(button);
            listOfCameraNames.add(getResources().getResourceEntryName(button.getId()));
            normalCameraNames.add(getString(R.string.camera) + Integer.valueOf(i + 1));
            button.setOnClickListener(onClickListener);
        }
        cameraModel = new CameraModel(listOfCameraNames);
        cameraController = new CameraController(cameraModel);
        toViewerActivity = (Button) findViewById(R.id.goToViewerActivity);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Camera");
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textViewProgress = (TextView) findViewById(R.id.textViewProgress);
    }
}