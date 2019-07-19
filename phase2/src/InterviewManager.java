import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InterviewManager {

    /* Initially, InterviewManager class gets all those who have applied to a job after the job has closed (in its constructor)

    Once the first round is complete, the class gets the approved list of applicants and shoots it to the HR coordinator, who then
    shoots the list back to InterviewManager

    and so on, until the a single applicant gets hired

    */

    ArrayList<Applicant> ApprovedApplicants = new ArrayList<>();
    private Map<Applicant, Integer> candidates = new HashMap();
    private ArrayList<Applicant> rejectedApplicants = new ArrayList<>();
    String position;
    int roundNum =0;


    InterviewManager(ArrayList<Applicant> applicantsList){
        this.ApprovedApplicants = applicantsList;
    }

    public void sendListToInterview(UserAccess userAccess){
        int numInterviewer = 0;
        int numApplicants = 0;
        ArrayList<Interviewer> intList = userAccess.getListInterviewers();
        if (intList.size() == 1) {
            //send the whole list to one interviewer
            for (Applicant a: ApprovedApplicants){
                intList.get(numInterviewer).addToList(a,position);
            }
        }else{
            while (ApprovedApplicants.size() > numApplicants){
                while (intList.size() >= numInterviewer){
                    Applicant a = ApprovedApplicants.get(numApplicants);
                    intList.get(numInterviewer).addToList(a,position);
                    numApplicants += 1;
                    numInterviewer +=1;
                }
                if (intList.size()==(numInterviewer-1) && ApprovedApplicants.size() > numApplicants){ // if there are still more Applicants to add
                    numInterviewer =0; //loop back through the list of interviewers and keep add to their list
                }

            }
        }
    }

    public void nextRound(Applicant applicant) {
        this.candidates.put(applicant, this.candidates.get(applicant) + 1);
    }

    public void removeFromPool(Applicant applicant){
        this.rejectedApplicants.add(applicant);
    }

    public boolean advanceRound(){
        boolean advance = false;
        int numApproved = 0;
        int numDeclined = 0;
        for (Applicant key: candidates.keySet()){
            numApproved +=1;
        }
        for (Applicant declined: rejectedApplicants){
            numDeclined +=2;
        }
        if (numApproved+numDeclined == ApprovedApplicants.size()){advance = true;}
        return advance;
    }


}
