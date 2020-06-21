package com.company.Model.Services;

import com.company.Model.Entitys.*;
import com.company.Model.Repository.ORM;

import java.util.HashMap;

public class MemberServices {
    private final ORM ObjectRelationMap;

    public MemberServices() {
        ObjectRelationMap = ORM.ORMBuilder();
    }

    public Owner getMember(User u){
        Owner o = new Owner();
        o.user = u;
        HashMap<String,String> identifier = new HashMap<>();
        identifier.put("nationCode",u.getUsername());
        o.setPerson(ObjectRelationMap.getPerson(identifier));
        return o;
    }
}
