// ID 33612377
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Solution  {

    public static  void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int n=Integer.parseInt(reader.readLine());
        String [] s=reader.readLine().split(" ");
        int [] array = new int[n];
        for(int i=0; i<n; i++) {
            array[i]=Integer.parseInt(s[i]);
        }
        int total=0;
        if(n>1) {
            while(true) {
                Arrays.sort(array);
                if (array[n - 2] == 0) {
                    break;
                }
                int i = 0;
                while (array[i] == 0) {
                    i++;
                }
                array[i]--;
                array[n - 1]--;
                total++;
            }
        }
        System.out.print(total);
    }
}