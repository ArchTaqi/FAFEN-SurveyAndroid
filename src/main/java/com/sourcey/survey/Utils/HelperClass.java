package com.sourcey.survey.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sourcey.survey.Fragments.CustomButtonFragment;
import com.sourcey.survey.Fragments.CustomTimePickerFragment;
import com.sourcey.survey.Fragments.EntryTextFragment;
import com.sourcey.survey.Models.ENUMSQuestionType;
import com.sourcey.survey.Models.FormQuestionModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelperClass {

    public static void showMessage(Context context, String message){
        Toast.makeText(context,message, Toast.LENGTH_LONG).show();
    }

    public static void showDialog(Context context, String message) {

        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Make sure that we are currently visible
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder( context);
        }
        builder.setTitle(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private static void goToSettings(Context mContext) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", mContext.getPackageName(), null);
        intent.setData(uri);
        mContext.startActivity(intent);
    }

    public static AlertDialog.Builder showDialogForStoragePermission(final Context context, String message) {

        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Make sure that we are currently visible
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder( context);
        }
        builder.setTitle(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        goToSettings(context);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

        return builder;
    }

    public static boolean errorShowIfEditTextEmpty(Context context,Button button, EditText input){
        if(input.getText().toString().trim().length() < 1){
            HelperClass.showDialog(context, "Empty Field");
            button.setSelected(false);
            return false;

        }else {
            return  true;
        }
    }

    public static boolean validateInternet(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isAvailable() && activeNetwork.isConnected();
    }

    public static boolean isValidPhoneNumber(CharSequence phoneNumber) {
        if (!TextUtils.isEmpty(phoneNumber)) {
            return Patterns.PHONE.matcher(phoneNumber).matches();
        }
        return false;
    }

    public static boolean validatePhone(String ans){

        String phonenumber = "0345-5332208";
        Pattern pattern = Pattern.compile("\\d{4}-\\d{7}");
        Matcher matcher = pattern.matcher(ans);
        if (matcher.matches()) {
            System.out.println("phone Valid");
            return true;
        }
        else
        {
            System.out.println("phone number must be in the form XXXX-XXXXXXX");
//                HelperClass.showMessage(context,"\"cnic must be in the form XXXXX-XXXXXXX-X\"");
            return false;
        }
    }

    public static boolean validateCNIC(String ans){

            String cnic = "16102-7020715-1";
            Pattern pattern = Pattern.compile("\\d{5}-\\d{7}-\\d{1}");
            Matcher matcher = pattern.matcher(ans);
            if (matcher.matches()) {
                System.out.println("cnic Valid");
                return true;
            }
            else
            {
                System.out.println("Cnic must be in the form XXXXX-XXXXXXX-X");
//                HelperClass.showMessage(context,"\"cnic must be in the form XXXXX-XXXXXXX-X\"");
                return false;
            }
    }

    public static boolean validateEmail(String ans){

        return (!TextUtils.isEmpty(ans) && Patterns.EMAIL_ADDRESS.matcher(ans).matches());
    }
   }
