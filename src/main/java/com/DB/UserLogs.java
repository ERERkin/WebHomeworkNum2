package com.DB;

import java.sql.Timestamp;

public class UserLogs{
    private int id;
    private int user_id;
    private Timestamp timeLog;
    private Status status;

    public UserLogs(int id, int user_id, Status status) {
        this.id = id;
        this.user_id = user_id;
        //this.timeLog = timeLog;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Timestamp getTimeLog() {
        return timeLog;
    }


    public Status getStatus() {
        return status;
    }

}
