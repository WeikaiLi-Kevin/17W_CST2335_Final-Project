package com.teamwork.final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.teamwork.final_project.R.id.automobile;


public class MainActivity extends AppCompatActivity {

    Button b_kitchen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        b_kitchen = (Button)findViewById(R.id.kitchen);
        b_kitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, kitchen_main.class);
                startActivity(intent);
            }
        });


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
