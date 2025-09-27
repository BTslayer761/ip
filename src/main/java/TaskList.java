import Bambot.tasks.Deadline;
import Bambot.tasks.Event;
import Bambot.tasks.Task;
import Bambot.tasks.ToDo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class TaskList {
    private final ArrayList<Task> myList;

    public TaskList() {
        myList = new ArrayList<>();
    }

    public void writeToFile(String filePath) throws IOException {
        FileWriter taskFile = new FileWriter(filePath);
        for (Task task : myList) {
            taskFile.write(task.toStorageString() + System.lineSeparator());
        }
        taskFile.close();
    }

    public void printList() {
        System.out.println(Ui.DIVIDER);
        int counter = 0; // used to check if list is empty
        for (int i = 0; i < myList.size(); i++) {
            System.out.print(i + 1);
            System.out.println(myList.get(i));
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
        myList.add(newItem);
        printList();
    }

    public void addDeadline(String input) throws BambotException {
        String[] inputs = input.split("/by ");
        if (inputs.length != 2) {
            throw new BambotException("Invalid input format. Correct format: deadline description /from (deadline time)");
        }
        Deadline newItem = new Deadline(inputs[0], false, inputs[1]);
        myList.add(newItem);
        printList();
    }

    public void addEvent(String input) throws BambotException {
        String[] inputs = input.split("/from ");
        if (inputs.length != 2) {
            throw new BambotException("Invalid input format. Correct format: event description /from (start time) /to (end time)");
        }
        String[] timings = inputs[1].split("/to");
        if (timings.length != 2) {
            throw new BambotException("Invalid input format. Correct format: event description /from (start time) /to (end time)");
        }
        String startTime = timings[0];
        String endTime = timings[1];
        Event newItem = new Event(inputs[0], false, startTime, endTime);
        myList.add(newItem);
        printList();
    }

    public void deleteTask(String input) throws BambotException {
        int removeIndex = -1;
        try {
            removeIndex = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            throw new BambotException("Input must be a number (Example: delete 2)");
        }
        try {
            Task removedTasked = myList.remove(removeIndex);
            System.out.println("Noted. I've removed this task:");
            System.out.println(" " + removedTasked);
            System.out.println("Now you have " + myList.size() + " tasks in the list");
        } catch (IndexOutOfBoundsException e) {
            throw new BambotException("Index is out of bounds:" + input);
        }
    }

    public void unmarkTask(String input) throws BambotException {
        int unmarkIndex = -1;
        try {
            unmarkIndex = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new BambotException("Input must be a number (Example: mark 2)");
        }
        try {
            myList.get(unmarkIndex - 1).unmarkTask();
            System.out.println("Task " + unmarkIndex + " has been successfully unmarked");
        } catch (IndexOutOfBoundsException e) {
            throw new BambotException("Index is out of bounds:" + input);
        }
    }

    public void markTask(String input) throws BambotException {
        int markIndex = -1;
        try {
            markIndex = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new BambotException("Input must be a number (Example: mark 2)");
        }
        try {
            myList.get(markIndex - 1).markTask();
            System.out.println("Task " + markIndex + " has been successfully marked");
        } catch (IndexOutOfBoundsException e) {
            throw new BambotException("Index is out of bounds:" + input + "\n" + "Pls use a number in the list");
        }
    }

    public void addTodoFromStorage(String input, Boolean isDone) {
        ToDo todo = new ToDo(input, isDone);
        myList.add(todo);
    }

    public void addDeadlineFromStorage(String input, Boolean isDone, String doneBy) {
        Deadline deadline = new Deadline(input, isDone, doneBy);
        myList.add(deadline);
    }

    public void addEventFromStorage(String input, Boolean isDone, String startTime, String endTime) {
        Event event = new Event(input, isDone, startTime, endTime);
        myList.add(event);
    }

}
