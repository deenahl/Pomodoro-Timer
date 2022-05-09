//package ui;
//
//import model.Interval;
//import model.Task;
//import model.ToDoList;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Scanner;
//import java.util.Timer;
//import java.util.TimerTask;
//
//import persistence.Reader;
//import persistence.Writer;
//
//// Represents the functional methods for the UI to run
//// This class references from this repo:
//// https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
//public class PomodoroApp {
//
//    private ToDoList list = new ToDoList();
//    private ArrayList<Task> pomodoroList = new ArrayList<>();
//    private Scanner input;
//    private Task task;
//    private static final String JSON_STORE = "./data/toDoList.json";
//    private Writer jsonWriter;
//    private Reader jsonReader;
//
//    public PomodoroApp() throws FileNotFoundException {
//        jsonReader = new Reader(JSON_STORE);
//        jsonWriter = new Writer(JSON_STORE);
//        runPomodoro();
//
//    }
//
//    private void runPomodoro() {
//        String command = null;
//
//        init();
//
//        displayMenu();
//        command = input.next();
//        command = command.toLowerCase();
//
//        processCommand(command);
//
//    }
//
//
//    private void processCommand(String command) {
//        if (command.equals("a")) {
//            addTask();
//        } else if (command.equals("d")) {
//            removeTask();
//            runPomodoro();
//        } else if (command.equals("s")) {
//            runTimer();
//        } else if (command.equals("x")) {
//            changeStudyLength();
//            runPomodoro();
//        } else if (command.equals("y")) {
//            changeShortBreakLength();
//            runPomodoro();
//        } else if (command.equals("z")) {
//            changeLongBreakLength();
//            runPomodoro();
//        } else if (command.equals("n")) {
//            saveToDoList();
//        } else if (command.equals("l")) {
//            loadToDoList();
//        } else {
//            System.out.println("Selection not valid...");
//        }
//    }
//
//    private void saveToDoList() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(list);
//            jsonWriter.close();
//            System.out.println("saved " + list.getName() + " to " + JSON_STORE);
//        } catch (FileNotFoundException e) {
//            System.out.println("unable to write to file: " + JSON_STORE);
//        }
//    }
//
//    private void loadToDoList() {
//        try {
//            list = jsonReader.read();
//            System.out.println("Loaded " + list.getName() + " from " + JSON_STORE);
//            runPomodoro();
//        } catch (IOException e) {
//            System.out.println("unable to read from file: " + JSON_STORE);
//        }
//    }
//
//    private void init() {
//        input = new Scanner(System.in);
//    }
//
//    private void displayMenu() {
//
//        toDoList();
//
//        System.out.println("\nTimer");
//        System.out.println("\ta -> add task");
//        System.out.println("\td -> delete task");
//        System.out.println("\ts -> start timer");
//        System.out.println("\tx -> change study length");
//        System.out.println("\ty -> change short break length");
//        System.out.println("\tz -> change long break length");
//        System.out.println("\tn -> save to-do list to file");
//        System.out.println("\tl -> load to-do list from file");
//    }
//
//
//    private void runTimer() {
//        pomodoroList = list.makePomodoroSession();
//        startTimer(pomodoroList);
//    }
//
//    private void cancelTimer(ArrayList<Task> l) {
//        if (l.size() == 0) {
//            System.out.println("Done Studying!");
//        } else {
//            startTimer(l);
//        }
//    }
//
//    private void startTimer(ArrayList<Task> l) {
//        Timer timer = new Timer();
//        TimerTask timerTask = new TimerTask() {
//            Task task = l.get(0);
//            float counter = task.getTaskIntervalLength();
//            @Override
//            public void run() {
//                if (counter > 0) {
//                    displayTasks(l);
//                    System.out.println((counter / 60000) + ":" + ((counter % 60000) / 1000));
//                    counter -= 300000;
//                } else if (counter == 0) {
//                    endOfCounterPhrases(task);
//                    timer.cancel();
//                    l.remove(task);
//                    cancelTimer(l);
//                }
//            }
//        };
//        timer.scheduleAtFixedRate(timerTask, 0, 1000);
//    }
//
//
//    private void endOfCounterPhrases(Task t) {
//        if ((t.getTaskName().equals("short break")) || (t.getTaskName().equals("long break"))) {
//            System.out.println("break done!");
//        } else {
//            System.out.println("time for a break!");
//        }
//    }
//
//
//    private void displayTasks(ArrayList<Task> l) {
//        ArrayList<String> taskNameOnList = new ArrayList<>();
//        for (Task t : l) {
//            if (!((t.getTaskName().equals("short break")) || (t.getTaskName().equals("long break")))) {
//                if (!taskNameOnList.contains(t.getTaskName())) {
//                    taskNameOnList.add(t.getTaskName());
//                }
//            }
//        }
//        for (String s : taskNameOnList) {
//            System.out.println("• " + s);
//        }
//    }
//
//    private void addTask() {
//
//        System.out.println("Name of the Task: ");
//        String name = accessTaskName();
//        System.out.println("Number of Intervals: ");
//        Integer length = accessTaskLength();
//
//        Interval studyInterval = list.getStudyInterval();
//        task = new Task(name, length, studyInterval);
//        list.addTask(task);
//
//        runPomodoro();
//    }
//
//    private void removeTask() {
//        String deleteCommand = null;
//        Task deleteThisTask;
//        System.out.println("Select which task to be delete");
//        int counter = 1;
//
//        for (Task t : list.getToDoList()) {
//            System.out.println("\t " + counter + " -> " + t.getTaskName());
//            counter++;
//        }
//
//        deleteCommand = input.next();
//
//        int deleteTaskInteger = Integer.parseInt(deleteCommand) - 1;
//
//        deleteThisTask = list.get(deleteTaskInteger);
//        list.deleteTask(deleteThisTask);
//    }
//
//
//    private String accessTaskName() {
//        String selectName = "";
//
//        while (selectName.equals("")) {
//            selectName = input.nextLine();
//            selectName = selectName.toLowerCase();
//        }
//        return selectName;
//    }
//
//    private Integer accessTaskLength() {
//        Integer selectLength = input.nextInt();
//        return selectLength;
//    }
//
//    private void toDoList() {
//        for (Task t : list.getToDoList()) {
//            System.out.println("• " + t.getTaskName());
//        }
//    }
//
//    private void changeStudyLength() {
//        System.out.println("Study Length in Minutes: ");
//        Integer length = input.nextInt();
//        Interval newStudyBreakInterval = list.getStudyInterval();
//        newStudyBreakInterval.changeAndConvertIntervalLength(length);
//    }
//
//    private void changeShortBreakLength() {
//        System.out.println("Short Break Length in Minutes: ");
//        Integer length = input.nextInt();
//        Interval newShortBreakInterval = list.getShortBreakInterval();
//        newShortBreakInterval.changeAndConvertIntervalLength(length);
//    }
//
//    private void changeLongBreakLength() {
//        System.out.println("Short Break Length in Minutes: ");
//        Integer length = input.nextInt();
//        Interval newLongBreakInterval = list.getLongBreakInterval();
//        newLongBreakInterval.changeAndConvertIntervalLength(length);
//    }
//}
