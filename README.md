# IR_Agile_Scrum_Board
Integrated Research – Graduate/Intern Software Engineer Assignment

It is implemented in Java, which is using JDK 1.6.


***Command to run IR Board App via terminal***

0.Navigation
locate the IR_Board.zip in the terminal

1. unzip under the current directory
  * unzip IR_Board.zip -d ./IR_Board

2. create a bin directory under IR_Board
  * cd IR_Board
  * mkdir bin 
  * (mkdir ./IR_Board/bin)

3. compile (in IR_Board directory, -d for destination)
  * javac -d bin -sourcepath src src/scrumBoardManagement/ScrumBoardApplication.java

4. execute (-cp for class path to search for load the main class)
  * java -cp bin scrumBoardManagement.ScrumBoardApplication

