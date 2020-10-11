//34985268
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class Solution {
    public static String text;
    public static String[] array;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        text = reader.readLine();
        int n = Integer.parseInt(reader.readLine());
        AhoCorasik bor = new AhoCorasik();
        array = new String[n];
        for (int i = 0; i < n; i++) {
            array[i] = reader.readLine();
            bor.addWord(i);
        }
        bor.addSufRefs();
        bor.findMatchers();
        bor.printResult();
    }

    public static class AhoCorasik {
        public Node root = new Node();
        public Node currentNode;
        public char currentChar;
        public List<Node> bfsList;
        public Map<String, LinkedHashSet<Integer>> resultMap = new HashMap<>();

        public void addWord(int index) {
            currentNode = root;
            for (int i = 0; i < array[index].length(); i++) {
                currentChar = array[index].charAt(i);
                addCurrentCharacter();
                if (i == array[index].length() - 1) {
                    setTermNode(index);
                }
            }
        }

        private void addCurrentCharacter() {
            if (!currentNode.child.containsKey(currentChar)) {
                currentNode.child.put(currentChar, new Node());
                currentNode.child.get(currentChar).parent = currentNode;
                currentNode = currentNode.child.get(currentChar);
                currentNode.inChar = currentChar;
            } else {
                currentNode = currentNode.child.get(currentChar);
            }
        }

        private void setTermNode(int index) {
            currentNode.end = true;
            currentNode.words = index;
        }

        public void addSufRefs() {
            setBfsList();
            for (int i = 0; i < bfsList.size(); i++) {
                setSuffRefCurrentNode(i);
            }
        }

        private void setSuffRefCurrentNode(int index) {
            currentNode = bfsList.get(index);
            if (currentNode == root) {
                root.sufRef = root;
            } else if (currentNode.parent == root) {
                currentNode.sufRef = root;
            } else {
                currentChar = currentNode.inChar;
                if (!currentNode.parent.sufRef.child.containsKey(currentChar)) {
                    currentNode = currentNode.parent.sufRef;
                    findAndSetSufRef(index);
                } else {
                    bfsList.get(index).sufRef = currentNode.parent.sufRef.child.get(currentChar);
                    setSufRefAndTermSufRef(index);
                }
            }
        }

        private void findAndSetSufRef(int index) {
            while (!currentNode.child.containsKey(currentChar)) {
                if (currentNode == root) {
                    bfsList.get(index).sufRef = root;
                    break;
                }
                currentNode = currentNode.sufRef;
                if (currentNode.child.containsKey(currentChar)) {
                    bfsList.get(index).sufRef = currentNode.child.get(currentChar);
                    setSufRefAndTermSufRef(index);
                }
            }
        }

        private void setSufRefAndTermSufRef(int index) {
            if (bfsList.get(index).sufRef.end) {
                bfsList.get(index).termSufRef = bfsList.get(index).sufRef;
            } else {
                selectTermSufRef(index);
            }
        }

        private void selectTermSufRef(int index) {
            Node temp = bfsList.get(index).sufRef;
            while (temp != root) {
                if (temp.sufRef.end) {
                    bfsList.get(index).termSufRef = temp.sufRef;
                    break;
                }
                temp = temp.sufRef;
            }
        }

        private void setBfsList() {
            bfsList = new ArrayList<>();
            bfsList.add(root);
            int sizeList = bfsList.size();
            for (int i = 0; i < sizeList; i++) {
                currentNode = bfsList.get(i);
                for (Map.Entry<Character, Node> pair : currentNode.child.entrySet()) {
                    bfsList.add(pair.getValue());
                }
                sizeList = bfsList.size();
            }
        }

        public void findMatchers() {
            currentNode = root;
            for (int i = 0; i < text.length(); i++) {
                currentChar = text.charAt(i);
                while (!currentNode.child.containsKey(currentChar)) {
                    if (currentNode == root) {
                        break;
                    }
                    currentNode = currentNode.sufRef;
                }
                if (currentNode.child.containsKey(currentChar)) {
                    currentNode = currentNode.child.get(currentChar);
                    checkTerm(currentNode, i);
                }
            }
        }

        private void checkTerm(Node node, int index) {
            Node temp = node;
            if (node.end) {
                putResult(node, index);
            }
            while (temp.termSufRef != null) {
                temp = temp.termSufRef;
                putResult(temp, index);
            }
        }

        private void putResult(Node currentNode, int index) {
            if (!resultMap.containsKey(array[currentNode.words])) {
                resultMap.put(array[currentNode.words], new LinkedHashSet<>());
            }
            int value = index - array[currentNode.words].length() + 2;
            resultMap.get(array[currentNode.words]).add(value);
        }

        public void printResult() throws Exception {
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            for (String str : array) {
                if (resultMap.containsKey(str)) {
                    writer.write(str + " ");
                    Iterator<Integer> it = resultMap.get(str).iterator();
                    while (it.hasNext()) {
                        writer.write(it.next() + " ");
                    }
                } else {
                    writer.write(str);
                }
                writer.newLine();
            }
            writer.close();
        }
    }

    public static class Node {
        public Map<Character, Node> child = new HashMap<>();
        public Node parent;
        public Node sufRef;
        public Character inChar;
        public boolean end = false;
        public Node termSufRef;
        public int words;
    }
}
