// create random graph 
// using random edge generation in java
import java.util.*;
//import java.io.*;

public class randomGraph {
    static class Edge {
        int source;
        int destination;
        int weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
    }

    public int vertices;
    public int edges;

    // set max limit to vertices
    final int MAX_LIMIT = 40;

    // Random instance to generate random values
    Random random = new Random();

    // Adjacency list to represent a graph
    public List <List<Integer>> adjacencyList;

    // create the constructor
    public randomGraph() {
        // set number of vertices
        this.vertices = 10;

        // compute max number of edges
        // and randomly choose number of edges 
        // less than or equal to max number 
        // of possible edges
        this.edges = random.nextInt(computeMaxEdges(vertices)) + 1;

        // create adjacency list
        adjacencyList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++)
            adjacencyList.add(new ArrayList<>());

        // for loop to randomly generate edges
        for (int i = 0; i < edges; i++) {
            // randomly select 2 vertices to form edge
            int v = random.nextInt(vertices);
            int w = random.nextInt(vertices);

            // add edge between them
            addEdge(v, w);
        }
    }
    //calculate nCr
    static int nCr(int n, int r) 
    {
        return fact(n) / (fact(r) * fact(n - r));
    }
 
    // Returns factorial of n
    static int fact(int n)
    {
        if(n==0)
        return 1;
        int res = 1;
        for (int i = 2; i <= n; i++)
            res = res * i;
        return res;
    }

    //Method to compute max number of possible edges
    //for given number of vertices
    int computeMaxEdges(int numOfVertices) {
        // it is undirected graph, 
        // so for a given number of vertices
        // there can be at most v*(v-1)/2 edges
        return numOfVertices * ((numOfVertices - 1) / 2);
    }

    //Method to add edges between two vertices
    void addEdge(int v, int w) {
        // add w to v's adjacency list
        adjacencyList.get(v).add(w);

        // add v to w's adjacency list
        adjacencyList.get(w).add(v);
    }

    public static void main(String[] args) {
        // create randomGraph object
        randomGraph practiceGraph = new randomGraph();

        //print graph
        System.out.println("The generated random (practice) graph: ");
        for (int i = 0; i < practiceGraph.adjacencyList.size(); i++) {
            System.out.print(i + " -> { ");

            List<Integer> list = practiceGraph.adjacencyList.get(i);

            if (list.isEmpty())
                System.out.print("No adjacent vertices");
            else {
                int size = list.size();
                for (int j = 0; j<size; j++) {
                    System.out.print(list.get(j));
                    if (j < size - 1) {
                        System.out.print(" , ");
                    }
                }
            System.out.println(" }");
            }

        }
    }
    
}
