package ui;

import model.Event;
import model.EventLog;
import model.ToDoList;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

// PomodoroAppUI represents the graphical user interface that will run the Pomodoro App
// This class references from this repo:
// https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git
public class AppUI extends JFrame implements ListSelectionListener {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private ToDoList toDoList = new ToDoList();
    private JList jlist;
    private DefaultListModel listModel;
    private JButton deleteButton;
    private AddTaskAction addTask;
    private ChangeLongBreakTimeLength changeLongBreakTimeLength;
    private ChangeShortBreakTimeLength changeShortBreakTimeLength;
    private ChangeStudyTimeLength changeStudyTimeLength;
    private DeleteTaskAction deleteTask;
    private LoadToDoList loadToDoList;
    private SaveToDoList saveToDoList;
    private StartTimer startTimer;

    // EFFECTS: constructs the PomodoroApp
    public AppUI() throws FileNotFoundException {

        super("PomodoroApp Timer");

        listModel = new DefaultListModel();
        jlist = new JList(listModel);
        initializeGraphics();

        addButtonPanel();
    }

    // represents action to be taken when user clicks the desktop to switch focus (required for key handling)
    private class DesktopFocusAction extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            AppUI.this.requestFocusInWindow();
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the JFrame to have a MouseListener, sets the size, adds the list and button display
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        addMouseListener(new DesktopFocusAction());
        setSize(WIDTH, HEIGHT);
        addListDisplay();
        addButtonPanel();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        centreOnScreen();
        setVisible(true);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                printLog(EventLog.getInstance());
                System.exit(0);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: adds a list display of tasks in the To-do list
    private void addListDisplay() {

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BorderLayout());

        listModel = new DefaultListModel();

        jlist = new JList(listModel);
        jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jlist.setSelectedIndex(0);
        jlist.addListSelectionListener(this);
        jlist.setVisibleRowCount(10);
        JScrollPane listScrollPane = new JScrollPane(jlist);

        listPanel.add(listScrollPane, BorderLayout.CENTER);
        add(listPanel, BorderLayout.NORTH);

    }

    // MODIFIES: this
    // EFFECTS: adds a button panel to the bottom of the main JFrame
    private void addButtonPanel() {

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));
        buttonPanel.setSize(new Dimension(0, 0));
        add(buttonPanel, BorderLayout.SOUTH);

        deleteTask = new DeleteTaskAction(jlist, listModel, toDoList);
        initializeDeleteButton();
        addTask = new AddTaskAction(jlist, listModel, toDoList);
        changeLongBreakTimeLength = new ChangeLongBreakTimeLength(toDoList);
        changeShortBreakTimeLength = new ChangeShortBreakTimeLength(toDoList);
        changeStudyTimeLength = new ChangeStudyTimeLength(toDoList);
        loadToDoList = new LoadToDoList(listModel);
        saveToDoList = new SaveToDoList(toDoList);
        startTimer = new StartTimer(toDoList);

        buttonPanel.add(new JButton(addTask));
        buttonPanel.add(deleteButton);
        buttonPanel.add(new JButton(startTimer));
        buttonPanel.add(new JButton(changeStudyTimeLength));
        buttonPanel.add(new JButton(changeShortBreakTimeLength));
        buttonPanel.add(new JButton(changeLongBreakTimeLength));
        buttonPanel.add(new JButton(saveToDoList));
        buttonPanel.add(new JButton(loadToDoList));

        buttonPanel.setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: initializes a deleteButton
    private void initializeDeleteButton() {

        deleteButton = new JButton(deleteTask);

    }

    // MODIFIES: this
    // EFFECTS: centers the app to the middle of the desktop
    private void centreOnScreen() {

        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);

    }

    // MODIFIES: this
    // EFFECTS: enables or disables the delete button based on the size of the to do list
    @Override
    public void valueChanged(ListSelectionEvent e) {

        if (!e.getValueIsAdjusting()) {
            deleteButton.setEnabled(jlist.getSelectedIndex() != -1);
        }
    }

    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }

    // EFFECTS: starts the application
    public static void main(String[] args) {

        try {
            new AppUI();
        } catch (FileNotFoundException y) {
            JOptionPane.showMessageDialog(null, "unable to run application: file not found",
                    "Pomodoro App", JOptionPane.WARNING_MESSAGE);
        }
    }
}
