/**
 * Class responsible for writing data to JSON files, including user data, courses, languages, words, and phrases.
 */
package com.model;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants {

    /**
     * Saves the list of users to the JSON file using the UserList singleton.
     */
    @SuppressWarnings("unchecked")
    public static void saveUsers() {
        UserList users = UserList.getInstance(); // Ensure we work with the current list
        JSONArray jsonUsers = new JSONArray();
    
        for (User user : users.getUsers()) {
            jsonUsers.add(getUserJSON(user)); // Convert each user to JSON
        }
    
        try (FileWriter file = new FileWriter(USERS_FILE)) {
            file.write(jsonUsers.toJSONString()); // Write the full list of users to the file
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    @SuppressWarnings("unchecked")
    private static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();
        userDetails.put(USER_ID, user.getId().toString());
        userDetails.put(USER_USERNAME, user.getUsername());
        userDetails.put(USER_EMAIL, user.getEmail());
        userDetails.put(USER_PASSWORD, user.getPassword());
        if (user.getCurrentCourse() != null) {
            userDetails.put("currentCourseID", user.getCurrentCourse().toString());
        }
        if (user.getCurrentLanguage() != null) {
            userDetails.put("currentLanguageID", user.getCurrentLanguage().toString());
        }
        if (user.getCurrentLanguageName() != null && !user.getCurrentLanguageName().isEmpty()) {
            userDetails.put("currentLanguageName", user.getCurrentLanguageName());
        }
        return userDetails;
    }

    /**
     * Saves the list of courses to the JSON file using the CourseList singleton.
     */
    @SuppressWarnings("unchecked")
    public static void saveCourses() {
        CourseList courseList = CourseList.getInstance();
        JSONArray jsonCourses = new JSONArray();
    
        for (Course course : courseList.getCourses()) {
            JSONObject courseJSON = new JSONObject();
            courseJSON.put("courseID", course.getId().toString());
            courseJSON.put("name", course.getName());
            courseJSON.put("description", course.getDescription());
            courseJSON.put("userAccess", course.getUserAccess());
            courseJSON.put("courseProgress", course.getCourseProgress());
            courseJSON.put("completed", course.isCompletedCourse());
    
            JSONArray lessonsArray = new JSONArray();
            for (Lesson lesson : course.getAllLessons()) {
                JSONObject lessonJSON = new JSONObject();
                lessonJSON.put("lessonName", lesson.getLessonName());
                lessonJSON.put("lessonID", lesson.getId().toString());
                lessonJSON.put("lessonProgress", lesson.getLessonProgress());
                lessonJSON.put("description", lesson.getDescription());
                lessonsArray.add(lessonJSON);
            }
            courseJSON.put("lessons", lessonsArray);
    
            JSONArray flashcardsArray = new JSONArray();
            for (FlashcardQuestion flashcard : course.getFlashcards()) {
                JSONObject flashcardJSON = new JSONObject();
                flashcardJSON.put("front", flashcard.getFrontInfo());
                flashcardJSON.put("back", flashcard.getBackAnswer());
                flashcardsArray.add(flashcardJSON);
            }
            courseJSON.put("flashcards", flashcardsArray);
    
            jsonCourses.add(courseJSON);
        }
    
        try (FileWriter file = new FileWriter(COURSES_FILE)) {
            file.write(jsonCourses.toJSONString());
            file.flush();
            System.out.println("Courses saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving courses: " + e.getMessage());
        }
    }
    
    

    @SuppressWarnings("unchecked")
    private static JSONObject getCourseJSON(Course course) {
        JSONObject courseDetails = new JSONObject();
        courseDetails.put(COURSE_ID, course.getId().toString());
        courseDetails.put(COURSE_NAME, course.getName());
        courseDetails.put(COURSE_DESCRIPTION, course.getDescription());
        courseDetails.put(COURSE_PROGRESS, course.getCourseProgress());
        return courseDetails;
    }

    /**
     * Saves the list of languages to the JSON file using the LanguageList singleton.
     */
    @SuppressWarnings("unchecked")
    public static void saveLanguages() {
        LanguageList languages = LanguageList.getInstance();
        JSONArray jsonLanguages = new JSONArray();

        for (Language language : languages.getLanguages()) {
            jsonLanguages.add(getLanguageJSON(language));
        }

        try (FileWriter file = new FileWriter(LANGUAGES_FILE)) {
            file.write(jsonLanguages.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private static JSONObject getLanguageJSON(Language language) {
        JSONObject languageDetails = new JSONObject();
        languageDetails.put(LANGUAGE_ID, language.getId().toString());
        languageDetails.put(LANGUAGE_NAME, language.getName());
        languageDetails.put(LANGUAGE_PROGRESS, language.getLanguageProgress());
        return languageDetails;
    }

    /**
     * Saves the list of words to a JSON file using the WordsList singleton.
     */
    @SuppressWarnings("unchecked")
    public static void saveWords() {
        WordsList wordsList = WordsList.getInstance(); // Get the singleton instance
        JSONArray jsonWords = new JSONArray();

        for (Word word : wordsList.getAllWords()) {
            jsonWords.add(getWordJSON(word)); // Convert each word to JSON
        }

        try (FileWriter file = new FileWriter(WORDS_FILE)) {
            file.write(jsonWords.toJSONString()); // Write the full list of words to the file
            file.flush();
            System.out.println("Words saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private static JSONObject getWordJSON(Word word) {
        JSONObject wordDetails = new JSONObject();
        wordDetails.put("id", word.getId().toString());
        wordDetails.put("word", word.getWordText());
        wordDetails.put("difficulty", word.getDifficulty());
        wordDetails.put("translation", word.getTranslation());
        return wordDetails;
    }

    /**
     * Saves the list of phrases to a JSON file using the PhraseList singleton.
     */
    public static void savePhrases() {
        PhraseList phraseList = PhraseList.getInstance();
        JSONArray jsonPhrases = new JSONArray();

        for (Phrase phrase : phraseList.getAllPhrases()) {
            jsonPhrases.add(getPhraseJSON(phrase));
        }

        try (FileWriter file = new FileWriter(PHRASES_FILE)) {
            file.write(jsonPhrases.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private static JSONObject getPhraseJSON(Phrase phrase) {
        JSONObject phraseDetails = new JSONObject();
        phraseDetails.put(PHRASE_TEXT, phrase.getPhraseText());
        phraseDetails.put(PHRASE_TRANSLATION, phrase.getPhraseText());
        return phraseDetails;
    }
}