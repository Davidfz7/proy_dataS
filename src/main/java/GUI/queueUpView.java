package GUI;

import DB.DB;
import DataStructures.Queue;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class queueUpView extends JFrame {



    private JButton verListaDePrioridadButton;
    private JPanel queue_pnl;
    private JButton returnButton;

    public queueUpView(String user) {
    setContentPane(queue_pnl);
    setTitle("Queue Panel");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(1080, 720);
    setLocationRelativeTo(null);
    setVisible(true);




    verListaDePrioridadButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            DB newDB = new DB();
            Queue laQiu= newDB.prioTasks(user);

            laQiu.traverseQueue();
            newDB.closeConnection();

        }
    });


        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SelectOp(user);
            }
        });
    }


}
