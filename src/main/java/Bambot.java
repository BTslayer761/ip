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
    static ArrayList<ListItem> myList = new ArrayList<>(); //create an array with initial size 100
    static Scanner scanner = new Scanner(System.in);//create a scanner

    // A function to print the entire list out

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
        System.out.println(Storage.DIVIDER);
        System.out.println("Woof! I'm Bambot");
        System.out.println("What can I do for you?");
        System.out.println(Storage.DIVIDER);
        Storage.createFile();
        Storage.writeToArray("./tasks.txt", myList);
        echo();
    }
}


