package com.model;

import java.util.ArrayList;

public class LanguageList {

    private final ArrayList<Language> languages;
    private static LanguageList languageList;

    private LanguageList() {
        languages = new ArrayList<>();
    }

    public static LanguageList getInstance() {
        if (languageList == null) {
            languageList = new LanguageList();
            languageList.loadLanguages();
        }
        return languageList;
    }

    public void loadLanguages(){
        DataLoader.loadLanguages();
    }

    public void addLanguage(Language language) {
        languages.add(language);
        saveLanguages();
    }

    public ArrayList<Language> getLanguages() {
        return languages;
    }

    public void saveLanguages() {
        DataWriter.saveLanguages();
    }
}
