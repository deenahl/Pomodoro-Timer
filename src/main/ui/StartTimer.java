package ui;

import model.Event;
import model.EventLog;
import model.Task;
import model.ToDoList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

// represents an action to be taken when the user wants to start the timer
public class StartTimer extends AbstractAction {

    private ToDoList toDoList;
    private Boolean isCounterRunning;
    private JFrame timerFrame;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private JPanel timerPanel;
    private JPanel stringPanel;
    private JLabel displayString;
    private JLabel taskString;
    private JLabel timer;

    // EFFECTS: constructs an action
    public StartTimer(ToDoList toDoList) {
        super("Start Timer");

        isCounterRunning = false;
        this.toDoList = toDoList;
        timerFrame = new JFrame();
        timer = new JLabel();
    }

    // MODIFIES: this
    // EFFECTS: action to be taken when user wants to start the timer
    @Override
    public void actionPerformed(ActionEvent e) {
        initializeTimerFrame();
        ArrayList<Task> pomodoroList = toDoList.makePomodoroSession();
        isCounterRunning = true;
        startTimer(pomodoroList);
    }

    // MODIFIES: this
    // EFFECTS: sets up the timer JFrame to have a BoxLayout, sets the size, adds a MouseListener, and initializes the
    //          panels within the JFrame
    private void initializeTimerFrame() {

        timerFrame.setLayout(new BoxLayout(timerFrame.getContentPane(), BoxLayout.Y_AXIS));
        timerFrame.setSize(WIDTH / 2, HEIGHT / 2);
        initializeTimerGraphics();
        timerFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        timerFrame.setVisible(true);

        timerFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                printLog(EventLog.getInstance());
                System.exit(0);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: sets up and adds the timer panel, the string panel, and the button panel to the timer JFrame
    private void initializeTimerGraphics() {

        timerPanel = new JPanel();
        timerPanel.setLayout(new GridBagLayout());
        timerPanel.setBackground(Color.lightGray);
        timerFrame.add(timerPanel);

        stringPanel = new JPanel();
        stringPanel.setLayout(new BoxLayout(stringPanel, BoxLayout.Y_AXIS));
        displayString = new JLabel();
        taskString = new JLabel();
        stringPanel.add((displayString));
        stringPanel.add(taskString);
        timerFrame.add(stringPanel);

        addTimerButtonPanel();
    }

    // MODIFIES: this
    // EFFECTS: sets up button panel for the timer JFrame with a pause and resume button
    private void addTimerButtonPanel() {

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));
        buttonPanel.setSize(new Dimension(0, 0));
        timerFrame.add(buttonPanel);

        buttonPanel.add(new JButton(new PauseTimerAction()));
        buttonPanel.add(new JButton(new ResumeTimerAction()));

        buttonPanel.setVisible(true);
    }

    // represents the action to be taken when the user wants to pause the timer
    private class PauseTimerAction extends AbstractAction {

        // EFFECTS: constructs an action
        PauseTimerAction() {
            super("Pause Timer");
        }

        // MODIFIES: this
        // EFFECTS: action to be taken when the user wants to pause the timer
        @Override
        public void actionPerformed(ActionEvent e) {
            displayString.setText("Paused");
            isCounterRunning = false;
        }
    }

    // represents the action to be taken when the user wants to resume the timer
    private class ResumeTimerAction extends AbstractAction {

        // EFFECTS: constructs an action
        ResumeTimerAction() {
            super("Resume Timer");
        }

        // MODIFIES: this
        // EFFECTS: action to be taken when the user wants to resume the timer
        @Override
        public void actionPerformed(ActionEvent e) {
            isCounterRunning = true;
        }
    }

    // MODIFIES: this
    // EFFECTS: starts the timer
    private void startTimer(ArrayList<Task> list) {

        java.util.Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            Task task = list.get(0);
            Integer counter = task.getTaskIntervalLength();

            @Override
            public void run() {
                if (isCounterRunning) {
                    if (counter > 0) {
                        displayStringInTimer(counter, task);
                        counter -= 1000;
                    } else {
                        timer.cancel();
                        list.remove(task);
                        cancelTimer(list);
                    }
                } else {
                    task.setTaskIntervalLength(counter);
                    timer.cancel();
                    startTimer(list);
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    // MODIFIES: this
    // EFFECTS: adds the countdown and interval-specific strings to the timer JFrame
    private void displayStringInTimer(int i, Task t) {

        timerPanel.add(timer);
        timer.setFont(new Font("Serif", Font.PLAIN, 30));

        displayCountDown(i);
        displayStringForInterval(t);
    }

    // MODIFIES: this
    // EFFECTS: adds the countdown display to the timer JFrame
    private void displayCountDown(int i) {

        Integer mins = i / 60000;
        Integer secs = (i % 60000) / 1000;

        String timerText;
        if (secs > 9) {
            timerText = Integer.toString(mins) + ":" + Integer.toString((i % 60000) / 1000);
        } else {
            timerText = Integer.toString(mins) + ":0" + Integer.toString((i % 60000) / 1000);
        }
        timer.setText(timerText);
    }

    // MODIFIES: this
    // EFFECTS: sets a string to display based on the interval of the task and what the task currently is
    private void displayStringForInterval(Task task) {

        if (task.getTaskInterval().getIntervalName() == "study") {
            displayString.setText("Keep Studying!");
        } else {
            displayString.setText("Time For a Break!");
        }

        taskString.setText("Current Task: " + task.getTaskName());
    }

    // MODIFIES: this
    // EFFECTS: cancels the timer and sets a string if the list is empty, otherwise, it loops the timer to start again
    private void cancelTimer(ArrayList<Task> list) {

        if (list.size() == 0) {
            displayString.setText("Done Studying!");
        } else {
            startTimer(list);
        }
    }

    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }

}
