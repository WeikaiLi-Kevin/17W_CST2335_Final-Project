package com.teamwork.final_project;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Di Yuan on 18-Apr-17.
 */

public class HouseGarage extends AppCompatActivity {

    private Context mContext;
    Switch doorSwitch, lightSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.house_garage);

        // Garage Door Switch Control
        doorSwitch =(Switch) findViewById(R.id.doorSwitch);
        doorSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // create toast when switch is checked
                    Toast.makeText(HouseGarage.this, getString(R.string.DoorOpen), Toast.LENGTH_LONG).show();
                    // When doorSwitch is checked, the lightSwitch will be checked automatically
                    lightSwitch.setChecked(true);
                } else {
                    Toast.makeText(HouseGarage.this, getString(R.string.DoorClose), Toast.LENGTH_LONG).show();
                }
            }
        });

        // Light Switch Control
        lightSwitch =(Switch) findViewById(R.id.lightSwitch);
        lightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // create toast when switch is checked
                    Toast.makeText(HouseGarage.this, getString(R.string.TurnOn), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(HouseGarage.this, getString(R.string.TurnOff), Toast.LENGTH_LONG).show();
                }
            }
        });

    }


}
