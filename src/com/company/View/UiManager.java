package com.company.View;

import com.company.Model.Entitys.Agent;
import com.company.Model.Entitys.Owner;
import com.company.Model.Entitys.User;
import com.company.Model.Services.Access;
import com.company.Model.Services.MemberServices;
import com.company.Model.Services.OfficerServices;
import com.company.View.AgentPanel.EmployeePanel;
import com.company.View.AgentPanel.OfficerPanel;
import com.company.View.AgentPanel.SupervisorPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UiManager extends JFrame {
    public static JFrame jFrame;
    public UiManager(){
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

        JTextField username = new JTextField();
        username.setFont(new Font("arial",Font.BOLD,27));
        username.setBounds((Toolkit.getDefaultToolkit().getScreenSize().width/2)-250,300,500,30);
        username.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(username);

        JPasswordField password = new JPasswordField();
        password.setFont(new Font("arial",Font.BOLD,27));
        password.setBounds((Toolkit.getDefaultToolkit().getScreenSize().width/2)-250,400,500,30);
        password.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(password);

        JButton login = new JButton("ورود");
        login.setBounds((Toolkit.getDefaultToolkit().getScreenSize().width/2)-50,500,100,30);
        layeredPane.add(login);
        login.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User u = new User();
                u.setPassword(String.valueOf(password.getPassword()));
                u.setUsername(username.getText());
                User user = new Access().login(u);
                if(user != null)
                {
                    UiManager.jFrame.dispose();
                    Owner o = new MemberServices().getMember(user);
                    if(o!=null)
                        new MemberPanel(o);
                    else
                    {
                        Agent a = new OfficerServices().getAgent(user);
                        if(a!=null)
                        {
                            switch (a.rank){
                                case officer:
                                    new OfficerPanel(a);
                                    break;
                                case supervisor:
                                    new SupervisorPanel(a);
                                    break;
                                case employee:
                                    new EmployeePanel(a);
                                    break;
                            }
                        }
                    }
                }
                else
                    JOptionPane.showMessageDialog(null,"نام کاربری یا گذرواژه اشتباه است","عدم دسترسی",JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton register = new JButton("یا ثبت نام کنید");
        register.setBounds((Toolkit.getDefaultToolkit().getScreenSize().width/2)-50,600,100,30);
        register.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterMember r = new RegisterMember();
                jFrame.dispose();
            }
        });
        layeredPane.add(register);

        this.setVisible(true);
        jFrame = this;
    }
}
