package com.teamwork.final_project;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.teamwork.final_project.DatabaseHelper.AUTO_COLUMN_NAME;
import static com.teamwork.final_project.DatabaseHelper.KEY_MESSAGE;
//import static com.teamwork.final_project.MainActivity.ACTIVITY_NAME;

/**
 * Created by Li Weikai on 2017-04-03.
 */

public class AutoSettingsListView extends AppCompatActivity {

    public ArrayList<String> autoArrayList = new ArrayList<>(); // store the auto information of the database
    public ArrayList<String> autoArrayType = new ArrayList<>();  // store the auto type information of the database

    public ArrayList<Long> autoArrayId = new ArrayList<>();// store the auto id information of the database

    Cursor results;
    autoAdapter myAutoAdapter;
    SQLiteDatabase autoDB;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auto_settings_listview);
         lv = (ListView) findViewById(R.id.autoSettingListView);
         myAutoAdapter = new autoAdapter(this);
        lv.setAdapter(myAutoAdapter);
        DatabaseHelper autodbHelper = new DatabaseHelper(this);
        autoDB = autodbHelper.getWritableDatabase();

        results = autoDB.query(false,DatabaseHelper.AUTO_DATABASE_NAME,new String[]{DatabaseHelper.KEY_ID, AUTO_COLUMN_NAME,DatabaseHelper.KEY_MESSAGE},null,
                null,null,null,null,null
        );
        //int rows = results.getCount();
        int messageIndex = results.getColumnIndex(DatabaseHelper.KEY_MESSAGE);
        int messageType = results.getColumnIndex(AUTO_COLUMN_NAME);
        results.moveToFirst();
        while(!results.isAfterLast()) {
            autoArrayList.add(results.getString(messageIndex));
            autoArrayType.add(results.getString(messageType));
            autoArrayId.add(results.getLong(results.getColumnIndex(DatabaseHelper.KEY_ID)));

            myAutoAdapter.notifyDataSetChanged();
//            Log.i(ACTIVITY_NAME,"SQL MESSAGE:"+results.getString(messageIndex));
//            Log.i(ACTIVITY_NAME,"Cursor's column count =" + results.getColumnCount()) ;
            results.moveToNext();
        }
        for(int num = 0 ;num<results.getColumnCount();num++)
//            Log.i(ACTIVITY_NAME,results.getColumnName( num));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("ListView", "onItemClick: " + i + " " + l);
Log.d("ArrayList",autoArrayType.get(i)+"");

                Bundle bun = new Bundle();
                bun.putLong("ID", l );//l is the database ID of selected item
                bun.putString("Message",autoArrayList.get(i));

                //step 2, if a tablet, insert fragment into FrameLayout, pass data
                if(autoArrayType.get(i).equals("new station number")||autoArrayType.get(i).equals("radio station 1")||autoArrayType.get(i).equals("radio station 2")||autoArrayType.get(i).equals("radio station 3")||autoArrayType.get(i).equals("radio station 4")
                        ||autoArrayType.get(i).equals("radio station 5")||autoArrayType.get(i).equals("radio station 6")) {
                    Intent intnt11 = new Intent(AutoSettingsListView.this, AutoListViewDetails.class);
                   // intnt.putExtras(bun);//("ID" , l); //pass the Database ID to next activity
                     intnt11.putExtra("station number",autoArrayType.get(i));
                      intnt11.putExtra("radio number",autoArrayList.get(i));
                    intnt11.putExtra("id",autoArrayId.get(i));
                    startActivityForResult(intnt11,0); //go to view fragment details
                }
            }
        });
    }
    protected  void  onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode==1) {
            String stationNum = data.getExtras().get("deleteStationNum").toString();//得到新Activity 关闭后返回的数据
            String radioNum = data.getExtras().get("deleteRadioNum").toString();
            Long deleteId = data.getExtras().getLong("id");
           // Log.i("result", "" + result);
            // final ChatAdapter messageAdapter = new ChatAdapter(this);
            //lv.setAdapter(messageAdapter);
            deleteId(deleteId);
        }
        else if (resultCode==2){
            String stationNum = data.getExtras().get("updateStationNum").toString();//得到新Activity 关闭后返回的数据
            String radioNum = data.getExtras().get("updateRadioNum").toString();
            Long deleteId = data.getExtras().getLong("id");
            // Log.i("result", "" + result);
            // final ChatAdapter messageAdapter = new ChatAdapter(this);
            //lv.setAdapter(messageAdapter);
            updateId(deleteId, stationNum, radioNum);
        }
        else if (resultCode==7){
            String stationNum = data.getExtras().get("addStationNum").toString();//得到新Activity 关闭后返回的数据
            String radioNum = data.getExtras().get("addRadioNum").toString();
           // Long deleteId = data.getExtras().getLong("id");
             Log.i("result add station", "" + stationNum);
            Log.i("result add station", "" + radioNum);

            // final ChatAdapter messageAdapter = new ChatAdapter(this);
            //lv.setAdapter(messageAdapter);
            addId(stationNum, radioNum);

        }
    }
    public void addId(String columnName, String message){


        ContentValues autoTempValues = new ContentValues();

        autoTempValues.put(KEY_MESSAGE,message);
        autoTempValues.put(AUTO_COLUMN_NAME,columnName);
                // arrayId.add(
        autoDB.insert(DatabaseHelper.AUTO_DATABASE_NAME, null, autoTempValues);
        autoArrayId.clear();
        autoArrayList.clear();
        autoArrayType.clear();


        results = autoDB.query(false,DatabaseHelper.AUTO_DATABASE_NAME,new String[]{DatabaseHelper.KEY_ID, AUTO_COLUMN_NAME,DatabaseHelper.KEY_MESSAGE},null,
                null,null,null,null,null
        );
        //int rows = results.getCount();
        int messageIndex = results.getColumnIndex(DatabaseHelper.KEY_MESSAGE);
        int messageType = results.getColumnIndex(AUTO_COLUMN_NAME);
        results.moveToFirst();
        // arrayId.clear();
        //int i = 0;
        while(!results.isAfterLast()) {
            autoArrayList.add(results.getString(messageIndex));
            autoArrayType.add(results.getString(messageType));
            autoArrayId.add(results.getLong(results.getColumnIndex(DatabaseHelper.KEY_ID)));

            myAutoAdapter.notifyDataSetChanged();
          //  Log.i(ACTIVITY_NAME,"SQL MESSAGE:"+results.getString(messageIndex));
          //  Log.i(ACTIVITY_NAME,"Cursor's column count =" + results.getColumnCount()) ;
            results.moveToNext();
        }


        myAutoAdapter.notifyDataSetChanged();

    }
    public void deleteId(Long id)
    {

        autoDB.delete(DatabaseHelper.AUTO_DATABASE_NAME, "_id=?", new String[] { Long.toString(id)});
        autoArrayId.clear();
        autoArrayList.clear();
        autoArrayType.clear();


        results = autoDB.query(false,DatabaseHelper.AUTO_DATABASE_NAME,new String[]{DatabaseHelper.KEY_ID, AUTO_COLUMN_NAME,DatabaseHelper.KEY_MESSAGE},null,
                null,null,null,null,null
        );
        //int rows = results.getCount();
        int messageIndex = results.getColumnIndex(DatabaseHelper.KEY_MESSAGE);
        int messageType = results.getColumnIndex(AUTO_COLUMN_NAME);
        results.moveToFirst();
        // arrayId.clear();
        //int i = 0;
        while(!results.isAfterLast()) {
            autoArrayList.add(results.getString(messageIndex));
            autoArrayType.add(results.getString(messageType));
            autoArrayId.add(results.getLong(results.getColumnIndex(DatabaseHelper.KEY_ID)));

            myAutoAdapter.notifyDataSetChanged();
           // Log.i(ACTIVITY_NAME,"SQL MESSAGE:"+results.getString(messageIndex));
          //  Log.i(ACTIVITY_NAME,"Cursor's column count =" + results.getColumnCount()) ;
            results.moveToNext();
        }


        myAutoAdapter.notifyDataSetChanged();

    }
    public void updateId(Long id, String columnName, String message)
    {

       // autoDB.delete(DatabaseHelper.AUTO_DATABASE_NAME, "_id=?", new String[] { Long.toString(id)});

        ContentValues values = new ContentValues();
        values.put(AUTO_COLUMN_NAME, columnName);
        values.put(KEY_MESSAGE, message);
         autoDB.update(DatabaseHelper.AUTO_DATABASE_NAME, values, "_id = " + id, null);
       // database.update(TABLE_NAME, values, "_id = " + id, null);
        autoArrayId.clear();
        autoArrayList.clear();
        autoArrayType.clear();


        results = autoDB.query(false,DatabaseHelper.AUTO_DATABASE_NAME,new String[]{DatabaseHelper.KEY_ID, AUTO_COLUMN_NAME,DatabaseHelper.KEY_MESSAGE},null,
                null,null,null,null,null
        );
        //int rows = results.getCount();
        int messageIndex = results.getColumnIndex(DatabaseHelper.KEY_MESSAGE);
        int messageType = results.getColumnIndex(AUTO_COLUMN_NAME);
        results.moveToFirst();
        // arrayId.clear();
        //int i = 0;
        while(!results.isAfterLast()) {
            autoArrayList.add(results.getString(messageIndex));
            autoArrayType.add(results.getString(messageType));
            autoArrayId.add(results.getLong(results.getColumnIndex(DatabaseHelper.KEY_ID)));

            myAutoAdapter.notifyDataSetChanged();
            //Log.i(ACTIVITY_NAME,"SQL MESSAGE:"+results.getString(messageIndex));
           // Log.i(ACTIVITY_NAME,"Cursor's column count =" + results.getColumnCount()) ;
            results.moveToNext();
        }


        myAutoAdapter.notifyDataSetChanged();

    }
    private class autoAdapter extends ArrayAdapter<String> {
        private autoAdapter(Context cont){
            super(cont, 0);
        }
        public int getCount(){
            return autoArrayList.size();
        }

        public long getItemId(int pos)
        {
            return autoArrayId.get(pos);
        }

        public String getItem(int position){
            return autoArrayList.get(position);
        }
        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = AutoSettingsListView.this.getLayoutInflater();
            View result = null;
            TextView message ;
Log.d("Position",position+"");
            switch(autoArrayType.get(position)){
                case "temperature":
                    result = inflater.inflate(R.layout.auto_row_temperature, null);
                    message = (TextView) result.findViewById(R.id.autoTempText);
                    message.setText(getItem(position));
                    break;
                case "lightTurnOff":
                    result = inflater.inflate(R.layout.auto_row_lightsoff, null);
                    message = (TextView) result.findViewById(R.id.autoLightOffText);
                    message.setText(getItem(position));

                    break;
                case "lightNormal":
                    result = inflater.inflate(R.layout.auto_row_lightnormal, null);
                    message = (TextView) result.findViewById(R.id.autoLightNormalText);
                    message.setText(getItem(position));

                    break;
                case "lightHigh":
                    result = inflater.inflate(R.layout.auto_row_lightson, null);
                    message = (TextView) result.findViewById(R.id.autoLightOnText);
                    message.setText(getItem(position));

                    break;
                case "dimlightTurnOff":
                    result = inflater.inflate(R.layout.auto_row_lightdimmable, null);
                    message = (TextView) result.findViewById(R.id.autoLightDimText);
                    message.setText(getItem(position));
                    break;

                case "radio station 1":
                    result = inflater.inflate(R.layout.auto_row_radio, null);
                    message = (TextView) result.findViewById(R.id.autoRadioText);
                    message.setText("radio station 1: "+getItem(position));
                    break;
                case "radio station 2":
                    result = inflater.inflate(R.layout.auto_row_radio, null);
                    message = (TextView) result.findViewById(R.id.autoRadioText);
                    message.setText("radio station 2: "+getItem(position));
                    break;
                case "radio station 3":
                    result = inflater.inflate(R.layout.auto_row_radio, null);
                    message = (TextView) result.findViewById(R.id.autoRadioText);
                    message.setText("radio station 3: "+getItem(position));
                    break;
                case "radio station 4":
                    result = inflater.inflate(R.layout.auto_row_radio, null);
                    message = (TextView) result.findViewById(R.id.autoRadioText);
                    message.setText("radio station 4: "+getItem(position));
                    break;
                case "radio station 5":
                    result = inflater.inflate(R.layout.auto_row_radio, null);
                    message = (TextView) result.findViewById(R.id.autoRadioText);
                    message.setText("radio station 5: "+getItem(position));
                    break;
                case "radio station 6":
                    result = inflater.inflate(R.layout.auto_row_radio, null);
                    message = (TextView) result.findViewById(R.id.autoRadioText);
                    message.setText("radio station 6: "+getItem(position));
                    break;
                case "new station number":
                    result = inflater.inflate(R.layout.auto_row_radio, null);
                    message = (TextView) result.findViewById(R.id.autoRadioText);
                    message.setText("new radio station: "+getItem(position));

                default: break;
            }


            return result;
        }
    }
}
