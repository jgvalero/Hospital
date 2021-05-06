// Description: The doctor class creates doctors that have the ability to be assigned with patients, releases a patient, and checks if it has a patient

public class Doctor {
	private int doctorID;
	private Patient currentPatient;

	// Constructor to initialize member variables
	// Initially no patient is assigned
	public Doctor(int id) {
		this.doctorID = id;
		currentPatient = null;
	}

	// toString method returns a string containing
	// the information of a doctor
	public String toString() {
		String result = "Doctor id " + doctorID;

		if (currentPatient == null)
			result += " is not seeing any patient";
		else
			result += " is seeing a patient with id " + currentPatient.getPatientID();

		return result;
	}

	// More Methods need to be added here
	
	// Checks if a doctor has a patient, returns true if yes, false if no
	public boolean hasPatient() {
		if (currentPatient == null) {
			return false;
		} else {
			return true;
		}
	}
	
	// Assigns a patient to a doctor, returns true if added, false otherwise
	public boolean assignPatient(Patient patient1) {
		if (!hasPatient()) {
			currentPatient = patient1;
			return true;
		} else {
			return false;
		}
	}
	
	// Releases a patient from a doctor, returns the patient or null if doctor does not have a patient
	Patient releasePatient() {
		if (hasPatient()) {
			Patient temp = currentPatient;
			currentPatient = null;
			return temp;
		}
		return null;
	}

}
