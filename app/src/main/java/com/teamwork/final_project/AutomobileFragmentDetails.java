package com.teamwork.final_project;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static com.teamwork.final_project.R.id.autoListView;

public class AutomobileFragmentDetails extends Activity {

    //Immediately create the fragment and insert it in the framelayout

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

setContentView(R.layout.activity_automobile_fragment_details);
        //Step 3, create fragment onCreation, pass data from Intent Extras to FragmentTransction

        AutomobileFragment frag = new AutomobileFragment();
        Bundle bun =        getIntent().getExtras();
        //Log.i("GetInformation",""+getIntent().getExtras());
        frag.setArguments( bun );
        getFragmentManager().beginTransaction().add(R.id.fragmentHolder, frag).commit();


    }



}
