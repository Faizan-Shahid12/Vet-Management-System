package TestMain;


import Database.DBHandler;
import java.time.LocalDate;
import java.util.ArrayList;


public class Pet 
{
    private int PetId;
    private int PetOwnerId;
    private String OwnerName;
    private String Name;
    private String Breed;
    private String Species;
    private int Age;
    private LocalDate DOB;
    private ArrayList<TreatmentPlan> ListTreatment;
    private ArrayList<DayCare> ListDayCare;
    private ArrayList<Prescription> ListPrescription;
    private ArrayList<MedicalHistory> ListMedical;
    
    public Pet(int Petid,int PetOwner,String Nam, String Breedd,String Speic, int Agee, LocalDate DOOB) 
    {
        Name = Nam;
        Breed = Breedd;
        Species = Speic;
        Age = Agee;
        DOB = DOOB;
        PetId = Petid;
        PetOwnerId = PetOwner;
        ListTreatment = new ArrayList<>();
        ListDayCare = new ArrayList<>();
        ListPrescription = new ArrayList<>();
        ListMedical = new ArrayList<>();
    }
    
    public void AddTreatment(TreatmentPlan T1)
    {
        ListTreatment.add(T1);
    }
    
    public void RemoveTreatmentPlan(TreatmentPlan SelectedPlan)
    {
        ListTreatment.remove(SelectedPlan);
    }
    
    
    
    public void AddDayCare(LocalDate Start,LocalDate End,ArrayList<String> Special,int Bill)
    {
        DBHandler DB = DBHandler.getInstance();
        
        int DayCareId = DB.GetLastDayCareId() + 1;
        
        DayCare NewDay = new DayCare(DayCareId,PetId,Start,End,Special);
        
        NewDay.setBill(Bill);
        
        ListDayCare.add(NewDay);
        
        DB.WriteDayCare(NewDay);
    }
    
    public void RemoveDayCare(DayCare D2)
    {
        for(DayCare D : ListDayCare)
        {
            if(D.getDaycareId() == D2.getDaycareId())
            {
                D.Cancel();
                break;
            }
        }
    }
    
    public MedicalHistory AddMedicalHistory(ArrayList<String> Diagnosis,ArrayList<String> Symptoms,ArrayList<String> Treatment,LocalDate Date)
    {
        DBHandler DB = DBHandler.getInstance();
        
        int MedicalHistoryId = DB.GetLastMedicalHistoryId() + 1;
        
        MedicalHistory NewMedical = new MedicalHistory(MedicalHistoryId,PetId,Diagnosis,Symptoms,Treatment,Date);
        
        ListMedical.add(NewMedical);
        
        DB.WriteMedicalHistory(NewMedical);
        
        return NewMedical;
    }
    
    public void RemoveMedicalHistory(MedicalHistory MH)
    {
        for(MedicalHistory M : ListMedical)
        {
            if(MH.getMedicalId() == M.getMedicalId())
            {
                ListMedical.remove(M);    
               
                DBHandler DB = DBHandler.getInstance();
                
                DB.RemoveMedicalHistory(MH.getMedicalId());
                break;
            }
        }
    }
    
    public void AddPrescription(Prescription P1)
    {
        ListPrescription.add(P1);
    }
    
    public void RemovePrescription(Prescription SelectedPrescription)
    {
        ListPrescription.remove(SelectedPrescription);
    }
    
    public ArrayList<DayCare> getListDayCare() {
        return ListDayCare;
    }

    public void setListDayCare(ArrayList<DayCare> ListDayCare) {
        this.ListDayCare = ListDayCare;
    }
    
    public ArrayList<TreatmentPlan> getListTreatment() {
        return ListTreatment;
    }

    public void setListTreatment(ArrayList<TreatmentPlan> ListTreatment) 
    {
        this.ListTreatment = ListTreatment;
    }

    public int getPetId() {
        return PetId;
    }

    public void setPetId(int PetId) {
        this.PetId = PetId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getBreed() {
        return Breed;
    }

    public void setBreed(String Breed) {
        this.Breed = Breed;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }

    public ArrayList<Prescription> getListPrescription() {
        return ListPrescription;
    }

    public void setListPrescription(ArrayList<Prescription> ListPrescription) {
        this.ListPrescription = ListPrescription;
    }

    public String getSpecies() {
        return Species;
    }

    public void setSpecies(String Species) {
        this.Species = Species;
    }

    public int getPetOwnerId() {
        return PetOwnerId;
    }

    public void setPetOwnerId(int PetOwnerId) {
        this.PetOwnerId = PetOwnerId;
    }

    public ArrayList<MedicalHistory> getListMedical() {
        return ListMedical;
    }

    public void setListMedical(ArrayList<MedicalHistory> ListMedical) {
        this.ListMedical = ListMedical;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String OwnerName) {
        this.OwnerName = OwnerName;
    }
    
    
}
