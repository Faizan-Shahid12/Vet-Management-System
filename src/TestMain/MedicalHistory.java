package TestMain;

import java.time.LocalDate;
import java.util.ArrayList;

public class MedicalHistory 
{
    private int MedicalId;
    private int PetId;
    private ArrayList<String> Diagnosis;
    private ArrayList<String> Symptoms;
    private ArrayList<String> TreatmentDetails;
    private LocalDate Date;

    public MedicalHistory(int MedicalId, int PetId, ArrayList<String> Diagnosis, ArrayList<String> Symptoms, ArrayList<String> TreatmentDetails, LocalDate Date) {
        this.MedicalId = MedicalId;
        this.PetId = PetId;
        this.Diagnosis = Diagnosis;
        this.Symptoms = Symptoms;
        this.TreatmentDetails = TreatmentDetails;
        this.Date = Date;
    }
    
    public int getMedicalId() {
        return MedicalId;
    }

    public void setMedicalId(int MedicalId) {
        this.MedicalId = MedicalId;
    }

    public int getPetId() {
        return PetId;
    }

    public void setPetId(int PetId) {
        this.PetId = PetId;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate Date) {
        this.Date = Date;
    }

    public ArrayList<String> getDiagnosis() {
        return Diagnosis;
    }

    public void setDiagnosis(ArrayList<String> Diagnosis) {
        this.Diagnosis = Diagnosis;
    }

    public ArrayList<String> getSymptoms() {
        return Symptoms;
    }

    public void setSymptoms(ArrayList<String> Symptoms) {
        this.Symptoms = Symptoms;
    }

    public ArrayList<String> getTreatmentDetails() {
        return TreatmentDetails;
    }

    public void setTreatmentDetails(ArrayList<String> TreatmentDetails) {
        this.TreatmentDetails = TreatmentDetails;
    }

}
