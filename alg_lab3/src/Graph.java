import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Graph {

    private final int V;
    private final List<List<Integer>> adj;

    public Graph(int V) {
        this.V = V;
        adj = new ArrayList<>(V);

        for (int i = 0; i < V; i++) {
            adj.add(new LinkedList<>());
        }
    }

    private boolean isCyclicUtil(int i, boolean[] visited,
                                 boolean[] stack) {
        if (stack[i]) {
            return true;
        }

        if (visited[i]) {
            return false;
        }

        visited[i] = true;
        stack[i] = true;

        System.out.print(i + " ");

        List<Integer> children = adj.get(i);

        for (Integer c : children) {
            if (isCyclicUtil(c, visited, stack)) {
                return true;
            }
        }
        stack[i] = false;

        return false;
    }

    private void addEdge(int source, int dest) {
        adj.get(source).add(dest);
    }

    private boolean isCyclic() {
        boolean[] visited = new boolean[V];
        boolean[] recStack = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (isCyclicUtil(i, visited, recStack))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Graph g = new Graph(19);

        g.addEdge(18, 1);
        g.addEdge(3, 0);
        g.addEdge(0, 10);
        g.addEdge(7, 9);
        g.addEdge(11, 10);
        g.addEdge(11, 5);
        g.addEdge(8, 5);
        g.addEdge(8, 4);
        g.addEdge(5, 0);
        g.addEdge(7, 10);
        g.addEdge(5, 4);
        g.addEdge(6, 10);
        g.addEdge(8, 6);
        g.addEdge(4, 11);
        g.addEdge(10, 5);
        g.addEdge(8, 1);
        g.addEdge(6, 11);
        g.addEdge(6, 1);
        g.addEdge(9, 1);

//        g.addEdge(8, 5);
//        g.addEdge(0, 3);
//        g.addEdge(1, 4);
//        g.addEdge(4, 2);
//        g.addEdge(5, 0);
//        g.addEdge(5, 4);
//        g.addEdge(5, 1);
//        g.addEdge(1, 2);
//        g.addEdge(3, 1);

        if (g.isCyclic()) {
            System.out.println("\nGraph contains cycle");
        } else {
            System.out.println("\nGraph doesn't "
                    + "contain cycle");
        }
    }
}