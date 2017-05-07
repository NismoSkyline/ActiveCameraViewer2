package com.projects.thirtyseven.activecameraviewer2;

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

    public CameraController(CameraModel cameraModel) {
        model = cameraModel;
    }


    public void clickCamera(int cameraID, int cameraStatus) {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Camera");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                collectCameras((Map<String,Object>) dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                cameraList = new ArrayList<String>();
//                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                    cameraList.add(String.valueOf(dataSnapshot1.geValue()));
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
        if (cameraStatus == 1) {
            status = 2;
        } else if (cameraStatus == 2) {
            status = 3;
        } else if (cameraStatus == 3) {
            status = 1;
        }
//        model.setStatus(cameraID, status);
    }
    private void collectCameras(Map<String, Object> cameras) {
        cameraList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : cameras.entrySet()){
            Map singleCamera = (Map) entry.getValue();
            cameraList.add((Long) singleCamera.get("status"));
        }
    }
}
