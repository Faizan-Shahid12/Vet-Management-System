/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestMain;

import Database.DBHandler;
import java.util.ArrayList;

/**
 *
 * @author Kamran Shahid
 */
public class Cart 
{   
    private int CartId;
    private int PetOwnerId;
    private ArrayList<Medicine> BuyMed;
    private double Total;

    public Cart(int CartId,int PetOwnerId)
    {
        this.CartId = CartId;
        this.PetOwnerId = PetOwnerId;
        Total = 0;
    }
    
    public double GetTotal()
    {
        for(Medicine Med : BuyMed)
        {
            Total += Med.getPrice()*Med.getQuantity();
        }
        
        return Total;
    }
    
    public void UpdateQuantity(int MedId,int Quant)
    {
        for(Medicine Med : BuyMed)
        {
            if(Med.getMedicineId() == MedId)
            {
                Med.setQuantity(Quant);
                DBHandler DB = DBHandler.getInstance();
                DB.UpdateQuan(CartId, Med);
                break;
            }
        }
    }
    
    public void RemoveMed(int MedId)
    {
        for(Medicine Med : BuyMed)
        {
            if(Med.getMedicineId() == MedId)
            {
                BuyMed.remove(Med);
                DBHandler DB = DBHandler.getInstance();
                DB.RemoveMed(CartId,MedId);
                break;
            }
        }
    }
    
    public void empty()
    {
        DBHandler DB = DBHandler.getInstance();
        BuyMed.clear();
        Total = 0;
        DB.EmptyCart(CartId);
    }
    
    public void AddMed(Medicine Med,int Quant)
    {
        Med.setQuantity(Quant);
        
        DBHandler DB = DBHandler.getInstance();
        for(Medicine Med1 : BuyMed)
        {
            if(Med1.getMedicineId() == Med.getMedicineId())
            {
                this.UpdateQuantity(Med.getMedicineId(), Med1.getQuantity() + Med.getQuantity());
                return;
            }
        }
        
        BuyMed.add(Med);
        DB.AddMeds(CartId, Med);
    }
    
    public boolean Check()
    {
        if(BuyMed.isEmpty() == true)
        {
            return true;
        }
        
        return false;
    }
    
    public int getCartId() {
        return CartId;
    }

    public void setCartId(int CartId) {
        this.CartId = CartId;
    }

    public int getPetOwnerId() {
        return PetOwnerId;
    }

    public void setPetOwnerId(int PetOwnerId) {
        this.PetOwnerId = PetOwnerId;
    }

    public ArrayList<Medicine> getBuyMed() {
        return BuyMed;
    }

    public void setBuyMed(ArrayList<Medicine> BuyMed) {
        this.BuyMed = BuyMed;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }
       
       

   
}
