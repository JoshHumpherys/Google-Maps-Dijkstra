/**
 * Created by cubemaster on 4/25/16.
 */
public class Edge {
    private String name;
    private Vertex vertex;
    public Edge(String name, Vertex vertex) {
        this.name = name;
        this.vertex = vertex;
    }

    public String getName() {
        return name;
    }

    public Vertex getVertex() {
        return vertex;
    }

    @Override
    public String toString() {
        return name + " " + vertex.getName();
    }
}
