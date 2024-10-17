package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;

public class DateTest {

    // Test cases for isValidDate
    @Test
    public void testIsValidDate() {
        // Valid date
        assertTrue(Date.isValidDate(2024, 2, 29));  // Leap year
        assertTrue(Date.isValidDate(2023, 2, 28));  // Non-leap year
        assertTrue(Date.isValidDate(2023, 4, 30));  // 30-day month

        // Invalid date
        assertFalse(Date.isValidDate(2023, 2, 29));  // Non-leap year
        assertFalse(Date.isValidDate(2023, 4, 31));  // Invalid day for month
        assertFalse(Date.isValidDate(2023, 13, 15)); // Invalid month
    }

    // Test cases for isLeapYear
    @Test
    public void testIsLeapYear() {
        assertTrue(Date.isLeapYear(2024));  // Divisible by 4 but not 100
        assertFalse(Date.isLeapYear(2023)); // Not divisible by 4
        assertFalse(Date.isLeapYear(1900)); // Divisible by 100 but not 400
        assertTrue(Date.isLeapYear(2000));  // Divisible by 400
    }

    // Test cases for nextDate
    @Test
    public void testNextDate() {
        Date date = new Date(2023, 12, 31);
        Date nextDay = date.nextDate();
        assertEquals(new Date(2024, 1, 1), nextDay);

        date = new Date(2023, 2, 28);
        nextDay = date.nextDate();
        assertEquals(new Date(2023, 3, 1), nextDay);

        date = new Date(2024, 2, 28);  // Leap year
        nextDay = date.nextDate();
        assertEquals(new Date(2024, 2, 29), nextDay);
    }

    // Test cases for previousDate
    @Test
    public void testPreviousDate() {
        Date date = new Date(2024, 1, 1);
        Date prevDay = date.previousDate();
        assertEquals(new Date(2023, 12, 31), prevDay);

        date = new Date(2023, 3, 1);
        prevDay = date.previousDate();
        assertEquals(new Date(2023, 2, 28), prevDay);

        date = new Date(2024, 3, 1);  // Leap year
        prevDay = date.previousDate();
        assertEquals(new Date(2024, 2, 29), prevDay);
    }

    // Test cases for compareTo
    @Test
    public void testCompareTo() {
        Date date1 = new Date(2023, 1, 1);
        Date date2 = new Date(2023, 1, 2);

        assertTrue(date1.compareTo(date2) < 0);  // date1 < date2
        assertTrue(date2.compareTo(date1) > 0);  // date2 > date1
        assertEquals(0, date1.compareTo(new Date(2023, 1, 1)));  // date1 == date1

        Assertions.assertThrows(NullPointerException.class, () -> {
            date1.compareTo(null);
        });
    }

    // Test case for isValidDate: negative year
    @Test
    public void testIsValidDateNegativeYear() {
        assertFalse(Date.isValidDate(-100, 10, 10));  // Negative year
    }

    // Test case for nextDate: middle of the month
    @Test
    public void testNextDateMiddleOfMonth() {
        Date date = new Date(2023, 6, 15);
        Date nextDay = date.nextDate();
        assertEquals(new Date(2023, 6, 16), nextDay);
    }

    // Test case for previousDate: middle of the month
    @Test
    public void testPreviousDateMiddleOfMonth() {
        Date date = new Date(2023, 6, 15);
        Date prevDay = date.previousDate();
        assertEquals(new Date(2023, 6, 14), prevDay);
    }
    
    
}
