package spiderman;
import java.util.*;
public class Graph{
    int Vertices; 
   private LinkedList<Integer> adj[];

    @SuppressWarnings("unchecked") Graph(int vertices){
        this.Vertices = vertices;
        adj = new LinkedList[vertices];
        for(int i = 0; i<vertices; ++i){
         adj[i] = new LinkedList<>();
        }

    }
  public void addEdge(int index, int value){
        adj[index].add(value);

    }
    public void DFSUtil(int vertices, boolean visited[]){
        //mark current node and print
        visited[vertices] = true;
        //recur for all vertice adjacent to this vertex
        Iterator<Integer> i = adj[vertices].listIterator();
        while(i.hasNext()){
            int n = i.next();
            if(!visited[n])
            DFSUtil(n, visited);
        }      
    }
    public void DFS(int v){
        boolean visited[] = new boolean [Vertices];
        DFSUtil(v, visited);
    }
}
