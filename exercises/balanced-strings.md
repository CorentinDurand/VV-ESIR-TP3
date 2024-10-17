# Balanced strings

A string containing grouping symbols `{}[]()` is said to be balanced if every open symbol `{[(` has a matching closed symbol `)]}` and the substrings before, after and between each pair of symbols is also balanced. The empty string is considered as balanced.

For example: `{[][]}({})` is balanced, while `][`, `([)]`, `{`, `{(}{}` are not.

Implement the following method:

```java
public static boolean isBalanced(String str) {
    ...
}
```

`isBalanced` returns `true` if `str` is balanced according to the rules explained above. Otherwise, it returns `false`.

Use the coverage criteria studied in classes as follows:

1. Use input space partitioning to design an initial set of inputs. Explain below the characteristics and partition blocks you identified.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators, check if the test cases written so far satisfy *Base Choice Coverage*. If needed, add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Write below the actions you took on each step and the results you obtained.
Use the project in [tp3-balanced-strings](../code/tp3-balanced-strings) to complete this exercise.

## Answer

1. 

I Input Length (Empty vs Non-Empty)
The first partition block considers whether the input string is empty or non-empty. An empty string is always considered balanced by definition, as there are no symbols to match. This is a special case that must be handled separately in the code. On the other hand, non-empty strings can contain various types of characters and must be further analyzed for balancing. These two cases ensure that both trivial and non-trivial strings are covered by the test cases.

II- Presence of Grouping Symbols
This partition block focuses on whether the string contains grouping symbols like parentheses, brackets, or braces. We divide this block into:

-Strings with no grouping symbols: These are strings that contain only letters, numbers, or other non-grouping characters. Since no symbols need matching, such strings are considered balanced.
-Strings with only grouping symbols: These strings contain only parentheses, braces, or brackets, which are the core symbols the method must evaluate. The function should check whether these symbols are properly balanced or not.
-Strings with mixed content: These strings contain a mix of grouping symbols and non-grouping symbols. The function should ignore the non-grouping characters while checking for balance. This block ensures that the method handles realistic input containing a combination of symbols and regular text.

III- Balanced vs. Unbalanced Grouping Symbols
Within the non-empty strings containing grouping symbols, we further partition based on whether the symbols are properly balanced. A string is considered balanced if every opening symbol has a matching closing symbol in the correct order. The unbalanced cases are divided into several subcategories:

-Missing closing brackets: These are cases where there are unmatched opening symbols that never get closed.
-Missing opening brackets: Here, closing symbols appear without a corresponding opening symbol.
-Improper nesting: These cases involve symbols that are not nested correctly, where an inner set of symbols is closed before an outer one. These subcategories ensure that both simple and complex unbalanced scenarios are tested.

IV- Type of Grouping Symbols
This partition block separates inputs based on the types of grouping symbols used. Some strings contain only one type of grouping symbol, such as only parentheses or only brackets. These cases are simpler, as the method needs to match symbols of the same type. However, other strings may contain multiple types of grouping symbols, like a combination of parentheses, braces, and brackets. The method must correctly manage these different types and ensure that each opening symbol is matched by the correct type of closing symbol. Testing both cases ensures that the method can handle homogeneous and heterogeneous symbols accurately.

2. After evaluating the statement coverage of the test cases, we achieved 100% coverage so we did not add any additionnal tests.

3. To satisfy Base Choice Coverage, we located predicates that uses more than two boolean operators. Then, we had to ensure that all possible outcomes are covered by the test cases. After reviewing the test cases, we decided to add these two tests : 

```java
@Test
void testNestedDifferentBrackets() {
    assertTrue(isBalanced("{[()]}"));
}

@Test
void testMismatchedPairs() {
    assertFalse(isBalanced("(}"));
    assertFalse(isBalanced("{]"));
    assertFalse(isBalanced("[)"));
}
```

4. 
