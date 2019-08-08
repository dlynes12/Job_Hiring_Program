package MVC;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Interviewer extends User {
    private Map<String, ArrayList<Applicant>> applicantsList = new HashMap<>();
    private Company company;

    public Interviewer(String username, String password) {
        super(username, password);
    }

//    public void recommend(Applicant applicant, JobPosting job){
//        job.getCandidatePool().nextRound(applicant);
//    }
//
//    public void decline(Applicant applicant, JobPosting job){
//        job.getCandidatePool().removeFromPool(applicant);
//    }

    //TODO when someone gets hired, please change the 'filled' boolean for the jobPosting to TRUE

    void addToList(Applicant applicant, String position) {
        if (applicantsList.get(position) == null){
            ArrayList<Applicant> updateList = new ArrayList<>();
            updateList.add(applicant);
            applicantsList.put(position,updateList);
        }else{
            ArrayList<Applicant> updateList = applicantsList.get(position);
            updateList.add(applicant);
            applicantsList.put(position,updateList);
        }

    }

    ArrayList<Applicant> getInterviewees(String position) {
//        ArrayList<Applicant> listOfInterviewees = new ArrayList<>();
//        for (Applicant a : applicantsList.keySet()) {
//            listOfInterviewees.add(a);
//        }
        ArrayList<Applicant> result = this.applicantsList.get(position);
        return result;
    }

    public void clearInterviewees(String position){
        ArrayList<Applicant> clearedList= new ArrayList<>();
        applicantsList.put(position,clearedList);
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Company getCompany() {
        return this.company;
    }


    public String toString() {
        return "{I," + this.getUsername() + "," + this.getPassword() + "}";
    }
}