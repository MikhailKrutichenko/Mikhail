//ID 33560948
import java.io.BufferedReader;
import java.io.FileReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader=new BufferedReader(new FileReader("input.txt"));
        int n=Integer.parseInt(reader.readLine());
        MyQueue queue = new MyQueue(n);
        for(int i=0; i<n; i++) {
            String temp = reader.readLine();
            if (temp.equals("get")) {
                System.out.println(queue.get());
            }else if(temp.equals("get_size")){
                System.out.println(queue.get_size());
            }else{
                String [] s=temp.split(" ");
                queue.put(Integer.parseInt(s[1]));
            }
        }
    }

    public static class MyQueue {
        private int size;
        private Stack stack;
        private Stack stackPop;
        private int head;
        private int tail;
        private boolean point;

        public MyQueue (int size) {
            this.size=size;
            this.stack=new Stack(size);
            this.stackPop=new Stack(size);
        }
        public void put(int n) {
            if(stackPop.getSize()==0){
                stack.push(n);
            }else{
                while(stackPop.getSize()!=0) {
                    stack.push(stackPop.pop());
                }
                stack.push(n);
            }
        }
        public String get() {
            if(stack.top==-1&&stackPop.top==-1) {
                return "error";
            }else {
                if (stackPop.getSize() == 0) {
                    while (stack.getSize() != 0) {
                        stackPop.push(stack.pop());
                    }
                    return stackPop.pop()+"";
                } else {
                    return stackPop.pop()+"";
                }
            }
        }
        public int get_size() {
            if(stackPop.getSize()==0) {
                return stack.getSize();
            }else{
                return stackPop.getSize();
            }
        }
    }
    public static class Stack {
        private int [] array;
        private int size;
        private int top;

        public Stack(int size) {
            this.size=size;
            this.array=new int[size];
            this.top=-1;
        }
        public void push(int n) {
            array[++top]=n;
        }
        public int pop() {
            return array[top--];
        }
        public int getSize() {
            return top+1;
        }
    }
}