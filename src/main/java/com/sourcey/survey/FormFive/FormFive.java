package com.sourcey.survey.FormFive;

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
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.sourcey.survey.FormOne.FormOne;
import com.sourcey.survey.FormTwo.FormTwo;
import com.sourcey.survey.Fragments.TimePickerFragment;
import com.sourcey.survey.Interface.TimerInterface;
import com.sourcey.survey.R;
import com.sourcey.survey.Utils.HelperClass;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;


public class FormFive extends AppCompatActivity implements TimerInterface
{

    private Location currentLocation;

    SharedPreferences sharedPreferences;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private static final String TAG = "FormOne";
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;

    private static final String FINE_LOCATION = android.Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = android.Manifest.permission.ACCESS_COARSE_LOCATION;
    private Boolean mLocationPermissionsGranted = false;




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
        setContentView(R.layout.activity_form_five);

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        final String currentDateandTime = df.format(Calendar.getInstance().getTime());
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mAdapter = new MyAdapter(getSupportFragmentManager());
        mPager = findViewById(R.id.viewpager);
        mPager.setAdapter(mAdapter);
        doneButton= findViewById(R.id.doneButton);
        nextButton = findViewById(R.id.nextButton);
        backButton= findViewById(R.id.backButton);



        sharedPreferences  = getApplicationContext().getSharedPreferences("USER_ID",MODE_PRIVATE);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(FormFive.this);
        doneButton.setOnClickListener(new View.OnClickListener()
        {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
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
//                                Toast.makeText(FormFive.this, " Latitude: " + currentLocation.getLatitude() + " Longitude: " + currentLocation.getLongitude(), Toast.LENGTH_LONG).show();
                                DatabaseAsyncFormFive worker = new DatabaseAsyncFormFive(FormFive.this);


                                worker.execute((String.valueOf(sharedPreferences.getInt("ID",0))),
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
                                query += "INSERT INTO form5survey (email,ans1 ,ans2, ans3, ans4, ans5,date, lati, longi) VALUES ("+sb.toString()+")&";

                                editor.putBoolean("checkSync",true);
                                editor.putString("query", query);

                                editor.apply();
                            }


                        }else{
                            Log.d(TAG, "getLocations: unable to complete location task");
                            Toast.makeText(FormFive.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }

                        currentLocation = (Location) task.getResult();
                        sharedPreferences.edit().putString("FormFive",sharedPreferences.getInt("ID",0)+ans1+ans2+ans3+ans4+ans5+currentDateandTime+currentLocation.getLongitude()+""+currentLocation.getLongitude()+"").apply();

                    }
                });
                Toast.makeText(FormFive.this, "Done", Toast.LENGTH_LONG).show();
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
                if(!questionOneAsnwered&&currentPage ==0)
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
                        HelperClass.showDialog(FormFive.this, "Please select the time between 8am to 8pm");
                    }else {
                        HelperClass.showDialog(FormFive.this, "Empty Field");
                    }
                    return;
                }

                else if(!questionFourAsnwered && currentPage==3)
                {
                    if(exceedsTimeLimit){
                        HelperClass.showDialog(FormFive.this, "Please select the time between 8am to 8pm");
                    }else {
                        HelperClass.showDialog(FormFive.this, "Empty Field");
                    }
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

    @Override
    public void callFromFragment(boolean questionThreeAsnwereds, String ans3s, boolean exceedsTimeLimits) {
        if(currentPage==2){
            ans3 = ans3s;
            questionThreeAsnwered = questionThreeAsnwereds;
            exceedsTimeLimit = exceedsTimeLimits;
        }else  if(currentPage==3){
            ans4 = ans3s;
            questionFourAsnwered = questionThreeAsnwereds;
            exceedsTimeLimit = exceedsTimeLimits;
        }

    }

    @Override
    public void onErrorValidation(boolean questionThreeAsnwereds, boolean exceedsTimeLimits) {
        if(currentPage==2){
            questionThreeAsnwered = questionThreeAsnwereds;
            exceedsTimeLimit = exceedsTimeLimits;
        }else  if(currentPage==3){
            questionFourAsnwered = questionThreeAsnwereds;
            exceedsTimeLimit = exceedsTimeLimits;
        }
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
            String pagerNumber = "";
            pagerNumber = position+1  + " of " + NUMBER_OF_PAGES;
            switch (position) {
                case 0:
                    return FragmentOne.newInstance(0, Color.WHITE);
                case 1:
                    return FragmentTwo.newInstance(1, Color.CYAN);
                case 2:
                    TimePickerFragment fragment=new TimePickerFragment();
                    fragment.newInstance(2, Color.CYAN );
                    fragment.setMediator(FormFive.this,pagerNumber);
                    return fragment;
                case 3:
                    TimePickerFragment fragment3=new TimePickerFragment();
                    fragment3.newInstance(3, Color.CYAN );
                    fragment3.setMediator(FormFive.this,pagerNumber);
                    return fragment3;
                case 4:
                    return FragmentFive.newInstance(4, Color.CYAN);
                default:
                    return null;
            }
        }
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
            v = inflater.inflate(R.layout.fragment_form_five_q1, container, false);
            editText = v.findViewById(R.id.ans1EditTextFormFive);
            editText.setFilters(new InputFilter[]{new FormTwo.InputFilterMinMax("1", "500")});
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
                        FormFive.questionOneAsnwered = true;
                    else
                        FormFive.questionOneAsnwered = false;
                }

                @Override
                public void afterTextChanged(Editable s)
                {

                }
            });

            return v;

        }
    }
    public static class FragmentTwo extends Fragment implements View.OnClickListener {

        //RadioGroup radioGroup;
        View v;
        Button mButton1, mButton2, mButton3;

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
            v = inflater.inflate(R.layout.fragment_form_five_q2, container, false);
            mButton1 = v.findViewById(R.id.maleButtonFormFive);
            mButton2 = v.findViewById(R.id.femaleButtonFormFive);
            mButton3 = v.findViewById(R.id.combinedButtonFormFive);

            mButton1.setOnClickListener(this);
            mButton2.setOnClickListener(this);
            mButton3.setOnClickListener(this);


            if(questionTwoAsnwered)
            {
                if(Objects.equals(ans2, mButton1.getText().toString()))
                {
                    mButton1.setSelected(true);
                }
                else if(Objects.equals(ans2, mButton2.getText().toString()))
                {
                    mButton2.setSelected(true);
                }
                else if(Objects.equals(ans2, mButton3.getText().toString()))
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
            FormFive.questionTwoAsnwered=true;
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

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_form_five_q3, container, false);
            timePicker= v.findViewById(R.id.q3TimePickerFormFive);
            timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                @Override
                public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    {
                        ans3 = (timePicker.getHour() + ":" + timePicker.getMinute() + "");
                        questionThreeAsnwered=true;
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
    public static class FragmentFour extends Fragment implements View.OnClickListener {


        View v;
        TimePicker timePicker;
        public void setUserVisibleHint(boolean isVisibleToUser) {
            super.setUserVisibleHint(isVisibleToUser);

            // Make sure that we are currently visible
            if (this.isVisible())
            {
                // If we are becoming invisible, then...
                if (!isVisibleToUser) {


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

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
             v = inflater.inflate(R.layout.fragment_form_five_q4, container, false);
            timePicker= v.findViewById(R.id.q4TimePickerFormFive);
            timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                @Override
                public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        ans4 = (timePicker.getHour() + ":" + timePicker.getMinute() + "");
                        questionFourAsnwered = true;
                    } else {
                        ans4 = (timePicker.getCurrentHour().toString() + ":" + timePicker.getCurrentMinute().toString());
                        questionFourAsnwered=true;
                    }
                }
            });
            return v;

        }


        @Override
        public void onClick(View v)
        {

        }
    }
   */

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

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
            v = inflater.inflate(R.layout.fragment_form_five_q5, container, false);
            editText = v.findViewById(R.id.q5EditTextFormFive);
            if (!(Objects.equals(ans5, "")))
            {
                doneButton.setVisibility(v.VISIBLE);

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
                    if (TextUtils.isEmpty(editText.getText().toString()))
                    {
                        doneButton.setVisibility(View.INVISIBLE);
                        questionFiveAsnwered = false;
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

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(FormFive.this);

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
//                            Toast.makeText(FormFive.this, "unable to get current location", Toast.LENGTH_SHORT).show();
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
