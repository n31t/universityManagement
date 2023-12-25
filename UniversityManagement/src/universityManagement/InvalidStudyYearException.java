package universityManagement;

public class InvalidStudyYearException extends Exception {
    private static final long serialVersionUID = 1L;

	public InvalidStudyYearException(String message) {
        super(message);
    }
}