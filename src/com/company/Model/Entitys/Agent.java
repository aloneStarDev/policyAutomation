package com.company.Model.Entitys;

public class Agent extends Person implements Accessible{
    public int agentId;
    public MilitaryRank rank;
    public User user;
    @Override
    public boolean access(String type) {
        return false;
    }
}
