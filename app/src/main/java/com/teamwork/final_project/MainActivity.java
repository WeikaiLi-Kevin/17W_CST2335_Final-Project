package com.teamwork.final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.teamwork.final_project.R.id.automobile;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button autoBtn = (Button) findViewById(automobile);
        autoBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                Intent ListAutoItems = new Intent(MainActivity.this,Automobile.class);
                startActivity(ListAutoItems);
            }
        });
    }
}
