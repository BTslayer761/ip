import Bambot.tasks.Deadline;
import Bambot.tasks.Event;
import Bambot.tasks.Task;
import Bambot.tasks.ToDo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;


public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void writeToFile(String filePath) throws IOException {
        FileWriter taskFile = new FileWriter(filePath);
        for (Task task : tasks) {
            taskFile.write(task.toStorageString() + System.lineSeparator());
        }
        taskFile.close();
    }

    public void printList() {
        System.out.println(Ui.DIVIDER);
        int counter = 0; // used to check if list is empty
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i + 1);
            System.out.println(tasks.get(i));
            counter++;
        }
        if (counter == 0) {
            System.out.println("List is empty");
        }
        System.out.println(Ui.DIVIDER);
    }

    public void addToDo(String input) {
        ToDo newItem = new ToDo(input, false);
        String itemToAdd = newItem.toString();
        tasks.add(newItem);
        printList();
    }

    public void addDeadline(String input) throws BambotException {
        String[] inputs = input.split("/by ");
        if (inputs.length != 2) {
            throw new BambotException("Error: Invalid input format. Correct format: deadline description /from (yyyy-mm-dd)");
        }
        LocalDate deadline = LocalDate.parse(inputs[1].trim());
        Deadline newItem = new Deadline(inputs[0], false, deadline);
        tasks.add(newItem);
        printList();
    }

    public void addEvent(String input) throws BambotException {
        String[] inputs = input.split("/from ");
        if (inputs.length != 2) {
            throw new BambotException("Error: Invalid input format. Correct format: event description /from (yyyy-mm-dd) /to (yyyy-mm-dd)");
        }
        String[] timings = inputs[1].split("/to");
        if (timings.length != 2) {
            throw new BambotException("Error: Invalid input format. Correct format: event description /from (yyyy-mm-dd) /to (yyyy-mm-dd)");
        }
        LocalDate startTime = LocalDate.parse(timings[0].trim());
        LocalDate endTime = LocalDate.parse(timings[1].trim());
        if (startTime.isAfter(endTime)) {
            throw new BambotException("Error: Invalid start and end date: start date should be before end date");
        }
        Event newItem = new Event(inputs[0], false, startTime, endTime);
        tasks.add(newItem);
        printList();
    }

    public void deleteTask(String input) throws BambotException {
        int removeIndex = -1;
        try {
            removeIndex = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            throw new BambotException("Error: Input must be a number (Example: delete 2)");
        }
        try {
            Task removedTasked = tasks.remove(removeIndex);
            System.out.println("Noted. I've removed this task:");
            System.out.println(" " + removedTasked);
            System.out.println("Now you have " + tasks.size() + " tasks in the list");
        } catch (IndexOutOfBoundsException e) {
            throw new BambotException("Error: Index is out of bounds:" + input);
        }
    }

    public void unmarkTask(String input) throws BambotException {
        int unmarkIndex = -1;
        try {
            unmarkIndex = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new BambotException("Error: Input must be a number (Example: mark 2)");
        }
        try {
            Task currentTask = tasks.get(unmarkIndex - 1);
            if (!currentTask.getIsDone()) {
                throw new BambotException("Error: How can you unmark something that is not done ;-;");
            }
            currentTask.unmarkTask();
            System.out.println("Task " + unmarkIndex + " has been successfully unmarked");
        } catch (IndexOutOfBoundsException e) {
            throw new BambotException("Error: Index is out of bounds:" + input);
        }

    }

    public void markTask(String input) throws BambotException {
        int markIndex = -1;
        try {
            markIndex = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new BambotException("Error: Input must be a number (Example: mark 2)");
        }
        try {
            Task currentTask = tasks.get(markIndex - 1);
            if (currentTask.getIsDone()) {
                throw new BambotException("Error:task already marked as done");
            }
            currentTask.markTask();
            System.out.println("Task " + markIndex + " has been successfully marked");
        } catch (IndexOutOfBoundsException e) {
            throw new BambotException("Error: Index is out of bounds:" + input + "\n" + "Pls use a number in the list");
        }
    }

    public void findTask(String keyword) throws BambotException {
        int count = 1;
        System.out.println(Ui.DIVIDER);
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(task);
                count++;
            }
        }
        if (count == 1) {
            System.out.println(Ui.DIVIDER);
            throw new BambotException("Error: No task found");
        }
    }

    public void addTodoFromStorage(String input, Boolean isDone) {
        ToDo todo = new ToDo(input, isDone);
        tasks.add(todo);
    }

    public void addDeadlineFromStorage(String input, Boolean isDone, String doneBy) {
        Deadline deadline = new Deadline(input, isDone, LocalDate.parse(doneBy));
        tasks.add(deadline);
    }

    public void addEventFromStorage(String input, Boolean isDone, String startTime, String endTime) {
        Event event = new Event(input, isDone, LocalDate.parse(startTime), LocalDate.parse(endTime));
        tasks.add(event);
    }

}
