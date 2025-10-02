# Bambot Chatbot
Bambot is an application that allows you to manage your task through command line instructions
on the CLI.It helps to keep track on all your current tasks which will aid you in more efficient
planning.

## Quick Start
1.Ensure that you have installed JDK 17 or above in your computer

2.Download the latest jar from Github releases

3.Go to the terminal and cd into the folder containing the jar file and run ```java -jar Bambot.jar```

[NOTE]
A folder 'data' will be created which will store the file BambotTasks.txt to contain all the tasks you have on your list

[NOTE]
All new tasks will be written into the BambotTasks.txt after you end the program by entering the command ```bye```

# Features
All commands are case insensitive
## Getting Commands
Provides a list of all possible commands available

Input Format: ```help```

Example:
```
__________________________
Here are the available commands:
list                                          - Show all tasks
todo <description>                            - Add a ToDo task
deadline <description> /by <date>             - Add a Deadline task
event <description> /from <time> /to <time>   - Add an Event task
mark <task number>                            - Mark a task
unmark <task number>                          - Unmark a task
delete <task number>                          - Delete a task
find <keyword>                                - Find tasks containing the keyword
help                                          - Show this list of commands
bye                                           - Exit the program
__________________________
```

## Add todo Task
Adds a todo task along with its description

Input Format: ```todo <description>```

Example:```todo Lecture```
will print out the TaskList after the todo task has been added
```
__________________________
1[T][ ]Lecture
__________________________
```

## Add Deadline Task
Adds a deadline task along with its description and due date

Input Format: ```deadline <description> /by <YYYY-MM-DD>```

Example: ```deadline Chemistry tutorial /by 2025-03-02```
will print out the TaskList after the deadline task has been added
```
__________________________
1[T][ ]Lecture
2[D][ ]Chemistry tutorial  (by: 2025-03-02)
__________________________
```

## Add Event Task
Adds an event task along with its description, start date and end date

Input Format: ```event <description> /from <YYYY-MM-DD> /to <YYYY-MM-DD>```

Example: ```event Birthday /from 2025-04-02 /to 2025-04-03``` 
will print out the TaskList after the event task has been added
```
__________________________
1[T][ ]Lecture
2[D][ ]Chemistry tutorial  (by: 2025-03-02)
3[E][ ]Birthday (from:2025-04-02 to:2025-04-03)
__________________________
```
[WARNING]
All date inputs must strictly follow the format as shown above else and error will occur

## Print TaskList
Shows all the tasks current store inside the TaskList. If list is empty it will print 'List is Empty'

Input Format:```list```

Example:
```
__________________________
1[T][ ]Lecture
2[D][ ]Chemistry tutorial  (by: 2025-03-02)
3[E][ ]Birthday (from:2025-04-02 to:2025-04-03)
__________________________
```
OR
```
__________________________
List is empty
__________________________
```

## Marking Task
Marks the task at the index 

Input Format:```mark <index>```

Example: ```mark 1``` will mark the first task in the TaskList and respond when successful
```
Task 1 has been successfully marked
```
```
list
__________________________
1[T][x]Lecture
2[D][ ]Chemistry tutorial  (by: 2025-03-02)
3[E][ ]Birthday (from:2025-04-02 to:2025-04-03)
__________________________
```

## Unmarking Task
Unmarks the task at the index

Input Format:```unmark <index>```

Example: ```unmark 1``` will unmark the first task in the TaskList and respond when successful
```
Task 1 has been successfully unmarked
```
```
list
__________________________
1[T][ ]Lecture
2[D][ ]Chemistry tutorial  (by: 2025-03-02)
3[E][ ]Birthday (from:2025-04-02 to:2025-04-03)
__________________________
```

## Deleting Task
Deletes the task at the index

Input Format:```delete <index>```

Example: ```delete 2``` will unmark the second task in the TaskList and respond when successful
```
Noted. I've removed this task:
 [D][ ]Chemistry tutorial  (by: 2025-03-02)
Now you have 2 tasks in the list
```

## Finding Specific Tasks
Prints out all tasks containing the keyword 

Input Format:```find <keyword>```

Example: ```find lecture``` will list all the task with the keyword 'lecture'

Given list
```
__________________________
1[T][ ]Lecture
2[E][ ]Birthday (from:2025-04-02 to:2025-04-03)
3[D][ ]cs2113 lecture  (by: 2025-03-03)
__________________________
```
Will return
```
__________________________
[T][ ]Lecture
[D][ ]cs2113 lecture  (by: 2025-03-03)
__________________________
```

## Exiting Program
 Input Format: ```bye```  will print out

```
__________________________
Bye. Hope to see you again soon!
__________________________
```
Before exiting the program and adding the newly edited TaskList into the BambotTasks.txt file




