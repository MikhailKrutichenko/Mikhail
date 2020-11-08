//37952782

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    public static BufferedReader reader;
    public static ArrayList<Integer>[] graph;
    public static PriorityQueue<Edge> pq = new PriorityQueue<>();
    public static boolean[] vertexVisit;
    public static int result;
    public static int amountVertex;
    public static int count;

    public static void main(String[] args) throws Exception {
        buildGraph();
        maxSumFrame();
        printResult();
    }

    public static void buildGraph() throws IOException {
        reader = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        amountVertex = Integer.parseInt(st.nextToken());
        int edg = Integer.parseInt(st.nextToken());
        graph = new ArrayList[amountVertex + 1];
        for (int i = 0; i < edg; i++) {
            addVertex(st);
        }
    }

    private static void addVertex(StringTokenizer st) throws IOException {
        st = new StringTokenizer(reader.readLine());
        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());
        int n3 = Integer.parseInt(st.nextToken());
        if (graph[n1] == null) {
            graph[n1] = new ArrayList<>();
        }
        graph[n1].add(n2);
        graph[n1].add(n3);
        if (graph[n2] == null) {
            graph[n2] = new ArrayList<>();
        }
        graph[n2].add(n1);
        graph[n2].add(n3);
    }

    public static void maxSumFrame() {
        vertexVisit = new boolean[amountVertex + 1];
        dfs(1);
    }

    private static void dfs(int v) {
        vertexVisit[v] = true;
        if (graph[v] != null) {
            for (int i = 0; i < graph[v].size(); i++) {
                pq.add(new Edge(graph[v].get(i), graph[v].get(++i)));
            }
            while (count < amountVertex - 1 && !pq.isEmpty()) {
                Edge e = pq.poll();
                if (!vertexVisit[e.toVertex]) {
                    result += e.weight;
                    count++;
                    dfs(e.toVertex);
                    break;
                }
            }
        }
    }

    public static void printResult() {
        if (count == amountVertex - 1) {
            System.out.println(result);
        } else {
            System.out.println("Oops! I did it again");
        }
    }

    public static class Edge implements Comparable<Edge> {
        int weight;
        int toVertex;

        public Edge(int toVertex, int weight) {
            this.toVertex = toVertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge edge) {
            if (this.weight > edge.weight) {
                return -1;
            }
            if (this.weight < edge.weight) {
                return 1;
            }
            return 0;
        }
    }
}