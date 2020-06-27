package com.company.Model.Entitys;

import java.util.ArrayList;

public class Certificate{
    public int id;
    public int ownerId;
    public boolean isActive;
    public int point;
    public long penalty;
    public String CarTag;
    public ArrayList<Log> logs;
}
