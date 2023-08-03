package GUI;

import DB.DB;

import javax.naming.Name;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends  JFrame {
    private JPanel main_pnl;
    private JButton rgts_btn;
    private JButton lgn_btn;


    public Main(){
        setContentPane(main_pnl);
        setTitle("Simple GUI App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1080, 720);
        setLocationRelativeTo(null);
        setVisible(true);

        //Action for register button
        rgts_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Register();

            }
        });



        //Action for login button

        lgn_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login();
            }
        });


    }
    public static void main(String[] args){

        new Main();


    }

}
