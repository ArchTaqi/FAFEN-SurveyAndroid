package com.sourcey.survey.Models;

import java.util.ArrayList;

public class FormOptionModel {

    public  String optionTextInEnglish = "";
    public  String optionTextInUrdu = "";
    public  boolean isOptionSelected = false;
    public  boolean  isEnteryTypeOption = false;
    public  boolean  isPhotoUploadTypeOption = false;
    public  String  localPhotoPath = "";

    public  String entryTypeOptionResponse = "";
    public  int spinnerResponseIndex = -1;
    public  ArrayList<FormQuestionModel> mutableArrayForDependantQuestions = new ArrayList<>();
    public  ArrayList<String> mutableArrayForPickerItems = new ArrayList<>();

}
