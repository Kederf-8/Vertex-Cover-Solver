package src;

// GraphReader.java
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GraphReader {
    /**
     * Costruisce il grafo a partire da un file con il seguente formato:
     * - Prima riga: numero di nodi.
     * - Seconda riga: pesi dei nodi (separati da spazi).
     * - Righe successive: matrice di adiacenza (una riga per nodo, con 0 o 1
     * separati da spazi).
     *
     * @param filePath Percorso del file di input.
     * @return Oggetto Graph.
     */
    public static Graph buildGraphFromWeightedAdjMatrixFile(String filePath) {
        List<Edge> edges = new ArrayList<>();
        int numNodes;
        double[] weights;
        int[][] matrix;

        try (Scanner sc = new Scanner(new File(filePath))) {
            // Legge il numero di nodi
            if (!sc.hasNextLine()) {
                throw new IllegalArgumentException("Il file è vuoto o mal formattato.");
            }
            numNodes = Integer.parseInt(sc.nextLine().trim());

            // Legge i pesi dei nodi
            if (!sc.hasNextLine()) {
                throw new IllegalArgumentException("Mancano i pesi dei nodi.");
            }
            String weightsLine = sc.nextLine().trim();
            String[] weightTokens = weightsLine.split("\\s+");
            if (weightTokens.length != numNodes) {
                throw new IllegalArgumentException("Numero di pesi (" + weightTokens.length
                        + ") non corrisponde al numero di nodi (" + numNodes + ").");
            }
            weights = new double[numNodes];
            for (int i = 0; i < numNodes; i++) {
                weights[i] = Double.parseDouble(weightTokens[i]);
            }

            // Legge la matrice di adiacenza
            matrix = new int[numNodes][numNodes];
            for (int i = 0; i < numNodes; i++) {
                if (!sc.hasNextLine()) {
                    throw new IllegalArgumentException("Matrice incompleta: manca la riga " + (i + 1) + ".");
                }
                String line = sc.nextLine().trim();
                String[] tokens = line.split("\\s+");
                if (tokens.length != numNodes) {
                    throw new IllegalArgumentException(
                            "La riga " + (i + 1) + " della matrice non contiene " + numNodes + " elementi.");
                }
                for (int j = 0; j < numNodes; j++) {
                    matrix[i][j] = Integer.parseInt(tokens[j]);
                }
            }

            // Estrae gli archi dalla matrice (solo i < j per evitare duplicati)
            for (int i = 0; i < numNodes; i++) {
                for (int j = i + 1; j < numNodes; j++) {
                    if (matrix[i][j] == 1) {
                        edges.add(new Edge(i, j));
                    }
                }
            }

            System.out.println("Grafo letto dal file: Nodi = " + numNodes + ", Archi = " + edges.size());

        } catch (FileNotFoundException e) {
            System.err.println("File non trovato: " + filePath);
            System.exit(1);
            return null; // Non raggiunto
        } catch (NumberFormatException e) {
            System.err.println("Errore di formattazione numerica: " + e.getMessage());
            System.exit(1);
            return null; // Non raggiunto
        } catch (IllegalArgumentException e) {
            System.err.println("Errore nel file di input: " + e.getMessage());
            System.exit(1);
            return null; // Non raggiunto
        }

        return new Graph(numNodes, weights, edges);
    }
}
