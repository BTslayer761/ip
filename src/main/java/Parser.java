import Bambot.tasks.Deadline;
import Bambot.tasks.Event;
import Bambot.tasks.Task;
import Bambot.tasks.ToDo;

import java.io.IOException;
import java.util.ArrayList;


public class Parser {

    public static void addToDo(String input, ArrayList<Task> myList) {
        ToDo newItem = new ToDo(input, false);
        String itemToAdd = newItem.toString();
        myList.add(newItem);
        Storage.printList(myList);
    }

    public static void addDeadline(String input, ArrayList<Task> myList) throws BambotException {
        String[] inputs = input.split("/by ");
        if (inputs.length != 2) {
            throw new BambotException("Invalid input format. Correct format: deadline description /from (deadline time)");
        }
        Deadline newItem = new Deadline(inputs[0], false, inputs[1]);
        myList.add(newItem);
        Storage.printList(myList);
    }

    public static void addEvent(String input, ArrayList<Task> myList) throws BambotException {
        String[] inputs = input.split("/from ");
        if (inputs.length != 2) {
            throw new BambotException("Invalid input format. Correct format: event description /from (start time) /to (end time)");
        }
        String[] timings = inputs[1].split("/to");
        if (timings.length != 2) {
            throw new BambotException("Invalid input format. Correct format: event description /from (start time) /to (end time)");
        }
        String startTime = timings[0];
        String endTime = timings[1];
        Event newItem = new Event(inputs[0], false, startTime, endTime);
        myList.add(newItem);
        Storage.printList(myList);
    }

    public static void deleteTask(String input, ArrayList<Task> myList) throws BambotException {
        int removeIndex = -1;
        try {
            removeIndex = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            throw new BambotException("Input must be a number (Example: delete 2)");
        }
        try {
            Task removedTasked = myList.remove(removeIndex);
            System.out.println("Noted. I've removed this task:");
            System.out.println(" " + removedTasked);
            System.out.println("Now you have " + myList.size() + " tasks in the list");
        } catch (IndexOutOfBoundsException e) {
            throw new BambotException("Index is out of bounds:" + input);
        }
    }

    public static void unmarkTask(String input, ArrayList<Task> myList) throws BambotException {
        int unmarkIndex = -1;
        try {
            unmarkIndex = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new BambotException("Input must be a number (Example: mark 2)");
        }
        try {
            myList.get(unmarkIndex - 1).unmarkTask();
            System.out.println("Task " + unmarkIndex + " has been successfully unmarked");
        } catch (IndexOutOfBoundsException e) {
            throw new BambotException("Index is out of bounds:" + input);
        }
    }

    public static void markTask(String input, ArrayList<Task> myList) throws BambotException {
        int markIndex = -1;
        try {
            markIndex = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new BambotException("Input must be a number (Example: mark 2)");
        }
        try {
            myList.get(markIndex - 1).markTask();
            System.out.println("Task " + markIndex + " has been successfully marked");
        } catch (IndexOutOfBoundsException e) {
            throw new BambotException("Index is out of bounds:" + input + "\n" + "Pls use a number in the list");
        }
    }

    public static void printByeMessage() {
        System.out.println(Storage.DIVIDER);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(Storage.DIVIDER);
    }

    public static boolean handleCommands(String message, ArrayList<Task> myList) throws BambotException {
        String[] commandAndInput = message.split(" ", 2);
        String command = commandAndInput[0].toLowerCase(); //ensure the command is recognised regardless of capitalisation
        String input = (commandAndInput.length > 1) ? commandAndInput[1] : "";
        switch (command) {
        case "bye":
            try {
                Storage.writeToFile(myList);
                printByeMessage();
            } catch (IOException e) {
                System.out.println("An error occurred while writing to file");
            }
            return true;
        case "todo":
            addToDo(input, myList);
            break;
        case "deadline":
            addDeadline(input, myList);
            break;
        case "event":
            addEvent(input, myList);
            break;
        case "delete":
            deleteTask(input, myList);
            break;
        case "list":
            Storage.printList(myList);
            break;
        case "mark":
            markTask(input, myList);
            break;
        case "unmark":
            unmarkTask(input, myList);
            break;
        default:
            System.out.println(message + " is not a valid command");
            System.out.println(Storage.DIVIDER);
            break;
        }
        return false;
    }
}
