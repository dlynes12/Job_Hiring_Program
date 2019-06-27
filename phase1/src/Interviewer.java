import java.util.ArrayList;

public class Interviewer extends UserAccess {

    protected ArrayList<Applicant> interviewees = new ArrayList();

    void Interview(ArrayList<Applicant> interviewees){
        this.interviewees = interviewees;

    }

    public ArrayList getInterviewees() {
        return this.interviewees;
    }







}
