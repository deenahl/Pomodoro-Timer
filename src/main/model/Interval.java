package model;

// Interval represents the name and the length of the intervals (in milliseconds)
// that will make up the pomodoro session
public class Interval {

    private String name;
    private Integer length;

    // EFFECTS: constructs an interval with a name and length 1500000 milliseconds
    public Interval(String name, Integer length) {
        this.name = name;
        this.length = length;
    }

    // EFFECTS: returns length of interval
    public int getIntervalLength() {
        return length;
    }

    // EFFECTS: returns the name of the interval
    public String getIntervalName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: set the interval length to the one provided
    public void setIntervalLength(int length) {
        this.length = length;
    }

    // REQUIRES: l must be in minutes
    // MODIFIES: this
    // EFFECTS: changes the interval length to l, converted into milliseconds
    public void changeAndConvertIntervalLength(Integer l) {
        length = l * 60000;
    }

}
