public class List {
    private final List_Item[] toDoList;
    private int numberOfTask;

    public List(){ //Create a list object with 100 element capacity
        toDoList = new List_Item[100];
        numberOfTask = 0;
    }



    // print out entire list with numbering
    public void getToDoList(){
        for(int i = 1; i < numberOfTask + 1; i++){
            System.out.print( i + ".");
            toDoList[i-1].printTask();
        };
    }

    public void addTask(String task){
        toDoList[numberOfTask] = new List_Item(task);
        numberOfTask++;
        System.out.println("Added: " + task);
    }

    public void deleteTask(int index){
        if (index < 0 || index > numberOfTask) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        //shift elements to the left
        for(int j = index - 1; j < numberOfTask - 1; j++){
            toDoList[j] = toDoList[j + 1];
        }
        toDoList[numberOfTask-1] = null;
        numberOfTask--;
    }

}
