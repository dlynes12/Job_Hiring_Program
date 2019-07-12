import java.util.ArrayList;

public class InterviewManager {
    /* Initially, InterviewManager class gets all those who have applied to a job after  the job has closed (in its constructor)

    Once the first round is complete, the class gets the approved list of applicants and shoots it to the HR coordinator, who then
    shoots the list back to InterviewManager

    and so on, until the a single applicant gets hired

    * */

    ArrayList<Applicant> ApprovedApplicants = new ArrayList<>();

    InterviewManager(ArrayList<Applicant> applicantsList){
        this.ApprovedApplicants = applicantsList;
    }

    public void sendListToInterview(){
        for (Applicant applicant: ApprovedApplicants){
           // while (){}
        }
    }






}
