package com.application.utils;

public enum Constant {
    FILE_NOT_FOUND("File does not exist"),
    FILE_OR_KEY_NOT_FOUND("File or key does not exists"),
    NOTHING_TO_EXPORT("There is no text to export"),
    FILE_EXTENSION_ALL("All files"),
    FILE_EXTENSION_TXT("*.txt"),
    DECRYPTION_NOT_COMPLETED("Decryption cannot be completed without key");

    private String text;

    Constant(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
