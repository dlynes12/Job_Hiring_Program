import java.io.Serializable;
import java.util.ArrayList;

public class Company implements Serializable {

    private HR_Coordinator hrCoordinator;
    private ArrayList<JobPosting> jobPostings;
    private ArrayList<Interviewer> interviewers = new ArrayList<>();
    private String companyName;

    Company(String companyName, SystemAdmin systemAdmin){
        this.companyName = companyName;
        systemAdmin.getUserManager().initiateCompany(this);
        systemAdmin.getJobManager().initializeCompany(this);
    }

    String getCompanyName(){
        return this.companyName;
    }



    HR_Coordinator getHrCoordinator(){ return this.hrCoordinator; }
}
