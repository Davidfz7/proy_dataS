package DB;

import DataStructures.LinkedListOwn;
import DataStructures.Queue;
import org.yaml.snakeyaml.Yaml;


import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.LinkedList;
import java.util.Map;


public class DB {
    private String url;
    private String usuario;
    private String pass;
    Connection cnx;

    private Statement cursor = null;
    //Starting the connection
    public DB(){

        try{
            InputStream inputStream = new FileInputStream(new File("src\\main\\java\\DB\\db.yaml"));
            Yaml yaml = new Yaml();
            Map<String, Object> data = yaml.load(inputStream);

            url = data.get("url").toString();
            usuario = data.get("user").toString();
            pass = data.get("password").toString();

            this.cnx = DriverManager.getConnection(url, usuario, pass);
            this.cursor = cnx.createStatement();


        } catch (SQLException e) {
            e.printStackTrace();

        }catch(IOException ie){
            System.out.println(ie);

        }
    }

    public void insertUser(String new_name, String new_pass) {
        try {
            LinkedListOwn my_list = retrieveUsers();
            if(my_list.exist(new_name)){
                JOptionPane.showMessageDialog(null, "This username already exist in the database");

            }else {
                //Insert data into database
                String sql = String.format("INSERT INTO usuarios(name, password) VALUES ('%s', '%s')", new_name, new_pass);
                cursor.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "User registered successfully");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public LinkedListOwn retrieveUsers(){
        try {
            LinkedListOwn my_list = new LinkedListOwn();
        //Retrieve all data in database.
            ResultSet info = cursor.executeQuery("SELECT * FROM usuarios;");
            while (info.next()) {

                int id = info.getInt("id");
                String name = info.getString("name");
                String password = info.getString("password");
                my_list.insertData(id, name, password);

            }
            info.close();
            return my_list;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
    }


    }

    public LinkedListOwn retrieveTasks(String user) {
        try {
            LinkedListOwn my_list = new LinkedListOwn();
            // Retrieve all data in database.
            ResultSet info = cursor.executeQuery(String.format("SELECT * FROM tareas WHERE name = '%s'", user ));
            if (!info.next()) {
                return null;
            } else {
                do {
                    int id = info.getInt("id");
                    String name = info.getString("name");
                    String date = info.getString("date");
                    String status = info.getString("status");
                    String description = info.getString("description");
                    my_list.insertTask(id, name, date, status, description);
                } while (info.next());

                info.close();
                return my_list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public Queue prioTasks(String user){
        try {
            Queue prioQueue = new Queue();
            //Retrieve all data in database.
            ResultSet info = cursor.executeQuery(String.format("SELECT * FROM tareas WHERE name = '%s' AND status = 'Prioritize'", user ));
            if (!info.next()) {
                return null;
            } else {
                do {
                    int id = info.getInt("id");
                    String name = info.getString("name");
                    String date = info.getString("date");
                    String status = info.getString("status");
                    String description = info.getString("description");
                    prioQueue.queueUP(id, name, date, status, description);
                } while (info.next());
            }
                info.close();
                return prioQueue;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addTask(String user, String date, String status, String description) {
        try{



            //Insert data into database
            String sql = String.format("INSERT INTO tareas (name, date, status, description)\n" +
                    "VALUES ('%s', '%s', '%s', '%s');", user, date, status, description);
            cursor.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Task added successfully");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTask(String user, String status, int id) {
        try{

            //Insert data into database
            String sql = String.format("UPDATE tareas SET status = '%s' WHERE name = '%s' AND id = %d;", status, user, id);
            cursor.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Task modified successfully");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (cnx != null) {
                cnx.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
