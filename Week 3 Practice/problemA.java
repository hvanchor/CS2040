import java.util.*;

public class problemA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        if (name.isEmpty()) {
            System.out.println("");
            return;
        }
        String finalName = "";
        for (int i = 0; i < name.length() - 1; i++) {
            if (name.charAt(i) != name.charAt(i+1)) {
                finalName += (name.charAt(i));
            }
        }
        finalName += (name.charAt(name.length()-1));
        System.out.println(finalName.toString());
    }
}