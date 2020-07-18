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
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width/2)-250,400);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


        this.getContentPane().setLayout(new FlowLayout());
        this.setResizable(false);


        JTextPane output = new JTextPane();
        SimpleAttributeSet attribs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(attribs,21);
        output.setParagraphAttributes(attribs, true);
        output.setText(message);
        output.setEditable(false);
        output.setBounds(0,0,500,700);

        JScrollPane scrollPane = new JScrollPane(output);
        scrollPane.setPreferredSize(new Dimension(700, 500));
//        add(scrollPane, BorderLayout.CENTER);


        this.getContentPane().add(scrollPane);

        this.setVisible(true);
        jFrame = this;
    }
}
