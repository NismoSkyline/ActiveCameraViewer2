package com.projects.thirtyseven.activecameraviewer2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridLayout cameraGridLayout;
    ArrayList<FrameLayout> cameraFrameLayoutButtons;

    View.OnClickListener onClickListener;

    private CameraController cameraController;
    private CameraModel cameraModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        refreshView();

        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraController.clickCamera(String.valueOf(v.getId()));
                refreshView();
            }
        };
    }

    private void refreshView() {
        for (FrameLayout button : cameraFrameLayoutButtons){
            ImageView image = (ImageView)button.getChildAt(0);
            int cameraStatus = cameraModel.getCameraStatus(String.valueOf(button.getId()));
            if (cameraStatus == 0) image.setImageResource(R.drawable.grey_status);
            else if (cameraStatus == 1) image.setImageResource(R.drawable.yellow_status);
            else if ( cameraStatus == 2) image.setImageResource(R.drawable.red_status);
        }
    }

    private void init() {
        cameraFrameLayoutButtons = new ArrayList<>();
        ArrayList<String> listOfCameraNames = new ArrayList<>();

        cameraGridLayout = (GridLayout) findViewById(R.id.cameraGridLayout);
        for (int i = 0; i < cameraGridLayout.getChildCount(); i++){
            FrameLayout button = (FrameLayout) cameraGridLayout.getChildAt(i);
            cameraFrameLayoutButtons.add(button);
            listOfCameraNames.add(String.valueOf(button.getId()));
            button.setOnClickListener(onClickListener);
        }

        cameraModel = new CameraModel(listOfCameraNames);
        cameraController = new CameraController(cameraModel);
    }
}