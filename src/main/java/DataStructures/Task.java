package DataStructures;

public class Task {
    private int id;
    private String user_name;
    private String date;
    private String status;
    private String description;

    public Task(int id, String user, String date, String status, String description) {
        this.id = id;
        this.user_name = user;
        this.date = date;
        this.status = status;
        this.description = description;

    }

    public int getId() {
        return id;
    }



    public String getUser_name() {
        return user_name;
    }


    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString(){

        return String.format("Task owner: %s Task description: %s", getUser_name(), getDescription());
    }
}
