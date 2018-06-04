package com.sourcey.survey.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.sourcey.survey.FilePath;
import com.sourcey.survey.FormEleven.FormEleven;
import com.sourcey.survey.FormThirteen.BaseActivity;
import com.sourcey.survey.FormThirteen.FormThirteen;
import com.sourcey.survey.HttpRequestImageLoadTask;
import com.sourcey.survey.HttpRequestLongOperation;
import com.sourcey.survey.Interface.ImagePickerInterface;
import com.sourcey.survey.R;
import com.sourcey.survey.Upload;
import com.sourcey.survey.Utils.HelperClass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CustomImageFragment  extends Fragment
{

    String websiteURL   = "https://premium43.web-hosting.com:2083/";
    String apiURL       = "http://emis.creativecube.io/Servey-PHP"; // Without ending slash
    String apiPassword  = "qw2e3erty6uiop";
    String uploadedPath ="";
    public static ImageView imgPreview;
    public static VideoView videoPreview;
    public static Button btnRecordVideo;
    public static Button galleryButton;
    TextView textViewDynamicText, textview,topLable;
    Button videoButton;

    public String currentImagePath = "";
    public String currentImage = "";

    private String selectedPath;
    private String myPathThumbnail;

    private String pageNumber = "";
    ImageView imageViewImage;
    int position = -1;

    View v;
    Button btnCapturePicture;

    public static CustomImageFragment newInstance(int position,String pageNumber)
    {
        CustomImageFragment f = new CustomImageFragment();
        Bundle args = new Bundle();
        f.setArguments(args);

        f.pageNumber =pageNumber;
        f.position = position;
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }
    public void imageUploadResult() {
        // Dynamic text

        String dynamicText = textViewDynamicText.getText().toString();

        // Split
        int index = dynamicText.lastIndexOf('/');
        try {
            currentImagePath = dynamicText.substring(0, index);
        }
        catch (Exception e){
            Toast.makeText(getActivity(), "path: " + e.toString(), Toast.LENGTH_LONG).show();
        }
        try {
            currentImage = dynamicText.substring(index,dynamicText.length());
        }
        catch (Exception e){
            Toast.makeText(getActivity(), "image: " + e.toString(), Toast.LENGTH_LONG).show();
        }

        // Load new image
        // Todo: loadImage();

    }





    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK && requestCode == 1) {
            Uri selectedImageUri = data.getData();

            // Save image
            String destinationFilename = FilePath.getPath(getActivity(), selectedImageUri);
            myPathThumbnail =destinationFilename;
            // Dynamic text
            TextView textViewDynamicText = (TextView) v.findViewById(R.id.dynamicTextViewForm3); // Dynamic text

            // URL
            String urlToApi = apiURL + "/uploadImage.php";


             // Data

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            String imageName = sharedPreferences.getString("ID","")+ "::"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());


            Map mapData = new HashMap();
            mapData.put("inp_api_password", apiPassword);
            mapData.put("img_name",imageName);
            uploadedPath = imageName + ".png";
            FormEleven.questionElevenAnswerd=true;

            HttpRequestLongOperation task = new HttpRequestLongOperation(getActivity(), urlToApi,
                    "post_image", mapData, destinationFilename, textViewDynamicText, new HttpRequestLongOperation.TaskListener() {
                @Override
                public void onFinished(String result) {
                    // Do Something after the task has finished
                    imageUploadResult();

                    loadImage();

                }
            });
            task.execute();



        }
        else if(resultCode == getActivity().RESULT_OK && requestCode== 2)
        {

            System.out.println("SELECT_VIDEO");
            Uri selectedImageUri = data.getData();
            selectedPath = getPath(selectedImageUri);
            textViewDynamicText.setText(selectedPath);
            uploadVideo();
        }
    }

    private void uploadVideo() {
        class UploadVideo extends AsyncTask<Void, Void, String>
        {

            ProgressDialog uploading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                uploading = ProgressDialog.show(getActivity(), "Uploading File", "Please wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                uploading.dismiss();
                textViewDynamicText.setText(Html.fromHtml("<b>Uploaded at <a href='" + s + "'>" + s + "</a></b>"));
                textViewDynamicText.setMovementMethod(LinkMovementMethod.getInstance());
            }

            @Override
            protected String doInBackground(Void... params) {
                Upload u = new Upload();
                String msg = u.uploadVideo(selectedPath);
                return msg;
            }
        }
        UploadVideo uv = new UploadVideo();
        uv.execute();
    }
    public String getPath(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getActivity().getContentResolver().query(
                android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
        cursor.close();

        return path;
    }
    /*- Load image ------------------------------------------------------------------ */
    public void loadImage(){


        if(!(currentImagePath.equals("")) && !(currentImage.equals(""))){

            String loadImage = websiteURL + "/" + currentImagePath + "/" + currentImage;
//            new HttpRequestImageLoadTask(getActivity(), loadImage, imageViewImage).execute();

            imageViewImage.setImageURI(Uri.parse(myPathThumbnail));
            imageViewImage.setVisibility(View.VISIBLE);
            FormThirteen.formQuestionModels.get(position).selectedOption.entryTypeOptionResponse = uploadedPath;
            FormThirteen.formQuestionModels.get(position).selectedOption.localPhotoPath = uploadedPath;

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.fragment_form_image_picker, container, false);

        galleryButton = v.findViewById(R.id.galleryButtonFormTen);
        textViewDynamicText = (TextView)v.findViewById(R.id.dynamicTextViewForm3);

        imgPreview = v.findViewById(R.id.imgPreview);

        TextView textview = (TextView) v.findViewById(R.id.textview); // Dynamic text
        TextView topLable = (TextView) v.findViewById(R.id.topLable); // Dynamic text

        textview.setText(FormThirteen.formQuestionModels.get(position).questionTextInEnglish +"\n"
                +FormThirteen.formQuestionModels.get(position).questionTextInUrdu);
        topLable.setText(pageNumber);

         imageViewImage = v.findViewById(R.id.imgPreview);


         String localPath = FormThirteen.formQuestionModels.get(position).selectedOption.localPhotoPath;
         if(localPath .trim().length() >0) {
             imageViewImage.setImageURI(Uri.parse(localPath));
             imageViewImage.setVisibility(View.VISIBLE);
         }

        galleryButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);//one can be replaced with any action code
            }  });
        videoButton = v.findViewById(R.id.cameraButtonFormTen);
        videoButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                /*Intent intent = new Intent();
                intent.setType("Video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select a Photo "), 2);*/

                if(((BaseActivity) getActivity()).checkREADSDtoragePermissionInApp(CustomImageFragment.this) == true) {
                    openGallery();
                }/*else{
                    HelperClass.showDialogForStoragePermission(getActivity(), "Permission Denied, You don't have permission to use the photos.\nPlease turn on the permission from the App Setting .");
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }*/
            }
        });


        return v;


    }
    AlertDialog.Builder  builder;
    public void openGallery(){
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, 1);//one can be replaced with any action code
    }


}
