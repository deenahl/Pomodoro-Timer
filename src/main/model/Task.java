package model;

import org.json.JSONObject;
import persistence.Writable;

// A task is consists of an interval type (with a given name and length), how many study interval it requires, the
// name of the task, and if the task is completed or not
public class Task implements Writable {

    protected Interval taskType;
    protected Integer taskLength;         // Length of how many intervals the task will take
    protected String taskName;            // Name of task
    protected Boolean taskCompleted;      // True if the task is completed, false if it is not completed

    // REQUIRES: a length > 0
    // EFFECT: constructs a Study task
    public Task(String taskName, Integer taskLength, Interval taskType) {
        this.taskType = taskType;
        this.taskName = taskName;
        this.taskLength = taskLength;
        taskCompleted = false;
    }

    // MODIFIES: this
    // EFFECT: changes the length of a task
    public void changeTaskLength(Integer i) {
        taskLength = i;
    }

    // EFFECTS: returns the interval length associated with task
    public int getTaskIntervalLength() {
        return taskType.getIntervalLength();
    }

    // EFFECTS: returns name of task
    public String getTaskName() {
        return taskName;
    }

    // EFFECTS: returns the length of the task
    public Integer getTaskLength() {
        return taskLength;
    }

    // EFFECTS: returns the Interval type of the task
    public Interval getTaskInterval() {
        return taskType;
    }

    public void setTaskIntervalLength(int i) {
        taskType.setIntervalLength(i);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("taskLength", taskLength);
        json.put("taskName", taskName);
        return json;
    }

}