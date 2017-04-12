package com.teamwork.final_project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HouseListView extends AppCompatActivity implements AdapterView.OnItemClickListener{

    String snackbarMessage = "Done!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.house_activity_house_list_view);

        //String[] names = new String[]{ "Garage", "House Temperature", "Outside Weather"};
        String[] names = new String[]{ getString(R.string.Garage), getString(R.string.HouseTem), getString(R.string.OutsideWeather)};

        int[] imgIds = new int[]{R.mipmap.car, R.mipmap.temp, R.mipmap.weather};


        Button enterBtn = (Button) findViewById(R.id.testToolbar);
        enterBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HouseListView.this, House_Toolbar.class);
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
                // Step 9 Toast
                Toast.makeText(HouseListView.this, getString(R.string.GotoGarage), Toast.LENGTH_LONG).show();
                break;

            case 1:  Intent newActivity1 = new Intent(this, House_Database.class);
                startActivity(newActivity1);
                // Step 9 Toast
                Toast.makeText(HouseListView.this, getString(R.string.SetHouseTemperature), Toast.LENGTH_LONG).show();
                break;

            case 2:
                // Step 9 dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle( getString(R.string.Ask) );
                builder.setPositiveButton(getString(R.string.OK), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(HouseListView.this, HouseWeather.class);
                        startActivityForResult(intent, 7);
                    }
                });
                builder.setNegativeButton(getString(R.string.Cancle), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
        }
    }

}
