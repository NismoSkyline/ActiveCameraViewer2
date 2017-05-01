package com.projects.thirtyseven.activecameraviewer2;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewerActivity extends AppCompatActivity {
    Spinner spinner;
    ArrayList<String> listOfCameraNames;

    private CameraController cameraController;
    private CameraModel cameraModel;
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer);

        init();
        setAdapterOtions();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int listOfCameraNamess, long id) {
                cameraModel.getCameraStatus(listOfCameraNames);
                int cameraStatus = cameraModel.getCameraStatus(String.valueOf(listOfCameraNames));
                if (cameraStatus == 0) layout.setBackgroundResource(R.drawable.grey_status);
                else if (cameraStatus == 1) layout.setBackgroundResource(R.drawable.yellow_status);
                else if (cameraStatus == 2) layout.setBackgroundResource(R.drawable.red_status);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Selected: " + listOfCameraNamess, Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setAdapterOtions() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listOfCameraNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Cameras");
        spinner.setSelection(2);
    }

    private void init() {
        listOfCameraNames = new ArrayList<>();
        spinner = (Spinner) findViewById(R.id.spinner);
        cameraModel = new CameraModel(listOfCameraNames);
        layout = (ConstraintLayout) findViewById(R.id.constraintLayout);
    }
}
