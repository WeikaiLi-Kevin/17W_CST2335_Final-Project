package com.teamwork.final_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.ArrayList;

import static com.teamwork.final_project.MainActivity.ACTIVITY_NAME;

public class AutomobileTemperature extends AppCompatActivity {
    private SQLiteDatabase autoDB;
    ProgressBar pb1;
    String min = "", max = "", curTem = "";
    TextView tv1, tv2, tv3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automobile_temperature);
        final EditText temp = (EditText) findViewById(R.id.tempTxt);
        Button btn = (Button) findViewById(R.id.tempBtn);
        tv1 = (TextView) findViewById(R.id.temp);
        tv2 = (TextView) findViewById(R.id.min);
        tv3 = (TextView) findViewById(R.id.max);
        pb1 = (ProgressBar)findViewById(R.id.progressBar);
        pb1.setVisibility(View.VISIBLE);

        DatabaseHelper autodbHelper = new DatabaseHelper(this);
        autoDB = autodbHelper.getWritableDatabase();
        final ContentValues autoTempValues = new ContentValues();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoTempValues.put(DatabaseHelper.KEY_MESSAGE,temp.getText().toString() );
                autoTempValues.put(DatabaseHelper.AUTO_COLUMN_NAME,"temperature");
               // arrayId.add(
                autoDB.insert(DatabaseHelper.AUTO_DATABASE_NAME, "", autoTempValues);
                //et.setText("");
                Cursor results = autoDB.query(false,DatabaseHelper.AUTO_DATABASE_NAME,new String[]{DatabaseHelper.KEY_ID,DatabaseHelper.AUTO_COLUMN_NAME,DatabaseHelper.KEY_MESSAGE},null,
                        null,null,null,null,null
                );
                //int rows = results.getCount();
                int messageIndex = results.getColumnIndex(DatabaseHelper.KEY_MESSAGE);
                results.moveToFirst();
                while(!results.isAfterLast()) {
                    Log.i(ACTIVITY_NAME,"SQL MESSAGE:"+results.getString(messageIndex));
                    Log.i(ACTIVITY_NAME,"Cursor's column count =" + results.getColumnCount()) ;
                   results.moveToNext();
                }
                for(int num = 0 ;num<results.getColumnCount();num++)
                    Log.i(ACTIVITY_NAME,results.getColumnName( num));
                Toast.makeText(AutomobileTemperature.this, getString(R.string.autoSubmitSuccessful), Toast.LENGTH_LONG).show();
            }
        });
        TempQuery thread = new TempQuery();
        thread.execute();

    }
    private class TempQuery extends AsyncTask<String, Integer, String>
    {

        protected String doInBackground(String ...args)
        {
            byte buffer[] = new byte[512];
            String in = "";
            String name = "";
            try {
                URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=d99666875e0e51521f0040a3d97d0f6a&mode=xml&units=metric");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                InputStream inStream = conn.getInputStream();
                //  Log.i("url input stream:" , "" + inStream);
                // inStream.read(buffer);
                //in = new String(buffer);
                //Log.i("in",in);
                //  conn.setReadTimeout(10000 /* milliseconds */);
                //  conn.setConnectTimeout(15000 /* milliseconds */);
                // conn.setRequestMethod("GET");
                // conn.setDoInput(true);
                // Starts the query
                // conn.connect();
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser parser = factory.newPullParser();
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(inStream, null);
                //parser.nextTag();
                Log.i("XML parsing:" , "" + parser.getEventType());
                Log.i("parser get name", parser.getName()==null? "null":parser.getName() );
                while(parser.getEventType() != XmlPullParser.END_DOCUMENT)
                {
                    Log.i("parser get name",parser.getName()==null? "null":parser.getName());
                    if(parser.getEventType() == XmlPullParser.START_TAG) {
                        if (parser.getName().equals("temperature") ){
                            min = parser.getAttributeValue(null, "min");
                            Log.i("min:", min);
                            publishProgress(25);
                            max = parser.getAttributeValue(null, "max");
                            Log.i("max:", max);
                            publishProgress(50);
                            curTem = parser.getAttributeValue(null, "value");
                            Log.i("value:", curTem);
                            publishProgress(75);
                        }

                    }
                    parser.next();
                }
            } catch (Exception ex) {
                Log.d("XML:", ex.getMessage());
            }
            return in;
        }
           public void onProgressUpdate(Integer ...value)
        {

            pb1.setVisibility(View.VISIBLE);
            pb1.setProgress(value[0]);



        }

        public void onPostExecute(String work)
        {

            tv1.setText(curTem);
            tv2.setText(min);
            tv3.setText(max);
            //img.setImageBitmap(pic);
            pb1.setVisibility(View.INVISIBLE);
        }

    }
}
