import java.io.IOException;
import java.util.Scanner;

public class Bambot {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    static Scanner scanner = new Scanner(System.in);//create a scanner

    public Bambot(String filePath) throws IOException {
        storage = new Storage(filePath);
        tasks = new TaskList();
        ui = new Ui();
        storage.writeToArray(tasks);
    }


    private void run() {
        ui.printHelloMessage();
        while (true) {
            String message = scanner.nextLine();
            boolean validCommand = false;
            try {
                validCommand = Parser.handleCommands(message, tasks);
            } catch (BambotException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (validCommand)
                return;
        }
    }

    public static void main(String[] args) throws IOException {
        new Bambot("data/tasks.txt").run();
    }
}


