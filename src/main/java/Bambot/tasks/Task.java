package Bambot.tasks;

public class Task {
    protected final String Description;
    protected Boolean isDone;

    public Task(String task, boolean isDone) {
        Description = task;
        this.isDone = isDone;
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

    //Reformats how Task is stored into Storage (File)
    public String toStorageString() {
        return (this.getClass().getSimpleName() + "," + Description + "," + isDone);
    }

}
