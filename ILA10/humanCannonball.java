import java.util.*;

public class humanCannonball {
     static class Point {
        double x, y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Pair implements Comparable<Pair> {
        double time; 
        int nodeId;  

        Pair(double time, int nodeId) {
            this.time = time;
            this.nodeId = nodeId;
        }

        @Override
        public int compareTo(Pair other) {
            return Double.compare(this.time, other.time);
        }
    }

static double distance(Point p1, Point p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        Point start = new Point(io.getDouble(), io.getDouble());
        Point end = new Point(io.getDouble(), io.getDouble());

        int n = io.getInt();
        int numNodes = n + 2; 

        Point[] locations = new Point[numNodes];
        locations[0] = start;
        locations[numNodes - 1] = end;
        for (int i = 1; i <= n; i++) {
            locations[i] = new Point(io.getDouble(), io.getDouble());
        }

        double[] minTime = new double[numNodes];
        Arrays.fill(minTime, Double.POSITIVE_INFINITY);
        minTime[0] = 0.0;

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(0.0, 0)); 

        while (!pq.isEmpty()) {
          
            Pair current = pq.poll();
            double time = current.time;
            int u = current.nodeId;

            if (time > minTime[u]) {
                continue;
            }

            if (u == numNodes - 1) {
                io.println(time); 
                io.close();       
                return;
            }

            for (int v = 0; v < numNodes; v++) {
                if (u == v) continue; 

                double dist = distance(locations[u], locations[v]);
                double edgeTime;

                if (u == 0) {
                    edgeTime = dist / 5.0;
                } else {
                    double runTime = dist / 5.0;

                    double remainingDist = Math.abs(dist - 50.0);
                    double cannonTime = 2.0 + (remainingDist / 5.0);

                    edgeTime = Math.min(runTime, cannonTime);
                }

                double newTime = minTime[u] + edgeTime;

                if (newTime < minTime[v]) {
                    minTime[v] = newTime;
                    pq.add(new Pair(newTime, v));
                }
            }
        }
        io.close(); 
    }
}
