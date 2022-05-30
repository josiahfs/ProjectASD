import java.util.*;
public class SPVNode {
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
