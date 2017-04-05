package com.teamwork.final_project;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class kitchen_add_new extends AppCompatActivity {

    TextView type;
    EditText name;
    EditText set_num;
    Button set;
    ProgressBar bar;
    kitchenDatabaseHelper kdb;
    SQLiteDatabase db;
    ContentValues contentValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_add_new);

        kdb = new kitchenDatabaseHelper(getBaseContext());
        db = kdb.getWritableDatabase();
        contentValues = new ContentValues();

        type = (TextView) findViewById(R.id.device_type);
        name = (EditText) findViewById(R.id.device_name);
        set_num = (EditText) findViewById(R.id.device_setting);
        set = (Button) findViewById(R.id.device_submit);
        bar = (ProgressBar)findViewById(R.id.pros_bar);

        final String type_d = getIntent().getStringExtra("device_type");
        type.setText(type_d);


        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name_d = name.getText().toString();
                final String setting = set_num.getText().toString();
                final AsyncTask<Void, Void, Void> adddevice = new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        insert(name_d, type_d, setting);
                        db.insert(kdb.TableName, null, contentValues);
                        return null;

                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        // Close this activity
                        bar.setVisibility(View.VISIBLE);
                        Intent intent = new Intent(kitchen_add_new.this, kitchen_main.class);
                        intent.putExtra("dname", name_d);
                        finish();
                    }
                };
                adddevice.execute((Void) null);
            }


            public void insert(String named, String typed, String settingd) {
                contentValues.put(kdb.KEY_NAME, named);
                contentValues.put(kdb.KEY_TYPE, typed);
                contentValues.put(kdb.KEY_SETTING, settingd);

            }

        });
    }
}