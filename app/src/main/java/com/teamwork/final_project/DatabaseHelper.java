package com.teamwork.final_project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Li Weikai on 2017-04-03.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    public final static String AUTO_DATABASE_NAME = "AutoDatabase";
    final static String AUTO_COLUMN_NAME = "SETTINGS";
    final static int VERSION_NUM = 3;
    final static String  KEY_MESSAGE = "Message";
    final static String KEY_ID = "_id";
    public DatabaseHelper(Context ctx){
        super(ctx, AUTO_DATABASE_NAME,null, VERSION_NUM);
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS " + AUTO_DATABASE_NAME + " ("+KEY_ID + " INTEGER PRIMARY KEY, "+AUTO_COLUMN_NAME+" text, "+ KEY_MESSAGE+" text);" );

        Log.i("ChatDatabaseHelper", "Calling onCreate");
    }
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+AUTO_DATABASE_NAME);
        onCreate(db);
        Log.i("DatabaseHelper", "Calling onUpgrade, oldVersion=" + oldVersion + " newVersion=" + newVersion);

    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + AUTO_DATABASE_NAME);
        onCreate(db);
    }
}
