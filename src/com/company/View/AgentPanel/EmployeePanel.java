package com.company.View.AgentPanel;

import com.company.Model.Entitys.Agent;
import com.company.View.UiManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EmployeePanel extends JFrame {
    static JFrame jFrame;
    private Agent agent;
    public EmployeePanel(Agent a){
        super("پنل کاربری");
        agent = a;
        this.setSize(350,500);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JLayeredPane layeredPane = new JLayeredPane();
        this.setLayeredPane(layeredPane);
        this.setResizable(false);

        JButton tradeBtn = new JButton("خرید و فروش");
        tradeBtn.setBounds( 100,100,150,30);
        tradeBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SellCar();
            }
        });
        layeredPane.add(tradeBtn);

        JButton searchMember = new JButton("جستجو ی کاربر");
        searchMember.setBounds( 100,150,150,30);
        searchMember.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageMember();
            }
        });
        layeredPane.add(searchMember);

        JButton searchCar = new JButton("جستجو ی خودرو");
        searchCar.setBounds( 100,200,150,30);
        layeredPane.add(searchCar);

        JButton registerCar = new JButton("ثبت خودرو");
        registerCar.setBounds( 100,250,150,30);
        registerCar.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterCar();
            }
        });
        layeredPane.add(registerCar);

        JButton profileBtn = new JButton("اطلاعات کاربری");
        profileBtn.setBounds( 100,300,150,30);
        layeredPane.add(profileBtn);

        JButton logout = new JButton("خروج از حساب کاربری");
        logout.setBounds( 100,350,150,30);
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
