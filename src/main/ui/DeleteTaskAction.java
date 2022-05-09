package ui;

import model.Task;
import model.ToDoList;

import javax.swing.*;
import java.awt.event.ActionEvent;

// represents the action to be taken when the user wants to delete a task from a to-do list
public class DeleteTaskAction extends AbstractAction {

    private JList jlist;
    private DefaultListModel listModel;
    private ToDoList toDoList;

    // EFFECTS: constructs an action
    public DeleteTaskAction(JList jlist, DefaultListModel listModel, ToDoList toDoList) {
        super("Delete Task");
        this.jlist = jlist;
        this.listModel = listModel;
        this.toDoList = toDoList;
    }

    // MODIFIES: this
    // EFFECTS: action to be taken when a user wants to delete a task from the to-do list
    @Override
    public void actionPerformed(ActionEvent e) {
        int index = jlist.getSelectedIndex();
        Object name = jlist.getSelectedValue();
        listModel.remove(index);

        for (Task t : toDoList.getToDoList()) {
            if (t.getTaskName() == name) {
                toDoList.deleteTask(t);
            }
        }

        int size = listModel.getSize();

        if (index == size) {
            index--;
        }

        jlist.setSelectedIndex(index);
        jlist.ensureIndexIsVisible(index);

    }
}
