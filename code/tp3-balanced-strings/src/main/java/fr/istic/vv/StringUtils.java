package fr.istic.vv;

import java.util.Stack;

public class StringUtils {

    private StringUtils() {}

    public static boolean isBalanced(String str) {
        Stack<Character> stack = new Stack<>();
        for (char ch : str.toCharArray()) {
            // Si c'est une parenthèse ouvrante, l'ajouter à la pile
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } 
            // Si c'est une parenthèse fermante, vérifier si elle correspond à celle du sommet de la pile
            else if (ch == ')' || ch == '}' || ch == ']') {
                if (stack.isEmpty()) return false;  // Aucune parenthèse ouvrante pour cette parenthèse fermante
                char top = stack.pop();
                if (!isMatchingPair(top, ch)) return false;  // Missmatched pair
            }
        }
        // La pile doit être vide si toutes les parenthèses ouvrantes avaient une parenthèse fermante correspondante
        return stack.isEmpty();
    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '{' && close == '}') ||
               (open == '[' && close == ']');
    }
}
