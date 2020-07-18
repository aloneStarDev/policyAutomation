package com.company.Model.Entitys;

public class Owner extends Person{
    public int id;
    public Car car;//vin plk
    public Certificate certificate;
    public User user;

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +"\n"+
                ", certificate=" + certificate.toString() +"\n"+
                ", user=" + user.toString() +"\n"+
                '}'+super.toString();
    }
}
