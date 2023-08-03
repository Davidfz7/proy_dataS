package DB;

import DataStructures.LinkedListOwn;
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

            Connection cnx = DriverManager.getConnection(url, usuario, pass);
            this.cursor = cnx.createStatement();


        } catch (SQLException e) {
            e.printStackTrace();

        }catch(IOException ie){
            System.out.println(ie);

        }
    }

    public void insertData(String new_name, String new_pass) {
        try {
            LinkedListOwn my_list = retrieveData();
            if(my_list.exist(new_name)){
                JOptionPane.showMessageDialog(null, "This username already exist in the database");

            }else {
                //Insert data into database
                String sql = String.format("INSERT INTO usuarios(name, password) VALUES ('%s', '%s')", new_name, new_pass);
                cursor.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "User registered successfully");
                cursor.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LinkedListOwn retrieveData(){
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

}
