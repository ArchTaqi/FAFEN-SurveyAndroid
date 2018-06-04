package com.sourcey.survey.Models;

import java.util.ArrayList;

public class FormQuestionModel {
    public double itemNumber;
    public String questionTextInEnglish;
    public String questionTextInUrdu;
    public int questionType;
    public int limitIfIntegertype = -1;
    public FormOptionModel selectedOption = new FormOptionModel();
    public ArrayList<FormOptionModel> optionsArray = new ArrayList<>() ;
    public String reponseText = "";
    public boolean isFormComplete;
    public String imageURI;
    public int day = -1;
    public int month = -1;
    public int year = -1;
    public String stringForAnswerID = "";

    public void setAllOptionsUnselectedAndRemoveDependentQuestionsFromArray(){
        // re selection
    }
}
