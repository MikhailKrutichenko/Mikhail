//33626879
import java.io.BufferedReader;
import java.io.FileReader;

public class Solution {
    public static int[] array;
    public static int left;
    public static int right;
    public static int element;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(reader.readLine());
        element = Integer.parseInt(reader.readLine());
        String[] s = reader.readLine().split(" ");
        array = new int[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(s[i]);
        }
        left=0;
        right=array.length-1;
        System.out.println(binaryBoard(element, array[0]));
    }

    public static int binaryBoard(int element, int temp) {
        int mid=(right+left)/2;
        if(element==array[mid]) {
            return mid;
        }
        if(left>=right){
            return -1;
        }
        if(temp>array[right]) {
            if (array[mid] > temp) {
                bynaryHelperOne(array[mid], mid, temp);
            }
            if(array[mid] < temp) {
                bynaryHelperThree(array[mid], mid, temp);
            }
        }else{
            if(array[mid]>element) {
                right = mid-1;
            }
            if(array[mid]<element) {
                left = mid+1;
            }
        }
        temp=array[left];
        return binaryBoard(element, temp);
    }

    public static void bynaryHelperOne(int arrayMid, int mid, int temp)  {
        if (arrayMid < element) {
            left = mid+1;
        }else{
            bynaryHelperTwo(mid, temp);
        }
    }

    public static void bynaryHelperTwo(int mid,  int temp) {
        if(element > temp) {
            right = mid-1;
        }else{
            left = mid+1;
        }
    }

    public static  void bynaryHelperThree(int arrayMid, int mid, int temp) {
        if(arrayMid > element) {
            right = mid-1;
        }else{
            bynaryHelperFour(mid, temp);
        }
    }

    public static void bynaryHelperFour(int mid, int temp) {
        if(element < temp) {
            left = mid+1;
        }else{
            right = mid-1;
        }
    }
}