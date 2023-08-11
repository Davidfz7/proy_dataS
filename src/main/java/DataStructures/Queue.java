package DataStructures;

public class Queue {

    private Nodo first = null;
    private Nodo last = null;
    private Task data;


    public Queue() {

    }

    public boolean empty() {
        return this.first == null;
    }


    public Nodo getFirst() {
        return first;
    }

    public void setFirst(Nodo first) {
        this.first = first;
    }

    public Nodo getLast() {
        return last;
    }

    public void setLast(Nodo last) {
        this.last = last;
    }

    public Task getData() {
        return data;
    }

    public void setData(Task data) {
        this.data = data;
    }

    public void queueUP(int id, String user, String date, String status, String description) {
        Task tsk = new Task(id,user, date, status, description);
        Nodo newNode = new Nodo();
        newNode.setTaskData(tsk);

        if (empty()) {
            this.first = newNode;
            this.last = newNode;
        } else {
            this.last.setNext(newNode);
            this.last = newNode;
        }

    }

    public void traverseQueue() {
        Nodo current = this.first;

        while (current != null) {
            Task task = current.getTaskData();
            System.out.println("ID: " + task.getId());
            System.out.println("User: " + task.getUser_name());
            System.out.println("Date: " + task.getDate());
            System.out.println("Status: " + task.getStatus());
            System.out.println("Description: " + task.getDescription());
            System.out.println("------------------------");

            current = current.getNext();
        }
    }
}
