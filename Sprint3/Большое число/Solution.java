/*
Для уменьшения длинны строки
вместо if(a || b) {
            .....
          }
записал  if(a) {
            ....
            }else if(b) {
            .....
            }
 Это выход из ситуации??
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Solution {
    public static int[][] result;
    public static int thousand;
    public static int zero;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(reader.readLine());
        String[] str = reader.readLine().split(" ");
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(str[i]);
        }
        Arrays.sort(array);
        result = new int[10][n];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = -1;
            }
        }
        partition(array);
        sort(result, n);
        for (int i = 9; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (result[i][j] != -1) {
                    System.out.print(result[i][j]);
                }
            }
        }
        while (thousand != 0) {
            System.out.print(1000);
            thousand--;
        }
        while (zero != 0) {
            System.out.print(0);
            zero--;
        }
    }

    public static void partition(int[] array) {
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == 1000) {
                thousand++;
                continue;
            }
            if (array[i] == 0) {
                zero++;
                continue;
            }
            int index = index(array[i]);
            for (int k = 0; k < array.length; k++) {
                if (result[index][k] == -1) {
                    result[index][k] = array[i];
                    break;
                }
            }
        }
    }

    public static int length(int value) {
        int count = 0;
        if (value == 0) {
            return 1;
        }
        while (value != 0) {
            value /= 10;
            count++;
        }
        return count;
    }

    public static int index(int value) {
        if (length(value) == 1) {
            return value;
        } else if (length(value) == 2) {
            return value / 10;
        } else {
            return value / 100;
        }
    }

    public static void sort(int[][] array, int n) {
        for (int i = 0; i < 10; i++) {
            boolean b = true;
            while (b) {
                b = false;
                for (int j = 0; j < n - 1; j++) {
                    if (length(array[i][j]) == 3 && length(array[i][j + 1]) == 2) {
                        if (array[i][j] % 10 <= array[i][j + 1] % 10 ) {
                            change(array, i, j, b);
                        }else if( array[i][j] % 10 / 10 < array[i][j + 1] % 10) {
                            change(array, i, j, b);
                        }
                    }
                    if ((length(array[i][j]) == 2) && length(array[i][j + 1]) == 1) {
                        if (array[i][j] % 10 < array[i][j + 1]) {
                            change(array, i, j, b);
                        }
                    }
                    if (length(array[i][j]) == 3 && length(array[i][j + 1]) == 1) {
                        if ((array[i][j] % 100) / 10 <= array[i][j + 1]) {
                            if (array[i][j] % 10 <= array[i][j + 1]) {
                                change(array, i, j, b);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void change(int[][] array, int i, int j, boolean b) {
        int temp = array[i][j];
        array[i][j] = array[i][j + 1];
        array[i][j + 1] = temp;
        b = true;
    }
}