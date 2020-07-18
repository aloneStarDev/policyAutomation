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

    @Override
    public String toString() {
        return "Certificate{" +
                "id=" + id +"\n"+
                ", ownerId=" + ownerId +"\n"+
                ", isActive=" + isActive +"\n"+
                ", point=" + point +"\n"+
                ", penalty=" + penalty +"\n"+
                ", CarTag='" + CarTag + '\'' +"\n"+
                '}';
    }
}
