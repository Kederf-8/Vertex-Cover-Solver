package src;

// Solution.java
import java.util.HashSet;
import java.util.Set;

public class Solution implements Cloneable {
    private final Set<Integer> vertexCover; // Indici dei nodi nel vertex cover
    private final double cost; // Costo totale (somma dei pesi)

    public Solution(Set<Integer> vertexCover, double cost) {
        this.vertexCover = new HashSet<>(vertexCover);
        this.cost = cost;
    }

    public Set<Integer> getVertexCover() {
        return vertexCover;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public Solution clone() {
        return new Solution(this.vertexCover, this.cost);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertex Cover (nodi): ");
        for (Integer node : vertexCover) {
            sb.append(node + 1).append(" "); // Converti in 1-based per la leggibilità
        }
        sb.append("| Costo Totale: ").append(cost);
        return sb.toString();
    }
}
