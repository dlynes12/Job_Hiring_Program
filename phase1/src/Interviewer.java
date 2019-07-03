import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Interviewer extends User {

    private Map<Applicant, Integer> shortlist = new HashMap();  // <Applicant, Status(1,2,3)>
    private String username;
    private String password;

    public Interviewer(String username, String password){
        super(username,password);
    }

    @Override
    public boolean login(User user) {
        return false;
    }



    public Set getInterviewees(){
        return shortlist.keySet();
    }

    public void addApplicant(Applicant username){
        shortlist.put(username, 1);
    }

    public void Approve(Applicant username){
        shortlist.replace(username , shortlist.get(username) + 1);
    }

    public void Decline(Applicant username){}

}
