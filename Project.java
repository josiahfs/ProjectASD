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

    public static String getLabel(int idx){
        String label = "";
        if (idx == 0) {
            label = "Brebes";
        } else if (idx == 1) {
            label = "Tegal";
        } else if (idx == 2) {
            label = "Slawi";
        } else if (idx == 3) {
            label = "Purwokerto";
        } else if (idx == 4) {
            label = "Cilacap";
        } else if (idx == 5) {
            label = "Kroya";
        } else if (idx == 6) {
            label = "Pemalang";
        } else if (idx == 7) {
            label = "Purbalingga";
        } else if (idx == 8) {
            label = "Kebumen";
        } else if (idx == 9) {
            label = "Banjarnegara";
        } else if (idx == 10) {
            label = "Pekalongan";
        } else if (idx == 11) {
            label = "Purworejo";
        } else if (idx == 12) {
            label = "Wonosobo";
        } else if (idx == 13) {
            label = "Temanggung";
        } else if (idx == 14) {
            label = "Kendal";
        } else if (idx == 15) {
            label = "Semarang";
        } else if (idx == 16) {
            label = "Salatiga";
        } else if (idx == 17) {
            label = "Boyolali";
        } else if (idx == 18) {
            label = "Klaten";
        } else if (idx == 19) {
            label = "Demak";
        } else if (idx == 20) {
            label = "Kudus";
        } else if (idx == 21) {
            label = "Purwodadi";
        } else if (idx == 22) {
            label = "Solo";
        } else if (idx == 23) {
            label = "Sragen";
        } else if (idx == 24) {
            label = "Sukoharjo";
        } else if (idx == 25) {
            label = "Wonogiri";
        } else if (idx == 26) {
            label = "Blora";
        } else if (idx == 27) {
            label = "Rembang";
        } else if (idx == 28) {
            label = "Magelang";
        }
        return label;
    }

    public Dijkstra(int n){
        nTown = n;
        map = new double[nTown][nTown];
    }
    public void addEdge(int from, int to, double len){
        map[from][to] = len;
        map[to][from] = len;
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
            if (distance[marked] == Double.MAX_VALUE) {
                return;
            }
            selected[marked] = true;
            //if (marked == dst) {
            //    return;
          //  }

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
            System.out.println("Tempat ke-"+(i+1)+ " : "+ getLabel(min.dst) +"\tJarak : "+min.distance+" km");
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
        System.out.print("Rute Terdekat: ");
        Iterator<Integer> iter = SPVArray.listIterator();
        while(iter.hasNext()){
            System.out.print(getLabel(iter.next()) + " ");
        }
        System.out.print("\nJarak Total: " + totalCost + " km");

        System.out.print("\n\nKembali ke titik awal {" +getLabel(start)+ "} (Ya (1) / Tidak (0)) ? ");
        Scanner in = new Scanner(System.in);
        int k = in.nextInt();
        if (k == 1)
        {
            back(temp, start);
        }
        else if (k == 0){
            System.out.println("Terima Kasih");
        }
    }

    public void back(int start, int vToVisit){
        int temp = start;
        boolean[] visited = new boolean[nTown];
        for (int i = 0; i < nTown; i++) {
            visited[i] = false;
        }
        ArrayList<Integer> SPVArray = new ArrayList<Integer>();
        SPVArray.add(temp);
        for(int i = 0; i<1;i++){
            solve(temp,vToVisit);
            
            PriorityQueue<SPVNode> pq = new 
             PriorityQueue<SPVNode>(1-i, new SPVNodeComparator());
            
            for(int j = 0; j<1;j++){
                if(!visited[vToVisit]){
                    SPVNode tempNode = makeIntoSPVNode(temp, vToVisit);
                    pq.add(tempNode);
                }
            }
            
            SPVNode min = pq.peek();
            System.out.println("Kembali ke"+ " : "+ getLabel(min.dst)+"\tJarak : "+min.distance+" km");
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
        System.out.print("Rute Terdekat: ");
        Iterator<Integer> iter = SPVArray.listIterator();
        while(iter.hasNext()){
            System.out.print(getLabel(iter.next()) + " ");
        }
        System.out.println("\nTerima Kasih");
    }
}

public class Project {
	public static void main(String[] args) {
		
		Dijkstra map1 = new Dijkstra(29);
        map1.addEdge(0, 1, 41);
        map1.addEdge(0, 2, 29);
        map1.addEdge(2, 3, 91);
        map1.addEdge(3, 4, 48);
        map1.addEdge(4, 5, 28);
        map1.addEdge(1, 6, 33);
        map1.addEdge(1, 2, 12);
        map1.addEdge(6, 10, 48);
        map1.addEdge(6, 7, 66);
        map1.addEdge(10,14,75);
        map1.addEdge(14, 13, 70);
        map1.addEdge(14, 15, 36);
        map1.addEdge(15, 19, 34);
        map1.addEdge(15, 16, 50);
        map1.addEdge(19, 20, 38);
        map1.addEdge(19, 21, 34);
        map1.addEdge(20, 27, 78);
        map1.addEdge(20, 21, 55);
        map1.addEdge(27, 26, 38);
        map1.addEdge(26, 21, 67);
        map1.addEdge(26, 23, 82);
        map1.addEdge(21, 22, 65);
        map1.addEdge(16, 13, 55);
        map1.addEdge(16, 17, 36);
        map1.addEdge(13, 28, 21);
        map1.addEdge(13, 12, 40);
        map1.addEdge(12, 28, 60);
        map1.addEdge(12, 9, 47);
        map1.addEdge(9, 7, 31);
        map1.addEdge(7, 3, 35);
        map1.addEdge(3, 5, 30);
        map1.addEdge(3, 8, 74);
        map1.addEdge(5, 8, 52);
        map1.addEdge(8, 11, 42);
        map1.addEdge(11, 28, 43);
        map1.addEdge(28, 17, 65);
        map1.addEdge(17, 18, 34);
        map1.addEdge(17, 22, 29);
        map1.addEdge(22, 23, 35);
        map1.addEdge(22, 24, 15);
        map1.addEdge(24, 25, 44);

        Scanner in = new Scanner(System.in);
         System.out.println("Program untuk menentukan rute perjalan antar kota di Jawa Tengah");
        System.out.println("Berikut ini adalah daftar kota yang tersedia :");
        System.out.println("0 = Brebes\t\t10 = Pekalongan\t20 = Kudus");
        System.out.println("1 = Tegal\t\t11 = Purworejo\t\t21 = Purwodadi");
        System.out.println("2 = Slawi\t\t12 = Wonosobo\t\t22 = Solo");
        System.out.println("3 = Purwokerto\t\t13 = Temanggung\t23 = Sragen");
        System.out.println("4 = Cilacap\t\t14 = Kendal\t\t24 = Sukoharjo");
        System.out.println("5 = Kroya\t\t15 = Semarang\t\t25 = Wonogiri");
        System.out.println("6 = Pemalang\t\t16 = Salatiga\t\t26 = Blora");
        System.out.println("7 = Purbalingga\t17 = Boyolali\t\t27 = Rembang");
        System.out.println("8 = Kebumen\t\t18 = Klaten\t\t28 = Magelang");
        System.out.println("9 = Banjarnegara\t19 = Demak");

        System.out.print("Posisi Awal : ");
        int n = in.nextInt();
        
        System.out.print("Jumlah Destinasi : ");
        int N = in.nextInt();
        int[] toVisit = new int[N];

        

        for (int i = 0; i < N; i++) {
            System.out.print("Destinasi ke-" + (i + 1) + " : ");
            toVisit[i] = in.nextInt();
        }
		
		map1.computePathForward(n, toVisit);  
        in.close();
 	}
}
