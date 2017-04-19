package com.teamwork.final_project;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

/**
 * Created by Di Yuan on 18-Apr-17.
 */

public class House_Fragment extends Fragment {
    HouseListView houseListView;

    public House_Fragment(){}
    public House_Fragment(HouseListView house){
        houseListView = house;
    }

    // the data is in the getInt
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        Bundle bun = new Bundle();
        int time = bun.getInt("Time");
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View gui = inflater.inflate(R.layout.house_database, null);
        return gui;

    }


}
