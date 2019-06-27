import java.util.ArrayList;

public class Interviewer extends UserAccess {

    public ArrayList<Applicant> interviewees = new ArrayList();

    void Interview(ArrayList interviewees){
        this.interviewees = interviewees;

    }

    public ArrayList getInterviewees() {
        return this.interviewees;
    }







}
