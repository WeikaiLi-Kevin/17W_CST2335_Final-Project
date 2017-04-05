package com.teamwork.final_project;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


import static com.teamwork.final_project.kitchenDatabaseHelper.KEY_NAME;
import static com.teamwork.final_project.kitchenDatabaseHelper.TableName;

public class kitchen_main extends AppCompatActivity {

    protected ListView list_kitchen;
    protected Button b_add;
    kitchenDatabaseHelper kdb;
    SQLiteDatabase db;
    Cursor cursor;

    public static String KEY_NAME="name";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kitchen_fragment_listviewkitchen);

        list_kitchen = (ListView)findViewById(R.id.list_kitchen);
        b_add = (Button)findViewById(R.id.add_device);

        kdb = new kitchenDatabaseHelper(this);
        db = kdb.getWritableDatabase();

        b_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(kitchen_main.this);
                builder.setMessage("What type of device would you like to add?");

                builder.setPositiveButton("Light", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        String type_light = "Light";
                        Intent in = new Intent(kitchen_main.this, kitchen_add_new.class);
                        in.putExtra("device_type", type_light);
                        startActivity(in);
                    }
                });

                builder.setNegativeButton("Fridge", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        String type_fridge = "Fridge";
                        Intent in = new Intent(kitchen_main.this, kitchen_add_new.class);
                        in.putExtra("device_type", type_fridge);
                        startActivity(in);
                    }
                });

                builder.setNeutralButton("Micwave", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        String type_micro = "Microwave";
                        Intent in = new Intent(kitchen_main.this, kitchen_add_new.class);
                        in.putExtra("device_type", type_micro);
                        startActivity(in);
                    }
                });
                builder.show();

            }
        });

/*        String[] option = new String[]{"kitchenMicrowave",
                "kitchenFridge", "Main Light"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.kitchen_main_list, option);

*/
        cursor = db.rawQuery("SELECT * FROM kitchen_device", null);
        String[] names = new String[] { KEY_NAME };
        int[] to = new int[] { R.id.list_name};

        CursorAdapter adapter = new SimpleCursorAdapter(kitchen_main.this,
                R.layout.kitchen_main_list, cursor, names, to, 0);
        list_kitchen.setAdapter(adapter);
        list_kitchen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int index = position+1;
                cursor = db.rawQuery("SELECT * FROM kitchen_device where _id = ? ", new String[]{String.valueOf(index)});
                int index_type = cursor.getColumnIndex( "type" );
                int index_setting = cursor.getColumnIndex("settings");
                cursor.moveToFirst();
                String type = cursor.getString(index_type);
                String set = cursor.getString(index_setting);

                if(type.equals("Microwave")){
                    Intent intent1 = new Intent(kitchen_main.this, kitchenMicrowave_detail.class);
                    intent1.putExtra("time", set);
                    startActivity(intent1);
                }

                if(type.equals("Fridge")){
                    Intent intent2 = new Intent(kitchen_main.this, kitchenFridge_detail.class);
                    intent2.putExtra("temper", set);
                    startActivity(intent2);
                    }

                if(type.equals("Light")){
                    Intent intent3 = new Intent(kitchen_main.this, Kitchen_main_light.class);
                    intent3.putExtra("dim", set);
                    startActivity(intent3);
                }

            }
        });



    }


}
