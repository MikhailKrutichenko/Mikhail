//34980165

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Solution {
    public static BufferedWriter writer;
    public static String[] array;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(reader.readLine());
        array = new String[n];
        Bor bor = new Bor();
        for (int i = 0; i < n; i++) {
            array[i] = reader.readLine();
            bor.add(i);
        }
        writer = new BufferedWriter(new FileWriter("output.txt"));
        for (int i = 0; i < n; i++) {
            bor.findPalindromePairs(i);
        }
        writer.close();
    }

    public static class Bor {
        public TreeSet<Integer> result = new TreeSet<>();
        public Node root = new Node();

        public void add(int index) {
            Node curNode = root;
            for (int i = array[index].length() - 1; i >= 0; i--) {
                char curChar = array[index].charAt(i);
                if (!curNode.child.containsKey(curChar)) {
                    curNode.child.put(curChar, new Node());
                }
                curNode = curNode.child.get(curChar);
                if (i == 0) {
                    curNode.term = true;
                    curNode.indexWord = index;
                }
            }
        }

        public void findPalindromePairs(int index) throws IOException {
            Node curNode = root;
            for (int i = 0; i < array[index].length(); i++) {
                char curChar = array[index].charAt(i);
                if (!curNode.child.containsKey(curChar)) {
                    break;
                } else {
                    curNode = curNode.child.get(curChar);
                    if (i == array[index].length() - 1) {
                        addMatches(curNode, 0);
                    } else if (curNode.term) {
                        addMatches(index, i + 1, curNode.indexWord);
                    }
                }
            }
            printResult(index);
        }

        private void printResult(int index) throws IOException {
            while (!result.isEmpty()) {
                if ((index + 1) != (result.first() + 1)) {
                    writer.write((index + 1) + " " + (result.pollFirst() + 1));
                    writer.newLine();
                } else {
                    result.pollFirst();
                }
            }
        }

        private void addMatches(int index, int start, int indexWords) {
            boolean b = true;
            for (int i = start, j = array[index].length() - 1; i < j; i++, j--) {
                if (array[index].charAt(i) != array[index].charAt(j)) {
                    b = false;
                    break;
                }
            }
            if (b) {
                result.add(indexWords);
            }
        }

        private void addMatches(Node cur, int index) {
            if (cur.term) {
                boolean b = true;
                for (int i = index - 1, j = 0; i > j; i--, j++) {
                    if (array[cur.indexWord].charAt(i) != array[cur.indexWord].charAt(j)) {
                        b = false;
                        break;
                    }
                }
                if (b) {
                    result.add(cur.indexWord);
                }
            }
            for (Map.Entry<Character, Node> pair : cur.child.entrySet()) {
                addMatches(pair.getValue(), index + 1);
            }
        }
    }

    public static class Node {
        public HashMap<Character, Node> child = new HashMap<>();
        public boolean term = false;
        public int indexWord;
    }
}