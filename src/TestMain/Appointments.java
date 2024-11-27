package TestMain;

import Database.DBHandler;
import java.time.LocalDate;
import java.time.LocalTime;

public class Appointments
{
    private int AppointmentId;
    private int PetOwnerId;
    private int SlotId;
    private Pet ToPet;
    private Veterinarian Vet;
    private LocalDate AppointDate;
    private String Status = "Pending";
    private String Purpose;
    private String StartTime;
    
    public Appointments(int Appointmentid,int PetOwnerid,int Slot,Pet pet1,Veterinarian vet1,LocalDate Date,String Purp,String T1)
    {
        AppointmentId = Appointmentid;
        PetOwnerId = PetOwnerid;
        SlotId = Slot;
        ToPet = pet1;
        Vet = vet1;
        AppointDate = Date;
        Purpose = Purp;
        StartTime = T1;
    }
    
    void Confirm()
    {
        Status = "Confirmed";
        
        DBHandler DB = DBHandler.getInstance();
        DB.UpdateAppointment(AppointmentId, Status);
    }
    
    void Decline()
    {
        Status = "Declined";
        
        DBHandler DB = DBHandler.getInstance();
        DB.UpdateAppointment(AppointmentId, Status);
    }
    
    void Complete()
    {
        Status = "Completed";
        
        DBHandler DB = DBHandler.getInstance();
        DB.UpdateAppointment(AppointmentId, Status);
    }

    public int getAppointmentId() 
    {
        return AppointmentId;
    }

    public int getPetOwnerId() {
        return PetOwnerId;
    }

    public Pet getToPet() {
        return ToPet;
    }

    public Veterinarian getVet() {
        return Vet;
    }

    public String getStatus() {
        return Status;
    }

    public String getPurpose() {
        return Purpose;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStatus(String Status) 
    {
        this.Status = Status;
    }

    public int getSlotId() {
        return SlotId;
    }

    public void setSlotId(int SlotId) {
        this.SlotId = SlotId;
    }

    public LocalDate getAppointDate() {
        return AppointDate;
    }

    public void setAppointDate(LocalDate AppointDate) {
        this.AppointDate = AppointDate;
    }
    
}
