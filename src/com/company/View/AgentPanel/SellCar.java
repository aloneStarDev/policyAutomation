package com.company.View.AgentPanel;

import com.company.Model.Entitys.ValidationException;
import com.company.Model.Services.OfficerServices;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SellCar extends JFrame {
    static JFrame jFrame;
    public SellCar() {
        this.setSize(700,700);
        this.setLocation(200,200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLayeredPane layeredPane = new JLayeredPane();
        this.setLayeredPane(layeredPane);
        setSize(700,700);
        this.setResizable(false);


        JLabel sellIdlbl = new JLabel("کد ملی فروشنده");
        sellIdlbl.setFont(new Font("arial",Font.BOLD,27));
        sellIdlbl.setBounds(50,50,150,50);
        sellIdlbl.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(sellIdlbl);

        JTextField sellId = new JTextField();
        sellId.setFont(new Font("arial",Font.BOLD,27));
        sellId.setBounds(200,50,300,50);
        sellId.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(sellId);


        JLabel buyIdlbl = new JLabel("کد ملی خریدار");
        buyIdlbl.setFont(new Font("arial",Font.BOLD,27));
        buyIdlbl.setBounds(50,150,150,50);
        buyIdlbl.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(buyIdlbl);

        JTextField buyId = new JTextField();
        buyId.setFont(new Font("arial",Font.BOLD,27));
        buyId.setBounds(200,150,300,50);
        buyId.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(buyId);


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

        JButton trade = new JButton("ثبت خودرو");
        trade.setBounds(300,350,100,50);
        trade.setBackground(Color.darkGray);
        trade.setForeground(Color.white);
        trade.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buy = buyId.getText();
                String sell = sellId.getText();
                String v = vin.getText();
                try {
                    if(new OfficerServices().trade(v,buy,sell))
                        JOptionPane.showMessageDialog(jFrame,"انتقال با موفقیت انجام شد");
                } catch (ValidationException validationException) {
                    JOptionPane.showMessageDialog(null,validationException.getMessage());
                }
            }
        });
        layeredPane.add(trade);

        this.setVisible(true);
        jFrame = this;
    }
}
