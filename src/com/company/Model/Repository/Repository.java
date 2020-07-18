package com.company.Model.Repository;

import com.company.Model.Entitys.*;

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
            PreparedStatement preparedStatement =connection.prepareStatement("select id from `policy`.`person` where nationCode = ?");
            preparedStatement.setString(1, p.getNationCode());
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
                return rs.getInt("id");
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return 0;
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
    public synchronized ResultSet getPerson(int id){
        ResultSet rs = null;
        try {
            PreparedStatement statement = connection.prepareStatement("select * from `policy`.`person` where id = ?");
            statement.setInt(1,id);
            rs = statement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
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
    public int[] getOwner(String key,int val){
        int[] links = {0,0,0,0,0};
        try {
            PreparedStatement statement = connection.prepareStatement("select * from `policy`.`owner` where "+key+" = ?");
            statement.setInt(1,val);
            ResultSet rs =statement.executeQuery();
            if(rs.next())
            {
                links[0] = rs.getInt("id");
                links[1] = rs.getInt("carId");
                links[2] = rs.getInt("personId");
                links[3] = rs.getInt("certificateId");
                links[4] = rs.getInt("userId");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return links;
    }
    public synchronized int InsertUser(User u){
        try {
            PreparedStatement statement = connection.prepareStatement("insert into `policy`.`user` (`username`,`password`) values (?,?);");
            statement.setString(1,u.getUsername());
            statement.setString(2,u.getHashedPassword());
            statement.execute();

            PreparedStatement preparedStatement =connection.prepareStatement("select id from `policy`.`user` where username = ?");
            preparedStatement.setString(1, u.getUsername());
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
                return rs.getInt("id");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
    public ResultSet getCar(int id){
        ResultSet rs = null;
        try {
            PreparedStatement statement = connection.prepareStatement("select * from `policy`.`car` where id = ?");
            statement.setInt(1,id);
            rs = statement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }
    public ResultSet getCar(String vin){
        ResultSet rs = null;
        try {
            PreparedStatement statement = connection.prepareStatement("select * from `policy`.`car` where vin = ?");
            statement.setString(1,vin);
            rs = statement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }
    public ResultSet getCertificate(String key,int val){
        ResultSet rs = null;
        try {
            PreparedStatement statement = connection.prepareStatement("select * from `policy`.`certificate` where "+key+" = ?");
            statement.setInt(1,val);
            rs = statement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }
    public ResultSet getCertificate(String plk){
        ResultSet rs = null;
        try {
            PreparedStatement statement = connection.prepareStatement("select * from `policy`.`certificate` where `carTag` = ?");
            statement.setString(1,plk);
            rs = statement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }
    public ResultSet getLogs(int id){
        ResultSet rs = null;
        try {
            PreparedStatement statement = connection.prepareStatement("select * from `policy`.`log` where cardId = ?");
            statement.setInt(1,id);
            rs = statement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }
    public int[] getAgent(String key,int value){
        int[] agentLinks = {0,0,0,0};
        try {
            PreparedStatement statement = connection.prepareStatement("select * from `policy`.`agent` where "+key+" = ?");
            statement.setInt(1,value);
            ResultSet rs = statement.executeQuery();
            if(rs!=null && rs.next())
            {
                agentLinks[0] = rs.getInt(1);
                agentLinks[1] = rs.getInt(2);
                agentLinks[2] = rs.getInt(3);
                agentLinks[3] = rs.getInt(4);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return agentLinks;
    }

    public boolean insertAgent(Agent a) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into `policy`.`agent` (`rank`,`userId`,`personId`) values (?,?,?)");
            int r = 0;
            for (MilitaryRank m :MilitaryRank.values()) {
                if(m == a.rank)
                    break;
                r++;
            }
            statement.setInt(1,r);
            statement.setInt(2,a.user.id);
            statement.setInt(3,a.getId());
            statement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public boolean InsertCar(Car c) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into `policy`.`car` (`color`,`model`,`vin`) values (?,?,?)");
            statement.setString(1,c.color);
            statement.setString(2,c.model);
            statement.setString(3,c.vin);
            statement.execute();
            return true;
        } catch (SQLException throwables) {
            if(!throwables.getMessage().contains("Duplicate entry"))
                throwables.printStackTrace();
        }
        return false;
    }

    public boolean Trade(int buyid, int sellid, int carid,String plk) {
        try {
            connection.setAutoCommit(false);
            //plk
            PreparedStatement statement1 = connection.prepareStatement("UPDATE `policy`.`car` SET `ownerId` = ? , `carTag` = ? WHERE id = ?");
            statement1.setInt(1,buyid);
            statement1.setString(2,plk);
            statement1.setInt(3,carid);
            statement1.execute();
            //sell  ======> if sell id is 0 then seller is factory
            if(sellid != 0){
                PreparedStatement statement2 = connection.prepareStatement("UPDATE `policy`.`owner` SET `carId` = ?  WHERE id = ?");
                statement2.setInt(1,0);
                statement2.setInt(2,sellid);
                statement2.execute();
            }
            //buy
            PreparedStatement statement3 = connection.prepareStatement("UPDATE `policy`.`owner` SET `carId` = ?  WHERE id = ?");
            statement3.setInt(1,carid);
            statement3.setInt(2,buyid);
            statement3.execute();
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public void updateCertificate(int certificateId, String plk) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE `policy`.`certificate` SET `carTag` = ? WHERE `certificate`.`id` = ?");
            statement.setString(1,plk);
            statement.setInt(2,certificateId);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet selectNationCode() {
        ResultSet rs = null;
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery("select `username` from `policy`.`user`");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }

    public boolean createCertificate(int ownerId) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO `policy`.`certificate` (`ownerId`,`point`,`isactive`,`penalty`) values (?,?,?,?)");
            statement.setInt(1,ownerId);
            statement.setInt(2,0);
            statement.setBoolean(3,true);
            statement.setLong(4,0);
            statement.execute();
            PreparedStatement statement1 = connection.prepareStatement("select `id` from `policy`.`certificate` where ownerId=?");
            statement1.setInt(1,ownerId);
            ResultSet rs = statement1.executeQuery();
            int certificateId = 0;
            if(rs.next())
                 certificateId = rs.getInt("id");
            PreparedStatement statement2 = connection.prepareStatement("UPDATE `policy`.`owner` SET `certificateId` = ? WHERE `owner`.`id` = ?");
            statement2.setInt(1,certificateId);
            statement2.setInt(2,ownerId);
            statement2.execute();
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public void updatePenalty(int id,long p) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE `policy`.`certificate` SET `penalty` = ? WHERE id = ?");
            statement.setLong(1,p);
            statement.setInt(2,id);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public ResultSet getCar(String key,String value){
        ResultSet rs = null;
        try {
            PreparedStatement statement = connection.prepareStatement("select * from `policy`.`car` where "+key+" = ?");
            statement.setString(1,value);
            rs = statement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }

    public void updatePoint(int id, int point) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE `policy`.`certificate` SET `point` = ? WHERE id = ?");
            statement.setInt(1,point);
            statement.setInt(2,id);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean insertLog(Log l) {
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO `policy`.`log` (`cardId`, `key`, `value`, `message`) VALUES (?,?,?,?)");
            statement.setInt(1,l.cardId);
            statement.setString(2,l.key);
            statement.setString(3,l.value);
            statement.setString(4,l.messages);
            statement.execute();
            return true;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;

    }
}
