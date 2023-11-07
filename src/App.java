import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a pool of letters to find words:");
        String letters = scanner.nextLine().toUpperCase();

        scanner.close();

        try {
            Map<Character, Integer> lettersCountMap = getLetterCountMap(letters);
            try (BufferedReader reader = new BufferedReader(new FileReader("D:\\javamaterial\\wordfinder\\wordfinder\\lib\\dictionary.txt"))) {
                String currentWord;
                while ((currentWord = reader.readLine()) != null) {
                    currentWord = currentWord.toUpperCase();
                    if (canBeFormed(currentWord, lettersCountMap)) {
                        System.out.println(currentWord);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean canBeFormed(String word, Map<Character, Integer> lettersCountMap) {
        Map<Character, Integer> wordCountMap = getLetterCountMap(word);
        for (Map.Entry<Character, Integer> entry : wordCountMap.entrySet()) {
            char letter = entry.getKey();
            int requiredCount = entry.getValue();
            if (!lettersCountMap.containsKey(letter) || lettersCountMap.get(letter) < requiredCount) {
                return false;
            }
        }
        return true;
    }

    public static Map<Character, Integer> getLetterCountMap(String letters) {
        Map<Character, Integer> lettersCountMap = new HashMap<>();
        for (char currentLetter : letters.toCharArray()) {
            lettersCountMap.put(currentLetter, lettersCountMap.getOrDefault(currentLetter, 0) + 1);
        }
        return lettersCountMap;
    }
}