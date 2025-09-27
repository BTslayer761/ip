import java.io.IOException;

public class Parser {


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
