package com.teamwork.final_project;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.kitchen_activity_microwave, container, false);

        timer = (TextView)v.findViewById(R.id.micro_timer);
        entry = (EditText)v.findViewById(R.id.time_entry);
        b_reset = (Button)v.findViewById(R.id.time_reset);
        b_start = (Button)v.findViewById(R.id.time_start);
        b_stop = (Button)v.findViewById(R.id.stop);
        b_set = (Button)v.findViewById(R.id.time_set);
        vibrator= (Vibrator)getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        exception = true;

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
                    } catch (NumberFormatException e) {
                        Toast.makeText(getActivity().getApplicationContext(), "Please enter your cooking time", Toast.LENGTH_LONG).show();
                    }
                    start = time_trans;
                    countdown = new Cook_timer(start, 1000);
                    if(time != ""){
                        b_reset.setEnabled(true);
                        b_start.setEnabled(true);
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



}
