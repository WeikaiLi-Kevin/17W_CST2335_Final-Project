package com.teamwork.final_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Di Yuan on 18-Apr-17.
 */

public class House_TemperatureActivity extends Activity {
    TextView scheduledTime;
    TextView houseTemp;
    House_Temperature selectedHouseTemperature;
    House_SQLiteHelper db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.house_temperature);
        scheduledTime = (TextView) findViewById(R.id.time);
        houseTemp = (TextView) findViewById(R.id.temp);

        // get the intent that passed from House_Database
        Intent intent = getIntent();
        int id = intent.getIntExtra("time", -1);
        // open the database of the application context
        db = new House_SQLiteHelper(getApplicationContext());
        // read the time  with id from the database
        selectedHouseTemperature = db.readTime(id);
        initializeViews();
    }

    public void initializeViews() {
        scheduledTime.setText(selectedHouseTemperature.getTime());
        houseTemp.setText(selectedHouseTemperature.getTemp());
    }

    // use for add data
    public void add(View v) {
        Toast.makeText(getApplicationContext(), "This House_Temperature is added.", Toast.LENGTH_SHORT).show();
        selectedHouseTemperature.setTime(((EditText) findViewById(R.id.timeEdit)).getText().toString());
        selectedHouseTemperature.setTemp(((EditText) findViewById(R.id.tempEdit)).getText().toString());
        finish();
    }
    // use for update data
    public void update(View v) {
        Toast.makeText(getApplicationContext(), "This House_Temperature is updated.", Toast.LENGTH_SHORT).show();
        selectedHouseTemperature.setTime(((EditText) findViewById(R.id.timeEdit)).getText().toString());
        selectedHouseTemperature.setTemp(((EditText) findViewById(R.id.tempEdit)).getText().toString());
        // update temp with changes
        db.updateTime(selectedHouseTemperature);
        finish();
    }
    // use for delete data
    public void delete(View v) {
        Toast.makeText(getApplicationContext(), "This House_Temperature is removed.", Toast.LENGTH_SHORT).show();
        // delete selected temp
        db.removeTime(selectedHouseTemperature);
        finish();
    }
}
