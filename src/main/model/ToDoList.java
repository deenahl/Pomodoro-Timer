package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// ToDoList is a list of tasks to be done
public class ToDoList implements Writable {
    private ArrayList<Task> toDoList;
    private String name = "To-Do List";
    private static Interval studyInterval = new Interval("study", 1500000);
    private static Interval shortBreakInterval = new Interval("short break", 300000);
    private static Interval longBreakInterval = new Interval("long break", 1200000);
    private static Task shortBreak = new Task("short break", 1, shortBreakInterval);
    private static Task longBreak = new Task("long break", 1, longBreakInterval);


    // EFFECTS: constructs an empty To-do list
    public ToDoList() {
        toDoList = new ArrayList<>();
    }

    // EFFECTS: returns the name of the ToDoList
    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds task t to the to-do list
    public void addTask(Task t) {
        toDoList.add(t);
        EventLog.getInstance().logEvent(new Event(t.taskName
                + " task has been added to the to-do list"));
    }

    // REQUIRES: that task t is in to-do list
    // MODIFIES: this
    // EFFECTS: deletes task t from the to-do list
    public void deleteTask(Task t) {
        toDoList.remove(t);
        EventLog.getInstance().logEvent(new Event(t.taskName
                + " task has been removed from the to-do list"));
    }

    // EFFECTS: produces a list of Task, spacing each Task in the to-do list with the appropriate break interval
    public ArrayList<Task> makePomodoroSession() {
        ArrayList<Task> result = new ArrayList<>();
        int pos = 1;
        for (Task t : toDoList) {

            if (!t.taskCompleted) {
                for (int i = t.taskLength; i > 0; i--) {
                    if (!(divisibleByThree(pos))) {
                        result.add(t);
                        if (i > 1) {
                            result.add(shortBreak);
                            pos++;
                        }
                    } else {
                        result.add(t);
                        if (i > 1) {
                            result.add(longBreak);
                            pos++;
                        }
                    }
                }
            }
        }
        return result;
    }


    // EFFECTS: produces true if i is divisible by 3
    public Boolean divisibleByThree(Integer i) {
        if (i % 3 == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("toDoList", toDoListToJson());
        return json;
    }

    // EFFECTS: returns tasks in the ToDoList as a JSON Array
    private JSONArray toDoListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Task t : toDoList) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns size of to-do list
    public Integer size() {
        return toDoList.size();
    }

    // EFFECTS: returns task at index i of the to-do list
    public Task get(Integer i) {
        return toDoList.get(i);
    }

    // EFFECT: accesses shortBreakInterval field
    public Interval getShortBreakInterval() {
        return shortBreakInterval;
    }

    // EFFECT: accesses longBreakInterval field
    public Interval getLongBreakInterval() {
        return longBreakInterval;
    }

    // EFFECT: accesses studyInterval field
    public Interval getStudyInterval() {
        return studyInterval;
    }

    // EFFECT: accesses shortBreakTask field
    public Task getShortBreakTask() {
        return shortBreak;
    }

    // EFFECT: accesses longBreakTask field
    public Task getLongBreakTask() {
        return longBreak;
    }

    // EFFECT: returns to-do list
    public ArrayList<Task> getToDoList() {
        return toDoList;
    }


}
