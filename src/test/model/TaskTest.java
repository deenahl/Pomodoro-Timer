package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// tests for Task class
public class TaskTest {

    Interval i1;
    Interval i2;
    Interval i3;
    Task t1;
    Task t2;
    Task t3;
    Task t4;
    Task t5;
    Task t6;

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
    }

    @Test
    void changeTaskLengthTest() {
        t1.changeTaskLength(5);
        t2.changeTaskLength(4);

        assertEquals(5, t1.taskLength);
        assertEquals(4, t2.taskLength);
    }

    @Test
    void getTaskIntervalLengthTest() {
        Integer il1 = t1.getTaskIntervalLength();
        Integer il5 = t5.getTaskIntervalLength();
        Integer il6 = t6.getTaskIntervalLength();

        assertEquals(1500000, il1);
        assertEquals(300000, il5);
        assertEquals(1200000, il6);
    }

    @Test
    void getTaskNameTest() {
        String n2 = t2.getTaskName();
        String n5 = t5.getTaskName();
        String n6 = t6.getTaskName();

        assertEquals("MATH 221 WebWork", n2);
        assertEquals("short break", n5);
        assertEquals("long break", n6);
    }
}