package languageManagement;

public class InvalidLanguageException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidLanguageException(String message) {
        System.out.print("Wrong Language Input");
    }
}
