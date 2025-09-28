package Bambot.tasks;

import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate doneBy;

    public Deadline(String description, boolean isDone, LocalDate deadline) {
        super(description, isDone);
        doneBy = deadline;
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
