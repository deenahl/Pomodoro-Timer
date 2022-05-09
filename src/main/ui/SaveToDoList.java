package ui;

import model.ToDoList;
import persistence.Writer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

// represents the action to be taken when the user wants to save the to-do list with a given name
public class SaveToDoList extends AbstractAction {

    private static final String JSON_STORE_FRONT = "./data/";
    private static final String JSON_STORE_BACK = "_toDoList.json";
    private Writer jsonWriter;
    private ToDoList toDoList;

    // EFFECTS: constructs an action
    public SaveToDoList(ToDoList toDoList) {
        super("Save To-do List");
        this.toDoList = toDoList;
    }

    // MODIFIES: this
    // EFFECTS: action to be taken when the user wants to save a to-do list to a file
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = JOptionPane.showInputDialog("Name of To-do list file");
        String jsonStore = JSON_STORE_FRONT + name + JSON_STORE_BACK;
        jsonWriter = new Writer(jsonStore);

        String successMessage = "saved " + name + " to-do list to " + jsonStore;
        String warningMessage = "unable to write to file: " + jsonStore;

        try {
            jsonWriter.open();
            jsonWriter.write(toDoList);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null, successMessage, "File Saved",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException y) {
            JOptionPane.showMessageDialog(null, warningMessage, "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}
