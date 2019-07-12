import java.util.*;

public class Interviewer extends User {
    Map<Applicant,String> applicantsList = new HashMap<>();

    public Interviewer(String username, String password){
        super(username,password);
    }

    public void recommend(Applicant applicant, JobPosting job){
        job.getCandidatePool().nextRound(applicant);
    }

    public void decline(Applicant applicant, JobPosting job){
        job.getCandidatePool().removeFromPool(applicant);
    }

    public void addToList(Applicant applicant, String position){
        applicantsList.put(applicant,position);
    }


    public String toString(){
        return "*********** \n" + this.getUsername() + "\n" + this.getPassword() + "\n" + "***********";

    }

}

