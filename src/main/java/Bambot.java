import java.util.Scanner;

public class Bambot {
    static Scanner messageInput = new Scanner(System.in);
    private static void echo(String message){
        System.out.println("_________________________");
        System.out.println(message);
        System.out.println("__________________________");
    }

    public static void main(String[] args) {
        System.out.println("_________________________");
        System.out.println("Woof! I'm Bambot");
        System.out.println("What can I do for you?");
        System.out.println("__________________________");

        String message = "";
        while(true) {
            message = messageInput.nextLine();
            if(message.equalsIgnoreCase("bye")){
                break;
            }
            else{
                echo(message);
            }
        }
        System.out.println("__________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("__________________________");
    }
}
