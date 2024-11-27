/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestMain;

import Database.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Kamran Shahid
 */
public class Admin extends User
{
    private int AdminId;
    private String Status;
    
    public Admin()
    {
        
    }
    
    public Admin(String Us, String Pass, String Nam,String Gender, int age1, String Ema, LocalDate DateofBirth,int Id,String Status1)
    {
        super(Us, Pass, Nam,Gender, age1, Ema, DateofBirth);
        AdminId = Id;
        Status = Status1;
    }
    
    public void CreatePetOwner(String Us, String Pass, String Nam,String Gender, int age1, String Ema, LocalDate DateofBirth,String Con) 
    {
        DBHandler DB = DBHandler.getInstance();
        
        DB.WriteUser(Us, Pass, Nam, Gender, age1, Ema, DateofBirth);
        
        int Id = DB.GetLastPetOwnerId() + 1;
        PetOwner Owner = new PetOwner(Us,Pass,Nam,Gender,age1,Ema,DateofBirth,Id,Con);
        
        DB.WritePetOwner(Owner);
        
        int CartId = DB.GetLastCartId() + 1;
        Cart cart = new Cart(CartId,Owner.getPetOwnerId());
        Owner.setCart(cart);
        
        DB.WriteCart(cart);
        
    }
    public void CreateVeterinarian(String Us, String Pass, String Nam, String Gender,int age1, String Ema, LocalDate DateofBirth,String License,ArrayList<String> Special) 
    {
        DBHandler DB = DBHandler.getInstance();
        
        DB.WriteUser(Us, Pass, Nam, Gender, age1, Ema, DateofBirth);
        
        int Id = DB.GetLastVetId() + 1;
        
        Veterinarian Vet = new Veterinarian(Us,Pass,Nam,Gender,age1,Ema,DateofBirth,License,Special,"Pending",Id);
        
        DB.WriteVet(Vet);
        
    }
    public void CreateAdmin(String Us, String Pass, String Nam,String Gender, int age1, String Ema, LocalDate DateofBirth)
    {
        DBHandler DB = DBHandler.getInstance();
        
        DB.WriteUser(Us, Pass, Nam, Gender, age1, Ema, DateofBirth);
        
        int Id = DB.GetLastAdminId() + 1;
        
        Admin NewAdmin = new Admin(Us,Pass,Nam,Gender,age1,Ema,DateofBirth,Id,"Pending");
        
        DB.WriteAdmin(NewAdmin);
    }
    
    public void VerifyVet(Veterinarian Vet)
    {
        Vet.setStatus("Approved");
        
        DBHandler DB = DBHandler.getInstance();
        
        DB.UpdateVetStatus(Vet.getVetId());
    }
    
    public void VerifyAdmin(Admin Adm)
    {
        Adm.setStatus("Approved");
        
        DBHandler DB = DBHandler.getInstance();
        
        DB.UpdateAdminStatus(Adm.getAdminId());
    }
    
    public void DeleteAdmin(Admin Adm)
    {
        DBHandler DB = DBHandler.getInstance();
        
        DB.DeleteAdmin(Adm.getAdminId(), Adm.getUsername());
    }
    
    public void DeleteVet(Veterinarian Vet)
    {
        DBHandler DB = DBHandler.getInstance();
        
        DB.DeleteVet(Vet.getVetId(), Vet.getUsername());
    }
    
    public void RestockInventory(Pharmacy Phar)
    {
        Phar.ReStock();
    }
    
    public void DeleteMedicine(Medicine Med)
    {
        DBHandler DB = DBHandler.getInstance();
        
        DB.DeleteMedicine(Med.getMedicineId());
    }
    
    public void AddMedicine(String Name,String Type,String Purpose,double Price)
    {
        DBHandler DB = DBHandler.getInstance();
        
        int MedId = DB.GetLastMedicineId() + 1;
        
        Medicine Med = new Medicine(MedId,Name,Type,Purpose,0,Price);
        
        DB.WriteMedicine(Med);
        
        AddPharMed(Med);
    }
    
    public void AddPharMed(Medicine Med)
    {
        DBHandler DB = DBHandler.getInstance();
        
        for(Pharmacy Phar : DB.GetPharmacy())
        {
            Phar.AddMedicine(Med);
        }
    }
    
    public int getAdminId() {
        return AdminId;
    }

    public void setAdminId(int AdminId) {
        this.AdminId = AdminId;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
}
