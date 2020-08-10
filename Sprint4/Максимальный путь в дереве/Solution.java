//33733096
public class Solution {
    public static int currentResult = Integer.MIN_VALUE;
   
    public static String treeSolution(Node... heads) {
        bestWay(heads[0]);
        return currentResult + "";
    }

    public static int bestWay(Node node) {
        checkCurrentResult(node.value);
        if (node.right == null && node.left == null) {
            return checkNegativeValue(node.value);
        }
        if (node.right != null && node.left != null) {
            int temp = bestWay(node.left) + bestWay(node.right) + node.value;
            checkCurrentResult(temp);
            if (bestWay(node.left) > bestWay(node.right)) {
                int temp1 = bestWay(node.left) + node.value;
                checkCurrentResult(temp1);
                return checkNegativeValue(temp1);
            } else {
                int temp1 = bestWay(node.right) + node.value;
                checkCurrentResult(temp1);
                return checkNegativeValue(temp1);
            }
        } else if (node.right != null) {
            int temp = bestWay(node.right) + node.value;
            checkCurrentResult(temp);
            return checkNegativeValue(temp);
        } else {
            int temp = bestWay(node.left) + node.value;
            checkCurrentResult(temp);
            return checkNegativeValue(temp);
        }
    }

    public static void checkCurrentResult(int n) {
        if (n > currentResult) {
            currentResult = n;
        }
    }

    public static int checkNegativeValue(int n) {
        if (n <= 0) {
            return 0;
        } else {
            return n;
        }
    }
}