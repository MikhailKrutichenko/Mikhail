//ID 33552626
public class Solution {
   
        public static String hasCycle (Node head) {
        Node temp1=head.getNext();
        if(temp1==null){
            return "False";
        }
        Node temp2=temp1.getNext();
        while(true) {
            if(temp2==null) {
                return "False";
            }
            temp2=temp2.getNext();
            if(temp2==null) {
                return "False";
            }
            if(temp1==temp2) {
                return "True";
            }
            temp1=temp1.getNext();
            temp2=temp2.getNext();
        }
    }
}