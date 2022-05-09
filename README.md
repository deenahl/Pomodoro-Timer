# My Personal Project

## Pomodoro Timer

Pomodoro is a study method that breaks down studying time into *study* and *break* intervals

Each Pomodoro session
typically consists of 3 study intervals and 2 short break intervals and 1 long break interval. The timer will start the 
study interval, which will begin counting down to zero. Once the *study* interval is finished, it will switch to the 
*break* interval and begin counting down, and switch back to the *study* interval at the end. The timer will also have 
a to-do/task functionality, allowing students to keep track of what they have to do for each *study* interval. 

As a student, I have tested many ways of studying and have found that this method works best for me. It
builds in mental breaks within the study session, which allows me to study for longer periods of time without losing 
focus. 

## User Stories

*Phase 1 User Stories*
- As a user, I want to be able to start the timer
- As a user, I want to be able to change the length of the study and break intervals
- As a user, I want to be able to add/delete tasks to my to-do list
- As a user, I want to be able to assign how many study intervals it will take for each task

*Phase 2 User Stories*
- As a user, I want to be able to save my to-do list to file
- As a user, I want to be able to upload a saved to-do list from file
- As a user, I want to display my to-do list

*Phase 3 Stories*
- As a user, I want to be able to add multiple tasks to a to-do list using a button, and to create a Pomodoro session
  based on the tasks in the to-do list in a GUI
- As a user, I want to be able to load and save the state of the application using buttons in a GUI
- As a user, I want to be able to pause and resume the timer


## Phase 4: Task 2

Thu Nov 25 20:44:17 PST 2021  
cpsc 210 project task has been added to the to-do list  
Thu Nov 25 20:44:27 PST 2021  
math 221 webwork task has been added to the to-do list  
Thu Nov 25 20:44:39 PST 2021  
stat 200 webwork task has been added to the to-do list  
Thu Nov 25 20:44:42 PST 2021  
math 221 webwork task has been removed from the to-do list  
Thu Nov 25 20:44:50 PST 2021  
stat 200 lab task has been added to the to-do list

## Phase 4: Task 3

![UML Diagram](UML%20Diagram.pdf)
- I would change the interval class to an enum to clean up its usage in my program
- I would change the Change...TimeLength classes in the UI to implement an interface
- I'm sure that I can refactor the StartTimer class to make it a little cleaner