public class Ui {
    public static final String DIVIDER = "__________________________";

    public Ui() {

    }

    public void printHelloMessage() {
        System.out.println(Ui.DIVIDER);
        System.out.println("Woof! I'm Bambot");
        System.out.println("What can I do for you?");
        printCommands();
    }

    public static void printByeMessage() {
        System.out.println(DIVIDER);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(Ui.DIVIDER);
    }

    public static void printCommands() {
        System.out.println(DIVIDER);
        System.out.println("Here are the available commands:");
        System.out.println("  list                              - Show all tasks");
        System.out.println("  todo <description>                - Add a ToDo task");
        System.out.println("  deadline <description> /by <date> - Add a Deadline task");
        System.out.println("  event <description> /at <time>    - Add an Event task");
        System.out.println("  done <task number>                - Mark a task as done");
        System.out.println("  delete <task number>              - Delete a task");
        System.out.println("  find <keyword>                    - Find tasks containing the keyword");
        System.out.println("  help                              - Show this list of commands");
        System.out.println("  bye                               - Exit the program");
        System.out.println(DIVIDER);
    }
}
