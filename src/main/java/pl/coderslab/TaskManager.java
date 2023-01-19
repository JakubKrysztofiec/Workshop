package pl.coderslab;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.*;
import java.util.Scanner;

import static pl.coderslab.ConsoleColors.*;

public class TaskManager {

    public static void main(String[] args) throws IOException {

        System.out.println("Welcome to the Task Manager 2.0 :)");
        Scanner scanner = new Scanner(System.in);
        File file = new File("tasks.csv");
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
                    deleteTask("tasks.csv");
                    break;
                }
                case 4: {
                    quit = false;
                    System.out.println("See you next time!");
                    break;
                }
                default: {
                    throw new java.lang.IllegalStateException("Incorrect value, try again");
                }
            }
            System.out.println("\n" + ("-").repeat(100) + "\n" );

        }
    }


    public static void optionList() {
        System.out.println("Please select an option:");
        System.out.println("1. Add task");
        System.out.println("2. See the list of tasks");
        System.out.println("3. Delete task");
        System.out.println("4. Quit program");
    }

    public static void addTask() {
        try(FileWriter FileWriter = new FileWriter("tasks.csv", true)) {
            System.out.println("Please write the description of the task");
            Scanner scanner = new Scanner(System.in);
            FileWriter.append(scanner.nextLine().trim()).append(", ");
            System.out.println("Please write the due date of the task");
            FileWriter.append(scanner.nextLine().trim() + ", ");
            System.out.println("Is the task a priority? True/false");
            FileWriter.append(scanner.nextLine().trim() + "\n");
        }
        catch (IOException e) {
            System.out.println("File not found!");
        }
    }

    public static void taskList() throws FileNotFoundException {

        Scanner scan = new Scanner(new File("tasks.csv"));
        int i = 1;
        while(scan.hasNextLine()){
            System.out.println(i + ". " + scan.nextLine());
            i++;
        }
    }

    public static void deleteTask(String filepath){
        System.out.println("Please select the index of the task you wish to delete: ");
        Scanner scanner = new Scanner(System.in);
        int indexDelete = scanner.nextInt();

        String tempFile = "temp.csv";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);

        int index = 0;
        String currentLine;

        try{
            FileWriter fileWriter = new FileWriter(tempFile,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            FileReader fileReader = new FileReader(filepath);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((currentLine = bufferedReader.readLine()) != null){
                index++;
                if(indexDelete != index){
                    printWriter.println(currentLine);
                }
            }

            printWriter.flush();
            printWriter.close();
            fileReader.close();
            bufferedReader.close();
            bufferedWriter.close();
            fileWriter.close();

            oldFile.delete();
            File dump = new File(filepath);
            newFile.renameTo(dump);
            System.out.println("Task was successfully deleted.");
        }
        catch(Exception e){
            throw new java.lang.IllegalStateException("Error! Try again.");
        }
    }
}