import java.util.*;

class Node {
    String name; // Node name or identifier
    int cost;    // Cost to reach this node from the start node

    public Node(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }
}

public class UCS {
    public static int uniformCostSearch(Map<String, List<Node>> graph, String start, String goal) {
        PriorityQueue<Node> openList = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                return Integer.compare(node1.cost, node2.cost);
            }
        });

        List<String> closedList = new ArrayList<>();
        Map<String, Integer> costSoFar = new HashMap<>();

        openList.add(new Node(start, 0));
        costSoFar.put(start, 0);

        while (!openList.isEmpty()) {
            Node current = openList.poll();

            if (current.name.equals(goal)) {
                return current.cost; // Goal reached, return the cost
            }

            closedList.add(current.name);

            for (Node neighbor : graph.get(current.name)) {
                int newCost = costSoFar.get(current.name) + neighbor.cost;

                if (!costSoFar.containsKey(neighbor.name) || newCost < costSoFar.get(neighbor.name)) {
                    costSoFar.put(neighbor.name, newCost);
                    openList.add(new Node(neighbor.name, newCost));
                }
            }
        }

        return -1; // Goal not reachable
    }

    public static void main(String[] args) {
        // Create a sample graph as a Map of nodes and their neighbors with associated costs
        Map<String, List<Node>> graph = new HashMap<>();
        graph.put("A", Arrays.asList(new Node("B", 1), new Node("C", 3)));
        graph.put("B", Arrays.asList(new Node("D", 2)));
        graph.put("C", Arrays.asList(new Node("D", 4), new Node("E", 5)));
        graph.put("D", Arrays.asList(new Node("E", 1)));
        graph.put("E", Arrays.asList());

        String startNode = "A";
        String goalNode = "E";

        int result = uniformCostSearch(graph, startNode, goalNode);

        if (result != -1) {
            System.out.println("Cost from " + startNode + " to " + goalNode + ": " + result);
        } else {
            System.out.println("No path found from " + startNode + " to " + goalNode);
        }
    }
}


