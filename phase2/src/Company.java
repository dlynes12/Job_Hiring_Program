import java.util.ArrayList;

public class Company {

    private HR_Coordinator hrCoordinator;
    private ArrayList<JobPosting> jobPostings;
    private String companyName;

    public Company(String companyName, HR_Coordinator hrCoordinator){
        this.hrCoordinator = hrCoordinator;
        this.companyName = companyName;

    }

    public void setHrCoordinator(HR_Coordinator hrCoordinator){
        this.hrCoordinator = hrCoordinator;
    }

    public HR_Coordinator getHrCoordinator(){ return this.hrCoordinator; }
}
