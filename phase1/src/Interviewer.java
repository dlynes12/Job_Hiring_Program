import java.util.ArrayList;

public class Interviewer {

    protected ArrayList<Applicant> interviewees = new ArrayList<>();
    private String username = null;
    private String password = null;


    void Interview(ArrayList<Applicant> interviewees){
        this.interviewees = interviewees;

    }

    public ArrayList getInterviewees() {
        return this.interviewees;
    }







}
