import java.util.*;
public class GraphAdjacencyMatrix<T> {
    /*
        Optional attribute that can be included to map node label to a node id
        if node label has meaning. For example if the node labels are city names,
        we would use this use map to map each city name to an internal id that would be used
        throughout the Graph implementation.
     */
    //private Map<T, Integer> nodeMapping;

    // Adjacency matrix used to represent graph
    private int[][] adjNeighbors;
    private boolean isDirected;

    private int numVertices;

    public GraphAdjacencyMatrix(int numNodes, boolean isDirected) {
        adjNeighbors = new int[numNodes][numNodes];
        this.isDirected = isDirected;
        this.numVertices = numNodes;
    }

    /*
        Add an edge between the source and target node. The isDirected parameter
     */
    public void addEdge(int sourceId, int targetId) {
        adjNeighbors[sourceId][targetId] = 1;
        if(!isDirected) {
            adjNeighbors[targetId][sourceId] = 1;
        }
    }

    public boolean isConnected(int source, int target) {
        boolean[] visitedNodes = new boolean[getNumVertices()];
        return isConnected(source, target, visitedNodes);
    }

    private boolean isConnected(int source, int target, boolean[] visitedNodes) {
        if(source == target) {
            return true;
        }
        visitedNodes[source] = true;
        for(int neighborIdx = 0; neighborIdx < getNumVertices(); neighborIdx ++) {
            if(adjNeighbors[source][neighborIdx] == 1 && !visitedNodes[neighborIdx]) {
                if(isConnected(neighborIdx, target, visitedNodes)) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
        Get all paths from source node to every target node that is reachable such
        that each path contains a node at most 1 time.
     */
    public List<String> getAllPaths(int startingNode) {
        boolean[] visited = new boolean[getNumVertices()];
        List<Integer> currentPath = new ArrayList<>();
        List<String> allPaths = new ArrayList<>();
        allPathsHelper(startingNode, visited, currentPath, allPaths);
        return allPaths;
    }

    private void allPathsHelper(int node, boolean[] visited, List<Integer> curPath, List<String> allPaths) {
        visited[node] = true;
        curPath.add(node);
        allPaths.add(curPath.toString());
        for(int nodeIdx = 0; nodeIdx < getNumVertices(); nodeIdx ++) {
            if(!visited[nodeIdx] && adjNeighbors[node][nodeIdx] == 1) {
                allPathsHelper(nodeIdx, visited, curPath, allPaths);
            }
        }
        curPath.remove(curPath.size() - 1);
        visited[node] = false;
    }
    private int getNumVertices() {
        return numVertices;
    }

    public static void main(String[] args) {
        GraphAdjacencyMatrix graph = new GraphAdjacencyMatrix(11, true);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 10);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 2);
        graph.addEdge(3, 9);
        graph.addEdge(4, 2);
        graph.addEdge(4, 5);
        graph.addEdge(5, 8);
        graph.addEdge(6, 1);
        graph.addEdge(6, 7);
        graph.addEdge(7, 8);
        graph.addEdge(8, 9);
        graph.addEdge(10, 3);
        System.out.println(graph.getAllPaths(0));
    }
}
