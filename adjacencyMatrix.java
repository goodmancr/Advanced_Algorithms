// Caroline Goodman
// CSCI 310: HW 3
// Prim's Code Credit: https://www.programiz.com/dsa/prim-algorithm
// Kruskal's Code Credit: 

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

public class adjacencyMatrix {
    int vertices;
    int matrix[][];
    Random rand = new Random();

    adjacencyMatrix(int vertices) {
        this.vertices=vertices;
        matrix = new int[vertices][vertices];
    }

    public int[][] getMatrix() {
        return matrix;
    }

    static int factorial(int n){    
        if (n == 0)    
          return 1;    
        else    
          return(n * factorial(n-1));    
    }    

    void addEdge(int source, int destination) {
        matrix[source][destination]=rand.nextInt(15) + 1;
        matrix[destination][source]=rand.nextInt(15) + 1;
    }

    void printGraph() {
        for(int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    //Prim's
    public void Prim(int G[][], int V) {
        int INF = 9999999;
        int no_edge; // number of edge

        // create a array to track selected vertex
        // selected will become true otherwise false
        boolean[] selected = new boolean[V];

        // set selected false initially
        Arrays.fill(selected, false);

        // set number of edge to 0
        no_edge = 0;

        // the number of egde in minimum spanning tree will be
        // always less than (V -1), where V is number of vertices in
        // graph

        // choose 0th vertex and make it true
        selected[0] = true;

        // print for edge and weight
        System.out.println("Edge : Weight");

        while (no_edge < V - 1) {
            // For every vertex in the set S, find the all adjacent vertices
            // , calculate the distance from the vertex selected at step 1.
            // if the vertex is already in the set S, discard it otherwise
            // choose another vertex nearest to selected vertex at step 1.

            int min = INF;
            int x = 0; // row number
            int y = 0; // col number

            for (int i = 0; i < V; i++) {
                if (selected[i] == true) {
                for (int j = 0; j < V; j++) {
                    // not in selected and there is an edge
                    if (!selected[j] && G[i][j] != 0) {
                    if (min > G[i][j]) {
                        min = G[i][j];
                        x = i;
                        y = j;
                    }
                    }
                }
                }
            }
            System.out.println(x + " - " + y + " :  " + G[x][y]);
            selected[y] = true;
            no_edge++;
        }
    }

    // Kruskal's
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
       
        static class Graph {
        int vertices;
        ArrayList<Edge> allEdges = new ArrayList<>();
       
        Graph(int vertices) {
        this.vertices = vertices;
        }
       
        public void addEgde(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        allEdges.add(edge); //add to total edges
        }
        
        public void kruskalMST(){
        PriorityQueue<Edge> pq = new PriorityQueue<>(allEdges.size(), Comparator.comparingInt(o -> o.weight));
       
        //add all the edges to priority queue, //sort the edges on weights
        for (int i = 0; i <allEdges.size() ; i++) {
        pq.add(allEdges.get(i));
        }
       
        //create a parent []
        int [] parent = new int[vertices];
       
        //makeset
        makeSet(parent);
       
        ArrayList<Edge> mst = new ArrayList<>();
       
        //process vertices – 1 edges
        int index = 0;
        while(index<vertices–1){
        Edge edge = pq.remove();
        //check if adding this edge creates a cycle
        int x_set = find(parent, edge.source);
        int y_set = find(parent, edge.destination);
       
        if(x_set==y_set){
        //ignore, will create cycle
        }else {
        //add it to our final result
        mst.add(edge);
        index++;
        union(parent,x_set,y_set);
        }
        }
        //print MST
        System.out.println("Minimum Spanning Tree: ");
        printGraph(mst);
        }
       
        public void makeSet(int [] parent){
        //Make set- creating a new element with a parent pointer to itself.
        for (int i = 0; i <vertices ; i++) {
        parent[i] = i;
        }
        }
       
        public int find(int [] parent, int vertex){
        //chain of parent pointers from x upwards through the tree
        // until an element is reached whose parent is itself
        if(parent[vertex]!=vertex)
        return find(parent, parent[vertex]);;
        return vertex;
        }
       
        public void union(int [] parent, int x, int y){
        int x_set_parent = find(parent, x);
        int y_set_parent = find(parent, y);
        //make x as parent of y
        parent[y_set_parent] = x_set_parent;
        }
       
        public void printGraph(ArrayList<Edge> edgeList){
        for (int i = 0; i <edgeList.size() ; i++) {
        Edge edge = edgeList.get(i);
        System.out.println("Edge-" + i + " source: " + edge.source +
        " destination: " + edge.destination +
        " weight: " + edge.weight);
        }
    }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        try (Scanner sc = new Scanner(System.in)) {
            Random rand = new Random();
            System.out.println("Enter the number of vertices: ");
            int V = sc.nextInt();
            adjacencyMatrix amg = new adjacencyMatrix(V);

            System.out.println("Enter the number of edges: ");
            int E = sc.nextInt();
            // ensure each vertex has an edge by iterating through
            // the first column and assigning a weight
            for (int i = 1; i < V; i++) {
                amg.addEdge(i, 0);
            }

            // for the remaining number of edges in E, 
            // randomly choose vertices to connect
            for (int i = 0; i < E - V; i++) {
                int a = rand.nextInt(V);
                int b = rand.nextInt(V);
                amg.addEdge(a,b);
            }

            amg.printGraph();
            int[][] newMatrix = amg.getMatrix();
            System.out.println("Prim's Algorithm: ");
            amg.Prim(newMatrix, 10000);
            //mark end time 
            long end = System.currentTimeMillis();
            //calculate full runtime
            long run_time = end - start;
            System.out.println("Prim's Algorithm Runtime: " + run_time);
        }
    }
}
