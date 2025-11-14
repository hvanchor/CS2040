import java.util.*;

public class problemF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int result = 0;
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int power = lastDigit(n);
            int newN = n/10;
            double newResult = Math.pow(newN, power);
            result += (int) newResult;
        }
        System.out.println(result);
    }

    public static int lastDigit(int x) {
        return x % 10;
    }
}
