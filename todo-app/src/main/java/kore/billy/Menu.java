package kore.billy;

import java.util.Scanner;

public class Menu {
    private final TodoList todoList;
    private final Scanner scanner;
    private boolean keepGoing = true;

    public Menu(TodoList todoList, Scanner scanner) {
        this.todoList = todoList;
        this.scanner = scanner;
    }

    public void start() {
        while (keepGoing) {
            display();
            processOption();
        }
    }

    private void display() {
        System.out.println("""
                Welcome to Todo List App
                Select an option:
                  1. Display Todo List
                  2. Add Todo
                  3. Delete Todo
                  4. Complete Todo
                  5. Reset
                  0. Exit
                """);
    }

    private void processOption() {
        printTitle("Your option: ");
        String option = scanner.nextLine();
        useOption(option);
    }

    private void useOption(String option) {
        switch (option) {
            case "1":
                displayTodoListOption();
                break;
            case "2":
                addTodoOption();
                break;
            case "3":
                deleteTodoOption();
                break;
            case "4":
                completeTodoOption();
                break;
            case "5":
                resetOption();
                break;
            case "0":
                exitOption();
                break;
        }
    }

    private void displayTodoListOption() {
        this.todoList.getTodos();
    }

    private void addTodoOption() {
        printTitle("Title: ");
        String title = this.scanner.nextLine();

        printTitle("Description: ");
        String description = this.scanner.nextLine();

        Todo newTodo = new Todo(title, description);
        this.todoList.add(newTodo);

        System.out.println("Todo added");
    }

    private void deleteTodoOption() {
        printTitle("Todo id: ");
        String id = this.scanner.nextLine();
        this.todoList.delete(Integer.parseInt(id));
        System.out.println("Todo deleted");
    }

    private void completeTodoOption() {
        printTitle("Todo id: ");
        String id = this.scanner.nextLine();
        this.todoList.completeTodo(Integer.parseInt(id));
        System.out.println("Todo completed");
    }

    private void exitOption() {
        keepGoing = false;
    }

    private void resetOption() {
        this.todoList.resetTodos();
    }

    private void printTitle(String title) {
        System.out.print(title);
    }
}
