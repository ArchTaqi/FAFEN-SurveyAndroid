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

import com.sourcey.survey.Interface.TimerInterface;
import com.sourcey.survey.R;

public class TimePickerFragment extends Fragment {
    View v;
    TimePicker timePicker;
    TextView topLable;
    Context context;
    TimerInterface mediator;
    String pageNumber;

    public TimerInterface getMediator() {
        return mediator;
    }

    public void setMediator(TimerInterface mediator, String pageNumber) {
        this.mediator = mediator;
        this.pageNumber =pageNumber;
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        // Make sure that we are currently visible
        if (this.isVisible()) {
            // If we are becoming invisible, then...
            if (!isVisibleToUser) {

            }
        }
    }


    // You can modify the parameters to pass in whatever you want
    public static TimePickerFragment newInstance(int num, int color) {
        TimePickerFragment f = new TimePickerFragment();
        Bundle args = new Bundle();
        f.setArguments(args);

        return f;
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_form_timepicker, container, false);
        timePicker = v.findViewById(R.id.simpleTimePicker);
        topLable = v.findViewById(R.id.topLable);
        timePicker.setIs24HourView(false);
        topLable.setText(pageNumber);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {

                int  hour = 0;
                int minute = 0;
                boolean questionThreeAsnwered;
                String ans3;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    hour = timePicker.getHour();
                    minute = timePicker.getMinute();

                } else {
                    hour = timePicker.getCurrentHour();
                    minute = timePicker.getCurrentMinute();

                }

                if(hour >= 8 &&  minute >= 0){
                    if( hour <= 19 &&   minute <= 59){
                        ans3 = String.valueOf(hour) + ":" + String.valueOf(minute);
                        questionThreeAsnwered = true;

                        mediator.callFromFragment(questionThreeAsnwered,ans3, false);
                    }else{
                        mediator.onErrorValidation(false, true);
                    }

                }else{
                    mediator.onErrorValidation(false, true);
                }

            }
        });


        return v;

    }


}
