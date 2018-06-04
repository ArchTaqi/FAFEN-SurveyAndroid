package com.sourcey.survey.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sourcey.survey.FormThirteen.FormThirteen;
import com.sourcey.survey.Interface.CustomSelectionInterface;
import com.sourcey.survey.Interface.TrueFalseInterface;
import com.sourcey.survey.Models.FormOptionModel;
import com.sourcey.survey.Models.FormQuestionModel;
import com.sourcey.survey.R;

import java.util.ArrayList;
import java.util.Objects;

public class CustomButtonFragment extends Fragment implements View.OnClickListener
{

    //RadioGroup radioGroup;
    View v;
    TextView textview,topLable;
    //    CustomSelectionInterface mediator;
//    boolean questionAsnwered;
    String  PageNumber; //ans,titleMessage,
    //    FormQuestionModel oneQuestModel;
    ArrayList<FormOptionModel> listOption;
    //    ArrayList<FormQuestionModel> datasource;
//    int questionPos;
    int position = -1;
    Button myBtn = null;

    ArrayList<Button> btnArray = new ArrayList<>();
    // You can modify the parameters to pass in whatever you want
    public static Fragment newInstance(int position, String pageNumber)
    {
        CustomButtonFragment f = new CustomButtonFragment();
        Bundle args = new Bundle();

//        f.mediator = mediator;
//        f.questionAsnwered = questionAsnweredx;
//        f.ans = ansx;
//        f.titleMessage = titleMessagex;
//        f.oneQuestModel  = listQuests;
        f.PageNumber  = pageNumber;
//        f.datasource = datasource;
//        f.questionPos= questionpos;
        f.listOption = FormThirteen.formQuestionModels.get(position).optionsArray;
        f.position = position;
//        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.custom_add_button, container, false);

        textview = v.findViewById(R.id.textview);
        topLable = v.findViewById(R.id.topLable);
//        textview.setMovementMethod(new ScrollingMovementMethod());

        for (int i = 0; i < listOption.size(); i++) {
            myBtn = (Button)inflater.inflate(R.layout.my_button_layout, null);
            myBtn.setText(listOption.get(i).optionTextInEnglish + "\n" +listOption.get(i).optionTextInUrdu );
            myBtn.setTag(i);
            myBtn.setOnClickListener(this);
            LinearLayout.LayoutParams rl = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            rl.setMargins(20,25,20,0);
            myBtn.setLayoutParams(rl);
            btnArray.add(myBtn);
            ((LinearLayout) v.findViewById(R.id.linearButton)).addView(myBtn);

            String alreadyAnswered = FormThirteen.formQuestionModels.get(position).selectedOption.entryTypeOptionResponse;
            if(alreadyAnswered.trim().length()>0) {
                if(myBtn != null) {
                    if (Objects.equals(alreadyAnswered, myBtn.getText().toString())) {
                        myBtn.setSelected(true);
                    }else {
                        myBtn.setSelected(false);
                    }
                }
            }
        }

        textview.setText(FormThirteen.formQuestionModels.get(position).questionTextInEnglish + "\n" + FormThirteen.formQuestionModels.get(position).questionTextInUrdu);
        topLable.setText(PageNumber);





        return v;

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v)
    {

        Button button = (Button) v;
        for (int i = 0; i < btnArray.size(); i++) {
            Button oneBtn = btnArray.get(i);
            oneBtn .setSelected(false);
            oneBtn.setPressed(false);


        }

        if (v.getId() == button.getId())
        {
            String answerResponse = "";
            // change state
            button.setSelected(true);
            button.setPressed(true);
            answerResponse = button.getText().toString();


            FormOptionModel retriveFromButton = listOption.get((Integer) button.getTag());
            if(retriveFromButton.mutableArrayForDependantQuestions.size() >0) {
                updateModelAfterSuccess(retriveFromButton.mutableArrayForDependantQuestions,answerResponse);
                FormThirteen.nextButton.performClick();
            }else {
//                String answerRespinse = FormThirteen.formQuestionModels.get(position).selectedOption.entryTypeOptionResponse;
                if(answerResponse.trim().length() >0 ) {
                    FormThirteen.formQuestionModels.get(position).selectedOption.entryTypeOptionResponse = answerResponse;
                    FormThirteen.nextButton.performClick();
                }
            }
        }





    }

    private void updateModelAfterSuccess(ArrayList<FormQuestionModel> mutableArrayForDependantQuestions, String answerResponse){

        if(mutableArrayForDependantQuestions != null) {
            FormThirteen.formQuestionModels.get(position).selectedOption.entryTypeOptionResponse = answerResponse;

            int tempPage = position;
            FormThirteen.formQuestionModels.addAll(tempPage + 1, mutableArrayForDependantQuestions);

            FormThirteen.mAdapter.notifyDataSetChanged();
            synchronized (FormThirteen.mPager) {
                FormThirteen.mPager.notify();
            }
            FormThirteen.nextButton.setEnabled(true);
            FormThirteen.nextButton.setVisibility(View.VISIBLE);
            FormThirteen.doneButton.setVisibility(View.INVISIBLE);


        }
    }
/*

    private void removeDependentQuestionInMainlist(ArrayList<FormQuestionModel> mutableArrayForDependantQuestions){
        removeModelAfterSuccess( mutableArrayForDependantQuestions);
    }
*/



}
