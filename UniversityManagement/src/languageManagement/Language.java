package languageManagement;

import java.io.Serializable;

import exceptions.InvalidLanguageException;


/**
 * Enumeration representing supported languages in the application.
 */
public enum Language implements Serializable{
    DEFAULT("English"), EN("English"), RU("Russian"), KZ("Kazakh");

    private String languageName;
    private static Language instance = null;

    
    /**
     * Constructor for Language enum.
     *
     * @param languageName The name of the language.
     */
    Language(String languageName) {
        this.languageName = languageName;
    }

    
    /**
     * Gets the name of the language.
     *
     * @return The name of the language.
     */
    public String getLanguageName() {
        return languageName;
    }
    
    
    /**
     * Gets the singleton instance of the Language enum, defaulting to English.
     *
     * @return The Language instance.
     */
    public static Language getInstance() {
        if (instance == null) {
            synchronized (Language.class) {
                if (instance == null) {
                    instance = DEFAULT;
                }
            }
        }
        return instance;
    }

    /**
     * Sets the language name for the current instance.
     *
     * @param languageName The name of the language.
     * @throws InvalidLanguageException If an invalid language is provided.
     */
    public void setLanguageName(String languageName) throws InvalidLanguageException {
        for(Language lang : Language.values()) {
        	if(languageName.equals(lang.getLanguageName())) {
        		this.languageName = languageName;
        		return;
        	}
        }
        // Throw an exception for invalid language
        throw new InvalidLanguageException("Invalid language: " + languageName);
    }


}


