//37949554 

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int amountVertex = Integer.parseInt(st.nextToken());
        int[][] graph = buildGraph(reader, amountVertex);
        if (isOptimal(graph, amountVertex)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static int[][] buildGraph(BufferedReader reader, int amountVertex) throws IOException {
        int[][] graph = new int[amountVertex + 1][amountVertex + 1];
        for (int i = 0; i < amountVertex - 1; i++) {
            String input = reader.readLine();
            int point = i + 1;
            for (int j = point + 1, k = 0; j < amountVertex + 1; j++, k++) {
                if (input.charAt(k) == 'R') {
                    graph[point][j] = 1;
                } else {
                    graph[j][point] = 1;
                }
            }
        }
        return graph;
    }

    public static boolean isOptimal(int[][] graph, int amountVertex) {
        int[] vertexVisit = new int[amountVertex + 1];
        for (int i = 1; i < amountVertex + 1; i++) {
            if(vertexVisit[0] == 7) {
                return false;
            }
            if (vertexVisit[i] == 0) {
                dfs(i, vertexVisit, graph);
            }
        }
        return true;
    }

    public static void dfs(int v, int[] vertexVisit, int[][] graph) {
        for (int i = 1; i < vertexVisit.length; i++) {
            if (graph[v][i] == 1 && vertexVisit[i] == 0) {
                vertexVisit[v] = 1;
                dfs(i, vertexVisit, graph);
            } else if (graph[v][i] == 1 && vertexVisit[i] == 1) {
                Arrays.fill(vertexVisit, Integer.MAX_VALUE);
                vertexVisit[0] = 7;
                return;
            }
        }
        vertexVisit[v] = Integer.MAX_VALUE;
    }
}