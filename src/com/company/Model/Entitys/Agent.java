package com.company.Model.Entitys;

public class Agent extends Person implements Accessible{
    public int id;
    public MilitaryRank rank;
    public Card identifier;
    public User user;
    @Override
    public boolean access(String type) {
        return false;
    }
}
