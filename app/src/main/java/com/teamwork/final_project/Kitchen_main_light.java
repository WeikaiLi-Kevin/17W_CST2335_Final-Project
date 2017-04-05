package com.teamwork.final_project;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;


public class Kitchen_main_light extends Activity {

    EditText ed_light;
    Button b_dim;
    String light_set;
    Switch sh;
    ProgressBar bar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kitchen_activity_main_light);


        ed_light = (EditText) findViewById(R.id.light_dim);
        b_dim = (Button) findViewById(R.id.light_adjust);
        sh = (Switch)findViewById(R.id.light_switch);
        bar = (ProgressBar) findViewById(R.id.pros_bar);
        bar.setVisibility(View.VISIBLE);



        //light_set = ed_light.getText().toString();
        //final String light_set = this.getActivity().getIntent().getStringExtra("dim");
        //ed_light.setText(light_set);
        b_dim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar.setVisibility(View.INVISIBLE);
                Toast.makeText(Kitchen_main_light.this, "The light set to " + light_set + " %", Toast.LENGTH_SHORT).show();
            }
        });

        new LoadQuery().execute();

    }


    class LoadQuery extends AsyncTask<String, Integer, String> {
        protected String doInBackground(String... args) {
            light_set = getIntent().getStringExtra("dim");
            publishProgress(100);
            return light_set;
        }

        protected void onProgressUpdate(Integer... values) {
            bar.setVisibility(View.VISIBLE);
            bar.setProgress(values[0]);
        }

        protected void onPostExecute(String result) {
            //bar.setVisibility(View.INVISIBLE);
            ed_light.setText(light_set);

        }


    }
}
