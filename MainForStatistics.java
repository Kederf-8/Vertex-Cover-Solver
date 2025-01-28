

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import src.Graph;
import src.GraphReader;
import src.Solution;
import src.TabuSearch;

public class MainForStatistics {

    public static void main(String[] args) {
        if (args.length < 4) {
            System.err.println(
                    "Uso: java VertexCoverMain <file_grafo.txt> <maxIterations> <tabuTenure> <maxNoImprovement> [nomeCsv]");
            System.exit(1);
        }

        String inputFilePath = args[0];
        int maxIterations = Integer.parseInt(args[1]);
        int tabuTenure = Integer.parseInt(args[2]);
        int maxNoImprovement = Integer.parseInt(args[3]);

        // CSV di default, se non specificato come quinto argomento
        String nomeCsv = "statistics/scalability_results.csv";
        if (args.length >= 5) {
            nomeCsv = args[4];
        }

        // Caricamento del grafo (metodo definito in GraphReader)
        Graph graph = GraphReader.buildGraphFromWeightedAdjMatrixFile(inputFilePath);
        int nNodes = graph.getNumNodes();
        int nArcs = graph.getEdges().size();

        // Creazione della TabuSearch con i parametri
        TabuSearch tabuSearch = new TabuSearch(graph, maxIterations, tabuTenure, maxNoImprovement);

        // Misura del tempo
        long start = System.currentTimeMillis();
        Solution bestSolution = tabuSearch.search();
        long end = System.currentTimeMillis();
        double elapsedTimeSec = (end - start) / 1000.0;

        // Calcolo del costo (può essere somma pesi se Weighted, o conteggio nodi)
        double bestCost = bestSolution.getCost();

        // Stampa a video
        System.out.println("Eseguito su " + inputFilePath);
        System.out.println("Parametri: maxIterations=" + maxIterations + ", tabuTenure=" + tabuTenure
                + ", maxNoImprovement=" + maxNoImprovement);
        System.out.println("Nodi=" + nNodes + ", Archi=" + nArcs);
        System.out.println("Miglior costo: " + bestCost);
        System.out.println("Tempo (s): " + elapsedTimeSec);

        // Scrittura su CSV
        // (se il file non esiste o è vuoto, aggiungiamo l'header)
        File outFile = new File(nomeCsv);
        boolean fileVuoto = (!outFile.exists()) || (outFile.length() == 0);

        try (FileWriter fw = new FileWriter(outFile, true)) {
            // Se è la prima volta, scrivi l'header
            if (fileVuoto) {
                fw.write("nNodes,nArcs,maxIterations,tabuTenure,maxNoImprovement,bestCost,timeSec\n");
            }

            fw.write(nNodes + "," + nArcs + ","
                    + maxIterations + "," + tabuTenure + ","
                    + maxNoImprovement + "," + bestCost + ","
                    + elapsedTimeSec + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
