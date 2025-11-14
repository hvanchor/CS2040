import java.util.*;

public class problemA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nR= sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < nR; i++) {
            int k = sc.nextInt();
            sc.nextLine(); //consume the newline
            String rName = sc.nextLine();
            boolean pancakes = false;
            boolean peasoup = false;
            for (int j = 0; j < k; j++) {
                String nextLine = sc.nextLine();
                if (nextLine.equals("pancakes")) {
                    pancakes = true;
                }
                if (nextLine.equals("pea soup")) {
                    peasoup = true;
                }
            }
            if (pancakes && peasoup) {
                System.out.println(rName);
                return;
            }
        }
        System.out.println("Anywhere is fine I guess");
    }
}