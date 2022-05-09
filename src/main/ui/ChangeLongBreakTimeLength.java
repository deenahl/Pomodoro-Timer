package ui;

import model.Interval;
import model.ToDoList;

import javax.swing.*;
import java.awt.event.ActionEvent;

// represents the action to be taken when the user wants to change the length of the long break in minutes
public class ChangeLongBreakTimeLength extends AbstractAction {

    private ToDoList toDoList;

    // EFFECTS: constructs an action
    public ChangeLongBreakTimeLength(ToDoList toDoList) {
        super("Change Long Break Time Length");
        this.toDoList = toDoList;
    }

    // MODIFIES: this
    // EFFECTS: action to be taken when the user wants to change the long break time length
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane optionPane = new JOptionPane();
        String newLengthString = JOptionPane.showInputDialog("Study Time Length in Minutes: ");
        int newLength = Integer.parseInt(newLengthString);
        Interval newLongStudyBreakInterval = toDoList.getLongBreakInterval();
        newLongStudyBreakInterval.changeAndConvertIntervalLength(newLength);

        optionPane.setVisible(true);
    }
}
