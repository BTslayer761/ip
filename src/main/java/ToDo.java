public class ToDo extends List_Item{
    public ToDo(String Task){
        super(Task);
    }
    public String toString(){
        return "[T]" + super.toString();
    }
}
