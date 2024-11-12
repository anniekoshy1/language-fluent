/**
 * This class defines constants used across the language learning system, including file paths, user configurations, course details, and language specifications.
 */
package com.model;

import java.util.Arrays;
import java.util.List;

public class DataConstants {

    // File paths for storing data
    public static final String USERS_FILE = "speek/docs/JSON/User.json";
    public static final String COURSES_FILE = "speek/docs/JSON/Courses.json";
    public static final String LANGUAGES_FILE = "speek/docs/JSON/Languages.json";
    public static final String WORDS_FILE = "speek/docs/JSON/words.json";
    public static final String PHRASES_FILE = "speek/docs/JSON/phrases.json";
    
    // Constants for user information
    public static final int MIN_PASSWORD_LENGTH = 5;
    public static final int MAX_PASSWORD_LENGTH = 10;
    public static final int MAX_LOGIN_ATTEMPTS = 5;
    public static final String USER_USERNAME = "username";
    public static final String USER_PASSWORD = "password";
    public static final String USER_EMAIL = "email";
    public static final String USER_ID = "userId";
    public static final String USER_PROGRESS = "progress";
    public static final String USER_CURRENT_COURSE_ID = "currentCourseID";
    public static final String USER_CURRENT_LANGUAGE_ID = "currentLanguageID";
    public static final String USER_CURRENT_LANGUAGE_NAME = "currentLanguageName";


    // Constants for courses
    public static final String COURSE_NAME = "name";
    public static final String COURSE_DESCRIPTION = "description";
    public static final String COURSE_ID = "courseID";
    public static final String COURSE_PROGRESS = "progress";

    // Constants for lessons
    public static final String LESSON_DESCRIPTION = "lessonDescription";
    public static final String LESSON_ID = "lessonID";
    public static final int MAX_WORDS_PER_LESSON = 50;
    public static final int MAX_HISTORICAL_FACTS_PER_LESSON = 10;

    // Constants for assessments
    public static final String ASSESSMENT_ID = "assessmentID";
    public static final int ASSESSMENT_SCORE = 60;
    public static final int MAX_QUESTIONS_PER_QUIZ = 10;

    // Constants for languages
    public static final String LANGUAGE_NAME = "languageName";
    public static final String DEFAULT_LANGUAGE = "English";
    public static final String LANGUAGE_ID = "languageId";
    public static final String LANGUAGE_PROGRESS = "progress";

    public static final List<String> SUPPORTED_LANGUAGES = Arrays.asList("English", "Spanish", "French", "German", "Mandarin");

    // Constants for phrases
    public static final String PHRASE_ID = "id";
    public static final String PHRASE_TEXT = "word";
    public static final String PHRASE_TRANSLATION = "translation";
    public static final String PHRASE_DIFFICULTY = "difficulty";

    // Constants for words
    public static final String WORD_ID = "id";
    public static final String WORD_TEXT = "word";
    public static final String WORD_TRANSLATION = "translation";
    public static final String WORD_DIFFICULTY = "difficulty";

    // Constants for difficulty levels
    public static final List<String> DIFFICULTY_LEVELS = Arrays.asList("Rudimentary", "Intermediate", "Advanced");
    public static final String DEFAULT_DIFFICULTY_LEVEL = "Rudimentary";

    // Constants for phrase and word limits
    public static final int MAX_WORDS_PER_PHRASE = 5;
    public static final int MIN_SCORE_PASS = 60;
}