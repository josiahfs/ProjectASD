public class Dijkstra {
    int nTown;
    double[][] map;
    double[] distance;
    int[] parent;
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
        parent = new int[nTown];

        for (int i = 0; i < nTown; i++) {
            distance[i] = Double.MAX_VALUE;
            selected[i] = false;
        }
        distance[src] = 0;
        parent[src]=src;

        while (true) {
            int marked = minIndex(distance, selected);

            if (marked < 0) {
                return;
            }
           // if (distance[marked] == Double.MAX_VALUE) {
             //   return;
          //  }
            selected[marked] = true;
            if (marked == dst) {
                return;
            }

            for (int j = 0; j < nTown; j++) {
                if (map[marked][j] > 0 && !selected[j]) {
                    double newDistance = distance[marked] + map[marked][j];
                    if (newDistance < distance[j]) {
                        distance[j] = newDistance;
                        parent[j]= marked;
                    }
                }
            }

        }
    }
    public SPVNode makeIntoSPVNode(int src, int dst){
        SPVNode node = new SPVNode(src,dst);
        node.distance = getDistance(src,dst);
        int k = dst;
        node.addVertex(dst);
        while(k!=src){
            node.addVertex(parent[k]);
            k=parent[k];
        }
        return node;
    }
    public void computePathForward(int start, int[] vToVisit){
        int temp = start;
        int vlength = vToVisit.length;
        double totalCost = 0;
        boolean[] visited = new boolean[nTown];
        for (int i = 0; i < nTown; i++) {
            visited[i] = false;
        }
        ArrayList<Integer> SPVArray = new ArrayList<Integer>();
        SPVArray.add(temp);
        for(int i = 0; i<vlength;i++){
            solve(temp,vToVisit[0]);
            
            PriorityQueue<SPVNode> pq = new 
             PriorityQueue<SPVNode>(vlength-i, new SPVNodeComparator());
            
            for(int j = 0; j<vlength;j++){
                if(!visited[vToVisit[j]]){
                    SPVNode tempNode = makeIntoSPVNode(temp, vToVisit[j]);
                    pq.add(tempNode);
                }
            }
            
            SPVNode min = pq.peek();
            System.out.println(i+1+"st Place: "+ min.dst+" Dist: "+min.distance);
            totalCost += min.distance;
            Iterator<Integer> iter = min.path.listIterator();
            while(iter.hasNext()){
                int n = iter.next();
                if(n!=temp){
                    SPVArray.add(n);
                }
            }
            visited[min.dst]=true;
            temp = min.dst;
        }
        System.out.print("Shortest path: ");
        Iterator<Integer> iter = SPVArray.listIterator();
        while(iter.hasNext()){
            System.out.print(iter.next() + " ");
        }
        System.out.print("\nDistance: " + totalCost);
    }
}
