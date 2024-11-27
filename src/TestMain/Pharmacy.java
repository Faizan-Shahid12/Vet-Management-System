package TestMain;

import Database.DBHandler;
import java.util.ArrayList;


public class Pharmacy
{
    private int PharmacyId;
    private String PharName;
    private String Location;
    private String Contact;
    private int MaxStock;
    private ArrayList<Medicine> PharMedicine;

    public Pharmacy(int PharmacyId, String PharName, String Location, String Contact, int MaxStock,ArrayList<Medicine> PharMedicine) 
    {
        this.PharmacyId = PharmacyId;
        this.PharName = PharName;
        this.Location = Location;
        this.Contact = Contact;
        this.MaxStock = MaxStock;
        this.PharMedicine = PharMedicine;
    }
    
    public boolean CheckStock(Medicine Meds, int Quan)
    {
        boolean check = false;
        
        if(Meds.getQuantity() > Quan)
        {
            return true;
        }
        
        return check;
    }
    
    public void BuyMedicine(ArrayList<Medicine> Meds)
    {
        DBHandler DB = DBHandler.getInstance();
        
        for(Medicine PMeds : PharMedicine)
        {
            for(Medicine CMed : Meds)
            {
                if(CMed.getMedicineId() == PMeds.getMedicineId())
                {
                    PMeds.DecrementQuan(CMed.getQuantity());
                    
                    DB.UpdatePharMedicineQuantity(PharmacyId,PMeds);
                }
            }
        }
    }
    
    public void ReStock()
    {
        DBHandler DB = DBHandler.getInstance();
        
        for(Medicine Med : PharMedicine)
        {
            Med.RestockMedicine(MaxStock);
            
            DB.UpdatePharMedicineQuantity(PharmacyId,Med);
        }
    }
    
    public void AddMedicine(Medicine Med)
    {
        Med.setQuantity(MaxStock);
        PharMedicine.add(Med);
        
        DBHandler DB = DBHandler.getInstance();
        DB.WritePharMed(this.PharmacyId,Med);
        
    }

    public int getPharmacyId() {
        return PharmacyId;
    }

    public String getPharName() {
        return PharName;
    }

    public String getLocation() {
        return Location;
    }

    public String getContact() {
        return Contact;
    }

    public ArrayList<Medicine> getPharMedicine() {
        return PharMedicine;
    }

    public int getMaxStock() {
        return MaxStock;
    }

    public void setMaxStock(int MaxStock) {
        this.MaxStock = MaxStock;
    }
    
    
}
