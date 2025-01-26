package src;

// Graph.java
import java.io.FileWriter;
import java.io.IOException;
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

    /**
     * Esporta il grafo in formato DOT in maniera efficiente utilizzando
     * StringBuilder.
     *
     * @param filename Nome del file di output.
     */
    public void exportGraphToDOTFast(String filename) {
        StringBuilder sb = new StringBuilder();
        sb.append("graph G {\n");

        // Aggiunge tutti i nodi con un'etichetta che include anche il peso (per
        // chiarezza)
        for (int i = 0; i < numNodes; i++) {
            sb.append("  ")
                    .append(i) // 0-based oppure (i+1) per 1-based
                    .append(" [label=\"")
                    .append(i + 1)
                    .append(" (")
                    .append(weights[i])
                    .append(")\"];\n");
        }

        // Aggiunge tutti gli archi
        for (Edge e : edges) {
            sb.append("  ")
                    .append(e.getU())
                    .append(" -- ")
                    .append(e.getV())
                    .append(";\n");
        }
        sb.append("}\n");

        try (FileWriter fw = new FileWriter(filename)) {
            fw.write(sb.toString());
        } catch (IOException ex) {
            System.err.println("Errore nell'esportazione del file DOT: " + ex.getMessage());
            System.exit(1);
        }
        System.out.println("Grafo esportato in formato DOT in maniera veloce sul file: " + filename);
    }
}
