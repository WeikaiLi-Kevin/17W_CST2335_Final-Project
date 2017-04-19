package com.teamwork.final_project;

/**
 * Created by Di Yuan on 18-Apr-17.
 */

public class House_Temperature {
    // class to pass data
    private int id;
    private String time;
    private String temp;

    // default constructor
    public House_Temperature(){}

    // constructor with parameter time and temp
    public House_Temperature(String time, String temp) {
        super();
        this.time = time;
        this.temp = temp;
    }

    // accessor and modifier of id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    // accessor and modifier of time
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    // accessor and modifier of temperature
    public String getTemp() {
        return temp;
    }
    public void setTemp(String temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "House_Temperature [id=" + id + ", time=" + time + ", temp=" + temp
                + "]";
    }

}