package src;
// Edge.java
public class Edge {
    private final int u; // Indice del nodo u (0-based)
    private final int v; // Indice del nodo v (0-based)

    public Edge(int u, int v) {
        this.u = u;
        this.v = v;
    }

    public int getU() {
        return u;
    }

    public int getV() {
        return v;
    }

    @Override
    public String toString() {
        return "(" + (u + 1) + " -- " + (v + 1) + ")";
    }
}
