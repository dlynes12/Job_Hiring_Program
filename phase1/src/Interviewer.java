import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Interviewer {

    private Map<Applicant, Integer> shortlist = new HashMap();  // <Applicant, Status(1,2,3)>
    private String username = null;
    private String password = null;

    public Interviewer(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
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
