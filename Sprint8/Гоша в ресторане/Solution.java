//id 38833843

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Solution {
    public static final int INF = 1000000;
    public static int dp[][];
    public static int price[];

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(reader.readLine());
        price = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            price[i] = Integer.parseInt(reader.readLine());
        }
        dp = new int[n + 1][n + 2];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n + 1; j++)
                dp[i][j] = -1;
        }
        int resultSum = INF;
        int remainCoupon = 0;
        for (int i = 0; i <= n; i++) {
            int temp = sum(n, i);
            if (resultSum >= temp) {
                resultSum = temp;
                remainCoupon = i;
            }
        }
        LinkedList<Integer> useCoupon = new LinkedList<>();
        addUseCoupon(useCoupon, n, remainCoupon);
        System.out.println(resultSum + " " + useCoupon.size());
        while (!useCoupon.isEmpty()) {
            System.out.print(useCoupon.pollLast() + " ");
        }
    }

    public static int sum(int i, int j) {
        if (j > i) {
            return INF;
        } else {
            int result = 0;
            int cost = price[i];
            if (j <= 0) {
                if (i >= 1) {
                    if (cost <= 500) {
                        result = Math.min(sum(i - 1, j + 1), sum(i - 1, j) + cost);
                    } else {
                        return sum(i - 1, j + 1);
                    }
                } else {
                    return 0;
                }
            } else {
                if (dp[i][j] != -1) {
                    return dp[i][j];
                }
                int temp = 0;
                if (cost > 500) {
                    temp = Math.min(sum(i - 1, j + 1), sum(i - 1, j - 1) + cost);
                } else {
                    temp = Math.min(sum(i - 1, j + 1), sum(i - 1, j) + cost);
                }
                result = temp;
            }
            dp[i][j] = result;
            return result;
        }
    }


    public static void addUseCoupon(LinkedList<Integer> used, int i, int j) {
        if (j < i) {
            int cost = price[i];
            if (j <= 0) {
                if (i >= 1) {
                    if (cost > 500) {
                        used.add(i);
                        addUseCoupon(used, i - 1, j + 1);
                    } else {
                        if (sum(i, j) == sum(i - 1, j + 1)) {
                            used.add(i);
                            addUseCoupon(used, i - 1, j + 1);
                        } else {
                            addUseCoupon(used, i - 1, j);
                        }
                    }
                }
            } else {
                if (cost <= 500) {
                    if (sum(i - 1, j + 1) == sum(i, j)) {
                        used.add(i);
                        addUseCoupon(used, i - 1, j + 1);
                    } else {
                        addUseCoupon(used, i - 1, j);
                    }
                } else {
                    if (sum(i - 1, j + 1) == sum(i, j)) {
                        used.add(i);
                        addUseCoupon(used, i - 1, j + 1);
                    } else {
                        addUseCoupon(used, i - 1, j - 1);
                    }
                }
            }
        }
    }
}