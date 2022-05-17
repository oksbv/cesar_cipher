package com.application.utils;
import com.application.encrypt_decrypt.EncryptText;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class KeyEncoderAndDecoder {
    public static String encodeKey() {
        int keyToEncode = EncryptText.getKey();
        Base64.Encoder encoder = Base64.getEncoder();
        String encodedKey = encoder.encodeToString(String.valueOf(keyToEncode).getBytes(StandardCharsets.UTF_8));
        return encodedKey;
    }

    public static int decodeKey(String encodedKey) {
        int decryptionKey;
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            byte[] bytes = decoder.decode(encodedKey);
            String decodedKey = new String(bytes);
            decryptionKey = Integer.parseInt(decodedKey);
        } catch (Exception e) {
            return -1;
        }
        return decryptionKey;
    }
}
