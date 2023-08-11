package GUI;

import DB.DB;
import DataStructures.LinkedListOwn;
import DataStructures.Nodo;
import DataStructures.Task;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class VerifyTasks extends JFrame {
    private JPanel verify_pnl;
    private JTable verify_table;
    private JPanel table_pnl;
    private JButton returnButton;
    private JComboBox status_op;

    public VerifyTasks(String user) {
        setContentPane(verify_pnl);
        setTitle("Verify tasks");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1080, 720);
        setLocationRelativeTo(null);
        setVisible(true);
        DB newDB = new DB();
        LinkedListOwn another_list = newDB.retrieveTasks(user);
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setNumRows(0);
        model.addColumn("Id");
        model.addColumn("User name");
        model.addColumn("Date");
        model.addColumn("Status");
        model.addColumn("Task Description");
        verify_table.setModel(model);

        addLinkedListDataToTableModel(another_list.getFirst(), model);

        //=====================================


        verify_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DefaultTableModel model = (DefaultTableModel) verify_table.getModel();
                int row = verify_table.getSelectedRow();
                String id = model.getValueAt(row, 0).toString();
                String name = model.getValueAt(row, 1).toString();
                String date = model.getValueAt(row, 2).toString();
                String status = model.getValueAt(row, 3).toString();
                String description = model.getValueAt(row, 4).toString();

                new ModifyTask(id, name, date, status, description);
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

    public void  addLinkedListDataToTableModel(Nodo current, DefaultTableModel model){
        if (current != null) {
            Task oTask = current.getTaskData();
            Object[] fila = {oTask.getId(), oTask.getUser_name(), oTask.getDate(), oTask.getStatus(), oTask.getDescription()};
            model.addRow(fila);
            addLinkedListDataToTableModel(current.getNext(), model); // Llamada recursiva con el siguiente nodo
        }


    }


}

