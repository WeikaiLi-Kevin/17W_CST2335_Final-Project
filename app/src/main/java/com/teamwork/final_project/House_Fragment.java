package com.teamwork.final_project;

import android.app.Activity;
import android.os.Bundle;


public class House_Fragment extends Activity {


    //Immediately create the fragment and insert it in the framelayout
    @Override
    public void onCreate(Bundle b)
    {
        super.onCreate(b);
        setContentView(R.layout.house_detail_fragement);

        House_DetailFragment frag = new House_DetailFragment();
        Bundle bun =        getIntent().getExtras();
        frag.setArguments( bun );
        getFragmentManager().beginTransaction().add(R.id.fragmentHolder, frag).commit();



    }



}