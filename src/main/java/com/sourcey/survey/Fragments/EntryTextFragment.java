package com.sourcey.survey.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.sourcey.survey.FormSeven.FormSeven;
import com.sourcey.survey.FormThirteen.FormThirteen;
import com.sourcey.survey.FormTwo.FormTwo;
import com.sourcey.survey.Interface.EntryTextInterface;
import com.sourcey.survey.Models.ENUMSQuestionType;
import com.sourcey.survey.Models.FormOptionModel;
import com.sourcey.survey.Models.FormQuestionModel;
import com.sourcey.survey.R;
import com.sourcey.survey.Utils.HelperClass;
import com.sourcey.survey.Utils.InputFilterEdittextLimit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntryTextFragment  extends Fragment
{
    View v;
    EditText editText;
    TextView textview,topLable;
//    EntryTextInterface mediator;
//    FormQuestionModel oneQuestModel;

//    boolean questionAsnwered;
//    String ans ,questionText,
    String numberOFPages;
    int type;
    int position = -1;



/*
    public EntryTextInterface getMediator() {
        return mediator;
    }

    public void setMediator() {

    }*/


    // You can modify the parameters to pass in whatever you want
    public static Fragment newInstance(int position,int editTextType, String Pages)
    {
        EntryTextFragment f = new EntryTextFragment();
//        f.mediator = mediator;
//        f.questionAsnwered = questionFourAsnweredx;
//        f.ans = ans4x;
//        f.questionText = titleMessagex;
        f.position = position;
        f.type = editTextType;
        f.numberOFPages = Pages;
//        f.oneQuestModel = oneQuestModel;
//        Bundle args = new Bundle();
//        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.fragment_form_entry, container, false);
        editText = v.findViewById(R.id.ans1EditTextFormTwelve);
        textview = v.findViewById(R.id.textview);
        topLable = v.findViewById(R.id.topLable);
        textview.setText(FormThirteen.formQuestionModels.get(position).questionTextInEnglish + "\n" + FormThirteen.formQuestionModels.get(position).questionTextInUrdu);
        topLable.setText(numberOFPages);
        try {

            String alreadyAnswer = FormThirteen.formQuestionModels.get(position).selectedOption.entryTypeOptionResponse;
            if(alreadyAnswer.trim().length()>0) {
                editText.setText(alreadyAnswer);
//                mediator.entrySuccesful(questionAsnwered,ans  );
            }

            if(type == 1 || FormThirteen.formQuestionModels.get(position).questionType == ENUMSQuestionType.kQuestionTypeNumeric){ // number
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            }else if(type == 2){ // string
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
            }else if(type == 3){ // CNIC
                editText.setInputType(InputType.TYPE_CLASS_PHONE);
            }else if(type == 4){ // PhoneNumber
                editText.setInputType(InputType.TYPE_CLASS_PHONE);
            }else if(type == 5){ // EMAIl
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            }


            if(FormThirteen.formQuestionModels.get(position).questionType == ENUMSQuestionType.kQuestionTypeSingleLargeEntry){
                editText.setSingleLine(false);
                editText.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                editText.setLines(7);
                editText.setMaxLines(10);
                editText.setVerticalScrollBarEnabled(true);
                editText.setMovementMethod(ScrollingMovementMethod.getInstance());
                editText.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);

            }

            if(FormThirteen.formQuestionModels.get(position).questionType == ENUMSQuestionType.kQuestionTypeNumeric) {

                int editTextLimit = FormThirteen.formQuestionModels.get(position).limitIfIntegertype;
                if (editTextLimit != -1) {
                    editText.setFilters(new InputFilter[]{new InputFilterEdittextLimit("1", "" + editTextLimit)});
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        editText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String responseAnswer = (editText.getText().toString());

                if(type == 4) { // phone number
                    editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(12)});
                    if (responseAnswer.trim().length()  == 4){
                        editable.insert(4, "-");
                    }
                }else  if(type == 3) { // CNIC
                    editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});
                    if (responseAnswer.trim().length()  == 5){
                        editable.insert(5, "-");
                    } if (responseAnswer.trim().length()  == 13){
                        editable.insert(13, "-");
                    }

                }
                FormThirteen.formQuestionModels.get(position).selectedOption.entryTypeOptionResponse = responseAnswer;

            }


        });

        return v;

    }


}
