package com.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the DataWriter class.
 */
public class DataWriterTest {

    private static final String TEST_USERS_FILE = "testData/User.json";
    private static final String TEST_COURSES_FILE = "testData/Courses.json";
    private static final String TEST_LANGUAGES_FILE = "testData/Languages.json";
    private static final String TEST_WORDS_FILE = "testData/words.json";
    private static final String TEST_PHRASES_FILE = "testData/phrases.json";

    @Before
    public void setUp() throws IOException {
        // Create test data directories if they don't exist
        new File("testData").mkdirs();

        // Set the DataWriter and DataConstants file paths to the test files
        DataConstants.USERS_FILE = TEST_USERS_FILE;
        DataWriter.USERS_FILE = TEST_USERS_FILE;
        DataWriter.COURSES_FILE = TEST_COURSES_FILE;
        DataWriter.LANGUAGES_FILE = TEST_LANGUAGES_FILE;
        DataWriter.WORDS_FILE = TEST_WORDS_FILE;
        DataWriter.PHRASES_FILE = TEST_PHRASES_FILE;

        // Initialize test data files
        createEmptyJsonArrayFile(TEST_USERS_FILE);
        createEmptyJsonArrayFile(TEST_COURSES_FILE);
        createEmptyJsonArrayFile(TEST_LANGUAGES_FILE);
        createEmptyJsonArrayFile(TEST_WORDS_FILE);
        createEmptyJsonArrayFile(TEST_PHRASES_FILE);
    }

    private void createEmptyJsonArrayFile(String filePath) throws IOException {
        JSONArray emptyArray = new JSONArray();
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(emptyArray.toJSONString());
        }
    }

    // Test saveUsers()

    @Test
    public void testSaveUsers_EmptyList() {
        // Arrange
        ArrayList<User> users = new ArrayList<>();

        // Act
        DataWriter.saveUsers(users);

        // Assert
        JSONArray usersArray = readJsonArrayFromFile(TEST_USERS_FILE);
        assertNotNull(usersArray);
        assertEquals(0, usersArray.size());
    }

    @Test
    public void testSaveUsers_WithData() {
        // Arrange
        ArrayList<User> users = new ArrayList<>();

        User user = new User(
                UUID.randomUUID(),
                "testuser",
                "testuser@example.com",
                "password123",
                new ArrayList<>(), // courses
                new HashMap<>(),   // progress
                new ArrayList<>(), // completedCourses
                UUID.randomUUID(), // currentCourseID
                new ArrayList<>(), // languages
                UUID.randomUUID(), // currentLanguageID
                "Spanish"          // currentLanguageName
        );

        users.add(user);

        // Act
        DataWriter.saveUsers(users);

        // Assert
        JSONArray usersArray = readJsonArrayFromFile(TEST_USERS_FILE);
        assertNotNull(usersArray);
        assertEquals(1, usersArray.size());

        JSONObject userJson = (JSONObject) usersArray.get(0);
        assertEquals(user.getId().toString(), userJson.get("userId"));
        assertEquals(user.getUsername(), userJson.get("username"));
        assertEquals(user.getEmail(), userJson.get("email"));
        assertEquals(user.getPassword(), userJson.get("password"));
    }

    // Test saveCourses()

    @Test
    public void testSaveCourses_EmptyList() {
        // Arrange
        ArrayList<Course> courses = new ArrayList<>();

        // Act
        DataWriter.saveCourses(courses);

        // Assert
        JSONArray coursesArray = readJsonArrayFromFile(TEST_COURSES_FILE);
        assertNotNull(coursesArray);
        assertEquals(0, coursesArray.size());
    }

    @Test
    public void testSaveCourses_WithData() {
        // Arrange
        ArrayList<Course> courses = new ArrayList<>();

        Course course = new Course(
                UUID.randomUUID(),
                "Spanish Basics",
                "An introductory course to Spanish",
                true,
                0.0,
                false,
                new ArrayList<>(), // lessons
                new ArrayList<>(), // assessments
                new ArrayList<>(), // completedAssessments
                null               // flashcard
        );

        courses.add(course);

        // Act
        DataWriter.saveCourses(courses);

        // Assert
        JSONArray coursesArray = readJsonArrayFromFile(TEST_COURSES_FILE);
        assertNotNull(coursesArray);
        assertEquals(1, coursesArray.size());

        JSONObject courseJson = (JSONObject) coursesArray.get(0);
        assertEquals(course.getId().toString(), courseJson.get("courseID"));
        assertEquals(course.getName(), courseJson.get("name"));
        assertEquals(course.getDescription(), courseJson.get("description"));
    }

    // Test saveLanguages()

    @Test
    public void testSaveLanguages_EmptyList() {
        // Arrange
        ArrayList<Language> languages = new ArrayList<>();
        DataWriter dataWriter = new DataWriter();

        // Act
        boolean result = dataWriter.saveLanguages(languages);

        // Assert
        assertTrue(result);
        JSONArray languagesArray = readJsonArrayFromFile(TEST_LANGUAGES_FILE);
        assertNotNull(languagesArray);
        assertEquals(0, languagesArray.size());
    }

    @Test
    public void testSaveLanguages_WithData() {
        // Arrange
        ArrayList<Language> languages = new ArrayList<>();
        DataWriter dataWriter = new DataWriter();

        Language language = new Language(UUID.randomUUID(), "Spanish");
        languages.add(language);

        // Act
        boolean result = dataWriter.saveLanguages(languages);

        // Assert
        assertTrue(result);
        JSONArray languagesArray = readJsonArrayFromFile(TEST_LANGUAGES_FILE);
        assertNotNull(languagesArray);
        assertEquals(1, languagesArray.size());

        JSONObject languageJson = (JSONObject) languagesArray.get(0);
        assertEquals(language.getId().toString(), languageJson.get("languageID"));
        assertEquals(language.getName(), languageJson.get("name"));
    }

    // Test saveWords()

    @Test
    public void testSaveWords_EmptyList() {
        // Arrange
        WordsList wordsList = new WordsList();
        DataWriter dataWriter = new DataWriter();

        // Act
        dataWriter.saveWords(wordsList);

        // Assert
        JSONArray wordsArray = readJsonArrayFromFile(TEST_WORDS_FILE);
        assertNotNull(wordsArray);
        assertEquals(0, wordsArray.size());
    }

    @Test
    public void testSaveWords_WithData() {
        // Arrange
        WordsList wordsList = new WordsList();
        DataWriter dataWriter = new DataWriter();

        Word word = new Word("Hola", "Hello", "Easy", "Hello");
        wordsList.addWord(word);

        // Act
        dataWriter.saveWords(wordsList);

        // Assert
        JSONArray wordsArray = readJsonArrayFromFile(TEST_WORDS_FILE);
        assertNotNull(wordsArray);
        assertEquals(1, wordsArray.size());

        JSONObject wordJson = (JSONObject) wordsArray.get(0);
        assertEquals(word.getWordText(), wordJson.get("word"));
        assertEquals(word.getDefinition(), wordJson.get("definition"));
    }

    // Test savePhrases()

    @Test
    public void testSavePhrases_EmptyList() {
        // Arrange
        PhraseList phraseList = new PhraseList();
        DataWriter dataWriter = new DataWriter();

        // Act
        dataWriter.savePhrases(phraseList);

        // Assert
        JSONArray phrasesArray = readJsonArrayFromFile(TEST_PHRASES_FILE);
        assertNotNull(phrasesArray);
        assertEquals(0, phrasesArray.size());
    }

    @Test
    public void testSavePhrases_WithData() {
        // Arrange
        PhraseList phraseList = new PhraseList();
        DataWriter dataWriter = new DataWriter();

        Phrase phrase = new Phrase("Buenos días", "Good morning");
        phraseList.addPhrase(phrase);

        // Act
        dataWriter.savePhrases(phraseList);

        // Assert
        JSONArray phrasesArray = readJsonArrayFromFile(TEST_PHRASES_FILE);
        assertNotNull(phrasesArray);
        assertEquals(1, phrasesArray.size());

        JSONObject phraseJson = (JSONObject) phrasesArray.get(0);
        assertEquals(phrase.getPhraseText(), phraseJson.get("phrase"));
        assertEquals(phrase.getDefinition(), phraseJson.get("definition"));
    }

    // Helper method to read a JSON array from a file

    private JSONArray readJsonArrayFromFile(String filePath) {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(filePath)) {
            Object obj = parser.parse(reader);
            return (JSONArray) obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
