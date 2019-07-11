import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Interviewer extends User {

    private Map<Applicant, Integer> shortlist = new HashMap();  // <Applicant, Status(1,2,3)>

    public Interviewer(String username, String password){
        super(username,password);
    }

    public Set getInterviewees(){
        return shortlist.keySet();
    }

    public void addApplicant(Applicant username){
        shortlist.put(username, 1);
    }

    public void recommend(Applicant applicant, JobPosting job){
        HiringPool pool = job.getCandidatePool();
    }

    public void decline(Applicant username){}

}
