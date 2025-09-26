import Bambot.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bambot {
    static ArrayList<Task> myList = new ArrayList<>(); //create an array with initial size 100
    static Scanner scanner = new Scanner(System.in);//create a scanner

    // A function to print the entire list out

    private static void echo() {
        while (true) {
            String message = scanner.nextLine();
            boolean validCommand = false;
            try {
                validCommand = Parser.handleCommands(message, myList);
            } catch (BambotException e) {
                System.out.println(e.getMessage());
            }
            if (validCommand)
                return;
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(Storage.DIVIDER);
        System.out.println("Woof! I'm Bambot");
        System.out.println("What can I do for you?");
        System.out.println(Storage.DIVIDER);
        Storage.createFile();
        Storage.writeToArray("./tasks.txt", myList);
        echo();
    }
}


