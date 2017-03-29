package com.teamwork.final_project;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static com.teamwork.final_project.R.id.autoListView;
import static com.teamwork.final_project.R.id.list_item;

public class Automobile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automobile);
        ListView AutoList = (ListView)findViewById(autoListView);
        AutoList.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,getSet()));

        AutoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("ListView", "onItemClick: " + i + " " + l);
                Bundle bun = new Bundle();
                int arrayNum = i;
                Intent intnt = new Intent();
                switch (arrayNum) {
                    case 0:
                         intnt = new Intent(Automobile.this,AutomobileTemperature.class);
                        break;
                    case 1:
                        intnt = new Intent(Automobile.this,AutomobileRadio.class);
                        break;
                    case 2:
                        Uri uri = Uri.parse("geo:45.351040, -75.755904");
                       intnt = new Intent(Intent.ACTION_VIEW,uri );
                        intnt.setPackage("com.google.android.apps.maps");
                        break;
                    case 3:
                        intnt = new Intent(Automobile.this,AutomobileLights.class);
                        break;
                    default: break;
                }
                //intnt.putExtras(bun);//("ID" , l); //pass the Database ID to next activity
                // intnt.putExtra("Message",arrayList.get(i));
                //  intnt.putExtra("I",i);
                startActivity(intnt);
            }
        });
    }
    private List<String> getSet(){
        ArrayList<String> set = new ArrayList<String>();
        set.add("Temperature");
        set.add("Radio Controls");
        set.add("GPS directions");
        set.add("Lights");
        return set;
    }
}
