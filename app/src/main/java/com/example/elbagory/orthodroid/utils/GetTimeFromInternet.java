package com.example.elbagory.orthodroid.utils;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetTimeFromInternet {


    /**
     *
     * @return current time
     */
    public  String getTime() {

        String finalDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH);//"2019-02-02T08:15+00:00"

        DwnloadTask task = new DwnloadTask();
        String result = null;
        try {
            result = task.execute("http://www.worldclockapi.com/api/json/gmt/now").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Pattern pattern1 = Pattern.compile("\"currentDateTime\":(.*?),");
        Matcher matcher1 = pattern1.matcher(result);
        String full1 = null;
        while (matcher1.find()) {
            full1 = matcher1.group(1);
        }
        if (full1 != null) {
            full1 = full1.replace("\"", "");
            Date date = null;
            try {
                date = sdf.parse(full1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (date != null) {
                finalDate = sdf1.format(date);
            } else {
                finalDate = sdf1.format(Calendar.getInstance().getTime());
            }
        }
        if (finalDate == null) {
            finalDate = sdf1.format(Calendar.getInstance().getTime());

        }
        return finalDate;
    }

    private static class DwnloadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String result = null;
            URL url;
            HttpURLConnection urlconnection;
            try {
                url = new URL(strings[0]);
                urlconnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlconnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while (data != -1) {
                    char curd = (char) data;
                    result += curd;
                    data = reader.read();

                }
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "failed";
            } catch (IOException e) {
                e.printStackTrace();
                return "failed";
            }

        }
    }


}
