package com.sourcey.survey.FormFour;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
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
import com.sourcey.survey.R;
import com.sourcey.survey.Utils.HelperClass;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;


public class FormFour extends AppCompatActivity
{

    SharedPreferences sharedPreferences;
    private Location currentLocation;

    private FusedLocationProviderClient mFusedLocationProviderClient;
    private static final String TAG = "FormOne";
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;

    private static final String FINE_LOCATION = android.Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = android.Manifest.permission.ACCESS_COARSE_LOCATION;
    private Boolean mLocationPermissionsGranted = false;



    static final int NUMBER_OF_PAGES = 9;
    public static String ans1 = "", ans2 = "", ans3 = "", ans4 = "", ans5 = "", ans6 = "", ans7 = "", ans8 = "", ans9 = "";
    public static boolean questionOneAsnwered = false;
    public static boolean questionTwoAsnwered = false;
    public static boolean questionThreeAsnwered = false;
    public static boolean questionFourAsnwered = false;
    public static boolean questionFiveAnswerd = false;
    public static boolean questionSixAnswerd = false;
    public static boolean questionSevenAnswerd = false;
    public static boolean isQuestionEightAsnwered = false;
    public static boolean questionNineAnswerd = false;


    MyAdapter mAdapter;
    ViewPager mPager;
    int currentPage = 0;
    public static int dependent=0;
    public static Button nextButton;
    Button backButton;
    static Button doneButton;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_three);

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        final String currentDateandTime = df.format(Calendar.getInstance().getTime());
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        sharedPreferences  = getApplicationContext().getSharedPreferences("USER_ID",MODE_PRIVATE);
        mAdapter = new MyAdapter(getSupportFragmentManager());
        mPager = findViewById(R.id.viewpager);
        mPager.setAdapter(mAdapter);
        doneButton = findViewById(R.id.doneButton);
        nextButton = findViewById(R.id.nextButton);
        backButton = findViewById(R.id.backButton);


        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(FormFour.this);
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
                location.addOnCompleteListener(new OnCompleteListener()
                {
                    @Override
                    public void onComplete(@NonNull Task task)
                    {
                        Log.d(TAG, "getLocations: in onComplete function");
                        if (task.isSuccessful())
                        {
                            currentLocation = (Location) task.getResult();
                            Log.d(TAG, "getLocations: data of location got in currentLocation variable ");
                            if(validateInternet())
                            {
//                                Toast.makeText(FormFour.this, " Latitude: " + currentLocation.getLatitude() + " Longitude: " + currentLocation.getLongitude(), Toast.LENGTH_LONG).show();
                                DatabaseAsyncFormFour worker = new DatabaseAsyncFormFour(FormFour.this);


                                worker.execute((String.valueOf(sharedPreferences.getInt("ID",0))),
                                        ans1,
                                        ans2,
                                        ans3,
                                        ans4,
                                        ans5,
                                        ans6,
                                        ans7,
                                        ans8,
                                        ans9,
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
                                sb.append("\'"+ans6+"\'");
                                sb.append(",");
                                sb.append("\'"+ans7+"\'");
                                sb.append(",");
                                sb.append("\'"+ans8+"\'");
                                sb.append(",");
                                sb.append("\'"+ans9+"\'");
                                sb.append(",");
                                sb.append("\'"+currentDateandTime+"\'");
                                sb.append(",");
                                sb.append("\'"+currentLocation.getLatitude()+"\'");
                                sb.append(",");
                                sb.append("\'"+currentLocation.getLongitude()+"\'");
                                query += "INSERT INTO form4survey (email,ans1 ,ans2, ans3, ans4, ans5,ans6,ans7,ans8,ans9,date, lati, longi) VALUES ("+sb.toString()+")&";

                                editor.putBoolean("checkSync",true);
                                editor.putString("query", query);

                                editor.apply();
                            }


                        } else
                        {
                            Log.d(TAG, "getLocations: unable to complete location task");
//                            Toast.makeText(FormFour.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }

                        currentLocation = (Location) task.getResult();
                        sharedPreferences.edit().putString("FormFour",sharedPreferences.getInt("ID",0)+ans1+ans2+ans3+ans4+ans5+ans6+ans7+ans8+ans9+currentDateandTime+currentLocation.getLongitude()+""+currentLocation.getLongitude()+"").apply();

                    }
                });
                Toast.makeText(FormFour.this, "Done", Toast.LENGTH_LONG).show();
                finish();

            }
        });

        nextButton.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v)
            {

                backButton.setEnabled(true);
                doneButton.setVisibility(View.INVISIBLE);
                if (!questionOneAsnwered && currentPage == 0)
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
                else if (!questionTwoAsnwered && currentPage == 1)
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
                else if (!questionThreeAsnwered && currentPage == 2)
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
                else if (!questionFiveAnswerd && currentPage == 4)
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
                else if (!questionSixAnswerd && currentPage == 5)
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
                else if (!questionSevenAnswerd && currentPage == 6)
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
                else if (!isQuestionEightAsnwered && currentPage == 7)
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
                else if (!questionNineAnswerd && currentPage == 8)
                    return;


                else
                {
                    nextButton.setEnabled(true);
                }
                if (currentPage < NUMBER_OF_PAGES - 1)
                {
                    currentPage++;

                    if(Objects.equals(ans4, "Entire Polling Station \n پولنگ اسٹیشن پر")&&dependent==1)
                        currentPage=5;


                }
                if (NUMBER_OF_PAGES - 1 == currentPage)
                {
                    nextButton.setEnabled(false);
                    nextButton.setVisibility(View.INVISIBLE);
                    doneButton.setVisibility(View.VISIBLE);
                }
                setCurrentItem(currentPage, true);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v)
            {
                doneButton.setVisibility(View.INVISIBLE);
                nextButton.setEnabled(true);
                nextButton.setVisibility(View.VISIBLE);
                if (currentPage > 0)
                {
                    if(currentPage==5 && Objects.equals(ans4, "Entire Polling Station \n پولنگ اسٹیشن پر"))
                    {
                        questionFourAsnwered=false;
                        dependent=0;
                        currentPage=4;
                    }

                    currentPage--;
                }
                if (currentPage == 0)
                {
                    backButton.setEnabled(false);
                }
                setCurrentItem(currentPage, true);
            }
        });

    }

    public void setCurrentItem(int item, boolean smoothScroll)
    {
        mPager.setCurrentItem(item, smoothScroll);
    }

    public static class MyAdapter extends FragmentPagerAdapter
    {
        public MyAdapter(FragmentManager fm)
        {
            super(fm);
        }


        @Override
        public int getCount()
        {
            return NUMBER_OF_PAGES;
        }

        @Override
        public Fragment getItem(int position)
        {

            switch (position)
            {
                case 0:
                    return FragmentOne.newInstance(0, Color.WHITE);
                case 1:
                    return FragmentTwo.newInstance(1, Color.CYAN);
                case 2:
                    return FragmentThree.newInstance(2, Color.CYAN);
                case 3:
                    return FragmentFour.newInstance(3, Color.CYAN);
                case 4:
                    return FragmentFive.newInstance(4, Color.CYAN);
                case 5:
                    return FragmentSix.newInstance(5, Color.CYAN);
                case 6:
                    return FragmentSeven.newInstance(6, Color.CYAN);
                case 7:
                    return FragmentEight.newInstance(7, Color.CYAN);
                case 8:
                    return FragmentNine.newInstance(8, Color.CYAN);
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




    public static class FragmentOne extends Fragment
    {


        View v;
        EditText editText;

        public void setUserVisibleHint(boolean isVisibleToUser)
        {
            super.setUserVisibleHint(isVisibleToUser);

            // Make sure that we are currently visible
            if (this.isVisible())
            {
                // If we are becoming invisible, then...
                if (!isVisibleToUser)
                {
                    ans1 = (editText.getText().toString());
                }
            }
        }

        // You can modify the parameters to pass in whatever you want
        static FragmentOne newInstance(int num, int color)
        {
            FragmentOne f = new FragmentOne();
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
            v = inflater.inflate(R.layout.fragment_form_four_q1, container, false);
            editText = v.findViewById(R.id.ans1EditTextFormFour);
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
                    if (!editText.getText().toString().isEmpty())
                        FormFour.questionOneAsnwered = true;
                    else
                        FormFour.questionOneAsnwered = false;
                }

                @Override
                public void afterTextChanged(Editable s)
                {

                }
            });

            return v;

        }
    }

    public static class FragmentTwo extends Fragment implements View.OnClickListener
    {

        //RadioGroup radioGroup;
        View v;
        Button mButton1, mButton2,mButton3;

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
        static FragmentTwo newInstance(int num, int color)
        {
            FragmentTwo f = new FragmentTwo();
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
            v = inflater.inflate(R.layout.fragment_form_four_q2, container, false);
            mButton1 = v.findViewById(R.id.femaleButtonFormFour);
            mButton2 = v.findViewById(R.id.combinedButtonFormFour);
            mButton3 = v.findViewById(R.id.maleButtonFormFour);

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
            FormFour.questionTwoAsnwered = true;
            Button button = (Button) v;


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
            ans2 = button.getText().toString();
            nextButton.performClick();
        }
    }

    public static class FragmentThree extends Fragment implements View.OnClickListener
    {
        View v;
        //EditText editText;
        Button mButton1, mButton2,mButton3,mButton4;

        public void setUserVisibleHint(boolean isVisibleToUser)
        {
            super.setUserVisibleHint(isVisibleToUser);

            // Make sure that we are currently visible
            if (this.isVisible())
            {
                // If we are becoming invisible, then...
                if (!isVisibleToUser)
                {
                    //ans3 = editText.getText().toString();
                }
            }
        }


        // You can modify the parameters to pass in whatever you want
        static FragmentThree newInstance(int num, int color)
        {
            FragmentThree f = new FragmentThree();
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
            v = inflater.inflate(R.layout.fragment_form_four_q3, container, false);

            mButton1 = v.findViewById(R.id.pollingAgentButtonFormFour);
            mButton2 = v.findViewById(R.id.PartyWorkerFormFour);
            mButton3 = v.findViewById(R.id.ElectionOfficialsFormFour);
            mButton4 = v.findViewById(R.id.anyOtherButtonFormFour);

            mButton1.setOnClickListener(this);
            mButton2.setOnClickListener(this);
            mButton3.setOnClickListener(this);
            mButton4.setOnClickListener(this);


            if(questionThreeAsnwered)
            {
                if(Objects.equals(ans3, mButton1.getText().toString()))
                {
                    mButton1.setSelected(true);
                }
                else if(Objects.equals(ans3, mButton2.getText().toString()))
                {
                    mButton2.setSelected(true);
                }
                else if(Objects.equals(ans3, mButton3.getText().toString()))
                {
                    mButton3.setSelected(true);
                }
                else
                {
                    mButton4.setSelected(true);
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

            // change state
            button.setSelected(true);
            button.setPressed(false);
            if (v.getId() == mButton4.getId())
            {
                final EditText input = new EditText(getActivity());
                input.setInputType(InputType.TYPE_CLASS_TEXT);

                if (questionThreeAsnwered) {
                    input.setText(ans3);
                }
                new AlertDialog.Builder(getActivity())
                        .setTitle("Explain")
                        .setMessage("Any other, please provide details \n دیگر ذرائع کی تفصیل")
                        .setView(input)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener()
                                {
                                    public void onClick(DialogInterface dialog, int id)
                                    {
                                        // get user input and set it to result
                                        // edit text
                                        if(input.getText().toString().trim().length() < 1){
                                            HelperClass.showDialog(getActivity(), "Empty Field");
                                            button.setSelected(false);
                                            return;
                                        }


                                        ans3 = (input.getText().toString());
                                        questionThreeAsnwered = true;
                                        nextButton.performClick();

                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener()
                                {
                                    public void onClick(DialogInterface dialog, int id)
                                    {
                                        dialog.cancel();
                                        button.setSelected(false);
                                        questionThreeAsnwered = false;
                                    }
                                })
                        .show();
            } else {
                ans3 = button.getText().toString();
                questionThreeAsnwered = true;
                nextButton.performClick();
            }

        }
    }


    public static class FragmentFour extends Fragment implements View.OnClickListener
    {
        View v;
        //EditText editText;
        Button mButton1, mButton2;

        public void setUserVisibleHint(boolean isVisibleToUser)
        {
            super.setUserVisibleHint(isVisibleToUser);

            // Make sure that we are currently visible
            if (this.isVisible())
            {
                // If we are becoming invisible, then...
                if (!isVisibleToUser)
                {
                    //ans3 = editText.getText().toString();
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
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);

        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {
            v = inflater.inflate(R.layout.fragment_form_four_q4, container, false);

            mButton1 = v.findViewById(R.id.entireButtonFormFour);
            mButton2 = v.findViewById(R.id.boothButtonFormFour);


            mButton1.setOnClickListener(this);
            mButton2.setOnClickListener(this);

            if(questionFourAsnwered)
            {
                if(Objects.equals(ans4, mButton1.getText().toString()))
                {
                    mButton1.setSelected(true);
                }
                else if(Objects.equals(ans4, mButton2.getText().toString()))
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


            // clear state
            mButton1.setSelected(false);
            mButton1.setPressed(false);
            mButton2.setSelected(false);
            mButton2.setPressed(false);


            // change state
            button.setSelected(true);
            button.setPressed(false);
            dependent=0;
            if (v.getId() == mButton1.getId())
            {
                dependent=1;
                ans4 = button.getText().toString();
                questionFourAsnwered=true;
                nextButton.performClick();

            } else if (v.getId() == mButton2.getId()) {
                dependent=0;
                ans4 = button.getText().toString();
                questionFourAsnwered = true;
                nextButton.performClick();
            }

        }
    }


    public static class FragmentFive extends Fragment
    {

        static View v;
        EditText editText;


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

                }
            }
        }



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
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            v = inflater.inflate(R.layout.fragment_form_four_q5, container, false);
            editText = v.findViewById(R.id.ans5EditText);
            editText.setFilters(new InputFilter[]{new FormOne.InputFilterMinMax("1", "1500")});
            editText.addTextChangedListener(new TextWatcher()
            {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after)
                {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count)
                {
                    if(TextUtils.isEmpty(editText.getText()))
                    {
                        questionFiveAnswerd = false;
                    }
                    else
                    {
                        questionFiveAnswerd = true;
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

    public static class FragmentSix extends Fragment
    {

        static View v;
        EditText editText;


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

                }
            }
        }



        static FragmentSix newInstance(int num, int color)
        {
            FragmentSix f = new FragmentSix();
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
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            v = inflater.inflate(R.layout.fragment_form_four_q6, container, false);
            editText = v.findViewById(R.id.q6EditText);
            editText.addTextChangedListener(new TextWatcher()
            {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after)
                {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count)
                {
                    if(TextUtils.isEmpty(editText.getText()))
                    {
                        questionSixAnswerd = false;
                    }
                    else
                    {
                        questionSixAnswerd = true;
                        ans6 = editText.getText().toString();
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



    public static class FragmentSeven extends Fragment
    {

        View v;

        EditText editText;

        static FragmentSeven newInstance(int num, int color)
        {
            FragmentSeven f = new FragmentSeven();
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
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            v = inflater.inflate(R.layout.fragment_form_four_q7, container, false);

            editText = v.findViewById(R.id.q7EditTextFormFour);

            editText.addTextChangedListener(new TextWatcher()
            {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after)
                {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count)
                {
                    if(TextUtils.isEmpty(editText.getText()))
                    {
                        questionSevenAnswerd = false;
                    }
                    else
                    {
                        ans7 = editText.getText().toString();
                        questionSevenAnswerd=true;
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


    public static class FragmentEight extends Fragment {
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
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                        ans8 = (timePicker.getHour()+":"+timePicker.getMinute()+"");
                    else
                        ans8 =(timePicker.getCurrentHour().toString()+":"+timePicker.getCurrentMinute().toString());
                }
            }
        }


        // You can modify the parameters to pass in whatever you want
        static FormFour.FragmentEight newInstance(int num, int color) {
            FormFour.FragmentEight f = new FormFour.FragmentEight();
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
            View v = inflater.inflate(R.layout.fragment_form_four_q8, container, false);
            timePicker= v.findViewById(R.id.simpleTimePickerFormFour);
            timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                @Override
                public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                        ans8 = (timePicker.getHour()+":"+timePicker.getMinute()+"");
                    else
                        ans8 =(timePicker.getCurrentHour().toString()+":"+timePicker.getCurrentMinute().toString());

                    isQuestionEightAsnwered=true;
                }
            });



            return v;

        }
    }


    public static class FragmentNine extends Fragment
    {


        View v;
        EditText editText;

        public void setUserVisibleHint(boolean isVisibleToUser)
        {
            super.setUserVisibleHint(isVisibleToUser);

            // Make sure that we are currently visible
            if (this.isVisible())
            {
                // If we are becoming invisible, then...
                if (!isVisibleToUser)
                {
                    ans9 = (editText.getText().toString());
                    questionNineAnswerd=true;
                }
            }
        }

        // You can modify the parameters to pass in whatever you want
        static FragmentNine newInstance(int num, int color)
        {
            FragmentNine f = new FragmentNine();
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
            v = inflater.inflate(R.layout.fragment_form_four_q9, container, false);
            editText = v.findViewById(R.id.q9EditTextFormFour);
            editText.addTextChangedListener(new TextWatcher()
            {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after)
                {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count)
                {
                    if(TextUtils.isEmpty(editText.getText()))
                    {
                        FormFour.questionNineAnswerd = false;
                        doneButton.setVisibility(View.INVISIBLE);
                    }
                    else
                    {
                        ans9 = editText.getText().toString();
                        FormFour.questionNineAnswerd = false;
                        doneButton.setVisibility(View.VISIBLE);
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



    }





