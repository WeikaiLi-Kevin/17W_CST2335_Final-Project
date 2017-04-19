package com.teamwork.final_project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Di Yuan on 18-Apr-17.
 */

public class HouseListView extends AppCompatActivity implements AdapterView.OnItemClickListener{

    boolean isTablet;
    House_WeatherFragment houseWeatherFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.house_list_view);

        // create a toolbar with a item for help
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
//        isTablet = (findViewById(R.id.fragmentHolder) != null);
        // create the String array for store the word list of activities
        String[] names = new String[]{ getString(R.string.Garage), getString(R.string.HouseTem), getString(R.string.OutsideWeather)};

        // create the int array for store the image of logo
        int[] imgIds = new int[]{R.mipmap.car, R.mipmap.temp, R.mipmap.weather};

        // create the arrayList for hold String array and int array together
        List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> showitem = new HashMap<String, Object>();
            showitem.put("image", imgIds[i]);
            showitem.put("item", names[i]);
            listitem.add(showitem);
        }

        SimpleAdapter myAdapter = new SimpleAdapter(getApplicationContext(), listitem, R.layout.house_activity_house_list_item, new String[]{"image", "item"}, new int[]{R.id.imgtou, R.id.name});

        // create the listView
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(this);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 5) {
        }
    }

    // create the onItemClick adapter to switch activities
    @Override
    public void onItemClick(AdapterView<?> adapter, View arg1, int position, long arg3) {
        switch (position) {
            // when click to the first item in the listView, go to HouseGarage activity
            case 0:
                // create alert dialog to ask if want to go to HouseGarage activity
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);

                    builder.setTitle(getString(R.string.Ask));
                    builder.setPositiveButton(getString(R.string.OK), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(HouseListView.this, HouseGarage.class);
                            startActivityForResult(intent, 7);
                        }
                    });
                    builder.setNegativeButton(getString(R.string.Cancel), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();

                // create a toast when switch to HouseGarage activity
                Toast.makeText(HouseListView.this, getString(R.string.GotoGarage), Toast.LENGTH_LONG).show();
                break;

            // when click to the second item in the listView, go to House_Database activity
            case 1:
                Intent newActivity1 = new Intent(this, House_Database.class);
                startActivity(newActivity1);
                // create a toast when switch to House_Database activity
                Toast.makeText(HouseListView.this, getString(R.string.SetHouseTemperature), Toast.LENGTH_LONG).show();
                break;

            // when click to the third item in the listView, go to HouseWeather activity
            case 2:

                isTablet = (findViewById(R.id.fragmentHolder) != null);

                if (isTablet) {
                    houseWeatherFragment = new House_WeatherFragment(HouseListView.this);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHolder, houseWeatherFragment).commit();

                } else {
                    Intent intent = new Intent(HouseListView.this, HouseWeather.class);
                    startActivityForResult(intent, 7);
                   break;
              }
        }
    }
    // create toolbar by inflating it from xml file
    public boolean onCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.house_toolbar_menu, m );
        return true;
    }

    // create a toolbar with a item for help
    public boolean onOptionsItemSelected (MenuItem mi){
        switch(mi.getItemId()){
            // click item pop out instruction
            case R.id.help:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle( getString(R.string.HouseHelp) );
                builder.setMessage( getString(R.string.HouseHelp1) +
                          "\n\n" + getString(R.string.HouseHelp2) +
                          "\n\n" + getString(R.string.HouseHelp3) +
                          "\n\n" + getString(R.string.HouseHelp4) +
                          "\n\n" + getString(R.string.HouseHelp5) +
                          "\n\n" + getString(R.string.HouseHelp6) );

                builder.setPositiveButton( getString(R.string.HouseHelpOK), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                // snackBar
                Snackbar.make(findViewById(android.R.id.content), getString(R.string.SnackBar), Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                    }
                });
                // create the alertDialog
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
        }
        return true;
    }

}
