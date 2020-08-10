//33729294
import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(reader.readLine());
        System.out.println(catalanNumbers(new BigInteger(n + "")));
    }

    public static BigInteger catalanNumbers(BigInteger n) {
        return fac(multiplyTwo(n)).divide(fac(addOne(n)).multiply(fac(n)));
    }

    public static BigInteger fac(BigInteger n) {
        if (n.equals(new BigInteger(1 + ""))) {
            return new BigInteger(String.valueOf(1));
        }
        return n.multiply(fac(n.subtract(new BigInteger(String.valueOf(1)))));
    }

    public static BigInteger multiplyTwo(BigInteger n) {
        return n.multiply(new BigInteger(2 + ""));
    }

    public static BigInteger addOne(BigInteger n) {
        return n.add(new BigInteger(1 + ""));
    }
}