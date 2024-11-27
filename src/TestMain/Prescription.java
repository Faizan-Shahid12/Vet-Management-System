package TestMain;

import Database.DBHandler;
import java.util.ArrayList;

public class Prescription 
{
    private int PrescripId;
    private ArrayList<String> Instructions; 
    private String Status;
    private ArrayList<Medicine> ListMedicine;
    private Veterinarian Vet;
    private int PetId;

    public Prescription(int PrescripId, ArrayList<String> Instructions, ArrayList<Medicine> ListMedicine,
            Veterinarian Vet,int pet)
    {
        this.PrescripId = PrescripId;
        this.Instructions = Instructions;
        this.Status = "On Going";
        this.ListMedicine = ListMedicine;
        this.Vet = Vet;
        this.PetId= pet;
    }
    
    public void Complete()
    {
        Status = "Completed";
        DBHandler DB = DBHandler.getInstance();
        DB.UpdatePrescriptionStatus(PrescripId);
    }

    public int getPrescripId() {
        return PrescripId;
    }

    public ArrayList<String> getInstructions() {
        return Instructions;
    }

    public String getStatus() {
        return Status;
    }

    public ArrayList<Medicine> getListMedicine() {
        return ListMedicine;
    }

    public Veterinarian getVet() {
        return Vet;
    }

    public int getPetId() {
        return PetId;
    }

    public void setPetId(int PetId) {
        this.PetId = PetId;
    }

    public void setPrescripId(int PrescripId) {
        this.PrescripId = PrescripId;
    }

    public void setInstructions(ArrayList<String> Instructions) {
        this.Instructions = Instructions;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public void setListMedicine(ArrayList<Medicine> ListMedicine) {
        this.ListMedicine = ListMedicine;
    }

    public void setVet(Veterinarian Vet) {
        this.Vet = Vet;
    }
    
    
}
