package com.teamwork.final_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Di Yuan on 18-Apr-17.
 */

public class House_SQLiteHelper extends SQLiteOpenHelper {

    // database version
    private static final int database_VERSION = 1;
    // database name
    private static final String database_NAME = "TimeTempDB";
    private static final String table_Time = "Time";
    private static final String time_ID = "id";
    private static final String time = "time";
    private static final String temp = "temp";

    // create String Array to hold columns
    private static final String[] COLUMNS = { time_ID, time, temp };

    public House_SQLiteHelper(Context context) {
        super(context, database_NAME, null, database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create time table
        String CREATE_TIME_TABLE = "CREATE TABLE Time ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "time TEXT, " + "temp TEXT )";
        db.execSQL(CREATE_TIME_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop time table if already exists
        db.execSQL("DROP TABLE IF EXISTS Time");
        this.onCreate(db);
    }

    public void createTime(House_Temperature houseTemperature) {
        // get reference of the database
        SQLiteDatabase db = this.getWritableDatabase();
        // make values to be inserted
        ContentValues values = new ContentValues();
        values.put(time, houseTemperature.getTime());
        values.put(temp, houseTemperature.getTemp());
        // insert houseTemperature
        db.insert(table_Time, null, values);
        // close database transaction
        db.close();
    }

    public House_Temperature readTime(int id) {
        // get reference of the database
        SQLiteDatabase db = this.getReadableDatabase();
        // get houseTemperature query
        Cursor cursor = db.query(table_Time, // a. table
                COLUMNS, " id = ?", new String[] { String.valueOf(id) }, null, null, null, null);
        // if results != null, parse the first one
        if (cursor != null)
            cursor.moveToFirst();
        House_Temperature houseTemperature = new House_Temperature();
        houseTemperature.setId(Integer.parseInt(cursor.getString(0)));
        houseTemperature.setTime(cursor.getString(1));
        houseTemperature.setTemp(cursor.getString(2));
        return houseTemperature;
    }

    public List<House_Temperature> getAllTime() {
        List<House_Temperature> houseTemperatures = new LinkedList<House_Temperature>();
        // select houseTemperature query
        String query = "SELECT  * FROM " + table_Time;
        // get reference of the database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // parse all results
        House_Temperature houseTemperature = null;
        if (cursor.moveToFirst()) {
            do {
                houseTemperature = new House_Temperature();
                houseTemperature.setId(Integer.parseInt(cursor.getString(0)));
                houseTemperature.setTime(cursor.getString(1));
                houseTemperature.setTemp(cursor.getString(2));
                // Add houseTemperature to houseTemperatures
                houseTemperatures.add(houseTemperature);
            } while (cursor.moveToNext());
        }
        return houseTemperatures;
    }

    public int updateTime(House_Temperature houseTemperature) {
        // get reference of the database
        SQLiteDatabase db = this.getWritableDatabase();
        // make values to be inserted
        ContentValues values = new ContentValues();
        values.put("time", houseTemperature.getTime()); // get time
        values.put("temp", houseTemperature.getTemp()); // get temp
        // update database
        int i = db.update(table_Time, values, time_ID + " = ?", new String[] { String.valueOf(houseTemperature.getId()) });
        db.close();
        return i;
    }
    // delete single houseTemperature
    public void removeTime(House_Temperature houseTemperature) {
        // get reference of the database
        SQLiteDatabase db = this.getWritableDatabase();
        // delete houseTemperature
        db.delete(table_Time, time_ID + " = ?", new String[] { String.valueOf(houseTemperature.getId()) });
        db.close();
    }
}

