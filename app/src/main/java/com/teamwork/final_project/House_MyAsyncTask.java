package com.teamwork.final_project;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

public class House_MyAsyncTask extends AsyncTask<Integer,Integer,String>
{
    private TextView txt;
    private ProgressBar pgbar;

    public House_MyAsyncTask(TextView txt, ProgressBar pgbar)
    {
        super();
        this.txt = txt;
        this.pgbar = pgbar;
    }

    // AsyncTask
    @Override
    protected String doInBackground(Integer... params) {
        DelayOperator dop = new DelayOperator();
        int i = 0;
        for (i = 10;i <= 100;i+=10)
        {
            dop.delay();
            publishProgress(i);
        }
        return  i + params[0].intValue() + "";
    }

    @Override
    protected void onPreExecute() {
        txt.setText("Prepare to control house temperature");
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int value = values[0];
        pgbar.setProgress(value);
    }

    public class DelayOperator {
        public void delay()
        {
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();;
            }
        }
    }

}