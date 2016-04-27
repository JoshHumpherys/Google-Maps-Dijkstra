// Josh Humpherys
// Dijkstra's Algorithm

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class DijkstraHighway {
    public static void main(String[] args) {
        if(args.length < 3) {
            System.err.println("Error: Please pass the file name, a start point and an end point as command line arguments.");
            System.exit(1);
        }
        File f = new File(args[0]);
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch(FileNotFoundException e) {
            System.err.println("Error: File not found.");
            System.exit(1);
        }
        if(!s.hasNextLine()) {
            System.err.println("Error: File is empty.");
            System.exit(1);
        }
        String[] header = s.nextLine().split(" ");
        if(header.length < 2) {
            System.err.println("Error: Unable to parse header.");
            System.exit(1);
        }
        int numVertices = -1;
        int numPaths = -1;
        try {
            numVertices = Integer.parseInt(header[0]);
            numPaths = Integer.parseInt(header[1]);
        } catch(NumberFormatException e) {
            System.err.println("Error: Unable to parse header.");
            System.exit(1);
        }
        Graph graph = new Graph(numVertices * 2);
        for(int i = 0; i < numVertices; i++) {
            if(s.hasNextLine()) {
                String[] vertexVars = s.nextLine().split(" ");
                Vertex vertex;
                try {
                    vertex = new Vertex(vertexVars[0], Double.parseDouble(vertexVars[1].substring(0, vertexVars[1].length() - 1)), Double.parseDouble(vertexVars[2]), i);
                    graph.add(i, vertex);
                } catch(NumberFormatException e) {
                    System.err.println("Warning: Unable to parse line " + (i + 1) + " of file. Ignoring line " + (i + 1) + ".");
                    continue;
                }
            } else {
                System.err.println("Error: Unable to parse file.");
            }
        }
        for(int i = 0; i < numPaths; i++) {
            if(s.hasNextLine()) {
                String[] edgeVars = s.nextLine().split(" ");
                try {
                    Edge edge = new Edge(edgeVars[2], graph.get(Integer.parseInt(edgeVars[1])));
                    Vertex vertex = graph.get(Integer.parseInt(edgeVars[0]));
                    if(vertex != null) {
                        vertex.addEdge(edge);
                    } else {
                        printUnexpectedError();
                        System.exit(-1);
                    }
                } catch(NumberFormatException e) {
                    System.err.println("Warning: Unable to parse line " + (i + 1) + " of file. Ignoring line " + (i + 1) + ".");
                }
            } else {
                System.err.println("Error: Unable to parse file.");
            }
        }
        System.out.println(graph);
        try {
            graph.writeToFile("out.gra");
        } catch(FileNotFoundException e) {
            printUnexpectedError();
            System.exit(-1);
        } catch(UnsupportedEncodingException e) {
            System.out.println("Error: Unable to write file in encoding \"UTF-8\".");
            System.exit(-1);
        }
    }

    public static void printUnexpectedError() {
        System.err.println("Error: Unexpected error on line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + ".");
    }
}