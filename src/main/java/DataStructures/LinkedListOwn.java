package DataStructures;

import javax.swing.*;


public class LinkedListOwn {

    private Nodo first;

    public LinkedListOwn() {

        this.first = null;

    }

    public Nodo getFirst() {
        return first;
    }

    public void setFirst(Nodo first) {
        this.first = first;
    }

    public boolean empty() {

        return first == null;

    }

    public void insertData(int id, String user, String password) {

        User newUser = new User();
        newUser.setId(id);
        newUser.setUser(user);
        newUser.setPassword(password);

        Nodo nodo_new = new Nodo();

        nodo_new.setData(newUser);

        if (empty() || newUser.getId() < first.getData().getId()) {
            nodo_new.setNext(first);
            first = nodo_new;

        } else {
            Nodo aux = first;
            while (aux.getNext() != null && aux.getData().getId() < nodo_new.getData().getId()) {
                aux = aux.getNext();
            }

            nodo_new.setNext(aux.getNext());
            aux.setNext(nodo_new);

        }

    }

    public void insertTask(int id, String user, String date, String status, String description){


        Task newTsk = new Task(id, user, date, status, description);
        Nodo newNode = new Nodo();
        newNode.setTaskData(newTsk);

        if (empty() || newTsk.getId() < first.getTaskData().getId()) {
            newNode.setNext(first);
            first = newNode;

        } else {
            Nodo aux = first;
            while (aux.getNext() != null && aux.getTaskData().getId() < newNode.getTaskData().getId()) {
                aux = aux.getNext();
            }

            newNode.setNext(aux.getNext());
            aux.setNext(newNode);

        }

    }

    public String mostrar() {
        if (empty()) {
            return "[]";
        } else {
            Nodo aux = first;
            String minha_lista = "[ "+first.getData().toString();
            while (aux.getNext() != null) {
                minha_lista += ", " + aux.getNext().getData();
                aux = aux.getNext();

            }
            return minha_lista + " ]";
        }
    }
    public String mostrarTsks() {
        if (empty()) {
            return "[]";
        } else {
            Nodo aux = first;
            String minha_lista = "[ "+first.getTaskData().toString();
            while (aux.getNext() != null) {
                minha_lista += ", " + aux.getNext().getTaskData();
                aux = aux.getNext();

            }
            return minha_lista + " ]";
        }
    }


    public boolean exist(String name) {
        if (empty()) {
            return false;
        }

        Nodo aux = first;
        while (aux != null) {
            if (aux.getData().getUser().equals(name)) {
                System.out.println(aux.getData().toString());
                return true;
            }
            aux = aux.getNext();
        }

        return false;
    }

    public Nodo retrieveSingleNode(String name){
            if(exist(name)){
                Nodo aux = first;
                while(aux != null){
                    if(aux.getData().getUser().equals(name)){
                        return aux;

                    }
                    aux = aux.getNext();
                }

            }else{

                JOptionPane.showMessageDialog(null, "there is no user with this username :)");

            }
        return null;
    }

}
