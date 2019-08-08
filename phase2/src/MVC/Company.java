package MVC;

import java.util.ArrayList;

public class Company {

    private HR hrCoordinator;
    private ArrayList<JobPosting> jobPostings;
    private ArrayList<Interviewer> interviewers;
    private String companyName;

    public Company(String companyName){
        this.companyName = companyName;
    }

    public String getCompanyName(){
        return this.companyName;
    }

    public void setHrCoordinator(HR hrCoordinator){ this.hrCoordinator = hrCoordinator; }

    public void addInterviewer(Interviewer interviewer) { this.interviewers.add(interviewer); }

    HR getHrCoordinator(){ return this.hrCoordinator; }
}
