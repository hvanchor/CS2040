import java.util.*;

public class problemG {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i ++) {
            String line1 = sc.nextLine();
            String line2 = sc.nextLine();
            System.out.println(line1);
            System.out.println(line2);
            System.out.println(diff(line1, line2));
            System.out.println();
        }
    }

    public static String diff(String line1, String line2) {
        String result = "";
        int length = Math.min(line1.length(), line2.length());
        for (int i = 0; i < length; i++) {
            if (line1.charAt(i) == line2.charAt(i)) {
                result += ".";
            }
            else {
                result += "*";
            }
        }
        return result;
    }
}
