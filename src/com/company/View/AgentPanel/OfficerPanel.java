package com.company.View.AgentPanel;

import com.company.Model.Entitys.Agent;
import com.company.Model.Entitys.Car;
import com.company.Model.Entitys.Log;
import com.company.Model.Entitys.ValidationException;
import com.company.Model.Services.OfficerServices;
import com.company.View.MessageBox;
import com.company.View.UiManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class OfficerPanel extends JFrame {
    static JFrame jFrame;
    public OfficerPanel(Agent a){
        super("پنل کاربری");

        this.setSize(350, 500);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JLayeredPane layeredPane = new JLayeredPane();
        this.setLayeredPane(layeredPane);
        this.setResizable(false);

        JButton searchMember = new JButton("ثبت خلافی");
        searchMember.setBounds(100, 150, 150, 30);
        searchMember.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String plk = JOptionPane.showInputDialog("لطفا پلاک ماشین را وارد کنید");
                    String value = JOptionPane.showInputDialog("لطفا علت جریمه را وارد کنید");
                    String message = JOptionPane.showInputDialog("پیام");
                    String pe = JOptionPane.showInputDialog("میزان مبلغ جریمه را وارد کنید");
                    String po = JOptionPane.showInputDialog("میزان نمره منفی را وارد کنید");
                    int point = Integer.parseInt(po);
                    long penalty = Long.parseLong(pe);
                    Log l = new Log();
                    l.messages = message;
                    l.key = "جریمه";
                    l.value = value;
                    new OfficerServices().addPenalty(plk, l, point, penalty);
                    JOptionPane.showMessageDialog(null,"ثبت شد");
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null,"لطفا مقادیر عددی را درست وارد کنید");
                }
            }
        });
        layeredPane.add(searchMember);

        JButton searchCar = new JButton("جستجو ی خودرو");
        searchCar.setBounds(100, 200, 150, 30);
        searchCar.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String plk = JOptionPane.showInputDialog("پلاک خودرو را وارد کنید");
                    if (plk == null || plk.length() != 7)
                        throw new ValidationException("این پلاک معتبر نیست");
                    Car c = new OfficerServices().findCar("carTag",plk);
                    new MessageBox("car info",c.toString());
                } catch (ValidationException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        layeredPane.add(searchCar);

        JButton registerCar = new JButton("بررسی ماشین");
        registerCar.setBounds(100, 250, 150, 30);
        registerCar.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String plk = JOptionPane.showInputDialog("پلاک خودرو را وارد کنید");
                Car c = new OfficerServices().findCar("carTag", plk);
                StringBuilder sb = new StringBuilder();
                sb.append("مالک:");
                sb.append("\n");
                sb.append(c.owner.getLastName());
                sb.append("\n");
                sb.append("کد ملی:");
                sb.append("\n");
                sb.append(c.owner.getNationCode());
                sb.append("\n");
                sb.append("شماره تماس:");
                sb.append("\n");
                sb.append(c.owner.getPhoneNumber());
                sb.append("\n");
                if (c.owner.certificate.logs != null && c.owner.certificate.logs.size() != 0) {
                    sb.append("لیست عملکرد:");
                    sb.append("\n");
                    sb.append("===============================");
                    sb.append("\n");
                    c.owner.certificate.logs.forEach(x -> {
                        sb.append(x.key);
                        sb.append("\n");
                        sb.append(x.value);
                        sb.append("\n");
                        sb.append(x.messages);
                        sb.append("\n");
                        sb.append("===============================");
                        sb.append("\n");

                    });
                }
                new MessageBox("جزئیات خودرو",sb.toString());
            }
        });
        layeredPane.add(registerCar);

        JButton profileBtn = new JButton("اطلاعات کاربری");
        profileBtn.setBounds(100, 300, 150, 30);
        profileBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder();
                sb.append("نام:");
                sb.append(a.getFirstName());
                sb.append("\n");
                sb.append("نام خانوادگی:");
                sb.append(a.getLastName());
                sb.append("\n");
                sb.append("شماره تماس:");
                sb.append(a.getPhoneNumber());
                sb.append("\n");
                sb.append("کد ملی:");
                sb.append(a.getNationCode());
                sb.append("\n");
                sb.append("آدرس:");
                sb.append(a.getAddress());
                sb.append("\n");
                new MessageBox("اطلاعات کاربری", sb.toString());
            }
        });
        layeredPane.add(profileBtn);

        JButton logout = new JButton("خروج از حساب کاربری");
        logout.setBounds(100, 350, 150, 30);
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
