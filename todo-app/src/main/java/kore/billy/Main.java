package kore.billy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TodoList todoList = new TodoList();
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu(todoList, scanner);
        menu.start();
    }
}
