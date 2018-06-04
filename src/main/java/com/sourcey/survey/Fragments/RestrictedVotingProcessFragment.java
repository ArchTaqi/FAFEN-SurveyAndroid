package com.sourcey.survey.Fragments;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.sourcey.survey.Interface.RestrictedVotingInterface;
import com.sourcey.survey.Interface.TimerInterface;
import com.sourcey.survey.R;
import com.sourcey.survey.Utils.HelperClass;

import java.util.Objects;

public class RestrictedVotingProcessFragment  extends Fragment implements View.OnClickListener {

    //RadioGroup radioGroup;
    View v;
    //EditText editText;
    Button mButton1, mButton2, mButton3, mButton4, mButton5, mButton6, mButton7, mButton8;

    boolean questionFourAsnwered;
    String ans4;

    RestrictedVotingInterface mediator;

    public RestrictedVotingInterface getMediator() {
        return mediator;
    }

    public void setMediator(RestrictedVotingInterface mediator, boolean questionFourAsnwered, String ans4) {
        this.mediator = mediator;
        this.questionFourAsnwered = questionFourAsnwered;
        this.ans4 = ans4;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        // Make sure that we are currently visible
        if (this.isVisible()) {
            // If we are becoming invisible, then...
            if (!isVisibleToUser) {

                //FormOne.answers.add(((RadioButton) v.findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString()+" "+editText.getText());
            }
        }
    }

    // You can modify the parameters to pass in whatever you want
    public static RestrictedVotingProcessFragment newInstance(int num, int color) {
        RestrictedVotingProcessFragment f = new RestrictedVotingProcessFragment();
        Bundle args = new Bundle();
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_form_two_q4, container, false);

        mButton1 = v.findViewById(R.id.policeButtonFormOne);
        mButton2 = v.findViewById(R.id.OtherSecurityPersonnelButtonFormOne);
        mButton3 = v.findViewById(R.id.presidingOfficerButtonFormOne);
        mButton4 = v.findViewById(R.id.OtherPersonsButtonFormOne);
        mButton5 = v.findViewById(R.id.PollingOfficersButtonFormOne);
        mButton6 = v.findViewById(R.id.AssistantPresidingofficerButtonFormOne);
        mButton7 = v.findViewById(R.id.anyOtherButtonFormTwo);

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        mButton5.setOnClickListener(this);
        mButton6.setOnClickListener(this);
        mButton7.setOnClickListener(this);


        if (questionFourAsnwered) {
            if (Objects.equals(ans4, mButton1.getText().toString())) {
                mButton1.setSelected(true);
            } else if ((Objects.equals(ans4, mButton2.getText().toString()))) {
                mButton2.setSelected(true);
            } else if ((Objects.equals(ans4, mButton3.getText().toString()))) {
                mButton3.setSelected(true);
            } else if ((Objects.equals(ans4, mButton4.getText().toString()))) {
                mButton4.setSelected(true);
            } else if ((Objects.equals(ans4, mButton5.getText().toString()))) {
                mButton5.setSelected(true);
            } else if ((Objects.equals(ans4, mButton6.getText().toString()))) {
                mButton6.setSelected(true);
            } else {
                mButton7.setSelected(true);
            }
        }


        return v;

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        final Button button = (Button) v;

        // clear state
        mButton1.setSelected(false);
        mButton1.setPressed(false);
        mButton2.setSelected(false);
        mButton2.setPressed(false);
        mButton3.setSelected(false);
        mButton3.setPressed(false);
        mButton4.setSelected(false);
        mButton4.setPressed(false);
        mButton5.setSelected(false);
        mButton5.setPressed(false);
        mButton6.setSelected(false);
        mButton6.setPressed(false);
        mButton7.setSelected(false);
        mButton7.setPressed(false);

        // change state
        button.setSelected(true);
        button.setPressed(false);
        if (v.getId() == mButton7.getId()) {
            final EditText input = new EditText(getActivity());
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            if (questionFourAsnwered) {
                input.setText(ans4);
            }

            new AlertDialog.Builder(getActivity())
                    .setTitle("please elaborate \n دیگر افراد کی تفصیل")
                    .setView(input)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // get user input and set it to result
                                    // edit text

                                    if(input.getText().toString().trim().length() < 1){
                                        HelperClass.showDialog(getActivity(), "Empty Field");
                                        button.setSelected(false);
                                        return;
                                    }

                                    ans4 = (input.getText().toString());
                                    questionFourAsnwered = true;

                                    mediator.callFromRestrictedFragment(questionFourAsnwered,ans4);

                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    button.setSelected(false);
                                }
                            })
                    .show();
        } else {
            ans4 = button.getText().toString();
            questionFourAsnwered = true;
            mediator.callFromRestrictedFragment(questionFourAsnwered,ans4);
        }
    }

}
