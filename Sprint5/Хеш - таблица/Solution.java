//id 33866361
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.LinkedList;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int amountRequest = Integer.parseInt(reader.readLine());
        HashTable table = new HashTable(1000);
        String[] input;
        for(int i = 0; i < amountRequest; i++) {
            input = reader.readLine().split(" ");
            if(input[0].equals("put")) {
                table.put(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
            } else if(input[0].equals("get")) {
                System.out.println(table.get(Integer.parseInt(input[1])));
            } else if(input[0].equals("delete")) {
                table.delete(Integer.parseInt(input[1]));
            }
        }
    }

    public static class HashTable {
        private int size;
        private LinkedList<Content>[] array;

        public HashTable(int size) {
            this.size = size;
            array = new LinkedList[size];
        }

        public void put(int key, int value) {
            int index = hashKey(key);
            int indexList = searcherKey(key, index);
            if(indexList == -1) {
                if(array[index] == null) {
                    array[index] = new LinkedList<>();
                    array[index].add(new Content(key, value));
                } else {
                    array[index].offerFirst(new Content(key, value));
                }
            } else {
                Content currentContent = array[index].get(indexList);
                currentContent.value = value;
            }
        }

        public int get(int key) {
            int index = hashKey(key);
            int indexList = searcherKey(key, index);
            if(indexList != -1) {
                Content findContent = array[index].get(indexList);
                return findContent.value;
            } else {
                return -1;
            }
        }

        public void delete(int key) {
            int index = hashKey(key);
            int indexList = searcherKey(key, index);
            if(indexList == -1) {
                System.out.println("error");
            } else {
                array[index].remove(indexList);
                System.out.println("ok");
            }
        }

        private int searcherKey(int key, int index) {
            LinkedList<Content> findList = array[index];
            if(array[index] != null) {
                Iterator<Content> iterator = findList.iterator();
                Content current;
                while (iterator.hasNext()){
                    current = iterator.next();
                    if(current.key == key) {
                        return findList.indexOf(current);
                    }
                }
            }
            return -1;
        }

        public int hashKey(int key) {
            return (int) ((key % 0.6180339887) * size);
        }

        public static class Content {
            public int key;
            public int value;

            public Content(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
    }
}