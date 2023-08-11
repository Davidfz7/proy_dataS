package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import DB.DB;
public class ModifyTask extends JFrame {
    private JLabel name_label;
    private JLabel date_label;
    private JLabel status_label;
    private JLabel descrip_label;
    private JPanel modify_pnl;
    private JLabel selected_row;
    private JComboBox status_op;
    private JTextField textField1;
    private JButton saveButton;
    private JButton saveButton1;

    public ModifyTask(String id, String name, String date, String status, String description) {
        setContentPane(modify_pnl);
        setTitle("Verify tasks");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(420, 200);
        setLocationRelativeTo(null);
        setVisible(true);
        selected_row.setText(String.format("Selected row: %s", id));
        name_label.setText(name);
        date_label.setText(date);
        status_label.setText(status);
        descrip_label.setText(description);

        status_op.addItem("Done");
        status_op.addItem("Prioritize");
        status_op.addItem("In progress");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DB newDB = new DB();
                String newStatus = status_op.getSelectedItem().toString();
                int idTsk = Integer.parseInt(id);
                newDB.updateTask(name, newStatus, idTsk);
                newDB.closeConnection();

            }
        });
    }

    public static void main(String[] args){

//        new ModifyTask();


    }
}
