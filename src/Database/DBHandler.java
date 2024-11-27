package Database;


import java.sql.*;
import TestMain.*;
import java.time.LocalDate;
import java.util.ArrayList;


public class DBHandler 
{
    String userName = "Faizan"; //The username of the sql database
    String pass = "okok"; //The pass of your database
    Connection con; //Connection object
    Statement st; //Statement Obj
    
    private static DBHandler DB;
    
    private DBHandler()
    {
        //Connect to DB
        try
        {       
                String connectionUrl = "jdbc:sqlserver://Faizan_Laptop\\SQLEXPRESS;database=SDA_Project;integratedSecurity=false;encrypt=false;";
                con = DriverManager.getConnection(connectionUrl,userName,pass);  
                System.out.println("Connected to DB");
        }
        catch(SQLException e)
        {
                System.out.println("Connection Failed");
                e.printStackTrace();
        }
    }
    
    public static DBHandler getInstance()
    {
        if (DB == null)
            DB = new DBHandler();
        return DB;
    }
    
    public void WriteUser(String Us, String Pass, String Nam,String Gender, int age1, String Ema, LocalDate DateofBirth)
    {
        try
        {
                st = con.createStatement();
                String query1 = "INSERT INTO SystemUser (UserName, Password, Name, Gender, Age, Email , DOB) " +
                                "VALUES ('" + Us + "', '" +Pass + "', '" +
                                Nam+ "', '" + Gender + "', " + age1 + ", '" +
                                Ema + "', '" + Date.valueOf(DateofBirth) + "')";
                st.executeUpdate(query1);
                System.out.println("User Succesfully inserted");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in User insertion");
                e.printStackTrace();
        }
    }
    
    public ArrayList<String> GetEmail()
    {
        ArrayList<String> Email = new ArrayList<>();
        
        try
        {
            Statement st1 = con.createStatement();
            String query1 = "Select * From SystemUser ";
            ResultSet rs1 = st1.executeQuery(query1);

            while(rs1.next())
            {
                Email.add(rs1.getString("Email"));
            }
            
            return Email;
        }
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in User Email");
                e.printStackTrace();
        }
        
        return null;
    }
    
    public ArrayList<String> GetUserName()
    {
        ArrayList<String> Email = new ArrayList<>();
        
        try
        {
            Statement st1 = con.createStatement();
            String query1 = "Select * From SystemUser ";
            ResultSet rs1 = st1.executeQuery(query1);

            while(rs1.next())
            {
                Email.add(rs1.getString("UserName"));
            }
            
            return Email;
        }
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Get Username");
                e.printStackTrace();
        }
        
        return null;
    }
    
    public void DeleteUser(String UserId)
    {
        try
        {
                st = con.createStatement();
                String query1 = "Delete From SystemUser WHERE Username = '" + UserId + "'";
                st.executeUpdate(query1);
                System.out.println("User Succesfully Deletion");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in User Deletion");
                e.printStackTrace();
        }
    }
    
    public int GetLastUser()
    {
        try
        {
            st = con.createStatement();
            String query1 = "SELECT TOP 1 * FROM SystemUser ORDER BY UserId DESC;";
            ResultSet rs = st.executeQuery(query1);
            int lastId = 0;

               if (rs.next()) //not empty
                {
                        lastId = rs.getInt(1);
                }
                else //is empty
                {
                        lastId = 0;
                }

                return lastId;
                
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Last User ID");
                e.printStackTrace();
        }
         
         return 0;
    }
    
    public void WritePetOwner(PetOwner Owner)
    {
        try
        {       int UserId = GetLastUser();
                st = con.createStatement();
                String query1 = "INSERT INTO PetOwner (UserId,PetOwnerId, Contact) " +
                                "VALUES ('" + UserId + "', '" + Owner.getPetOwnerId() + "','" + Owner.getContact() + "')";
                st.executeUpdate(query1);
                System.out.println("PetOwner Succesfully inserted");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in PetOwner insertion");
                e.printStackTrace();
        }
    }
    
    public int GetLastPetOwnerId()
    {
        try
        {
              
            st = con.createStatement();
            String query1 = "SELECT TOP 1 * FROM PetOwner ORDER BY PetOwnerId DESC;";
            ResultSet rs = st.executeQuery(query1);
            int lastId = 0;

               if (rs.next()) //not empty
                {
                        lastId = rs.getInt(2);
                }
                else //is empty
                {
                        lastId = 0;
                }

                return lastId;
                
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in last PetOwnerID");
                e.printStackTrace();
        }
         
         return 0;
    }
    
    public ArrayList<PetOwner> GetPetOwner()
    {
        try
        {
            Statement st1 = con.createStatement();
            String query1 = "Select * From SystemUser "
                    + "Join PetOwner on SystemUser.UserId = PetOwner.UserId";
            ResultSet rs1 = st1.executeQuery(query1);

            ArrayList<PetOwner> ArrayOwner = new ArrayList<>();

            while(rs1.next())
            {
                PetOwner Owner = new PetOwner(rs1.getString(2),rs1.getString(3),rs1.getString(4),rs1.getString(5),rs1.getInt(6),rs1.getString(7),rs1.getDate(8).toLocalDate(),rs1.getInt(10),rs1.getString(11));
                ArrayList<Pet> ListP = new ArrayList<>();
                
                for(Pet P : DB.GetPet())
                {
                    if(P.getPetOwnerId() == Owner.getPetOwnerId())
                    {
                        P.setOwnerName(Owner.getName());
                        ListP.add(P);
                    }
                }
                
                ArrayList<Appointments> ListApp = new ArrayList<>();
                
                for(Appointments A : DB.GetAppointment())
                {
                    if(A.getPetOwnerId() == Owner.getPetOwnerId())
                    {
                        ListApp.add(A);
                    }
                }
                Owner.setListAppointment(ListApp);
                Owner.setListPet(ListP);
                
                Owner.setCart(DB.GetCart(Owner.getPetOwnerId()));
                
                ArrayOwner.add(Owner);
            }
            
         
            System.out.println("PetOwner Successfully Fetched");
                
            return ArrayOwner;
                
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Get PetOwner");
                e.printStackTrace();
        }
        
        return null;
    }
    
    public void WriteAdmin(Admin Owner)
    {
        try
        {       int UserId = GetLastUser();
                st = con.createStatement();
                String query1 = "INSERT INTO Admin (UserId,AdminId, Status) " +
                                "VALUES ('" + UserId + "', '" + Owner.getAdminId() + "', '" + Owner.getStatus()+ "')";
                st.executeUpdate(query1);
                System.out.println("Admin Succesfully inserted");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Pet insertion");
                e.printStackTrace();
        }
    }
    
    public int GetLastAdminId()
    {
        try
        {
            st = con.createStatement();
            String query1 = "SELECT TOP 1 * FROM Admin ORDER BY AdminId DESC;";
            ResultSet rs = st.executeQuery(query1);
            int lastId = 0;

               if (rs.next()) //not empty
                {
                        lastId = rs.getInt(2);
                }
                else //is empty
                {
                        lastId = 0;
                }

                return lastId;
                
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Pet insertion");
                e.printStackTrace();
        }
         
         return 0;
    }

    public void UpdateAdminStatus(int AdminId)
    {
        try
        {
                st = con.createStatement();
                String query1 = "UPDATE Admin SET Status = 'Approved' WHERE AdminId = " + AdminId;
                st.executeUpdate(query1);
                System.out.println("Admin Succesfully Approved");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Admin Approval");
                e.printStackTrace();
        }
    }
    
    public void DeleteAdmin(int AdminId,String UserId)
    {
        try
        {
                DB.DeleteUser(UserId);
                st = con.createStatement();
                String query1 = "Delete From Admin WHERE AdminId = " + AdminId;
                st.executeUpdate(query1);
                System.out.println("Admin Succesfully Deletion");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Admin Deletion");
                e.printStackTrace();
        }
    }
    
    public ArrayList<Admin> GetAdmin()
    {
        try
        {
                st = con.createStatement();
                String query1 = "Select * From Admin";
                ResultSet rs = st.executeQuery(query1);
                
                ArrayList<Admin> Ad = new ArrayList<>();
                
                while(rs.next())
                {
                    st = con.createStatement();
                    query1 = "Select * From SystemUser Where UserID = " + rs.getInt(1) + "";
                    System.out.println(rs.getInt(1));
                    ResultSet rs1 = st.executeQuery(query1);
                    
                    if (rs1.next())
                     {
                       
                     }
                     
                    Admin Ad1 = new Admin(rs1.getString(2),rs1.getString(3),rs1.getString(4),rs1.getString(5),rs1.getInt(6),rs1.getString(7),rs1.getDate(8).toLocalDate(),rs.getInt("AdminId"),rs.getString(3));
                    Ad.add(Ad1);
                    
                }
                
                return Ad;
                
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in User Reading");
                e.printStackTrace();
        }
        
        return null;
         
    }
    
    public void WriteVet(Veterinarian Owner)
    {
        try
        {       int UserId = GetLastUser();
                st = con.createStatement();
                String query1 = "INSERT INTO Veterinarian (UserId,VetId,LiscenseNumber, Status) " +
                                "VALUES ('" + UserId + "', '" + Owner.getVetId()+ "', '" + Owner.getLicenseNumber() + "', '" + Owner.getStatus() + "')";
                st.executeUpdate(query1);
                
                int Id = GetLastVetId();
                
                for (String Special : Owner.getSpecialization())
                {
                    st = con.createStatement();
                    query1 = "INSERT INTO VetSpecialization (VetId,Specialization) " +
                                "VALUES ('" + Id + "', '" + Special + "')";
                    st.executeUpdate(query1);
                
                }
                
                System.out.println("Vet Succesfully inserted");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Vet insertion");
                e.printStackTrace();
        }
    }
    
    public int GetLastVetId()
    {
        try
        {
            st = con.createStatement();
            String query1 = "SELECT TOP 1 * FROM Veterinarian ORDER BY VetId DESC;";
            ResultSet rs = st.executeQuery(query1);
            int lastId = 0;

               if (rs.next()) //not empty
                {
                        lastId = rs.getInt(2);
                }
                else //is empty
                {
                        lastId = 0;
                }

                return lastId;
                
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Last Vet Id");
                e.printStackTrace();
        }
         
         return 0;
    }
    
    public void UpdateVetStatus(int VetId)
    {
        try
        {
                st = con.createStatement();
                String query1 = "UPDATE Veterinarian SET Status = 'Approved' WHERE VetId = " + VetId;
                st.executeUpdate(query1);
                System.out.println("Vet Succesfully Approved");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Vet Approval");
                e.printStackTrace();
        }
    }
    
    public void DeleteVet(int VetId,String UserId)
    {
        try
        {   
                DB.DeleteUser(UserId);
                st = con.createStatement();
                String query1 = "Delete From Veterinarian WHERE VetId = " + VetId;
                st.executeUpdate(query1);
                System.out.println("Veterinarian Succesfully Deletion");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Veterinarian Deletion");
                e.printStackTrace();
        }
    }
    
    public ArrayList<Veterinarian> GetVeterinarian()
    {
        try
        {
            Statement st1 = con.createStatement();
            String query1 = "Select * From SystemUser "
                    + "Join Veterinarian on SystemUser.UserId = Veterinarian.UserId";
            ResultSet rs = st1.executeQuery(query1);

            ArrayList<Veterinarian> ArrayVet = new ArrayList<>();

            while(rs.next())
            {
                Statement st2 = con.createStatement();
                query1 = "Select * From VetSpecialization Where VetId = " + rs.getInt(10) + "";
                ResultSet rs1 = st2.executeQuery(query1);
                ArrayList<String> Spec = new ArrayList<>();

                while(rs1.next())
                {
                    Spec.add(rs1.getString(3));
                }

                Veterinarian Vet = new Veterinarian(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getDate(8).toLocalDate(),rs.getString(11),Spec,rs.getString(12),rs.getInt("VetId"));
               
                ArrayVet.add(Vet);

            }

            for(Veterinarian Vet : ArrayVet)
            {
              ArrayList<FeedBack> NewFeed = DB.GetFeedBack(Vet.getVetId());
              Vet.setListFeedBack(NewFeed);
            }

            System.out.println("Veterinarian Successfully Fetched");
                
            return ArrayVet;
                
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Get Veterinarian");
                e.printStackTrace();
        }
        
        return null;
    }
    
    public void WritePet(Pet P1,int PetOwnerId)
    {
        try
        {
            st = con.createStatement();
            String query1 = "INSERT INTO Pet (PetId,PetOwnerID,OwnerName, Name, Breed, Species, Age, DOB) " +
                            "VALUES ('" + P1.getPetId() +"','" + PetOwnerId + "', '" + P1.getOwnerName() + "','" + P1.getName() + "', '" +
                            P1.getBreed() + "', '" + P1.getSpecies() + "', " + P1.getAge() + ", '" +
                            P1.getDOB() + "')";
            st.executeUpdate(query1);
            System.out.println("Pet Succesfully inserted");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Pet insertion");
                e.printStackTrace();
        }
    }
    
    public int GetLastPetId()
    {
         try
        {
                st = con.createStatement();
                String query1 = "SELECT TOP 1 * FROM Pet ORDER BY PetId DESC;";
                ResultSet rs = st.executeQuery(query1);
                int lastId = 0;
                
                   if (rs.next()) //not empty
                    {
                            lastId = rs.getInt(1);
                    }
                    else //is empty
                    {
                            lastId = 0;
                    }

                return lastId;
                
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Last Pet Id");
                e.printStackTrace();
        }
         
         return 0;
    }
    
    public void UpdatePet(Pet pet)
    {
        try
        {
                st = con.createStatement();
                String query1 = "Update Pet Set Name = '" + pet.getName() + "', Breed = '" + pet.getBreed() + "', Species = '" 
                + pet.getSpecies() + "', Age = " + pet.getAge() + ", DOB = '" + pet.getDOB() + "' Where PetId = " + pet.getPetId();
                st.executeUpdate(query1);
                System.out.println("Pet Succesfully Updated");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Pet Updated");
                e.printStackTrace();
        }
    }
    
    public void DeletePet(int PetId)
    {
        try
        {
                st = con.createStatement();
                String query1 = "Delete From Pet Where PetId = " + PetId;
                st.executeUpdate(query1);
                System.out.println("Pet Succesfully Deleted");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Pet Deletion");
                e.printStackTrace();
        }
    }
    
    public ArrayList<Pet> GetPet() //Medical History
    {
        try
        {
            Statement st1 = con.createStatement();
            String query1 = "Select * From Pet";
            ResultSet rs = st1.executeQuery(query1);

            ArrayList<Pet> ArrayPet = new ArrayList<>();

            while(rs.next())
            {
                Pet NewPet = new Pet(rs.getInt("PetId"),rs.getInt("PetOwnerID"),rs.getString("Name"),rs.getString("Breed"),rs.getString("Species"),rs.getInt("Age"),rs.getDate("DOB").toLocalDate());
                NewPet.setOwnerName(rs.getString("OwnerName"));
                NewPet.setListTreatment(DB.GetTreatmentPlan(rs.getInt("PetId")));
                NewPet.setListPrescription(DB.GetPrescription(rs.getInt("PetId")));
                NewPet.setListDayCare(DB.GetDayCare(rs.getInt("PetId")));
                NewPet.setListMedical(DB.GetMedicalHistory(rs.getInt("PetId")));
                ArrayPet.add(NewPet);
            }

            System.out.println("Pet Successfully Fetched");
                
            return ArrayPet;
                
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Get Pet");
                e.printStackTrace();
        }
        
        return null;
    }
    
    public void WriteTreatmentPlan(TreatmentPlan T1)
    {
          try
        {
                st = con.createStatement();
                String query1 = "INSERT INTO TreatmentPlan (PlanId, PetId, VetId, Status) " +
                                "VALUES ('" + T1.getPlanId() + "', '" + T1.getPetId() + "', '" +
                                T1.getVet().getVetId() + "', '" + T1.getStatus() + "')";
                st.executeUpdate(query1);
                
                for (String Deat : T1.getDetails())
                {
                    query1 = "INSERT INTO TreatDetails (PlanId, Details) " +
                                    "VALUES ('" + T1.getPlanId() + "', '" + Deat + "')";
                    st.executeUpdate(query1);
                }
                
                for (String Note : T1.getNotes())
                {
                    query1 = "INSERT INTO TreatNotes (PlanId, Notes) " +
                                    "VALUES ('" + T1.getPlanId() + "', '" + Note + "')";
                    st.executeUpdate(query1);
                }
                
                System.out.println("TreatmentPlan Succesfully inserted");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in TreatmentPlan insertion");
                e.printStackTrace();
        }
    }
         
    public int GetLastTreatmentPlanId()
    {
         try
        {
                st = con.createStatement();
                String query1 = "SELECT TOP 1 * FROM TreatmentPlan ORDER BY PlanId DESC;";
                ResultSet rs = st.executeQuery(query1);
                int lastId = 0;
                
                   if (rs.next()) //not empty
                    {
                            lastId = rs.getInt(1);
                    }
                    else //is empty
                    {
                            lastId = 0;
                    }

                return lastId;
                
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Last Treatment Id");
                e.printStackTrace();
        }
         
         return 0;
    }
    
    public void UpdateTreatmentPlanStatus(int PlanId)
    {
        try
        {
                st = con.createStatement();
                String query1 = "UPDATE TreatmentPlan SET Status = 'Completed' WHERE PlanId = " + PlanId;
                st.executeUpdate(query1);
                System.out.println("TreatmentPlan Succesfully Completed");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in TreatmentPlan Completion");
                e.printStackTrace();
        }
    }
    
    public void RemoveTreatmentPlan(int PlanId)
    {
        try
        {
                st = con.createStatement();
                String query1 = "Delete From TreatmentPlan WHERE PlanId = " + PlanId;
                st.executeUpdate(query1);
                System.out.println("TreatmentPlan Succesfully Deleted");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in TreatmentPlan Deletion");
                e.printStackTrace();
        }
    }
    
    public ArrayList<TreatmentPlan> GetTreatmentPlan(int PetId)
    {
          try
        { 
                st = con.createStatement();
                String query1 = "Select * From TreatmentPlan Where PetId = " + PetId + "";
                ResultSet rs = st.executeQuery(query1);
                ArrayList<TreatmentPlan> ArrayTreat = new ArrayList<>();
               
                while(rs.next())
                {
                    Statement st2 = con.createStatement();
                    query1 = "Select * From TreatDetails Where PlanId = " + rs.getInt("PlanId") + "";
                    ResultSet rs1 = st2.executeQuery(query1);
                    
                    ArrayList<String> Details = new ArrayList<>();
                    
                    while(rs1.next())
                    {
                        Details.add(rs1.getString("Details"));
                    }
                    
                    Statement st1 = con.createStatement();
                    query1 = "Select * From TreatNotes Where PlanId = " + rs.getInt("PlanId") + "";
                    ResultSet rs2 = st1.executeQuery(query1);
                    
                    ArrayList<String> Notes = new ArrayList<>();
                    
                    while(rs2.next())
                    {
                        Notes.add(rs2.getString("Notes"));
                    }
                    
                    Veterinarian Vet = null;
                    
                    for(Veterinarian Vet1 : DB.GetVeterinarian())
                    {
                        if(Vet1.getVetId() == rs.getInt("VetId"))
                        {
                            Vet = Vet1;
                        }
                    }
                    
                    TreatmentPlan NewPlan = new TreatmentPlan(rs.getInt("PlanId"),Vet,rs.getInt("PetId"),Details,Notes);
                    NewPlan.setStatus(rs.getString("Status"));
                    ArrayTreat.add(NewPlan);
                }
                
                System.out.println("TreatmentPlan Succesfully Fetched");
                
                return ArrayTreat;
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Get TreatmentPlan");
                e.printStackTrace();
        }
          
        return null;
    }
    
    public void WritePrescription(Prescription P1)
    {
        try
        {
                st = con.createStatement();
                String query1 = "INSERT INTO Prescription (PrescriptionID, PetId, VetId, Status) " +
                                "VALUES ('" + P1.getPrescripId() + "', '" + P1.getPetId() + "', '" +
                                P1.getVet().getVetId() + "', '" + P1.getStatus() + "')";
                st.executeUpdate(query1);
                
                for (String Note : P1.getInstructions())
                {
                    query1 = "INSERT INTO PresInstructions (PrescriptionID, Instruction) " +
                                    "VALUES ('" + P1.getPrescripId() + "', '" + Note + "')";
                    st.executeUpdate(query1);
                }
                
                for (Medicine Med : P1.getListMedicine())
                {
                    query1 = "INSERT INTO PresMed (PrescriptionID, MedId,Dosage) " +
                                    "VALUES ('" + P1.getPrescripId() + "', '" + Med.getMedicineId() +"','" + Med.getDosage() + "')";
                    st.executeUpdate(query1);
                }
                
                
                System.out.println("Prescription Succesfully inserted");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Prescription insertion");
                e.printStackTrace();
        }
    }
         
    public int GetLastPrescriptionId()
    {
         try
        {
                st = con.createStatement();
                String query1 = "SELECT TOP 1 * FROM Prescription ORDER BY PrescriptionId DESC;";
                ResultSet rs = st.executeQuery(query1);
                int lastId = 0;
                
                   if (rs.next()) //not empty
                    {
                            lastId = rs.getInt("PrescriptionId");
                    }
                    else //is empty
                    {
                            lastId = 0;
                    }

                return lastId;
                
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Last Treatment Id");
                e.printStackTrace();
        }
         
         return 0;
    }
    
    public void UpdatePrescriptionStatus(int PrescriptionId)
    {
        try
        {
                st = con.createStatement();
                String query1 = "UPDATE Prescription SET Status = 'Completed' WHERE PrescriptionID = " + PrescriptionId;
                st.executeUpdate(query1);
                System.out.println("{Prescription Succesfully Completed");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Prescription Completition");
                e.printStackTrace();
        }
    }
    
    public void RemovePrescription(int PrescriptionId)
    {
        try
        {
                st = con.createStatement();
                String query1 = "Delete From Prescription WHERE PrescriptionId = " + PrescriptionId;
                st.executeUpdate(query1);
                System.out.println("Prescription Succesfully Deleted");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Prescription Deletion");
                e.printStackTrace();
        }
    }
    
    public ArrayList<Prescription> GetPrescription(int PetId)
    {
        try
        { 
                Statement st1 = con.createStatement();
                String query1 = "Select * From Prescription Where PetId = " + PetId + "";
                ResultSet rs = st1.executeQuery(query1);
                ArrayList<Prescription> ArrayTreat = new ArrayList<>();
               
                while(rs.next())
                {
                    
                    Statement st3 = con.createStatement();
                    query1 = "Select * From PresInstructions Where PrescriptionID = " + rs.getInt("PrescriptionId") + "";
                    ResultSet rs1 = st3.executeQuery(query1);
                    
                    ArrayList<String> Inst = new ArrayList<>();
                    
                    while(rs1.next())
                    {
                        Inst.add(rs1.getString("Instruction"));
                    }
                    
                    Statement st4 = con.createStatement();
                    query1 = "Select Medicine.*,PresMed.Dosage From PresMed Join Medicine on PresMed.MedId = Medicine.MedId "
                            + "Where PrescriptionID = " + rs.getInt("PrescriptionId") + "";
                    rs1 = st4.executeQuery(query1);
                    
                    ArrayList<Medicine> Med = new ArrayList<>();
                    
                    while(rs1.next())
                    {
                        Medicine Med1 = new Medicine(rs1.getInt("MedId"),rs1.getString("Name"),rs1.getString("Type"),rs1.getString("Purpose"),0,rs1.getDouble("Price"));
                        Med1.setDosage(rs1.getInt("Dosage"));
                        Med.add(Med1);
                    }
                    
                    Veterinarian Vet = null;
                    
                    for(Veterinarian Vet1 : DB.GetVeterinarian())
                    {
                        if(Vet1.getVetId() == rs.getInt("VetId"))
                        {
                            Vet = Vet1;
                        }
                    }
                    
                    Prescription NewPlan = new Prescription(rs.getInt("PrescriptionId"),Inst,Med,Vet,PetId);
                    NewPlan.setStatus(rs.getString("Status"));
                    ArrayTreat.add(NewPlan);
                }
                
                System.out.println("Prescription Succesfully Fetched");
                
                return ArrayTreat;
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Get Prescription");
                e.printStackTrace();
        }
          
        return null;
    }
    
    public void WriteFeedBack(FeedBack Feed)
    {
        try
        {
                st = con.createStatement();
                String query1 = "INSERT INTO FeedBack (VetID,Rating, Comment,FDate) " +
                                "VALUES ('" + Feed.getVetId() + "', '" + Feed.getRating() + "', '" + Feed.getComments() + "', '" + Feed.getDate() + "')";
                st.executeUpdate(query1);
                System.out.println("Feedback Succesfully inserted");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in FeedBack Insertion");
                e.printStackTrace();
        }
    }
    
    public ArrayList<FeedBack> GetFeedBack(int VetId)
    {
        try
        {
                st = con.createStatement();
                String query1 = "Select * From Feedback Where VetId = " + VetId + "";
                ResultSet rs = st.executeQuery(query1);
                ArrayList<FeedBack> ArrayFeed = new ArrayList<>();
                
                while(rs.next())
                {
                    FeedBack Back = new FeedBack(rs.getInt(2),rs.getDouble(3),rs.getString(4),rs.getDate("FDate").toLocalDate());
                    ArrayFeed.add(Back);
                }
                
                System.out.println("Feedback Succesfully Fetched");
                
                return ArrayFeed;
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Get FeedBack");
                e.printStackTrace();
        }
         
        return null;
    }
    
    public ArrayList<Medicine> GetMedicine()
    {
        try
        {
                st = con.createStatement();
                String query1 = "Select * From Medicine ";
                ResultSet rs = st.executeQuery(query1);
                ArrayList<Medicine> ArrayMed = new ArrayList<>();
                
                while(rs.next())
                {
                    Medicine Med = new Medicine(rs.getInt("MedId"),rs.getString("Name"),rs.getString("Type"),rs.getString("Purpose"),0,rs.getDouble("Price"));
                    ArrayMed.add(Med);
                }
                
                System.out.println("Medicine Succesfully Fetched");
                
                return ArrayMed;
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Get Medicine");
                e.printStackTrace();
        }
         
        return null;
    }
    
    public ArrayList<Pharmacy> GetPharmacy()
    {
        try
        {
                st = con.createStatement();
                String query1 = "Select * From Pharmacy ";
                ResultSet rs = st.executeQuery(query1);
                ArrayList<Pharmacy> ArrayPhar = new ArrayList<>();
                
                while(rs.next())
                {
                    Statement st1 = con.createStatement();
                    query1 = "Select * From PharMedicine Where PharId = " + rs.getString("PharId");
                    ResultSet rs1 = st1.executeQuery(query1);
                    ArrayList<Medicine> ListMed = new ArrayList<>();
                    
                    while(rs1.next())
                    {
                        for(Medicine Med : DB.GetMedicine())
                        {
                            if(Med.getMedicineId() == rs1.getInt("MedId"))
                            {
                               Med.setQuantity(rs1.getInt("Quantity"));
                               ListMed.add(Med);
                            }
                        }
                    }
                    
                    Pharmacy NewPhar = new Pharmacy(rs.getInt("PharId"),rs.getString("PharName"),rs.getString("Location"),rs.getString("Contact"),rs.getInt("MaxStock"),ListMed);
                    ArrayPhar.add(NewPhar);
                }
                
                System.out.println("Pharmacy Succesfully Fetched");
                
                return ArrayPhar;
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Get Pharmacy");
                e.printStackTrace();
        }
         
        return null;
    }
    
    public void UpdatePharMedicineQuantity(int Pharid,Medicine PMed)
    {
        try
        {
                st = con.createStatement();
                String query1 = "Update PharMedicine Set Quantity = " + PMed.getQuantity() + " Where PharId = " + Pharid
                                + "And MedId = " + PMed.getMedicineId();
                st.executeUpdate(query1);
                System.out.println("Medicine Succesfully Updated");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Medicine Updation");
                e.printStackTrace();
        }
    }
    
    public void WriteDayCare(DayCare D1)
    {
        try
        {
            st = con.createStatement();
            String query1 = "INSERT INTO DayCare (DayCareId, PetId, StartDate, EndDate,Status,Bill) " +
                            "VALUES ('" + D1.getDaycareId() + "', '" + D1.getPetId() + "', '" +
                            D1.getStartDate() + "', '" + D1.getEndDate() + "', '" + D1.getStatus() + "', '" + D1.getBill()+ "')";
            st.executeUpdate(query1);
            
            for(String Spec : D1.getSpecial())
            {
                Statement st1 = con.createStatement();
                query1 = "INSERT INTO DayCareSpecial (DayCareId,SpecialInstruction) " +
                         "VALUES ('" + D1.getDaycareId() + "', '" + Spec + "')";
                st1.executeUpdate(query1);
            }
            
            System.out.println("DayCare Succesfully inserted");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in DayCare insertion");
                e.printStackTrace();
        }
    }
         
    public int GetLastDayCareId()
    {
        try
        {
                st = con.createStatement();
                String query1 = "SELECT TOP 1 * FROM DayCare ORDER BY DaycareId DESC;";
                ResultSet rs = st.executeQuery(query1);
                int lastId = 0;
                
                   if (rs.next()) //not empty
                    {
                            lastId = rs.getInt("DayCareId");
                    }
                    else //is empty
                    {
                            lastId = 0;
                    }

                return lastId;
                
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Last DayCare Id");
                e.printStackTrace();
        }
         
         return 0;
    }
    
    public void UpdateDayCareStatus(int DayCareId,String Status)
    {
        try
        {
                st = con.createStatement();
                String query1 = "UPDATE DayCare SET Status = '" + Status + "' WHERE DayCareId = " + DayCareId;
                st.executeUpdate(query1);
                System.out.println("{DayCare Succesfully Updation");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in DayCare Updation");
                e.printStackTrace();
        }
    }
    
    public ArrayList<DayCare> GetDayCare(int PetId)
    {
        try
        { 
                Statement st1 = con.createStatement();
                String query1 = "Select * From DayCare Where PetId = " + PetId + "";
                ResultSet rs = st1.executeQuery(query1);
                ArrayList<DayCare> ArrayDay = new ArrayList<>();
               
                while(rs.next())
                {
                    
                    Statement st2 = con.createStatement();
                    query1 = "Select * From DayCareSpecial Where DayCareID = " + rs.getInt("DayCareId") + "";
                    ResultSet rs1 = st2.executeQuery(query1);
                    
                    ArrayList<String> Special = new ArrayList<>();
                    
                    while(rs1.next())
                    {
                        Special.add(rs1.getString("SpecialInstruction"));
                    }
                    
                    DayCare NewDay = new DayCare(rs.getInt("DayCareId"),PetId,rs.getDate("StartDate").toLocalDate(),rs.getDate("EndDate").toLocalDate(),Special);
                    NewDay.setStatus(rs.getString("Status"));
                    NewDay.setBill(rs.getInt("Bill"));
                    ArrayDay.add(NewDay);
                }
                
                System.out.println("DayCare Succesfully Fetched");
                
                return ArrayDay;
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Get DayCare");
                e.printStackTrace();
        }
          
        return null;
    }
    
    public void MakeAppointmentSlots()
    {
        try
        { 
            
            Statement st1 = con.createStatement();
            String query1 = "Select * From AppointmentSlots ";
            ResultSet rs = st1.executeQuery(query1);
            
            while(rs.next())
            {
                if(rs.getDate("AppointDate").toLocalDate().isBefore(LocalDate.now()))
                {
                    Statement st2 = con.createStatement();
                    String query2 = "Delete From AppointmentSlots Where AppointDate = '" + rs.getDate("AppointDate").toLocalDate() + "' AND isAvail = 1";
                    st2.executeUpdate(query2);
                }
            }
            
            Statement st3 = con.createStatement();
            query1 = "Select * From AppointmentSlots ";
            ResultSet rs1 = st3.executeQuery(query1);
            
            ArrayList<LocalDate> Date = new ArrayList<>();
            LocalDate Datetobe = LocalDate.now();
            Date.add(Datetobe);
            
            for(int i = 0;i<6;i++)
            {
                Datetobe = Datetobe.plusDays(1);
                Date.add(Datetobe);
            }
            
            if (!rs1.next()) 
            {
                for (LocalDate date : Date) 
                {
                    for (int hour = 9; hour <= 15; hour++) 
                    {
                        String timeSlot = String.format("%02d:00 %s", hour % 12 == 0 ? 12 : hour % 12, hour < 12 ? "AM" : "PM");

                        String query2 = "INSERT INTO AppointmentSlots (AppointDate, StartTime, IsAvail) " +
                                        "VALUES ('" + date + "', '" + timeSlot + "', 1)";

                        Statement st4 = con.createStatement();
                        st4.executeUpdate(query2);
                    }
                }
            }
            else
            {
                for(LocalDate Dateto : Date)
                { 
                        st3 = con.createStatement();
                        query1 = "Select * From AppointmentSlots ";
                        rs1 = st3.executeQuery(query1);
                        
                        while(rs1.next())
                        {
                            if(rs1.getDate("AppointDate").toLocalDate().equals(Dateto) || rs1.getDate("AppointDate").toLocalDate().isBefore(Dateto))
                            {
                                break;
                            }
                            else
                            {
                                Statement st2 = con.createStatement();

                                for (int hour = 9; hour <= 15; hour++) 
                                {
                                    String timeSlot = String.format("%02d:00 %s", hour % 12 == 0 ? 12 : hour % 12, hour < 12 ? "AM" : "PM");

                                    String query2 = "INSERT INTO AppointmentSlots (AppointDate, StartTime, IsAvail) " +
                                                "VALUES ('" + Dateto + "', '" + timeSlot + "', 1)";

                                    st2.executeUpdate(query2);
                                }

                            }
                        }
                   }
                }

        }    
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Get AppointmentSlots");
                e.printStackTrace();
        }
          
    }
    
    public void UpdateSlot(int SlotId)
    {
        try
        {
                st = con.createStatement();
                String query1 = "UPDATE AppointmentSlots SET IsAvail = 0 WHERE SlotId = " + SlotId;
                st.executeUpdate(query1);
                System.out.println("{Slot Succesfully Updated");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Slot Updation");
                e.printStackTrace();
        }
    }
    
    public ArrayList<Slot> GetSlot()
    {
        try
        { 
                Statement st1 = con.createStatement();
                String query1 = "Select * From AppointmentSlots where IsAvail = 1";
                ResultSet rs = st1.executeQuery(query1);
                ArrayList<Slot> ArraySlot = new ArrayList<>();
               
                while(rs.next())
                {
                   Slot newSlot = new Slot(rs.getInt("SlotId"),rs.getDate("AppointDate").toLocalDate(),rs.getString("StartTime"),rs.getInt("IsAvail"));
                   ArraySlot.add(newSlot);
                }
                
                System.out.println("Slots Succesfully Fetched");
                
                return ArraySlot;
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Get Slots");
                e.printStackTrace();
        }
          
        return null;
    }
    
    public void WriteAppointment(Appointments A1)
    {
       try
        {
            Statement st1 = con.createStatement();
            String query1 = "INSERT INTO Appointment (AppointmentID,PetOwnerID, VetID, PetID, SlotId, Purpose, Status) "
                 + "VALUES (" + A1.getAppointmentId() + ", " 
                 + A1.getPetOwnerId() + ","
                 + A1.getVet().getVetId() + ", "
                 + A1.getToPet().getPetId() + ", "
                 + A1.getSlotId() + ", '"
                 + A1.getPurpose() + "', '"
                 + A1.getStatus() + "');";
            st1.executeUpdate(query1);

            DB.UpdateSlot(A1.getSlotId());

            System.out.println("{Appointment Succesfully inserted");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Appointment inserted");
                e.printStackTrace();
        }
    }
    
    public int GetLastAppointmentId()
    {
        try
        {
                st = con.createStatement();
                String query1 = "SELECT TOP 1 * FROM Appointment ORDER BY AppointmentId DESC;";
                ResultSet rs = st.executeQuery(query1);
                int lastId = 0;
                
                   if (rs.next()) //not empty
                    {
                            lastId = rs.getInt("AppointmentId");
                    }
                    else //is empty
                    {
                            lastId = 0;
                    }

                return lastId;
                
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Last Appointment Id");
                e.printStackTrace();
        }
         
         return 0;
    }
    
    public void UpdateAppointment(int AppointId,String Status)
    {
         try
        {
                st = con.createStatement();
                String query1 = "Update Appointment Set Status = '" + Status + "' Where AppointmentId = " + AppointId;
                st.executeUpdate(query1);
                
                System.out.println("Appointment Succesfully Updated");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Appointment Updation");
                e.printStackTrace();
        }
    }
    
    public ArrayList<Appointments> GetAppointment()
    {
        try
        { 
                Statement st1 = con.createStatement();
                String query1 = "Select * From Appointment Join AppointmentSlots on AppointmentSlots.SlotId = Appointment.SlotId";
                ResultSet rs = st1.executeQuery(query1);
                ArrayList<Appointments> ArrayAppoint = new ArrayList<>();
               
                while(rs.next())
                {
                    Pet Pet1 = null;
                    for(Pet P : DB.GetPet())
                    {
                        if(P.getPetId() == rs.getInt("PetId"))
                        {
                            Pet1 = P;
                            break;
                        }
                    }
                    
                    Veterinarian Vet = null;
                    for(Veterinarian V : DB.GetVeterinarian())
                    {
                        if(V.getVetId() == rs.getInt("VetId"))
                        {
                            Vet = V;
                            break;
                        }
                    }
                    
                    Appointments App = new Appointments(rs.getInt("AppointmentId"),rs.getInt("PetOwnerID"),rs.getInt("SlotId"),Pet1,Vet,rs.getDate("AppointDate").toLocalDate(),rs.getString("Purpose"),rs.getString("StartTime"));
                    App.setStatus(rs.getString("Status"));
                    ArrayAppoint.add(App);
                }
                
                System.out.println("Appointment Succesfully Fetched");
                
                return ArrayAppoint;
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Get Appointment");
                e.printStackTrace();
        }
          
        return null;
    }
    
    public void WriteCart(Cart C)
    {
       
        try
        {
            Statement st1 = con.createStatement();
            String query1 = "INSERT INTO Cart (CartId,PetOwnerID) "
                 + "VALUES (" + C.getCartId() + ", '" 
                 + C.getPetOwnerId() + "');";
            st1.executeUpdate(query1);

            System.out.println("Cart Succesfully inserted");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Cart inserted");
                e.printStackTrace();
        }
    }
    
    public int GetLastCartId()
    {
        try
        {
                st = con.createStatement();
                String query1 = "SELECT TOP 1 * FROM Cart ORDER BY CartId DESC;";
                ResultSet rs = st.executeQuery(query1);
                int lastId = 0;
                
                   if (rs.next()) //not empty
                    {
                            lastId = rs.getInt("Cartid");
                    }
                    else //is empty
                    {
                            lastId = 0;
                    }

                return lastId;
                
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Last Appointment Id");
                e.printStackTrace();
        }
         
         return 0;
    }
    
    public Cart GetCart(int OwnerId)
    {
        try
        {
            Statement st1 = con.createStatement();
            String query1 = "Select * From Cart Where PetOwnerId = " + OwnerId + "";
            ResultSet rs = st1.executeQuery(query1);
            
           if(rs.next())
           {

                Cart toCart = new Cart(rs.getInt("CartId"),rs.getInt("PetOwnerId"));

                Statement st2 = con.createStatement();
                String query2 = "Select * From CartMed Join Medicine on Medicine.MedID = CartMed.MedId " +
                        "Where CartId = " + rs.getString("CartId");
                ResultSet rs2 = st2.executeQuery(query2);
                ArrayList<Medicine> CarMed = new ArrayList<>();

                while(rs2.next())
                {
                    Medicine Med = new Medicine(rs2.getInt("MedId"),rs2.getString("Name"),rs2.getString("Type"),rs2.getString("Purpose"),rs2.getInt("Quantity"),rs2.getDouble("Price"));
                    CarMed.add(Med);
                }
                toCart.setBuyMed(CarMed);

                System.out.println("Cart Succesfully Fetched");

                return toCart;
           }
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Get Cart");
                e.printStackTrace();
        }
        
        return null;
    }
    
    public void AddMeds(int CartId,Medicine Med)
    {
        try
        {
            Statement st1 = con.createStatement();
            String query1 = "INSERT INTO CartMed (CartId,MedId,Quantity) "
                 + "VALUES (' " + CartId + " ',' " 
                 + Med.getMedicineId() + "','" + Med.getQuantity() +  "');";
            st1.executeUpdate(query1);

            System.out.println("MedinCart Succesfully inserted");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in MedinCart inserted");
                e.printStackTrace();
        }
    }
    
    public void EmptyCart(int CartId)
    {
        try
        {
            Statement st1 = con.createStatement();
            String query1 = "Delete From CartMed Where CartId = " + CartId + "";
            st1.executeUpdate(query1);

            System.out.println("Cart Succesfully Deletion");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Cart Deletion");
                e.printStackTrace();
        }
    }
    
    public void RemoveMed(int CartId,int MedId)
    {
        try
        {
            Statement st1 = con.createStatement();
            String query1 = "Delete From CartMed Where CartId = " + CartId + " AND MedID = " + MedId + "";
            st1.executeUpdate(query1);

            System.out.println("removeMedCart Succesfully Deletion");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in removeMedCart Deletion");
                e.printStackTrace();
        }
    }
    
    public void UpdateQuan(int CartId,Medicine Med)
    {
        try
        {
            Statement st1 = con.createStatement();
            String query1 = "Update CartMed Set Quantity = " + Med.getQuantity() + 
                            "Where CartId = " + CartId + "AND MedID = " + Med.getMedicineId() + "";
            st1.executeUpdate(query1);

            System.out.println("UpdateQuan Succesfully inserted");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in UpdateQuan inserted");
                e.printStackTrace();
        }
    }
    
    public void WriteMedicalHistory(MedicalHistory M1)
    {
       try
        {
            
           Statement st1 = con.createStatement();
           String query1 = "INSERT INTO MedicalHistory (MedicalId, PetID, MDate) "
                        + "VALUES (" 
                        + M1.getMedicalId() + ", " 
                        + M1.getPetId() + ",'"
                        + M1.getDate() + "');";
            st1.executeUpdate(query1);
            
            for (String diagnosis : M1.getDiagnosis()) 
            {
                String query2 = "INSERT INTO MedicalHistoryDiag (MedicalId, Diagnosis) "
                               + "VALUES ("
                               + M1.getMedicalId() + ", '"
                               + diagnosis + "');";
                st1.executeUpdate(query2);
            }

            for (String symptom : M1.getSymptoms())
            {
                String query3 = "INSERT INTO MedicalHistorySymp (MedicalId, Symptom) "
                               + "VALUES ("
                               + M1.getMedicalId() + ", '"
                               + symptom + "');";
                st1.executeUpdate(query3);
            }
            for (String treatment : M1.getTreatmentDetails())
            {
                String query4 = "INSERT INTO MedicalHistoryTreat (MedicalId, Treatment) "
                               + "VALUES ("
                               + M1.getMedicalId() + ", '"
                               + treatment + "');";
                st1.executeUpdate(query4);
            }
            System.out.println("{Medical Succesfully inserted");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Medical inserted");
                e.printStackTrace();
        }
    }
    
    public void RemoveMedicalHistory(int MedicalId)
    {
        try
        {
                st = con.createStatement();
                String query1 = "Delete From MedicalHistory WHERE MedicalId = " + MedicalId;
                st.executeUpdate(query1);
                System.out.println("MedicalHistory Succesfully Deleted");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in MedicalHistory Deletion");
                e.printStackTrace();
        }
    }
    
    public int GetLastMedicalHistoryId()
    {
        try
        {
                st = con.createStatement();
                String query1 = "SELECT TOP 1 * FROM MedicalHistory ORDER BY MedicalId DESC;";
                ResultSet rs = st.executeQuery(query1);
                int lastId = 0;
                
                   if (rs.next()) //not empty
                    {
                            lastId = rs.getInt("MedicalId");
                    }
                    else //is empty
                    {
                            lastId = 0;
                    }

                return lastId;
                
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Last MedicalHistory Id");
                e.printStackTrace();
        }
         
         return 0;
    }
    
    public ArrayList<MedicalHistory> GetMedicalHistory(int Petid)
    {
        try
        {
            Statement st1 = con.createStatement();
            String query1 = "Select * From MedicalHistory Where PetId = " + Petid + "";
            ResultSet rs = st1.executeQuery(query1);
            ArrayList<MedicalHistory> ArrayMed = new ArrayList<>();
            
            while(rs.next())
            {
              Statement st2 = con.createStatement();
              String query2 = "Select * From MedicalHistoryDiag Where MedicalId = " + rs.getInt("Medicalid") + "";
              ResultSet rs1 = st2.executeQuery(query2);
              ArrayList<String> Diag = new ArrayList<>();
              
              while(rs1.next())
              {
                  Diag.add(rs1.getString("Diagnosis"));
              }
              
              Statement st3 = con.createStatement();
              String query3 = "Select * From MedicalHistorySymp Where MedicalId = " + rs.getInt("Medicalid") + "";
              ResultSet rs2 = st3.executeQuery(query3);
              ArrayList<String> Symp = new ArrayList<>();
              
              while(rs2.next())
              {
                  Symp.add(rs2.getString("Symptom"));
              }
              
              Statement st4 = con.createStatement();
              String query4 = "Select * From MedicalHistoryTreat Where MedicalId = " + rs.getInt("Medicalid") + "";
              ResultSet rs3 = st4.executeQuery(query4);
              ArrayList<String> Treatment = new ArrayList<>();
              
              while(rs3.next())
              {
                  Treatment.add(rs3.getString("Treatment"));
              }
              
              MedicalHistory NewMed = new MedicalHistory(rs.getInt("Medicalid"),rs.getInt("PetId"),Diag,Symp,Treatment,rs.getDate("MDate").toLocalDate());
              
              ArrayMed.add(NewMed);
            }
            
            System.out.println("Medical Succesfully Fetched");
            
            return ArrayMed;
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Get Medical");
                e.printStackTrace();
        }
        return null;
    }

    public void WriteMedicine(Medicine Med)
    {
        try 
        {
            st = con.createStatement();
            String query1 = "INSERT INTO Medicine (MedId, Name, Type, Purpose,Price) " +
                    "VALUES ('" + Med.getMedicineId() + "', '" + Med.getName() + "', '" +
                    Med.getType() + "', '" + Med.getPurpose() + "','" + Med.getPrice() + "')";
            st.executeUpdate(query1);
            
            System.out.println("Medicine Successfully Inserted");
            
        } 
        catch (SQLException ex)
        {
            System.out.println("Error in Medicine Insertion");
            ex.printStackTrace();
        }
        
        
    }
    
    public void DeleteMedicine(int MedId)
    {
         try
        {
            Statement st1 = con.createStatement();
            String query1 = "Delete From Medicine Where MedId = " + MedId;
            st1.executeUpdate(query1);
            
            System.out.println("Medicine Succesfully Deleted");
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Medicine Deleted");
                e.printStackTrace();
        }
    }
    
    public int GetLastMedicineId()
    {
        try
        {
                st = con.createStatement();
                String query1 = "SELECT TOP 1 * FROM Medicine ORDER BY MedId DESC;";
                ResultSet rs = st.executeQuery(query1);
                int lastId = 0;
                
                   if (rs.next()) //not empty
                    {
                            lastId = rs.getInt("MedId");
                    }
                    else //is empty
                    {
                            lastId = 0;
                    }

                return lastId;
                
        } 
        catch (SQLException e) 
        {
                // TODO Auto-generated catch block
                System.out.println("Error in Last Medicine Id");
                e.printStackTrace();
        }
         
        return 0;
    }
    
    public void WritePharMed(int PharId,Medicine Med)
    {
        
        try 
        {
            Statement st1 = con.createStatement();
            String query1 = "Insert into PharMedicine (PharId , MedId, Quantity) Values ('" + PharId + "','" + Med.getMedicineId() + "','" + Med.getQuantity() + "')";
            st1.executeUpdate(query1);
            
            System.out.println("PharMed Successfully Inserted");
            
        }
        catch (SQLException ex)
        {
            System.out.println("Error in PharMed Insertion");
            ex.printStackTrace();
        }
    }
    
}

