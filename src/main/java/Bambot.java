import java.util.ArrayList;
import java.util.Scanner;

public class Bambot {
    public static final String divider = "__________________________";
    static ArrayList<List_Item> myList = new ArrayList<>(100); //create an array with initial size 100
    static Scanner scanner = new Scanner(System.in); //create a scanner

    // A function to print the entire list out
    private static void printList(){
        for (int i = 0; i < myList.size(); i++){
            System.out.print(i+1);
            myList.get(i).printTask();
        }
    }

    private static void echo(){
        while(true) {
            String message = scanner.nextLine();
            if(message.equalsIgnoreCase("bye")) {
                System.out.println(divider);
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println(divider);
                break;
            }
            else if(message.equalsIgnoreCase("add")) {
                System.out.print("What do you wanna add: "); // stays on same line
                String description = scanner.nextLine();
                List_Item newItem = new List_Item(description);
                myList.add(newItem);
                System.out.println(divider);
            }

            else if(message.equalsIgnoreCase("remove")) {
                System.out.print("Which index do you wanna remove: ");
                int index = scanner.nextInt();
                System.out.println("Tasked " + index + " removed ");
                myList.remove(index-1);
            }
            else if(message.equalsIgnoreCase("list")) {
                printList();
                System.out.println(divider);
            }
            else if(message.equalsIgnoreCase("mark")) {
                System.out.println(divider);
                printList();
                System.out.println(divider);
                System.out.print("Which task have you completed? :");
                int index = scanner.nextInt();
                System.out.println("Tasked marked");
                myList.get(index-1).markTask();
            }
            else if(message.equalsIgnoreCase("unmark")) {
                System.out.println(divider);
                printList();
                System.out.println(divider);
                System.out.print("Which task do you want to unmark? :");
                int index = scanner.nextInt();
                System.out.println("Tasked unmarked");
                myList.get(index-1).unmarkTask();
            }

            else {
                System.out.println(message);
                System.out.println(divider);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(divider);
        System.out.println("Woof! I'm Bambot");
        System.out.println("What can I do for you?");
        System.out.println(divider);

        echo();
    }
}
