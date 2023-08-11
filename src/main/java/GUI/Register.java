package GUI;

import DB.DB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JFrame {
    private JTextField name_field;
    private JTextField cpassword_field;
    private JTextField password_field;
    private JButton doneButton;
    private JButton returnButton;
    private JPanel register_pnl;

    public Register(){
        setContentPane(register_pnl);
        setTitle("Simple GUI App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1080, 720);
        setLocationRelativeTo(null);
        setVisible(true);

        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (name_field.getText().equals("") || password_field.getText().equals("") || cpassword_field.getText().equals("")){

                    JOptionPane.showMessageDialog(null, "Please fill all fields");

                }else{
                    String name = name_field.getText();
                    if (password_field.getText().equals(cpassword_field.getText())){
                        String password = password_field.getText();
                        DB newDb = new DB();
                        newDb.insertUser(name, password);
                        newDb.closeConnection();


                    }else{

                        JOptionPane.showMessageDialog(null, "Password do not match");

                    }
                }

            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Main();
            }
        });
    }

    public static void main(String[] args){

        new Register();


    }
}
