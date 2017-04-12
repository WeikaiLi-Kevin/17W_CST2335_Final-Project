package com.teamwork.final_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class AutoListViewDetails extends AppCompatActivity {
    String stationNum;
    String radioNum;
    Long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_list_view_details);
        TextView tv1 = (TextView) findViewById(R.id.autoRadioTextView1);
        TextView tv2 = (TextView) findViewById(R.id.autoRadioTextView2);
        Button delBtn = (Button) findViewById(R.id.autoRadioDelBtn);
        Button updateBtn = (Button) findViewById(R.id.autoRadioUpdateBtn);
        Button addBtn = (Button) findViewById(R.id.autoRadioAddBtn);
        Bundle bun = getIntent().getExtras();
        stationNum = bun.get("station number").toString();
        radioNum = bun.get("radio number").toString();
        id = bun.getLong("id");
        tv1.setText(stationNum);
        tv2.setText(radioNum);
        Log.i("station number",stationNum);
        Log.i("radio number",radioNum);
        Log.i("id",id+"");
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent data = new Intent();
                    data.putExtra("deleteStationNum", stationNum);
                data.putExtra("deleteRadioNum",radioNum);
                data.putExtra("id",id);
                    //Log.i("delete", "" + id);
                   setResult(1, data);
                AlertDialog.Builder builder1 = new AlertDialog.Builder(AutoListViewDetails.this);
                // Get the layout inflater
                LayoutInflater inflater = AutoListViewDetails.this.getLayoutInflater();
                final View inf = inflater.inflate(R.layout.auto_dialog_delete, null);
                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                builder1.setView(inf);
                //builder1.setMessage(getString(R.string.autoDelMessage));
                // Add action buttons
                builder1.setPositiveButton(getString(R.string.autoOK), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Log.i("Dialog","1111");
                        /*
                        EditText tx = (EditText) inf.findViewById(R.id.newMsg) ;
                        setInp(tx.getText().toString());
                        Log.i("dialog",getInp());
                        Snackbar.make(findViewById(R.id.fab),getInp() , Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
*/
                        finish();
                    }
                });
                builder1.setNegativeButton(getString(R.string.autoCancel),new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

                AlertDialog dialog1 = builder1.create();
                dialog1.show();




            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent(AutoListViewDetails.this, autoRadioUpdate.class);
                // intnt.putExtras(bun);//("ID" , l); //pass the Database ID to next activity
                // intnt.putExtra("Message",arrayList.get(i));
                //  intnt.putExtra("I",i);

                data.putExtra("updateStationNum", stationNum);
                data.putExtra("updateRadioNum",radioNum);
                data.putExtra("id",id);
                //Log.i("delete", "" + id);
                //setResult(2, data);
                startActivityForResult(data,5);

               //finish();


            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent data = new Intent(AutoListViewDetails.this, autoRadioAdd.class);
                // intnt.putExtras(bun);//("ID" , l); //pass the Database ID to next activity
                // intnt.putExtra("Message",arrayList.get(i));
                //  intnt.putExtra("I",i);

                data.putExtra("addStationNum", stationNum);
                data.putExtra("addRadioNum",radioNum);
                data.putExtra("id",id);
                //Log.i("delete", "" + id);
                //setResult(2, data);
                startActivityForResult(data,6);


            }
        });
       // intnt.putExtra("station number",autoArrayType.get(i));
       // intnt.putExtra("radio number",autoArrayList.get(i));
    }

    @Override
    protected  void  onActivityResult(int requestCode, int resultCode, Intent data){
        if ( resultCode == 5 ||requestCode==5) {
           // Intent data1 = new Intent();
            String stationNum = data.getExtras().get("updateStationNum").toString();
            String radioNum = data.getExtras().get("updateRadioNum").toString();
            Long id = data.getExtras().getLong("id");
            Intent data2 = new Intent();
            data2.putExtra("updateStationNum",stationNum);
            data2.putExtra("updateRadioNum",radioNum);
            data2.putExtra("id",id);
            Log.i("get update stat", "" + stationNum);
            Log.i("get update radio", "" + radioNum);
            Log.i("get update id", "" + id);
            setResult(2, data2);
            finish();
        }
        else if (resultCode == 6 ||requestCode==6) {

            String stationNum = data.getExtras().get("addStationNum").toString();
            String radioNum = data.getExtras().get("addRadioNum").toString();
            //Long id = data.getExtras().getLong("id");
            Intent data2 = new Intent();
            data2.putExtra("addStationNum",stationNum);
            data2.putExtra("addRadioNum",radioNum);
            //data2.putExtra("id",id);
            Log.i("get add stat", "" + stationNum);
            Log.i("get add radio", "" + radioNum);
            //Log.i("get update id", "" + id);
            setResult(7, data2);
            finish();
        }
    }

}
