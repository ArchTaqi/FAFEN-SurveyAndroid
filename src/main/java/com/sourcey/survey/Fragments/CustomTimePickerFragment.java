package com.sourcey.survey.Fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.sourcey.survey.FormThirteen.FormThirteen;
import com.sourcey.survey.Interface.CustomSelectionInterface;
import com.sourcey.survey.Interface.TimerInterface;
import com.sourcey.survey.R;

public class CustomTimePickerFragment extends Fragment {
    View v;
    TimePicker timePicker;
    TextView topLable,textview;
    Context context;
    String pageNumber;
    int position = -1;


    // You can modify the parameters to pass in whatever you want
    public static CustomTimePickerFragment newInstance(int position, String pageNumber) {
        CustomTimePickerFragment f = new CustomTimePickerFragment();
        Bundle args = new Bundle();


        f.setArguments(args);
//        f.mediator = mediator;
        f.pageNumber =pageNumber;
        f.position =position;
//        f.questionText = questionText;
        return f;
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_form_timepicker, container, false);
        timePicker = v.findViewById(R.id.simpleTimePicker);
        topLable = v.findViewById(R.id.topLable);
        textview = v.findViewById(R.id.textview);
        timePicker.setIs24HourView(false);
        topLable.setText(pageNumber);
        textview.setText(FormThirteen.formQuestionModels.get(position).questionTextInEnglish + "\n" + FormThirteen.formQuestionModels.get(position).questionTextInUrdu);

        String AlreadyAnswered = FormThirteen.formQuestionModels.get(position).selectedOption.entryTypeOptionResponse;
        if(AlreadyAnswered.trim().length() >0){
             String[] separated = AlreadyAnswered.split(":");
             timePicker.setCurrentHour(Integer.valueOf(separated[0].trim()));
             timePicker.setCurrentMinute(Integer.valueOf(separated[1].trim()));
        }
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {

                int  hour = 0;
                int minute = 0;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    hour = timePicker.getHour();
                    minute = timePicker.getMinute();

                } else {
                    hour = timePicker.getCurrentHour();
                    minute = timePicker.getCurrentMinute();

                }

                if(hour >= 8 &&  minute >= 0){
                    if( hour <= 19 &&   minute <= 59){
                        String answer = String.valueOf(hour) + ":" + String.valueOf(minute);
                        FormThirteen.formQuestionModels.get(position).selectedOption.entryTypeOptionResponse = answer;

//                        mediator.callFromFragment(questionThreeAsnwered,ans3, false);
                    }else{
                        FormThirteen.formQuestionModels.get(position).selectedOption.entryTypeOptionResponse = "-1";
//                        mediator.onErrorValidation(false, true);
                    }

                }else{
//                    mediator.onErrorValidation(false, true);
                    FormThirteen.formQuestionModels.get(position).selectedOption.entryTypeOptionResponse = "-1";
                }

            }
        });


        return v;

    }


}
