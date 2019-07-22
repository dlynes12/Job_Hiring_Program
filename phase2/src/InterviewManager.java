import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InterviewManager {

    /* Initially, InterviewManager class gets all those who have applied to a job after the job has closed (in its constructor)

    Once the first round is complete, the class gets the approved list of applicants and shoots it to the HR coordinator, who then
    shoots the list back to InterviewManager

    and so on, until the a single applicant gets hired


    How to check the status of an applicant: check if the applicant is in the candidates list,
        if they are not in the list, they have been rejected,
        if they are in the list their status is the integer value of the roundNum
                0: resume submitted; (Job is open)
                1: phone interview; (Job is closed)

    */

    ArrayList<Applicant> approvedApplicants = new ArrayList<>(); // ppl that have applied for the job/ started a new round
    private ArrayList<Applicant> candidates = new ArrayList<>(); // people who are waiting to be moved to the next round
    private ArrayList<Applicant> rejectedFromRound = new ArrayList<>();
    private ArrayList<Applicant> rejectedApplicants = new ArrayList<>();
    JobPosting jobPosting;
    //String position;
    int roundNum =0;
    ArrayList<String>hiringStage = new ArrayList<>(); // list of all the stages in the hiring process
    //todo: work on way for Applicants to see the progress of their application through InterviewManager

    // methods used while job is open  -----------------------------------------------------------

    public void addStage(String stageInProcess){ // add a new stage in the interview process, makes the hiring process dynamic
        boolean inList = false;
        for (String stage: hiringStage){
            if (stageInProcess.equals(stage)){inList = true;}
        }
        if (!inList){hiringStage.add(stageInProcess);}
    }

    public void applyToJob(Applicant applicant){ // may be useless
        boolean inList = false;
        for (Applicant user: this.approvedApplicants){
            if (user.getUsername().equals(applicant.getUsername())){inList = true;}
        }
        if (!inList){this.approvedApplicants.add(applicant);}
    }

    public void withdrawApp(Applicant applicant){
        this.approvedApplicants.remove(applicant);
    }

    /// methods to use once job is closed-------------------------------------------------------------------

    InterviewManager(ArrayList<Applicant> listApplicants, JobPosting job){
        this.approvedApplicants = listApplicants;
        this.jobPosting = job;
        for (Applicant a: approvedApplicants){
            a.updateStatus(jobPosting,hiringStage.get(roundNum));
        }
    }

    public void sendListToInterview(UserAccess userAccess){ //method to distribute applicants to interviewers
        int numInterviewer = 0;
        int numApplicants = 0;
        String position = jobPosting.getPosition();
        ArrayList<Interviewer> intList = userAccess.getListInterviewers();
        if (intList.size() == 1) {
            //send the whole list to one interviewer
            for (Applicant a: approvedApplicants){
                intList.get(numInterviewer).addToList(a,position);
            }
        }else{
            while (approvedApplicants.size() > numApplicants){
                while (intList.size() >= numInterviewer){
                    Applicant a = approvedApplicants.get(numApplicants);
                    intList.get(numInterviewer).addToList(a,position);
                    numApplicants += 1;
                    numInterviewer +=1;
                }
                if (intList.size()==(numInterviewer-1) && approvedApplicants.size() > numApplicants){ // if there are still more Applicants to add
                    numInterviewer =0; //loop back through the list of interviewers and keep add to their list
                }

            }
        }
    }

    public void nextRound(Applicant applicant) { //method to recommend an applicant
        boolean inList = false;
        for (Applicant user: this.candidates){
            if (user.getUsername().equals(applicant.getUsername())){
                inList = true;
            }
        }
        if (!inList){this.candidates.add(applicant);}
    }

    public void reject(Applicant applicant){ //method to reject an applicant
        this.rejectedApplicants.add(applicant);
        this.rejectedFromRound.add(applicant);
    }

    private boolean advanceRound(){ // never actually called except by getRecommendList()
        boolean advance = false;
        int numApproved = 0;
        int numDeclined = 0;
        for (Applicant key: candidates){
            numApproved +=1;
        }
        for (Applicant declined: rejectedFromRound){
            numDeclined +=1;
        }
        if (numApproved+numDeclined == approvedApplicants.size()){advance = true;}
        return advance;
    }

    public ArrayList<Applicant> getRecommendList(){ // list of applicants who have moved onto the next round & are being sent to HR
        ArrayList<Applicant> appsForNextRound = new ArrayList<>();
        if (this.advanceRound()){
            for (Applicant a: candidates){
                appsForNextRound.add(a);
            }
        }
        return appsForNextRound;
    }

    public void getListFromHR(ArrayList<Applicant> applicants){ // gets list of applicants from HR for the next round
        this.approvedApplicants = applicants;
        this.candidates = new ArrayList<>();
        this.rejectedFromRound = new ArrayList<>();
        roundNum +=1;
        for (Applicant a: candidates){
            a.updateStatus(jobPosting,hiringStage.get(roundNum));
        }

    }

  // complete list of applicants is in JobPostings Class

}
