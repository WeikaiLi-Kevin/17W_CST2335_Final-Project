package com.teamwork.final_project;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.button;

/**
 * Created by Di Yuan on 18-Apr-17.
 */

public class House_Database extends ListActivity implements OnItemClickListener {
    House_SQLiteHelper db = new House_SQLiteHelper(this);
    List<House_Temperature> list;
    ArrayAdapter<String> myAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.house_database);

        // drop this database if already exists
        db.onUpgrade(db.getWritableDatabase(), 1, 2);
        // create a database
        // db.createTime(new House_Temperature("Scheduled Time", "House_Temperature")
        db.createTime(new House_Temperature(" 6:00", " 20℃"));
        db.createTime(new House_Temperature(" 9:00", " 16℃"));
        db.createTime(new House_Temperature(" 16:00", " 20℃"));
        db.createTime(new House_Temperature(" 22:00", " 18℃"));
        db.createTime(new House_Temperature(" Click to add", " Set temperature"));
        db.createTime(new House_Temperature(" Click to add", " Set temperature"));

        // get all time
        list = db.getAllTime();
        List<String> listTitle = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            listTitle.add(i, list.get(i).getTime());
        }
        myAdapter = new ArrayAdapter<String>(this, R.layout.house_row_layout, R.id.listText, listTitle);
        getListView().setOnItemClickListener(this);
        setListAdapter(myAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // start House_TemperatureActivity with extras the time id
        Intent intent = new Intent(this, House_TemperatureActivity.class);
        intent.putExtra("time", list.get(arg2).getId());
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // get all time again, some data changed
        list = db.getAllTime();
        List<String> listTitle = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            listTitle.add(i, list.get(i).getTime());
        }
        myAdapter = new ArrayAdapter<String>(this, R.layout.house_row_layout, R.id.listText, listTitle);
        getListView().setOnItemClickListener(this);
        setListAdapter(myAdapter);
    }
}
