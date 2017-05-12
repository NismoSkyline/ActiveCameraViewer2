package com.projects.thirtyseven.activecameraviewer2;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
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
    int name;
    private Integer numericCameraName;

    public CameraController(CameraModel cameraModel) {
        model = cameraModel;
    }

    public void clickCamera(int name, int status, Camera[] camList) {

        database = FirebaseDatabase.getInstance();

        changeStatus(status, name, camList);

    }


    private void changeStatus(int status, int name, Camera[] camList) {
        camera = new Camera();
        camera.setCameraName(name);
        if (status == 1) {
            camera.setStatus(2);
        } else if (status == 2) {
            camera.setStatus(3);
            //checkCamera(status, name, camList);

        } else if (status == 3) {
            camera.setStatus(1);
        }
        databaseReference = database.getReference("Camera").child("camera" + name);
        databaseReference.setValue(camera);

    }

    /*private void checkCamera(int status, int name, Camera[] camList) {
        for (int i = 0; i < camList.length; i++) {
            Camera cam = camList[name - 1];
            if (i != name - 1) {
                if (cam.getStatus() == 3) {
                    databaseReference = database.getReference("Camera").child("camera" + (i + 1));
                    cam.setCameraName(i + 1);
                    cam.setStatus(2);
                    databaseReference.setValue(cam);
                }
            } else {
                databaseReference = database.getReference("Camera").child("camera" + (name));
                cam.setCameraName(name);
                cam.setStatus(3);
                databaseReference.setValue(cam);
            }
        }
    }*/


    public void getId(int id) {
        model.setCameraID(id);
    }

}
