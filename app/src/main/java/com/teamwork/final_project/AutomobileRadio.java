package com.teamwork.final_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

//import static com.teamwork.final_project.MainActivity.ACTIVITY_NAME;

public class AutomobileRadio extends AppCompatActivity {
    private SQLiteDatabase autoDB;   //   use to hold all info of automobile database
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automobile_radio);
        final EditText rs1 = (EditText) findViewById(R.id.radioStation1);    //    hold the radio station 1 input by user
        final EditText rs2 = (EditText) findViewById(R.id.radioStation2);//    hold the radio station 2 input by user
        final EditText rs3 = (EditText) findViewById(R.id.radioStation3);//    hold the radio station 3 input by user
        final EditText rs4 = (EditText) findViewById(R.id.radioStation4);//    hold the radio station 4 input by user
        final EditText rs5 = (EditText) findViewById(R.id.radioStation5);//    hold the radio station 5 input by user
        final EditText rs6 = (EditText) findViewById(R.id.radioStation6);//    hold the radio station 6 input by user

        Button btn = (Button) findViewById(R.id.radioBtn);

        DatabaseHelper autodbHelper = new DatabaseHelper(this);
        autoDB = autodbHelper.getWritableDatabase();
        final ContentValues autoTempValues = new ContentValues();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // insert all radio stations into database
                autoTempValues.put(DatabaseHelper.KEY_MESSAGE,rs1.getText().toString() );
                autoTempValues.put(DatabaseHelper.AUTO_COLUMN_NAME,"radio station 1");
                autoDB.insert(DatabaseHelper.AUTO_DATABASE_NAME, "", autoTempValues);

                autoTempValues.put(DatabaseHelper.KEY_MESSAGE,rs2.getText().toString() );
                autoTempValues.put(DatabaseHelper.AUTO_COLUMN_NAME,"radio station 2");
                autoDB.insert(DatabaseHelper.AUTO_DATABASE_NAME, "", autoTempValues);

                autoTempValues.put(DatabaseHelper.KEY_MESSAGE,rs3.getText().toString() );
                autoTempValues.put(DatabaseHelper.AUTO_COLUMN_NAME,"radio station 3");
                autoDB.insert(DatabaseHelper.AUTO_DATABASE_NAME, "", autoTempValues);

                autoTempValues.put(DatabaseHelper.KEY_MESSAGE,rs4.getText().toString() );
                autoTempValues.put(DatabaseHelper.AUTO_COLUMN_NAME,"radio station 4");
                autoDB.insert(DatabaseHelper.AUTO_DATABASE_NAME, "", autoTempValues);

                autoTempValues.put(DatabaseHelper.KEY_MESSAGE,rs5.getText().toString() );
                autoTempValues.put(DatabaseHelper.AUTO_COLUMN_NAME,"radio station 5");
                autoDB.insert(DatabaseHelper.AUTO_DATABASE_NAME, "", autoTempValues);

                autoTempValues.put(DatabaseHelper.KEY_MESSAGE,rs6.getText().toString() );
                autoTempValues.put(DatabaseHelper.AUTO_COLUMN_NAME,"radio station 6");
                autoDB.insert(DatabaseHelper.AUTO_DATABASE_NAME, "", autoTempValues);
                //et.setText("");
                Cursor results = autoDB.query(false,DatabaseHelper.AUTO_DATABASE_NAME,new String[]{DatabaseHelper.KEY_ID,DatabaseHelper.AUTO_COLUMN_NAME,DatabaseHelper.KEY_MESSAGE},null,
                        null,null,null,null,null
                );
                //int rows = results.getCount();
                int messageIndex = results.getColumnIndex(DatabaseHelper.KEY_MESSAGE);
                results.moveToFirst();
                while(!results.isAfterLast()) {
          //          Log.i(ACTIVITY_NAME,"SQL MESSAGE:"+results.getString(messageIndex));
          //          Log.i(ACTIVITY_NAME,"Cursor's column count =" + results.getColumnCount()) ;
                    results.moveToNext();
                }
                for(int num = 0 ;num<results.getColumnCount();num++)
           //         Log.i(ACTIVITY_NAME,results.getColumnName( num));
                Toast.makeText(AutomobileRadio.this, getString(R.string.autoSubmitSuccessful), Toast.LENGTH_LONG).show();
            }
        });
    }
}
