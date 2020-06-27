package com.company.Model.Repository;

import com.company.Model.Entitys.User;

import javax.swing.*;
import java.sql.*;

public class ConnectionManager implements AutoCloseable{
    protected Connection connection;
    /**
     *
     * please sure that this username and password has all privilege
     *
     */
    String username = "amir";
    String password = "icGVfhB4V9a36LaO";

    public ConnectionManager(String username,String password){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/policy",username,password);
            System.out.println("connected");
        } catch (ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null,"فایل های کتابخانه پیدا نشد");
        }catch ( SQLException ex){
            if(ex.getMessage().equals("Unknown database 'policy'")){
                JOptionPane.showMessageDialog(null,"در حال آماده سازی پایگاه داده");
                connection = ConnectionManager.Startup(username,password);
            }else {
                JOptionPane.showMessageDialog(null, "مشکل در اتصال به پایگاه داده");
                ex.printStackTrace();
            }
        }
    }
    public ConnectionManager(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/policy",username,password);
            System.out.println("connected");
        } catch (ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null,"فایل های کتابخانه پیدا نشد");
        }catch ( SQLException ex){
            if(ex.getMessage().equals("Unknown database 'policy'")){
                JOptionPane.showMessageDialog(null,"در حال آماده سازی پایگاه داده");
                connection = ConnectionManager.Startup(username,password);
            }
            else{
                JOptionPane.showMessageDialog(null,"مشکل در اتصال به پایگاه داده");
                ex.printStackTrace();
            }
        }
    }
    /**
     *
     * to start work with this part
     * you should use a user with root access
     * this method also create all tables you need
     *
     * @param username = "username of database"
     * @param password = "password of database"
     * @return Connection
     */
    public static Connection Startup(String username,String password){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/",username,password);
            Statement statement = connection.createStatement();
            statement.execute("create database policy");
            statement.execute("CREATE TABLE `policy`.`person` ( `id` INT NOT NULL AUTO_INCREMENT , `firstName` VARCHAR(30) NOT NULL , `lastName` VARCHAR(30) NOT NULL , `nationCode` VARCHAR(10) NOT NULL , `phoneNumber` varchar(11) NOT NULL , `address` varchar(50) NOT NULL , PRIMARY KEY (`id`), UNIQUE (`nationCode`)) ENGINE = InnoDB;");
            statement.execute("CREATE TABLE `policy`.`agent` ( `Id` INT NOT NULL AUTO_INCREMENT , `rank` INT NOT NULL , `userId` INT NOT NULL , `personId` INT NOT NULL , PRIMARY KEY (`Id`)) ENGINE = InnoDB;");
            statement.execute("CREATE TABLE `policy`.`car` ( `id` INT NOT NULL AUTO_INCREMENT , `carTag` VARCHAR(7), `color` VARCHAR(20) NOT NULL ,`model` VARCHAR(20) NOT NULL ,`ownerId` INT, `vin` VARCHAR(30) NOT NULL , PRIMARY KEY (`id`),UNIQUE(`vin`) ) ENGINE = InnoDB;");
            statement.execute("CREATE TABLE `policy`.`certificate` ( `id` INT NOT NULL AUTO_INCREMENT , `ownerId` INT NOT NULL , `point` INT NOT NULL , `isActive` BOOLEAN NOT NULL , `penalty` BIGINT NOT NULL ,`carTag` varchar(7) , PRIMARY KEY (`id`),unique('carTag') ) ENGINE = InnoDB;");
            statement.execute("CREATE TABLE `policy`.`log` ( `id` INT NOT NULL AUTO_INCREMENT , `cardId` INT NOT NULL , `key` VARCHAR(30) NOT NULL , `value` VARCHAR(30) NOT NULL , `message` VARCHAR(50) NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;");
            statement.execute("CREATE TABLE `policy`.`owner` ( `id` INT NOT NULL AUTO_INCREMENT , `carId` INT NOT NULL , `personId` INT NOT NULL , `certificateId` INT NOT NULL , `userId` INT NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;");
            statement.execute("CREATE TABLE `policy`.`user` ( `id` INT NOT NULL AUTO_INCREMENT , `username` VARCHAR(30) NOT NULL , `password` VARCHAR(30) NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;");
            statement.execute("INSERT INTO `policy`.`person` (`firstName`, `lastName`, `nationCode`, `phoneNumber`, `address`) VALUES ('electro', 'cyber', '1234567890', '093xxxxxxxx', '0x404')");
            statement.execute("INSERT INTO `policy`.`agent` (`rank`, `userId`, `personId`) VALUES ('2', '1', '1')");
            PreparedStatement preparedStatement =connection.prepareStatement("INSERT INTO `policy`.`user` (`username`, `password`) VALUES (?,?)");
            User master = new User();
            master.setUsername("amir");
            master.setPassword("star");
            preparedStatement.setString(1,master.getUsername());
            preparedStatement.setString(2,master.getHashedPassword());
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null," پایگاه داده با موفقیت راه اندازی شد");
            return connection;
        } catch (ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null,"فایل های کتابخانه پیدا نشد");
        }catch ( SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"مشکل در اتصال به پایگاه داده");
        }
        return null;
    }
    @Override
    public void close() throws Exception {
        connection.close();
    }
}
