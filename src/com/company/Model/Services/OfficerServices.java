package com.company.Model.Services;

import com.company.Model.Entitys.*;
import com.company.Model.Repository.ORM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class OfficerServices {
    private final ORM ObjectRelationMap;

    public OfficerServices() {
        ObjectRelationMap = ORM.ORMBuilder();
    }
    public Agent getAgent(User u){
        Agent agent= null;
        int[] agentLinks = ObjectRelationMap.getAgentLinks("userId",u.id);
        if(agentLinks == null)
            return null;
        agent = new Agent();
        agent.rank = MilitaryRank.values()[agentLinks[1]];
        agent.agentId = agentLinks[0];
        agent.user = u;
        agent.setPerson(ObjectRelationMap.getPerson(agentLinks[3]));
        return agent;
    }
    public boolean RegisterCar(Car c){
        return ObjectRelationMap.CreateCar(c);
    }

    public boolean trade(String v, String buy, String sell) throws ValidationException {

        boolean factory = sell.equals("factory");
        Car c = ObjectRelationMap.getCar(v);
        HashMap<String,String> identifier = new HashMap<>();
        identifier.put("nationCode",sell);
        Person p = null;
        int[] sellerLinks = null;
        if(!factory) {
            p = ObjectRelationMap.getPerson(identifier);
            if (p == null)
                throw new ValidationException("کد ملی فروشنده صحیح نیست");
            sellerLinks = ObjectRelationMap.getOwnerLinks("personId", p.getId());
            if (sellerLinks[1] == 0 || c.id != sellerLinks[1])
                throw new ValidationException("این ماشین به نام این فروشنده نیست");
        }
        identifier.clear();
        identifier.put("nationCode",buy);
        p = ObjectRelationMap.getPerson(identifier);
        if(p==null)
            throw new ValidationException("کد ملی خریدار صحیح نیست");
        int[] buyerLinks = ObjectRelationMap.getOwnerLinks("personId",p.getId());
        Certificate certificate = ObjectRelationMap.getCertificate("id",buyerLinks[3]);
        if(buyerLinks[1] != 0 )
            throw new ValidationException("طبق قانون نظارت بر خرید و فروش خودرو هر شخص میتواند فقط یک ماشین داشته باشد");
        if(certificate==null)
                throw new ValidationException("خریدار باید گواهینامه داشته باشد");
        if(certificate.CarTag==null)
            certificate.CarTag = GenerateCarTag(certificate.id);
        if(factory)
            return ObjectRelationMap.swapCar(buyerLinks[0],0,c.id,certificate.CarTag);
        else
            return ObjectRelationMap.swapCar(buyerLinks[0],sellerLinks[0],c.id,certificate.CarTag);
    }
    private String GenerateCarTag(int certificateId){
        StringBuilder sb = new StringBuilder();
        Random r= new Random();
        for(int i=0;i<7;i++)
            sb.append(r.nextInt());
        if(ObjectRelationMap.checkPlk(sb.toString())==null) {
            ObjectRelationMap.regiseterPlk(sb.toString(), certificateId);
            return sb.toString();
        }
        else
            return GenerateCarTag(certificateId);
    }

    public String[][] getMembersData() {
        ArrayList<Owner> members = new ArrayList<>();
        MemberServices ms = new MemberServices();
        ObjectRelationMap.getAllNationCode().forEach(x->{
            Owner o = ms.getMember(x);
            if(o!=null)
                members.add(o);
        });

        String[][] data = new String[members.size()][8];
        for (int i = 0; i < members.size(); i++) {
            data[i][0] = members.get(i).id+"";
            data[i][1] = members.get(i).getFirstName();
            data[i][2] = members.get(i).getLastName();
            data[i][3] = members.get(i).getPhoneNumber();
            data[i][4] = members.get(i).getNationCode();
            if(members.get(i).certificate != null) {
                data[i][5] = members.get(i).certificate.point + "";
                data[i][6] = members.get(i).certificate.CarTag;
                data[i][7] = members.get(i).certificate.penalty+"";
            }
            else {
                data[i][5] = "فاقد گواهینامه";
                data[i][6] = "فاقد گواهینامه";
                data[i][7] = "فاقد گواهینامه";
            }
        }
        return data;
    }
}