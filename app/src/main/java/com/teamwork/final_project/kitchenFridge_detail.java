package com.teamwork.final_project;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class kitchenFridge_detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_fridge_detail);

        kitchenFridge kf = new kitchenFridge();

        FragmentTransaction ft1 = getFragmentManager().beginTransaction();
        ft1.add(R.id.activity_kitchen_fridge_detail, kf);
        ft1.commit();

    }
}
