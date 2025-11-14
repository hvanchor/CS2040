import java.util.*;

public class problemA {
    public static void main(String[] args) {
        String s = new Scanner(System.in).nextLine();
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0)); // first character
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) == '-') {
                sb.append(s.charAt(i));
            }
        }
        System.out.println(sb);
    }
}