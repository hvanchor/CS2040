import java.util.*;

class bracketMatching {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        Stack<Character> s = new Stack<>();
        int n = sc.nextInt();
        sc.nextLine();
        String input = sc.next();

        for (int i = 0; i < n; i++) {
            char next = input.charAt(i);
            if (next == '(' || next == '[' || next == '{') {
                s.push(next);
            } else if (next == ')' || next == ']' || next == '}') {
                if (s.empty()) {
                    System.out.println("Invalid");
                    return;
                }
                char top = s.pop();
                if ((next == ')' && top != '(') ||
                    (next == ']' && top != '[') ||
                    (next == '}' && top !='{')) {
                    System.out.println("Invalid");
                    return;
                }
            }
        }

        if (s.empty()) {
            System.out.println("Valid");
        } else {
            System.out.println("Invalid");
        }
        sc.close();
    }
}