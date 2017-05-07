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
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridLayout cameraGridLayout;
    ArrayList<FrameLayout> cameraFrameLayoutButtons;
    ArrayList<String> listOfCameraNames;
    ArrayList<String> normalCameraNames;
    View.OnClickListener onClickListener;
    Button toViewerActivity;

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
                cameraController.clickCamera(v.getId(), cameraModel.getStatus(v.getId()));
                Toast.makeText(MainActivity.this, String.valueOf(cameraModel.getStatus(v.getId())), Toast.LENGTH_SHORT).show();
                refreshView();
            }
        };
        init();

        toViewerActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ViewerActivity.class);
                i.putStringArrayListExtra("normalCameraNames", normalCameraNames);
                startActivity(i);
            }
        });

    }

    private void refreshView() {
        for (FrameLayout button : cameraFrameLayoutButtons) {
            if (button.getId() == cameraModel.getCameraID()) {
                Toast.makeText(this, String.valueOf(button.getId()), Toast.LENGTH_SHORT).show();
                ImageView image = (ImageView) button.getChildAt(0);
                int cameraStatus = cameraModel.getStatus(button.getId());
                Toast.makeText(getApplicationContext(), "Status: " + cameraStatus, Toast.LENGTH_SHORT).show();
                if (cameraStatus == 1) image.setImageResource(R.drawable.grey_status);
                else if (cameraStatus == 2) image.setImageResource(R.drawable.yellow_status);
                else if (cameraStatus == 3) image.setImageResource(R.drawable.red_status);
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
    }
}