import java.util.*;

class SPVNode {
    public LinkedList<Integer> path;
    public double distance;
    int src;
    int dst;
    
    public SPVNode(int src, int dst){
        this.distance = 0;
        path = new LinkedList<Integer>();
        this.src = src;
        this.dst = dst;
    }
    public void addVertex(int vertex){
        path.addFirst(vertex);
    }
}

class SPVNodeComparator implements Comparator<SPVNode>{

    SPVNodeComparator() {

    }
    public int compare(SPVNode s1, SPVNode s2){
        double EPS = 1e-9;
        double tmp;
        
        if(Math.abs(s1.distance-s2.distance)>EPS){
            tmp=s1.distance-s2.distance;
            if(tmp>EPS) return 1;
            else return -1;
        }
        else{
            return 0;
        }
    }
}

class Dijkstra {
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


    // index tiap kota
    // 0 = Brebes           15 = Semarang
    // 1 = Tegal            16 = Salatiga
    // 2 = Slawi            17 = Boyolali
    // 3 = Purwokerto       18 = Klaten
    // 4 = Cilacap          19 = Demak
    // 5 = Kroya            20 = Kudus
    // 6 = Pemalang         21 = Purwodadi
    // 7 = Purbalingga      22 = Solo
    // 8 = Kebumen          23 = Sragen
    // 9 = Banjarnegara     24 = Sukoharjo
    // 10 = Pekalongan      25 = Wonogiri
    // 11 = Purworejo       26 = Blora
    // 12 = Wonosobo        27 = Rembang
    // 13 = Temanggung      28 = Magelang
    // 14 = Kendal


public class Project {
	public static void main(String[] args) {
		// index tiap kota
	    // 0 = Brebes           15 = Semarang
	    // 1 = Tegal            16 = Salatiga
	    // 2 = Slawi            17 = Boyolali
	    // 3 = Purwokerto       18 = Klaten
	    // 4 = Cilacap          19 = Demak
	    // 5 = Kroya            20 = Kudus
	    // 6 = Pemalang         21 = Purwodadi
	    // 7 = Purbalingga      22 = Solo
	    // 8 = Kebumen          23 = Sragen
	    // 9 = Banjarnegara     24 = Sukoharjo
	    // 10 = Pekalongan      25 = Wonogiri
	    // 11 = Purworejo       26 = Blora
	    // 12 = Wonosobo        27 = Rembang
	    // 13 = Temanggung      28 = Magelang
	    // 14 = Kendal
		
		double map[][] = new double[][] {
	          { 0, 41, 29, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	          { 41, 0, 12, 0, 0, 0, 33, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	          { 29, 12, 0, 91, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	          { 0, 0, 91, 0, 48, 30, 0, 35, 74, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	          { 0, 0, 0, 48, 0, 28, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	          { 0, 0, 0, 30, 28, 0, 0, 0, 52, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	          { 0, 33, 0, 0, 0, 0, 0, 66, 0, 0, 48, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	          { 0, 0, 0, 35, 0, 0, 66, 0, 0, 31, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	          { 0, 0, 0, 74, 0, 52, 0, 0, 0, 0, 0, 42, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	          { 0, 0, 0, 0, 0, 0, 0, 31, 0, 0, 0, 0, 47, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	          { 0, 0, 0, 0, 0, 0, 48, 0, 0, 0, 0, 0, 0, 0, 75, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	          { 0, 0, 0, 0, 0, 0, 0, 0, 42, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 43 },
	          { 0, 0, 0, 0, 0, 0, 0, 0, 0, 47, 0, 0, 0, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 60 },
	          { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 40, 0, 70, 0, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21 },
	          { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 75, 0, 0, 70, 0, 36, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	          { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 36, 0, 50, 0, 0, 34, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	          { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 55, 0, 50, 0, 36, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	          { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 36, 0, 34, 0, 0, 0, 29, 0, 0, 0, 0, 0, 65 },
	          { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 34, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	          { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 34, 0, 0, 0, 0, 38, 34, 0, 0, 0, 0, 0, 0, 0 },
	          { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 38, 0, 55, 0, 0, 0, 0, 0, 78, 0 },
	          { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 34, 55, 0, 65, 0, 0, 0, 67, 0, 0 },
	          { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 29, 0, 0, 0, 65, 0, 35, 15, 0, 0, 0, 0 },
	          { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 35, 0, 0, 0, 82, 0, 0 },
	          { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15, 0, 0, 44, 0, 0, 0 },
	          { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 44, 0, 0, 0, 0 },
	          { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 67, 0, 82, 0, 0, 0, 38, 0 },
	          { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 78, 0, 0, 0, 0, 0, 38, 0, 0 },
	          { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 43, 60, 21, 0, 0, 0, 65, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
		};
		
	}
}
