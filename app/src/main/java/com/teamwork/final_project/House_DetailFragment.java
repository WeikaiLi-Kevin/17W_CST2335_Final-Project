package com.teamwork.final_project;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class House_DetailFragment extends Fragment {
    Context parent;
    Long id;

    @Override
    public void onCreate(Bundle b)
    {
        super.onCreate(b);
//        Bundle bun = getArguments();
//        id = bun.getLong("ID");

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


        parent = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View gui = inflater.inflate(R.layout.house_fragement, null);

        Button fragmentBtn = (Button) gui.findViewById(R.id.fragment);
        fragmentBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HouseListView.class);
                startActivityForResult(intent, 5);
            }
        });

        TextView tv = (TextView)gui.findViewById(R.id.tv);
        //tv.setText("You clicked on ID:" + id);
        return gui;
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 5) {
        }
    }
}