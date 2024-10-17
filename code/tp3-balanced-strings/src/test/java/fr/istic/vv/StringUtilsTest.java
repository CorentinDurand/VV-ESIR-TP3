package fr.istic.vv;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;

class StringUtilsTest {

    @Test
    void testEmptyString() {
        assertTrue(isBalanced(""));  // By definition, the empty string is balanced
    }

    @Test
    void testSimpleBalancedStrings() {
        assertTrue(isBalanced("{}[]"));
        assertTrue(isBalanced("([])"));
        assertTrue(isBalanced("()"));
        assertTrue(isBalanced("{[()]}"));
    }

    @Test
    void testNestedBalancedStrings() {
        assertTrue(isBalanced("({[()]})"));
        assertTrue(isBalanced("{{[]}}"));
    }

    @Test
    void testUnbalancedStrings() {
        assertFalse(isBalanced("([)]"));  
        assertFalse(isBalanced("{[}"));  
        assertFalse(isBalanced("{"));    
        assertFalse(isBalanced(")"));    
        assertFalse(isBalanced("[(])"));
    }

    @Test
    void testStringsWithOtherCharacters() {
        assertTrue(isBalanced("a(b)c"));
        assertTrue(isBalanced("a(b{c[d]e}f)"));
        assertFalse(isBalanced("a)b(c"));  
    }

    @Test
    void testStringWithoutParentheses() {
        assertTrue(isBalanced("abc"));
        assertTrue(isBalanced("hello world!"));
    }

    @Test
    void testUnmatchedOpeningBrackets() {
        assertFalse(isBalanced("{"));
        assertFalse(isBalanced("[["));
        assertFalse(isBalanced("((("));
    }

    @Test
    void testMismatchedPairs() {
        assertFalse(isBalanced("(}"));
        assertFalse(isBalanced("{]"));
        assertFalse(isBalanced("[)"));
    }

    @Test
    void testNestedDifferentBrackets() {
        assertTrue(isBalanced("{[()]}"));
    }
}
