//ID 33550974
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String [] input=reader.readLine().split(" ");
        MyStack stack = new MyStack();
        for(String s: input) {
            try{
                int n=Integer.parseInt(s);
                stack.push(n);
            }catch(NumberFormatException e) {
                int temp=0;
                if(s.equals("+")) {
                    temp=stack.pop()+stack.pop();
                }else if(s.equals("-")) {
                    temp=-stack.pop()+stack.pop();
                }else if(s.equals("*")) {
                    temp=stack.pop()*stack.pop();
                }else if(s.equals("/")) {
                    int temp1=stack.pop();
                    temp=stack.pop()/temp1;
                }
                stack.push(temp);
            }
        }
       System.out.print(stack.pop());
    }


     static class MyStack  {
        private ArrayList<Integer> list;
        private int top;

        public MyStack () {
            this.list=new ArrayList<>();
            this.top=-1;
        }
        public void push (int n) {
            this.list.add(++top, n);
        }
        public int pop () {
            if(this.top==-1) {
                return -1;
            }else{
                int o=list.get(top);
                this.list.remove(top);
                top--;
                return o;
            }
        }
    }
}