import java.util.*;
public class GraphAdjacencyList<T> {
    private List<Integer>[] adjacencyList;
    private boolean isDirected;

    public GraphAdjacencyList(int numNodes, boolean isDirected) {
        adjacencyList = new ArrayList[numNodes];
        for(int n = 0; n < numNodes; n ++) {
            adjacencyList[n] = new ArrayList<>();
        }
        this.isDirected = isDirected;
    }
    public void addEdge(int sourceId, int targetId) {
        adjacencyList[sourceId].add(targetId);
        if(!isDirected) {
            adjacencyList[targetId].add(sourceId);
        }
    }
}
