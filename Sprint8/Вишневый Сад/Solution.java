//id 38830427

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    public static final int INF = 10000000;
    public static int root;
    public static int min;
    public static int vertex;
    public static int resultVertex;
    public static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        vertex = Integer.parseInt(st.nextToken());
        resultVertex = Integer.parseInt(st.nextToken());
        graph = new ArrayList[vertex + 1];
        boolean b = true;
        for (int i = 0; i < vertex - 1; i++) {
            st = new StringTokenizer(reader.readLine());
            int from = Integer.parseInt(st.nextToken());
            if (b) {
                root = from;
                b = false;
            }
            int to = Integer.parseInt(st.nextToken());
            if (graph[from] == null) {
                graph[from] = new ArrayList<>();
            }
            graph[from].add(to);
            if (graph[to] == null) {
                graph[to] = new ArrayList<>();
            }
            graph[to].add(from);
        }
        min = INF;
        dfs(root, -1 );
        System.out.println(min);
    }

    public static int[] dfs(int curVertex, int p) {
        int[] count = new int[vertex + 1];
        Arrays.fill(count, INF);
        int edges = graph[curVertex].size();
        if (p != -1) {
            edges--;
        }
        count[1] = edges;
        if (graph[curVertex] != null) {
            for (int i = 0; i < graph[curVertex].size(); i++) {
                if (graph[curVertex].get(i) != p) {
                    int[] vertexCount = dfs(graph[curVertex].get(i), curVertex);
                    for(int f = vertex; f >= 1; f--) {
                        if(count[f] != INF) {
                            for(int j = 1; j <= vertex; j++) {
                                if(vertexCount[j] != INF) {
                                    count[f + j] = Math.min(count[f + j], count[f] + vertexCount[j] - 1);
                                }else{
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        if(p == -1) {
            min = Math.min(min, count[resultVertex]);
        }else{
            min = Math.min(min, count[resultVertex] + 1);
        }
        return count;
    }
}