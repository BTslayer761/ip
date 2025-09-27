import java.io.IOException;

public class Parser {


    public static boolean handleCommands(String message, TaskList tasks) throws BambotException, IOException {
        String[] commandAndInput = message.split(" ", 2);
        String command = commandAndInput[0].toLowerCase(); //ensure the command is recognised regardless of capitalisation
        String input = (commandAndInput.length > 1) ? commandAndInput[1] : "";
        switch (command) {
        case "bye":
            tasks.writeToFile("data/tasks.txt");
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
        default:
            System.out.println(message + " is not a valid command");
            System.out.println(Ui.DIVIDER);
            break;
        }
        return false;
    }
}
