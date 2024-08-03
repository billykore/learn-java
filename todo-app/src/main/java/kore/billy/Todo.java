package kore.billy;

public class Todo {
    private final String title;
    private final String description;
    private boolean completed;

    public Todo(String title, String description) {
        this.title = title;
        this.description = description;
        this.completed = false;
    }

    public void print() {
        System.out.printf("Title: %s\n", this.title);
        System.out.printf("Description: %s\n", this.description);
        System.out.printf("Completed: %s\n", this.completed);
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
