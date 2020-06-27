package com.company.View.AgentPanel;

import com.company.Model.Services.MemberServices;
import com.company.Model.Services.OfficerServices;
import com.company.View.MemberPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ManageMember extends JFrame {
    public static JFrame jFrame;
    public ManageMember(){
        super("الکترو سایبر");
        this.setSize(1300,700);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JLayeredPane layeredPane = new JLayeredPane();
        this.setLayeredPane(layeredPane);
        this.setResizable(false);

        JLabel nationCode = new JLabel("کد ملی کاربر");
        nationCode.setFont(new Font("arial",Font.BOLD,20));
        nationCode.setBounds(50,50,100,30);
        nationCode.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(nationCode);

        JTextField username = new JTextField();
        username.setFont(new Font("arial",Font.BOLD,20));
        username.setBounds(200,50,500,30);
        username.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(username);

        JButton search = new JButton("جستجو");
        search.setBounds(750,50,100,30);
        layeredPane.add(search);
        search.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        String[][] data = new OfficerServices().getMembersData();
        JTable userTable = new JTable(data,new String[]{"id","name","lastName","phoneNumber","nationCode","point","carTag","penalty"});
        JScrollPane jp = new JScrollPane(userTable);
        jp.setBounds(50,100,900,500);
        layeredPane.add(jp);

        JButton checkUser = new JButton("بررسی کاربر");
        checkUser.setBounds(900,50,100,30);
        checkUser.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = userTable.getSelectedRow();
                new MemberPanel(new MemberServices().getMember(data[row][4]));
            }
        });
        layeredPane.add(checkUser);

        
        this.setVisible(true);
        jFrame = this;
    }
}
