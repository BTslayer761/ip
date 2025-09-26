import Bambot.tasks.Deadline;
import Bambot.tasks.Event;
import Bambot.tasks.ListItem;
import Bambot.tasks.ToDo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Bambot {
    public static final String DIVIDER = "__________________________";
    static ArrayList<ListItem> myList = new ArrayList<>(); //create an array with initial size 100
    static Scanner scanner = new Scanner(System.in);//create a scanner

    // A function to print the entire list out
    private static void printList() {
        System.out.println(DIVIDER);
        int counter = 0; // used to check if list is empty
        for (int i = 0; i < myList.size(); i++) {
            System.out.print(i + 1);
            System.out.println(myList.get(i));
            counter++;
        }
        if (counter == 0) {
            System.out.println("List is empty");
        }
        System.out.println(DIVIDER);
    }

    private static void createFile() throws IOException {
        File tasks = new File("./tasks.txt");
        if (!tasks.exists()) {
            try {
                if (tasks.createNewFile()) {
                    System.out.println("Task File has been created in" + tasks.getAbsolutePath());
                } else {
                    System.out.println("Failed to create Task File");
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file");
            }
        }
    }

    private static void echo() {
        while (true) {
            String message = scanner.nextLine();
            boolean validCommand = false;
            try {
                validCommand = handleCommands(message);
                validCommand = Parser.handleCommands(message, myList);
            } catch (BambotException e) {
                System.out.println(e.getMessage());
            }
            if (validCommand)
                return;
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(DIVIDER);
        System.out.println("Woof! I'm Bambot");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);
        createFile();
        Storage.writeToArray("./tasks.txt", myList);
        echo();
    }
}


