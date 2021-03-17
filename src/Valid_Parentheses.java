/*
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
An input string is valid if:
Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
*/

import java.util.Scanner;
import java.util.Stack;

class Valid_Parentheses {

    public static boolean isValid(String s) {
        Stack<Character> st = new Stack<Character>();

        if (s.length() % 2 != 0) {
            return false;
        }
        if (s.length() == 0) {
            return true;
        }

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                st.push(')');
                continue;
            } else if (s.charAt(i) == '{') {
                st.push('}');
                continue;
            } else if (s.charAt(i) == '[') {
                st.push(']');
                continue;
            } else if (st.isEmpty() || st.pop() != s.charAt(i)) {
                return false;
            }
        }
        return st.isEmpty();
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        System.out.println("Input: ");
        String s = in.nextLine();
        System.out.println(isValid(s));
    }
}