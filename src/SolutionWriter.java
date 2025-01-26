package src;

// SolutionWriter.java
import java.io.FileWriter;
import java.io.IOException;

public class SolutionWriter {
    /**
     * Scrive la migliore soluzione trovata in un file di testo.
     *
     * @param filename Nome del file di output.
     * @param solution La soluzione da scrivere.
     * @param graph    Il grafo associato.
     */
    public static void writeSolutionToFile(String filename, Solution solution, Graph graph) {
        try (FileWriter fw = new FileWriter(filename)) {
            fw.write("Miglior Vertex Cover Trovato:\n");
            fw.write(solution.toString() + "\n\n");
            fw.write("Dettagli dei Nodi nel Vertex Cover:\n");
            for (Integer nodeIndex : solution.getVertexCover()) {
                fw.write("Nodo " + (nodeIndex + 1) + ": Peso = " + graph.getWeights()[nodeIndex] + "\n");
            }
            fw.write("\nCosto Totale del Vertex Cover: " + solution.getCost() + "\n");
            System.out.println("La migliore soluzione è stata salvata in: " + filename);
        } catch (IOException e) {
            System.err.println("Errore nella scrittura del file: " + e.getMessage());
        }
    }
}
