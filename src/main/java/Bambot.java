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
        for (int i = 0; i < myList.size(); i++) {
            System.out.print(i + 1);
            myList.get(i).printTask();
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

    private static boolean handleCommands(String message) {
        String[] commandAndInput = message.split(" ", 2);
        String command = commandAndInput[0].toLowerCase();
        switch (command) {
        case "bye":
            printByeMessage();
            return true;
        case "add":
            handleAddCommand(commandAndInput);
            break;
        case "remove":
            handleRemoveCommand(commandAndInput);
            break;
        case "list":
            printList();
            break;
        case "mark":
            handleMarkCommand(commandAndInput);
            break;
        case "unmark":
            handleUnmarkCommand(commandAndInput);
            break;
        default:
            System.out.println(message);
            System.out.println(DIVIDER);
            break;
        }
        return false;
    }

    private static void handleUnmarkCommand(String[] commandAndInput) {
        int unmarkIndex = Integer.parseInt(commandAndInput[1]);
        if(unmarkIndex < 1 || unmarkIndex >= myList.size() + 1){
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        myList.get(unmarkIndex - 1).unmarkTask();
        printList();
    }

    private static void handleMarkCommand(String[] commandAndInput) {
        int markIndex = Integer.parseInt(commandAndInput[1]);
        if(markIndex < 1 || markIndex >= myList.size() + 1){
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        myList.get(markIndex - 1).markTask();
        printList();
    }

    private static void handleRemoveCommand(String[] commandAndInput) {
        myList.remove(Integer.parseInt(commandAndInput[1]) - 1);
        printList();
    }

    private static void handleAddCommand(String[] commandAndInput) {
        List_Item newItem = new List_Item(commandAndInput[1]);
        myList.add(newItem);
        printList();
    }

    private static void printByeMessage() {
        System.out.println(DIVIDER);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }
}

