package com.teamwork.final_project;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import static com.teamwork.final_project.MainActivity.ACTIVITY_NAME;
import static com.teamwork.final_project.R.mipmap.temp;

public class AutomobileLights extends AppCompatActivity {
    private SQLiteDatabase autoDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automobile_lights);
       // final EditText temp = (EditText) findViewById(R.id.tempTxt);
        Button btn = (Button) findViewById(R.id.autoLightsBtn);
        final RadioGroup rg = (RadioGroup) findViewById(R.id.autoRadioGroup);
        final RadioButton rb1 = (RadioButton) findViewById(R.id.autoLightClose);
        final RadioButton rb2 = (RadioButton) findViewById(R.id.autoLightNormal);
        final RadioButton rb3 = (RadioButton) findViewById(R.id.autoLightHigh);
        final Switch sw = (Switch) findViewById(R.id.autoLightDimmable);
        DatabaseHelper autodbHelper = new DatabaseHelper(this);
        autoDB = autodbHelper.getWritableDatabase();
        final ContentValues autoTempValues = new ContentValues();
        //final ContentValues autoTempValues1 = new ContentValues();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioButtonID = rg.getCheckedRadioButtonId();
                Log.i("RadioButton",""+radioButtonID);
                Log.d("Switch",sw.isChecked()+"");

                if(rb1.isChecked()){
                autoTempValues.put(DatabaseHelper.KEY_MESSAGE, "light turn off" );
                    autoTempValues.put(DatabaseHelper.AUTO_COLUMN_NAME,"lightTurnOff");}
                else if (rb2.isChecked()){
                    autoTempValues.put(DatabaseHelper.KEY_MESSAGE, "normal light" );
                    autoTempValues.put(DatabaseHelper.AUTO_COLUMN_NAME,"lightNormal");}
                else if (rb3.isChecked()){
                    autoTempValues.put(DatabaseHelper.KEY_MESSAGE, "high light" );
                    autoTempValues.put(DatabaseHelper.AUTO_COLUMN_NAME,"lightHigh");}
                autoDB.insert(DatabaseHelper.AUTO_DATABASE_NAME, "", autoTempValues);
                if(sw.isChecked())
                    autoTempValues.put(DatabaseHelper.KEY_MESSAGE, "dimmable light turn on" );
                else
                    autoTempValues.put(DatabaseHelper.KEY_MESSAGE, "dimmable light turn off" );
                autoTempValues.put(DatabaseHelper.AUTO_COLUMN_NAME,"dimlightTurnOff");
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
                Toast.makeText(AutomobileLights.this, "Submit successfully", Toast.LENGTH_LONG).show();
            }
        });
    }
}
