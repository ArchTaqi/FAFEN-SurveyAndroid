package com.sourcey.survey.Interface;

import com.sourcey.survey.Models.FormQuestionModel;

import java.util.ArrayList;

public interface CustomSelectionInterface {
//    public void success( );
    public void updateModelAfterSuccess( String answer, ArrayList<FormQuestionModel> datasource);
    public void removeModelAfterSuccess( ArrayList<FormQuestionModel> datasource);
}
