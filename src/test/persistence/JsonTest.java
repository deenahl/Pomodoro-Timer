package persistence;

import model.Interval;
import model.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Class that allows us to check if the JSONObject forms the correct task
public class JsonTest {
    protected void checkTask(String name, Integer length, Interval interval, Task task) {
        assertEquals(name, task.getTaskName());
        assertEquals(length, task.getTaskLength());
        assertEquals(interval, task.getTaskInterval());
    }

}
