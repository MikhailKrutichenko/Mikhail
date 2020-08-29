//id 33843753
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static final int b = 62;

    public static Map<Character, Integer> mapBase;
    public static Map<Long, String> dataBase;
    public static String base;
    public static int count = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        base = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        mapBase = new HashMap<>();
        dataBase = new HashMap<>();
        for (int i = 0; i < base.length(); i++) {
            mapBase.put(base.charAt(i), i);
        }
        int amount = Integer.parseInt(reader.readLine());
        for(int i = 0; i < amount; i++) {
            command(reader.readLine());
        }
    }

    public static void command(String input) {
        if (input.charAt(0) == 'p') {
            String shortURL = getShortURL(encode(count), getURL(input));
            dataBase.put(decode(shortURL), getContent(input));
            System.out.println(shortURL);
            count++;
        } else {
            String url = getURL(input);
            long result = decode(url);
            if (dataBase.containsKey(result)) {
                String res = dataBase.get(result);
                System.out.println(res);
            } else {
                System.out.println("error");
            }
        }
    }

    public static String getShortURL(String encodeString, String input) {
        int startSeq = 0;
        int endSeq = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '/') {
                startSeq = i + 2;
                i++;
            }
            if (input.charAt(i) == '.') {
                endSeq = i;
                break;
            }
        }
        return input.substring(0, startSeq) + encodeString + input.substring(endSeq);
    }

    public static String getURL(String input) {
        String[] array = input.split(" ");
        return array[1];

    }

    public static String getContent(String input) {
        String[] array = input.split(" ");
        String content = "";
        for (int i = 2; i < array.length; i++) {
            content = content + array[i] + " ";
        }
        return content;
    }

    public static long decode(String input) {
        long decodeValue = 0;
        for (int i = 0; i < input.length(); i++) {
            if(!mapBase.containsKey(input.charAt(i))) {
                continue;
            }
            decodeValue = b * decodeValue + mapBase.get(input.charAt(i));
        }
        return decodeValue;
    }

    public static String encode(long num) {
        String encodedString = "";
        while (num >= 0) {
            if (num < b) {
                encodedString = base.charAt((int) num) + encodedString;
                break;
            }
            long index = num % b;
            encodedString = base.charAt((int) index) + encodedString;
            num /= b;
        }
        return encodedString;
    }
}