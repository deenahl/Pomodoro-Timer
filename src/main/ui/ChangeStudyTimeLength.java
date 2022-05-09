package ui;

import model.Interval;
import model.ToDoList;

import javax.swing.*;
import java.awt.event.ActionEvent;

// represents the action to be taken when the user wants to change the length of the study time in minutes
public class ChangeStudyTimeLength extends AbstractAction {

    private ToDoList toDoList;

    // EFFECTS: constructs an action
    public ChangeStudyTimeLength(ToDoList toDoList) {
        super("Change Study Time Length");
        this.toDoList = toDoList;
    }

    // MODIFIES: this
    // EFFECTS: action to be taken when the user wants to change the study time length
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane optionPane = new JOptionPane();
        String newLengthString = JOptionPane.showInputDialog("Study Time Length in Minutes: ");
        int newLength = Integer.parseInt(newLengthString);
        Interval newStudyInterval = toDoList.getStudyInterval();
        newStudyInterval.changeAndConvertIntervalLength(newLength);

        optionPane.setVisible(true);
    }
}
