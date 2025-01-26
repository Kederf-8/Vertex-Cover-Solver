package src;

// Graph.java
import java.util.List;

public class Graph {
    private final int numNodes;
    private final double[] weights; // weights[i] è il peso del nodo con indice i
    private final List<Edge> edges;

    public Graph(int numNodes, double[] weights, List<Edge> edges) {
        this.numNodes = numNodes;
        this.weights = weights;
        this.edges = edges;
    }

    public int getNumNodes() {
        return numNodes;
    }

    public double[] getWeights() {
        return weights;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
