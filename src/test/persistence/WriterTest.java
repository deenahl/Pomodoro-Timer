package persistence;

import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Tests for Writer class
// This class references from this repo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class WriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ToDoList list = new ToDoList();
            Writer writer = new Writer("./data/my\0illegal:filename.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testWriterEmptyToDoList() {
        try {
            ToDoList list = new ToDoList();
            Writer writer = new Writer("./data/testWriterToDoList.json");
            writer.open();
            writer.write(list);
            writer.close();

            Reader reader = new Reader("./data/testWriterEmptyToDoList.json");
            list = reader.read();
            assertEquals("To-Do List", list.getName());
            assertEquals(0, list.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralToDoList() {
        try {
            ToDoList list = new ToDoList();
            list.addTask(new Task("cpsc 210 project", 3, list.getStudyInterval()));
            list.addTask(new Task("math 221 matlab", 2, list.getStudyInterval()));
            Writer writer = new Writer("./data/testWriterGeneralToDoList.json");
            writer.open();
            writer.write(list);
            writer.close();

            Reader reader = new Reader("./data/testWriterGeneralToDoList.json");
            list = reader.read();
            assertEquals("To-Do List", list.getName());
            ArrayList<Task> tasks = list.getToDoList();
            assertEquals(2, list.size());
            checkTask("cpsc 210 project", 3, list.getStudyInterval(), tasks.get(0));
            checkTask("math 221 matlab", 2, list.getStudyInterval(), tasks.get(1));
        } catch (IOException e) {
            fail("Exception not expected here");
        }
    }
}
