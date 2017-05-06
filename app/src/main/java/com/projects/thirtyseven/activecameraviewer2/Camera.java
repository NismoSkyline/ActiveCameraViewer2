package com.projects.thirtyseven.activecameraviewer2;

/**
 * Created by ThirtySeven on 02-May-17.
 */

class Camera {
    private int status;
    private int cameraName;

    public Camera() {
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public int getCameraName() {
        return cameraName;
    }
}
