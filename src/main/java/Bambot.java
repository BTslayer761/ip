import Bambot.tasks.Deadline;
import Bambot.tasks.Event;
import Bambot.tasks.ListItem;
import Bambot.tasks.ToDo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Bambot {
    public static final String DIVIDER = "__________________________";
    public static final int MAX_CAPACITY = 100;
    static ArrayList<ListItem> myList = new ArrayList<>(MAX_CAPACITY); //create an array with initial size 100
    static Scanner scanner = new Scanner(System.in); //create a scanner

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
        File tasks = new File("src/tasks.txt");
        if(!tasks.exists()){
            try {
                if (tasks.createNewFile()) {
                    System.out.println("Task File has been created in" + tasks.getAbsolutePath());
                } else {
                    System.out.println("Failed to create Task File");
                }
            }catch (IOException e) {
                System.out.println("An error occurred while creating the file");
            }
        }
    }

    private static void echo() {
        while (true) {
            String message = scanner.nextLine();
            if (handleCommands(message)) return;
        }
    }

    // Run different functions base on the first word(Command) in the input
    private static boolean handleCommands(String message) {
        String[] commandAndInput = message.split(" ", 2);
        String command = commandAndInput[0].toLowerCase(); //ensure the command is recognised regardless of capitalisation
        String input = (commandAndInput.length > 1) ? commandAndInput[1] : "";
        switch (command) {
        case "bye":
            printByeMessage();
            return true;
        case "todo":
            handleToDoCommand(input);
            break;
        case "deadline":
            handleDeadlineCommand(input);
            break;
        case "event":
            try {
                handleEventCommand(input);
            } catch (BambotException e) {
                System.out.println(e.getMessage());
            }
            break;
        case "delete":
            handleDeleteCommand(input);
            break;
        case "list":
            printList();
            break;
        case "mark":
            handleMarkCommand(input);
            break;
        case "unmark":
            handleUnmarkCommand(input);
            break;
        default:
            System.out.println(message + " is not a valid command");
            System.out.println(DIVIDER);
            break;
        }
        return false;
    }

    private static void handleUnmarkCommand(String input) {
        int unmarkIndex = -1;
        try {
            unmarkIndex = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Input must be a number (Example: mark 2)");
        }
        try {
            myList.get(unmarkIndex - 1).unmarkTask();
            printList();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index is out of bounds:" + input);
            System.out.println("Pls use a number in the list");
        }
    }

    private static void handleMarkCommand(String input) {
        int markIndex = -1;
        try {
            markIndex = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Input must be a number (Example: mark 2)");
        }
        try {
            myList.get(markIndex - 1).markTask();
            printList();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index is out of bounds:" + input);
            System.out.println("Pls use a number in the list");
            printList();
        }
    }

    private static void handleDeleteCommand(String input) {
        int removeIndex = -1;
        try {
            removeIndex = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Input must be a number (Example: remove 2)");
        }
        try {
            ListItem removedTasked = myList.remove(removeIndex);
            System.out.println("Noted. I've removed this task:");
            System.out.println(" " + removedTasked);
            System.out.println("Now you have " + myList.size() + " tasks in the list");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index is out of bounds:" + input);
            System.out.println("Pls use a number in the list");
            printList();
        }
    }

    private static void handleToDoCommand(String input) {
        ToDo newItem = new ToDo(input);
        myList.add(newItem);
        printList();
    }

    private static void handleDeadlineCommand(String input) {
        String[] inputs = input.split("/by ", 2);
        try {
            Deadline newItem = new Deadline(inputs[0], inputs[1]);
            myList.add(newItem);
            printList();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid input format. Correct format: deadline description /from (deadline time)");
        }
    }

    private static void handleEventCommand(String input) throws BambotException {
        String[] inputs = input.split("/from ", 2);
        if (inputs.length != 2) {
            throw new BambotException("Invalid input format. Correct format: event description /from (start time) /to (end time)");
        }
        String[] timings = inputs[1].split("/to", 2);
        if (timings.length != 2) {
            throw new BambotException("Invalid input format. Correct format: event description /from (start time) /to (end time)");
        }
        String startTime = timings[0];
        String endTime = timings[1];
        Event newItem = new Event(inputs[0], startTime, endTime);
        myList.add(newItem);
        printList();
    }

    private static void printByeMessage() {
        System.out.println(DIVIDER);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    public static void main(String[] args) throws IOException {
        System.out.println(DIVIDER);
        System.out.println("Woof! I'm Bambot");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);
        createFile();
        echo();
    }
}


