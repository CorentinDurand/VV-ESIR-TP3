# Test the Date class

Implement a class `Date` with the interface shown below:

```java
class Date implements Comparable<Date> {

    public Date(int day, int month, int year) { ... }

    public static boolean isValidDate(int day, int month, int year) { ... }

    public static boolean isLeapYear(int year) { ... }

    public Date nextDate() { ... }

    public Date previousDate { ... }

    public int compareTo(Date other) { ... }

}
```

The constructor throws an exception if the three given integers do not form a valid date.

`isValidDate` returns `true` if the three integers form a valid year, otherwise `false`.

`isLeapYear` says if the given integer is a leap year.

`nextDate` returns a new `Date` instance representing the date of the following day.

`previousDate` returns a new `Date` instance representing the date of the previous day.

`compareTo` follows the `Comparable` convention:

* `date.compareTo(other)` returns a positive integer if `date` is posterior to `other`
* `date.compareTo(other)` returns a negative integer if `date` is anterior to `other`
* `date.compareTo(other)` returns `0` if `date` and `other` represent the same date.
* the method throws a `NullPointerException` if `other` is `null` 

Design and implement a test suite for this `Date` class.
You may use the test cases discussed in classes as a starting point. 
Also, feel free to add any extra method you may need to the `Date` class.


Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-date](../code/tp3-date) to complete this exercise.

## Answer
1.
To create an effective test suite for your `Date` class using *Input Space Partitioning*, we will divide the input space into characteristics and blocks for each method. Below is the breakdown for each method.
The constructor will be the same as `isvalidDate`.

For `isValidDate` Method :
-Characteristics:
* Valid/Invalid year
* Valid/Invalid month
* Valid/Invalid day for a given month, handling also different month lengths and leap years
    - Blocks:
        * Year: valid (e.g., 2024), invalid (e.g., -1000)
        * Month: valid (1–12), invalid (<1, >12)
        * Day: valid for 30-day months, 31-day months, February in common years, February in leap years
        * Leap year logic: (year divisible by 4, but not 100 unless also divisible by 400)

For `isLeapYear` Method :
    - Characteristics:
        * Divisibility by 4
        * Divisibility by 100
        * Divisibility by 400
    - Blocks:
        * Divisible by 400 is a leap year
        * Divisible by 4 but not by 100, is a leap year
        * Divisible by 100 but not 400, is nott a leap year
        * Not divisible by 4, is not a leap year

For `nextDate` Method :
    - Characteristics:
        * End of month transitions
        * End of year transitions
        * Leap year February transitions
    - Blocks:
        * Non-leap year February 28 → March 1
        * Leap year February 28 → February 29
        * End of month (e.g., April 30 → May 1)
        * End of year (e.g., December 31 → January 1)
        * Middle of month (e.g., June 15 → June 16)
        
For `previousDate` Method:
    - Characteristics:
        * Start of month transitions
        * Start of year transitions
        * Leap year February transitions
    - Blocks:
        * Non-leap year March 1 → February 28
        * Leap year March 1 → February 29
        * Start of month (e.g., May 1 → April 30)
        * Start of year (e.g., January 1 → December 31 of the previous year)
        * Middle of month (e.g., June 15 → June 14)
        
For `compareTo` Method :
    - Characteristics:
        * Date earlier than other
        * Date equal to other
        * Date later than other
        * Null other argument
    - Blocks:
        * `this < other` (e.g., January 1, 2020 vs. January 2, 2020)
        * `this == other` (e.g., February 15, 2022 vs. February 15, 2022)
        * `this > other` (e.g., March 10, 2021 vs. March 9, 2021)
        * `other == null` (should throw NullPointerException)
        
The Common Characteristics
    * Year: Common between `isValidDate`, `isLeapYear`
    * Month/Day transitions: Common between `nextDate`, `previousDate`
    * Leap year handling: Common between `isValidDate`, `isLeapYear`, `nextDate`, `previousDate`
