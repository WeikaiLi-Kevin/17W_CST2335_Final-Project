package com.teamwork.final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class autoRadioAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_radio_add);
        final EditText ed = (EditText)findViewById(R.id.autoRadioAddETNew);
        Button btn = (Button)findViewById(R.id.autoRadioAddBtnNew);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent data = new Intent();
               data.putExtra("addStationNum", "new station number");  //   pass the station number(new station number) to the database
                data.putExtra("addRadioNum",ed.getText().toString());       // pass the information input by user into database
                Log.i("after update", "" + ed.getText().toString());

                setResult(6, data);   //pass back by matching 6
                finish();


            }
        });
    }
}
