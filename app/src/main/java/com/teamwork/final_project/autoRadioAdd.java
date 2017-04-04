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
                //stationNum =  getIntent().getExtras().get("updateStationNum").toString();//得到新Activity 关闭后返回的数据
                //radioNum =  getIntent().getExtras().get("updateRadioNum").toString();
                //deleteId =  getIntent().getExtras().getLong("id");
                //Log.i("update get result stati", "" + stationNum);
                //Log.i("update get result radio",radioNum);
                //Log.i("update id",deleteId+"");
                Intent data = new Intent();
               data.putExtra("addStationNum", "new station number");
                data.putExtra("addRadioNum",ed.getText().toString());
                Log.i("after update", "" + ed.getText().toString());
                //data.putExtra("id",deleteId);
                //Log.i("delete", "" + id);
                setResult(6, data);
                finish();


            }
        });
    }
}
