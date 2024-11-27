
package TestMain;

import Database.DBHandler;
import java.time.LocalDate;
import java.util.ArrayList;


public class DayCare 
{
    private int DaycareId;
    private int PetId;
    private LocalDate StartDate;
    private LocalDate EndDate;
    private String Status;
    private ArrayList<String> Special;
    private int Bill;
    
    public DayCare(int Id,int petId,LocalDate SD,LocalDate ED,ArrayList<String> Spec)
    {
        DaycareId = Id;
        PetId = petId;
        StartDate = SD;
        EndDate = ED;
        Special = Spec;
        
        if(SD.isEqual(LocalDate.now()))
        {
            Status = "In Progress";
        }
        else
        {
            Status = "To Start";
        }
    }
    
    public void Cancel()
    {
        Status = "Cancelled";
        
        DBHandler DB = DBHandler.getInstance();
        DB.UpdateDayCareStatus(DaycareId, Status);
    }
    
    
    public long CalDuration()
    {
        LocalDate Start = LocalDate.now();
        
        long remainingDays = EndDate.toEpochDay() - StartDate.toEpochDay();
        
        if(LocalDate.now() == EndDate)
        {
            Status = "Completed";

            DBHandler DB = DBHandler.getInstance();
            DB.UpdateDayCareStatus(DaycareId, Status);
            
            return 0;
        }
        
        return remainingDays;
    }
    
    public ArrayList<String> getSpecial() {
        return Special;
    }

    public void setSpecial(ArrayList<String> Special) {
        this.Special = Special;
    }
    
    public int getDaycareId() {
        return DaycareId;
    }

    public void setDaycareId(int DaycareId) {
        this.DaycareId = DaycareId;
    }

    public LocalDate getStartDate() {
        return StartDate;
    }

    public void setStartDate(LocalDate StartDate) {
        this.StartDate = StartDate;
    }

    public LocalDate getEndDate() {
        return EndDate;
    }

    public void setEndDate(LocalDate EndDate) {
        this.EndDate = EndDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public int getPetId() {
        return PetId;
    }

    public void setPetId(int PetId) {
        this.PetId = PetId;
    }

    public int getBill() {
        return Bill;
    }

    public void setBill(int Bill) {
        this.Bill = Bill;
    }
    
}
