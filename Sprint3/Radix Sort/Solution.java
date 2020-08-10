/*ID 33681208
В пердыдущем решении O(n*v^2), в этом O(n*v) , где v максимальная разрядность.
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;


public class Solution {
    public static int[] count;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(reader.readLine());
        int[] array1 = new int[n];
        if (n != 0) {
            String[] str = reader.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                array1[i] = Integer.parseInt(str[i]);
            }
        }
        count = new int[10];
        int radix = radixMaxValue(array1);
        radix(array1, n, radix);

    }

    public static void radix(int[] array1, int n, int radix) throws Exception {
        int[] result = new int[array1.length];
        int div = 1;
        int iteration = 0;
        while (iteration <= radix) {
            for (int i = 0; i < array1.length; i++) {
                int index = array1[i] / div % 10;
                count[index]++;
            }
            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }
            for (int i = array1.length - 1; i >= 0; i--) {
                int index = array1[i] / div % 10;
                result[--count[index]] = array1[i];
            }
            for (int i = 0; i < array1.length; i++) {
                array1[i] = result[i];
            }
            fill(result);
            fill(count);
            div *= 10;
            iteration++;
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        for (Integer i : array1) {
            writer.write(i + " ");
        }
        writer.close();
    }

    public static void fill(int[] count) {
        for (int i = 0; i < count.length; i++) {
            count[i] = 0;

        }
    }

    public static int radixMaxValue(int[] array) {
        int maxValue = 0;
        int radix = 0;
        for (int i = 0; i < array.length; i++) {
            if (maxValue < array[i]) {
                maxValue = array[i];
            }
        }
        if (maxValue != 0) {
            while (maxValue != 0) {
                maxValue /= 10;
                radix++;
            }
        } else {
            return 1;
        }
        return radix;
    }
}