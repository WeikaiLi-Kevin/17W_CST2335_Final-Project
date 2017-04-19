package com.teamwork.final_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Di Yuan on 18-Apr-17.
 */

public class House_FragmentDetails extends AppCompatActivity {

    // create the fragment and insert it in the framelayout
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.house_list_view);
        // create fragment onCreation, pass data from Intent Extras to FragmentTransction
        House_Fragment frag = new House_Fragment();
        Bundle bun = getIntent().getExtras();
        frag.setArguments( bun );
    }
}
