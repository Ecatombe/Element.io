# Installation note
1. Download the code from the repo
2. Go to the src/main/kotlin folder and run:
```shell
kotlinc Main.kt Exercise.kt -include-runtime -d main.jar
```
3. Execute the program via:
```shell
cat input.txt | kotlin main.jar <your_time_stamp>
```
for instance
```shell
cat input.txt | kotlin main.jar 23:59
```
# Structure and solution
I went for a simple approach where the 2 main task (reading the file and provide the solution)
are done all in the Exercise.kt file.
Once the checks on the file and the time are completed the class provide the formatted output Strings as required from the test.
I have provided a set of test for both checking the file and the output.

# What I will improve/change if I had more time
1. A better clean architecture by creating all the correct folders (domain, repo and presentation) and moving some of the task done by the Exercise class to the correct layer   
2. Write test for those layer
3. Implement a regex for the file and the timestamp 
4. A better logic in order to produce the output using timestamp instead of strings