package com.teamwork.final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HouseListView extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private String[] names = new String[]{"Garage", "House Temperature", "Outside Weather"};
    private int[] imgIds = new int[]{R.mipmap.car, R.mipmap.temp, R.mipmap.weather};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.house_activity_house_list_view);

        Button enterBtn = (Button) findViewById(R.id.houseBackMain);
        enterBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HouseListView.this, MainActivity.class);
                startActivityForResult(intent, 5);
            }
        });

        List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> showitem = new HashMap<String, Object>();
            showitem.put("image", imgIds[i]);
            showitem.put("item", names[i]);
            listitem.add(showitem);
        }

        SimpleAdapter myAdapter = new SimpleAdapter(getApplicationContext(), listitem, R.layout.house_activity_house_list_item, new String[]{"image", "item"}, new int[]{R.id.imgtou, R.id.name});
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(this);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 5) {
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapter, View arg1, int position, long arg3) {
        switch( position )
        {
            case 0:  Intent newActivity = new Intent(this, HouseGarage.class);
                startActivity(newActivity);
                break;
            case 1:  Intent newActivity1 = new Intent(this, HouseTemperature.class);
                startActivity(newActivity1);
                break;
            case 2:  Intent newActivity2 = new Intent(this, HouseWeather.class);
                startActivity(newActivity2);
                break;
        }
    }

}
