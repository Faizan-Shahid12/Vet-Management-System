package TestMain;


import Database.DBHandler;
import java.util.ArrayList;


public class TreatmentPlan 
{
    private int PlanId;
    private Veterinarian Vet;
    private int PetId;
    private ArrayList<String> Details;
    private ArrayList<String> Notes;
    private String Status;
    
    public TreatmentPlan()
    {
        
    }
    
    public TreatmentPlan(int Planid,Veterinarian Vet1,int P1,ArrayList<String> Deta,ArrayList<String> Note)
    {
        PlanId = Planid;
        Vet = Vet1;
        PetId = P1;
        Details = Deta;
        Notes = Note;
        Status = "On Going";
    }
    public void CompletePlan()
    {
        Status = "Completed";
        
        DBHandler DB = DBHandler.getInstance();
        DB.UpdateTreatmentPlanStatus(PlanId);
        
    }
    
    public int getPlanId() {
        return PlanId;
    }

    public Veterinarian getVet() {
        return Vet;
    }

    public ArrayList<String> getDetails() {
        return Details;
    }

    public ArrayList<String> getNotes() {
        return Notes;
    }

    public String getStatus() {
        return Status;
    }

    public int getPetId() {
        return PetId;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
    
}
