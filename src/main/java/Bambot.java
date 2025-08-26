import java.util.ArrayList;
import java.util.Scanner;

public class Bambot {
    static List myList = new List();
    static Scanner scanner = new Scanner(System.in);
    private static void echo(){
        while(true) {
            String message = scanner.nextLine();
            if(message.equalsIgnoreCase("bye")) {
                System.out.println("__________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("__________________________");
                break;
            }
            else if(message.equalsIgnoreCase("add")) {
                System.out.print("What do you wanna add: "); // stays on same line
                String input = scanner.nextLine();
                myList.addTask(input);
                System.out.println("__________________________");
            }

            else if(message.equalsIgnoreCase("remove")) {
                System.out.print("Which index do you wanna remove: ");
                int index = scanner.nextInt();
                System.out.println("Tasked" + index + "removed: ");
                myList.deleteTask(index);

                System.out.println("__________________________");
            }
            else if(message.equalsIgnoreCase("list")) {
                myList.getToDoList();
                System.out.println("__________________________");
            }

            else {
                System.out.println("_________________________");
                System.out.println(message);
                System.out.println("__________________________");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("_________________________");
        System.out.println("Woof! I'm Bambot");
        System.out.println("What can I do for you?");
        System.out.println("__________________________");

        echo();

        System.out.println("__________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("__________________________");
    }
}
