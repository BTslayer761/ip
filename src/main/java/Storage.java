import Bambot.tasks.Event;
import Bambot.tasks.Deadline;
import Bambot.tasks.Task;
import Bambot.tasks.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Storage {
    public static final String DIVIDER = "__________________________";

    public static void printList(ArrayList<Task> myList) {
        System.out.println(DIVIDER);
        int counter = 0; // used to check if list is empty
        for (int i = 0; i < myList.size(); i++) {
            System.out.print(i + 1);
            System.out.println(myList.get(i));
            counter++;
        }
        if (counter == 0) {
            System.out.println("List is empty");
        }
        System.out.println(DIVIDER);
    }

    public static void createFile() throws IOException {
        File tasks = new File("./tasks.txt");
        if (!tasks.exists()) {
            try {
                if (tasks.createNewFile()) {
                    System.out.println("Task File has been created in" + tasks.getAbsolutePath());
                } else {
                    System.out.println("Failed to create Task File");
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file");
            }
        }
    }

    public static void writeToFile(ArrayList<Task> list) throws IOException {
        FileWriter taskFile = new FileWriter("./tasks.txt");
        for (Task task : list) {
            taskFile.write(task.toStorageString() + System.lineSeparator());
        }
        taskFile.close();
    }

    public static void writeToArray(String filePath, ArrayList<Task> list) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            boolean isDone = false;
            String line = fileScanner.nextLine();
            String[] stringComponents = line.split(",");
            int stringComponentsLength = stringComponents.length;
            if (stringComponentsLength >= 3) {
                isDone = Boolean.parseBoolean(stringComponents[2].trim());
                System.out.println("isDone: " + isDone);
            }
            switch (stringComponents[0]) {
            case "ToDo":
                list.add(new ToDo(stringComponents[1], isDone));
                break;
            case "Deadline":
                if (stringComponentsLength < 4) {
                    continue;
                }
                String doneBy = stringComponents[3];
                list.add(new Deadline(stringComponents[1], isDone, doneBy));
                break;
            case "Event":
                if (stringComponentsLength < 5) {
                    continue;
                }
                String startTime = stringComponents[3];
                String endTime = stringComponents[4];
                list.add(new Event(stringComponents[1], isDone, startTime, endTime));
                break;
            }
        }
        fileScanner.close();
    }
}
