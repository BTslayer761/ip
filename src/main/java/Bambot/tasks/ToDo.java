package Bambot.tasks;

public class ToDo extends Task {
    public ToDo(String Task, boolean isDone) {
        super(Task, isDone);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
