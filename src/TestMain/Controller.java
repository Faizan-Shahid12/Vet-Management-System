/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestMain;

import Database.DBHandler;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Kamran Shahid
 */
public class Controller 
{
    private static PetOwner Owner;
    private static Admin Adm = new Admin();
    private static Veterinarian Vet;

    private DBHandler DB = DBHandler.getInstance();
    
    public void RegisterNewPet(String PetName,String Breed,String Species,int Age,LocalDate DOB) throws ClassNotFoundException
    {
        Owner.AddPet(PetName, Breed, Species, Age, DOB);
    }
    
    public void UpdatePet(Pet pet,String Name,String Breed,String Species,int age,LocalDate Date)
    {
        Owner.UpdatePet(pet, Name, Breed, Species, age, Date);
    }
    
    public void DeletePet(Pet pet)
    {
        Owner.DeletePet(pet);
    }
    
    public ArrayList<Veterinarian> GetVets()
    {
        ArrayList<Veterinarian> Vet = DB.GetVeterinarian();
        
        return Vet;
    }
    
    public ArrayList<Pharmacy> GetPharmacy()
    {
        return DB.GetPharmacy();
    }
    
    public void MakeAppointSlots()
    {
        DB.MakeAppointmentSlots();
    }
    
    public ArrayList<Slot> GetSlot()
    {
        return DB.GetSlot();
    }
    
    public void ScheduleAppointment(Veterinarian Vet,Pet pet,int Slotid,LocalDate Date,String Purp,String T1)
    {
        Owner.RequestAppointment(Vet, pet, Slotid, Date, Purp, T1);
    }
    
    public void GiveFeedBack(Veterinarian Vet,double rating,String comment)
    {
        Owner.SubmitFeedback(Vet, rating, comment);
    }
    
    public void BookDayCare(DayCare D)
    {
        Owner.BookDayCare(D.getPetId(), D.getStartDate(), D.getEndDate(), D.getSpecial(),D.getBill());
    }
    
    public void CancelDayCare(DayCare D)
    {
        Owner.CancelDayCare(D);
    }
    
    public void AddtoCart(Medicine Med,int Quant)
    {
        Owner.getCart().AddMed(Med, Quant);
    }
    
    public void CartEmpty()
    {
        Owner.getCart().empty();
    }
    
    public void UpdateCartMed(int MedId,int Quant)
    {
        Owner.getCart().UpdateQuantity(MedId, Quant);
    }
    
    public void RemoveCartMed(int MedId)
    {
        Owner.getCart().RemoveMed(MedId);
    }
    
    public double GetCartTotal()
    {
        return Owner.getCart().GetTotal();
    }
    
    public Cart GetCart()
    {
        return Owner.getCart();
    }
    
    public boolean CheckCart()
    {
        return Owner.getCart().Check();
    }
    
    public void AddMedical(Pet pet,ArrayList<String> Diagnosis,ArrayList<String> Symptoms,ArrayList<String> Treatment,LocalDate Date)
    {
        Vet.AddMedicalHistory(pet, Diagnosis, Symptoms, Treatment, Date);
    }
    
    public void DeleteMedical(Pet pet,MedicalHistory Date)
    {
        Vet.DeleteMedicalHistory(pet,Date);
    }
    
    public ArrayList<Medicine> CartMed()
    {
        return Owner.getCart().getBuyMed();
    }
    
    public ArrayList<DayCare> GetDayCare(Pet P)
    {
        return P.getListDayCare();
    }
    
    public ArrayList<Medicine> GetMedicine(Pharmacy P)
    {
        return P.getPharMedicine();
    }
    
    public boolean PharCheckStock(Pharmacy Phar,Medicine Meds,int Quan)
    {
        return Phar.CheckStock(Meds, Quan);
    }
    
    public void BuyMedicine(Pharmacy Phar)
    {
        Owner.BuyMedicine(Phar);
    }
    
    public static PetOwner getOwner()
    {
        return Owner;
    }

    public static void setOwner(PetOwner Owner) 
    {
        Controller.Owner = Owner;
    }
    
    public ArrayList<PetOwner> GetPetOwners()
    {
        return DB.GetPetOwner();
    }
    
    public ArrayList<Pet> GetPets()
    {
        return Owner.getListPet();
    }
    public ArrayList<Appointments> GetAppoint()
    {
        return Owner.getListAppointment();
    }
    
    public ArrayList<MedicalHistory> GetHistory(Pet P)
    {
        return P.getListMedical();
    }
    
    public ArrayList<TreatmentPlan> GetTreatmentPlans(Pet P)
    {
        return P.getListTreatment();
    }
    public ArrayList<Prescription> GetPrescription(Pet P)
    {
        return P.getListPrescription();
    }
    public ArrayList<Admin> GetAdmin()
    {
        return DB.GetAdmin();
    }
    
    public ArrayList<Medicine> GetAllMedicine()
    {
        return DB.GetMedicine();
    }
    
    public static Admin getAdmin() {
        return Adm;
    }

    public static void setAdmin(Admin aAdm) {
        Adm = aAdm;
    }
    
    public void DeleteAdmin(Admin Ad)
    {
        Adm.DeleteAdmin(Ad);
    }
    
    public void AuthenticateAdmin(Admin Ad)
    {
        Adm.VerifyAdmin(Ad);
    }
    
    public void DeleteVet(Veterinarian Vet)
    {
        Adm.DeleteVet(Vet);
    }
    
    public void AuthenticateVet(Veterinarian Vet)
    {
        Adm.VerifyVet(Vet);
    }
    
    public void PharRestock(Pharmacy Phar)
    {
        Adm.RestockInventory(Phar);
    }
    
    public void DeleteMedicine(Medicine Med)
    {
        Adm.DeleteMedicine(Med);
    }
    
    public void AddMedicine(String Name,String Type,String Purpose,double Price)
    {
        Adm.AddMedicine(Name,Type,Purpose,Price);
    } 

    public static Veterinarian getVet() {
        return Vet;
    }

    public static void setVet(Veterinarian aVet) {
        Vet = aVet;
    }
    
    public ArrayList<Appointments> GetVetConfirmedAppoint()
    {
        return Vet.GetVetConAppoints();
    }
    
    public ArrayList<Appointments> GetVetPendingAppoints()
    {
        return Vet.GetVetPendingAppoints();
    }
    
    public ArrayList<FeedBack> GetFeedbacks()
    {
        return Vet.getListFeedBack();
    }
    
    public void Confirm_DeclineAppoints(Appointments App,String Val)
    {
        if(Val.equalsIgnoreCase("Confirmed"))
        {
          Vet.ConfirmAppoint(App);
        }
        else if (Val.equalsIgnoreCase("Declined"))
        {
          Vet.DeclineAppoint(App);
        }
        else if (Val.equalsIgnoreCase("Completed"))
        {
          Vet.CompleteAppoint(App);
        }
    }
    
    public ArrayList<TreatmentPlan> GetVetTreatmentPlan(Pet P)
    {
        return Vet.GetVetTreatmentPlan(P);
    }
    
    public void CompleteTreatmentPlan(Pet P1, int PlanId)
    {
        Vet.CompleteTreatmentPlan(P1, PlanId);
    }
    
    public void DeleteTreatmentPlan(Pet P1, TreatmentPlan SelectedPlan)
    {
        Vet.DeleteTreatmentPlan(P1,SelectedPlan);
    }
    
    public void CreateTreatmentPlan(Pet pet,ArrayList<String> Details,ArrayList<String> Note)
    {
        Vet.CreateTreatmentPlan(pet, Details, Note);
    }
    
    public void CompletePrescription(Pet P1,int PrescriptionId)
    {
        Vet.CompletePrescription(P1, PrescriptionId);
    }
    
    public void DeletePrescription(Pet P1, Prescription SelectedPrescription)
    {
        Vet.DeletePrescription(P1,SelectedPrescription);
    }
    
    public void CreatePrescription(Pet pet,ArrayList<String> Instructions,ArrayList<Medicine> Med)
    {
        Vet.RecordPrescription(Instructions, Med, pet);
    }
    
    public ArrayList<MedicalHistory> GetMedicalHistory(Pet P)
    {
        return P.getListMedical();
    }
    
    public ArrayList<Medicine> GetPresMed(Prescription P)
    {
        return P.getListMedicine();
    }
    
    public void CompleteAppointment(Appointments App)
    {
        Vet.CompleteAppoint(App);
    }
    
    public void RegisterPetOwner(String Us, String Pass, String Nam,String Gender, int age1, String Ema, LocalDate DateofBirth,String Con) 
    {
        Adm = new Admin();
        Adm.CreatePetOwner(Us, Pass, Nam, Gender, age1, Ema, DateofBirth, Con);
    }
    
    public void RegisterVeterinarian(String Us, String Pass, String Nam, String Gender,int age1, String Ema, LocalDate DateofBirth,String License,ArrayList<String> Special)
    {
        Adm = new Admin();
        Adm.CreateVeterinarian(Us, Pass, Nam, Gender, age1, Ema, DateofBirth, License, Special);
    }
    
    public void RegisterAdmin(String Us, String Pass, String Nam,String Gender, int age1, String Ema, LocalDate DateofBirth)
    {
        Adm = new Admin();
        Adm.CreateAdmin(Us, Pass, Nam, Gender, age1, Ema, DateofBirth);
    }
    
    public ArrayList<String> GetEmail()
    {
        return DB.GetEmail();
    }
    
    public ArrayList<String> GetUserName()
    {
        return DB.GetUserName();
    }
}
