package Bambot.tasks;

public class ListItem {
    protected final String Description;
    protected Boolean isDone;

    public ListItem(String task, boolean isDone) {
        Description = task;
        this.isDone = false;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getDescription() {
        return Description;
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

    public String toStorageString() {
        return (this.getClass().getSimpleName() + "," + Description + "," + isDone);
    }

}
