package com.projects.thirtyseven.activecameraviewer2;

/**
 * Created by ThirtySeven on 01-May-17.
 */

class CameraController {
    private CameraModel model;

    public CameraController(CameraModel cameraModel) {
        model = cameraModel;
    }

    public void clickCamera(int cameraID, int cameraStatus) {
        int status = 1;
        if(cameraStatus == 1){
            status = 2;
        } else if (cameraStatus == 2){
            status = 3;
        } else if (cameraStatus == 3){
            status = 1;
        }
        model.setStatus(cameraID, status);
    }

}
