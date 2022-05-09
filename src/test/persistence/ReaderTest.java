package persistence;

import org.junit.jupiter.api.Test;
import model.Task;
import model.ToDoList;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


// Test for Reader class
// This class references from this repo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class ReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        Reader reader = new Reader("./data/noSuchFile.json");
        try {
            ToDoList list = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyToDoList() {
        Reader reader = new Reader("./data/testReaderEmptyToDoList.json");
        try {
            ToDoList list = reader.read();
            assertEquals("To-Do List", list.getName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralToDoList() {
        Reader reader = new Reader("./data/testReaderGeneralToDoList.json");
        try {
            ToDoList list = reader.read();
            ArrayList<Task> tasks = list.getToDoList();
            assertEquals(2, tasks.size());
            checkTask("cpsc 210", 3, list.getStudyInterval(), tasks.get(0));
            checkTask("stat 200 webwork", 2, list.getStudyInterval(), tasks.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
