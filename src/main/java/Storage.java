import Bambot.tasks.Event;
import Bambot.tasks.Deadline;
import Bambot.tasks.ListItem;
import Bambot.tasks.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public static void writeToFile(ArrayList<ListItem> list) throws IOException {
        FileWriter taskFile = new FileWriter("src/tasks.txt");
        for (ListItem listItem : list) {
            taskFile.write(listItem.toStorageString() + System.lineSeparator());
        }
        taskFile.close();
    }

    public static void writeToArray(String filePath, ArrayList<ListItem> list) throws IOException {
        File file = new File(filePath);
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            boolean isDone = false;
            String line = fileScanner.nextLine();
            String[] stringComponents = line.split(",");
            int stringComponentsLength = stringComponents.length;
            if (stringComponentsLength >= 3) {
                isDone = Boolean.parseBoolean(stringComponents[2]);
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
    }
}
