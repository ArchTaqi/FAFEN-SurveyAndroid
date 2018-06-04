package com.sourcey.survey.Models;

import java.util.ArrayList;
import java.util.Arrays;

import static com.sourcey.survey.Models.FormModels._mutableArrayForForm14;

public class FormSeventeenToTwentyModel {

    public static String[] whoStopped = {"DRO","RO","Police","Security Agencies","Political Party/Candidate","Any Other"};
    static String[] victimIncluded = {"DRO","RO","Police","Security Agencies","Political Party / candidate","Monitoring Committee","Any Other"};
    static String[] nameOFtheOffice = {"RO","DRO","DEC","Any Other"};
    static String[] perpitrators = {"Political Party","Ethnic Group","Sectarian groups","Any Other"};
    static String[] victims = {"General voters","Women","Transgender","Non-Muslims","Persons with Disabilities","Any Other"};
    static String[] information = {"Direct observation","Reliable source","Local Newspaper / Channel"};
    static String[] violance = {"Scuffle, no serious casualties","Use of fire arms and other weapons, injuries, death/s","Bombing, firing, large scale injuries, deaths"}; //"Police",


    public static ArrayList<FormQuestionModel> _mutableArrayForForm17 = new ArrayList<>();
    public static ArrayList<FormQuestionModel> _mutableArrayForForm18 = new ArrayList<>();
    public static ArrayList<FormQuestionModel> _mutableArrayForForm19 = new ArrayList<>();
    public static ArrayList<FormQuestionModel> _mutableArrayForForm20 = new ArrayList<>();
    public static ArrayList<FormQuestionModel> _mutableArrayForForm21 = new ArrayList<>();


    public static ArrayList<FormQuestionModel> get_mutableArrayForForm17() {
        _mutableArrayForForm17 = new ArrayList<>();
        loadForm17();
        return _mutableArrayForForm17;
    }

    public static ArrayList<FormQuestionModel> get_mutableArrayForForm18() {
        _mutableArrayForForm18 = new ArrayList<>();
        loadForm18();
        return _mutableArrayForForm18;
    }

    public static ArrayList<FormQuestionModel> get_mutableArrayForForm19() {
        _mutableArrayForForm19 = new ArrayList<>();
        loadForm19();
        return _mutableArrayForForm19;
    }

    public static ArrayList<FormQuestionModel> get_mutableArrayForForm20() {
        _mutableArrayForForm20 = new ArrayList<>();
        loadForm20();
        return _mutableArrayForForm20;
    }

    public static ArrayList<FormQuestionModel> get_mutableArrayForForm21() {
        _mutableArrayForForm21 = new ArrayList<>();
        loadForm21();
        return _mutableArrayForForm21;
    }

    ///////////////////////////////////////////////////////////////////
    public static int  kQuestionTypeYESNO = 0; //CustomButtonFragment
    public static int  kQuestionTypeSingleEntry = 1;   // Entry
    public static int  kQuestionTypeOptions = 2;
    public static int  kQuestionTypeDate = 3;   //TimePickerInterface
    public static int kQuestionTypeSpinnerPicker = 4;   // Entry
    public static int  kQuestionTypePhoto=5; // //CustomImageFragment
    public static int  kQuestionTypeNIC =6;  // Entry
    public static int  kQuestionTypePhoneNumber = 7;   // Entry
    public static int kQuestionTypeEmail = 8;   // Entry
    public static int kQuestionTypeNumeric = 9; //Numeric entry
    public static int kQuestionTypeTime = 10; //TimePickerInterface
    public static int kQuestionTypeSingleLargeEntry = 11; //Large Entry


    public static void loadForm17(){
        {
            FormQuestionModel question = new FormQuestionModel();
            question.stringForAnswerID = "&ans1=";
            question.questionTextInEnglish = "Name of the office";
            question.questionTextInUrdu = "";
            question.questionType = kQuestionTypeSpinnerPicker;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;
            String[] arrayOffice = nameOFtheOffice;
            option.mutableArrayForPickerItems.addAll(Arrays.asList(arrayOffice));
            question = addArrayInQuest(question,option);


            _mutableArrayForForm17.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.stringForAnswerID = "&ans2=";
            question.questionTextInEnglish = "Who stopped you?";
            question.questionTextInUrdu = "";
            question.questionType = kQuestionTypeSpinnerPicker;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;

            String[] arrayOffice = whoStopped;
            option.mutableArrayForPickerItems.addAll(Arrays.asList(arrayOffice));
            question = addArrayInQuest(question,option);


            _mutableArrayForForm17.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.stringForAnswerID = "&ans3=";
            question.questionTextInEnglish = "Reason for Denial";
            question.questionTextInUrdu = "";
            question.questionType = kQuestionTypeSingleLargeEntry;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;
            question.optionsArray.add(option);
            _mutableArrayForForm17.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////////


    }
    //-----------------------------------------------------------------------------------------------------------------------------------------------------
    public static void loadForm18(){
        {
            FormQuestionModel question = new FormQuestionModel();
            question.stringForAnswerID = "&ans1=";
            question.questionTextInEnglish = "Which document was requested";
            question.questionTextInUrdu = "";
            question.questionType = kQuestionTypeSingleEntry;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;
            question.optionsArray.add(option);
            _mutableArrayForForm18.add(question);
        }
        ////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.stringForAnswerID = "&ans2=";
            question.questionTextInEnglish = "Name of the office";
            question.questionTextInUrdu = "";
            question.questionType = kQuestionTypeSpinnerPicker;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;


            String[] arrayOffice = nameOFtheOffice;
            option.mutableArrayForPickerItems.addAll(Arrays.asList(arrayOffice));
            question = addArrayInQuest(question,option);

            _mutableArrayForForm18.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.stringForAnswerID = "&ans3=";
            question.questionTextInEnglish = "Fee paid?";
            question.questionTextInUrdu = "";
            question.questionType = kQuestionTypeYESNO;
            FormOptionModel option = new FormOptionModel();
            option.optionTextInEnglish = "Yes";

            option.optionTextInUrdu = "ہاں";
            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "No";
            option1.optionTextInUrdu = "نہیں";
            question.optionsArray.add(option);
            question.optionsArray.add(option1);
            _mutableArrayForForm18.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.stringForAnswerID = "&ans4=";
            question.questionTextInEnglish = "Reason for Denial";
            question.questionTextInUrdu = "";
            question.questionType = kQuestionTypeSingleLargeEntry;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;
           question.optionsArray.add(option);
            _mutableArrayForForm18.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////////


    }
    //-----------------------------------------------------------------------------------------------------------------------------------------------------
    public static void loadForm19(){

        {
            FormQuestionModel question = new FormQuestionModel();
            question.stringForAnswerID = "&ans1=";
            question.questionTextInEnglish = "Activity barred from observation";
            question.questionTextInUrdu = "";
            question.questionType = kQuestionTypeSingleEntry;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;
            question.optionsArray.add(option);
            _mutableArrayForForm19.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.stringForAnswerID = "&ans2=";
            question.questionTextInEnglish = "Who stopped you?";
            question.questionTextInUrdu = "";
            question.questionType = kQuestionTypeSpinnerPicker;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;

            String[] arrayOffice = whoStopped;
            option.mutableArrayForPickerItems.addAll(Arrays.asList(arrayOffice));
            question = addArrayInQuest(question,option);

            _mutableArrayForForm19.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.stringForAnswerID = "&ans3=";
            question.questionTextInEnglish = "Reason for stopping?";
            question.questionTextInUrdu = "";
            question.questionType = kQuestionTypeSingleLargeEntry;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;
            question.optionsArray.add(option);
            _mutableArrayForForm19.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////////


    }
    //-----------------------------------------------------------------------------------------------------------------------------------------------------
    public static void loadForm20(){
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.stringForAnswerID = "&ans1=";
            question.questionTextInEnglish = "Location of the incident";
            question.questionTextInUrdu = "";
            question.questionType = kQuestionTypeSingleEntry;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;
                question.optionsArray.add(option);
            _mutableArrayForForm20.add(question);
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////// //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.stringForAnswerID = "&ans2=";
            question.questionTextInEnglish = "Method used";
            question.questionTextInUrdu = "";
            question.questionType = kQuestionTypeSingleEntry;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;
            question.optionsArray.add(option);
            _mutableArrayForForm20.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.stringForAnswerID = "&ans3=";
            question.questionTextInEnglish = "Injured (give numbers)";
            question.questionTextInUrdu = "";
            question.limitIfIntegertype = 5000;
            question.questionType = kQuestionTypeNumeric;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;
              question.optionsArray.add(option);
            _mutableArrayForForm20.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.stringForAnswerID = "&ans4=";
            question.questionTextInEnglish = "Deaths (give numbers)";
            question.questionTextInUrdu = "";
            question.limitIfIntegertype = 500;
            question.questionType = kQuestionTypeNumeric;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;
            question.optionsArray.add(option);
            _mutableArrayForForm20.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.stringForAnswerID = "&ans5=";
            question.questionTextInEnglish = "Property damaged (explain in few words)";
            question.questionTextInUrdu = "";
            question.questionType = kQuestionTypeSingleEntry;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;
            question.optionsArray.add(option);
            _mutableArrayForForm20.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.stringForAnswerID = "&ans6=";
            question.questionTextInEnglish = "Source of information";
            question.questionTextInUrdu = "";
            question.questionType = kQuestionTypeSpinnerPicker;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;
            option.mutableArrayForPickerItems.addAll(Arrays.asList(information));
            question = addArrayInQuest(question,option);

            _mutableArrayForForm20.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.stringForAnswerID = "&ans7=";
            question.questionTextInEnglish = "Level of violance";
            question.questionTextInUrdu = "";
            question.questionType = kQuestionTypeSpinnerPicker;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;
             option.mutableArrayForPickerItems.addAll(Arrays.asList(violance));
            question = addArrayInQuest(question,option);

            _mutableArrayForForm20.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.stringForAnswerID = "&ans8=";
            question.questionTextInEnglish = "Who were the perpetrators";
            question.questionTextInUrdu = "";
            question.questionType = kQuestionTypeSpinnerPicker;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;

            String[] arrayOffice = perpitrators;
            option.mutableArrayForPickerItems.addAll(Arrays.asList(arrayOffice));
//            question = addArrayInQuest(question,option);

            for(int i=0; i<option.mutableArrayForPickerItems.size();i++){
                FormOptionModel tempOption = new FormOptionModel();
                tempOption.optionTextInEnglish = option.mutableArrayForPickerItems.get(i);
                tempOption.optionTextInUrdu = "";

                String var = option.mutableArrayForPickerItems.get(i);

                if (var.equalsIgnoreCase("any other")){
                    FormQuestionModel question1 = new FormQuestionModel();
                    question1.stringForAnswerID = "&ans9=";
                    question1.questionTextInEnglish = "Which party/Ethnic/sectarian group - give name";
                    question1.questionTextInUrdu = "";
                    question1.questionType = kQuestionTypeSingleEntry;
                    FormOptionModel option1 = new FormOptionModel();
                    option1.isEnteryTypeOption = true;
                    question1.optionsArray.add(option1);
                    tempOption.mutableArrayForDependantQuestions.add(question1);

                }
                question.optionsArray .add(tempOption);
            }




            _mutableArrayForForm20.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.stringForAnswerID = "&ans10=";
            question.questionTextInEnglish = "who were the victims";
            question.questionTextInUrdu = "";
            question.questionType = kQuestionTypeSpinnerPicker;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;

            String[] arrayOffice = perpitrators;
            option.mutableArrayForPickerItems.addAll(Arrays.asList(arrayOffice));
//            question = addArrayInQuest(question,option);

            for(int i=0; i<option.mutableArrayForPickerItems.size();i++){
                FormOptionModel tempOption = new FormOptionModel();
                tempOption.optionTextInEnglish = option.mutableArrayForPickerItems.get(i);
                tempOption.optionTextInUrdu = "";

                String var = option.mutableArrayForPickerItems.get(i);

                if (var.equalsIgnoreCase("any other")){
                        FormQuestionModel question1 = new FormQuestionModel();
                        question1.stringForAnswerID = "&ans11=";
                        question1.questionTextInEnglish = "Which party/Ethnic/sectarian group - give name";
                        question1.questionTextInUrdu = "";
                        question1.questionType = kQuestionTypeSingleEntry;
                        FormOptionModel option1 = new FormOptionModel();
                        option1.isEnteryTypeOption = true;
                        question1.optionsArray.add(option1);
                        tempOption.mutableArrayForDependantQuestions.add(question1);

                }
                question.optionsArray .add(tempOption);
            }





            _mutableArrayForForm20.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.stringForAnswerID = "&ans12=";
            question.questionTextInEnglish = "Victims included - pick all that apply";
            question.questionTextInUrdu = "";
            question.questionType = kQuestionTypeSpinnerPicker;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;

            String[] arrayOffice = victims;
            option.mutableArrayForPickerItems.addAll(Arrays.asList(arrayOffice));
            question = addArrayInQuest(question,option);


            _mutableArrayForForm20.add(question);
        }

    }

    private static FormQuestionModel addArrayInQuest(FormQuestionModel question, FormOptionModel option) {
        for(int i=0; i<option.mutableArrayForPickerItems.size();i++){
            FormOptionModel tempOption = new FormOptionModel();
            tempOption.optionTextInEnglish = option.mutableArrayForPickerItems.get(i);
            tempOption.optionTextInUrdu = "";
            question.optionsArray .add(tempOption);
        }
        return question;
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------
    public static void loadForm21(){
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.stringForAnswerID = "&ans1=";
            question.questionTextInEnglish = "What violation was being observed";
            question.questionTextInUrdu = "";
            question.questionType = kQuestionTypeSingleEntry;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;
            question.optionsArray.add(option);
            _mutableArrayForForm21.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.stringForAnswerID = "&ans2=";
            question.questionTextInEnglish = "Who stopped you from taking pictures.";
            question.questionTextInUrdu = "";
            question.questionType = kQuestionTypeSpinnerPicker;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;
            String[] arrayOffice = victimIncluded;
            option.mutableArrayForPickerItems.addAll(Arrays.asList(arrayOffice));
//            question = addArrayInQuest(question,option);

            for(int i=0; i<option.mutableArrayForPickerItems.size();i++){
                FormOptionModel tempOption = new FormOptionModel();
                tempOption.optionTextInEnglish = option.mutableArrayForPickerItems.get(i);
                tempOption.optionTextInUrdu = "";

                String var = option.mutableArrayForPickerItems.get(i);

                if (var.equalsIgnoreCase("any other")){
                    FormQuestionModel question1 = new FormQuestionModel();
                    question1.stringForAnswerID = "&ans3=";
                    question1.questionTextInEnglish = "Give names of the official/party/candidate/individual";
                    question1.questionTextInUrdu = "";
                    question1.questionType = kQuestionTypeSingleEntry;
                    FormOptionModel option1 = new FormOptionModel();
                    option1.isEnteryTypeOption = true;
                    question1.optionsArray.add(option1);
                    tempOption.mutableArrayForDependantQuestions.add(question1);

                }
                question.optionsArray .add(tempOption);
            }


            _mutableArrayForForm21.add(question);
        }

        //////////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.stringForAnswerID = "&ans4=";
            question.questionTextInEnglish = "Reason for stopping.";
            question.questionTextInUrdu = "";
            question.questionType = kQuestionTypeSingleLargeEntry;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;
            question.optionsArray.add(option);
            _mutableArrayForForm21.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
    }
//-----------------------------------------------------------------------------------------------------------------------------------------------------
}
