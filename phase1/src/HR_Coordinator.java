import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HR_Coordinator extends User{

    JPostings ListJobs = new JPostings();

    public HR_Coordinator(String Username,String Password){
        super(Username,Password);
    }

    public boolean addJob(Date datePosted, Date dateClosed, String position){
        JobPosting job = new JobPosting(datePosted,dateClosed,position);
        return ListJobs.addJob(job);
    }

    public boolean removeJob(String position){return ListJobs.removeJob(position);}

    public String[] ViewOpenJobs(){return ListJobs.ViewJobs().split(",");}




    public void matchItoA(Interviewer interviewer, Applicant applicant){
        //interviewer.interviewees.add(applicant);
    }

}
