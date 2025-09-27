import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static String filePath;

    public Storage(String filepath) throws IOException {
        filePath = filepath;
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs(); // create data/ folder if it doesn't exist
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public void writeToArray(TaskList list) throws IOException {
        Scanner fileScanner = new Scanner(new File(filePath));
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
                list.addTodoFromStorage(stringComponents[1], isDone);
                break;
            case "Deadline":
                if (stringComponentsLength < 4) {
                    continue;
                }
                String doneBy = stringComponents[3];
                list.addDeadlineFromStorage(stringComponents[1], isDone, doneBy);
                break;
            case "Event":
                if (stringComponentsLength < 5) {
                    continue;
                }
                String startTime = stringComponents[3];
                String endTime = stringComponents[4];
                list.addEventFromStorage(stringComponents[1], isDone, startTime, endTime);
                break;
            }
        }
        fileScanner.close();
    }
}
