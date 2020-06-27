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
        int[] ownerLinks = ObjectRelationMap.getOwnerLinks("userId",u.id);
        if(ownerLinks == null) return null;
        HashMap<String,String> identifier = new HashMap<>();
        o.user = u;
        identifier.put("nationCode",u.getUsername());
        o.setPerson(ObjectRelationMap.getPerson(identifier));
        o.id = ownerLinks[0];
        o.certificate = ObjectRelationMap.getCertificate("ownerId",o.id);
        o.car = ownerLinks[1] ==0 ? null : ObjectRelationMap.getCar(ownerLinks[1]);
        if(o.car!=null)
            o.car.owner = o;
        return o;
    }
    public Owner getMember(String NationCode){
        Owner o = new Owner();
        User u = ObjectRelationMap.getUser(NationCode);
        int[] ownerLinks = ObjectRelationMap.getOwnerLinks("userId",u.id);
        if(ownerLinks == null) return null;
        HashMap<String,String> identifier = new HashMap<>();
        o.user = u;
        identifier.put("nationCode",u.getUsername());
        o.setPerson(ObjectRelationMap.getPerson(identifier));
        o.id = ownerLinks[0];
        o.certificate = ObjectRelationMap.getCertificate("ownerId",o.id);
        o.car = ownerLinks[1] ==0 ? null : ObjectRelationMap.getCar(ownerLinks[1]);
        if(o.car!=null)
            o.car.owner = o;
        return o;
    }
}
