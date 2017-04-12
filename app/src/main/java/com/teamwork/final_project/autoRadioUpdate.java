package com.teamwork.final_project;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;


public class autoRadioUpdate extends AppCompatActivity {
    String stationNum, radioNum;
    Long deleteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_radio_update);
        final EditText ed = (EditText)findViewById(R.id.autoRadioUpdateETNew);
        Button btn = (Button)findViewById(R.id.autoRadioUpdateBtnNew);
        final ProgressBar pb = (ProgressBar)findViewById(R.id.autoProgressBar);
        stationNum =  getIntent().getExtras().get("updateStationNum").toString();//得到新Activity 关闭后返回的数据
        radioNum =  getIntent().getExtras().get("updateRadioNum").toString();
        deleteId =  getIntent().getExtras().getLong("id");
        Log.i("update get result stati", "" + stationNum);
        Log.i("update get result radio",radioNum);
        Log.i("update id",deleteId+"");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent data = new Intent();
                data.putExtra("updateStationNum", stationNum);
                data.putExtra("updateRadioNum",ed.getText().toString());
                Log.i("after update", "" + ed.getText().toString());
                data.putExtra("id",deleteId);
                //Log.i("delete", "" + id);
                setResult(5, data);
                pb.setVisibility(View.VISIBLE);
                for(int i = 0;i<100;i++)
                pb.setProgress(i);

                Snackbar.make(v, getString(R.string.autoUpdateSuccessful), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

               // finish();


            }
        });
        ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pb.setVisibility(View.INVISIBLE);


            }
        });
    }

}
