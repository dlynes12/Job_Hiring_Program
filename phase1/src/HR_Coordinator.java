import java.util.Date;

/*One HR Person hires for their own section of the company*/
public class HR_Coordinator extends User{

    //JPostings ListJobs = new JPostings();

    public HR_Coordinator(String Username,String Password){
        super(Username,Password);
    }
/*
    public boolean addJob(Date datePosted, Date dateClosed, String position){
        JobPosting job = new JobPosting(datePosted,dateClosed,position);
        return ListJobs.addJob(job);
    }

    public boolean removeJob(String position){return ListJobs.removeJob(position);}

    public String[] ViewOpenJobs(){return ListJobs.ViewJobs().split(",");}

    public JPostings getListJobs() {
        return ListJobs;`
    }*/

    public void matchItoA(Interviewer interviewer, Applicant applicant, JobPosting jobPosting){
        // loop thru list of applicants,
        //call addPool()
        //interviewer.addApplicant(applicant);
    }

    public String toString(){
        return "*********** \n" + this.getUsername() + "\n" + this.getPassword() + "\n" + "***********";

    }

}
