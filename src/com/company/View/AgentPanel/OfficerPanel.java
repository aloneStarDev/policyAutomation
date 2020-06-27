package com.company.View.AgentPanel;

import com.company.Model.Entitys.Agent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class OfficerPanel extends JFrame {
    static JFrame jFrame;
    public OfficerPanel(Agent a){
        super("الکترو سایبر");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JLayeredPane layeredPane = new JLayeredPane();
        this.setLayeredPane(layeredPane);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setResizable(false);


        JLabel textfield = new JLabel(" سامانه ی الکترونیکی جامع پلیس ناجا ");
        textfield.setFont(new Font("arial",Font.BOLD,27));
        textfield.setBounds((Toolkit.getDefaultToolkit().getScreenSize().width/2)-250,100,500,100);
        textfield.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(textfield);


        JButton register = new JButton("یا ثبت نام کنید");
        register.setBounds((Toolkit.getDefaultToolkit().getScreenSize().width/2)-50,600,100,30);
        register.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterCar r = new RegisterCar();
                jFrame.dispose();
            }
        });
        layeredPane.add(register);

        this.setVisible(true);
        jFrame = this;
    }
}
