package Bambot.tasks;

public class Event extends Task {
    private String startTime;
    private String endTime;

    public Event(String Description, boolean isDone, String startTime, String endTime) {
        super(Description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {

        return startTime;
    }

    public String getEndTime() {

        return endTime;
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

