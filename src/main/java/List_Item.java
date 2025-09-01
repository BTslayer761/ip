public class List_Item {
    protected final String Description;
    protected Boolean isDone;

    public List_Item(String task) {
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
        if(isDone){
            return "[x]" + Description;
        }
        else{
            return "[ ]" + Description;
        }
    }

}
