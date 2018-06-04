package com.sourcey.survey.Models;

import com.sourcey.survey.FormThirteen.ListOFConstituency;
import com.sourcey.survey.FormThirteen.ListOFDistricts;
import com.sourcey.survey.FormThirteen.ListOFPoliticalarties;

import java.util.ArrayList;
import java.util.Arrays;

public class FormModels {



    public static ArrayList<FormQuestionModel> ArrayForForm13 = new ArrayList<>();
    public static ArrayList<FormQuestionModel> mutableArrayForForm15 = new ArrayList<>();

    public static ArrayList<FormQuestionModel> _mutableArrayForForm14 = new ArrayList<>();
    public static ArrayList<FormQuestionModel> mutableArrayForForm16 = new ArrayList<>();

    public static ArrayList<FormQuestionModel> get_mutableArrayForForm14() {
        _mutableArrayForForm14 = new ArrayList<>();
        loadForm14();
        return _mutableArrayForForm14;
    }

    public static void set_mutableArrayForForm14(ArrayList<FormQuestionModel> _mutableArrayForForm14) {
        FormModels._mutableArrayForForm14 = _mutableArrayForForm14;
    }

    public static ArrayList<FormQuestionModel> getMutableArrayForForm16() {
        mutableArrayForForm16 = new ArrayList<>();
        loadForm16();
        return mutableArrayForForm16;
    }

    public static void setMutableArrayForForm16(ArrayList<FormQuestionModel> mutableArrayForForm16) {
        FormModels.mutableArrayForForm16 = mutableArrayForForm16;
    }


    public static ArrayList<FormQuestionModel> getMutableArrayForForm15() {
        mutableArrayForForm15 = new ArrayList<>();
        loadForm15();
        return mutableArrayForForm15;
    }

    public void setMutableArrayForForm15(ArrayList<FormQuestionModel> mutableArrayForForm15) {
        this.mutableArrayForForm15 = mutableArrayForForm15;
    }

    public static ArrayList<FormQuestionModel> getArrayForForm13() {
        ArrayForForm13 = new ArrayList<>();
        loadForm13();
        return ArrayForForm13;
    }

    public static void setArrayForForm13(ArrayList<FormQuestionModel> arrayForForm13) {
        ArrayForForm13 = arrayForForm13;
    }



    public static int  kQuestionTypeYESNO = 0; //CustomButtonFragment
    public static int  kQuestionTypeSingleEntry = 1;   // Entry
    public static int  kQuestionTypeOptions = 2;
    public static int  kQuestionTypeDate = 3;   //TimePickerInterface
    public static int kQuestionTypeSpinnerPicker = 4;   // Entry
    public static int  kQuestionTypePhoto=5; // //CustomImageFragment
    public static int  kQuestionTypeNIC =6;  // Entry
    public static int  kQuestionTypePhoneNumber = 7;   // Entry
    public static int kQuestionTypeEmail = 8;   // Entry
    public static int kQuestionTypeNumeric = 9; //TimePickerInterface
    public static int kQuestionTypeTime = 10; //TimePickerInterface


    public static void  loadForm13(){
        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Voter Name";
            question.questionTextInUrdu = "ووٹر کا نام";
            question.itemNumber = 1;
            question.questionType = kQuestionTypeSingleEntry;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;;
            question.optionsArray.add(option);
            ArrayForForm13.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Voter NIC Number";
            question.questionTextInUrdu = "ووٹرکاشناختی کارڈ نمبر";
            question.itemNumber = 2;
            question.questionType = kQuestionTypeNIC;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;;
            question.optionsArray.add(option);
            ArrayForForm13.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Voter Mobile Number";
            question.questionTextInUrdu = "ووٹر کا موبائل نمبر";
            question.itemNumber = 3;
            question.questionType = kQuestionTypePhoneNumber;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;;
            question.optionsArray.add(option);
            ArrayForForm13.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Constituency";
            question.questionTextInUrdu = "حلقے کا نام";
            question.itemNumber = 4;
            question.questionType = kQuestionTypeSpinnerPicker;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;

            option.mutableArrayForPickerItems = getListOfConstituencies();
            for(int i=0; i<option.mutableArrayForPickerItems.size();i++){
                FormOptionModel tempOption = new FormOptionModel();
                tempOption.optionTextInEnglish = option.mutableArrayForPickerItems.get(i);
                tempOption.optionTextInUrdu = "";
                question.optionsArray .add(tempOption);
            }

            question.optionsArray.add(option);
            ArrayForForm13.add(question);

        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Name of Electoral Area";
            question.questionTextInUrdu = "الیکٹورل ایریا کا نام";
            question.itemNumber = 5;
            question.questionType = kQuestionTypeSingleEntry;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;;
            question.optionsArray.add(option);
            ArrayForForm13.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Did you participate in any of the candidates’ jalsas/rallies in connection with the election campaign in your area?";
            question.questionTextInUrdu = "کیا آپ نے اس الیکشن کی انتخابی مہم کے دوران اپنے علاقے میں  کسی امیدوار کے جلسے یا ریلی میں شرکت کی؟ ";
            question.itemNumber = 6;
            question.questionType = kQuestionTypeYESNO;
            FormOptionModel option = new FormOptionModel();

            option.optionTextInEnglish = "Yes";
            option.optionTextInUrdu = "ہاں";
            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "No";
            option1.optionTextInUrdu = "نہیں";
            question.optionsArray.add(option);

            question.optionsArray.add(option1);
            ArrayForForm13.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Did the candidate use loud speaker during the political gathering?";
            question.questionTextInUrdu = "کیا جلسے یا ریلی میں لاؤڈ سپیکر کا  استعمال کیاگیا؟";
            question.itemNumber = 7;
            question.questionType = kQuestionTypeYESNO;
            FormOptionModel option = new FormOptionModel();

            option.optionTextInEnglish = "Yes";
            option.optionTextInUrdu = "ہاں";
            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "No";
            option1.optionTextInUrdu = "نہیں";
            question.optionsArray.add(option);
            question.optionsArray.add(option1);
            ArrayForForm13.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Has any election activity conducted by the candidate or political party at any of the worship places, including Mosque, Temple, Church, Gurdwara, Imambargah etc., in your area?  ";
            question.questionTextInUrdu = "کیا آپ کے علاقے کی عبادت گاہوں(مسجد، مدرسہ ، مندر، گرجا گھر ، گرودوارہ ، امام بارگاہ، دربار وغیرہ)میں امیدوار یا کسی پارٹی کی طرف سے  الیکشن کی کوئی سرگرمی منعقد کی گئی ؟";
            question.itemNumber = 8;
            question.questionType = kQuestionTypeYESNO;
            FormOptionModel option = new FormOptionModel();

            option.optionTextInEnglish = "Yes";
            option.optionTextInUrdu = "ہاں";
            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "No";
            option1.optionTextInUrdu = "نہیں";
            question.optionsArray.add(option);
            question.optionsArray.add(option1);
            ArrayForForm13.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "If yes, who conducted this activity";
            question.questionTextInUrdu = "اگر ہاں تو یہ سرگرمی کس نیں  منعقد کروائی ";
            question.itemNumber = 9;
            question.questionType = kQuestionTypeSpinnerPicker;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;;

            option.mutableArrayForPickerItems = getListOfPolticalParties();
            for(int i=0; i<option.mutableArrayForPickerItems.size();i++){
                FormOptionModel tempOption = new FormOptionModel();
                tempOption.optionTextInEnglish = option.mutableArrayForPickerItems.get(i);
                tempOption.optionTextInUrdu = "";
                question.optionsArray .add(tempOption);
            }

            question.optionsArray.add(option);
            ArrayForForm13.add(question);

//        Please write name of the candidate    امیدوار کا نام لکھیں
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Have you confirmed whether you are registered as a voter? ";
            question.questionTextInUrdu = "کیا آپ نے تصدیق کی ہے   کہ آپ کا بطور ووٹراندراج ہو چکا ہے؟ ";
            question.itemNumber = 10;
            question.questionType = kQuestionTypeYESNO;
            FormOptionModel option = new FormOptionModel();

            option.optionTextInEnglish = "Yes";
            option.optionTextInUrdu = "ہاں";
            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "No";
            option1.optionTextInUrdu = "نہیں";
            question.optionsArray.add(option);
            question.optionsArray.add(option1);
            ArrayForForm13.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Is your polling station established at a walkaway distance from your residence (within one kilometer of radius)?";
            question.questionTextInUrdu = "کیا آپ کا پولنگ اسٹیشن پیدل فاصلے پر  بنایا گیا ہے؟ (آپ کے گھر سے ایک کلو میٹر دور)";
            question.itemNumber = 11;
            question.questionType = kQuestionTypeYESNO;
            FormOptionModel option = new FormOptionModel();

            option.optionTextInEnglish = "Yes";
            option.optionTextInUrdu = "ہاں";
            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "No";
            option1.optionTextInUrdu = "نہیں";
            question.optionsArray.add(option);
            question.optionsArray.add(option1);
            ArrayForForm13.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Are the polling stations established in your vicinity are easily accessible for women voters?";
            question.questionTextInUrdu = "کیا آپ کے علاقے میں بنائے گئے پولنگ اسٹیشنوں کا فاصلہ خواتین ووٹروں کے لئے موزوں ہے؟";
            question.itemNumber = 12;
            question.questionType = kQuestionTypeYESNO;
            FormOptionModel option = new FormOptionModel();

            option.optionTextInEnglish = "Yes";
            option.optionTextInUrdu = "ہاں";
            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "No";
            option1.optionTextInUrdu = "نہیں";
            question.optionsArray.add(option);
            question.optionsArray.add(option1);
            ArrayForForm13.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Has any development work initiated in your Mohalla/Village/Goth/Kilay etc., during last one month.";
            question.questionTextInUrdu = "پچھلے ایک مہینے کے دوران، کیا آپ کے محلے، گاؤں، گوٹھ، کلے وغیرہ میں کوئی ترقیاتی کام شروع کیا گیا؟";
            question.itemNumber = 13;
            question.questionType = kQuestionTypeYESNO;
            FormOptionModel option = new FormOptionModel();

            option.optionTextInEnglish = "Yes";
            {
                FormQuestionModel question1 = new FormQuestionModel();
                question1.questionTextInEnglish = "Name of Scheme";
                question1.questionTextInUrdu = "ترقیاتی سکیم کا نام";
                question1.questionType = kQuestionTypeSingleEntry;
                question1.itemNumber = 13.1;
                FormOptionModel option2 = new FormOptionModel();
                option2.isEnteryTypeOption = true;

                question1.optionsArray.add(option2);
                option.mutableArrayForDependantQuestions .add(question1);
            }
            {
                FormQuestionModel question1 = new FormQuestionModel();
                question1.questionTextInEnglish = "Precise Location";
                question1.questionTextInUrdu = "جگہ کا نام ";
                question1.itemNumber = 13.2;
                question1.questionType = kQuestionTypeSingleEntry;
                FormOptionModel option2 = new FormOptionModel();
                option2.isEnteryTypeOption = true;
                question1.optionsArray.add(option2);
                option.mutableArrayForDependantQuestions.add(question1);
            }
            {
                FormQuestionModel question1 = new FormQuestionModel();
                question1.questionTextInEnglish = "Who initiated the Development Work";
                question1.questionTextInUrdu = " کام کون کروا رہا ہے";
                question1.itemNumber = 13.3;
                question1.questionType = kQuestionTypeOptions;

                FormOptionModel option2 = new FormOptionModel();
                option2.optionTextInEnglish = "Contesting candidate";
                option2.optionTextInUrdu = "امیدوار";

                FormOptionModel option3 =   new FormOptionModel();
                option3.optionTextInEnglish = "Government official";
                option3.optionTextInUrdu = "سرکاری افسر";

                FormOptionModel option4 =   new FormOptionModel();
                option4.optionTextInEnglish = "Local Government Representative";
                option4.optionTextInUrdu = "مقامی حکومت کا عہدےدار";

                FormOptionModel option5 =   new FormOptionModel();
                option5.optionTextInEnglish = "Any Other";
                option5.optionTextInUrdu = "دیگر";
                option5.isEnteryTypeOption = true;

                question1.optionsArray.add(option2);
                question1.optionsArray.add(option3);
                question1.optionsArray.add(option4);
                question1.optionsArray.add(option5);

                option.mutableArrayForDependantQuestions.add(question1);
            }
            {
                FormQuestionModel question1 = new FormQuestionModel();
                question1.questionTextInEnglish = "Please specify any other";
                question1.questionTextInUrdu = "دیگر کی تفصیل لکھیں";
                question1.itemNumber = 13.4;
                question1.questionType = kQuestionTypeSingleEntry;
                FormOptionModel option2 = new FormOptionModel();
                option2.isEnteryTypeOption = true;
                question1.optionsArray.add(option2);
                option.mutableArrayForDependantQuestions.add(question1);
            }
            option.optionTextInUrdu = "ہاں";
            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "No";
            option1.optionTextInUrdu = "نہیں";
            question.optionsArray.add(option);
            question.optionsArray.add(option1);
            ArrayForForm13.add(question);

        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Are voters being intimidated or coerced to support any contesting candidate in your electoral area?";
            question.questionTextInUrdu = "کیا آپ کے علاقے میں ووٹروں کوکسی خاص امیدوار کی حمایت کرنے کے لئے دھمکایا یا مجبور کیا جا رہا ہے؟ ";
            question.itemNumber = 14;
            question.questionType = kQuestionTypeYESNO;
            FormOptionModel option = new FormOptionModel();
            option.optionTextInEnglish = "Yes";
            option.optionTextInUrdu = "ہاں";
            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "No";
            option1.optionTextInUrdu = "نہیں";
            question.optionsArray.add(option);
            question.optionsArray.add(option1);
            ArrayForForm13.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Are voters being intimidated or coerced to oppose any contesting candidate in your electoral area?";
            question.questionTextInUrdu = "کیا آپ کے علاقے میں ووٹروں کوکسی خاص امیدوار کی مخالفت کرنے کے لئے دھمکایا یا مجبور کیا جا رہا ہے؟ ";
            question.itemNumber = 15;
            question.questionType = kQuestionTypeYESNO;
            FormOptionModel option = new FormOptionModel();
            option.optionTextInEnglish = "Yes";
            option.optionTextInUrdu = "ہاں";
            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "No";
            option1.optionTextInUrdu = "نہیں";
            question.optionsArray.add(option);
            question.optionsArray.add(option1);
            ArrayForForm13.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Has any incident of political violence reported between candidates or political parties in your area? ";
            question.questionTextInUrdu = "کیا آپ کے علاقے میں امیدواروں یا سیاسی جماعتوں کے درمیان تشدد کا کوئی واقعہ پیش آیا؟";
            question.itemNumber = 16;
            question.questionType = kQuestionTypeYESNO;
            FormOptionModel option = new FormOptionModel();
            option.optionTextInUrdu = "ہاں";
            option.optionTextInEnglish = "Yes";
            {
                FormQuestionModel depQuestion1 = new FormQuestionModel();
                depQuestion1.questionTextInEnglish = "If yes, please describe briefly ";
                depQuestion1.questionTextInUrdu = "اگر ہاں تو اس کی مختصر تفصیل لکھیں";
                depQuestion1.itemNumber = 16.1;
                depQuestion1.questionType = kQuestionTypeSingleEntry;
                FormOptionModel depOption1 = new FormOptionModel();
                depOption1.isEnteryTypeOption = true;
                depQuestion1.optionsArray.add(depOption1);
                option.mutableArrayForDependantQuestions.add(depQuestion1);


            }


            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "No";
            option1.optionTextInUrdu = "نہیں";
            question.optionsArray.add(option);
            question.optionsArray.add(option1);
            ArrayForForm13.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Do women of your area cast vote during elections?";
            question.questionTextInUrdu = "کیا آپ کے علاقے کی خواتین ووٹ ڈالتی ہیں؟";
            question.itemNumber = 17;
            question.questionType = kQuestionTypeYESNO;
            FormOptionModel option = new FormOptionModel();
            option.optionTextInEnglish = "YES";
            option.optionTextInUrdu = "ہاں";



            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "No";
            option1.optionTextInUrdu = "نہیں";
            {
                FormQuestionModel depQuestion1 = new FormQuestionModel();
                depQuestion1.questionTextInEnglish = "If no, what are the main reasons of women not casting votes.";
                depQuestion1.questionTextInUrdu = "اگر نہیں توخواتین کے ووٹ نہ ڈالنے کی کیا وجوہات ہیں؟";
                depQuestion1.itemNumber = 17.1;
                depQuestion1.questionType = kQuestionTypeSingleEntry;
                FormOptionModel depOption1 = new FormOptionModel();
                depOption1.isEnteryTypeOption = true;
                depQuestion1.optionsArray.add(depOption1);
                option1.mutableArrayForDependantQuestions.add(depQuestion1);


            }
            question.optionsArray.add(option);
            question.optionsArray.add(option1);
            ArrayForForm13.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Has any candidate distributed goods (cash, motorcycles, bicycles, sewing machines, food etc.) to induce voters in your area?";
            question.questionTextInUrdu = "کیا آپ کے علاقے میں کسی امیدوار کی طرف سے ووٹروں کو اپنی طرف راغب کرنے کے لئے کوئی اشیاء (نقد رقم ، پانی کا نلکا ، موٹرسائیکل، سائیکل،  سلائی مشین یا کھانا وغیرہ) تقسیم کی گئیں؟";
            question.itemNumber = 18;
            question.questionType = kQuestionTypeYESNO;
            FormOptionModel option = new FormOptionModel();
            option.optionTextInUrdu = "ہاں";
            option.optionTextInEnglish = "Yes";
            {
                FormQuestionModel depQuestion1 = new FormQuestionModel();
                depQuestion1.questionTextInEnglish = "Name of the person";
                depQuestion1.questionTextInUrdu = "اگر ہاں تو اشیاء دینے والے کانام";
                depQuestion1.itemNumber = 18.1;
                depQuestion1.questionType = kQuestionTypeSingleEntry;
                FormOptionModel depOption1 = new FormOptionModel();
                depOption1.isEnteryTypeOption = true;
                depQuestion1.optionsArray.add(depOption1);
                option.mutableArrayForDependantQuestions.add(depQuestion1);


            }


            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "No";
            option1.optionTextInUrdu = "نہیں";
            question.optionsArray.add(option);
            question.optionsArray.add(option1);
            ArrayForForm13.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Political Affiliation";
            question.questionTextInUrdu = "سیاسی وابستگی";
            question.itemNumber = 19;
            question.questionType = kQuestionTypeSpinnerPicker;
            FormOptionModel option = new FormOptionModel();

            option.mutableArrayForPickerItems = getListOfPolticalParties();
            FormOptionModel tempOption1 = new FormOptionModel();
            tempOption1.optionTextInEnglish = "None";
            tempOption1.optionTextInUrdu = "";
            question.optionsArray .add(tempOption1);

            for(int i=0; i<option.mutableArrayForPickerItems.size();i++){

                FormOptionModel tempOption = new FormOptionModel();
                tempOption.optionTextInEnglish = option.mutableArrayForPickerItems.get(i);
                tempOption.optionTextInUrdu = "";
                question.optionsArray .add(tempOption);
            }


           /* option.optionTextInEnglish = "Yes";
            option.optionTextInUrdu = "ہاں";
            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "No";
            option1.optionTextInUrdu = "نہیں";
            question.optionsArray.add(option);
            question.optionsArray.add(option1);*/
            ArrayForForm13.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Details of goods distributed";
            question.questionTextInUrdu = "کون سی اشیاء  دی گئیں تفصیل لکھیں";
            question.itemNumber = 20;
            question.questionType = kQuestionTypeSingleEntry;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;;
            question.optionsArray.add(option);
            ArrayForForm13.add(question);
        }
    }



    public static void loadForm15(){
        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Constituency";
            question.questionTextInUrdu = "حلقے کا نام";

            question.questionType = kQuestionTypeSpinnerPicker;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;

            option.mutableArrayForPickerItems = getListOfConstituencies();
            for(int i=0; i<option.mutableArrayForPickerItems.size();i++){
                FormOptionModel tempOption = new FormOptionModel();
                tempOption.optionTextInEnglish = option.mutableArrayForPickerItems.get(i);
                tempOption.optionTextInUrdu = "";
                question.optionsArray .add(tempOption);
            }

            question.optionsArray.add(option);
            mutableArrayForForm15.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Have the ROs/AROs visited this polling station?";
            question.questionTextInUrdu = "کیا اس پولنگ اسٹیشن پر ریٹرننگ افسر / اسسٹنٹ ریٹرننگ افسر آئے تھے؟";
            question.questionType = kQuestionTypeYESNO;
            FormOptionModel option = new FormOptionModel();
            option.optionTextInEnglish = "Yes";
            option.optionTextInUrdu = "ہاں";
            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "No";
            option1.optionTextInUrdu = "نہیں";
            question.optionsArray.add(option);
            question.optionsArray.add(option1);
            mutableArrayForForm15 .add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Has the polling station established in a building?";
            question.questionTextInUrdu = "کیا پولنگ اسٹیشن کسی عمارت میں قائم کیا گیا ہے؟";
            question.questionType = kQuestionTypeYESNO;
            FormOptionModel option = new FormOptionModel();
            option.optionTextInEnglish = "Yes";
            option.optionTextInUrdu = "ہاں";
            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "No";
            option1.optionTextInUrdu = "نہیں";
            question.optionsArray.add(option);
            question.optionsArray .add(option1);
            mutableArrayForForm15 .add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Has any ramp built from where a wheel chair can easily enter the polling station?";
            question.questionTextInUrdu = "کیا پولنگ اسٹیشن میں کوئی  ایسی جگہ بنائی گئی ہے جہاں سے  ویل چئیر پولنگ اسٹیشن میں داخل ہو سکے؟";
            question.questionType = kQuestionTypeYESNO;
            FormOptionModel option = new FormOptionModel();
            option.optionTextInEnglish = "Yes";
            option.optionTextInUrdu = "ہاں";
            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "No";
            option1.optionTextInUrdu = "نہیں";
            question.optionsArray.add(option);
            question.optionsArray .add(option1);
            mutableArrayForForm15 .add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Does the polling station building has the facility of clean drinking water?";
            question.questionTextInUrdu = "کیا پولنگ اسٹیشن کی عمارت میں پینے کے پانی کی سہولت موجود ہے؟";
            question.questionType = kQuestionTypeYESNO;
            FormOptionModel option = new FormOptionModel();
            option.optionTextInEnglish = "Yes";
            option.optionTextInUrdu = "ہاں";
            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "No";
            option1.optionTextInUrdu = "نہیں";
            question.optionsArray.add(option);
            question.optionsArray .add(option1);
            mutableArrayForForm15 .add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Has washroom facility provided in the polling station building?";
            question.questionTextInUrdu = "کیا پولنگ اسٹیشن کی عمارت میں لیٹرین موجود ہیں؟";
            question.questionType = kQuestionTypeYESNO;
            FormOptionModel option = new FormOptionModel();
            option.optionTextInEnglish = "Yes";
            option.optionTextInUrdu = "ہاں";
            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "No";
            option1.optionTextInUrdu = "نہیں";
            question.optionsArray.add(option);
            question.optionsArray .add(option1);
            mutableArrayForForm15 .add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Does the polling station building has proper boundary wall?";
            question.questionTextInUrdu = "کیا پولنگ اسٹیشن کی عمارت کی چاردیواری ہے؟";
            question.questionType = kQuestionTypeYESNO;
            FormOptionModel option = new FormOptionModel();
            option.optionTextInEnglish = "Yes";
            option.optionTextInUrdu = "ہاں";
            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "No";
            option1.optionTextInUrdu = "نہیں";
            question.optionsArray.add(option);
            question.optionsArray .add(option1);
            mutableArrayForForm15 .add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "How many floors are there at the polling station building?";
            question.questionTextInUrdu = "پولنگ اسٹیشن کی عمارت کتنی منزلہ ہے؟";
            question.questionType = kQuestionTypeYESNO;

            FormOptionModel option = new FormOptionModel();
            option.optionTextInEnglish = "Two Story";
            option.optionTextInUrdu = "دو منزلہ";

            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "Three Story";
            option1.optionTextInUrdu = "تین منزلہ";

            FormOptionModel option2 = new FormOptionModel();
            option2.optionTextInEnglish = "Four Story";
            option2.optionTextInUrdu = "چار منزلہ";

            question.optionsArray.add(option);
            question.optionsArray .add(option1);
            question.optionsArray .add(option2);
            mutableArrayForForm15.add(question);
        }

    }






    public static void loadForm14(){
        {
            //List of constituencies already shared
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Constituency";
            question.questionTextInUrdu = "حلقے کا نام";
            question.questionType = kQuestionTypeSpinnerPicker;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;

            option.mutableArrayForPickerItems = getListOfConstituencies();
            for(int i=0; i<option.mutableArrayForPickerItems.size();i++){
                FormOptionModel tempOption = new FormOptionModel();
                tempOption.optionTextInEnglish = option.mutableArrayForPickerItems.get(i);
                tempOption.optionTextInUrdu = "";
                question.optionsArray .add(tempOption);
            }

            _mutableArrayForForm14.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            //List of electoral area will be provided by TDEA
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Name of Electoral Area";
            question.questionTextInUrdu = "الیکٹورل ایریا کا نام";
            question.questionType = kQuestionTypeSingleEntry;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;;
            question.optionsArray.add(option);
            _mutableArrayForForm14.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {

            FormQuestionModel question1 = new FormQuestionModel();
            question1.questionTextInEnglish = "Are Potraits displayed or not?";
            question1.questionTextInUrdu = " پورٹریٹ لگے ہوئے ہیں یا نہیں";
            question1.questionType = kQuestionTypeYESNO;
            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "Yes";
            {//sub branch 1
                FormQuestionModel question1_1 = new FormQuestionModel();
                question1_1.questionTextInEnglish = "Political Party/Candidate 1: Is name of the publisher written or not?";
                question1_1.questionTextInUrdu = "پبلشر کا نام درج ہے یا نہیں";
                question1_1.questionType = kQuestionTypeYESNO;
                FormOptionModel option1_1 = new FormOptionModel();
                option1_1.optionTextInEnglish = "Yes";
                option1_1.optionTextInUrdu = "ہاں";
                FormOptionModel option2_1 = new FormOptionModel();
                option2_1.optionTextInEnglish = "No";
                option2_1.optionTextInUrdu = "نہیں";
                question1_1.optionsArray.add(option1_1);
                question1_1.optionsArray.add(option2_1);
                option1.mutableArrayForDependantQuestions.add(question1_1);
            }
            {//sub branch 2
                FormQuestionModel question1_2 = new FormQuestionModel();
                question1_2.questionTextInEnglish = "Political Party/Candidate 1: Size of the potrait (height x width)";
                question1_2.questionTextInUrdu = "اشتہار کا سائز";
                question1_2.questionType = kQuestionTypeOptions;
                FormOptionModel option1_2 = new FormOptionModel();
                option1_2.optionTextInEnglish = "5x3 Feets";
                option1_2.optionTextInUrdu = "5x3 فٹ";
                FormOptionModel option2_2 = new FormOptionModel();
                option2_2.optionTextInEnglish = "Larger than 5x3 Feets";
                option2_2.optionTextInUrdu = "5x3 فٹ سے بڑا";
                question1_2.optionsArray.add(option1_2);
                question1_2.optionsArray.add(option2_2);
                option1.mutableArrayForDependantQuestions .add(question1_2);
            }
            {//sub branch 3
                FormQuestionModel question1_3 = new FormQuestionModel();
                question1_3.questionTextInEnglish = "Political Party/Candidate 1: Name of Political Party";
                question1_3.questionTextInUrdu = "اشتہار کس کے لگے ہیں"; //list of all candidates of political party
                question1_3.questionType = kQuestionTypeSpinnerPicker;
                FormOptionModel option1_3 = new FormOptionModel();
                option1_3.isEnteryTypeOption = true;
                option1_3.mutableArrayForPickerItems = getListOfPolticalParties();
                for(int i=0; i<option1_3.mutableArrayForPickerItems.size();i++){
                    FormOptionModel tempOption = new FormOptionModel();
                    tempOption.optionTextInEnglish = option1_3.mutableArrayForPickerItems.get(i);
                    tempOption.optionTextInUrdu = "";
                    question1_3.optionsArray .add(tempOption);
                }
                option1.mutableArrayForDependantQuestions .add(question1_3);
            }
            {//sub branch 4 will appear after selecting a party
                FormQuestionModel question1_4 = new FormQuestionModel();
                question1_4.questionTextInEnglish = "Political Party/Candidate 1: Name of Candidate";
                question1_4.questionTextInUrdu = "امیدوار کا نام";
                question1_4.questionType = kQuestionTypeSingleEntry;
                FormOptionModel option1_4 = new FormOptionModel();
                option1_4.isEnteryTypeOption = true;
                question1_4.optionsArray .add(option1_4);
                option1.mutableArrayForDependantQuestions .add(question1_4);
            }

            option1.optionTextInUrdu = "ہاں";
            FormOptionModel option2 = new FormOptionModel();
            option2.optionTextInEnglish = "No";
            option2.optionTextInUrdu = "نہیں";
            question1.optionsArray .add(option1);
            question1.optionsArray .add(option2);
            _mutableArrayForForm14 .add(question1);

        }

        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {

            FormQuestionModel question1 = new FormQuestionModel();
            question1.questionTextInEnglish = "Are Posters displayed or not";
            question1.questionTextInUrdu = "پوسٹر لگے ہوئے ہیں یا نہیں";
            question1.questionType = kQuestionTypeYESNO;
            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "Yes";
            {//sub branch 1
                FormQuestionModel question1_1 = new FormQuestionModel();
                question1_1.questionTextInEnglish = "Political Party/Candidate 1: Is name of the publisher written or not?";
                question1_1.questionTextInUrdu = "پبلشر کا نام درج ہے یا نہیں";
                question1_1.questionType = kQuestionTypeYESNO;
                FormOptionModel option1_1 = new FormOptionModel();
                option1_1.optionTextInEnglish = "Yes";
                option1_1.optionTextInUrdu = "ہاں";
                FormOptionModel option2_1 = new FormOptionModel();
                option2_1.optionTextInEnglish = "No";
                option2_1.optionTextInUrdu = "نہیں";
                question1_1.optionsArray.add(option1_1);
                question1_1.optionsArray.add(option2_1);
                option1.mutableArrayForDependantQuestions.add(question1_1);
            }
            {//sub branch 2
                FormQuestionModel question1_2 = new FormQuestionModel();
                question1_2.questionTextInEnglish = "Political Party/Candidate 1: Size of the posters (height x width)";
                question1_2.questionTextInUrdu = "اشتہار کا سائز";
                question1_2.questionType = kQuestionTypeOptions;
                FormOptionModel option1_2 = new FormOptionModel();
                option1_2.optionTextInEnglish = "2x3 Feets";
                option1_2.optionTextInUrdu = "2x3 فٹ";
                FormOptionModel option2_2 = new FormOptionModel();
                option2_2.optionTextInEnglish = "Larger than 2x3 Feets";
                option2_2.optionTextInUrdu = "2x3 فٹ سے بڑا";
                question1_2.optionsArray.add(option1_2);
                question1_2.optionsArray.add(option2_2);
                option1.mutableArrayForDependantQuestions.add(question1_2);
            }
            {//sub branch 3
                FormQuestionModel question1_3 = new FormQuestionModel();
                question1_3.questionTextInEnglish = "Political Party/Candidate 1: Name of Political Party";
                question1_3.questionTextInUrdu = "اشتہار کس کے لگے ہیں"; //list of all candidates of political party
                question1_3.questionType = kQuestionTypeSpinnerPicker;
                FormOptionModel option1_3 = new FormOptionModel();
                option1_3.isEnteryTypeOption = true;
                option1_3.mutableArrayForPickerItems = getListOfPolticalParties();
                for(int i=0; i<option1_3.mutableArrayForPickerItems.size();i++){
                    FormOptionModel tempOption = new FormOptionModel();
                    tempOption.optionTextInEnglish = option1_3.mutableArrayForPickerItems.get(i);
                    tempOption.optionTextInUrdu = "";
                    question1_3.optionsArray .add(tempOption);
                }
                option1.mutableArrayForDependantQuestions .add(question1_3);
            }
            {//sub branch 4 will appear after selecting a party
                FormQuestionModel question1_4 = new FormQuestionModel();
                question1_4.questionTextInEnglish = "Political Party/Candidate 1: Name of Candidate";
                question1_4.questionTextInUrdu = "امیدوار کا نام";
                question1_4.questionType = kQuestionTypeSingleEntry;
                FormOptionModel option1_4 = new FormOptionModel();
                option1_4.isEnteryTypeOption = true;
                question1_4.optionsArray .add(option1_4);
                option1.mutableArrayForDependantQuestions .add(question1_4);
            }

            option1.optionTextInUrdu = "ہاں";
            FormOptionModel option2 = new FormOptionModel();
            option2.optionTextInEnglish = "No";
            option2.optionTextInUrdu = "نہیں";
            question1.optionsArray .add(option1);
            question1.optionsArray .add(option2);
            _mutableArrayForForm14 .add(question1);

        }

        //////////////////////////////////////////////////////////////////////////////////////////////////////

        {

            FormQuestionModel question1 = new FormQuestionModel();
            question1.questionTextInEnglish = "Are Banners displayed or not";
            question1.questionTextInUrdu = "بینر لگے ہوئے ہیں یا نہیں";
            question1.questionType = kQuestionTypeYESNO;
            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "Yes";
            {//sub branch 1
                FormQuestionModel question1_1 = new FormQuestionModel();
                question1_1.questionTextInEnglish = "Political Party/Candidate 1: Is name of the publisher written or not?";
                question1_1.questionTextInUrdu = "پبلشر کا نام درج ہے یا نہیں";
                question1_1.questionType = kQuestionTypeYESNO;
                FormOptionModel option1_1 = new FormOptionModel();
                option1_1.optionTextInEnglish = "Yes";
                option1_1.optionTextInUrdu = "ہاں";
                FormOptionModel option2_1 = new FormOptionModel();
                option2_1.optionTextInEnglish = "No";
                option2_1.optionTextInUrdu = "نہیں";
                question1_1.optionsArray.add(option1_1);
                question1_1.optionsArray.add(option2_1);
                option1.mutableArrayForDependantQuestions .add(question1_1);
            }
            {//sub branch 2
                FormQuestionModel question1_2 = new FormQuestionModel();
                question1_2.questionTextInEnglish = "Political Party/Candidate 1: Size of the banners (height x width)";
                question1_2.questionTextInUrdu = "اشتہار کا سائز";
                question1_2.questionType = kQuestionTypeOptions;
                FormOptionModel option1_2 = new FormOptionModel();
                option1_2.optionTextInEnglish = "3x9 Feets";
                option1_2.optionTextInUrdu = "3x9 فٹ";
                FormOptionModel option2_2 = new FormOptionModel();
                option2_2.optionTextInEnglish = "Larger than 3x9 Feets";
                option2_2.optionTextInUrdu = "3x9 فٹ سے بڑا";
                question1_2.optionsArray.add(option1_2);
                question1_2.optionsArray.add(option2_2);
                option1.mutableArrayForDependantQuestions .add(question1_2);
            }
            {//sub branch 3
                FormQuestionModel question1_3 = new FormQuestionModel();
                question1_3.questionTextInEnglish = "Political Party/Candidate 1: Name of Political Party";
                question1_3.questionTextInUrdu = "اشتہار کس کے لگے ہیں"; //list of all candidates of political party
                question1_3.questionType = kQuestionTypeSpinnerPicker;
                FormOptionModel option1_3 = new FormOptionModel();
                option1_3.isEnteryTypeOption = true;

                option1_3.mutableArrayForPickerItems = getListOfPolticalParties();
                for(int i=0; i<option1_3.mutableArrayForPickerItems.size();i++){
                    FormOptionModel tempOption = new FormOptionModel();
                    tempOption.optionTextInEnglish = option1_3.mutableArrayForPickerItems.get(i);
                    tempOption.optionTextInUrdu = "";
                    question1_3.optionsArray .add(tempOption);
                }

                option1.mutableArrayForDependantQuestions .add(question1_3);

/*

                FormQuestionModel question1_2 = new FormQuestionModel();
                question1_2.questionTextInEnglish = "Size of Ad";
                question1_2.questionTextInUrdu = "اشتہار کا سائز";
                question1_2.questionType = kQuestionTypeOptions;
                FormOptionModel option1_2 = new FormOptionModel();
                option1_2.optionTextInEnglish = "3x9 Feets";
                option1_2.optionTextInUrdu = "3x9 فٹ";
                FormOptionModel option2_2 = new FormOptionModel();
                option2_2.optionTextInEnglish = "Larger than 3x9 Feets";
                option2_2.optionTextInUrdu = "3x9 فٹ سے بڑا";
                question1_2.optionsArray.add(option1_2);
                question1_2.optionsArray.add(option2_2);
                option1.mutableArrayForDependantQuestions .add(question1_2);*/



            }
            {//sub branch 4 will appear after selecting a party
                FormQuestionModel question1_4 = new FormQuestionModel();
                question1_4.questionTextInEnglish = "Political Party/Candidate 1: Name of Candidate";
                question1_4.questionTextInUrdu = "امیدوار کا نام";
                question1_4.questionType = kQuestionTypeSingleEntry;
                FormOptionModel option1_4 = new FormOptionModel();
                option1_4.isEnteryTypeOption = true;
                question1_4.optionsArray .add(option1_4);
                option1.mutableArrayForDependantQuestions .add(question1_4);
            }

            option1.optionTextInUrdu = "ہاں";
            FormOptionModel option2 = new FormOptionModel();
            option2.optionTextInEnglish = "No";
            option2.optionTextInUrdu = "نہیں";
            question1.optionsArray .add(option1);
            question1.optionsArray .add(option2);
            _mutableArrayForForm14 .add(question1);

        }

        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {

            FormQuestionModel question1 = new FormQuestionModel();
            question1.questionTextInEnglish = "Are Pamphlet displayed or not";
            question1.questionTextInUrdu = "پمفلٹ لگے ہوئے ہیں یا نہیں";
            question1.questionType = kQuestionTypeYESNO;
            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "Yes";
            {//sub branch 1
                FormQuestionModel question1_1 = new FormQuestionModel();
                question1_1.questionTextInEnglish = "Political Party/Candidate 1: Is name of the publisher written or not?";
                question1_1.questionTextInUrdu = "پبلشر کا نام درج ہے یا نہیں";
                question1_1.questionType = kQuestionTypeYESNO;
                FormOptionModel option1_1 = new FormOptionModel();
                option1_1.optionTextInEnglish = "Yes";
                option1_1.optionTextInUrdu = "ہاں";
                FormOptionModel option2_1 = new FormOptionModel();
                option2_1.optionTextInEnglish = "No";
                option2_1.optionTextInUrdu = "نہیں";
                question1_1.optionsArray.add(option1_1);
                question1_1.optionsArray.add(option2_1);
                option1.mutableArrayForDependantQuestions .add(question1_1);
            }
            {//sub branch 2
                FormQuestionModel question1_2 = new FormQuestionModel();
                question1_2.questionTextInEnglish = "Political Party/Candidate 1: Size of the Pamphlet (height x width)";
                question1_2.questionTextInUrdu = "اشتہار کا سائز";
                question1_2.questionType = kQuestionTypeOptions;
                FormOptionModel option1_2 = new FormOptionModel();
                option1_2.optionTextInEnglish = "3x5 Inches";
                option1_2.optionTextInUrdu = "9x6 انچ";
                FormOptionModel option2_2 = new FormOptionModel();
                option2_2.optionTextInEnglish = "Larger than 9x6 Inches";
                option2_2.optionTextInUrdu = "9x6 انچ سے بڑا";
                question1_2.optionsArray.add(option1_2);
                question1_2.optionsArray.add(option2_2);
                option1.mutableArrayForDependantQuestions .add(question1_2);
            }
            {//sub branch 3
                FormQuestionModel question1_3 = new FormQuestionModel();
                question1_3.questionTextInEnglish = "Political Party/Candidate 1: Name of Political Party";
                question1_3.questionTextInUrdu = "اشتہار کس کے لگے ہیں"; //list of all candidates of political party
                question1_3.questionType = kQuestionTypeSpinnerPicker;
                FormOptionModel option1_3 = new FormOptionModel();
                option1_3.isEnteryTypeOption = true;
                option1_3.mutableArrayForPickerItems = getListOfPolticalParties();
                for(int i=0; i<option1_3.mutableArrayForPickerItems.size();i++){
                    FormOptionModel tempOption = new FormOptionModel();
                    tempOption.optionTextInEnglish = option1_3.mutableArrayForPickerItems.get(i);
                    tempOption.optionTextInUrdu = "";
                    question1_3.optionsArray .add(tempOption);
                }
                option1.mutableArrayForDependantQuestions .add(question1_3);
            }
            {//sub branch 4 will appear after selecting a party
                FormQuestionModel question1_4 = new FormQuestionModel();
                question1_4.questionTextInEnglish = "Political Party/Candidate 1: Name of Candidate";
                question1_4.questionTextInUrdu = "امیدوار کا نام";
                question1_4.questionType = kQuestionTypeSingleEntry;
                FormOptionModel option1_4 = new FormOptionModel();
                option1_4.isEnteryTypeOption = true;
                question1_4.optionsArray .add(option1_4);
                option1.mutableArrayForDependantQuestions .add(question1_4);
            }

            option1.optionTextInUrdu = "ہاں";
            FormOptionModel option2 = new FormOptionModel();
            option2.optionTextInEnglish = "No";
            option2.optionTextInUrdu = "نہیں";
            question1.optionsArray .add(option1);
            question1.optionsArray .add(option2);
            _mutableArrayForForm14 .add(question1);

        }

        //////////////////////////////////////////////////////////////////////////////////////////////////////

        {

            FormQuestionModel question1 = new FormQuestionModel();
            question1.questionTextInEnglish = "Are Wall Chalking displayed or not";
            question1.questionTextInUrdu = "وال چاکنگ لگے ہوئے ہیں یا نہیں";
            question1.questionType = kQuestionTypeYESNO;
            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "Yes";
            {//sub branch 1
                FormQuestionModel question1_1 = new FormQuestionModel();
                question1_1.questionTextInEnglish = "Is name of the publisher written or not?";
                question1_1.questionTextInUrdu = "پبلشر کا نام درج ہے یا نہیں";
                question1_1.questionType = kQuestionTypeYESNO;
                FormOptionModel option1_1 = new FormOptionModel();
                option1_1.optionTextInEnglish = "Yes";
                option1_1.optionTextInUrdu = "ہاں";
                FormOptionModel option2_1 = new FormOptionModel();
                option2_1.optionTextInEnglish = "No";
                option2_1.optionTextInUrdu = "نہیں";
                question1_1.optionsArray.add(option1_1);
                question1_1.optionsArray.add(option2_1);
                option1.mutableArrayForDependantQuestions .add(question1_1);
            }
            {//sub branch 2
                FormQuestionModel question1_2 = new FormQuestionModel();
                question1_2.questionTextInEnglish = "Political Party/Candidate 1: Size of the Wall Chalking (height x width)";
                question1_2.questionTextInUrdu = "اشتہار کا سائز (فٹ)";
                question1_2.questionType = kQuestionTypeNumeric;
                FormOptionModel option1_2 = new FormOptionModel();
                option1_2.isEnteryTypeOption = true;
                question1_2.optionsArray.add(option1_2);
                option1.mutableArrayForDependantQuestions .add(question1_2);
            }
            {//sub branch 3
                FormQuestionModel question1_3 = new FormQuestionModel();
                question1_3.questionTextInEnglish = "Political Party/Candidate 1: Name of Political Party";
                question1_3.questionTextInUrdu = "اشتہار کس کے لگے ہیں"; //list of all candidates of political party
                question1_3.questionType = kQuestionTypeSpinnerPicker;
                FormOptionModel option1_3 = new FormOptionModel();
                option1_3.isEnteryTypeOption = true;
                option1_3.mutableArrayForPickerItems = getListOfPolticalParties();
                for(int i=0; i<option1_3.mutableArrayForPickerItems.size();i++){
                    FormOptionModel tempOption = new FormOptionModel();
                    tempOption.optionTextInEnglish = option1_3.mutableArrayForPickerItems.get(i);
                    tempOption.optionTextInUrdu = "";
                    question1_3.optionsArray .add(tempOption); //eeed to check
                }
                option1.mutableArrayForDependantQuestions .add(question1_3);
            }
            {//sub branch 4 will appear after selecting a party
                FormQuestionModel question1_4 = new FormQuestionModel();
                question1_4.questionTextInEnglish = "Political Party/Candidate 1: Name of Candidate";
                question1_4.questionTextInUrdu = "امیدوار کا نام";
                question1_4.questionType = kQuestionTypeSingleEntry;
                FormOptionModel option1_4 = new FormOptionModel();
                option1_4.isEnteryTypeOption = true;
                question1_4.optionsArray .add(option1_4);
                option1.mutableArrayForDependantQuestions .add(question1_4);
            }

            option1.optionTextInUrdu = "ہاں";
            FormOptionModel option2 = new FormOptionModel();
            option2.optionTextInEnglish = "No";
            option2.optionTextInUrdu = "نہیں";
            question1.optionsArray .add(option1);
            question1.optionsArray .add(option2);
            _mutableArrayForForm14 .add(question1);

        }

        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {

            FormQuestionModel question1 = new FormQuestionModel();
            question1.questionTextInEnglish = "Are Panaflex displayed or not";
            question1.questionTextInUrdu = "پینافلیکس لگے ہوئے ہیں یا نہیں";
            question1.questionType = kQuestionTypeYESNO;
            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "Yes";
            {//sub branch 1
                FormQuestionModel question1_1 = new FormQuestionModel();
                question1_1.questionTextInEnglish = "Is name of the publisher written or not?";
                question1_1.questionTextInUrdu = "پبلشر کا نام درج ہے یا نہیں";
                question1_1.questionType = kQuestionTypeYESNO;
                FormOptionModel option1_1 = new FormOptionModel();
                option1_1.optionTextInEnglish = "Yes";
                option1_1.optionTextInUrdu = "ہاں";
                FormOptionModel option2_1 = new FormOptionModel();
                option2_1.optionTextInEnglish = "No";
                option2_1.optionTextInUrdu = "نہیں";
                question1_1.optionsArray.add(option1_1);
                question1_1.optionsArray.add(option2_1);
                option1.mutableArrayForDependantQuestions .add(question1_1);
            }
            {//sub branch 2
                FormQuestionModel question1_2 = new FormQuestionModel();
                question1_2.questionTextInEnglish = "Political Party/Candidate 1: Size of the Panaflex (height x width)";
                question1_2.questionTextInUrdu = "اشتہار کا سائز (فٹ)";
                question1_2.questionType = kQuestionTypeNumeric;
                FormOptionModel option1_2 = new FormOptionModel();
                option1_2.isEnteryTypeOption = true;
                question1_2.optionsArray.add(option1_2);
                option1.mutableArrayForDependantQuestions .add(question1_2);
            }
            {//sub branch 3
                FormQuestionModel question1_3 = new FormQuestionModel();
                question1_3.questionTextInEnglish = "Political Party/Candidate 1: Name of Political Party";
                question1_3.questionTextInUrdu = "اشتہار کس کے لگے ہیں"; //list of all candidates of political party
                question1_3.questionType = kQuestionTypeSpinnerPicker;
                FormOptionModel option1_3 = new FormOptionModel();
                option1_3.isEnteryTypeOption = true;
                option1_3.mutableArrayForPickerItems = getListOfPolticalParties();
                for(int i=0; i<option1_3.mutableArrayForPickerItems.size();i++){
                    FormOptionModel tempOption = new FormOptionModel();
                    tempOption.optionTextInEnglish = option1_3.mutableArrayForPickerItems.get(i);
                    tempOption.optionTextInUrdu = "";
                    question1_3.optionsArray .add(tempOption);
                }
                option1.mutableArrayForDependantQuestions .add(question1_3);
            }
            {//sub branch 4 will appear after selecting a party
                FormQuestionModel question1_4 = new FormQuestionModel();
                question1_4.questionTextInEnglish = "Political Party/Candidate 1: Name of Candidate";
                question1_4.questionTextInUrdu = "امیدوار کا نام";
                question1_4.questionType = kQuestionTypeSingleEntry;
                FormOptionModel option1_4 = new FormOptionModel();
                option1_4.isEnteryTypeOption = true;
                question1_4.optionsArray .add(option1_4);
                option1.mutableArrayForDependantQuestions .add(question1_4);
            }

            option1.optionTextInUrdu = "ہاں";
            FormOptionModel option2 = new FormOptionModel();
            option2.optionTextInEnglish = "No";
            option2.optionTextInUrdu = "نہیں";
            question1.optionsArray .add(option1);
            question1.optionsArray .add(option2);
            _mutableArrayForForm14 .add(question1);

        }

        //////////////////////////////////////////////////////////////////////////////////////////////////////

        {

            FormQuestionModel question1 = new FormQuestionModel();
            question1.questionTextInEnglish = "Are Billboard displayed or not";
            question1.questionTextInUrdu = "بل بورڈ لگے ہوئے ہیں یا نہیں";
            question1.questionType = kQuestionTypeYESNO;
            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "Yes";
            {//sub branch 1
                FormQuestionModel question1_1 = new FormQuestionModel();
                question1_1.questionTextInEnglish = "Is name of the publisher written or not?";
                question1_1.questionTextInUrdu = "پبلشر کا نام درج ہے یا نہیں";
                question1_1.questionType = kQuestionTypeYESNO;
                FormOptionModel option1_1 = new FormOptionModel();
                option1_1.optionTextInEnglish = "Yes";
                option1_1.optionTextInUrdu = "ہاں";
                FormOptionModel option2_1 = new FormOptionModel();
                option2_1.optionTextInEnglish = "No";
                option2_1.optionTextInUrdu = "نہیں";
                question1_1.optionsArray.add(option1_1);
                question1_1.optionsArray.add(option2_1);
                option1.mutableArrayForDependantQuestions.add(question1_1);
            }
            {//sub branch 2
                FormQuestionModel question1_2 = new FormQuestionModel();
                question1_2.questionTextInEnglish = "Political Party/Candidate 1: Size of the Billboard (height x width)";
                question1_2.questionTextInUrdu = "اشتہار کا سائز (فٹ)";
                question1_2.questionType = kQuestionTypeNumeric;
                FormOptionModel option1_2 = new FormOptionModel();
                option1_2.isEnteryTypeOption = true;
                question1_2.optionsArray.add(option1_2);
                option1.mutableArrayForDependantQuestions .add(question1_2);
            }
            {//sub branch 3
                FormQuestionModel question1_3 = new FormQuestionModel();
                question1_3.questionTextInEnglish = "Political Party/Candidate 1: Name of Political Party";
                question1_3.questionTextInUrdu = "اشتہار کس کے لگے ہیں"; //list of all candidates of political party
                question1_3.questionType = kQuestionTypeSpinnerPicker;
                FormOptionModel option1_3 = new FormOptionModel();
                option1_3.isEnteryTypeOption = true;
                option1_3.mutableArrayForPickerItems = getListOfPolticalParties();
                for(int i=0; i<option1_3.mutableArrayForPickerItems.size();i++){
                    FormOptionModel tempOption = new FormOptionModel();
                    tempOption.optionTextInEnglish = option1_3.mutableArrayForPickerItems.get(i);
                    tempOption.optionTextInUrdu = "";
                    question1_3.optionsArray .add(tempOption);
                }
                option1.mutableArrayForDependantQuestions .add(question1_3);
            }
            {//sub branch 4 will appear after selecting a party
                FormQuestionModel question1_4 = new FormQuestionModel();
                question1_4.questionTextInEnglish = "Political Party/Candidate 1: Name of Candidate";
                question1_4.questionTextInUrdu = "امیدوار کا نام";
                question1_4.questionType = kQuestionTypeSingleEntry;
                FormOptionModel option1_4 = new FormOptionModel();
                option1_4.isEnteryTypeOption = true;
                question1_4.optionsArray .add(option1_4);
                option1.mutableArrayForDependantQuestions .add(question1_4);
            }

            option1.optionTextInUrdu = "ہاں";
            FormOptionModel option2 = new FormOptionModel();
            option2.optionTextInEnglish = "No";
            option2.optionTextInUrdu = "نہیں";
            question1.optionsArray .add(option1);
            question1.optionsArray .add(option2);
            _mutableArrayForForm14 .add(question1);

        }

        //////////////////////////////////////////////////////////////////////////////////////////////////////


        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Have you seen any party flag displayed on any government building?";
            question.questionTextInUrdu = "کیا آپ نے کسی پارٹی کا جھنڈا کسی سرکاری عمارت پر لگا دیکھا؟";
            question.questionType = kQuestionTypeYESNO;
            FormOptionModel option = new FormOptionModel();
            option.optionTextInEnglish = "Yes";
            {
                //List of Political Parties
                FormQuestionModel question1 = new FormQuestionModel();
                question1.questionTextInEnglish = "Name of the political party";
                question1.questionTextInUrdu = "پارٹی کا نام";
                question1.questionType = kQuestionTypeSpinnerPicker;
                FormOptionModel option1 = new FormOptionModel();
                option1.isEnteryTypeOption = true;
                option1.mutableArrayForPickerItems = getListOfPolticalParties();
                for(int i=0; i<option1.mutableArrayForPickerItems.size();i++){


                    FormOptionModel tempOption = new FormOptionModel();
                    tempOption.optionTextInEnglish = option1.mutableArrayForPickerItems.get(i);
                    tempOption.optionTextInUrdu = "";
                    question1.optionsArray .add(tempOption);
                }
                option.mutableArrayForDependantQuestions .add(question1);
            }

            {
                FormQuestionModel question2 = new FormQuestionModel();
                question2.questionTextInEnglish = "Name of the government’s institution";
                question2.questionTextInUrdu = "ادارے کا نام";
                question2.questionType = kQuestionTypeSingleEntry;
                FormOptionModel option2 = new FormOptionModel();
                option2.isEnteryTypeOption = true;
                question2.optionsArray .add(option2);
                option.mutableArrayForDependantQuestions .add(question2);
            }
            option.optionTextInUrdu = "ہاں";
            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "No";
            option1.optionTextInUrdu = "نہیں";
            question.optionsArray.add(option);
            question.optionsArray .add(option1);
            _mutableArrayForForm14.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////

        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Have you seen advertisement of any political party on any government building?";
            question.questionTextInUrdu = "کیا آپ نے کسی پارٹی کا اشتہاری مواد کسی سرکاری عمارت پر لگا دیکھا؟";
            question.questionType = kQuestionTypeYESNO;
            FormOptionModel option = new FormOptionModel();
            option.optionTextInEnglish = "Yes";
            {
                //List of Political Parties
                FormQuestionModel question1 = new FormQuestionModel();
                question1.questionTextInEnglish = "Name of the political party";
                question1.questionTextInUrdu = "پارٹی کا نام";
                question1.questionType = kQuestionTypeSpinnerPicker;
                FormOptionModel option1 = new FormOptionModel();
                option1.isEnteryTypeOption = true;
                option1.mutableArrayForPickerItems = getListOfPolticalParties();
                for(int i=0; i<option1.mutableArrayForPickerItems.size();i++){
                    FormOptionModel tempOption = new FormOptionModel();
                    tempOption.optionTextInEnglish = option1.mutableArrayForPickerItems.get(i);
                    tempOption.optionTextInUrdu = "";
                    question1.optionsArray .add(tempOption);
                }
                option.mutableArrayForDependantQuestions .add(question1);
            }

            {
                FormQuestionModel question2 = new FormQuestionModel();
                question2.questionTextInEnglish = "Name of the government’s institution";
                question2.questionTextInUrdu = "ادارے کا نام";
                question2.questionType = kQuestionTypeSingleEntry;
                FormOptionModel option2 = new FormOptionModel();
                option2.isEnteryTypeOption = true;
                question2.optionsArray .add(option2);
                option.mutableArrayForDependantQuestions .add(question2);
            }
            option.optionTextInUrdu = "ہاں";
            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "No";
            option1.optionTextInUrdu = "نہیں";
            question.optionsArray.add(option);
            question.optionsArray .add(option1);
            _mutableArrayForForm14.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////

    }
    //-----------------------------------------------------------------------------------------------------------------------------------------------------
    public static void loadForm16(){
        {
            //13 digits only (system should not accept more or less numbers) - Same CNIC should not be profiled twice. Format should be 36203-5555779-0
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "NIC Number";
            question.questionTextInUrdu = "شناختی کارڈ نمبر";
            question.questionType = kQuestionTypeNIC;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;
            question.optionsArray.add(option);
            mutableArrayForForm16.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            //Calender to choose the date of expiry, user should not be able to select back from currunt date
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Date of Expiry of NIC";
            question.questionTextInUrdu = "شناختی کارڈ کی تاریخ تنسیخ";
            question.questionType = kQuestionTypeDate;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;
            question.optionsArray.add(option);
            mutableArrayForForm16.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {

            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Name of Observer (As appeared in NIC)";
            question.questionTextInUrdu = "مشاہدہ کار کا پورا نام (شناختی کارڈ والا نام لکھیں)";
            question.questionType = kQuestionTypeSingleEntry;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;
            question.optionsArray.add(option);
            mutableArrayForForm16.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Gender";
            question.questionTextInUrdu = "جنس";
            question.questionType = kQuestionTypeOptions;

            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "Male";
            option1.optionTextInUrdu = "مرد";

            FormOptionModel option2 = new FormOptionModel();
            option2.optionTextInEnglish = "Female";
            option2.optionTextInUrdu = "عورت";

            FormOptionModel option3 = new FormOptionModel();
            option3.optionTextInEnglish = "Transgender";
            option3.optionTextInUrdu = "خواجہ سرا";


            question.optionsArray .add(option1);
            question.optionsArray .add(option2);
            question.optionsArray .add(option3);

            mutableArrayForForm16.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {

            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Date of Birth";
            question.questionTextInUrdu = "پیدائش کی تاریخ";
            question.questionType = kQuestionTypeDate;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;
            question.optionsArray.add(option);
            mutableArrayForForm16.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////

        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Educational Qualification";
            question.questionTextInUrdu = "تعلیمی قابلیت";
            question.questionType = kQuestionTypeOptions;

            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "Masters";
            option1.optionTextInUrdu = "ماسٹرز";

            FormOptionModel option2 = new FormOptionModel();
            option2.optionTextInEnglish = "Bachelors";
            option2.optionTextInUrdu = "بیچلرز";

            FormOptionModel option3 = new FormOptionModel();
            option3.optionTextInEnglish = "Intermediate";
            option3.optionTextInUrdu = "انٹرمیڈیٹ";

            FormOptionModel option4 = new FormOptionModel();
            option4.optionTextInEnglish = "Matriculation";
            option4.optionTextInUrdu = "میٹرک";

            FormOptionModel option5 = new FormOptionModel();
            option5.optionTextInEnglish = "Illiterate";
            option5.optionTextInUrdu = "ناخواندہ";


            question.optionsArray .add(option1);
            question.optionsArray .add(option2);
            question.optionsArray .add(option3);
            question.optionsArray .add(option4);
            question.optionsArray .add(option5);

            mutableArrayForForm16.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            //Number format should be like 0300-0000000
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Mobile Number";
            question.questionTextInUrdu = "موبائل نمبر";
            question.questionType = kQuestionTypePhoneNumber;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;
            question.optionsArray.add(option);
            mutableArrayForForm16.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////

        {
            //Email validation check
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Email Address";
            question.questionTextInUrdu = "ای میل ایڈریس";
            question.questionType = kQuestionTypeEmail;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;
            question.optionsArray.add(option);
            mutableArrayForForm16.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            //Constituency Coordinator Organization will be the Default Organization of the observer
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Organization";
            question.questionTextInUrdu = "تنظیم کا نام";
            question.questionType = kQuestionTypeSingleEntry;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;
            question.optionsArray.add(option);
            mutableArrayForForm16.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Designation";
            question.questionTextInUrdu = "عہدہ";
            question.questionType = kQuestionTypeOptions;

            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "Mobile Observer";
            option1.optionTextInUrdu = "موبائل مشاہدہ کار";

            FormOptionModel option2 = new FormOptionModel();
            option2.optionTextInEnglish = "Static Observer";
            option2.optionTextInUrdu = "سٹیٹک مشاہدہ کار";

            question.optionsArray .add(option1);
            question.optionsArray .add(option2);


            mutableArrayForForm16.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            //Place holder : "As per NIC"
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Permanent Address";
            question.questionTextInUrdu = "مستقل پتہ";
            question.questionType = kQuestionTypeSingleEntry;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;
            question.optionsArray.add(option);
            mutableArrayForForm16.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            //place holder "As per NIC"
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Temporary Address";
            question.questionTextInUrdu = "عارضی پتہ";
            question.questionType = kQuestionTypeSingleEntry;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;
            question.optionsArray.add(option);
            mutableArrayForForm16.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            //List of districts to be provided by TDEA
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "District";
            question.questionTextInUrdu = "ضلع";
            question.questionType = kQuestionTypeSpinnerPicker;
            FormOptionModel option = new FormOptionModel();

            option.mutableArrayForPickerItems = getListOfDistricts();
            for(int i=0; i<option.mutableArrayForPickerItems.size();i++){
                FormOptionModel tempOption = new FormOptionModel();
                tempOption.optionTextInEnglish = option.mutableArrayForPickerItems.get(i);
                tempOption.optionTextInUrdu = "";
                question.optionsArray .add(tempOption);
            }
            option.isEnteryTypeOption = true;

            mutableArrayForForm16.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////

        {
            //List of constituencies already provided
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Name of Constituency";
            question.questionTextInUrdu =  "حلقے کا نام";
            question.questionType = kQuestionTypeSpinnerPicker;
            FormOptionModel option = new FormOptionModel();
            option.isEnteryTypeOption = true;
            option.mutableArrayForPickerItems = getListOfConstituencies();
            for(int i=0; i<option.mutableArrayForPickerItems.size();i++){
                FormOptionModel tempOption = new FormOptionModel();
                tempOption.optionTextInEnglish = option.mutableArrayForPickerItems.get(i);
                tempOption.optionTextInUrdu = "";
                question.optionsArray .add(tempOption);
            }
            mutableArrayForForm16.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Does the election day observer has android phone/iPhone?";
            question.questionTextInUrdu = "کیا مشاہدہ کار کے پاس سمارٹ فون موجود ہے؟";
            question.questionType = kQuestionTypeYESNO;
            FormOptionModel option = new FormOptionModel();
            option.optionTextInEnglish = "Yes";
            {
                FormQuestionModel question1 = new FormQuestionModel();
                question1.questionTextInEnglish = "Which model of smart phone is with the observer?";
                question1.questionTextInUrdu = "مشاہدہ کار کے پاس سمارٹ فون کا کون سا ماڈل ہے؟";
                question1.questionType = kQuestionTypeSingleEntry;

                FormOptionModel option2 = new FormOptionModel();
                option2.isEnteryTypeOption = true;

                question1.optionsArray .add(option2);
                option.mutableArrayForDependantQuestions .add(question1);
            }
            option.optionTextInUrdu = "ہاں";
            FormOptionModel option1 = new FormOptionModel();
            option1.optionTextInEnglish = "No";
            option1.optionTextInUrdu = "نہیں";
            question.optionsArray.add(option);
            question.optionsArray .add(option1);
            mutableArrayForForm16.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////

        {
            //photo type questions
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Upload passport size picture of observer";
            question.questionTextInUrdu = "مشاہدہ کار کی پاسپورٹ سائز تصویر اپلوڈ کریں";
            question.questionType = kQuestionTypePhoto;
            FormOptionModel option = new FormOptionModel();
            option.isPhotoUploadTypeOption= true;
            question.optionsArray.add(option);
            mutableArrayForForm16.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            //photo type questions
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Upload Front side of NIC";
            question.questionTextInUrdu = "شناختی کارڈ کی فرنٹ سائیڈ کی تصویر اپلوڈ کریں";
            question.questionType = kQuestionTypePhoto;
            FormOptionModel option = new FormOptionModel();
            option.isPhotoUploadTypeOption= true;
            question.optionsArray.add(option);
            mutableArrayForForm16.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            //photo type questions
            FormQuestionModel question = new FormQuestionModel();
            question.questionTextInEnglish = "Upload Back side of NIC";
            question.questionTextInUrdu = "شناختی کارڈ کی بیک سائیڈ کی تصویر اپلوڈ کریں";
            question.questionType = kQuestionTypePhoto;
            FormOptionModel option = new FormOptionModel();
            option.isPhotoUploadTypeOption= true;
            question.optionsArray.add(option);
            mutableArrayForForm16.add(question);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////
    }



 public static ArrayList<String> getListOfConstituencies(){
     return  new ArrayList(Arrays.asList(ListOFConstituency.constituencyList));
    }


    public static ArrayList<String> getListOfDistricts(){
        return  new ArrayList(Arrays.asList(ListOFDistricts.districtsList));
    }

    public static ArrayList<String> getListOfPolticalParties(){
        return  new ArrayList(Arrays.asList(ListOFPoliticalarties.policalParties));
    }


}
