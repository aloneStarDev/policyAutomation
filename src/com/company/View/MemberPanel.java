package com.company.View;

import com.company.Model.Entitys.*;

import javax.swing.*;
import java.awt.*;

public class MemberPanel extends JFrame {
    Owner o;
    static JFrame jFrame;
    public MemberPanel(Owner owner){
        super("پنل کاربری");
        o = owner;
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JLayeredPane layeredPane = new JLayeredPane();
        this.setLayeredPane(layeredPane);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setResizable(false);



        this.setVisible(true);
        jFrame = this;
    }
}
