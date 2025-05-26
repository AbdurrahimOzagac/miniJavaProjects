
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("input.txt"))) {
            Map<String, List<String>> anagrams = new HashMap<>();
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    continue;
                }
                String key = sort(line);
                anagrams.computeIfAbsent(key, k -> new ArrayList<>()).add(line);
            }

            scanner.close();

            for (List<String> group : anagrams.values()) {
                System.out.println(group);

            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        System.out.println("Program Terminated");
    }

    public static String sort(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
