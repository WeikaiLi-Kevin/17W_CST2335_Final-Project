package com.teamwork.final_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class kitchenFridge extends AppCompatActivity {

    protected TextView fridge_current;
    protected TextView freezer_current;
    protected Spinner spinner_fridge;
    protected Spinner spinner_freezer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kitchen_activity_fridge);
        spinner_fridge = (Spinner)findViewById(R.id.fridge_change);
        spinner_freezer = (Spinner)findViewById(R.id.freezer_change);
        fridge_current = (TextView)findViewById(R.id.fridge);
        freezer_current = (TextView)findViewById(R.id.freezer);


        spinner_fridge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String set1 = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "kitchenFridge temperature set to " + set1, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_freezer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String set2 = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Freezer temperature set to " + set2, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayList<String> temperature_fridge = new ArrayList<>();
        temperature_fridge.add("0");
        temperature_fridge.add("2");
        temperature_fridge.add("4");
        temperature_fridge.add("6");

        ArrayAdapter<String> adapter_fridge = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, temperature_fridge);
        adapter_fridge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_fridge.setAdapter(adapter_fridge);



        ArrayList<String> temperature_freezer = new ArrayList<>();
        temperature_freezer.add("-24");
        temperature_freezer.add("-22");
        temperature_freezer.add("-20");
        temperature_freezer.add("-18");
        temperature_freezer.add("-16");
        temperature_freezer.add("-14");
        temperature_freezer.add("-12");

        ArrayAdapter<String> adapter_freezer = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, temperature_freezer);
        adapter_freezer.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_freezer.setAdapter(adapter_freezer);

        fridge_current.setText("kitchenFridge current temperature: ");
        freezer_current.setText("Freezer current temperature: ");

    }

}
