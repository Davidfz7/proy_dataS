package GUI;

import DB.DB;
import DataStructures.LinkedListOwn;
import DataStructures.Task;
import DataStructures.Nodo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



public class Add extends JFrame {
    private JTable task_table;
    private JPanel add_pnl;
    private JTextField task_field;
    private JPanel table_panel;
    private JButton addTaskButton;
    private JLabel return_labl;
    private JLabel user_label;
    private JLabel add_taskLabel;

    private int count = 0;
    public Add(String current_user) {
        // Configurar la ventana
        setContentPane(add_pnl);
        setTitle("Add task");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1080, 720);
        setLocationRelativeTo(null);
        setVisible(true);

        //Table config=========================
        DB newDB = new DB();
        LinkedListOwn another_list = newDB.retrieveTasks(current_user);
        DefaultTableModel model = new DefaultTableModel(){

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;}

        };
        model.setNumRows(0);
        model.addColumn("User name");
        model.addColumn("Date");
        model.addColumn("Status");
        model.addColumn("Task Description");
        task_table.setModel(model);
        if (another_list != null){
            addLinkedListDataToTableModel(another_list.getFirst(), model);
        }

        //=====================================
        user_label.setText(current_user);
        return_labl.setText("");
        return_labl.setIcon(imageResizer("images\\150519.png"));
        add_taskLabel.setText("");
        add_taskLabel.setIcon(imageResizer("images\\6783643.png"));
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(dateFormatter);


        return_labl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new SelectOp(current_user);
            }
        });
        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DB newDB = new DB();
                newDB.addTask(current_user,formattedDate, "Pending", task_field.getText());
                LinkedListOwn updatedList = newDB.retrieveTasks(current_user);
                model.setRowCount(0);
                addLinkedListDataToTableModel(updatedList.getFirst(), model);
                model.fireTableDataChanged();
                newDB.closeConnection();
            }
        });


    }

    public void  addLinkedListDataToTableModel(Nodo current, DefaultTableModel model){
        if (current != null) {
            Task oTask = current.getTaskData();
            Object[] fila = {oTask.getUser_name(), oTask.getDate(), oTask.getStatus(), oTask.getDescription()};
            model.addRow(fila);
            addLinkedListDataToTableModel(current.getNext(), model); // Llamada recursiva con el siguiente nodo
        }


    }
    private ImageIcon imageResizer(String imagePath){

        try{
            BufferedImage bufferedImage = ImageIO.read(new File(imagePath));
            Image image = bufferedImage.getScaledInstance(50, 50, Image.SCALE_DEFAULT);

            return new ImageIcon(image);
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }


}


