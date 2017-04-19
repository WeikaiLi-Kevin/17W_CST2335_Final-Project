package com.teamwork.final_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class AutoToolbar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.autoToolbar);
        setSupportActionBar(toolbar);

        AutomobileFragment frag = new AutomobileFragment();
        Bundle bun =        getIntent().getExtras();
        //Log.i("GetInformation",""+getIntent().getExtras());
        frag.setArguments( bun );
        getFragmentManager().beginTransaction().add(R.id.autoFragmentHolder, frag).commit();

    }
    public boolean onCreateOptionsMenu (Menu m) {
        getMenuInflater().inflate(R.menu.auto_toolbar_menu, m);
        return true;
    }
    public boolean onOptionsItemSelected (MenuItem mi){
        switch(mi.getItemId()){
            case R.id.Help:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Help");
// Add the buttons
                builder.setMessage("Automobile's author: Weikai Li\n\nVersion: 1.0.0\n\nInstruction of this interface: " +
                        "Click on the different button to open the related function.");
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
