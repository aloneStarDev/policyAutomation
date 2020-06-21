package com.company.Model.Entitys;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String nationCode;
    private String phoneNumber;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationCode() {
        return nationCode;
    }

    public void setNationCode(String nationCode) throws ValidationException {
        if(nationCode.length() != 10)
            throw new ValidationException("کد ملی باید 10 رقم باشد");
        for (char c: nationCode.toCharArray())
            if(c < '0' || c > '9')
                throw new ValidationException("کد ملی باید فقط شامل اعداد لاتین باشد");
        this.nationCode = nationCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPerson(Person p) {
        this.id = p.id;
        this.firstName = p.firstName;
        this.lastName = p.lastName;
        this.nationCode = p.nationCode;
        this.phoneNumber = p.phoneNumber;
        this.address = p.address;
    }
}
