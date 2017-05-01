package com.projects.thirtyseven.activecameraviewer2;

/**
 * Created by ThirtySeven on 01-May-17.
 */

class CameraController {
    private CameraModel model;

    public CameraController(CameraModel cameraModel) {
        model = cameraModel;
    }

    public void clickCamera(String id) {
        checkAllcameraStatus();
        model.setCameraTrigger(id);
    }

    private void checkAllcameraStatus() {

    }

}
