public class Deadline extends List_Item{
    protected String doneBy;
    public Deadline(String description, String deadline) {
        super(description);
        doneBy = deadline;
    }

    public String getDeadLine(){
        return doneBy;
    }

    public String toString(){
        return "[D]" + super.toString() + " (by: " + doneBy + ")";
    }
}
