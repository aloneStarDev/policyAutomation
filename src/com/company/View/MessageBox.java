package com.company.View;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

public class MessageBox extends JFrame {
    static JFrame jFrame;
    public MessageBox(String title,String message){
        super(title);
        this.setSize(700,500);
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width/2)-250,500);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JLayeredPane layeredPane = new JLayeredPane();
        this.setLayeredPane(layeredPane);
        this.setResizable(false);


        JTextPane output = new JTextPane();

        SimpleAttributeSet attribs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_RIGHT);
        StyleConstants.setFontSize(attribs,21);
        output.setParagraphAttributes(attribs, true);
        output.setText(message);
        output.setBounds(100,0,500,500);
        layeredPane.add(output);

        this.setVisible(true);
        jFrame = this;
    }
}
