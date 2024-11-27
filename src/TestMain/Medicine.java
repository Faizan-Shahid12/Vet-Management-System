
package TestMain;

public class Medicine 
{
    private int MedicineId;
    private String Name;
    private String type;
    private String purpose;
    private int Quantity;
    private double Price;
    private int Dosage;
    
    public Medicine(int Id,String Nam,String Manu,String purpos1,int Quan,double pri)
    {
        MedicineId = Id;
        Name = Nam;
        type = Manu;
        purpose = purpos1;
        Price = pri;
        Quantity = Quan; 
    }
    
    public void DecrementQuan(int Val)
    {
        Quantity = Quantity - Val;
    }
    
    public void RestockMedicine(int MaxStock)
    {
        Quantity = MaxStock;
    }

    public int getMedicineId() {
        return MedicineId;
    }

    public String getName() {
        return Name;
    }

    public int getQuantity() {
        return Quantity;
    }

    public double getPrice() {
        return Price;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public String getType() {
        return type;
    }

    public String getPurpose() {
        return purpose;
    }

    public int getDosage() {
        return Dosage;
    }

    public void setDosage(int Dosage) {
        this.Dosage = Dosage;
    }
    
}
