import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
 
public class Main {
 
    // Driver 
    public static void main(String args[])
    {
        Scanner snake = new Scanner(System.in);
		int n = snake.nextInt();
		if (n<2){
		    n = 2;
		}else if(n>1000){
		    n = 1000;
		}
 
        // Adjacency list
        ArrayList<ArrayList<Integer>> adj =
                            new ArrayList<ArrayList<Integer>>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Integer>());
        }
 int x,y;
        for (int i = 1; i<n; i++){
		    x = snake.nextInt();
		    y = snake.nextInt();
		    if(x<1) {
		        x = 1;
		    }
		    if(y>n) {
		        y = n;
		    }
		    if (x>y){
		        int tmp = x;
		        x = y;
		        y = tmp;
		    }else if(x == y){
		        y++;
		    }
		    addEdge(adj,x-1,y-1);
 }
        int source = 0, dest = n-1;
        printShortestDistance(adj, source, dest, n);
    }
 
    private static void addEdge(ArrayList<ArrayList<Integer>> adj, int i, int j)
    {
        adj.get(i).add(j);
        adj.get(j).add(i);
    }
 
    private static void printShortestDistance(
                     ArrayList<ArrayList<Integer>> adj,
                             int s, int dest, int v)
    {
        int pred[] = new int[v];
        int dist[] = new int[v];
 
        if (BFS(adj, s, dest, v, pred, dist) == false) {
            System.out.println("Given source and destination" +
                                         "are not connected");
            return;
        }
 
        // LinkedList to store path
        LinkedList<Integer> path = new LinkedList<Integer>();
        int crawl = dest;
        path.add(crawl);
        while (pred[crawl] != -1) {
            path.add(pred[crawl]);
            crawl = pred[crawl];
        }
 
        // Print distance, shortest
        System.out.println(dist[dest]);
 
        // Print path
        /*System.out.println("Path is ::");
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }*/
    }

    private static boolean BFS(ArrayList<ArrayList<Integer>> adj, int src,
                                  int dest, int v, int pred[], int dist[])
    {

        LinkedList<Integer> queue = new LinkedList<Integer>();
 
        boolean visited[] = new boolean[v];
 
        for (int i = 0; i < v; i++) {
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }
 
        visited[src] = true;
        dist[src] = 0;
        queue.add(src);

        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int i = 0; i < adj.get(u).size(); i++) {
                if (visited[adj.get(u).get(i)] == false) {
                    visited[adj.get(u).get(i)] = true;
                    dist[adj.get(u).get(i)] = dist[u] + 1;
                    pred[adj.get(u).get(i)] = u;
                    queue.add(adj.get(u).get(i));
 
                    if (adj.get(u).get(i) == dest)
                        return true;
                }
            }
        }
        return false;
    }
}
