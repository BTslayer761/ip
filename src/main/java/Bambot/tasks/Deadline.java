package Bambot.tasks;

public class Deadline extends ListItem {
    private String doneBy;

    public Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        doneBy = deadline;
    }

    public String getDoneBy() {
        return doneBy;
    }

    public String getDeadLine() {
        return doneBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + doneBy + ")";
    }

    @Override
    public String toStorageString() {
        return (this.getClass().getSimpleName() + "," + Description + "," + isDone + "," + doneBy);
    }
}
