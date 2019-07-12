public class Interviewer extends User {

    public Interviewer(String username, String password){
        super(username,password);
    }

    public void recommend(Applicant applicant, JobPosting job){
        job.getCandidatePool().nextRound(applicant);
    }

    public void decline(Applicant applicant, JobPosting job){
        job.getCandidatePool().removeFromPool(applicant);

    }

}
