package com.teamwork.final_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityToolbar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public boolean onCreateOptionsMenu (Menu m){
        getMenuInflater().inflate(R.menu.toolbar_menu, m );
        return true;
    }
    public boolean onOptionsItemSelected (MenuItem mi){
        switch(mi.getItemId()){
            case R.id.Icon_kitchen:
                Intent intent = new Intent(MainActivityToolbar.this, kitchen_main.class);
                startActivity(intent);

                break;
            case R.id.Icon_house:
                Intent intent1 = new Intent(MainActivityToolbar.this, HouseListView.class);
                startActivityForResult(intent1, 5);

                break;
            case R.id.Icon_automobile:

                Intent intnt = new Intent(MainActivityToolbar.this, AutomobileFragmentDetails.class);

                startActivity(intnt); //go to view fragment details

                break;
            case R.id.Help:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Help");
// Add the buttons
                builder.setMessage("Kitchen's author: Jiawei Guo\nHouse's author: Di Yuan\nAutomobile's author: Weikai Li\n\nVersion: 1.0.0\n\nInstruction of this interface: " +
                        "Click on the different icon to open the related activity.\nThe cookwave icon: open the kitchen activity\nThe house icon: open the house activity" +
                        "\n" +
                        "The car icon: open the automobile activity");
                //builder.setMessage("how to use");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        
                    }
                });

// Create the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();
                break;

        }
        return true;
    }

}
