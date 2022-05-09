package ui;

import model.Task;
import model.ToDoList;

import javax.swing.*;
import java.awt.event.ActionEvent;

// represents an action to be taken when the user wants to add a task to the to-do list
public class AddTaskAction extends AbstractAction {

    private JList jlist;
    private DefaultListModel listModel;
    private ToDoList toDoList;

    // EFFECTS: constructs an action
    public AddTaskAction(JList jlist, DefaultListModel listModel, ToDoList toDoList) {
        super("Add Task");
        this.jlist = jlist;
        this.listModel = listModel;
        this.toDoList = toDoList;

    }

    // MODIFIES: this
    // EFFECTS: action to be taken when the user wants to add a task to the to-do list
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = JOptionPane.showInputDialog("Task Name");
        String length = JOptionPane.showInputDialog("Number of Study Intervals ("
                + Integer.toString(toDoList.getStudyInterval().getIntervalLength() / 60000) + " minutes each)");
        int taskLength = Integer.parseInt(length);

        Task task = new Task(name, taskLength, toDoList.getStudyInterval());
        toDoList.addTask(task);

        int index = jlist.getSelectedIndex();
        if (index == -1) {
            index = 0;
        } else {
            index++;
        }

        listModel.addElement(name);
        jlist.setSelectedIndex(index);
        jlist.ensureIndexIsVisible(index);
    }
}
