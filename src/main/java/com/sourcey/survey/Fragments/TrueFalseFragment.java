package com.sourcey.survey.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sourcey.survey.FormSeven.FormSeven;
import com.sourcey.survey.Interface.TrueFalseInterface;
import com.sourcey.survey.R;

import java.util.Objects;

public class TrueFalseFragment extends Fragment implements View.OnClickListener
{

    //RadioGroup radioGroup;
    View v;
    Button mButton1, mButton2;
    TextView textview,topLable;
    TrueFalseInterface mediator;
    boolean questionAsnwered;
    String ans,questionText,page;

    public TrueFalseInterface getMediator() {
        return mediator;
    }

    public void setMediator() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint(isVisibleToUser);

        // Make sure that we are currently visible
        if (this.isVisible())
        {
            // If we are becoming invisible, then...
            if (!isVisibleToUser)
            {
                // FormOne.answers.add(((RadioButton) v.findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString());
            }
        }
    }

    // You can modify the parameters to pass in whatever you want
    public static Fragment newInstance(int num, int color,TrueFalseInterface mediator,
                                                boolean questionFourAsnweredx, String ans4x, String titleMessagex, String PageNumber)
    {
        TrueFalseFragment f = new TrueFalseFragment();
        f.mediator = mediator;
        f.questionAsnwered = questionFourAsnweredx;
        f.ans = ans4x;
        f.questionText = titleMessagex;
        f.page = PageNumber;
        Bundle args = new Bundle();
        f.setArguments(args);
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
        v = inflater.inflate(R.layout.fragment_form_truefalse, container, false);
        mButton1 = v.findViewById(R.id.yesButtonFormSeven);
        mButton2 = v.findViewById(R.id.noButtonFormSeven);
        textview = v.findViewById(R.id.textview);
        topLable = v.findViewById(R.id.topLable);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);

        textview.setText(questionText);
        topLable.setText(page);
        if(questionAsnwered)
        {
            if(Objects.equals(ans , mButton1.getText().toString()))
            {
                mButton1.setSelected(true);
            }
            else if(Objects.equals(ans , mButton2.getText().toString()))
            {
                mButton2.setSelected(true);
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
    public void onClick(View v)
    {

        Button button = (Button) v;
        if (v.getId() == mButton1.getId())
        {
            questionAsnwered = true;

        }
        if (v.getId() == mButton2.getId())
        {
            questionAsnwered = true;
        }


        // clear state
        mButton1.setSelected(false);
        mButton1.setPressed(false);
        mButton2.setSelected(false);
        mButton2.setPressed(false);

        // change state
        button.setSelected(true);
        button.setPressed(false);
        ans = button.getText().toString();
        mediator.callFromFragment(questionAsnwered,ans);
    }
}
