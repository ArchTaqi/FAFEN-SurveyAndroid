package com.sourcey.survey.Interface;

public interface ImagePickerInterface {
    public void onSucessImagePick(boolean isPicked, String mediaPath);
    public void onFailureImagePick(boolean isPicked);
}
