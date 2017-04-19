package com.teamwork.final_project;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class HouseGarage extends AppCompatActivity {

    private Context mContext;
    Switch doorSwitch, lightSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.house_activity_house_garage);

        Button backBtn = (Button) findViewById(R.id.garage);
        backBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HouseGarage.this, HouseListView.class);
                startActivityForResult(intent, 5);
            }
        });

        mContext = HouseGarage.this;
        Button toast = (Button) findViewById(R.id.btn_toast);
        toast.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                midToast(" Your Garage is ready ",Toast.LENGTH_SHORT);
            }
        });

        // Garage Door Control
        doorSwitch =(Switch) findViewById(R.id.doorSwitch);
        doorSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    midToast(" Open the garage door ",Toast.LENGTH_SHORT);
                } else {
                    midToast(" Close the garage door ",Toast.LENGTH_SHORT);
                }
            }
        });

        // Light Control
        lightSwitch =(Switch) findViewById(R.id.lightSwitch);
        lightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lightToast(getString(R.string.TurnOn),Toast.LENGTH_SHORT);
                } else {
                    lightToast(getString(R.string.TurnOff),Toast.LENGTH_SHORT);
                }
            }
        });

    }

    private void midToast(String str, int showTime)
    {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.house_activity_toast,
                (ViewGroup) findViewById(R.id.lly_toast));
        ImageView img_logo = (ImageView) view.findViewById(R.id.img_logo);
        img_logo.setImageResource(R.mipmap.door);
        TextView tv_msg = (TextView) view.findViewById(R.id.tv_msg);
        tv_msg.setText(str);
        Toast toast = new Toast(mContext);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }

    private void lightToast(String str, int showTime)
    {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.house_activity_toast,
                (ViewGroup) findViewById(R.id.lly_toast));
        ImageView img_logo = (ImageView) view.findViewById(R.id.img_logo);
        img_logo.setImageResource(R.mipmap.light);
        TextView tv_msg = (TextView) view.findViewById(R.id.tv_msg);
        tv_msg.setText(str);
        Toast toast = new Toast(mContext);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 5) {
        }
    }


}
