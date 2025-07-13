import static org.junit.jupiter.api.Assertions.*;

class GraphAdjacencyMatrixTest {

    private void populateGraph(GraphAdjacencyMatrix graph) {
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
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
    }

    @org.junit.jupiter.api.Test
    void testisConnectedV1() {
        GraphAdjacencyMatrix graph = new GraphAdjacencyMatrix(10, true);
        populateGraph(graph);
        assertTrue(graph.isConnected(0, 1));
        assertTrue(graph.isConnected(0, 2));
    }

    @org.junit.jupiter.api.Test
    void testIsConnectedV2() {
        GraphAdjacencyMatrix graph = new GraphAdjacencyMatrix(10, true);
        populateGraph(graph);
        assertFalse(graph.isConnected(6, 3));
    }

    @org.junit.jupiter.api.Test
    void testIsConnectedV3() {
        GraphAdjacencyMatrix graph = new GraphAdjacencyMatrix(10, true);
        populateGraph(graph);

        assertTrue(graph.isConnected(2, 9));
        assertTrue(graph.isConnected(6, 9));
        assertTrue(graph.isConnected(0, 8));

        assertFalse(graph.isConnected(0, 6));
        assertFalse(graph.isConnected(2, 3));
    }
}