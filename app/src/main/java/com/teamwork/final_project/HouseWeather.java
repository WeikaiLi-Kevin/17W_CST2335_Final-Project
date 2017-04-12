package com.teamwork.final_project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HouseWeather extends AppCompatActivity {

    private ProgressBar bar;
    private String min;
    private String max;
    private String current;
    private Bitmap pic;
    private TextView minT, maxT, currentT;
    private ImageView img;
    String snackbarMessage = "Done!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.house_activity_house_weather);

        Button enterBtn = (Button) findViewById(R.id.weather);
        enterBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HouseWeather.this, HouseListView.class);
                startActivityForResult(intent, 5);
            }
        });

        // Step 9 Custom dialog and Snackbar
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.house_activity_dialog_signin, null);
        dialogBuilder.setView(dialogView);
        final EditText edt = (EditText) dialogView.findViewById(R.id.username);
        dialogBuilder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                snackbarMessage = edt.getText().toString();
                Snackbar.make(findViewById(android.R.id.content), "Sign in.", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });
        dialogBuilder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();

        bar = (ProgressBar) findViewById(R.id.progressBar);
        bar.setVisibility(ProgressBar.VISIBLE);

        currentT = (TextView)findViewById(R.id.currentTemp);
        minT = (TextView)findViewById(R.id.minTemp);
        maxT = (TextView)findViewById(R.id.maxTemp);
        img = (ImageView)findViewById(R.id.currentWeather);

        ForecastQuery thread = new ForecastQuery();
        thread.execute();

    }

    private class ForecastQuery extends AsyncTask<String, Integer, String> {

        protected String doInBackground(String...args) {
            String in = "";
            try {
                URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=d99666875e0e51521f0040a3d97d0f6a&mode=xml&units=metric");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();

                InputStream inStream = conn.getInputStream();
                XmlPullParserFactory Xml = XmlPullParserFactory.newInstance();
                Xml.setNamespaceAware(false);

                XmlPullParser xpp = Xml.newPullParser();
                xpp.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                xpp.setInput(inStream, null);

                while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                    Log.i("parser get name", xpp.getName() == null ? "null" : xpp.getName());
                    if (xpp.getEventType() == XmlPullParser.START_TAG) {
                        if (xpp.getName().equals("temperature")) {
                            min = xpp.getAttributeValue(null, "min");
                            Log.i("min:", min);
                            publishProgress(25);
                            max = xpp.getAttributeValue(null, "max");
                            Log.i("max:", max);
                            publishProgress(50);
                            current = xpp.getAttributeValue(null, "value");
                            Log.i("value", current);
                            publishProgress(75);
                        } else if (xpp.getName().equals("weather")) {
                            String name1 = xpp.getAttributeValue(null, "icon");
                            Log.i("name: ", name1);

                            try {
                                URL weather = new URL("http://openweathermap.org/img/w/" + name1 + ".png");

                                HttpURLConnection connection = null;
                                try {
                                    connection = (HttpURLConnection) weather.openConnection();
                                    connection.connect();
                                    int responseCode = connection.getResponseCode();
                                    if (responseCode == 200) {
                                        if (fileExistance(name1 + ".png") == true) {
                                            FileInputStream fis = null;
                                            try {
                                                fis = openFileInput(name1 + ".png");
                                            } catch (FileNotFoundException e) {
                                                e.printStackTrace();
                                            }
                                            pic = BitmapFactory.decodeStream(fis);
                                        } else {
                                            Bitmap image = BitmapFactory.decodeStream(connection.getInputStream());
                                            FileOutputStream outputStream = openFileOutput(name1 + ".png", Context.MODE_PRIVATE);

                                            image.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
                                            outputStream.flush();
                                            outputStream.close();
                                            pic = image;
                                        }
                                    }
                                } catch (Exception e) {

                                } finally {
                                    if (connection != null) {
                                        connection.disconnect();
                                    }
                                }
                            } catch (MalformedURLException e) {

                            }
                        }
                    }
                    xpp.next();
                }
            } catch (Exception ex) {
                Log.d("XML: ", ex.getMessage());
            }
            return in;
        }
        public boolean fileExistance (String fname) {
            File file = getBaseContext().getFileStreamPath(fname);
            return file.exists();
        }

        protected void onProgressUpdate(Integer... value) {
            bar.setVisibility(View.VISIBLE);
            bar.setProgress(value[0]);
        }

        protected void onPostExecute(String result) {
            currentT.setText("Current House_Temperature: " + current);
            minT.setText("Minimum House_Temperature: " + min);
            maxT.setText("Maximum House_Temperature: " + max);
            img.setImageBitmap(pic);
            bar.setVisibility(View.INVISIBLE);

        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 5) {
        }
    }

}
