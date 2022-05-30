import java.util.*;

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
