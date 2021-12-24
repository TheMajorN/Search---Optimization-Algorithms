public class DijkstraAlgorithm {

    int inf = Integer.MAX_VALUE;    // Global infinite value

    static int[][] graph = new int[6][6];   // Global example graph
    int[] dist = {0, inf, inf, inf, inf, inf};  // Distance array
    boolean[] visited = {false, false, false, false, false, false}; // Boolean array with
                                                                    // all values unvisited
    public static void main(String[] args) {

        DijkstraAlgorithm dj = new DijkstraAlgorithm();

        dj.fill();
        dj.dump();
        dj.relax();

    }
    
    public void fill() {    // Specifically fills
        graph[0][1] = 6;    // graph corresponding
        graph[0][2] = 1;    // to the example graph
        graph[0][4] = 10;   // provided in the handout
        graph[1][3] = 3;    // using an adjacency matrix.
        graph[2][1] = 4;
        graph[2][5] = 2;
        graph[3][4] = 3;
        graph[5][1] = 1;
    }

    public void relax() {
        for (int i = 0; i < graph.length; i++){ // Iterates across the
            int min = minNode();                // graph, relaxes the next
            visited[min] = true;                // unvisited node, and
            relaxActual(min);                   // marks the node as visited.
        }
    }

    public void relaxActual(int min) {  // Actual relax method
        for (int i = 0 ; i < graph.length ; i++) {  			// Iterate across the graph
            if(!visited[i]                  					// If the node is unvisited, if the weight
                    && graph[min][i] != 0   					// value in the adj matrix isn't 0, and if
                    && (dist[min] + graph[min][i] < dist[i])) { // A + W < B, then
                dist[i] = dist[min] + graph[min][i];            // B = A + W
                dump(); 										// Dump after every change to show progress.
            }
        }
    }

    public int minNode() {  // O(n)
        int infTemp = Integer.MAX_VALUE; // Creates temporary infinity variable
        int min = 0;    // Minimum number starts at 0

        for (int i = 0 ; i < dist.length ; i++){    // Iterate across the distance array.
            if(!visited[i] && dist[i] < infTemp){   // If the node is unvisited and less
                infTemp = dist[i];                  // than infinity, the infinity value
                min = i;                            // becomes the new distance value,
            }                                       // and the minimum node now becomes
        }                                           // the node that i is pointing to.
        return min;
    }

    public void dump() {    // O(n)
        for (int i = 0; i < 6; i++) {    // Usual dump method
            if (dist[i] == inf) {
                System.out.println(i + ": inf");
            } else {
                System.out.println(i + ": " + dist[i]);
            }
        }
        System.out.println("");
    }
}