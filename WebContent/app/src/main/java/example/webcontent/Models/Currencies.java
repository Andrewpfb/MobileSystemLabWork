package example.webcontent.Models;

import java.util.Date;

/**
 * Created by frost on 05.12.2017.
 */

public class Currencies {
    private int Cur_ID;
    private String Date;
    private String Cur_Abbreviation;
    private int Cur_Scale;
    private String Cur_Name;
    private Double Cur_OfficialRate;

    public Currencies(int cur_ID,String date,String cur_Abbreviation,int cur_Scale,String cur_Name,Double cur_OfficialRate){
        Cur_ID = cur_ID;
        Date = date;
        Cur_Abbreviation = cur_Abbreviation;
        Cur_Scale = cur_Scale;
        Cur_Name = cur_Name;
        Cur_OfficialRate = cur_OfficialRate;
    }

    public int getCur_ID() {
        return Cur_ID;
    }

    public void setCur_ID(int cur_ID) {
        Cur_ID = cur_ID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCur_Abbreviation() {
        return Cur_Abbreviation;
    }

    public void setCur_Abbreviation(String cur_Abbreviation) {
        Cur_Abbreviation = cur_Abbreviation;
    }

    public int getCur_Scale() {
        return Cur_Scale;
    }

    public void setCur_Scale(int cur_Scale) {
        Cur_Scale = cur_Scale;
    }

    public String getCur_Name() {
        return Cur_Name;
    }

    public void setCur_Name(String cur_Name) {
        Cur_Name = cur_Name;
    }

    public Double getCur_OfficialRate() {
        return Cur_OfficialRate;
    }

    public void setCur_OfficialRate(Double cur_OfficialRate) {
        Cur_OfficialRate = cur_OfficialRate;
    }

    @Override
    public String toString() {
        return "Name: "+ Cur_Name + " Rate: " + Cur_OfficialRate;
    }
}
