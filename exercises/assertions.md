# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. The assertion `assertTrue(3 * 0.4 == 1.2)` fails because floating-point arithmetic in computers can lead to small precision errors. This happens due to the way floating-point numbers are represented in binary. Some decimal numbers cannot be exactly represented in binary, causing slight inaccuracies during calculations. For example, when you compute `3 * 0.4`, the result might be something like `1.2000000000000002` instead of exactly `1.2`. This slight difference causes the equality check `3 * 0.4 == 1.2` to fail.
To handle floating-point comparisons correctly, it's better to check if the two numbers are close enough rather than exactly equal.
