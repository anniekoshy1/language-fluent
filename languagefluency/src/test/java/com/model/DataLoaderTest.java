package com.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the DataLoader class.
 */
public class DataLoaderTest {

    private static final String TEST_USERS_FILE = "testData/User.json";
    private static final String TEST_COURSES_FILE = "testData/Courses.json";
    private static final String TEST_LANGUAGES_FILE = "testData/Languages.json";
    private static final String TEST_WORDS_FILE = "testData/words.json";
    private static final String TEST_PHRASES_FILE = "testData/phrases.json";

    @Before
    public void setUp() throws IOException {
        // Create test data directories if they don't exist
        new File("testData").mkdirs();

        // Set the DataLoader file paths to the test files
        DataLoader.USERS_FILE = TEST_USERS_FILE;
        DataLoader.COURSES_FILE = TEST_COURSES_FILE;
        DataLoader.LANGUAGES_FILE = TEST_LANGUAGES_FILE;
        DataLoader.WORDS_FILE = TEST_WORDS_FILE;
        DataLoader.PHRASES_FILE = TEST_PHRASES_FILE;

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

    // Test getUsers()

    @Test
    public void testGetUsers_EmptyFile() {
        ArrayList<User> users = DataLoader.getUsers();
        assertNotNull(users);
        assertEquals(0, users.size());
    }
    // GIT ISSUE - bug on assertEquals(1, users.size());
    @Test
    @SuppressWarnings("unchecked")
    public void testGetUsers_WithData() throws IOException {
        // Arrange
        JSONArray usersArray = new JSONArray();

        JSONObject userJson = new JSONObject();
        userJson.put("userId", UUID.randomUUID().toString());
        userJson.put("username", "testuser");
        userJson.put("email", "testuser@example.com");
        userJson.put("password", "password123");

        usersArray.add(userJson);

        try (FileWriter file = new FileWriter(TEST_USERS_FILE)) {
            file.write(usersArray.toJSONString());
        }

        // Act
        ArrayList<User> users = DataLoader.getUsers();

        // Assert
        assertNotNull(users);
        assertEquals(1, users.size()); //bug java.lang.AssertionError: expected:[1] but was:[0]
        assertEquals("testuser", users.get(0).getUsername());
        assertEquals("testuser@example.com", users.get(0).getEmail());
        assertEquals("password123", users.get(0).getPassword());
    }

    //bug assertNotNull(users);
    @Test
    @SuppressWarnings("unchecked")
    public void testGetUsers_InvalidUUID() throws IOException {
        // Arrange
        JSONArray usersArray = new JSONArray();

        JSONObject userJson = new JSONObject();
        userJson.put("userId", "invalid-uuid");
        userJson.put("username", "testuser");
        userJson.put("email", "testuser@example.com");
        userJson.put("password", "password123");

        usersArray.add(userJson);

        try (FileWriter file = new FileWriter(TEST_USERS_FILE)) {
            file.write(usersArray.toJSONString());
        }

        // Act
        ArrayList<User> users = DataLoader.getUsers();

        // Assert
        assertNotNull(users); //java.lang.AssertionError: expected:[1] but was:[0]
        assertEquals(1, users.size());
        assertNull(users.get(0).getId());
    }

    // Test loadCourses()

    @Test
    public void testLoadCourses_EmptyFile() {
        ArrayList<Course> courses = DataLoader.loadCourses();
        assertNotNull(courses);
        assertEquals(0, courses.size());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testLoadCourses_WithData() throws IOException {
        // Arrange
        JSONArray coursesArray = new JSONArray();

        JSONObject courseJson = new JSONObject();
        courseJson.put("courseID", UUID.randomUUID().toString());
        courseJson.put("name", "Spanish Basics");
        courseJson.put("description", "An introductory course to Spanish");
        courseJson.put("userAccess", true);
        courseJson.put("completed", false);
        courseJson.put("courseProgress", 0.0);
        courseJson.put("lessons", new JSONArray()); // Empty lessons array

        coursesArray.add(courseJson);

        try (FileWriter file = new FileWriter(TEST_COURSES_FILE)) {
            file.write(coursesArray.toJSONString());
        }

        // Act
        ArrayList<Course> courses = DataLoader.loadCourses();

        // Assert
        assertNotNull(courses);
        assertEquals(1, courses.size());
        assertEquals("Spanish Basics", courses.get(0).getName());
        assertEquals("An introductory course to Spanish", courses.get(0).getDescription());
        assertTrue(courses.get(0).getUserAccess());
        assertFalse(courses.get(0).isCompletedCourse());
        assertEquals(0.0, courses.get(0).getCourseProgress(), 0.01);
    }


    @Test
    @SuppressWarnings("unchecked")
    public void testLoadCourses_InvalidUUID() throws IOException {
        // Arrange
        JSONArray coursesArray = new JSONArray();

        JSONObject courseJson = new JSONObject();
        courseJson.put("courseID", "invalid-uuid");
        courseJson.put("name", "Spanish Basics");
        courseJson.put("description", "An introductory course to Spanish");
        courseJson.put("userAccess", true);
        courseJson.put("completed", false);
        courseJson.put("courseProgress", 0.0);
        courseJson.put("lessons", new JSONArray());

        coursesArray.add(courseJson);

        try (FileWriter file = new FileWriter(TEST_COURSES_FILE)) {
            file.write(coursesArray.toJSONString());
        }

        // Act
        ArrayList<Course> courses = DataLoader.loadCourses();

        // Assert
        assertNotNull(courses);
        assertEquals(1, courses.size());
        assertNull(courses.get(0).getId());
    }

    // Test getLanguages()

    @Test
    public void testGetLanguages_EmptyFile() {
        ArrayList<Language> languages = DataLoader.getLanguages();
        assertNotNull(languages);
        assertEquals(0, languages.size());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetLanguages_WithData() throws IOException {
        // Arrange
        JSONArray languagesArray = new JSONArray();

        JSONObject languageJson = new JSONObject();
        languageJson.put("languageID", UUID.randomUUID().toString());
        languageJson.put("name", "Spanish");

        languagesArray.add(languageJson);

        try (FileWriter file = new FileWriter(TEST_LANGUAGES_FILE)) {
            file.write(languagesArray.toJSONString());
        }

        // Act
        ArrayList<Language> languages = DataLoader.getLanguages();

        // Assert
        assertNotNull(languages);
        assertEquals(1, languages.size());
        assertEquals("Spanish", languages.get(0).getName());
    }

    // Test loadWords()

    @Test
    @SuppressWarnings("unchecked")
    public void testLoadWords_EmptyFile() {
        WordsList wordsList = DataLoader.loadWords();
        assertNotNull(wordsList);
        assertEquals(0, wordsList.getAllWords().size());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testLoadWords_WithData() throws IOException {
        // Arrange
        JSONArray wordsArray = new JSONArray();

        JSONObject wordJson = new JSONObject();
        wordJson.put("word", "Hola");
        wordJson.put("definition", "Hello");
        wordJson.put("difficulty", "Rudimentary");
        wordJson.put("translation", "Hello");

        wordsArray.add(wordJson);

        try (FileWriter file = new FileWriter(TEST_WORDS_FILE)) {
            file.write(wordsArray.toJSONString());
        }

        // Act
        WordsList wordsList = DataLoader.loadWords();

        // Assert
        assertNotNull(wordsList);
        assertEquals(1, wordsList.getAllWords().size());
        assertEquals("Hola", wordsList.getAllWords().get(0).getWordText());
        assertEquals("Hello", wordsList.getAllWords().get(0).getDefinition());
    }

    //bug assertEquals("Hello", translation);
    // Test getEnglishTranslation()
    @Test
    @SuppressWarnings("unchecked")
    public void testGetEnglishTranslation_Found() throws IOException {
        // Arrange
        JSONArray wordsArray = new JSONArray();

        JSONObject wordJson = new JSONObject();
        wordJson.put("word", "Hola");
        wordJson.put("definition", "Hello");
        wordJson.put("difficulty", "Rudimentary");
        wordJson.put("translation", "Hello");

        wordsArray.add(wordJson);

        try (FileWriter file = new FileWriter(TEST_WORDS_FILE)) {
            file.write(wordsArray.toJSONString());
        }

        // Act
        String translation = DataLoader.getEnglishTranslation("Hola");

        // Assert
        assertEquals("Hello", translation); //bug java.lang.AssertionError: expected:[Hello] but was:[null]
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetEnglishTranslation_NotFound() throws IOException {
        // Arrange
        JSONArray wordsArray = new JSONArray();

        JSONObject wordJson = new JSONObject();
        wordJson.put("word", "Hola");
        wordJson.put("definition", "Hello");
        wordJson.put("difficulty", "Rudimentary");
        wordJson.put("translation", "Hello");

        wordsArray.add(wordJson);

        try (FileWriter file = new FileWriter(TEST_WORDS_FILE)) {
            file.write(wordsArray.toJSONString());
        }

        // Act
        String translation = DataLoader.getEnglishTranslation("Adios");

        // Assert
        assertNull(translation);
    }

    // Test loadPhrases()

    @Test
    public void testLoadPhrases_EmptyFile() {
        PhraseList phraseList = new DataLoader().loadPhrases();
        assertNotNull(phraseList);
        assertEquals(0, phraseList.getAllPhrases().size());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testLoadPhrases_WithData() throws IOException {
        // Arrange
        JSONArray phrasesArray = new JSONArray();

        JSONObject phraseJson = new JSONObject();
        phraseJson.put("phrase", "Buenos días");
        phraseJson.put("definition", "Good morning");

        phrasesArray.add(phraseJson);

        try (FileWriter file = new FileWriter(TEST_PHRASES_FILE)) {
            file.write(phrasesArray.toJSONString());
        }

        // Act
        PhraseList phraseList = new DataLoader().loadPhrases();

        // Assert
        assertNotNull(phraseList);
        assertEquals(1, phraseList.getAllPhrases().size());
        assertEquals("Buenos días", phraseList.getAllPhrases().get(0).getPhraseText());
        assertEquals("Good morning", phraseList.getAllPhrases().get(0).getDefinition());
    }

    // Optional: Test confirmUser()
    //bug java.lang.AssertionError boolean result = new DataLoader().confirmUser("testuser", "password123");
    @Test
    @SuppressWarnings("unchecked")
    public void testConfirmUser_ValidCredentials() throws IOException {
        // Arrange
        JSONArray usersArray = new JSONArray();
        JSONObject userJson = new JSONObject();
        userJson.put("userId", UUID.randomUUID().toString());
        userJson.put("username", "testuser");
        userJson.put("email", "testuser@example.com");
        userJson.put("password", "password123");

        usersArray.add(userJson);

        try (FileWriter file = new FileWriter(TEST_USERS_FILE)) {
            file.write(usersArray.toJSONString());
        }

        // Act
        boolean result = new DataLoader().confirmUser("testuser", "password123"); //bug java.lang.AssertionError

        // Assert
        assertTrue(result);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testConfirmUser_InvalidCredentials() throws IOException {
        // Arrange
        JSONArray usersArray = new JSONArray();

        JSONObject userJson = new JSONObject();
        userJson.put("userId", UUID.randomUUID().toString());
        userJson.put("username", "testuser");
        userJson.put("email", "testuser@example.com");
        userJson.put("password", "password123");

        usersArray.add(userJson);

        try (FileWriter file = new FileWriter(TEST_USERS_FILE)) {
            file.write(usersArray.toJSONString());
        }

        // Act
        boolean result = new DataLoader().confirmUser("testuser", "wrongpassword");

        // Assert
        assertFalse(result);
    }

    // Test loadFlashcardsFromJson()

    @Test
    public void testLoadFlashcardsFromJson_EmptyFile() {
        List<FlashcardQuestion> flashcards = DataLoader.loadFlashcardsFromJson(TEST_WORDS_FILE);
        assertNotNull(flashcards);
        assertEquals(0, flashcards.size());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testLoadFlashcardsFromJson_WithData() throws IOException {
        // Arrange
        JSONArray wordsArray = new JSONArray();

        JSONObject wordJson = new JSONObject();
        wordJson.put("word", "Hola");
        wordJson.put("translation", "Hello");

        wordsArray.add(wordJson);

        try (FileWriter file = new FileWriter(TEST_WORDS_FILE)) {
            file.write(wordsArray.toJSONString());
        }

        // Act
        List<FlashcardQuestion> flashcards = DataLoader.loadFlashcardsFromJson(TEST_WORDS_FILE);

        // Assert
        assertNotNull(flashcards);
        assertEquals(1, flashcards.size());
        assertEquals("Hola", flashcards.get(0).getFrontInfo());
        assertEquals("Hello", flashcards.get(0).getBackAnswer());
    }
}
