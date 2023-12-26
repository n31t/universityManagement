package exceptions;

public class InvalidSupervisorException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidSupervisorException() {
        System.out.print("Supervisor must have hindex > 3");
    }
}
