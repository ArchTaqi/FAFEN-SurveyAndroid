package com.sourcey.survey.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sourcey.survey.FormThirteen.FormThirteen;
import com.sourcey.survey.Interface.EntryTextInterface;
import com.sourcey.survey.Models.FormOptionModel;
import com.sourcey.survey.Models.FormQuestionModel;
import com.sourcey.survey.R;
import com.sourcey.survey.Utils.HelperClass;

import java.util.ArrayList;

public class SpinnerFragment extends Fragment {
    View v;
    TextView editText;
    TextView textview, topLable;//,textListner;
    //    EntryTextInterface mediator;
//    FormQuestionModel oneQuestModel;
//    Spinner spinner;
//    boolean questionAsnwered;
    String numberOFPages; //ans ,questionText,
    int type;

    //    ArrayList<FormQuestionModel> datasource;
    //    int questionpos;
    int position;
    ArrayList<String> spinnerValue = new ArrayList<>();
    String alreadyAnswered = "";
    // You can modify the parameters to pass in whatever you want
    public static Fragment newInstance(int position, int editTextType, String Pages) {
        SpinnerFragment f = new SpinnerFragment();
//        f.mediator = mediator;
//        f.questionAsnwered = questionFourAsnweredx;
//        f.ans = ans4x;
//        f.questionText = titleMessagex;
        f.type = editTextType;
        f.numberOFPages = Pages;
//        f.oneQuestModel = oneQuestModel;
//        f.datasource = datasource;
//        f.questionpos = questionpos;
        f.position = position;


        for (int i = 0; i < FormThirteen.formQuestionModels.get(position).optionsArray.size(); i++) {
            f.spinnerValue.add(FormThirteen.formQuestionModels.get(position).optionsArray.get(i).optionTextInEnglish);
        }

        Bundle args = new Bundle();
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_form_spinner, container, false);
        editText = v.findViewById(R.id.ans1EditTextFormTwelve);
        textview = v.findViewById(R.id.textview);
        topLable = v.findViewById(R.id.topLable);
//        textListner = v.findViewById(R.id.textListner);
        textview.setText(FormThirteen.formQuestionModels.get(position).questionTextInEnglish + "\n" + FormThirteen.formQuestionModels.get(position).questionTextInUrdu);
        topLable.setText(numberOFPages);
        alreadyAnswered = FormThirteen.formQuestionModels.get(position).selectedOption.entryTypeOptionResponse;
        if (alreadyAnswered.trim().length() > 0) {
            editText.setText(alreadyAnswered);
            FormThirteen.formQuestionModels.get(position).selectedOption.entryTypeOptionResponse = alreadyAnswered;

            if(alreadyAnswered.equalsIgnoreCase("Any Other")){
                FormThirteen.formQuestionModels.get(position).selectedOption.entryTypeOptionResponse = "reselect";

            }
        }

        // Spinner element
        editText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                editTextListner();
            }
        });
        return v;

    }



    private void editTextListner(){
        String[] mStringArray = new String[spinnerValue.size()];
        mStringArray = spinnerValue.toArray(mStringArray);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //set the title for alert dialog
        builder.setTitle("Choose an option: ");

        //set items to alert dialog. i.e. our array , which will be shown as list view in alert dialog
        final String[] finalMStringArray = mStringArray;
        builder.setItems(mStringArray, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {
                //setting the button text to the selected itenm from the list
                if(finalMStringArray[item].equalsIgnoreCase("Any Other")){
                    FormOptionModel retriveFromButton = FormThirteen.formQuestionModels.get(position).optionsArray.get(item);

                    if(retriveFromButton.mutableArrayForDependantQuestions.size() >0) {
                        updateModelAfterSuccess(retriveFromButton.mutableArrayForDependantQuestions, alreadyAnswered, true);

                        editText.setText(finalMStringArray[item]);
                        String answer = editText.getText().toString();
                        if (answer.trim().length() > 0) {
                            FormThirteen.formQuestionModels.get(position).selectedOption.entryTypeOptionResponse = answer;
                            FormThirteen.formQuestionModels.get(position).selectedOption.spinnerResponseIndex = item;
                        }

                        FormThirteen.nextButton.performClick();
                    }else {
                        ifAnyOther();
                    }
                }else {
                    editText.setText(finalMStringArray[item]);
                    String answer = editText.getText().toString();
                    if (answer.trim().length() > 0) {
                        FormThirteen.formQuestionModels.get(position).selectedOption.entryTypeOptionResponse = answer;
                        FormThirteen.formQuestionModels.get(position).selectedOption.spinnerResponseIndex = item;
                    }
                }
            }
        });

        //Creating CANCEL button in alert dialog, to dismiss the dialog box when nothing is selected
        builder.setCancelable(false)
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //When clicked on CANCEL button the dalog will be dismissed
                        dialog.dismiss();
                    }
                });

        //Creating alert dialog
        AlertDialog alert = builder.create();

        //Showingalert dialog
        alert.show();

    }


    private void ifAnyOther(){
        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        new android.support.v7.app.AlertDialog.Builder(getActivity())
                .setTitle("Please provide details \n برائے مہربانی تفصیل فراہم کریں")
                .setView(input)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                editText.setText(input.getText().toString());
                                String answer = editText.getText().toString();
                                if (answer.trim().length() > 0) {
                                    FormThirteen.formQuestionModels.get(position).selectedOption.entryTypeOptionResponse = answer;
                                }else {
                                    HelperClass.showDialog(getActivity(), "Please enter details");
                                    FormThirteen.formQuestionModels.get(position).selectedOption.entryTypeOptionResponse = "";
                                }



                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                .show();
    }



    private void updateModelAfterSuccess(ArrayList<FormQuestionModel> mutableArrayForDependantQuestions, String answerResponse, boolean isNotified){

        if(mutableArrayForDependantQuestions != null) {
            FormThirteen.formQuestionModels.get(position).selectedOption.entryTypeOptionResponse = answerResponse;

            int tempPage = position;
            FormThirteen.formQuestionModels.addAll(tempPage + 1, mutableArrayForDependantQuestions);

            if(isNotified) {
                FormThirteen.mAdapter.notifyDataSetChanged();
                synchronized (FormThirteen.mPager) {
                    FormThirteen.mPager.notify();
                }
            }
            FormThirteen.nextButton.setEnabled(true);
            FormThirteen.nextButton.setVisibility(View.VISIBLE);
            FormThirteen.doneButton.setVisibility(View.INVISIBLE);


        }
    }
}
