package com.application.encrypt_decrypt;
import com.application.utils.DecryptionNotCompletedException;
import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DecryptText {
    private static final List<Character> alphabetList = List.of('а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к',
            'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э',
            'ю', 'я', '.', '”', ':', '"', '-', '!', '?', ' ', ',');
    private static final Map<Character, Character> alphabetMap = new HashMap<>();

    public static StringBuilder bruteforceDecrypt(Path path) throws DecryptionNotCompletedException, IOException {
        int decryptionKey = 0;
        while (true) {
            StringBuilder sb = decryptionCheck(decrypt(path, decryptionKey));
            if (sb != null) {
                return sb;
            }
            decryptionKey++;
            if (decryptionKey > alphabetList.size() + 1) {
                throw new DecryptionNotCompletedException("Decryption cannot be completed without key");
            }
        }
    }
    public static StringBuilder decrypt(Path path, int decryptKey) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < alphabetList.size(); i++) {
            int rest = decryptKey % alphabetList.size();
            if (i - rest < 0) {
                alphabetMap.put(alphabetList.get(i), alphabetList.get(alphabetList.size() + (i - rest)));
            } else {
                alphabetMap.put(alphabetList.get(i), alphabetList.get((i - rest)));
            }
        }
        try (BufferedReader bufferedReader = new BufferedReader
                (new InputStreamReader(new FileInputStream(path.toFile())))) {
            char key;
            while (bufferedReader.ready()) {
                if (alphabetMap.containsKey((key = (char) bufferedReader.read()))) {
                    char value = alphabetMap.get(key);
                    sb.append(value);
                } else {
                    sb.append(key);
                }
            }
        } catch (IOException e) {
            throw new IOException("File does not exist");
        }
        return sb;
    }
    private static StringBuilder decryptionCheck(StringBuilder sb) {
        String text = sb.toString();
        List<String> words = Arrays.stream(text.split("\\s+")).toList();
        Pattern pattern = Pattern.compile("[\\p{P}\\p{S}\\s\\d{a-z A-z}]");
        Matcher matcherWordEnd;
        Matcher matcherWordStart;
        Matcher matcherWord;
        for (String word : words) {
            matcherWordEnd = pattern.matcher(String.valueOf(word.charAt(word.length() - 1)));
            matcherWordStart = pattern.matcher(String.valueOf(word.charAt(0)));
            matcherWord = pattern.matcher(word);
            if (word.length() > 30) {
                return null;
            } else if (!matcherWordEnd.find() && !matcherWordStart.find()) {
                if (matcherWord.find() && !word.contains("-")) {
                    return null;
                }
            }
        }
        return sb;
    }
}
