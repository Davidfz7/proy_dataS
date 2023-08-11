package DataStructures;

public class Nodo {
    private Nodo next;
    private User data;
    private Task taskData;

    public Nodo() {
        this.next = null;
    }

    public Nodo getNext() {
        return next;
    }

    public void setNext(Nodo next) {
        this.next = next;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    public Task getTaskData() {
        return taskData;
    }

    public void setTaskData(Task taskData) {
        this.taskData = taskData;
    }
}
