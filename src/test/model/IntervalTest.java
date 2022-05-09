package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Test for Interval class
public class IntervalTest {

    Interval i1;
    Interval i2;
    Interval i3;

    @BeforeEach
    void before() {
        i1 = new Interval("study", 1500000);
        i2 = new Interval("short break", 300000);
        i3 = new Interval("long break", 1200000);
    }

    @Test
    void getIntervalLengthTest() {
        int length1 = i1.getIntervalLength();
        int length2 = i2.getIntervalLength();
        int length3 = i3.getIntervalLength();

        assertEquals(1500000, length1);
        assertEquals(300000, length2);
        assertEquals(1200000, length3);
    }

    @Test
    void changeIntervalLengthTest() {
        i1.changeAndConvertIntervalLength(2 );
        i2.changeAndConvertIntervalLength(100);
        assertEquals(2 * 60000, i1.getIntervalLength());
        assertEquals(100 * 60000, i2.getIntervalLength());

    }


}
