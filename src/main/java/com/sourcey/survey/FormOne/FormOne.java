package com.sourcey.survey.FormOne;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.sourcey.survey.Fragments.TimePickerFragment;
import com.sourcey.survey.Interface.TimerInterface;
import com.sourcey.survey.R;
import com.sourcey.survey.Utils.HelperClass;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;


public class FormOne extends AppCompatActivity  implements TimerInterface
{
//    private OnGetFromUserClickListener mListener;

    private Location currentLocation;

    private FusedLocationProviderClient mFusedLocationProviderClient;
    private static final String TAG = "FormOne";
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;

    private static final String FINE_LOCATION = android.Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = android.Manifest.permission.ACCESS_COARSE_LOCATION;
    private Boolean mLocationPermissionsGranted = false;
    public TimePicker timePicker;
    public static int TIME_PICKER_INTERVAL = 0;
    public static NumberPicker minutePicker;
    public static List<String> displayedValues;

    SharedPreferences sharedPreferences;

    static final int NUMBER_OF_PAGES = 5;
    public static String ans1="",ans2="",ans3="",ans4="",ans5="";
    public static boolean questionOneAsnwered = false;
    public static boolean questionTwoAsnwered = false;
    public static boolean questionThreeAsnwered = false;
    public static boolean questionFourAsnwered = false;
    public static boolean questionFiveAsnwered = false;

    MyAdapter mAdapter;
    ViewPager mPager;
    int currentPage = 0;

    static Button nextButton;
    Button backButton;
    static Button doneButton;


    private static  boolean exceedsTimeLimit = false;




//    EditText ans1EditText, ans5EditText,anyOtherEditText;
//
//    RadioGroup ans2RadioGroup, ans4RadioGroup;
//
//    TimePicker ans3TimePicker;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_one);

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        final String currentDateandTime = df.format(Calendar.getInstance().getTime());

        sharedPreferences  = getApplicationContext().getSharedPreferences("USER_ID",MODE_PRIVATE);


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mAdapter = new MyAdapter(getSupportFragmentManager());
        mPager = findViewById(R.id.viewpager);
        mPager.setAdapter(mAdapter);
        doneButton= findViewById(R.id.doneButton);
        nextButton = findViewById(R.id.nextButton);
        backButton= findViewById(R.id.backButton);


        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(FormOne.this);
        doneButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {










                @SuppressLint("MissingPermission") final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        Log.d(TAG, "getLocations: in onComplete function");
                        if(task.isSuccessful())
                        {
                            currentLocation = (Location) task.getResult();
                            Log.d(TAG, "getLocations: data of location got in currentLocation variable ");



                            if(validateInternet())
                            {
//                                Toast.makeText(FormOne.this, " Latitude: " + currentLocation.getLatitude() + " Longitude: " + currentLocation.getLongitude(), Toast.LENGTH_LONG).show();
                                DatabaseAsync worker = new DatabaseAsync(FormOne.this);


                                String id = null;
                                try {
                                    id = String.valueOf(sharedPreferences.getInt("ID",0));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                worker.execute((id),
                                        ans1,
                                        ans2,
                                        ans3,
                                        ans4,
                                        ans5,
                                        currentDateandTime,
                                        currentLocation.getLatitude()+"",
                                        currentLocation.getLongitude()+""
                                );

                            }
                            else
                            {
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                String query =sharedPreferences.getString("query","");


                                StringBuilder sb = new StringBuilder();
                                sb.append("\'"+String.valueOf(sharedPreferences.getInt("ID",0)+"\'"));
                                sb.append(",");
                                sb.append("\'"+ans1+"\'");
                                sb.append(",");
                                sb.append("\'"+ans2+"\'");
                                sb.append(",");
                                sb.append("\'"+ans3+"\'");
                                sb.append(",");
                                sb.append("\'"+ans4+"\'");
                                sb.append(",");
                                sb.append("\'"+ans5+"\'");
                                sb.append(",");
                                sb.append("\'"+currentDateandTime+"\'");
                                sb.append(",");
                                sb.append("\'"+currentLocation.getLatitude()+"\'");
                                sb.append(",");
                                sb.append("\'"+currentLocation.getLongitude()+"\'");



                                query += "INSERT INTO form1survey (email,ans1 ,ans2, ans3, ans4, ans5,date, lati, longi) VALUES ("+sb.toString()+")&";

                                editor.putBoolean("checkSync",true);
                                editor.putString("query", query);

                                editor.apply();
                            }



                        }else{
                            Log.d(TAG, "getLocations: unable to complete location task");
//                            Toast.makeText(FormOne.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                        currentLocation = (Location) task.getResult();
                        sharedPreferences.edit().putString("FormOne",sharedPreferences.getInt("ID",0)+ans1+ans2+ans3+ans4+ans5+currentDateandTime+currentLocation.getLongitude()+""+currentLocation.getLongitude()+"").apply();
                    }
                });

                Toast.makeText(FormOne.this, "Done", Toast.LENGTH_LONG).show();
                finish();

            }
        });

        nextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {





                backButton.setEnabled(true);
                doneButton.setVisibility(View.INVISIBLE);
                if(!questionOneAsnwered&&currentPage ==0) {
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        // Make sure that we are currently visible
                        builder = new AlertDialog.Builder(v.getContext(), android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(v.getContext());
                    }
                    builder.setTitle("Empty Field")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                    return;
                }
                else if(!questionTwoAsnwered && currentPage==1)
                {
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        // Make sure that we are currently visible
                        builder = new AlertDialog.Builder(v.getContext(), android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(v.getContext());
                    }
                    builder.setTitle("Empty Field")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                    return;
                }
                else if(!questionThreeAsnwered && currentPage==2)
                {
                    if(exceedsTimeLimit){
                        HelperClass.showDialog(FormOne.this, "Please select the time between 8am to 8pm");
                    }else {
                        HelperClass.showDialog(FormOne.this, "Empty Field");
                    }
                    return;
                }

                else if (!questionFourAsnwered && currentPage == 3)
                {
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        // Make sure that we are currently visible
                        builder = new AlertDialog.Builder(v.getContext(), android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(v.getContext());
                    }
                    builder.setTitle("Empty Field")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                    return;
                }

                else
                {
                    nextButton.setEnabled(true);
                }
                if(currentPage < NUMBER_OF_PAGES-1)
                {
                    currentPage++;
                }
                if(NUMBER_OF_PAGES-1 == currentPage)
                {
                    nextButton.setEnabled(false);
                    nextButton.setVisibility(View.INVISIBLE);

                }
                setCurrentItem (currentPage, true);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                doneButton.setVisibility(View.INVISIBLE);
                nextButton.setVisibility(View.VISIBLE);
                nextButton.setEnabled(true);
                if(currentPage > 0)
                {
                    currentPage--;


                }
                if(currentPage == 0)
                {
                    backButton.setEnabled(false);
                }
                setCurrentItem (currentPage, true);
            }
        });


    }




    public void setCurrentItem (int item, boolean smoothScroll) {
        mPager.setCurrentItem(item, smoothScroll);
    }


    public class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public int getCount()
        {
            return NUMBER_OF_PAGES;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return FragmentOne.newInstance(0, Color.WHITE);
                case 1:
                    return FragmentTwo.newInstance(1, Color.CYAN);
                case 2:
                    String pagerNumber = position+1  + " of " + NUMBER_OF_PAGES;
                    TimePickerFragment fragment=new TimePickerFragment();
                    fragment.newInstance(2, Color.CYAN );
                    fragment.setMediator(FormOne.this,pagerNumber);
                    return fragment;
                case 3:
                    return FragmentFour.newInstance(3, Color.CYAN);
                case 4:
                    return FragmentFive.newInstance(4, Color.CYAN);
                default:
                    return null;
            }
        }
    }


    @Override
    public void callFromFragment(boolean questionThreeAsnwered,String ans3,boolean exceedsTimeLimits) {
        FormOne.ans3 = ans3;
        FormOne.questionThreeAsnwered = questionThreeAsnwered;
        exceedsTimeLimit = exceedsTimeLimits;
    }

    @Override
    public void onErrorValidation(boolean questionThreeAsnwered, boolean exceedsTimeLimits) {
        FormOne.questionThreeAsnwered = questionThreeAsnwered;
        exceedsTimeLimit = exceedsTimeLimits;
    }

    private boolean validateInternet()
    {
        ConnectivityManager cm = (ConnectivityManager)(this).getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isAvailable() && activeNetwork.isConnected();
    }











    public static class FragmentOne extends Fragment {


        View v;
        EditText editText;
        public void setUserVisibleHint(boolean isVisibleToUser) {
            super.setUserVisibleHint(isVisibleToUser);

            // Make sure that we are currently visible
            if (this.isVisible())
            {
                // If we are becoming invisible, then...
                if (!isVisibleToUser)
                {
                    ans1 =(editText.getText().toString());
                }
            }
        }

        // You can modify the parameters to pass in whatever you want
        static FragmentOne newInstance(int num, int color) {
            FragmentOne f = new FragmentOne();
            return f;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            v = inflater.inflate(R.layout.fragment_form_one_q1, container, false);
            editText = v.findViewById(R.id.ans1EditText);
            editText.setFilters(new InputFilter[]{new InputFilterMinMax("1", "500")});
            if(editText.getText().toString().isEmpty())
                editText.addTextChangedListener(new TextWatcher()
                {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after)
                    {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count)
                    {
                        if(!editText.getText().toString().isEmpty())
                            FormOne.questionOneAsnwered = true;
                        else {
                            FormOne.questionOneAsnwered = false;


                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s)
                    {

                    }
                });



            return v;

        }
    }


    public static class InputFilterMinMax implements InputFilter {
        private int min, max;

        public InputFilterMinMax(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public InputFilterMinMax(String min, String max) {
            this.min = Integer.parseInt(min);
            this.max = Integer.parseInt(max);
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            try {
                int input = Integer.parseInt(dest.toString() + source.toString());
                if (isInRange(min, max, input))
                    return null;
            } catch (NumberFormatException nfe) { }
            return "";
        }

        private boolean isInRange(int a, int b, int c) {
            return b > a ? c >= a && c <= b : c >= b && c <= a;
        }
    }

    public static class FragmentTwo extends Fragment implements View.OnClickListener
    {

        //RadioGroup radioGroup;
        View v;
        Button mButton1, mButton2, mButton3;

        @Override
        public void setUserVisibleHint(boolean isVisibleToUser)
        {
            super.setUserVisibleHint(isVisibleToUser);

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
        static FragmentTwo newInstance(int num, int color) {
            FragmentTwo f = new FragmentTwo();
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
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            v = inflater.inflate(R.layout.fragment_form_one_q2, container, false);
            mButton1 = v.findViewById(R.id.maleButtonFormOne);
            mButton2 = v.findViewById(R.id.femaleButtonFormOne);
            mButton3 = v.findViewById(R.id.combinedButtonFormOne);

            mButton1.setOnClickListener(this);
            mButton2.setOnClickListener(this);
            mButton3.setOnClickListener(this);


            if(questionTwoAsnwered)
            {

                if(Objects.equals(ans2, mButton1.getText().toString()))
                {
                    mButton1.setSelected(true);
                }
                else if((Objects.equals(ans2, mButton2.getText().toString())))
                {
                    mButton2.setSelected(true);
                }
                else if((Objects.equals(ans2, mButton3.getText().toString())))
                {
                    mButton3.setSelected(true);
                }
            }



            //radioGroup = v.findViewById(R.id.formOneQ2RadioGroup);
            // radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
            // {
            //   @Override
            //   public void onCheckedChanged(RadioGroup group, int checkedId)
            //   {
            //         questionTwoAsnwered = true;
            //     }
            //  });
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
            FormOne.questionTwoAsnwered=true;
            Button button = (Button) v;

//        mButton1.setBackgroundResource(R.color.bg_selector);
//        mButton2.setBackgroundResource(R.color.bg_selector);
//        mButton3.setBackgroundResource(R.color.bg_selector);
//        button.setBackgroundResource(R.drawable.bg_selected);

            // clear state
            mButton1.setSelected(false);
            mButton1.setPressed(false);
            mButton2.setSelected(false);
            mButton2.setPressed(false);
            mButton3.setSelected(false);
            mButton3.setPressed(false);

            // change state
            button.setSelected(true);
            button.setPressed(false);
            ans2=button.getText().toString();
            nextButton.performClick();
        }
    }
  /*  public static class FragmentThree extends Fragment {
        View v;
        TimePicker timePicker;
        public void setUserVisibleHint(boolean isVisibleToUser) {
            super.setUserVisibleHint(isVisibleToUser);

            // Make sure that we are currently visible
            if (this.isVisible())
            {
                // If we are becoming invisible, then...
                if (!isVisibleToUser)
                {

                }
            }
        }


        // You can modify the parameters to pass in whatever you want
        static FragmentThree newInstance(int num, int color) {
            FragmentThree f = new FragmentThree();
            Bundle args = new Bundle();
            f.setArguments(args);
            return f;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_form_one_q3, container, false);
            timePicker= v.findViewById(R.id.simpleTimePicker);
            timePicker.setIs24HourView(false);

            timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                @Override
                public void onTimeChanged(TimePicker timePicker, int i, int i1) {


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        ans3 = (timePicker.getHour() + ":" + timePicker.getMinute() + "");
                        questionThreeAsnwered = true;
                    }
                    else {
                        ans3 = (timePicker.getCurrentHour().toString() + ":" + timePicker.getCurrentMinute().toString());
                        questionThreeAsnwered=true;
                    }
                }
            });





            return v;

        }
    }
*/

    public static class FragmentFour extends Fragment implements View.OnClickListener {

        //RadioGroup radioGroup;
        View v;
        //EditText editText;
        Button mButton1, mButton2, mButton3,mButton4,mButton5,mButton6,mButton7,mButton8;

        @Override
        public void setUserVisibleHint(boolean isVisibleToUser) {
            super.setUserVisibleHint(isVisibleToUser);

            // Make sure that we are currently visible
            if (this.isVisible()) {
                // If we are becoming invisible, then...
                if (!isVisibleToUser)
                {


                    //FormOne.answers.add(((RadioButton) v.findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString()+" "+editText.getText());
                }
            }
        }

        // You can modify the parameters to pass in whatever you want
        static FragmentFour newInstance(int num, int color)
        {
            FragmentFour f = new FragmentFour();
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
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
            v = inflater.inflate(R.layout.fragment_form_one_q4, container, false);

            mButton1 = v.findViewById(R.id.policeButtonFormOne);
            mButton2 = v.findViewById(R.id.armyButtonFormOne);
            mButton3 = v.findViewById(R.id.rangerButtonFormOne);
            mButton4 = v.findViewById(R.id.fcButtonFormOne);
            mButton5 = v.findViewById(R.id.levisButtonFormOne);
            mButton6 = v.findViewById(R.id.khasadarButtonFormOne);
            mButton7 = v.findViewById(R.id.presidingOfficerButtonFormOne);
            mButton8 = v.findViewById(R.id.anyOtherButtonFormOne);

            mButton1.setOnClickListener(this);
            mButton2.setOnClickListener(this);
            mButton3.setOnClickListener(this);
            mButton4.setOnClickListener(this);
            mButton5.setOnClickListener(this);
            mButton6.setOnClickListener(this);
            mButton7.setOnClickListener(this);
            mButton8.setOnClickListener(this);


            if(questionFourAsnwered)
            {
                if(Objects.equals(ans4, mButton1.getText().toString()))
                {

                    mButton1.setSelected(true);
                }
                else if((Objects.equals(ans4, mButton2.getText().toString())))
                {
                    mButton2.setSelected(true);
                }
                else if((Objects.equals(ans4, mButton3.getText().toString())))
                {
                    mButton3.setSelected(true);
                }
                else if((Objects.equals(ans4, mButton4.getText().toString())))
                {
                    mButton4.setSelected(true);
                }
                else if((Objects.equals(ans4, mButton5.getText().toString())))
                {
                    mButton5.setSelected(true);
                }
                else if((Objects.equals(ans4, mButton6.getText().toString())))
                {
                    mButton6.setSelected(true);
                }
                else if((Objects.equals(ans4, mButton7.getText().toString())))
                {
                    mButton7.setSelected(true);
                }
                else
                {
                    mButton8.setSelected(true);
                }
            }

            // radioGroup = v.findViewById(R.id.formOneQ4RadioGroup);
            //editText = v.findViewById(R.id.anyOtherEditText);
            //editText.setEnabled(false);
            //final RadioButton anyOther = (RadioButton) radioGroup.getChildAt(7);
//            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
//            {
//                @Override
//                public void onCheckedChanged(RadioGroup group, int checkedId)
//                {
//                    questionFourAsnwered = true;
//                    if(checkedId == anyOther.getId())
//                    {
//                        editText.setEnabled(true);
//
//                    }
//                    else
//                    {
//                        editText.setEnabled(false);
//                        editText.setText("");
//                    }
//                }
//            });


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

            FormOne.questionFourAsnwered=true;
            Button button = (Button) v;


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
            mButton8.setSelected(false);
            mButton8.setPressed(false);

            // change state
            button.setSelected(true);
            button.setPressed(false);
            if(v.getId() == mButton8.getId())
            {
                final EditText input = new EditText(getActivity());
                input.setInputType(InputType.TYPE_CLASS_TEXT );
                new AlertDialog.Builder(getActivity())
                        .setTitle("Please provide details \n برائے مہربانی تفصیل فراہم کریں")
                        .setView(input)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        nextButton.performClick();
                                        ans4 = (input.getText().toString());
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                })
                        .show();
            }
            else {
                ans4 = button.getText().toString();
                nextButton.performClick();
            }
        }
    }
    public static class FragmentFive extends Fragment {

        View v;
        EditText editText;

        static FragmentFive newInstance(int num, int color)
        {
            FragmentFive f = new FragmentFive();
            Bundle args = new Bundle();
            f.setArguments(args);
            return f;
        }

        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
        {
            v = inflater.inflate(R.layout.fragment_form_one_q5, container, false);
            editText  = v.findViewById(R.id.q5EditText);


            editText.addTextChangedListener(new TextWatcher()
            {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after)
                {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count)
                {

                    if (TextUtils.isEmpty(editText.getText().toString())) {
                        questionFiveAsnwered = false;
                        doneButton.setVisibility(View.INVISIBLE);

                    }
                    else {
                        questionFiveAsnwered = true;
                        doneButton.setVisibility(View.VISIBLE);
                        ans5 = editText.getText().toString();
                    }

                }

                @Override
                public void afterTextChanged(Editable s)
                {

                }
            });
            return v;

        }
    }
    private void getLocationPermission(){
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permissions = {android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            Log.d(TAG, "getLocationPermission: fine location permission granted");
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                Log.d(TAG, "getLocationPermission: coarse location permission granted boolean set to true");
                mLocationPermissionsGranted = true;
                // initMap();
            }
            else{
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
                Log.d(TAG, "getLocationPermission: requesting location permission");
            }
        }
        else{
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }
    private Location getLocations() {

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(FormOne.this);

        try{
            if(mLocationPermissionsGranted){

                Log.d(TAG, "getLocations: location permission was granted");
                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        Log.d(TAG, "getLocations: in onComplete function");
                        if(task.isSuccessful()){
                            currentLocation = (Location) task.getResult();
                            Log.d(TAG, "getLocations: data of location got in currentLocation variable ");
                            //Toast.makeText(UserHome.this, " Latitude: " + currentLocation.getLatitude() + " Longitude: " + currentLocation.getLongitude(), Toast.LENGTH_LONG).show();

                        }else{
                            Log.d(TAG, "getLocations: unable to complete location task");
//                            Toast.makeText(FormOne.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                return currentLocation;
            }
            else {
                Log.d(TAG, "getLocations: location permission was not granted granted");

            }
        }catch (SecurityException e){
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage() );
        }
        return null;
    }





}





