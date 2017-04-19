package com.teamwork.final_project;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static com.teamwork.final_project.kitchenDatabaseHelper.KEY_ID;
import static com.teamwork.final_project.kitchenDatabaseHelper.KEY_SETTING;
import static com.teamwork.final_project.kitchenDatabaseHelper.TableName;


public class kitchenMicrowave extends Fragment {

    protected TextView timer;
    protected EditText entry;
    protected CountDownTimer countdown;
    protected Button b_reset;
    protected Button b_start;
    protected Button b_stop;
    protected Button b_set;
    protected Vibrator vibrator;
    long start;
    String time;
    boolean exception;
    String time_set;
    kitchenDatabaseHelper kdb;
    SQLiteDatabase db;
    ContentValues cv_micro;
    Button b_save;
    RelativeLayout rl;
    TextView n_m;

    public kitchenMicrowave(){
        super();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.kitchen_activity_microwave, container, false);

        timer = (TextView)v.findViewById(R.id.micro_timer);
        entry = (EditText)v.findViewById(R.id.time_entry);
        b_reset = (Button)v.findViewById(R.id.time_reset);
        b_start = (Button)v.findViewById(R.id.time_start);
        b_stop = (Button)v.findViewById(R.id.stop);
        b_set = (Button)v.findViewById(R.id.time_set);
        b_save = (Button)v.findViewById(R.id.time_change);
        rl = (RelativeLayout)v.findViewById(R.id.activity_microwave);
        n_m = (TextView)v.findViewById(R.id.m_name);
        vibrator= (Vibrator)getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        kdb = new kitchenDatabaseHelper(this.getActivity());
        db = kdb.getWritableDatabase();

        exception = true;
        time_set = getActivity().getIntent().getStringExtra("time");
        entry.setText(time_set);
        b_reset.setEnabled(false);
        b_start.setEnabled(false);
        b_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long time_trans = 0;
                time = entry.getText().toString();
                    try {
                        time_trans = Long.valueOf(time) * 1000;
                        exception = false;
                        start = time_trans;
                        countdown = new Cook_timer(start, 1000);
                        if(time != ""){
                            b_reset.setEnabled(true);
                            b_start.setEnabled(true);
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(getActivity().getApplicationContext(), "Please enter your cooking time", Toast.LENGTH_LONG).show();
                    }

            }
        });

        b_start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (start > 0) {
                            countdown.start();
                            countdown.onTick(start);
                            countdown.onFinish();
                            vibrator.vibrate(3000);
                        }


                    }
                });

        b_reset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        countdown.cancel();
                        timer.setText("Reset!");
                        entry.setText("");
                        b_reset.setEnabled(false);
                        b_start.setEnabled(false);
                    }
                });


            b_stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    countdown.cancel();
                    timer.setText("Stop!");
                    b_reset.setEnabled(false);
                    b_start.setEnabled(false);
                }
            });

        b_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String change = entry.getText().toString();
                //Bundle b_g = getArguments();
                //Updateset(b_g.getString("dataid"), change);
                String i = getActivity().getIntent().getStringExtra("di");
                Updateset(i, change);
                Toast.makeText(v.getContext(), "The set has been updated ", Toast.LENGTH_LONG).show();
            }
        });

        n_m.setText(getActivity().getIntent().getStringExtra("n"));

       return v;

    }

    private class Cook_timer extends CountDownTimer {

        public Cook_timer(long start, long intervel){
            super(start, intervel);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            timer.setText("Time remaining: "+ millisUntilFinished/1000);
        }

        @Override
        public void onFinish() {
            timer.setText("The cooking is done!");
        }

    }

    private void Updateset(String id, String set_num){
        cv_micro = new ContentValues();
        cv_micro.put(KEY_SETTING, set_num);
        cv_micro.put(KEY_ID, id);

        db.update(TableName, cv_micro, KEY_ID + "=" + id, null);
    }



}
