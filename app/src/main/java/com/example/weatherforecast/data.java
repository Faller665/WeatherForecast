package com.example.weatherforecast;

public class data {
    private String date;
    private int tem_max;
    private int tem_min;
    private String weather;

    public int getTem_max() {
        return tem_max;
    }

    public int getTem_min() {
        return tem_min;
    }

    public String getDate() {
        return date;
    }

    public String getWeather() {
        return weather;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTem_max(int tem_max) {
        this.tem_max = tem_max;
    }

    public void setTem_min(int tem_min) {
        this.tem_min = tem_min;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
