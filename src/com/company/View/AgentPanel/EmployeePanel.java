package com.company.View.AgentPanel;

import com.company.Model.Entitys.Agent;
import com.company.Model.Entitys.Car;
import com.company.Model.Entitys.ValidationException;
import com.company.Model.Services.OfficerServices;
import com.company.View.MessageBox;
import com.company.View.UiManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EmployeePanel extends JFrame {
    static JFrame jFrame;
    private Agent agent;

    public EmployeePanel(Agent a) {
        super("پنل کاربری");
        agent = a;
        this.setSize(350, 500);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JLayeredPane layeredPane = new JLayeredPane();
        this.setLayeredPane(layeredPane);
        this.setResizable(false);

        JButton tradeBtn = new JButton("خرید و فروش");
        tradeBtn.setBounds(100, 100, 150, 30);
        tradeBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SellCar();
            }
        });
        layeredPane.add(tradeBtn);

        JButton searchMember = new JButton("جستجو ی کاربر");
        searchMember.setBounds(100, 150, 150, 30);
        searchMember.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageMember();
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
                    if (plk == null && plk.length() != 7)
                        throw new ValidationException("این پلاک معتبر نیست");
                    Car c = new OfficerServices().findCar("carTag",plk);
                    new MessageBox("car info",c.toString());
                } catch (ValidationException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }
        });
        layeredPane.add(searchCar);

        JButton registerCar = new JButton("ثبت خودرو");
        registerCar.setBounds(100, 250, 150, 30);
        registerCar.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterCar();
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
                sb.append(agent.getFirstName());
                sb.append("\n");
                sb.append("نام خانوادگی:");
                sb.append(agent.getLastName());
                sb.append("\n");
                sb.append("شماره تماس:");
                sb.append(agent.getPhoneNumber());
                sb.append("\n");
                sb.append("کد ملی:");
                sb.append(agent.getNationCode());
                sb.append("\n");
                sb.append("آدرس:");
                sb.append(agent.getAddress());
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
