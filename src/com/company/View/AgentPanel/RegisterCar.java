package com.company.View.AgentPanel;

import com.company.Model.Entitys.Car;
import com.company.Model.Services.OfficerServices;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegisterCar extends JFrame {
    static JFrame jFrame;
    public RegisterCar() {
        this.setSize(700,700);
        this.setLocation(200,200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLayeredPane layeredPane = new JLayeredPane();
        this.setLayeredPane(layeredPane);
        setSize(700,700);
        this.setResizable(false);


        JLabel modellbl = new JLabel("مدل خودرو");
        modellbl.setFont(new Font("arial",Font.BOLD,27));
        modellbl.setBounds(50,50,150,50);
        modellbl.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(modellbl);

        JTextField model = new JTextField();
        model.setFont(new Font("arial",Font.BOLD,27));
        model.setBounds(200,50,300,50);
        model.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(model);


        JLabel colorlbl = new JLabel("رنگ خودرو");
        colorlbl.setFont(new Font("arial",Font.BOLD,27));
        colorlbl.setBounds(50,150,150,50);
        colorlbl.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(colorlbl);

        JTextField color = new JTextField();
        color.setFont(new Font("arial",Font.BOLD,27));
        color.setBounds(200,150,300,50);
        color.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(color);


        JLabel vinlbl = new JLabel("وین");
        vinlbl.setFont(new Font("arial",Font.BOLD,27));
        vinlbl.setBounds(50,250,150,50);
        vinlbl.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(vinlbl);

        JTextField vin = new JTextField();
        vin.setFont(new Font("arial",Font.BOLD,27));
        vin.setBounds(200,250,300,50);
        vin.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(vin);

        JButton register = new JButton("ثبت خودرو");
        register.setBounds(300,350,100,50);
        register.setBackground(Color.darkGray);
        register.setForeground(Color.white);
        register.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Car c = new Car();
                c.model = model.getText();
                c.color = color.getText();
                c.vin = vin.getText();
                if(new OfficerServices().RegisterCar(c))
                    JOptionPane.showMessageDialog(jFrame,"با موفقیت ثبت شد");
                else
                    JOptionPane.showMessageDialog(jFrame,"این شناسه ی وین قبلا ثبت شده است");
            }
        });
        layeredPane.add(register);

        this.setVisible(true);
        jFrame = this;
    }
}
