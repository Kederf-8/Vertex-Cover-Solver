package src;

// TabuSearch.java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class TabuSearch {
    private final Graph graph;
    private final int maxIterations;
    private final int tabuTenure;
    private final int maxNoImprovement;
    private final Map<String, Integer> tabuList;

    public TabuSearch(Graph graph, int maxIterations, int tabuTenure, int maxNoImprovement) {
        this.graph = graph;
        this.maxIterations = maxIterations;
        this.tabuTenure = tabuTenure;
        this.maxNoImprovement = maxNoImprovement;
        this.tabuList = new HashMap<>();
    }

    /**
     * Esegue l'algoritmo Tabu Search per trovare un Vertex Cover pesato con costo
     * minimo.
     *
     * @return Migliore soluzione trovata.
     */
    public Solution search() {
        Solution bestSolution = initialSolution();
        Solution currentSolution = bestSolution.clone();

        int iterations = 0;
        int iterationsSinceLastImprovement = 0;
        Random rand = new Random();

        System.out.println("Inizio Tabu Search per il Weighted Vertex Cover...");

        while (iterations < maxIterations && iterationsSinceLastImprovement < maxNoImprovement) {
            List<Solution> neighbors = generateNeighbors(currentSolution);
            if (neighbors.isEmpty()) {
                System.out.println("Iterazione " + iterations + ": nessun vicino ammissibile trovato.");
                break;
            }

            // Seleziona il migliore vicino non tabu o che soddisfa il criterio di
            // aspirazione
            Solution bestNeighbor = null;
            for (Solution candidate : neighbors) {
                // Determina la mossa effettuata rispetto a currentSolution
                Set<Integer> diff = new HashSet<>(currentSolution.getVertexCover());
                diff.removeAll(candidate.getVertexCover());

                String move = "";
                if (!diff.isEmpty()) {
                    int removed = diff.iterator().next();
                    move = getMoveKey("REMOVE", removed);
                } else {
                    diff = new HashSet<>(candidate.getVertexCover());
                    diff.removeAll(currentSolution.getVertexCover());
                    if (!diff.isEmpty()) {
                        int added = diff.iterator().next();
                        move = getMoveKey("ADD", added);
                    }
                }

                // Criterio di aspirazione: se migliora la migliore soluzione globale
                boolean aspiration = false;
                if (tabuList.containsKey(move) && candidate.getCost() < bestSolution.getCost()) {
                    aspiration = true;
                }

                if (!tabuList.containsKey(move) || aspiration) {
                    if (bestNeighbor == null || candidate.getCost() < bestNeighbor.getCost()) {
                        bestNeighbor = candidate;
                    }
                }
            }

            // Se nessun candidato valido è stato trovato, seleziona un vicino a caso
            if (bestNeighbor == null) {
                bestNeighbor = neighbors.get(rand.nextInt(neighbors.size()));
            }

            // Determina la mossa effettuata da currentSolution a bestNeighbor
            Set<Integer> diff = new HashSet<>(currentSolution.getVertexCover());
            diff.removeAll(bestNeighbor.getVertexCover());
            String move = "";
            if (!diff.isEmpty()) {
                int removed = diff.iterator().next();
                move = getMoveKey("REMOVE", removed);
            } else {
                diff = new HashSet<>(bestNeighbor.getVertexCover());
                diff.removeAll(currentSolution.getVertexCover());
                if (!diff.isEmpty()) {
                    int added = diff.iterator().next();
                    move = getMoveKey("ADD", added);
                }
            }

            // Aggiorna la lista tabu
            if (!move.isEmpty()) {
                tabuList.put(move, tabuTenure);
            }

            // Aggiorna la soluzione corrente
            currentSolution = bestNeighbor.clone();

            // Aggiorna la migliore soluzione globale se necessario
            if (currentSolution.getCost() < bestSolution.getCost()) {
                bestSolution = currentSolution.clone();
                iterationsSinceLastImprovement = 0; // Resetta il contatore
            } else {
                iterationsSinceLastImprovement++;
            }

            // Aggiorna la lista tabu decrementando il tenure
            updateTabuList();

            // Incrementa il contatore delle iterazioni
            iterations++;

            // Stampa il progresso ogni 10 iterazioni o quando si raggiunge maxIterations o
            // non migliora per maxNoImprovement iterazioni
            if (iterations % 10 == 0 || iterations == maxIterations
                    || iterationsSinceLastImprovement == maxNoImprovement) {
                System.out.println("Iterazione " + iterations + " - Migliore Costo: " + bestSolution.getCost());
            }
        }

        return bestSolution;
    }

    /**
     * Crea una soluzione iniziale ammissibile (tutti i nodi inclusi).
     *
     * @return Soluzione iniziale.
     */
    private Solution initialSolution() {
        Set<Integer> cover = new HashSet<>();
        for (int i = 0; i < graph.getNumNodes(); i++) {
            cover.add(i);
        }
        double cost = calculateCost(cover);
        return new Solution(cover, cost);
    }

    /**
     * Calcola il costo totale di un vertex cover.
     *
     * @param cover Set di indici dei nodi nel cover.
     * @return Costo totale.
     */
    private double calculateCost(Set<Integer> cover) {
        double cost = 0.0;
        for (Integer i : cover) {
            cost += graph.getWeights()[i];
        }
        return cost;
    }

    /**
     * Genera soluzioni vicine alla soluzione corrente.
     *
     * @param current Soluzione corrente.
     * @return Lista di soluzioni vicine ammissibili.
     */
    private List<Solution> generateNeighbors(Solution current) {
        List<Solution> neighbors = new ArrayList<>();

        // Prova a rimuovere ogni nodo presente nel cover
        for (Integer node : current.getVertexCover()) {
            Set<Integer> newCover = new HashSet<>(current.getVertexCover());
            newCover.remove(node);
            if (isFeasible(newCover)) {
                double newCost = calculateCost(newCover);
                neighbors.add(new Solution(newCover, newCost));
            }
        }

        // Prova ad aggiungere ogni nodo non presente nel cover
        for (int i = 0; i < graph.getNumNodes(); i++) {
            if (!current.getVertexCover().contains(i)) {
                Set<Integer> newCover = new HashSet<>(current.getVertexCover());
                newCover.add(i);
                if (isFeasible(newCover)) {
                    double newCost = calculateCost(newCover);
                    neighbors.add(new Solution(newCover, newCost));
                }
            }
        }

        return neighbors;
    }

    /**
     * Verifica se un determinato set di nodi copre tutti gli archi.
     *
     * @param cover Set di indici dei nodi.
     * @return True se il cover è ammissibile, altrimenti False.
     */
    private boolean isFeasible(Set<Integer> cover) {
        for (Edge edge : graph.getEdges()) {
            if (!(cover.contains(edge.getU()) || cover.contains(edge.getV()))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Genera una chiave per la lista tabu.
     *
     * @param type      Tipo di mossa ("ADD" o "REMOVE").
     * @param nodeIndex Indice del nodo.
     * @return Stringa che rappresenta la mossa.
     */
    private String getMoveKey(String type, int nodeIndex) {
        return type + "_" + nodeIndex;
    }

    /**
     * Aggiorna la lista tabu decrementando il tenure e rimuovendo le mosse scadute.
     */
    private void updateTabuList() {
        List<String> toRemove = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : tabuList.entrySet()) {
            int remaining = entry.getValue() - 1;
            if (remaining <= 0) {
                toRemove.add(entry.getKey());
            } else {
                tabuList.put(entry.getKey(), remaining);
            }
        }
        for (String key : toRemove) {
            tabuList.remove(key);
        }
    }
}
