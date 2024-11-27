package TestMain;

import java.time.LocalDate;


public class FeedBack
{
    private double rating;
    private String comments;
    private int VetId;
    private LocalDate Date;
    
    public FeedBack(int Vet1,double rate,String comm,LocalDate Date1)
    {
        VetId = Vet1;
        rating = rate;
        comments = comm;
        Date = Date1;
    }

    public double getRating()
    {
        return rating;
    }

    public void setRating(double rating) 
    {
        this.rating = rating;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String comments) 
    {
        this.comments = comments;
    }

    public int getVetId() 
    {
        return VetId;
    }

    public void setVetId(int VetId) 
    {
        this.VetId = VetId;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate Date) {
        this.Date = Date;
    }

    
}
