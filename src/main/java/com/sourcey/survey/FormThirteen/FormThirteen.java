package com.sourcey.survey.FormThirteen;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.sourcey.survey.Fragments.CustomButtonFragment;
import com.sourcey.survey.Fragments.CustomImageFragment;
import com.sourcey.survey.Fragments.CustomTimePickerFragment;
import com.sourcey.survey.Fragments.DatePickerFragment;
import com.sourcey.survey.Fragments.EntryTextFragment;
import com.sourcey.survey.Fragments.SpinnerFragment;
import com.sourcey.survey.Models.ENUMSQuestionType;
import com.sourcey.survey.Models.FormModels;
import com.sourcey.survey.Models.FormOptionModel;
import com.sourcey.survey.Models.FormQuestionModel;
import com.sourcey.survey.Models.FormSeventeenToTwentyModel;
import com.sourcey.survey.R;
import com.sourcey.survey.Utils.ENUMS;
import com.sourcey.survey.Utils.HelperClass;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class FormThirteen extends BaseActivity
{

    private Location currentLocation;

    private FusedLocationProviderClient mFusedLocationProviderClient;
    private static final String TAG = "FormOne";
    public static int selectedForm = 13;
    public static MyAdapter mAdapter;
    public static ViewPager mPager;
    int currentPage = 0;
    public static Button nextButton;
    Button backButton;
    public static Button doneButton;
    SharedPreferences sharedPreferences;
    public static ArrayList<FormQuestionModel> formQuestionModels;

    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_form);

        formQuestionModels = new ArrayList<>();

        if(selectedForm == 13) {
            formQuestionModels = FormModels.getArrayForForm13();
        }else  if(selectedForm == 14) {
            formQuestionModels = FormModels.get_mutableArrayForForm14();
        }else  if(selectedForm == 15) {
            formQuestionModels = FormModels.getMutableArrayForForm15();
        }else  if(selectedForm == 16) {
            formQuestionModels = FormModels.getMutableArrayForForm16();
        }else  if(selectedForm == 17) {
            formQuestionModels = FormSeventeenToTwentyModel.get_mutableArrayForForm17();
        }else  if(selectedForm == 18) {
            formQuestionModels = FormSeventeenToTwentyModel.get_mutableArrayForForm18();
        }else  if(selectedForm == 19) {
            formQuestionModels = FormSeventeenToTwentyModel.get_mutableArrayForForm19();
        }else  if(selectedForm == 20) {
            formQuestionModels = FormSeventeenToTwentyModel.get_mutableArrayForForm20();
        }else  if(selectedForm == 21) {
            formQuestionModels = FormSeventeenToTwentyModel.get_mutableArrayForForm21();
        }

        sharedPreferences  = getApplicationContext().getSharedPreferences("USER_ID",MODE_PRIVATE);

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        final String currentDateandTime = df.format(Calendar.getInstance().getTime());

        mAdapter = new MyAdapter(getSupportFragmentManager());
        mPager = findViewById(R.id.viewpager);
        mPager.setAdapter(mAdapter);
        mPager.setOffscreenPageLimit(1);
        doneButton = findViewById(R.id.doneButton);
        nextButton = findViewById(R.id.nextButton);
        backButton = findViewById(R.id.backButton);

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(FormThirteen.this);
        doneButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                if(!validateQuestion()){
                    return;
                }
                @SuppressLint("MissingPermission")
                final Task location = mFusedLocationProviderClient.getLastLocation();
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
//                            Toast.makeText(FormThirteen.this, " Latitude: " + currentLocation.getLatitude() + " Longitude: " + currentLocation.getLongitude(), Toast.LENGTH_LONG).show();
                            if(HelperClass.validateInternet(FormThirteen.this))
                            {
//                                Toast.makeText(FormThirteen.this, " Latitude: " + currentLocation.getLatitude() + " Longitude: " + currentLocation.getLongitude(), Toast.LENGTH_LONG).show();

                                ArrayList<String > answerString = new ArrayList<>();
                                for (int i = 0; i < formQuestionModels.size(); i++) {
                                    String answerSelected = formQuestionModels.get(currentPage).selectedOption.entryTypeOptionResponse;//formQuestionModels.get(i).selectedOption.entryTypeOptionResponse;
                                    answerString.add(answerSelected);

                                }

                                String id = String.valueOf(sharedPreferences.getInt("ID",0));
                                DatabaseAsyncFormThirteen worker = new DatabaseAsyncFormThirteen(FormThirteen.this,answerString,id,
                                        currentDateandTime, currentLocation.getLatitude()+"", currentLocation.getLongitude()+"");
                                worker.execute();

                            }
                            else
                            {
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                String query =sharedPreferences.getString("query","");


                                StringBuilder sb = new StringBuilder();
                                sb.append("\'"+String.valueOf(sharedPreferences.getInt("ID",0)+"\'"));
                                sb.append(",");

                                for (int i = 0; i < formQuestionModels.size(); i++) {
                                    String answerSelected = formQuestionModels.get(currentPage).selectedOption.entryTypeOptionResponse;//formQuestionModels.get(i).selectedOption.entryTypeOptionResponse;
                                    sb.append("\'"+answerSelected+"\'");
                                    sb.append(",");
                                }
                                sb.append("\'"+currentDateandTime+"\'");
                                sb.append(",");
                                sb.append("\'"+currentLocation.getLatitude()+"\'");
                                sb.append(",");
                                sb.append("\'"+currentLocation.getLongitude()+"\'");

                                query += "INSERT INTO form12survey (email,ans1 ,ans2, ans3, ans4, ans5,ans6,ans7,ans8,ans9,ans10,ans11,ans12,ans13,date, lati, longi) VALUES ("+sb.toString()+")&";
                                editor.putBoolean("checkSync",true);
                                editor.putString("query", query);
                                editor.apply();
                            }

                        } else
                        {
                            Log.d(TAG, "getLocations: unable to complete location task");
//                            Toast.makeText(FormThirteen.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }

                        currentLocation = (Location) task.getResult();

                        StringBuilder sb = new StringBuilder();

                        for (int i = 0; i < formQuestionModels.size(); i++) {
                            String answerSelected = formQuestionModels.get(currentPage).selectedOption.entryTypeOptionResponse;//formQuestionModels.get(i).selectedOption.entryTypeOptionResponse;
                            sb.append(answerSelected);

                        }
                        sb.append("\'"+currentDateandTime+"\'");
                        sb.append(",");
                        sb.append("\'"+currentLocation.getLatitude()+"\'");
                        sb.append(",");
                        sb.append("\'"+currentLocation.getLongitude()+"\'");
                        sharedPreferences.edit().putString("FormTwelve",sharedPreferences.getInt("ID",0)+sb.toString()
                                +currentDateandTime+currentLocation.getLongitude()+""+currentLocation.getLongitude()+"").apply();
                    }
                });
                Toast.makeText(FormThirteen.this, "Done", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!validateQuestion()){
                    return;
                }

                if (currentPage < formQuestionModels.size() - 1)
                {
                    currentPage++;
                }
                if (formQuestionModels.size() - 1 == currentPage)
                {
                    nextButton.setEnabled(false);
                    nextButton.setVisibility(View.INVISIBLE);
                    doneButton.setVisibility(View.VISIBLE);
                }else {

                    backButton.setEnabled(true);
                    backButton.setVisibility(View.VISIBLE);
                    doneButton.setVisibility(View.INVISIBLE);
                }

                mPager.setCurrentItem(currentPage, true);
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
                if (currentPage > 0)
                {
                    currentPage--;
                }
                if (currentPage == 0)
                {
                    backButton.setEnabled(false);
                    backButton.setVisibility(View.INVISIBLE);
                }
                setCurrentItem(currentPage, true);
                checkIfRemovedChild();
            }
        });

        if (currentPage == 0)
        {
            backButton.setEnabled(false);
            backButton.setVisibility(View.INVISIBLE);
        }
    }

    private boolean validateQuestion() {

        String answerSelected =   formQuestionModels.get(currentPage).selectedOption.entryTypeOptionResponse;
        if(formQuestionModels.get(currentPage).questionType == ENUMSQuestionType.kQuestionTypeSingleEntry
                || formQuestionModels.get(currentPage).questionType == ENUMSQuestionType.kQuestionTypeNumeric
                || formQuestionModels.get(currentPage).questionType ==  ENUMSQuestionType.kQuestionTypeDate
                || formQuestionModels.get(currentPage).questionType ==  ENUMSQuestionType.kQuestionTypePhoto
                ){
            if(answerSelected  == null) answerSelected  = "";
            if(answerSelected.trim().length()>0){

            }  else {
                HelperClass.showDialog(FormThirteen.this, "Please enter details");
                return false;
            }
        }else if(formQuestionModels.get(currentPage).questionType ==  ENUMSQuestionType.kQuestionTypeYESNO){
            if(answerSelected  == null) answerSelected  = "";
            if(answerSelected.trim().length()>0){

            }  else {
                HelperClass.showDialog(FormThirteen.this, "Please select your answer");
                return false;
            }
        }else if( formQuestionModels.get(currentPage).questionType ==  ENUMSQuestionType.kQuestionTypeSpinnerPicker  ){
            if(answerSelected  == null) answerSelected  = "";
            if(answerSelected.trim().length()>0){
                if(answerSelected.equalsIgnoreCase("reselect")){
                    HelperClass.showDialog(FormThirteen.this, "Please re-select your answer");
                    return false;
                }
            }  else {
                HelperClass.showDialog(FormThirteen.this, "Please select your answer");
                return false;
            }
        } else  if(formQuestionModels.get(currentPage).questionType ==  ENUMSQuestionType.kQuestionTypePhoneNumber){

            if(answerSelected  == null) answerSelected  = "";
            if(answerSelected.trim().length()>0 ){
                if(!HelperClass.validatePhone(answerSelected)){
                    HelperClass.showDialog(FormThirteen.this, "Phone number  must be in the form 0XXX-XXXXXXX");
                    return false;
                }
            }  else {
                HelperClass.showDialog(FormThirteen.this, "Please enter details");
                return false;
            }
        }else  if(formQuestionModels.get(currentPage).questionType ==  ENUMSQuestionType.kQuestionTypeEmail){
            if(answerSelected  == null) answerSelected  = "";
            if(answerSelected.trim().length()>0 ){
                if(!HelperClass.validateEmail(answerSelected)){
                    HelperClass.showDialog(FormThirteen.this, "Invalid Email");
                    return false;
                }
            }  else {
                HelperClass.showDialog(FormThirteen.this, "Please enter details");
                return false;
            }
        }else  if(formQuestionModels.get(currentPage).questionType ==  ENUMSQuestionType.kQuestionTypeNIC){
            if(answerSelected  == null) answerSelected  = "";
            if(answerSelected.trim().length()>0){
                if(!HelperClass.validateCNIC(answerSelected)){
                    HelperClass.showDialog(FormThirteen.this, "Cnic must be in the form XXXXX-XXXXXXX-X");
                    return false;
                }
            }  else {
                HelperClass.showDialog(FormThirteen.this, "Please enter details");
                return false;
            }
        }else  if(formQuestionModels.get(currentPage).questionType ==  ENUMSQuestionType.kQuestionTypeTime){
            if(answerSelected  == null) answerSelected  = "";
            if(answerSelected.trim().length()>0){
                if(answerSelected.equalsIgnoreCase("-1")){
                    HelperClass.showDialog(FormThirteen.this, "Please select the time between 8am to 8pm.");
                    return false;
                }
            }  else {
                HelperClass.showDialog(FormThirteen.this, "Please enter details");
                return false;
            }
        }
        return true;
    }

    private void checkIfRemovedChild(){
        for (int i = 0; i < formQuestionModels.get(currentPage).optionsArray.size(); i++) {
            FormOptionModel retriveFromButton = formQuestionModels.get(currentPage).optionsArray.get(i);
            if(retriveFromButton.mutableArrayForDependantQuestions.size() >0) {
//                        removeDependentQuestionInMainlist(retriveFromButton.mutableArrayForDependantQuestions);
                for(int j= 0; j < retriveFromButton.mutableArrayForDependantQuestions.size(); j++) {
//                            formQuestionModels.remove(currentPage + j);
                    FormQuestionModel deleteObj = retriveFromButton.mutableArrayForDependantQuestions.get(j);
                    if (formQuestionModels.contains(deleteObj)) {
                        System.out.println("Account found");
                        formQuestionModels.remove(deleteObj);
                    }
                }
                mAdapter.notifyDataSetChanged();
                synchronized(mPager){
                    mPager.notify();
                }
            }
        }
    }

    public void setCurrentItem(int item, boolean smoothScroll){
        mPager.setCurrentItem(item, smoothScroll);
    }

    public class MyAdapter extends FragmentStatePagerAdapter
    {
        public MyAdapter(android.support.v4.app.FragmentManager fm){
            super(fm);
        }
        @Override
        public int getCount(){
            return formQuestionModels.size();
        }

        public int getItemPosition(Object object){
            return POSITION_NONE;
        }

        @Override
        public Fragment getItem(int position) {
            String pagerNumber = "";
            FormQuestionModel oneFormModel = new FormQuestionModel();
            oneFormModel = formQuestionModels.get(position);
            pagerNumber = position+1  + " of " + formQuestionModels.size();
            if(oneFormModel.questionType == ENUMSQuestionType.kQuestionTypeSingleEntry
                    || oneFormModel.questionType == ENUMSQuestionType.kQuestionTypeNumeric
                    || oneFormModel.questionType == ENUMSQuestionType.kQuestionTypeSingleLargeEntry){
                return EntryTextFragment.newInstance(position, ENUMS.editTextString ,pagerNumber );
            }else if(oneFormModel.questionType == ENUMSQuestionType.kQuestionTypeNIC){
                return EntryTextFragment.newInstance(position, ENUMS.editCNIC ,pagerNumber );
            }else  if(oneFormModel.questionType == ENUMSQuestionType.kQuestionTypePhoneNumber){
                return EntryTextFragment.newInstance(position, ENUMS.editPhoneNumber ,pagerNumber );
            }else  if(oneFormModel.questionType == ENUMSQuestionType.kQuestionTypeEmail){
                return EntryTextFragment.newInstance(position, ENUMS.editEmail ,pagerNumber );
            }else  if( oneFormModel.questionType == ENUMSQuestionType.kQuestionTypeSpinnerPicker){
                return SpinnerFragment.newInstance(position, ENUMS.editTextString ,pagerNumber );
            }else  if(oneFormModel.questionType == ENUMSQuestionType.kQuestionTypeYESNO ){
                return CustomButtonFragment.newInstance(position, pagerNumber );
            }else  if(oneFormModel.questionType == ENUMSQuestionType.kQuestionTypeOptions ){
                return CustomButtonFragment.newInstance(position, pagerNumber );
            }else  if( oneFormModel.questionType == ENUMSQuestionType.kQuestionTypeTime){
                return CustomTimePickerFragment.newInstance(position, pagerNumber);
            }else  if( oneFormModel.questionType == ENUMSQuestionType.kQuestionTypePhoto){
                return CustomImageFragment.newInstance(position, pagerNumber);
            }else  if( oneFormModel.questionType == ENUMSQuestionType.kQuestionTypeDate){
                return DatePickerFragment.newInstance(position,pagerNumber);
            }else {
                return CustomButtonFragment.newInstance(position, pagerNumber );
            }


        }
    }

}

