public class ToDo extends ListItem {
    public ToDo(String Task) {
        super(Task);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
