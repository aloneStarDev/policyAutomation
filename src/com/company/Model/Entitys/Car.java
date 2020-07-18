package com.company.Model.Entitys;

public class Car {
    public int id;
    public String carTag;
    public Owner owner;
    public String vin;
    public String model;
    public String color;

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +"\n"+
                ", carTag='" + carTag + '\'' +"\n"+
                ", owner=" + owner.toString() +"\n"+
                ", vin='" + vin + '\'' +"\n"+
                ", model='" + model + '\'' +"\n"+
                ", color='" + color + '\'' +"\n"+
                '}';
    }
}
