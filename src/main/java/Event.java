public class Event extends ListItem {
    private String startTime;
    private String endTime;

    public Event(String Description, String startTime, String endTime) {
        super(Description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String toString() {
        return "[E]" + super.toString() + "(from:" + startTime + " to:" + endTime + ")";
    }
}

