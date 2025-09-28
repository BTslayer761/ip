import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static String filePath;

    /**
     * This is a Constructor to ensure that the parent directory of the specified file path exists.
     * Creates the directory if it does not exit.
     * Creates the file at the file path if it does not exist
     *
      * @param filepath the path to the file used for storing and loading task data
     * @throws IOException if an IO error occurs while creating the file
     */
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

    /**
     * Reads from file at the given filePath and identifies what is the class of each object represented at each line.
     * Identifies all the variables of each object which are separated by a ','.
     * Add to the Tasklist from the param each Object based on their class and variables.
     *
     * @param list the Tasklist instance to which the tasks from the file will be added to.
     * @throws IOException if an IO error occurs while reading the file.
     */
    public void writeToArray(TaskList list) throws IOException {
        Scanner fileScanner = new Scanner(new File(filePath));
        while (fileScanner.hasNextLine()) {
            boolean isDone = false;
            String line = fileScanner.nextLine();
            String[] stringComponents = line.split(",");
            int stringComponentsLength = stringComponents.length;
            if (stringComponentsLength >= 3) {
                isDone = Boolean.parseBoolean(stringComponents[2].trim());
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
