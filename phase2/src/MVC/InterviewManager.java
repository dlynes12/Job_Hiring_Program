package MVC;

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
    ArrayList<Applicant> candidates = new ArrayList<>(); // people who are waiting to be moved to the next round
    private ArrayList<Applicant> rejectedFromRound = new ArrayList<>();
    private ArrayList<Applicant> rejectedApplicants = new ArrayList<>();

    private ArrayList<String> hiringStage = new ArrayList<>(); // list of all the stages in the hiring process
    private ArrayList<Interviewer> chosenInterviewers;
    private JobPosting jobPosting;
    private boolean appsDistributed;
    int roundNum = 0;

    //todo: work on way for Applicants to see the progress of their application through InterviewManager

    // methods used while job is open  -----------------------------------------------------------

    void addStage(String stageInProcess) { // add a new stage in the interview process, makes the hiring process dynamic
        boolean inList = false;
        for (String stage : hiringStage) {
            if (stageInProcess.equals(stage)) {
                inList = true;
            }
        }
        if (!inList) {
            hiringStage.add(stageInProcess);
        }
    }

    public int numStages(){return hiringStage.size();}

    public void withdrawApp(Applicant applicant) {
        this.approvedApplicants.remove(applicant);
    }

    /// methods to use once job is closed-------------------------------------------------------------------

    InterviewManager(ArrayList<Applicant> listApplicants, JobPosting jobPosting, ArrayList<String> stages, ArrayList<Interviewer> lstChosenInterviewers) {
        this.approvedApplicants = listApplicants;
        this.jobPosting = jobPosting;
        this.hiringStage = stages;
        for (Applicant a : approvedApplicants) {
            a.updateStatus(jobPosting, hiringStage.get(roundNum));
        }
        this.chosenInterviewers = lstChosenInterviewers;
        appsDistributed = false;
    }

    ArrayList<Applicant> getRoundOfApplicants() {
        return this.approvedApplicants;
    }

    int getRoundNum() {
        return roundNum+1;
    }

    public boolean isFinalRound(){
        boolean result = false;
        if (roundNum == hiringStage.size()-1){result = true;}
        return result;
    }

    void sendListToInterview(UserAccess userAccess) { //method to distribute applicants to interviewers
        int numInterviewer = 0;
        String position = jobPosting.getJob().getPosition();
        //ArrayList<Interviewer> intList = userAccess.getListInterviewers();
        if (this.chosenInterviewers.size() == 1) {
            //send the whole list to one interviewer
            for (Applicant applicant : approvedApplicants) {
                this.chosenInterviewers.get(numInterviewer).addToList(applicant, position);
                this.appsDistributed = true;
            }
        } else {
            for (Applicant applicant : approvedApplicants) {
                if (numInterviewer == this.chosenInterviewers.size()) {numInterviewer = 0;}
                this.chosenInterviewers.get(numInterviewer).addToList(applicant, position);
                this.appsDistributed = true;
                numInterviewer += 1;
            }
        }
    }

    public boolean isDistributed(){return this.appsDistributed;}

    void nextRound(Applicant applicant) { //method to recommend & hire an applicant
        boolean inList = false;
        for (Applicant user : this.candidates) {
            if (user.getUsername().equals(applicant.getUsername())) {
                inList = true;
            }
        }
        if (!this.isFinalRound()){
            if (!inList) {this.candidates.add(applicant);}
        }else {
            boolean isFilled = (jobPosting.getNumHires() == this.candidates.size());
            if (!inList && !isFilled){
                this.candidates.add(applicant);
                System.out.println("applicant has been approved");
            }
        }

    }

    void reject(Applicant applicant) { //method to reject an applicant
        this.rejectedApplicants.add(applicant);
        this.rejectedFromRound.add(applicant);
        applicant.updateStatus(this.jobPosting, "Rejected");
    }

    private boolean advanceRound() { // never called except by getRecommendList()
        boolean advance = false;
        int numApproved = 0;
        int numDeclined = 0;
        for (Applicant key : candidates) {
            numApproved += 1;
        }
        for (Applicant declined : rejectedFromRound) {
            numDeclined += 1;
        }
        if (numApproved + numDeclined == approvedApplicants.size()) {
            advance = true;
        }
        return advance;
    }

    ArrayList<Applicant> getRecommendList() { // list of applicants who have moved onto the next round & are being sent to HR
        ArrayList<Applicant> appsForNextRound = new ArrayList<>();
        if (this.advanceRound()) {
            for (Applicant a : candidates) {
                appsForNextRound.add(a);
            }
        }
        return appsForNextRound;
    }

    void getListFromHR(ArrayList<Applicant> applicants) { // gets list of applicants from HR for the next round
        if (!this.isFinalRound()){
            this.approvedApplicants = applicants;
            this.candidates = new ArrayList<>();
            this.rejectedFromRound = new ArrayList<>();
            this.appsDistributed = false;
            roundNum += 1;
            for (Applicant a : candidates) {
                a.updateStatus(jobPosting, hiringStage.get(roundNum));
            }
            for (Interviewer interviewer: chosenInterviewers){interviewer.clearInterviewees(jobPosting.getJob().getPosition());}
        }else{
            for (Applicant applicant: applicants){applicant.updateStatus(jobPosting,"Hired");}
        }

    }// complete list of applicants is in JobPostings Class
}