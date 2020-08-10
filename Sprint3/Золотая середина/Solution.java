//ID 33681383
import java.io.BufferedReader;
import java.io.FileReader;


public class Solution {
    public static int n;
    public static int m;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        n = Integer.parseInt(reader.readLine());
        m = Integer.parseInt(reader.readLine());
        String[] str = reader.readLine().split(" ");
        int[] array1 = new int[n];
        for (int i = 0; i < n; i++) {
            array1[i] = Integer.parseInt(str[i]);
        }
        str = reader.readLine().split(" ");
        int[] array2 = new int[m];
        for (int i = 0; i < m; i++) {
            array2[i] = Integer.parseInt(str[i]);
        }
        double result = getMedian(array1, array2);
        if (result % 1 == 0) {
            System.out.print((int) result);
        } else {
            System.out.print(result);
        }
    }

    public static int searchMedian(int[] array1, int i, int[] array2, int j, int k) {
        if (i >= array1.length) {
            return array2[j + k - 1];
        }
        if (j >= array2.length) {
            return array1[i + k - 1];
        }
        if (k == 1) {
            return Math.min(array1[i], array2[j]);
        }
        int mid1 = 0;
        if (i + k / 2 - 1 < array1.length) {
            mid1 = array1[i + k / 2 - 1];
        } else {
            mid1 = Integer.MAX_VALUE;
        }
        int mid2 = 0;
        if (j + k / 2 - 1 < array2.length) {
            mid2 = array2[j + k / 2 - 1];
        } else {
            mid2 = Integer.MAX_VALUE;
        }
        if (mid1 < mid2) {
            return searchMedian(array1, i + k / 2, array2, j, k - k / 2);
        } else {
            return searchMedian(array1, i, array2, j + k / 2, k - k / 2);
        }
    }

    public static double getMedian(int[] array1, int[] array2) {
        int left = (m + n + 1) / 2;
        int right = (m + n + 2) / 2;
        int leftResult = searchMedian(array1, 0, array2, 0, left);
        int rightResult = searchMedian(array1, 0, array2, 0, right);
        return (leftResult + rightResult) / 2.0;
    }
}