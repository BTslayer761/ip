public class Ui {
    public static final String DIVIDER = "__________________________";

    public Ui() {

    }

    public void printHelloMessage() {
        System.out.println(Ui.DIVIDER);
        System.out.println("Woof! I'm Bambot");
        System.out.println("What can I do for you?");
        System.out.println(Ui.DIVIDER);
    }

    public void printByeMessage() {
        System.out.println(DIVIDER);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }
}
