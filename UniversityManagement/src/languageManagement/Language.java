package languageManagement;

import java.io.Serializable;

import exceptions.InvalidLanguageException;

public enum Language implements Serializable{
    DEFAULT("English"), EN("English"), RU("Russian"), KZ("Kazakh");

    private String languageName;
    private static Language instance = null;

    Language(String languageName) {
        this.languageName = languageName;
    }

    public String getLanguageName() {
        return languageName;
    }
    
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

    public void setLanguageName(String languageName) throws InvalidLanguageException {
        for(Language lang : Language.values()) {
        	if(languageName.equals(lang.getLanguageName())) {
        		this.languageName = languageName;
        		return;
        	}
        }
        throw new InvalidLanguageException("Invalid language: " + languageName);
    }


}


