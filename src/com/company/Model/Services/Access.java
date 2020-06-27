package com.company.Model.Services;

import com.company.Model.Entitys.*;
import com.company.Model.Repository.ORM;

import java.util.HashMap;

public class Access {
    private final ORM ObjectRelationMap;
    public Access(){
        ObjectRelationMap = ORM.ORMBuilder();
    }
    public User login(User u){
        User user = ObjectRelationMap.getUser(u.getUsername());
        if(user != null && user.checkPassword(u.getPassword()))
            return user;
        return null;
    }
    public User Register(Person p,String password) throws ValidationException {
        if(!ObjectRelationMap.isValid(p))
            throw new ValidationException("این کد ملی قبلا در سامانه ثبت شده");
        User u = new User();
        u.setUsername(p.getNationCode());
        u.setPassword(password);
        if(!ObjectRelationMap.CreateAccount(p,u))
            throw new ValidationException("خطایی در ثبت اطلاعات رخ داد");
        return u;
    }
    public boolean RegisterAgent(Agent a){
        HashMap<String,String> identifier = new HashMap<>();
        identifier.put("nationCode",a.getNationCode());
        a.setPerson(ObjectRelationMap.getPerson(identifier));
        a.user = ObjectRelationMap.getUser(a.user.getUsername());
        return ObjectRelationMap.CreateAgent(a);
    }
}
