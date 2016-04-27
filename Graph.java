import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

/**
 * Created by cubemaster on 4/25/16.
 */
public class Graph {
    private HashTable<Integer, Vertex> vertices;
    public Graph(int size) {
        vertices = new HashTable<>(size);
    }

    public void add(int i, Vertex v) {
        vertices.put(i, v);
    }

    public Vertex get(int i) {
        return vertices.get(i);
    }

    public void writeToFile(String fileName) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");
        writer.write(toStringAsFile());
        writer.close();
    }

    private String toStringAsFile() {
        StringBuilder sbVertices = new StringBuilder();
        StringBuilder sbEdges = new StringBuilder();
        int numVertices = 0;
        int numEdges = 0;
        for(HashPair<Integer, Vertex> pair : vertices) {
            numVertices++;
            sbVertices.append(pair.getValue() + "\n");
            for(Edge edge : pair.getValue()) {
                numEdges++;
                sbEdges.append(pair.getKey() + " " + edge.getVertex().getIndex() + " " + edge.getName() + "\n");
            }
        }
        return (numVertices + " " + numEdges + "\n") + sbVertices.toString() + sbEdges.toString();
    }

    @Override
    public String toString() {
        Iterator<HashPair<Integer, Vertex>> it = vertices.iterator();
        StringBuilder sb = new StringBuilder();
        if(!it.hasNext()) {
            return "";
        } else {
            sb.append(it.next().getValue().toStringWithEdges());
        }
        while(it.hasNext()) {
            Vertex vertex = it.next().getValue();
            sb.append("\n");
            sb.append(vertex.toStringWithEdges());
        }
        return sb.toString();
    }
}
