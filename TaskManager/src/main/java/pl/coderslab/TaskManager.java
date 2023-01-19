package pl.coderslab;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import static pl.coderslab.ConsoleColors.*;

public class TaskManager {

    public static void main(String[] args) {

        System.out.println("Welcome to the Task Manager 2.0 :)");
        Scanner scanner = new Scanner(System.in);
        boolean quit = true;

        while (quit) {
            optionList();
            int task = scanner.nextInt();
            switch (task) {
                case 1: {
                    addTask();
                    break;
                }

                case 2: {
                    taskList();
                    break;
                }
                case 3: {
                    deleteTask();
                    break;
                }
                case 4: {
                    quit = false;
                    break;
                }
                default: {
                    throw new java.lang.IllegalStateException("Incorrect value, try again")
                }
            }

        }
    }
}



public static void optionList(){
    System.out.println("Please select an option:");
    System.out.println("1. Add task");
    System.out.println("2. See the list of tasks");
    System.out.println("3. Delete task");
    System.out.println("4. Quit program");
}
