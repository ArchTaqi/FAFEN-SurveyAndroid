package com.sourcey.survey.FormThirteen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by MAC on 4/5/2018.
 */

public class DatabaseAsyncFormThirteen extends AsyncTask<String,Void ,String> {

    private Context context;
    //private AlertDialog alertDialog;

    private ArrayList<String> strResult;
    private ArrayList<String> strRequest = new ArrayList<>();
    String id,currentDateandTime,  lat, lon;
    public DatabaseAsyncFormThirteen(Context context, ArrayList<String> answerString, String id, String currentDateandTime, String s, String s1)
    {
        this.context = context;
        this.strRequest = answerString;
        this.id = id;
        this.currentDateandTime  = currentDateandTime;
        this.lat= s;
        this.lon = s1;
    }

    @Override
    protected String doInBackground(String... params) {


        String ServerURL = "http://emis.creativecube.io/Servey-PHP/InsertForm.php" ;

        try {

            publishProgress();
            String formNo = "form " + FormThirteen.selectedForm;

            URL url = new URL(ServerURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String post_data = "";

            post_data =  URLEncoder.encode("formNo", "UTF-8") + "=" + URLEncoder.encode(formNo, "UTF-8")
                    + "&" +
                    URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");

            for (int i = 0; i < strRequest.size(); i++) {

                    post_data = post_data  +"&"+
                            URLEncoder.encode("ans"+ (i + 1),"UTF-8")+ "="+ URLEncoder.encode(strRequest.get(i),"UTF-8");

            }

            post_data = post_data  +"&"+
                    URLEncoder.encode("date","UTF-8")+ "="+ URLEncoder.encode(currentDateandTime,"UTF-8")
                    +"&"+
                    URLEncoder.encode("lati","UTF-8")+ "="+ URLEncoder.encode(lat,"UTF-8")
                    +"&"+
                    URLEncoder.encode("longi","UTF-8")+ "="+ URLEncoder.encode(lon,"UTF-8");

           /*  post_data =
                    URLEncoder.encode("formNo","UTF-8")+ "="+ URLEncoder.encode(formNo,"UTF-8")
                            +"&"+
                    URLEncoder.encode("id","UTF-8")+ "="+ URLEncoder.encode(id,"UTF-8")
                            +"&"+
                            URLEncoder.encode("ans1","UTF-8")+ "="+ URLEncoder.encode(ans1,"UTF-8")
                            +"&"+
                            URLEncoder.encode("ans2","UTF-8")+ "="+ URLEncoder.encode(ans2,"UTF-8")
                            +"&"+
                            URLEncoder.encode("ans3","UTF-8")+ "="+ URLEncoder.encode(ans3,"UTF-8")
                            +"&"+
                            URLEncoder.encode("ans4","UTF-8")+ "="+ URLEncoder.encode(ans4,"UTF-8")
                            +"&"+
                            URLEncoder.encode("ans5","UTF-8")+ "="+ URLEncoder.encode(ans5,"UTF-8")
                            +"&"+
                            URLEncoder.encode("ans6","UTF-8")+ "="+ URLEncoder.encode(ans6,"UTF-8")
                            +"&"+
                            URLEncoder.encode("ans7","UTF-8")+ "="+ URLEncoder.encode(ans7,"UTF-8")
                            +"&"+
                            URLEncoder.encode("ans8","UTF-8")+ "="+ URLEncoder.encode(ans8,"UTF-8")
                            +"&"+
                            URLEncoder.encode("ans9","UTF-8")+ "="+ URLEncoder.encode(ans9,"UTF-8")
                            +"&"+
                            URLEncoder.encode("ans10","UTF-8")+ "="+ URLEncoder.encode(ans10,"UTF-8")
                            +"&"+
                            URLEncoder.encode("ans11","UTF-8")+ "="+ URLEncoder.encode(ans11,"UTF-8")
                            +"&"+
                            URLEncoder.encode("ans12","UTF-8")+ "="+ URLEncoder.encode(ans12,"UTF-8")
                            +"&"+
                            URLEncoder.encode("ans13","UTF-8")+ "="+ URLEncoder.encode(ans13,"UTF-8")
                            +"&"+
                            URLEncoder.encode("date","UTF-8")+ "="+ URLEncoder.encode(date,"UTF-8")
                            +"&"+
                            URLEncoder.encode("lati","UTF-8")+ "="+ URLEncoder.encode(lati,"UTF-8")
                            +"&"+
                            URLEncoder.encode("longi","UTF-8")+ "="+ URLEncoder.encode(longi,"UTF-8")


                    ;*/
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader  =  new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));//,"iso-8859-1"
            String result = "[";
            String line;
            while ((line = bufferedReader.readLine())!=null)
            {
                result =result+ line;
            }
            result = result+"]";


            JSONArray jsonArray = new JSONArray(result);
            JSONObject jsonObject = (JSONObject) jsonArray.get(0);

            strResult = new ArrayList<>();

            result = (String) jsonObject.get("response");
            strResult.add(result);

            //for (int i=0;i<jsonArray.length();i++)
            //{
            //JSONObject jsonObject = (JSONObject) jsonArray.get(0);
            //result = (String) jsonObject.get("id");
            //}
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onPreExecute() {
        //alertDialog =  new AlertDialog.Builder(context).create();
        //alertDialog.setTitle("Login Status");


    }

    public String getId()
    {
        return strResult.get(1);
    }
    @Override
    protected void onPostExecute(String result) {


        // Toast.makeText(context, result , Toast.LENGTH_LONG).show();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result","Done Form Twelve");
        ((Activity)context).setResult(Activity.RESULT_OK,returnIntent);
        ((Activity)context).finish();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
