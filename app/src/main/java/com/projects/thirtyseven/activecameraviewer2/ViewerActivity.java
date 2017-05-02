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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ViewerActivity extends AppCompatActivity {
    Spinner spinner;
    ConstraintLayout layout;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    ArrayList<String> listOfCameraNames;
    ArrayList<Camera> camerasArrayList;
    int cameraIndex;
    Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer);

        init();
        setAdapterOtions();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> prent, View view, int position, long id) {
                cameraIndex = position;
                databaseReference.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        camerasArrayList.add(dataSnapshot.getValue(Camera.class));
                        //получение обьектов из ветки и запись их в ArrayList
                        camera = camerasArrayList.get(cameraIndex);
                        if (camera.getStatus() == 1) layout.setBackgroundResource(R.drawable.grey_status);
                        else if (camera.getStatus() == 2) layout.setBackgroundResource(R.drawable.yellow_status);
                        else if (camera.getStatus() == 3) layout.setBackgroundResource(R.drawable.red_status);
                        Toast toast = Toast.makeText(getApplicationContext(), "Selected: " + cameraIndex, Toast.LENGTH_SHORT);
                        toast.show();
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
        spinner = (Spinner) findViewById(R.id.spinner);
        layout = (ConstraintLayout) findViewById(R.id.constraintLayout);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Camera");
        camera = new Camera();
        listOfCameraNames = new ArrayList<>();
        camerasArrayList = new ArrayList<>();
    }
}
