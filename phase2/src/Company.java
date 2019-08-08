import java.io.Serializable;
import java.util.ArrayList;

public class Company implements Serializable {

    private HR_Coordinator hrCoordinator;
    private ArrayList<JobPosting> jobPostings;
    private ArrayList<Interviewer> interviewers;
    private String companyName;

    Company(String companyName){
        this.companyName = companyName;
    }

    String getCompanyName(){
        return this.companyName;
    }

    public void setHrCoordinator(HR_Coordinator hrCoordinator){ this.hrCoordinator = hrCoordinator; }

    public void addInterviewer(Interviewer interviewer) { this.interviewers.add(interviewer); }

    HR_Coordinator getHrCoordinator(){ return this.hrCoordinator; }
}
