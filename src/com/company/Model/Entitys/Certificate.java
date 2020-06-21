package com.company.Model.Entitys;

import java.util.ArrayList;

public class Certificate{
    public int id;
    public int ownerId;
    private boolean isActive;
    private int point;
    private long penalty;
    ArrayList<Log> logs;
}
