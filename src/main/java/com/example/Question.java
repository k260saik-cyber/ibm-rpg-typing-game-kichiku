package com.example.typing;

public class Question {
    private String command;
    private String englishMenu;
    private String japaneseMenu;

    public Question(String command, String englishMenu, String japaneseMenu) {
        this.command = command;
        this.englishMenu = englishMenu;
        this.japaneseMenu = japaneseMenu;
    }

    // Getters
    public String getCommand() {
        return command;
    }

    public String getEnglishMenu() {
        return englishMenu;
    }

    public String getJapaneseMenu() {
        return japaneseMenu;
    }
}