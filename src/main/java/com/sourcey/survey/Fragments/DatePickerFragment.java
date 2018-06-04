package com.sourcey.survey.Fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.sourcey.survey.FormThirteen.FormThirteen;
import com.sourcey.survey.Interface.TimerInterface;
import com.sourcey.survey.R;

public class DatePickerFragment extends Fragment {
    View v;
    DatePicker datePicker;
    TextView topLable,textview;
    Context context;
    String pageNumber;

    int position= -1;

    // You can modify the parameters to pass in whatever you want
    public static DatePickerFragment newInstance(int position,String pageNumber) {
        DatePickerFragment f = new DatePickerFragment();
        Bundle args = new Bundle();


        f.setArguments(args);
//        f.mediator = mediator;
        f.pageNumber =pageNumber;
//        f.questionText = questionText;
        f.position = position;
        return f;
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_form_datepicker, container, false);
        datePicker = v.findViewById(R.id.simpleDatePicker);
        topLable = v.findViewById(R.id.topLable);

        textview = v.findViewById(R.id.textview);
        topLable.setText(pageNumber);
        textview.setText(FormThirteen.formQuestionModels.get(position).questionTextInEnglish + "\n" +
        FormThirteen.formQuestionModels.get(position).questionTextInUrdu);

//        datePicker.setIs24HourView(false);
        topLable.setText(pageNumber);

        String AlreadyAnswered = FormThirteen.formQuestionModels.get(position).selectedOption.entryTypeOptionResponse;
        if(AlreadyAnswered.trim().length() >0){
            int day = FormThirteen.formQuestionModels.get(position).day;
            int month = FormThirteen.formQuestionModels.get(position).month;
            int year = FormThirteen.formQuestionModels.get(position).year;
            datePicker.updateDate(year, month, day);
        }

        datePicker.getCalendarView().setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Log.d("tag", "finally found the listener, the date is: year " + year + ", month "  + month + ", dayOfMonth " + dayOfMonth);
//                mediator.callFromFragment(true, dayOfMonth+"" + "" + month + ""+ year, true);
                String answer = dayOfMonth+"" + "" + month + ""+ year;
                FormThirteen.formQuestionModels.get(position).day = dayOfMonth;
                FormThirteen.formQuestionModels.get(position).month = month;
                FormThirteen.formQuestionModels.get(position).year = year;
                FormThirteen.formQuestionModels.get(position).selectedOption.entryTypeOptionResponse  = answer;
            }});

        return v;

    }


}
