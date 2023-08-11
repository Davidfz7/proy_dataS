package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectOp extends JFrame {
    private JLabel wlcm_user;
    private JButton add_task;
    private JButton verifyTasksButton;
    private JPanel scOp_pnl;
    private JButton returnButton;
    private JButton priority_button;

    public SelectOp(String user) {
        setContentPane(scOp_pnl);
        setTitle("STG");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1080, 720);
        setLocationRelativeTo(null);
        setVisible(true);
        wlcm_user.setText(String.format("Welcome %s", user));

        add_task.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Add(user);

            }
        });
        verifyTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new VerifyTasks(user);
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login();
            }
        });
        priority_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new queueUpView(user);
            }
        });
    }

    }


