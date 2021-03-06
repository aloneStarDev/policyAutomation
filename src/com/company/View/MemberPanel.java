package com.company.View;

import com.company.Model.Entitys.Owner;
import com.company.Model.Entitys.ValidationException;
import com.company.Model.Services.MemberServices;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MemberPanel extends JFrame {
    Owner o;
    static JFrame jFrame;
    public MemberPanel(Owner owner){
        super("پنل کاربری");
        o = owner;
        this.setSize(350,500);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JLayeredPane layeredPane = new JLayeredPane();
        this.setLayeredPane(layeredPane);
        this.setResizable(false);

        JButton infoBtn = new JButton("اطلاعات ماشین");
        infoBtn.setBounds( 100,100,150,30);
        infoBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(o.car == null)
                    JOptionPane.showMessageDialog(jFrame,"در حال حاظر در این سامانه هیچ خودرویی به نام شما ثبت نشده است");
                else
                {
                    StringBuilder sb = new StringBuilder();
                    sb.append("اطلاعات ماشین:");
                    sb.append("\n");
                    sb.append("پلاک:");
                    sb.append(o.car.carTag);
                    sb.append("\n");
                    sb.append("وین:");
                    sb.append(o.car.vin);
                    sb.append("\n");
                    sb.append("مدل:");
                    sb.append(o.car.model);
                    sb.append("\n");
                    sb.append("رنگ:");
                    sb.append(o.car.color);
                    new MessageBox("اطلاعات وسیله ی نقلیه",sb.toString());
                }
            }
        });

        layeredPane.add(infoBtn);

        JButton penaltyBtn = new JButton("استعلام خلافی");
        penaltyBtn.setBounds( 100,150,150,30);
        penaltyBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder();
                if (owner.certificate!=null) {
                    sb.append("نمره منفی شما:");
                    sb.append(o.certificate.point);
                    sb.append("\n");
                    sb.append("جریمه ی شما:");
                    sb.append(o.certificate.penalty);
                    sb.append("\n");
                    if(o.certificate.logs!=null && o.certificate.logs.size()!=0) {
                        sb.append("لیست عملکرد شما:");
                        o.certificate.logs.forEach(x -> {
                            sb.append("\n");
                            sb.append("===============================");
                            sb.append("\n");
                            sb.append(x.key);
                            sb.append("\n");
                            sb.append(x.value);
                            sb.append("\n");
                            sb.append(x.messages);
                            sb.append("\n");
                            sb.append("===============================");
                        });
                    }
                }else
                    sb.append("شما خلافی ندارید");
                new MessageBox("اطلاعات خلافی",sb.toString());
            }
        });
        layeredPane.add(penaltyBtn);

        JButton payBtn = new JButton("پرداخت جریمه");
        payBtn.setBounds( 100,200,150,30);
        payBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = JOptionPane.showInputDialog("جریمه ی شما "+o.certificate.penalty+"است.. مبلغ قابل پرداخت را وارد کنید");
                try {
                    long n = Long.parseLong(s);
                    if (n>o.certificate.penalty)
                        throw  new ValidationException("مبلغ ورودی نمی تواند از مبلغ کل جریمه بیشتر باشد");
                    o.certificate.penalty -= n;
                    new MemberServices().payP(o.certificate.penalty,o.certificate.id);
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null,"لطفا مبلغ صحیحی را وارد کنید");
                }catch (ValidationException ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            }
        });
        layeredPane.add(payBtn);

        JButton profileBtn = new JButton("اطلاعات کاربری");
        profileBtn.setBounds( 100,250,150,30);
        profileBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder();
                sb.append("نام:");
                sb.append(o.getFirstName());
                sb.append("\n");
                sb.append("نام خانوادگی:");
                sb.append(o.getLastName());
                sb.append("\n");
                sb.append("شماره تماس:");
                sb.append(o.getPhoneNumber());
                sb.append("\n");
                sb.append("کد ملی:");
                sb.append(o.getNationCode());
                sb.append("\n");
                sb.append("آدرس:");
                sb.append(o.getAddress());
                sb.append("\n");
                sb.append("اطلاعات گواهینامه");
                sb.append("\n");
                if(o.certificate == null)
                    sb.append("فاقد گواهینامه");
                else {
                    sb.append(o.certificate.isActive ? "فعال" : "غیر فعال" );
                    sb.append("\n");
                    sb.append("نمره منفی:");
                    sb.append(o.certificate.point);
                }
                new MessageBox("اطلاعات کاربری",sb.toString());
            }
        });
        layeredPane.add(profileBtn);

        JButton logout = new JButton("خروج از حساب کاربری");
        logout.setBounds( 100,300,150,30);
        logout.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                UiManager.jFrame.setVisible(true);
            }
        });
        layeredPane.add(logout);
        this.setVisible(true);
        jFrame = this;
    }
}
