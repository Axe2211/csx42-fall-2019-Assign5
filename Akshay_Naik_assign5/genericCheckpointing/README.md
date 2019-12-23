# CSX42: Assignment 5
## Name: Checkpointing Objects

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in genericCheckpointing/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile genericCheckpointing/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile genericCheckpointing/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile genericCheckpointing/src/build.xml run -Darg0="serdeser" -Darg1="checkpoint.txt" -Darg2="verify.txt" -Darg3="NONE"

Note: Arguments accept the absolute path of the files.


-----------------------------------------------------------------------
## Description:
My program at present is able to read the Checkpoint File and parse the data 
into a data structure (just plain data, not converting it into objects yet)

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [12/9/2019]


