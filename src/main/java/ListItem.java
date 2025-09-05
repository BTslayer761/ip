public class ListItem {
    protected final String Description;
    protected Boolean isDone;

    public ListItem(String task) {
        Description = task;
        isDone = false;
    }

    public void markTask() {
        isDone = true;
    }

    public void unmarkTask() {
        isDone = false;
    }

    public String toString() {
        if (isDone) {
            return "[x]" + Description;
        } else {
            return "[ ]" + Description;
        }
    }

}
