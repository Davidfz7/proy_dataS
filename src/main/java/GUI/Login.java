package GUI;

import DB.DB;
import DataStructures.LinkedListOwn;
import DataStructures.Nodo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame{
    private JPanel lgn_panel;
    private JTextField name_field;
    private JTextField password_field;
    private JButton returnButton;
    private JButton doneButton;


    public Login(){

        setContentPane(lgn_panel);
        setTitle("Simple GUI App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1080, 720);
        setLocationRelativeTo(null);
        setVisible(true);

        //Action for register button
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Main();

            }
        });

        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(name_field.getText().equals("") || password_field.getText().equals("")){

                    JOptionPane.showMessageDialog(null, "Please fill all fields");

                }else{
                    String name = name_field.getText();
                    String password = password_field.getText();
                    DB newDb = new DB();

                    LinkedListOwn my_list = newDb.retrieveData();

                    Nodo form = my_list.retrieveSingleNode(name);
                    if (form.getData().getUser().equals(name) && form.getData().getPassword().equals(password)){

                        JOptionPane.showMessageDialog(null, "Inicio de sesion exitoso");

                    }else{

                        JOptionPane.showMessageDialog(null, "Algo no matchea");
                    }
                }

            }
        });
    }



    public static void main(String[] args){

        new Login();
        int hola = 0;

        System.out.println();

    }
}
