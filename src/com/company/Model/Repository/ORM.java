package com.company.Model.Repository;

import com.company.Model.Entitys.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ORM {
    private Repository repository;
    public static ORM ORMBuilder(){
        return new ORM();
    }
    private ORM(){
        repository = new Repository();
    }
    public Agent getAgent(ResultSet rs){
        return new Agent();
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
    public boolean CreateAccount(Person p,User u){
        int pb =repository.InsertPerson(p);
        int ub =repository.InsertUser(u);
        boolean ob = false;
        if(pb !=0 && ub!=0)
            ob =repository.InsertEmptyOwner(ub,pb);
        return pb!=0 && ub!=0 && ob;
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
}
