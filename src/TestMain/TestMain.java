package TestMain;


import Database.DBHandler;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TestMain 
{
 //public static void main(String[] args) throws ClassNotFoundException
   {
      // Pet P1 = new Pet(1,"HellO","husky","dog",7,LocalDate.parse("2004-02-21"));
       
     //  System.out.println(P1.getName());
       
       ArrayList<String> Deta = new ArrayList<>();
       ArrayList<String> Beta = new ArrayList<>();
       
       Deta.add("Deta");
       Beta.add("Beta");
     //  TreatmentPlan Plan = new TreatmentPlan(1,null,P1,Deta,Beta);
       
      // P1.AddTreatment(Plan);
    //   ArrayList<TreatmentPlan> plan1 = P1.getListTreatment();
       
     //  for(TreatmentPlan T : plan1)
      // {
     ////      System.out.println(T.getDetails().get(0));
       //}
       
       ArrayList<String> Spec = new ArrayList<>();
       Spec.add("KKK");
       Spec.add("FFF");
       //Veterinarian Vet = new Veterinarian("h","k","p",7,"q",LocalDate.parse("2005-07-01"),"f",Spec);
       
     //  Vet.CreateTreatmentPlan(P1, Deta, Beta);
       
//       ArrayList<TreatmentPlan> plan2 = P1.getListTreatment();
       
   //    for(TreatmentPlan T : plan2)
       {
       //    System.out.println(T.getDetails().get(0));
       //    System.out.println(T.getVet().getUsername());
    //       T.EditNote("Beta ", "Beta1");
       //    System.out.println(T.getNotes().get(0));
       }
       
         DBHandler DB = DBHandler.getInstance();
        // DB.WritePet(P1,1);
         
         Admin A = DB.GetAdmin().get(0); // Testing ke liye 1
         
         System.out.println(A.getName());
         
//         Veterinarian Vet = A.CreateVeterinarian("h","k","p","f",7,"q",LocalDate.parse("2005-07-01"),"f",Spec);
         
        // PetOwner Owner = A.CreatePetOwner("Hi1123", "hi1","HII", "MAle", 7, "OK@GMAIL121>COM", LocalDate.parse("2004-11-17"), "03300012");
         
//         Owner.AddPet("HellO21","German","Cat",73,LocalDate.parse("2004-02-21"));
         
     //    A.VerifyVet(Vet);
         
       
        // Vet.CreateTreatmentPlan(Owner.ListPet.get(0), Deta, Spec);
        // Vet.CreateTreatmentPlan(Owner.ListPet.get(0), Deta, Spec);
         
       //  ArrayList<TreatmentPlan> Plan = DB.GetTreatmentPlan(Owner.ListPet.get(0).getPetId());
         
       //  for(TreatmentPlan P : Plan)
         {
       //      System.out.print(P.getNotes().get(0));
         }
   }   
}
