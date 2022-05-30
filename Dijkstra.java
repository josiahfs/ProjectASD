public class Dijkstra {
    int nTown;
    double[][] map;
    double[] distance;
    int src;

    public Dijkstra(double[][] map) {
        this.map = map;
        nTown = map.length;
    }

    int minIndex(double[] distance, boolean[] selected) {
        double dist = Double.MAX_VALUE;
        int idx = -1;
        for (int i = 0; i < nTown; i++) {
            if (!selected[i] && distance[i] < dist) {
                dist = distance[i];
                idx = i;
            }
        }
        return idx;
    }


    public double getDistance(int src, int dest) {
        return distance[dest];
    }
    public void solve(int src, int dst) {
        this.src = src;
        boolean[] selected = new boolean[nTown];
        distance = new double[nTown];

        for (int i = 0; i < nTown; i++) {
            distance[i] = Double.MAX_VALUE;
            selected[i] = false;
        }
        distance[src] = 0;

        while (true) {
            int marked = minIndex(distance, selected);

            if (marked < 0) {
                return;
            }
            if (distance[marked] == Double.MAX_VALUE) {
                return;
            }
            selected[marked] = true;
            if (marked == dst) {
                return;
            }

            for (int j = 0; j < nTown; j++) {
                if (map[marked][j] > 0 && !selected[j]) {
                    double newDistance = distance[marked] + map[marked][j];
                    if (newDistance < distance[j]) {
                        distance[j] = newDistance;
                    }
                }
            }

        }
    }
}