# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

### 1. 
The assertion `assertTrue(3 * 0.4 == 1.2)` fails because floating-point arithmetic in computers can lead to small precision errors. This happens due to the way floating-point numbers are represented in binary. Some decimal numbers cannot be exactly represented in binary, causing slight inaccuracies during calculations. For example, when you compute `3 * 0.4`, the result might be something like `1.2000000000000002` instead of exactly `1.2`. This slight difference causes the equality check `3 * 0.4 == 1.2` to fail.
To handle floating-point comparisons correctly, it's better to check if the two numbers are close enough rather than exactly equal.

### 2. 
The assertEquals method checks if the expected and actual objects are equal using the equals() method. On the other hand, assertSame checks if both references point to the same object in memory. So, even if two objects are considered equal, assertSame will fail if they are not the same instance.

#### Example
```java
class TestAssertEqualsAndSame {

 @Test
 void testAssertEquals() {
  assertEquals("1", "1"); // assertion will pass
  assertEquals("1", new String("1")); // assertion will pass
  assertEquals(new String("1"), new String("1")); // assertion will pass
 }

 @Test
 void testAssertSame() {
  // assertion will pass
  assertSame("1", "1"); 

  /* 
    assertion will fail because the references of both objects are different.
    new String("<your string>") will create a new object
  */
  assertSame("1", new String("1"));

  // assertion will fail because the references of both objects are different
  assertSame(new String("1"), new String("1"));
 }
}
```

### 3. 
If there are conditions where the code should never reach a particular point, fail() can be used to mark that unreachable section.

#### Example: 
You're working on a project and certain conditions should never occur. You can use fail() to verify that this unexpected case isn't reached:

```python
   def process_data(input_data):
    if input_data == "valid":
        return "Processed"
    elif input_data == "invalid":
        return "Error"
    else:
        # This should never happen
        fail("Unexpected input received")
  ```
### 4. 
The shift from the ```@Test(expected = Exception.class)``` annotation in JUnit 4 to the ```assertThrows``` method in JUnit 5 provides more granular control over expected exceptions. With JUnit 4, when you specify that an exception is expected via the annotation, that exception can be thrown at any point during the execution of the test method, making it tough to pinpoint exactly where the exception is supposed to occur. On the other hand, with ```assertThrows```, you can precisely target the block of code where you expect an exception to be thrown, allowing for a more accurate and reliable check that the exception is triggered where it’s supposed to be. This approach enhances the rigor of tests and reduces false positives that might occur in JUnit 4, where an unexpected exception elsewhere in the code would also be accepted.
