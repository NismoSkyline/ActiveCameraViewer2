package com.projects.thirtyseven.activecameraviewer2;

import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ThirtySeven on 01-May-17.
 */


class CameraController {
    private CameraModel model;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    ArrayList<Long> cameraList;
    Camera camera;
    int status;
    private Integer numericCameraName;

    public CameraController(CameraModel cameraModel) {
        model = cameraModel;
    }

    public void clickCamera(final Integer cameraNumber) {
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Camera").child(String.valueOf("camera" + cameraNumber));
        camera = new Camera();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                camera = dataSnapshot.getValue(Camera.class);
                camera.getCameraName();
                camera.getStatus();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        changeStatus(cameraNumber);
    }

    private void changeStatus(Integer cameraNumber) {
        camera.setCameraName(cameraNumber);
        if (camera.getStatus() == 1)
            camera.setStatus(2);
        else if (camera.getStatus() == 2)
            camera.setStatus(3);
        else if (camera.getStatus() == 3)
            camera.setStatus(1);
        else if (camera.getStatus() == 0)
            camera.setStatus(1);
        databaseReference.setValue(camera);
    }


    public void getId(int id) {
        model.setCameraID(id);
    }


}
