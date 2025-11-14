import java.util.*;

public class planks {
    private static int plankIdCounter = 0;

    private static class PlankWeight implements Comparable<PlankWeight> {
        final int weight;
        final int id;

        PlankWeight(int weight, int id) {
            this.weight = weight;
            this.id = id;
        }

        @Override
        public int compareTo(PlankWeight other) {
            int weightCompare = Integer.compare(this.weight, other.weight);
            if (weightCompare != 0) {
                return weightCompare;
            }
            return Integer.compare(this.id, other.id);
        }
    }

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int q = io.getInt();
        TreeMap<Integer, TreeSet<PlankWeight>> tm = new TreeMap<>();

        for (int i = 0; i < q; i++) {
            String operation = io.getWord();

            if (operation.equals("a")) {
                int w = io.getInt();
                int l = io.getInt();
                PlankWeight newPlank = new PlankWeight(w, ++plankIdCounter);
                tm.computeIfAbsent(l, k -> new TreeSet<>()).add(newPlank);

            } else {
                int x = io.getInt();
                // Longest Plank <= x, lightest weight
                Integer aL = tm.floorKey(x);
                TreeSet<PlankWeight> aSet = tm.get(aL);
                PlankWeight aPW = aSet.pollFirst();
                if (aSet.isEmpty()) {
                    tm.remove(aL);
                }
                // Shortest Plank >= x, heaviest weight
                Integer bL = tm.ceilingKey(x);
                TreeSet<PlankWeight> bSet = tm.get(bL);
                PlankWeight bPW = bSet.pollLast();
                if (bSet.isEmpty()) {
                    tm.remove(bL);
                }

                long aW = aPW.weight;
                long bW = bPW.weight;

                long e = (1L + aW + bW) * (1L + Math.abs(aL - bL));
                System.out.println(e);
            }
        }
    io.close();
   }
}
