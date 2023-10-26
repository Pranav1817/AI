
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {
    public static void print2DMarix(int[][] graph) {
        for (int[] row : graph) {
            for (int ele : row) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }
    }

    public static void bfs(int[][] graph, ArrayList<Integer> ans, int target, int current) {
        if (current == target) {
            ans.add(target + 1);
            return;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(current);
        int visited[] = new int[graph.length];
        visited[current] = 1;
        while (!queue.isEmpty()) {
            int n = queue.remove();
            int[] temp = graph[n];
            ans.add(n + 1);
            if (n == target) {
                return;
            }
            for (int i = 0; i < temp.length; i++) {
                if (visited[i] == 0 && temp[i] != 0) {
                    queue.add(i);
                    visited[i] = 1;
                }
            }
        }

        ans.add(-1);
    }

    public static void print(ArrayList<Integer> ans) {
        for (int ele : ans) {
            System.out.print(ele + " ");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Is your graph Bi-directional: (true/false)");
        boolean isByDirectional = sc.nextBoolean();
        System.out.println("Enter the number of nodes in your graph: ");
        int numOfVertex = sc.nextInt();
        int[][] graph = new int[numOfVertex][numOfVertex];
        System.out.println("Enter the number of edges in your graph: ");
        int numOfEdges = sc.nextInt();
        for (int i = 0; i < numOfEdges; i++) {
            System.out.println("Enter the source vertex of edge no. " + (i + 1));
            int v1 = sc.nextInt();
            System.out.println("Enter the destination vertex v2 of edge no. " + (i + 1));
            int v2 = sc.nextInt();
            graph[v1 - 1][v2 - 1] = 1;
            if (isByDirectional) {
                graph[v2 - 1][v1 - 1] = 1;
            }
        }

        System.out.println("The matrix representation of Given graph is: ");
        print2DMarix(graph);

        System.out.println("Enter the current node: ");
        int current = sc.nextInt();

        System.out.println("Enter the target node: ");
        int target = sc.nextInt();
        ArrayList<Integer> ans = new ArrayList<>();
        bfs(graph, ans, target - 1, current - 1);
        if (ans.get(ans.size() - 1) == -1) {
            System.out.println("Element not found, path followed is:  ");
            print(ans);
        } else {
            System.out.println("path followed to reach the target node is: ");
            print(ans);
        }

        sc.close();
    }
}
