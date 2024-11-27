package TestMain;

import Database.DBHandler;
import java.time.LocalDate;
import java.util.ArrayList;


public class Veterinarian extends User
{
    private int VetId;
    private String LicenseNumber;
    private ArrayList<String> Specialization;
    private ArrayList<FeedBack> ListFeedBack;
    private String Status;//Account Verified or not.
    
    public Veterinarian(String Us, String Pass, String Nam, String Gender,int age1, String Ema, LocalDate DateofBirth,String License,ArrayList<String> Special,String Status1,int Vetid) 
    {
        super(Us, Pass, Nam, Gender, age1, Ema, DateofBirth);
        LicenseNumber = License;
        Specialization = Special;
        ListFeedBack = new ArrayList<>();
        Status = Status1;
        VetId = Vetid;
    }
    
    public void AddFeedback(FeedBack Feed)
    {
        ListFeedBack.add(Feed);
    }
    
    public void CreateTreatmentPlan(Pet ForPet,ArrayList<String> Details,ArrayList<String> notes)
    {
        DBHandler DB = DBHandler.getInstance();
        
        int planId = DB.GetLastTreatmentPlanId() + 1;
        
        TreatmentPlan newPlan = new TreatmentPlan(planId,this,ForPet.getPetId(),Details,notes);
        
        ForPet.AddTreatment(newPlan);
        
        DB.WriteTreatmentPlan(newPlan);
      
    }
    
    public void RecordPrescription(ArrayList<String> Instructions, ArrayList<Medicine> ListMedicine, Pet pet)
    {
        DBHandler DB = DBHandler.getInstance();
        
        int PrescripId = DB.GetLastPrescriptionId() + 1;
        
        Prescription Pres = new Prescription(PrescripId,Instructions,ListMedicine,this,pet.getPetId());
        
        pet.AddPrescription(Pres);
        
        DB.WritePrescription(Pres);
        
    }
    
    public void DeleteTreatmentPlan(Pet P1, TreatmentPlan SelectedPlan)
    {
        P1.RemoveTreatmentPlan(SelectedPlan);
        
        DBHandler DB = DBHandler.getInstance();
        DB.RemoveTreatmentPlan(SelectedPlan.getPlanId());
    }
    
    public void CompleteTreatmentPlan(Pet ForPet,int PlanId)
    {
        for(TreatmentPlan Plan : ForPet.getListTreatment())
        {
            if(PlanId == Plan.getPlanId())
            {
                Plan.CompletePlan();
                
                break;
            }
        }
    }
    
    public void CompletePrescription(Pet ForPet,int PlanId)
    {
        for(Prescription Plan : ForPet.getListPrescription())
        {
            if(PlanId == Plan.getPrescripId())
            {
                Plan.Complete();
                
                break;
            }
        }
    }
    
    public void DeletePrescription(Pet P1, Prescription SelectedPlan)
    {
        P1.RemovePrescription(SelectedPlan);
        
        DBHandler DB = DBHandler.getInstance();
        DB.RemovePrescription(SelectedPlan.getPrescripId());
    }
    
    public void AddMedicalHistory(Pet pet,ArrayList<String> Diagnosis,ArrayList<String> Symptoms,ArrayList<String> Treatment,LocalDate Date)
    {
        pet.AddMedicalHistory(Diagnosis,Symptoms,Treatment,Date);
    }
    
    public void DeleteMedicalHistory(Pet pet,MedicalHistory M1)
    {
        pet.RemoveMedicalHistory(M1);
    }
    
    public void CompleteAppoint(Appointments Appoint)
    {
        Appoint.Complete();
    }
    
    public void ConfirmAppoint(Appointments App)
    {
        App.Confirm();
    }
    
    public void DeclineAppoint(Appointments App)
    {
        App.Decline();
    }
    
    public ArrayList<Appointments> GetVetPendingAppoints()
    {
        DBHandler DB = DBHandler.getInstance();
        
        ArrayList<Appointments> VetAppoint = new ArrayList<>();
        
        for(Appointments Appoint : DB.GetAppointment())
        {
            if(Appoint.getVet().getVetId() == this.VetId && Appoint.getStatus().equalsIgnoreCase("Pending"))
            {
                VetAppoint.add(Appoint);
            }
        }
        
        return VetAppoint;
    }
    
    public ArrayList<Appointments> GetVetConAppoints()
    {
         DBHandler DB = DBHandler.getInstance();
        
        ArrayList<Appointments> VetAppoint = new ArrayList<>();
        
        for(Appointments Appoint : DB.GetAppointment())
        {
            if(Appoint.getVet().getVetId() == this.VetId && Appoint.getStatus().equalsIgnoreCase("Confirmed"))
            {
                VetAppoint.add(Appoint);
            }
        }
        
        return VetAppoint;
    }
    
    public ArrayList<TreatmentPlan> GetVetTreatmentPlan(Pet P)
    {
        ArrayList<TreatmentPlan> ArrayTreat = new ArrayList<>();
        
        for(TreatmentPlan T1: P.getListTreatment())
        {
           if(T1.getVet().getVetId() == this.VetId)
           {
               ArrayTreat.add(T1);
           }
            
        }
        
        return ArrayTreat;
    }
    
    public String getLicenseNumber() {
        return LicenseNumber;
    }

    public void setLicenseNumber(String LicenseNumber) {
        this.LicenseNumber = LicenseNumber;
    }

    public ArrayList<String> getSpecialization() {
        return Specialization;
    }

    public void setSpecialization(ArrayList<String> Specialization) {
        this.Specialization = Specialization;
    }

    public ArrayList<FeedBack> getListFeedBack() {
        return ListFeedBack;
    }

    public void setListFeedBack(ArrayList<FeedBack> ListFeedBack) {
        this.ListFeedBack = ListFeedBack;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public int getVetId() {
        return VetId;
    }

    public void setVetId(int VetId) {
        this.VetId = VetId;
    }
    
}
