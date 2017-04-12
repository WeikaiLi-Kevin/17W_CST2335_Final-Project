package com.teamwork.final_project;

public class House_Temperature {

    private int id;
    private String time;
    private String temp;

    public House_Temperature(){}

    public House_Temperature(String time, String temp) {
        super();
        this.time = time;
        this.temp = temp;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
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