package com.company.View.AgentPanel;

import com.company.Model.Services.MemberServices;
import com.company.Model.Services.OfficerServices;
import com.company.View.MemberPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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

        String[][] data = new OfficerServices().getMembersData();
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("id");
        defaultTableModel.addColumn("name");
        defaultTableModel.addColumn("lastName");
        defaultTableModel.addColumn("phoneNumber");
        defaultTableModel.addColumn("nationCode");
        defaultTableModel.addColumn("point");
        defaultTableModel.addColumn("carTag");
        defaultTableModel.addColumn("penalty");
        for (String[] s:data)
            defaultTableModel.addRow(s);
        JTable userTable = new JTable(defaultTableModel);
        JScrollPane jp = new JScrollPane(userTable);
        jp.setBounds(50,100,900,500);
        layeredPane.add(jp);

        JButton search = new JButton("جستجو");
        search.setBounds(750,50,100,30);
        layeredPane.add(search);
        search.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(search.getText().equals("جستجو")) {
                    String n = username.getText();
                    for (String[] datum : data) {
                        if (n.equals(datum[4])) {
                            for (int i = 0; i < data.length; i++) {
                                ((DefaultTableModel) userTable.getModel()).removeRow(0);
                            }
                            ((DefaultTableModel) userTable.getModel()).addRow(datum);
                            search.setText("refresh");
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(null, "کاربر مورد نظر یافت نشد");
                }else
                {
                    for (int i = 0; i < defaultTableModel.getRowCount(); i++)
                        ((DefaultTableModel) userTable.getModel()).removeRow(0);
                    for (String[] datum : data) ((DefaultTableModel) userTable.getModel()).addRow(datum);
                    search.setText("جستجو");
                }
            }
        });


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

        JButton registerCertificate = new JButton("ثبت گواهینامه");
        registerCertificate.setBounds(1050,50,100,30);
        registerCertificate.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = userTable.getSelectedRow();
                if(row == -1) {
                    JOptionPane.showMessageDialog(null, "ابتدا کاربر خود را انتخاب کنید");
                    return;
                }
                if(data[row][5].equals("فاقد گواهینامه"))
                    if(new OfficerServices().registerCertificate(Integer.parseInt(data[row][0]))) {
                        JOptionPane.showMessageDialog(null, "ثبت شد");
                        jFrame.dispose();
                        new ManageMember();
                    }
                    else
                        JOptionPane.showMessageDialog(null,"خطایی رخ داد");
                else
                    JOptionPane.showMessageDialog(null,"این کاربر گواهینامه دارد");
            }
        });
        layeredPane.add(registerCertificate);


        JButton changePoint = new JButton("تغییر خلافی");
        changePoint.setBounds(1050,50,100,30);
        changePoint.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showInputDialog("نمره ی خلافی جدید را وارد کنید");
            }
        });
        layeredPane.add(changePoint);

        this.setVisible(true);
        jFrame = this;
    }
}
