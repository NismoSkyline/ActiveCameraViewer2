package com.projects.thirtyseven.activecameraviewer2;

import java.util.ArrayList;

/**
 * Created by ThirtySeven on 01-May-17.
 */

class CameraModel {
    int status;
    int cameraID;

    public int getCameraID() {
        return cameraID;
    }

    public void setCameraID(int cameraID) {
        this.cameraID = cameraID;
    }

    public CameraModel(ArrayList<String> listOfCameraNames) {

    }

    public int getStatus(int cameraID) {
        return status;
    }

    public void setStatus(int cameraID, int status) {
        this.cameraID = cameraID;
        this.status = status;
    }
}
