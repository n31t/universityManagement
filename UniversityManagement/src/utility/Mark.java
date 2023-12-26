package utility;

import java.io.Serializable;

import database.Database;


/**
 * Represents academic marks for a student in a specific course or assessment.
 * This class encapsulates details of the first and second attestation marks, as well as the final exam mark.
 */
public class Mark implements Serializable{
	private static final long serialVersionUID = 1L;
	private int markId;
	private double firstAttestation = 0;
	private double secondAttestation = 0;
	private double Final = 0;
	
	
	// Initializer block for markId
	{
		markId =Database.nextId();
	}
	
	 /**
     * Default constructor for creating a Mark instance without predefined values.
     */
    public Mark() {
    }

    /**
     * Retrieves the unique identifier for the mark.
     *
     * @return The mark ID.
     */
    public int getMarkId() {
        return this.markId;
    }

    /**
     * Retrieves the mark obtained in the first attestation.
     *
     * @return The first attestation mark.
     */
    public double getFirstAttestation() {
        return this.firstAttestation;
    }

    /**
     * Sets the mark for the first attestation.
     *
     * @param firstAttestation The first attestation mark to be set.
     */
    public void setFirstAttestation(double firstAttestation) {
        this.firstAttestation = firstAttestation;
    }

    /**
     * Retrieves the mark obtained in the second attestation.
     *
     * @return The second attestation mark.
     */
    public double getSecondAttestation() {
        return this.secondAttestation;
    }

    /**
     * Sets the mark for the second attestation.
     *
     * @param secondAttestation The second attestation mark to be set.
     */
    public void setSecondAttestation(double secondAttestation) {
        this.secondAttestation = secondAttestation;
    }

    /**
     * Retrieves the mark obtained in the final exam.
     *
     * @return The final exam mark.
     */
    public double getFinal() {
        return this.Final;
    }

    /**
     * Sets the mark for the final exam.
     *
     * @param Final The final exam mark to be set.
     */
    public void setFinal(double Final) {
        this.Final = Final;
    }

    // End of class definition
}
