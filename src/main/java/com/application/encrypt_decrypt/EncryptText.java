package com.application.encrypt_decrypt;
import java.io.*;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EncryptText {
    private static final int key = new SecureRandom().nextInt(4, 40);
    private static final List<Character> alphabetList = List.of('а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к',
            'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э',
            'ю', 'я', '.', '”', ':', '"', '-', '!', '?', ' ', ',');
    private static final Map<Character, Character> alphabetMap = new HashMap<>();

    public static String encrypt(Path path) throws IOException {
        for (int i = 0; i < alphabetList.size(); i++) {
            alphabetMap.put(alphabetList.get(i), alphabetList.get((i + key) % alphabetList.size()));
        }
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
                (new FileInputStream(path.toFile())))) {
            char mapKey;
            while (bufferedReader.ready()) {
                if (alphabetMap.containsKey((mapKey = (char) bufferedReader.read()))) {
                    char value = alphabetMap.get(mapKey);
                    sb.append(value);
                } else {
                    sb.append(mapKey);
                }
            }
        }
        return sb.toString();
    }

    public static int getKey() {
        return key;
    }
}


