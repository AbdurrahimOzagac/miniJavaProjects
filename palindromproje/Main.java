
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("input.txt"))) {

            Map<String, Boolean> map = new HashMap<>();

            while (scanner.hasNextLine()) {

                boolean isPalindrome = true;
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    continue;
                }
                char[] chars = line.toCharArray();
                int n = chars.length;

                //FIRST METHOD: USING TWO POINTERS
                for (int i = 0; i < n / 2; i++) {
                    int j = n - i - 1;
                    if (chars[i] != chars[j]) {
                        isPalindrome = false;
                        break;
                    }
                }

                //SECOND METHOD: USING STACK
                Stack<Character> stack = new Stack<>();

                for (int i = 0; i < n / 2; i++) {
                    stack.add(chars[i]);
                }

                int start = (n % 2 == 0) ? n / 2 : n / 2 + 1;

                for (int j = start; j<n; j++) {
                    if (stack.pop() != chars[j]) {
                        isPalindrome = false;
                        break;
                    }
                }

                map.put(line, isPalindrome);
            }

            for(Map.Entry<String, Boolean> entry : map.entrySet()){
                System.out.println(entry.getKey() + " → " + entry.getValue());
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        System.out.println("Program Terminated");
    }
}
