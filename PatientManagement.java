// Description: This program manages patient queues, assigns patients to doctors,
//              release them to check out, etc.

import java.util.LinkedList;

public class PatientManagement {
	private LinkedList<Patient> highPriorityQueue; // Requires immediate life-saving intervention
	private LinkedList<Patient> intermediateQueue; // Requires significant intervention within two to four hours.
	private LinkedList<Patient> delayedCareQueue; // Needs medical treatment, but this can safely be delayed.

	private LinkedList<Patient> checkOutQueue; // queue for patients that need to check out

	private Doctor[] doctorList; // an array of doctors

	// Constructor to initialize member variables
	public PatientManagement(int numOfDoctors) {
		highPriorityQueue = new LinkedList<Patient>();
		intermediateQueue = new LinkedList<Patient>();
		delayedCareQueue = new LinkedList<Patient>();
		checkOutQueue = new LinkedList<Patient>();

		// creating doctor objects
		doctorList = new Doctor[numOfDoctors];
		for (int i = 0; i < doctorList.length; i++) {
			doctorList[i] = new Doctor(i);
		}
	}

	// The printQueue prints out the content
	// of the queues and the array of doctors
	public void printPatientQueues() {
		System.out.print("\nHigh Priority Queue:\n" + highPriorityQueue.toString() + "\n");
		System.out.print("\nIntermediate Queue:\n" + intermediateQueue.toString() + "\n");
		System.out.print("\nDelayed Care Queue:\n" + delayedCareQueue.toString() + "\n\n");
		for (int i = 0; i < doctorList.length; i++) {
			System.out.println(doctorList[i].toString());
		}
		System.out.print("\nCheck Out Queue:\n" + checkOutQueue.toString() + "\n\n");
	}

	// More Methods need to be added here
	boolean addPatient(int patientID, String conditionLevel) {
		if (conditionLevel.equals("High Priority")) {
			Patient patient1 = new Patient(patientID, conditionLevel);
			highPriorityQueue.offer(patient1);
			return true;
		} else if (conditionLevel.equals("Intermediate")) {
			Patient patient1 = new Patient(patientID, conditionLevel);
			intermediateQueue.offer(patient1);
			return true;
		} else if (conditionLevel.equals("Delayed Care")) {
			Patient patient1 = new Patient(patientID, conditionLevel);
			delayedCareQueue.offer(patient1);
			return true;
		} else {
			return false;
		}
	}

	// Assigns a patient to a doctor
	Patient assignPatientToDoctor() {
		Patient patient1;
		int counter = 0;
		// Checks if any doctor is available
		while (doctorList[counter].hasPatient() && counter < doctorList.length - 1) {
			counter++;
		}
		// If a doctor is available and there is a patient in high priority, assign it to doctor, return null otherwise
		if (!highPriorityQueue.isEmpty()) {
			if (!doctorList[counter].hasPatient()) {
				patient1 = highPriorityQueue.remove();
				doctorList[counter].assignPatient(patient1);
				return patient1;
			} else {
				return null;
			} // If a doctor is available and there is a patient in intermediate priority, assign it to doctor, return null otherwise
		} else if (!intermediateQueue.isEmpty()) {
			if (!doctorList[counter].hasPatient()) {
				patient1 = intermediateQueue.remove();
				doctorList[counter].assignPatient(patient1);
				return patient1;
			} else {
				return null;
			} // If a doctor is available and there is a patient in delayed care, assign it to doctor, return null otherwise
		} else if (!delayedCareQueue.isEmpty()) {
			if (!doctorList[counter].hasPatient()) {
				patient1 = delayedCareQueue.remove();
				doctorList[counter].assignPatient(patient1);
				return patient1;
			} else {
				return null;
			}
		} else {
			return null;
		}

	}

	// Releases a patient from a doctor to the checkout queue, returns the patient or null if there is no patient with doctor
	Patient releasePatientFromDoctorToCheckOutQueue(int doctorID) {
		if (doctorList[doctorID].hasPatient()) {
			Patient temp = doctorList[doctorID].releasePatient();
			checkOutQueue.offer(temp);
			return temp;
		} else {
			return null;
		}

	}

	// Checks out patient from the queue, returns null if no patients
	Patient checkOutPatient() {
		if (!checkOutQueue.isEmpty()) {
			return checkOutQueue.remove();
		} else {
			return null;
		}
	}

}