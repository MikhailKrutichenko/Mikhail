//33751427
import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(reader.readLine());
        PriorityQueue heap = new PriorityQueue(n + 1);
        if (n == 1) {
            new Member(reader.readLine()).writeString();
        } else {
            for (int i = 0; i < n; i++) {
                heap.add(new Member(reader.readLine()));
            }
            for (int i = 0; i < n; i++) {
                heap.pool();
            }
        }
        Member.writer.close();
    }

    public static class Member {
        public static BufferedWriter writer;

        static {
            try {
                writer = new BufferedWriter(new FileWriter("output.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static char[] kondratiyArray;
        public static int count;
        public int[] point;
        public int sumPoint;
        public String name;
        public boolean kondr = false;
        public int thisCount;

        public Member(String input) {
            if (kondratiyArray == null) {
                kondratiyArray = "kondratiy".toCharArray();
            }
            count++;
            thisCount = count;
            String[] info = input.split(" ");
            name = info[0];
            point = new int[info.length - 1];
            for (int i = 1; i < info.length; i++) {
                int temp = Integer.parseInt(info[i]);
                point[i - 1] = temp;
                if (temp > 0) {
                    sumPoint += point[i - 1];
                }
            }
            if (this.kondratiy()) {
                kondr = true;
            }
        }

        public void writeString() throws Exception {
            writer.write(name + " ");
            for (int i = 0; i < point.length; i++) {
                writer.write(point[i] + " ");
            }
            writer.newLine();
        }

        public boolean compare(Member parent) {
            if (this.kondr && parent.kondr) {
                return this.compareKondr(parent);
            } else if (this.kondr) {
                return true;
            } else if (parent.kondr) {
                return false;
            } else if (this.sumPoint > parent.sumPoint) {
                return true;
            } else if (this.sumPoint == parent.sumPoint) {
                if (this.name.compareTo(parent.name) < 0) {
                    return true;
                } else if (this.name.compareTo(parent.name) == 0) {
                    if (this.thisCount > parent.thisCount) {
                        return true;
                    }
                }
            }
            return false;
        }

        public boolean compareKondr(Member konddr) {
            if (this.thisCount > konddr.thisCount) {
                return true;
            } else {
                return false;
            }
        }

        public boolean kondratiy() {
            int count = 0;
            char[] array = this.name.toCharArray();
            for (int i = 0; i < kondratiyArray.length; i++) {
                for (int j = 0; j < array.length; j++) {
                    if (kondratiyArray[i] == array[j]) {
                        count++;
                        break;
                    }
                }
            }
            if (count >= kondratiyArray.length) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static class PriorityQueue {
        public Member[] data;
        public int indexLastInsert;

        public PriorityQueue(int size) {
            this.data = new Member[size + 1];
        }

        public void add(Member member) {
            data[++indexLastInsert] = member;
            sortElement(indexLastInsert);
        }

        public void pool() throws Exception {
            data[1].writeString();
            data[1] = data[indexLastInsert];
            data[indexLastInsert--] = null;
            reversSort(1);

        }

        public void sortElement(int index) {
            if (index == 1) {
                return;
            }
            if (data[index].compare(data[index / 2])) {
                changeMember(index, index / 2);
                sortElement(index / 2);
            }
        }

        public void reversSort(int index) {
            if (index * 2 + 1 >= data.length) {
                return;
            }
            Member parent = data[index];
            Member leftChild = data[index * 2];
            Member rightChild = data[index * 2 + 1];
            if (childrenNotEmpty(leftChild, rightChild) && (leftChild.kondr && rightChild.kondr)) {
                if (leftChild.compareKondr(rightChild)) {
                    changeMember(index, index * 2);
                    reversSort(index * 2);
                } else {
                    changeMember(index, index * 2 + 1);
                    reversSort(index * 2 + 1);
                }
            } else if (childrenNotEmpty(leftChild, rightChild) && leftChild.kondr ^ rightChild.kondr) {
                if (leftChild.kondr) {
                    changeMember(index, index * 2);
                    reversSort(index * 2);
                } else {
                    changeMember(index, index * 2 + 1);
                    reversSort(index * 2 + 1);
                }
            } else if (childrenNotEmpty(leftChild, rightChild)) {
                if (leftChild.compare(rightChild)) {
                    if (leftChild.compare(parent)) {
                        changeMember(index, index * 2);
                        reversSort(index * 2);
                    }
                } else {
                    if (rightChild.compare(parent)) {
                        changeMember(index, index * 2 + 1);
                        reversSort(index * 2 + 1);
                    }
                }
            } else if (leftChild == null ^ rightChild == null) {
                if (leftChild != null) {
                    if (leftChild.compare(parent)) {
                        changeMember(index, index * 2);
                        reversSort(index * 2);
                    }
                } else {
                    if (rightChild.compare(parent)) {
                        changeMember(index, index * 2 + 1);
                        reversSort(index * 2 + 1);
                    }
                }
            }
        }

        public void changeMember(int indexParent, int indexChild) {
            Member temp = data[indexParent];
            data[indexParent] = data[indexChild];
            data[indexChild] = temp;
        }

        public boolean childrenNotEmpty(Member one, Member two) {
            if (one != null && two != null) {
                return true;
            } else {
                return false;
            }
        }
    }
}

