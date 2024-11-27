package TestMain;


import Database.DBHandler;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


public class PetOwner extends User 
{

    private int PetOwnerId;
    private String Contact;
    private ArrayList<Pet> ListPet;
    private ArrayList<Appointments> ListAppointment;
    private Cart cart;
    
    public PetOwner()
    {
     super();
    }
    
    public PetOwner(String Us, String Pass, String Nam,String Gender, int age1, String Ema, LocalDate DateofBirth,int Id,String Con)
    {
        super(Us, Pass, Nam,Gender, age1, Ema, DateofBirth);
        PetOwnerId = Id;
        Contact = Con;
        ListPet = new ArrayList<>();
        ListAppointment = new ArrayList<>();
    }
    
    public void AddPet(String Nam,String Breed,String Speci,int Agee,LocalDate DOB) throws ClassNotFoundException
    {
        
        DBHandler DB = DBHandler.getInstance();
        
        int PetId = DB.GetLastPetId()+1; //Database Query
        
        Pet NewPet = new Pet(PetId,this.PetOwnerId,Nam,Breed,Speci,Agee,DOB);
        
        NewPet.setOwnerName(this.getName());
        
        ListPet.add(NewPet);
        
        DB.WritePet(NewPet, PetOwnerId);
        
    }
    
    public void UpdatePet(Pet Pet1,String Nam,String Breed,String Speci,int Agee,LocalDate DOB)
    {
        for (Pet pet : ListPet)
        {
            if(pet.getPetId() == Pet1.getPetId())
            {
                pet.setName(Nam);
                pet.setBreed(Breed);
                pet.setSpecies(Speci);
                pet.setAge(Agee);
                pet.setDOB(DOB);
                break;
            }
        }
        
        DBHandler DB = DBHandler.getInstance();
        DB.UpdatePet(Pet1);
    }
    
    public void DeletePet(Pet pet)
    {
       ListPet.remove(pet);
       
       for(Appointments Appoint: ListAppointment)
       {
           if(Appoint.getToPet().getPetId() == pet.getPetId())
           {
               ListAppointment.remove(Appoint);
               break;
           }
       }
       
       DBHandler DB = DBHandler.getInstance();
       
       DB.DeletePet(pet.getPetId());
    }
    
    public void BookDayCare(int petid,LocalDate Start,LocalDate End,ArrayList<String> Special,int Bill)
    {
        
        for(Pet pet : ListPet)
        {
          if(pet.getPetId() == petid)
          {
            pet.AddDayCare(Start,End,Special,Bill);
            break;
          }  
        }
        
    }
    
    public void CancelDayCare(DayCare ReDay)
    {
        for(Pet P : ListPet)
        {
            if(P.getPetId() == ReDay.getPetId())
            {
                P.RemoveDayCare(ReDay);
                break;
            }
        }
        
    }
    
    public void BuyMedicine(Pharmacy Phar)
    {
        Phar.BuyMedicine(this.getCart().getBuyMed());
    }
    
    public void RequestAppointment(Veterinarian Vet,Pet pet,int Slotid,LocalDate Date,String Purp,String T1)
    {
        DBHandler DB = DBHandler.getInstance();
        int AppointmentId =  DB.GetLastAppointmentId() + 1;//use Database Query to get Id
        Appointments NewAppoint = new Appointments(AppointmentId,PetOwnerId,Slotid,pet,Vet,Date,Purp,T1);
        ListAppointment.add(NewAppoint);
        DB.WriteAppointment(NewAppoint);
    }
    
    public void SubmitFeedback(Veterinarian v1,double rate,String comment)
    {
        FeedBack newFeed = new FeedBack(v1.getVetId(),rate,comment,LocalDate.now());
        
        v1.AddFeedback(newFeed);
        
        DBHandler DB = DBHandler.getInstance();
        
        DB.WriteFeedBack(newFeed);
    }
    
    public int getPetOwnerId() 
    {
        return PetOwnerId;
    }

    public void setPetOwnerId(int PetOwnerIdd)
    {
        PetOwnerId = PetOwnerIdd;
    }

    public String getContact()
    {
        return Contact;
    }

    public void setContact(String Contact)
    {
        this.Contact = Contact;
    }

    public ArrayList<Pet> getListPet() {
        return ListPet;
    }

    public void setListPet(ArrayList<Pet> ListPet) {
        this.ListPet = ListPet;
    }

    public ArrayList<Appointments> getListAppointment() {
        return ListAppointment;
    }

    public void setListAppointment(ArrayList<Appointments> ListAppointment) {
        this.ListAppointment = ListAppointment;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
    
}
