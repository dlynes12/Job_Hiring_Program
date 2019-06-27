import java.util.ArrayList;

public class Interviewer implements UserAccess {

    public ArrayList<Applicant> interviewees = new ArrayList();

    void Interview(ArrayList interviewees){
        this.interviewees = interviewees;

    }

    public ArrayList getInterviewees() {
        return this.interviewees;
    }







}
