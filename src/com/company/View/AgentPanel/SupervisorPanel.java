package com.company.View.AgentPanel;

import com.company.Model.Entitys.Agent;
import com.company.View.RegisterAdmin;
import com.company.View.UiManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SupervisorPanel extends JFrame {

    static JFrame jFrame;
    public SupervisorPanel(Agent a){
        super("الکترو سایبر");
        this.setSize(350,500);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JLayeredPane layeredPane = new JLayeredPane();
        this.setLayeredPane(layeredPane);
        this.setResizable(false);


        JButton Employee = new JButton("پنل خدمات");
        Employee.setBounds(100,100,150,30);
        Employee.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeePanel ep = new EmployeePanel(a);
                jFrame.dispose();
            }
        });
        layeredPane.add(Employee);

        JButton Officer = new JButton("پنل عملیات");
        Officer.setBounds(100,150,150,30);
        Officer.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OfficerPanel ep = new OfficerPanel(a);
                jFrame.dispose();
            }
        });
        layeredPane.add(Officer);

        JButton register = new JButton("اضافه کردن مامور");
        register.setBounds(100,200,150,30);
        register.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterAdmin();
            }
        });
        layeredPane.add(register);

        JButton Logout = new JButton("خروج");
        Logout.setBounds(100,250,150,30);
        Logout.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                UiManager.jFrame.setVisible(true);
            }
        });
        layeredPane.add(Logout);

        this.setVisible(true);
        jFrame = this;
    }
}
