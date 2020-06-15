package com.google.sps.data;

import com.google.gson.Gson;
import java.util.Date;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  

public class Comment{

    private String time;
    private String username;
    private String comment;

    public Comment(Date time, String username, String comment){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm");
        this.time = dateFormat.format(time);
        this.username = username;
        this.comment = comment;
    }
}