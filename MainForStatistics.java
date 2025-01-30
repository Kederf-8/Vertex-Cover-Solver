import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import src.Graph;
import src.GraphReader;
import src.TabuSearch;

public class MainForStatistics {

    public static void main(String[] args) {
        if (args.length < 4) {
            System.err.println(
                    "Usage: java MainForStatistics <graph_file.txt> <maxIterations> <tabuTenure> <maxNoImprovement> [csvFile]");
            System.exit(1);
        }

        String inputFilePath = args[0];
        int maxIterations = Integer.parseInt(args[1]);
        int tabuTenure = Integer.parseInt(args[2]);
        int maxNoImprovement = Integer.parseInt(args[3]);

        // Default CSV file, if not specified as the fifth argument
        String nomeCsv = "statistics/scalability_results.csv";
        if (args.length >= 5) {
            nomeCsv = args[4];
        }

        // Load the graph
        Graph graph = GraphReader.buildGraphFromWeightedAdjMatrixFile(inputFilePath);
        int nNodes = graph.getNumNodes();
        int nArcs = graph.getEdges().size();

        // Number of runs
        final int RUNS = 5;
        double totalTime = 0.0;

        for (int i = 0; i < RUNS; i++) {
            // Create a new TabuSearch for each run
            TabuSearch tabuSearch = new TabuSearch(graph, maxIterations, tabuTenure, maxNoImprovement);

            long start = System.currentTimeMillis();
            tabuSearch.search();
            long end = System.currentTimeMillis();

            double elapsedTimeSec = (end - start) / 1000.0;
            totalTime += elapsedTimeSec;
        }

        // Average time
        double avgTime = totalTime / RUNS;

        // Round to 4 decimal places
        avgTime = Math.round(avgTime * 10000.0) / 10000.0;

        // Print results
        System.out.println("File: " + inputFilePath);
        System.out.println("maxIterations=" + maxIterations + ", tabuTenure=" + tabuTenure
                + ", maxNoImprovement=" + maxNoImprovement);
        System.out.println("Nodes=" + nNodes + ", Arcs=" + nArcs);
        System.out.println("Avg Time (sec, " + RUNS + " runs): " + avgTime);

        // Write to CSV
        File outFile = new File(nomeCsv);
        boolean fileVuoto = (!outFile.exists()) || (outFile.length() == 0);

        try (FileWriter fw = new FileWriter(outFile, true)) {
            if (fileVuoto) {
                // No cost column, just time
                fw.write("nNodes,nArcs,maxIterations,tabuTenure,maxNoImprovement,avgTime\n");
            }
            fw.write(nNodes + "," + nArcs + ","
                    + maxIterations + "," + tabuTenure + ","
                    + maxNoImprovement + ","
                    + avgTime + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}