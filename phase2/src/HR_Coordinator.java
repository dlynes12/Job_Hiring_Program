import java.util.Date;

// One HR Person hires for their own section of the company
public class HR_Coordinator extends User{


    public HR_Coordinator(String Username,String Password){
        super(Username,Password);
    }

    public void matchItoA(Interviewer interviewer, Applicant applicant, JobPosting jobPosting){
        // loop thru list of applicants,
        // call addPool()
        // interviewer.addApplicant(applicant);
    }

    public String toString(){
        return "*********** \n" + this.getUsername() + "\n" + this.getPassword() + "\n" + "***********";

    }

}
