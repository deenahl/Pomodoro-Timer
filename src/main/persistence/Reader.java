package persistence;

import model.Interval;
import model.Task;
import model.ToDoList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads ToDoList from JSON data stored in file
// This class references from this repo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class Reader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public Reader(String source) {
        this.source = source;
    }

    // EFFECTS: reads todolist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ToDoList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseToDoList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses todolist from JSON object and returns it
    private ToDoList parseToDoList(JSONObject jsonObject) {
        ToDoList list = new ToDoList();
        addTasks(list, jsonObject);
        return list;
    }

    // MODIFIES: list
    // EFFECTS: parses todolist from JSON object and adds them to the todolist
    private void addTasks(ToDoList list, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("toDoList");
        for (Object json : jsonArray) {
            JSONObject nextTask = (JSONObject) json;
            addTask(list, nextTask);
        }
    }

    // MODIFIES: list
    // EFFECTS: parses tasks from JSON object and adds it to todolist
    private void addTask(ToDoList list, JSONObject jsonObject) {
        Interval taskType = list.getStudyInterval();
        Integer taskLength = jsonObject.getInt("taskLength");
        String taskName = jsonObject.getString("taskName");
        Task task = new Task(taskName, taskLength, taskType);
        list.addTask(task);

    }

}
