package ui;

import model.Task;
import model.ToDoList;
import persistence.Reader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

// represents the action to be taken when the user wants to load a saved to-do list
public class LoadToDoList extends AbstractAction {

    private Reader jsonReader;
    private static final String JSON_STORE_FRONT = "./data/";
    private ToDoList toDoList;
    private DefaultListModel listModel;

    // EFFECTS: constructs an action
    public LoadToDoList(DefaultListModel listModel) {
        super("Load To-do List");

        this.listModel = listModel;
    }

    // MODIFIES: this
    // EFFECTS: action to be taken when the user wants to load a saved to-do list
    @Override
    public void actionPerformed(ActionEvent e) {

        File folder = new File("./data");
        File[] files = folder.listFiles();
        ArrayList<String> fileNames = new ArrayList<>();

        assert files != null;
        for (File f : files) {
            fileNames.add(f.getName());
        }

        String[] arrayFileNames = fileNames.toArray(new String[0]);
        Object fileName = JOptionPane.showInputDialog(null, "Choose File",
                "Load To-do List", JOptionPane.QUESTION_MESSAGE, null, arrayFileNames,
                arrayFileNames[1]);
        jsonReader = new Reader(JSON_STORE_FRONT + fileName.toString());

        try {
            toDoList = jsonReader.read();
            JOptionPane.showMessageDialog(null, "Loaded To-Do List from" + fileName,
                    "Successful Loading Message", JOptionPane.INFORMATION_MESSAGE);

            for (Task t : toDoList.getToDoList()) {
                listModel.addElement(t.getTaskName());
            }
        } catch (IOException i) {
            JOptionPane.showMessageDialog(null, "Unable to read from file: " + fileName,
                    "Error Message", JOptionPane.WARNING_MESSAGE);
        }
    }

}
