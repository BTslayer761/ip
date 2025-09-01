import java.util.ArrayList;
import java.util.Scanner;

public class Bambot {
    public static final String DIVIDER = "__________________________";
    public static final int MAX_CAPACITY = 100;
    static ArrayList<List_Item> myList = new ArrayList<>(MAX_CAPACITY); //create an array with initial size 100
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

    private static void echo() {
        while (true) {
            String message = scanner.nextLine();
            if (handleCommands(message)) return;
        }
    }

    public static void main(String[] args) {
        System.out.println(DIVIDER);
        System.out.println("Woof! I'm Bambot");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);
        echo();
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
            handleEventCommand(input);
            break;
        case "remove":
            handleRemoveCommand(input);
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
            System.out.println(message);
            System.out.println(DIVIDER);
            break;
        }
        return false;
    }

    private static void handleUnmarkCommand(String input) {
        int unmarkIndex = Integer.parseInt(input);
        if (unmarkIndex < 1 || unmarkIndex >= myList.size() + 1) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        myList.get(unmarkIndex - 1).unmarkTask();
        printList();
    }

    private static void handleMarkCommand(String input) {
        int markIndex = Integer.parseInt(input);
        if (markIndex < 1 || markIndex >= myList.size() + 1) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        myList.get(markIndex - 1).markTask();
        printList();
    }

    private static void handleRemoveCommand(String input) {
        myList.remove(Integer.parseInt(input) - 1);
        printList();
    }

    private static void handleToDoCommand(String input) {
        ToDo newItem = new ToDo(input);
        myList.add(newItem);
        printList();
    }

    private static void handleDeadlineCommand(String input) {
        String[] inputs = input.split("/by ", 2);
        Deadline newItem = new Deadline(inputs[0], inputs[1]);
        myList.add(newItem);
        printList();
    }

    private static void handleEventCommand(String input) {
        String[] inputs = input.split("/from ", 2);
        String[] timings = inputs[1].split("/to", 2);
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
}

