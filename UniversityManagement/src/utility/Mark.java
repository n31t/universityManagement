package utility;

import java.io.Serializable;

public class Mark implements Serializable{
	private static final long serialVersionUID = 1L;
	private int markId;
	private double firstAttestation;
	private double secondAttestation;
	private double Final;

	public int getMarkId() {
		return this.markId;
	}

	public void setMarkId(int markId) {
		this.markId = markId;
	}

	public double getFirstAttestation() {
		return this.firstAttestation;
	}

	public void setFirstAttestation(double firstAttestation) {
		this.firstAttestation = firstAttestation;
	}

	public double getSecondAttestation() {
		return this.secondAttestation;
	}

	public void setSecondAttestation(double secondAttestation) {
		this.secondAttestation = secondAttestation;
	}

	public double getFinal() {
		return this.Final;
	}

	public void setFinal(double Final) {
		this.Final = Final;
	}
}
