

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
        String solutionOutputPath = "best_solution.txt"; // Puoi modificare il nome se necessario

        // Costruisce il grafo dal file di input
        Graph graph = GraphReader.buildGraphFromWeightedAdjMatrixFile(inputFilePath);

        // Imposta i parametri per la Tabu Search
        int maxIterations = 1000; // Numero massimo di iterazioni
        int tabuTenure = 7; // Durata in iterazioni per cui una mossa resta tabu

        // Crea un'istanza di TabuSearch
        TabuSearch tabuSearch = new TabuSearch(graph, maxIterations, tabuTenure);

        // Esegue la Tabu Search
        Solution bestSolution = tabuSearch.search();

        // Stampa la migliore soluzione trovata
        System.out.println("\nMiglior soluzione trovata: " + bestSolution.toString());

        // Scrive la migliore soluzione in un file di testo
        SolutionWriter.writeSolutionToFile(solutionOutputPath, bestSolution, graph);
    }
}
