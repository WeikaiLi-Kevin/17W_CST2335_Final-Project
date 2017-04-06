package com.teamwork.final_project;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Li Weikai on 2017-04-03.
 */

public class AutomobileFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);



    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View gui = inflater.inflate(R.layout.activity_automobile_fragment, null);

        ListView AutoList = (ListView)gui.findViewById(R.id.autoListView);
        AutoList.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_expandable_list_item_1,getSet()));

        AutoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("ListView", "onItemClick: " + i + " " + l);
                Bundle bun = new Bundle();
                int arrayNum = i;
                Intent intnt = new Intent();
                switch (arrayNum) {
                    case 0:
                        intnt = new Intent(getActivity(),AutomobileTemperature.class);
                        break;
                    case 1:
                        intnt = new Intent(getActivity(),AutomobileRadio.class);
                        break;
                    case 2:
                        Uri uri = Uri.parse("geo:45.351040, -75.755904");
                        intnt = new Intent(Intent.ACTION_VIEW,uri );
                        intnt.setPackage("com.google.android.apps.maps");
                        break;
                    case 3:
                        intnt = new Intent(getActivity(),AutomobileLights.class);
                        break;
                    case 4:
                        intnt = new Intent(getActivity(),AutoSettingsListView.class);
                    default: break;
                }
                //intnt.putExtras(bun);//("ID" , l); //pass the Database ID to next activity
                // intnt.putExtra("Message",arrayList.get(i));
                //  intnt.putExtra("I",i);
                startActivity(intnt);
            }
        });
        return gui;
    }

    private List<String> getSet(){
        ArrayList<String> set = new ArrayList<String>();
        set.add(getString(R.string.autoTemperature));
        set.add(getString(R.string.autoRadioControls));
        set.add(getString(R.string.autoGPSDirections));
        set.add(getString(R.string.autoLights));
        set.add(getString(R.string.autoSettingsHistory));
        return set;
    }



}