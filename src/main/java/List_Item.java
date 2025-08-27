public class List_Item {
    private final String Description;
    private Boolean isDone;

    public List_Item(String task){
        Description = task;
        isDone = false;
    }

    private void printCheckBox(){
        if(isDone){
            System.out.print("[x]");
        }
        else{
            System.out.print("[ ]");
        }
    }

    public void printTask(){
        printCheckBox();
        System.out.print(" ");
        System.out.println(Description);
    }

    public void markTask(){
        isDone = true;
    }

    public void unmarkTask(){
        isDone = false;
    }
}
