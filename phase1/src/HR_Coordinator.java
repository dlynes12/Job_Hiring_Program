import java.util.ArrayList;

public class HR_Coordinator extends User {

    private String username;
    private String password;
    ArrayList<JobPosting> Jobs = new ArrayList<>();

    public HR_Coordinator(String username,String password){
        super(username, password);
    }

    public void matchItoA(Interviewer interviewer, Applicant applicant){
        //interviewer.interviewees.add(applicant);
    }

}
