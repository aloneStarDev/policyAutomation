package com.company.Model.Repository;

import com.company.Model.Entitys.Person;
import com.company.Model.Entitys.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

public class Repository extends ConnectionManager{
    public Repository(){
        super();
    }
    public Repository(String username,String password){
        super(username,password);
    }

    public synchronized ResultSet getUser(String username){
        ResultSet rs = null;
        try {
            PreparedStatement statement = connection.prepareStatement("select * from `policy`.`user` where username = ?");
            statement.setString(1,username);
            rs = statement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }
    public synchronized int InsertPerson(Person p){
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO `policy`.`person` (`firstName`, `lastName`, `nationCode`, `phoneNumber`, `address`) VALUES (?,?,?,?,?)");
            statement.setString(1,p.getFirstName());
            statement.setString(2,p.getLastName());
            statement.setString(3,p.getNationCode());
            statement.setString(4,p.getPhoneNumber());
            statement.setString(5,p.getAddress());
            statement.execute();
            PreparedStatement preparedStatement =connection.prepareStatement("select `id` from `policy`.`person` where nationCode = ?");
            preparedStatement.setString(1, p.getNationCode());
            return preparedStatement.executeQuery().getInt("id");
        }catch (SQLException ex){
            ex.printStackTrace();
            return 0;
        }
    }
    private String Condition(Map<String, String> identifier)
    {
        ArrayList<String> condition = new ArrayList<>();
        for (String key:identifier.keySet()) {
            condition.add(key+"="+identifier.get(key));
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<condition.size();i++){
            sb.append(condition.get(i));
            if(i!=0 || i!= condition.size()-1)
                sb.append(" and ");
        }
        return sb.toString();
    }

    public synchronized boolean InsertEmptyOwner(int userId,int personId){
        try {
            PreparedStatement statement = connection.prepareStatement("insert into `policy`.`Owner` (`carId`,`personId`,`userId`,`certificateId`) values (0,?,?,0)");
            statement.setInt(1,personId);
            statement.setInt(2,userId);
            statement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public synchronized ResultSet getPerson(Map<String,String> identifier){
        ResultSet rs = null;
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery("select * from `policy`.`person` where "+Condition(identifier));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }
    public synchronized int InsertUser(User u){
        try {
            PreparedStatement statement = connection.prepareStatement("insert into `policy`.`user` (`username`,`password`) values (?,?);");
            statement.setString(1,u.getUsername());
            statement.setString(2,u.getHashedPassword());
            statement.execute();

            PreparedStatement preparedStatement =connection.prepareStatement("select `id` from `policy`.`user` where username = ?");
            preparedStatement.setString(1, u.getUsername());
            return preparedStatement.executeQuery().getInt("id");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }
}
