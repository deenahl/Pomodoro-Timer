package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// tests for ToDoList class
public class ToDoListTest {

    private Interval i1;
    private Interval i2;
    private Interval i3;
    private Task t1;
    private Task t2;
    private Task t3;
    private Task t4;
    private Task t5;
    private Task t6;
    private ToDoList emptyList;
    private ToDoList list;
    private Task shortBreakTest;
    private Task longBreakTest;


    @BeforeEach
    void before() {
        i1 = new Interval("study", 1500000);
        i2 = new Interval("short break", 300000);
        i3 = new Interval("long break", 1200000);

        t1 = new Task("CPSC 210 Project", 3, i1);
        t2 = new Task("MATH 221 WebWork", 2, i1);
        t3 = new Task("STAT 200 Lab", 2, i1);
        t4 = new Task("CPSC 210 Lecture Tickets", 1, i1);
        t5 = new Task("short break", 1, i2);
        t6 = new Task("long break",1, i3);

        emptyList = new ToDoList();
        list = new ToDoList();
        list.addTask(t1);
        list.addTask(t2);
        list.addTask(t3);
        list.addTask(t4);
    }

    @Test
    void deleteTaskTest() {
        list.deleteTask(t1);
        list.deleteTask(t3);

        assertEquals(2, list.size());
        assertEquals(t2, list.get(0));
        assertEquals(t4, list.get(1));
    }

    @Test
    void makePomodoroSessionTestEmpty() {
        ArrayList<Task> result = emptyList.makePomodoroSession();

        assertEquals(0, result.size());
    }

    @Test
    void makePomodoroSessionTestNonEmptyList() {
        ArrayList<Task> result = list.makePomodoroSession();
        shortBreakTest = list.getShortBreakTask();
        longBreakTest = list.getLongBreakTask();

        assertEquals(16, result.size());
        assertEquals(t1, result.get(0));
        assertEquals(shortBreakTest, result.get(1));
        assertEquals(t1, result.get(2));
        assertEquals(shortBreakTest, result.get(3));
        assertEquals(t1, result.get(4));
        assertEquals(longBreakTest, result.get(5));
        assertEquals(t2, result.get(6));
        assertEquals(shortBreakTest, result.get(7));
        assertEquals(t2, result.get(8));
        assertEquals(shortBreakTest, result.get(9));
        assertEquals(t3, result.get(10));
        assertEquals(longBreakTest, result.get(11));
        assertEquals(t3, result.get(12));
        assertEquals(shortBreakTest, result.get(13));
        assertEquals(t4, result.get(14));
        assertEquals(shortBreakTest, result.get(15));
    }

    @Test
    void sizeTest() {
        Integer emptyResult = emptyList.size();
        Integer listResult = list.size();

        assertEquals(0, emptyResult);
        assertEquals(4, listResult);

    }
}