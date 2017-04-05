package com.teamwork.final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.view.View;
import android.widget.Button;

import static com.teamwork.final_project.R.id.automobile;



public class MainActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "MainActivity";

    Button b_kitchen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button houseBtn = (Button) findViewById(R.id.house);
        houseBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HouseListView.class);
                startActivityForResult(intent, 5);
            }
        });

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
              //  Intent ListAutoItems = new Intent(MainActivity.this,Automobile.class);
              //  startActivity(ListAutoItems);

                Intent intnt = new Intent(MainActivity.this, AutomobileFragmentDetails.class);
               // intnt.putExtras(bun);//("ID" , l); //pass the Database ID to next activity
                // intnt.putExtra("Message",arrayList.get(i));
                //  intnt.putExtra("I",i);
                startActivity(intnt); //go to view fragment details

            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 5) {
            Log.i(ACTIVITY_NAME, "Returned to MainActivity.onActivityResult");
        }




    }

}
