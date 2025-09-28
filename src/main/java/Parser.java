import java.io.IOException;

public class Parser {
    /**
     * Identifies the different actions the user wants to do and executes different functions
     * The command identified will be base off the first input before the spacing.
     * @param message Entire input command by the user.
     * @param tasks The Tasklist which the commands/functions will execute on
     * @return true if the command is "bye" to exit out of loop else false for every other command
     **/
    public static boolean handleCommands(String message, TaskList tasks) throws BambotException, IOException {
        String[] commandAndInput = message.split(" ", 2);
        String command = commandAndInput[0].toLowerCase(); //ensure the command is recognised regardless of capitalisation
        String input = (commandAndInput.length > 1) ? commandAndInput[1] : "";
        switch (command) {
        case "bye":
            tasks.writeToFile("data/tasks.txt");
            Ui.printByeMessage();
            return true;
        case "todo":
            tasks.addToDo(input);
            break;
        case "deadline":
            tasks.addDeadline(input);
            break;
        case "event":
            tasks.addEvent(input);
            break;
        case "delete":
            tasks.deleteTask(input);
            break;
        case "list":
            tasks.printList();
            break;
        case "mark":
            tasks.markTask(input);
            break;
        case "unmark":
            tasks.unmarkTask(input);
            break;
        case "find":
            tasks.findTask(input);
            break;
        case "help":
            Ui.printCommands();
            break;
        default:
            System.out.println(message + " is not a valid command.");
            System.out.println("Use command help to list the possible commands available ");
            System.out.println(Ui.DIVIDER);
            break;
        }
        return false;
    }
}
