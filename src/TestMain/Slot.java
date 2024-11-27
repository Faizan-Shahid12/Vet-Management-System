/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestMain;

import java.time.LocalDate;

/**
 *
 * @author Kamran Shahid
 */
public class Slot 
{
    private int SlotId;
    private LocalDate Date;
    private String Time;
    private int IsAvail;

    public Slot(int SlotId, LocalDate Date, String Time, int IsAvail) {
        this.SlotId = SlotId;
        this.Date = Date;
        this.Time = Time;
        this.IsAvail = IsAvail;
    }

    public int getSlotId() {
        return SlotId;
    }

    public LocalDate getDate() {
        return Date;
    }

    public String getTime() {
        return Time;
    }

    public int isIsAvail() {
        return IsAvail;
    }
    
    
}
