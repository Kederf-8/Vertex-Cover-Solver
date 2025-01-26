
// VertexCoverMain.java
import java.io.File;

import src.Graph;
import src.GraphReader;
import src.Solution;
import src.SolutionWriter;
import src.TabuSearch;

// VertexCoverMain.java
public class VertexCoverMain {
    public static void main(String[] args) {
        // Controlla se è stato fornito il percorso del file di input come argomento
        if (args.length < 1) {
            System.err.println("Uso: java VertexCoverMain <file_grafo.txt>");
            System.exit(1);
        }

        String inputFilePath = args[0];
        String solutionOutputPath;
        String dotOutputPath = "grafo.dot"; // Nome del file DOT per la visualizzazione

        // Costruisce il grafo dal file di input
        Graph graph = GraphReader.buildGraphFromWeightedAdjMatrixFile(inputFilePath);

        // Crea la cartella 'solutions' se non esiste
        File solutionsDir = new File("solutions");
        if (!solutionsDir.exists()) {
            boolean dirCreated = solutionsDir.mkdirs();
            if (!dirCreated) {
                System.err.println("Errore nella creazione della cartella 'solutions'.");
                System.exit(1);
            }
        }

        // Imposta il percorso del file di output con numero di nodi e archi
        solutionOutputPath = "solutions/solution_"
                + graph.getNumNodes() + "_" + graph.getEdges().size() + ".txt";

        // Imposta i parametri per la Tabu Search
        int maxIterations = 1000; // Numero massimo di iterazioni
        int tabuTenure = 7; // Durata in iterazioni per cui una mossa resta tabu

        // Crea un'istanza di TabuSearch
        TabuSearch tabuSearch = new TabuSearch(graph, maxIterations, tabuTenure);

        // Esegue la Tabu Search
        Solution bestSolution = tabuSearch.search();

        // Scrive la migliore soluzione in un file di testo
        SolutionWriter.writeSolutionToFile(solutionOutputPath, bestSolution, graph);

        // Esporta il grafo in formato DOT in maniera veloce (ottimizzata)
        tabuSearch.exportGraphToDOTFast(dotOutputPath);
    }
}
