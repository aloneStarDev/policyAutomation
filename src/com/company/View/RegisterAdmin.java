package com.company.View;

import com.company.Model.Entitys.*;
import com.company.Model.Services.Access;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegisterAdmin extends JFrame{
    static JFrame jFrame;
    public RegisterAdmin(){
        super("ثبت مامور جدید");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JLayeredPane layeredPane = new JLayeredPane();
        this.setLayeredPane(layeredPane);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setResizable(false);

        JLabel nameLbl = new JLabel("نام");
        nameLbl.setBounds(100,300,100,20);
        nameLbl.setFont(new Font("arial",Font.BOLD,27));
        layeredPane.add(nameLbl);

        JTextField name = new JTextField();
        name.setBounds(200,290,400,40);
        name.setFont(new Font("arial",Font.BOLD,27));
        layeredPane.add(name);

        JLabel lastNameLbl = new JLabel("نام خانوادگی");
        lastNameLbl.setBounds(70,400,200,25);
        lastNameLbl.setFont(new Font("arial",Font.BOLD,25));
        layeredPane.add(lastNameLbl);


        JTextField lastName = new JTextField();
        lastName.setBounds(200,390,400,40);
        lastName.setFont(new Font("arial",Font.BOLD,27));
        layeredPane.add(lastName);

        JLabel PhoneNumberLbl = new JLabel("شماره تماس");
        PhoneNumberLbl.setBounds(650,300,200,25);
        PhoneNumberLbl.setFont(new Font("arial",Font.BOLD,25));
        layeredPane.add(PhoneNumberLbl);

        JTextField PhoneNumber = new JTextField();
        PhoneNumber.setBounds(770,290,400,40);
        PhoneNumber.setFont(new Font("arial",Font.BOLD,27));
        layeredPane.add(PhoneNumber);

        JLabel NationCodeLbl = new JLabel("کد ملی");
        NationCodeLbl.setBounds(700,400,200,25);
        NationCodeLbl.setFont(new Font("arial",Font.BOLD,25));
        layeredPane.add(NationCodeLbl);

        JTextField NationCode = new JTextField();
        NationCode.setBounds(770,390,400,40);
        NationCode.setFont(new Font("arial",Font.BOLD,27));
        layeredPane.add(NationCode);

        JLabel AddressLbl = new JLabel("آدرس");
        AddressLbl.setBounds(75,500,200,25);
        AddressLbl.setFont(new Font("arial",Font.BOLD,25));
        layeredPane.add(AddressLbl);

        JTextArea Address = new JTextArea();
        Address.setBounds(200,490,400,200);
        Address.setFont(new Font("arial",Font.BOLD,27));
        layeredPane.add(Address);

        JLabel accesslable = new JLabel("سطح دسترسی");
        accesslable.setBounds(700,490,250,25);
        accesslable.setFont(new Font("arial",Font.BOLD,25));
        layeredPane.add(accesslable);

        JComboBox<String> militaryRank = new JComboBox<>();
        militaryRank.setBounds(900,490,100,30);
        militaryRank.addItem("مامور");
        militaryRank.addItem("کارمند");
        militaryRank.addItem("ارشد");
        layeredPane.add(militaryRank);

        JButton register = new JButton("ثبت نام");
        register.setBounds(700,550,200,50);
        register.setFont(new Font("arial",Font.BOLD,27));
        register.setForeground(Color.white);
        register.setBackground(Color.darkGray);
        register.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Person p = new Person();
                    p.setFirstName(name.getText());
                    p.setLastName(lastName.getText());
                    p.setNationCode(NationCode.getText());
                    p.setAddress(Address.getText());
                    p.setPhoneNumber(PhoneNumber.getText());
                    String password =JOptionPane.showInputDialog("لطفا برای این حساب کاربری گذرواژه انتخاب کنید");
                    if(password==null || password.isEmpty()){
                        JOptionPane.showMessageDialog(jFrame,"گذرواژه نمی تواند خالی بماند");
                        return;
                    }
                    User u =new Access().Register(p,password);
                    Agent a = new Agent();
                    a.setPerson(p);
                    a.user=u;
                    a.rank = MilitaryRank.values()[militaryRank.getSelectedIndex()];
                    new Access().RegisterAgent(a);
                    JOptionPane.showMessageDialog(null,"اطلاعات حساب کاربری ذخیره شد و حساب با نام کاربری کد ملی کاربر ایجاد شد","ثبت شد",JOptionPane.INFORMATION_MESSAGE);
                    jFrame.dispose();
                } catch (ValidationException validationException) {
                    JOptionPane.showMessageDialog(null, validationException.getMessage(),"اخطار",JOptionPane.WARNING_MESSAGE);
                }

            }
        });
        layeredPane.add(register);

        this.setVisible(true);
        jFrame = this;
    }
}
