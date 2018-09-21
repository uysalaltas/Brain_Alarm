package com.example.uysal.brain_alarm;


public class Clock {

    private int hr; //store hours
    private int min;  //store minutes
    private int sec; //store seconds
    private String clk = "";
    public Clock ()
    {
        setTime (0, 0, 0);
    }
    public Clock (int hours, int minutes, int seconds)
    {
        setTime (hours, minutes, seconds);
    }
    public void setTime (int hours, int minutes, int seconds)
    {
        if (0 <= hours && hours < 24)
            hr = hours;
        else
            hr = 0;

        if (0 <= minutes && minutes < 60)
            min = minutes;
        else
            min = 0;

        if (0 <= seconds && seconds < 60)
            sec = seconds;
        else
            sec = 0;
    }

    //Method to return the hours
    public int getHours ( )
    {
        return hr;
    }
    //Method to return the minutes
    public int getMinutes ( )
    {
        return min;
    }
    //Method to return the seconds
    public int getSeconds ( )
    {
        return sec;
    }
    public String printTime ( )
    {
        if (hr < 10)
            clk = "0"+String.valueOf(hr)+":";
        clk = String.valueOf(hr)+":";
        if (min < 10)
            clk = clk +"0"+String.valueOf(min);
        clk = clk + String.valueOf(min);
        return clk;
    }

    //The time is incremented by one second
    //If the before-increment time is 23:59:59, the time
    //is reset to 00:00:00
    public void incrementSeconds ( )
    {
        sec++;

        if (sec > 59)
        {
            sec = 0;
            incrementMinutes (1);  //increment minutes
        }
    }
    ///The time is incremented by one minute
    //If the before-increment time is 23:59:53, the time
    //is reset to 00:00:53
    public void incrementMinutes (int smin)
    {
        min = min + smin;

        if (min > 59)
        {
            min = min - 60;
            incrementHours (1);  //increment hours
        }

    }
    public void incrementHours (int shr)
    {
        hr = hr + shr;

        if (hr > 23)
            hr = hr - 24;
    }
    public boolean equals (Clock otherClock)
    {
        return (hr == otherClock.hr
                && min == otherClock.min
                && sec == otherClock.sec);
    }

}