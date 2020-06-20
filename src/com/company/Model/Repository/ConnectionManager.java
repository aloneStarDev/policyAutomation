package com.company.Model.Repository;

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

    public ConnectionManager(String url,String username,String password){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null,"فایل های کتابخانه پیدا نشد");
        }catch ( SQLException ex){
            JOptionPane.showMessageDialog(null,"مشکل در اتصال به پایگاه داده");
        }
    }
    public ConnectionManager(String url){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null,"فایل های کتابخانه پیدا نشد");
        }catch ( SQLException ex){
            JOptionPane.showMessageDialog(null,"مشکل در اتصال به پایگاه داده");
        }
    }
    public ConnectionManager(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/policy",username,password);
        } catch (ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null,"فایل های کتابخانه پیدا نشد");
        }catch ( SQLException ex){
            if(ex.getMessage().equals("Unknown database 'policy'")){
                JOptionPane.showMessageDialog(null,"در حال آماده سازی پایگاه داده");
                connection = ConnectionManager.Startup(username,password);
            }
            System.out.println(ex.getMessage());
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
