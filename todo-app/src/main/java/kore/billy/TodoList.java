package kore.billy;

import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private final List<Todo> todos;

    public TodoList() {
        this.todos = new ArrayList<>();
    }

    public void add(Todo todo) {
        this.todos.add(todo);
    }

    public void delete(int id) {
        this.todos.remove(id);
    }

    public void getTodos() {
        System.out.println("\nTodos:");
        if (this.todos.isEmpty()) {
            System.out.println("(empty)");
        }
        for (int i = 0; i < this.todos.size(); i++) {
            System.out.println("Id: " + (i + 1));
            todos.get(i).print();
        }
    }

    public void completeTodo(int id) {
        Todo todo = this.todos.get(id);
        todo.setCompleted(true);
    }

    public void resetTodos() {
        this.todos.clear();
    }
}
