package com.teamwork.final_project;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class kitchenFridge extends Fragment {

    protected TextView fridge_current;
    protected TextView freezer_current;
    protected Spinner spinner_fridge;
    protected Spinner spinner_freezer;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.kitchen_activity_fridge, container, false);
        spinner_fridge = (Spinner) view.findViewById(R.id.fridge_change);
        spinner_freezer = (Spinner) view.findViewById(R.id.freezer_change);
        fridge_current = (TextView) view.findViewById(R.id.fridge);
        freezer_current = (TextView) view.findViewById(R.id.freezer);

        ArrayList<String> temperature_fridge = new ArrayList<>();
        temperature_fridge.add("0");
        temperature_fridge.add("2");
        temperature_fridge.add("4");
        temperature_fridge.add("6");

        ArrayAdapter<String> adapter_fridge = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, temperature_fridge);
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

        ArrayAdapter<String> adapter_freezer = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, temperature_freezer);
        adapter_freezer.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_freezer.setAdapter(adapter_freezer);

        String temper = this.getActivity().getIntent().getStringExtra("temper");
        int tm = Integer.valueOf(temper);
        if(tm > 0){
            int spinnerPosition = adapter_fridge.getPosition(temper);
            spinner_fridge.setSelection(spinnerPosition);
        }else{
            int spinnerPosition = adapter_freezer.getPosition(temper);
            spinner_freezer.setSelection(spinnerPosition);
        }

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

        fridge_current.setText("kitchenFridge current temperature: 2");
        freezer_current.setText("Freezer current temperature: -20");

        return view;

    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
}
