package Bambot.tasks;

import java.time.LocalDate;

public class Event extends Task {
    private LocalDate startTime;
    private LocalDate endTime;

    public Event(String Description, boolean isDone, LocalDate startTime, LocalDate endTime) {
        super(Description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {

        return "[E]" + super.toString() + "(from:" + startTime + " to:" + endTime + ")";
    }

    @Override
    public String toStorageString() {
        return (this.getClass().getSimpleName() + "," + Description + "," + isDone + "," + startTime + "," + endTime);
    }
}

