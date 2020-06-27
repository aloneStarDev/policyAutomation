package com.company.Model.Entitys;

public class Owner extends Person{
    public int id;
    public Car car;//vin plk
    public Certificate certificate;
    public User user;

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", car=" + car +
                ", certificate=" + certificate +
                ", user=" + user +
                '}'+super.toString();
    }
}
