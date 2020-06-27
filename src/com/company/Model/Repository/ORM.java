package com.company.Model.Repository;

import com.company.Model.Entitys.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ORM {
    private Repository repository;
    public static ORM ORMBuilder(){
        return new ORM();
    }
    private ORM(){
        repository = new Repository();
    }
    public int[] getAgentLinks(String key,int value){
        int[] agentLinks = repository.getAgent(key,value);
        int counter =0;
        for(int i:agentLinks)
            if(i==0)
                counter++;
        if(counter == 5)
            return null;
        return agentLinks;
    }
    public User getUser(String username)
    {
        ResultSet rs = repository.getUser(username);
        User u = null;
        try {
            if(rs == null || !rs.next())
                return u;
            u = new User();
            u.id = rs.getInt("id");
            u.setUsername(rs.getString("username"));
            u.setPassword(rs.getString("password"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return u;
    }
    public Person getPerson(HashMap<String,String> identifier){
        Person p = null;
        try {
            ResultSet rs = repository.getPerson(identifier);
            if(rs != null && rs.next()) {
                p = new Person();
                p.setId(rs.getInt("id"));
                p.setFirstName(rs.getString("firstName"));
                p.setLastName(rs.getString("lastName"));
                p.setNationCode(rs.getString("NationCode"));
                p.setPhoneNumber(rs.getString("phoneNumber"));
                p.setAddress(rs.getString("Address"));
            }
        } catch (SQLException | ValidationException ex) {
            ex.printStackTrace();
        }
        return p;
    }
    public Person getPerson(int id){
        Person p = null;
        try {
            ResultSet rs = repository.getPerson(id);
            if(rs != null && rs.next()) {
                p = new Person();
                p.setId(rs.getInt("id"));
                p.setFirstName(rs.getString("firstName"));
                p.setLastName(rs.getString("lastName"));
                p.setNationCode(rs.getString("NationCode"));
                p.setPhoneNumber(rs.getString("phoneNumber"));
                p.setAddress(rs.getString("Address"));
            }
        } catch (SQLException | ValidationException ex) {
            ex.printStackTrace();
        }
        return p;
    }
    public int[] getOwnerLinks(String key, int val){
        int[] ownerLinks =repository.getOwner(key,val);
        int counter =0;
        for(int i:ownerLinks){ if(i==0) counter++;}

        if(counter == 5) {
            return null;
        }
        return ownerLinks;
    }
    public boolean CreateAccount(Person p,User u){
        int pb =repository.InsertPerson(p);
        int ub =repository.InsertUser(u);
        boolean ob = false;
        if(pb !=0 && ub!=0)
            ob =repository.InsertEmptyOwner(ub,pb);
        return pb!=0 && ub!=0 && ob;
    }
    public boolean CreateAgent(Agent a){
        return repository.insertAgent(a);
    }

    public Car getCar(String vin){
        Car c = null;
        try {
            ResultSet rs = repository.getCar(vin);
            if(rs != null && rs.next()){
                c = new Car();
                c.id = rs.getInt("id");
                c.carTag = rs.getString("carTag");
                int ownerId = rs.getInt("ownerId");
                if(ownerId == 0)
                    c.owner = null;
                else {
                    c.owner = new Owner();
                    c.owner.id = ownerId;
                }
                c.vin = rs.getString("vin");
                c.model = rs.getString("model");
                c.color = rs.getString("color");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return c;
    }
    public Car getCar(int id){
        Car c = null;
        try {
            ResultSet rs = repository.getCar(id);
            if(rs != null && rs.next()){
                c = new Car();
                c.id = rs.getInt("id");
                c.carTag = rs.getString("carTag");
                int ownerId = rs.getInt("ownerId");
                if(ownerId == 0)
                    c.owner = null;
                else {
                    c.owner = new Owner();
                    c.owner.id = ownerId;
                }
                c.vin = rs.getString("vin");
                c.model = rs.getString("model");
                c.color = rs.getString("color");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return c;
    }

    public boolean isValid(Person p){
        try {
            HashMap<String,String> identifier = new HashMap<>();
            identifier.put("NationCode",p.getNationCode());
            ResultSet rs = repository.getPerson(identifier);
            if(rs == null || rs.next())
                return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public Certificate getCertificate(String key,int val){
        Certificate c = null;
        ResultSet rs = repository.getCertificate(key,val);
        try {
            if(rs != null && rs.next()) {
                c = new Certificate();
                c.id = rs.getInt("id");
                c.ownerId = rs.getInt("ownerId");
                c.isActive = rs.getBoolean("isActive");
                c.penalty = rs.getLong("penalty");
                c.CarTag = rs.getString("CarTag");
                c.logs = this.getLogs(c.id);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return c;
    }
    private ArrayList<Log> getLogs(int CardId){
        ArrayList<Log> logs = new ArrayList<>();
        ResultSet rs = repository.getLogs(CardId);
        try {
            if(rs != null) {
                while (rs.next()){
                    Log l = new Log();
                    l.cardId = CardId;
                    l.key = rs.getString("key");
                    l.value = rs.getString("value");
                    l.messages = rs.getString("Message");
                    logs.add(l);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return logs;
    }

    public boolean CreateCar(Car c) {
        return repository.InsertCar(c);
    }


    public boolean swapCar(int buyid, int sellid, int carid,String plk) {
       return repository.Trade(buyid,sellid,carid,plk);
    }

    public Certificate checkPlk(String sb) {
        Certificate c = null;
        ResultSet rs = repository.getCertificate(sb);
        try {
            if(rs != null && rs.next()) {
                c = new Certificate();
                c.id = rs.getInt("id");
                c.ownerId = rs.getInt("ownerId");
                c.isActive = rs.getBoolean("isActive");
                c.penalty = rs.getLong("penalty");
                c.CarTag = rs.getString("CarTag");
                c.logs = this.getLogs(c.id);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return c;
    }

    public void regiseterPlk(String plk, int certificateId) {
        repository.updateCertificate(certificateId,plk);
    }

    public ArrayList<String> getAllNationCode() {
        ArrayList<String> nationCodes = new ArrayList<>();
        ResultSet rs = repository.selectNationCode();
        try {
            if(rs != null)
                while(rs.next())
                    nationCodes.add(rs.getString("username"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return nationCodes;
    }
}
