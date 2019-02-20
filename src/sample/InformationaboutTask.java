package sample;
import java.io.Serializable;
import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class InformationaboutTask implements Serializable {


    public String title;
    public String exp_time;
    public String priority;
    public String description;

    public InformationaboutTask()
    {
        this(null);
    }



    public InformationaboutTask(String title)
    {
        this.title = title;
        this.description = "";
        this.priority = "Low";
        this.exp_time = "dd.MM.yyyy";
    }

    public InformationaboutTask(String title, String description, String expDate, String priority) {
        this.title = title;
        this.description = description;
        this.exp_time = expDate;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }


    public String getExp_time() {
        return exp_time;
    }


    public void setExp_time(String exp_time) {
        this.exp_time = exp_time;
    }

    public String getPriority() {
        return priority;
    }

    public String getDesription() {
        return description;
    }

    public void setTitle(String title) {
        this.title=title;
    }

    public String getInformation(){
        String Information = "Date: "+ getExp_time() + "\nInformation: "+ getDesription();
        return Information;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setDesription(String desription) {
        this.description = desription;
    }
}
