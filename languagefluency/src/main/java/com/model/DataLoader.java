package com.model;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class DataLoader extends DataConstants {


    public static String USERS_FILE = "speek/docs/JSON/User.json";
    public static String COURSES_FILE = "speek/docs/JSON/Courses.json";
    public static String LANGUAGES_FILE = "speek/docs/JSON/Languages.json";
    public static String WORDS_FILE = "speek/docs/JSON/words.json";
    public static String PHRASES_FILE = "speek/docs/JSON/phrases.json";
    
    private static JSONObject wordsData;

    public DataLoader() {
        loadWordsData();
    }

    /**
     * Loads users from the JSON file and adds them to the UserList singleton.
     */
    public static void getUsers() {
        System.out.println("Datatloader.getUsers() called");
        UserList userListInstance = UserList.getInstance();
    
        try (FileReader reader = new FileReader(USERS_FILE)) {
            JSONParser jsonParser = new JSONParser();
            JSONArray userList = (JSONArray) jsonParser.parse(reader);
    
            for (Object obj : userList) {
                JSONObject userJSON = (JSONObject) obj;
    
                UUID id = parseUUID((String) userJSON.get("userId"), "userId");
                String username = (String) userJSON.get("username");
                String email = (String) userJSON.get("email");
                String password = (String) userJSON.get("password");
    
                // Load user-related data
                HashMap<UUID, Double> progress = parseProgress((JSONObject) userJSON.get("progress"));
                ArrayList<UUID> completedCourses = parseCompletedCourses((JSONArray) userJSON.get("completedCourses"));
                UUID currentCourseID = userJSON.get("currentCourseID") != null
                    ? UUID.fromString((String) userJSON.get("currentCourseID"))
                    : null;
                UUID currentLanguageID = userJSON.get("currentLanguageID") != null
                    ? UUID.fromString((String) userJSON.get("currentLanguageID"))
                    : null;
                String currentLanguageName = userJSON.get("currentLanguageName") != null
                    ? (String) userJSON.get("currentLanguageName")
                    : "";
    
                // Create and add user to UserList singleton
                User user = new User(id, username, email, password, CourseList.getInstance().getCourses(), progress,
                        completedCourses, currentCourseID, LanguageList.getInstance().getLanguages(), currentLanguageID,
                        currentLanguageName);
                userListInstance.addUserWithoutSaving(user); // Use a method that doesn't trigger a save
            }
            System.out.println("Total users loaded: " + userListInstance.getUsers().size());
    
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error in DataLoader.getUsers(): " + e.getMessage());
        }
    }
    

    /**
     * Loads courses from the JSON file and adds them to the CourseList singleton.
     * @return 
     */
    public static void loadCourses() {
        CourseList courseListInstance = CourseList.getInstance();

        try (FileReader reader = new FileReader(COURSES_FILE)) {
            JSONParser jsonParser = new JSONParser();
            JSONArray courseList = (JSONArray) jsonParser.parse(reader);

            for (Object obj : courseList) {
                JSONObject courseJSON = (JSONObject) obj;
                UUID id = UUID.fromString((String) courseJSON.get("courseID"));
                String name = (String) courseJSON.get("name");
                String description = (String) courseJSON.get("description");
                boolean userAccess = courseJSON.get("userAccess") != null ? (Boolean) courseJSON.get("userAccess") : false;
                boolean completed = (Boolean) courseJSON.get("completed");
                double courseProgress = ((Number) courseJSON.get("courseProgress")).doubleValue();

                // Load lessons and assessments for this course
                ArrayList<Lesson> lessons = parseLessons((JSONArray) courseJSON.get("lessons"));
                ArrayList<Assessment> assessments = new ArrayList<>();
                ArrayList<String> completedAssessments = new ArrayList<>();
                FlashcardQuestion flashcard = new FlashcardQuestion("Default Question", "Default Answer");

                Course course = new Course(id, name, description, userAccess, courseProgress, completed, lessons,
                        assessments, completedAssessments, flashcard);
                courseListInstance.addCourse(course);
                System.out.println("Loaded course: " + course.getName());
            }

            System.out.println("Total courses loaded: " + courseListInstance.getCourses().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads languages from the JSON file and adds them to the LanguageList singleton.
     */
    public static void loadLanguages() {
        LanguageList languageListInstance = LanguageList.getInstance();

        try (FileReader reader = new FileReader(LANGUAGES_FILE)) {
            JSONParser jsonParser = new JSONParser();
            JSONArray languageArray = (JSONArray) jsonParser.parse(reader);

            for (Object languageObject : languageArray) {
                JSONObject languageJson = (JSONObject) languageObject;
                UUID languageID = UUID.fromString((String) languageJson.get("languageID"));
                String name = (String) languageJson.get("name");

                Language language = new Language(languageID, name);
                languageListInstance.addLanguage(language);
            }
            System.out.println("Languages loaded successfully: " + languageListInstance.getLanguages().size());

        } catch (IOException | ParseException e) {
            System.err.println("Error loading languages: " + e.getMessage());
        }
    }

    /**
     * Loads words from the JSON file into a WordsList singleton.
     * @return 
     */
    public static void loadWords() {
        WordsList wordsList = WordsList.getInstance();
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(WORDS_FILE)) {
            JSONArray wordsArray = (JSONArray) parser.parse(reader);
            for (Object obj : wordsArray) {
                JSONObject wordObject = (JSONObject) obj;
                String wordText = (String) wordObject.get("word");
                String definition = (String) wordObject.get("definition");
                String difficulty = (String) wordObject.get("difficulty");
                String translation = (String) wordObject.get("translation");

                Word word = new Word(wordText, definition, difficulty, translation);
                wordsList.addWord(word);
            }
            System.out.println("Words loaded successfully.");

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper method to parse progress from a JSON object.
     */
    private static HashMap<UUID, Double> parseProgress(JSONObject progressJSON) {
        HashMap<UUID, Double> progress = new HashMap<>();
        if (progressJSON != null) {
            for (Object key : progressJSON.keySet()) {
                UUID courseId = parseUUID((String) key, "progress");
                double progressValue = ((Number) progressJSON.get(key)).doubleValue();
                progress.put(courseId, progressValue);
            }
        }
        return progress;
    }

    /**
     * Helper method to parse completed courses from a JSON array.
     */
    private static ArrayList<UUID> parseCompletedCourses(JSONArray completedCoursesJSON) {
        ArrayList<UUID> completedCourses = new ArrayList<>();
        if (completedCoursesJSON != null) {
            for (Object courseId : completedCoursesJSON) {
                completedCourses.add(parseUUID((String) courseId, "completedCourses"));
            }
        }
        return completedCourses;
    }

    /**
     * Helper method to parse lessons from a JSON array.
     */
    private static ArrayList<Lesson> parseLessons(JSONArray lessonsArray) {
        ArrayList<Lesson> lessons = new ArrayList<>();
        if (lessonsArray != null) {
            for (Object lessonObj : lessonsArray) {
                JSONObject lessonJSON = (JSONObject) lessonObj;
                String lessonName = (String) lessonJSON.get("lessonName");
                UUID lessonId = UUID.fromString((String) lessonJSON.get("lessonID"));
                String lessonDescription = (String) lessonJSON.get("description");
                double lessonProgress = ((Number) lessonJSON.get("lessonProgress")).doubleValue();
                String englishContent = (String) lessonJSON.get("englishContent");
                String spanishContent = (String) lessonJSON.get("spanishContent");

                Lesson lesson = new Lesson(lessonName, lessonId, lessonDescription, lessonProgress, englishContent, spanishContent);
                lessons.add(lesson);
            }
        }
        return lessons;
    }

    /**
     * Helper method to parse a UUID from a string.
     */
    private static UUID parseUUID(String uuidString, String fieldName) {
        if (uuidString == null || uuidString.trim().isEmpty()) {
            System.err.println("Warning: Missing or empty UUID for field: " + fieldName);
            return null;
        }
        try {
            return UUID.fromString(uuidString);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid UUID format for field: " + fieldName + ", value: " + uuidString);
            return null;
        }
    }

    /**
     * Pre-loads words data from the JSON file for on-demand word translation.
     */
    private static void loadWordsData() {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(WORDS_FILE)) {
            JSONArray wordsArray = (JSONArray) parser.parse(reader);
            wordsData = new JSONObject();
            wordsData.put("words", wordsArray);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }



    /**
     * Loads phrases from the JSON file into a PhraseList object
     * @return a PhraseList object containing all loaded phrases
     */
    public static void loadPhrases() {
        PhraseList phraseList = PhraseList.getInstance();
        JSONParser parser = new JSONParser();
    
        try (FileReader reader = new FileReader(PHRASES_FILE)) {
            JSONArray phrasesArray = (JSONArray) parser.parse(reader);
            for (Object obj : phrasesArray) {
                JSONObject phraseObj = (JSONObject) obj;
                String phraseText = (String) phraseObj.get("phrase");
                String definition = (String) phraseObj.get("definition");
    
                Phrase phrase = new Phrase(phraseText, definition);
                phraseList.addPhrase(phrase);
            }
            System.out.println("Phrases loaded successfully.");
    
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static String getEnglishTranslation(String spanishWord) {
        if (wordsData == null){
            loadWordsData();
        }
        JSONArray wordsArray = (JSONArray) wordsData.get("words");
        for (Object wordObj : wordsArray) {
            JSONObject wordJson = (JSONObject) wordObj;
            if (wordJson.get("word").equals(spanishWord)) {
                return (String) wordJson.get("translation");
            }
        }
        return null; // Return null if not found
    }


    /**
     * Saves the list of courses to storage.
     * @param courses the list of courses to be saved
     */
    public void saveCourses(ArrayList<Course> courses) {}

    /**
     * Saves the assessment history for a user.
     * @param user the User for whom the history is saved
     * @param assessment the Assessment being saved
     */
    public void saveAssessmentHistory(User user, Assessment assessment) {}

    /**
     * Loads an assessment by its ID.
     * @param assessmentIDSTR the ID of the assessment as a String
     * @return the loaded Assessment object
     * @throws UnsupportedOperationException if the method is not implemented
     */
    public static Assessment loadAssessmentById(String assessmentIDSTR) {
        throw new UnsupportedOperationException("Unimplemented method 'loadAssessmentById'");
    }
    public static List<FlashcardQuestion> loadFlashcardsFromJson(String filePath) {
        List<FlashcardQuestion> flashcards = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(filePath)) {
            JSONArray wordsArray = (JSONArray) parser.parse(reader);
            for (Object obj : wordsArray) {
                JSONObject wordObject = (JSONObject) obj;
                String frontInfo = (String) wordObject.get("word");  // Correct key
                String backAnswer = (String) wordObject.get("translation");  // Correct key
                flashcards.add(new FlashcardQuestion(frontInfo, backAnswer));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return flashcards;
    }

}