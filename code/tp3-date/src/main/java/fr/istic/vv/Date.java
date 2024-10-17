package fr.istic.vv;

import java.util.Objects;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    // Constructor throws an exception if the date is not valid
    public Date(int year, int month, int day) {
        if (!isValidDate(year, month, day)) {
            throw new IllegalArgumentException("Invalid date");
        }
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // Checks if the date is valid
    public static boolean isValidDate(int year, int month, int day) {
        if (year < 0) return false;
        if (month < 1 || month > 12) return false;
        if (day < 1) return false;

        int[] daysInMonth = {31, isLeapYear(year) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return day <= daysInMonth[month - 1];
    }

    // Determines if a year is a leap year
    public static boolean isLeapYear(int year) {
        if (year % 400 == 0) return true;
        if (year % 100 == 0) return false;
        return year % 4 == 0;
    }

    // Returns a new Date representing the next day
    public Date nextDate() {
        int[] daysInMonth = {31, isLeapYear(year) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int newDay = day + 1;
        int newMonth = month;
        int newYear = year;

        if (newDay > daysInMonth[month - 1]) {
            newDay = 1;
            newMonth++;
            if (newMonth > 12) {
                newMonth = 1;
                newYear++;
            }
        }

        return new Date(newYear, newMonth, newDay);
    }

    // Returns a new Date representing the previous day
    public Date previousDate() {
        int[] daysInMonth = {31, isLeapYear(year) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int newDay = day - 1;
        int newMonth = month;
        int newYear = year;

        if (newDay < 1) {
            newMonth--;
            if (newMonth < 1) {
                newMonth = 12;
                newYear--;
            }
            newDay = daysInMonth[newMonth - 1];
        }

        return new Date(newYear, newMonth, newDay);
    }

    // Compares this date with another
    @Override
    public int compareTo(Date other) {
        if (other == null) {
            throw new NullPointerException("Cannot compare to null");
        }

        if (this.year != other.year) {
            return this.year - other.year;
        }
        if (this.month != other.month) {
            return this.month - other.month;
        }
        return this.day - other.day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return year == date.year && month == date.month && day == date.day;
    }
}
