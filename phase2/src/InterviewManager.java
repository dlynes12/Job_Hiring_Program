import java.util.ArrayList;

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

    //TODO we need to have an attribute of how many people we are hiring

    ArrayList<Applicant> approvedApplicants = new ArrayList<>(); // ppl that have applied for the job/ started a new round
    private ArrayList<Applicant> candidates = new ArrayList<>(); // people who are waiting to be moved to the next round
    private ArrayList<Applicant> rejectedFromRound = new ArrayList<>();
    private ArrayList<Applicant> rejectedApplicants = new ArrayList<>();
    JobPosting jobPosting;
    //String position;
    int roundNum =0;
    ArrayList<String>hiringStage = new ArrayList<>(); // list of all the stages in the hiring process
    ArrayList<Interviewer> chosenInterviewers;
    //todo: work on way for Applicants to see the progress of their application through InterviewManager

    // methods used while job is open  -----------------------------------------------------------

    public void addStage(String stageInProcess){ // add a new stage in the interview process, makes the hiring process dynamic
        boolean inList = false;
        for (String stage: hiringStage){
            if (stageInProcess.equals(stage)){inList = true;}
        }
        if (!inList){hiringStage.add(stageInProcess);}
    }

    public void withdrawApp(Applicant applicant){
        this.approvedApplicants.remove(applicant);
    }

    /// methods to use once job is closed-------------------------------------------------------------------

    InterviewManager(ArrayList<Applicant> listApplicants, JobPosting job, ArrayList<String> stages, ArrayList<Interviewer> lstChosenInterviewers){
        this.approvedApplicants = listApplicants;
        this.jobPosting = job;
        this.hiringStage = stages;
        for (Applicant a: approvedApplicants){
            a.updateStatus(jobPosting,hiringStage.get(roundNum));
        }
        this.chosenInterviewers = lstChosenInterviewers;
    }

    public ArrayList<Applicant> getRoundOfApplicants(){return this.approvedApplicants;}

    public void sendListToInterview(UserAccess userAccess){ //method to distribute applicants to interviewers
        int numInterviewer = 0;
        String position = jobPosting.getPosition();
        //ArrayList<Interviewer> intList = userAccess.getListInterviewers();
        if (this.chosenInterviewers.size() == 1) {
            //send the whole list to one interviewer
            for (Applicant applicant: approvedApplicants){
                this.chosenInterviewers.get(numInterviewer).addToList(applicant,position);
            }
        }else{
            for(Applicant applicant: approvedApplicants){
                if (numInterviewer == this.chosenInterviewers.size()){numInterviewer = 0;}
                this.chosenInterviewers.get(numInterviewer).addToList(applicant,position);
                numInterviewer += 1;
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
        applicant.updateStatus(this.jobPosting, "Rejected");
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
