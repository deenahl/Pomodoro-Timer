package ui;

import model.Interval;
import model.ToDoList;

import javax.swing.*;
import java.awt.event.ActionEvent;

// represents the action to be taken when the user wants to change the length of the short break in minutes
public class ChangeShortBreakTimeLength extends AbstractAction {

    private ToDoList toDoList;

    // EFFECTS: constructs an action
    public ChangeShortBreakTimeLength(ToDoList toDoList) {
        super("Change Short Break Time Length");
        this.toDoList = toDoList;
    }

    // MODIFIES: this
    // EFFECTS: action to be taken when the user wants to change the short break time length
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane optionPane = new JOptionPane();
        String newLengthString = JOptionPane.showInputDialog("Study Time Length in Minutes: ");
        int newLength = Integer.parseInt(newLengthString);
        Interval newShortStudyBreakInterval = toDoList.getShortBreakInterval();
        newShortStudyBreakInterval.changeAndConvertIntervalLength(newLength);

        optionPane.setVisible(true);
    }
}
