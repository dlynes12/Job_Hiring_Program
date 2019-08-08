import java.util.ArrayList;

public class InterviewManager {

    private ArrayList<Applicant> approvedApplicants = new ArrayList<>(); // ppl that have applied for the job/ started a new round
    private ArrayList<Applicant> candidates = new ArrayList<>(); // people who are waiting to be moved to the next round
    private ArrayList<Applicant> rejectedFromRound = new ArrayList<>();
    private ArrayList<Applicant> rejectedApplicants = new ArrayList<>();
    private ArrayList<String> hiringStage = new ArrayList<>();
    private ArrayList<Interviewer> chosenInterviewers;
    private JobPosting jobPosting;
    private boolean appsDistributed;
    private int roundNum = 0;

    void addStage(String stageInProcess) { // add a new stage in the interview process, makes the hiring process dynamic
        boolean inList = false;
        for (String stage : hiringStage) {
            if (stageInProcess.equals(stage)) {
                inList = true;
                break;
            }
        }
        if (!inList) {
            hiringStage.add(stageInProcess);
        }
    }

    int numStages() {
        return hiringStage.size();
    }

    void withdrawApp(Applicant applicant) {
        boolean isFilled = (jobPosting.getNumHires() == this.candidates.size());
        if (!isFilled) {
            this.approvedApplicants.remove(applicant);
        }
    }

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

    int getRoundNum() {
        return roundNum + 1;
    }

    boolean isFinalRound() {
        boolean result = false;
        if (roundNum == hiringStage.size() - 1) {
            result = true;
        }
        return result;
    }

    void sendListToInterview() { //method to distribute applicants to interviewers
        int numInterviewer = 0;
        String position = jobPosting.getJob().getPosition();
        if (this.chosenInterviewers.size() == 1) {//send the whole list to one interviewer
            for (Applicant applicant : approvedApplicants) {
                this.chosenInterviewers.get(numInterviewer).addToList(applicant, position);
                this.appsDistributed = true;
            }
        } else {
            for (Applicant applicant : approvedApplicants) {
                if (numInterviewer == this.chosenInterviewers.size()) {
                    numInterviewer = 0;
                }
                this.chosenInterviewers.get(numInterviewer).addToList(applicant, position);
                this.appsDistributed = true;
                numInterviewer += 1;
            }
        }
    }

    boolean isDistributed() {
        return this.appsDistributed;
    }

    void nextRound(Applicant applicant) {
        boolean inList = false;
        for (Applicant user : this.candidates) {
            if (user.getUsername().equals(applicant.getUsername())) {
                inList = true;
            }
        }
        if (!this.isFinalRound()) {
            if (!inList) {
                this.candidates.add(applicant);
            }
        } else {
            boolean isFilled = (jobPosting.getNumHires() == this.candidates.size());
            if (!inList && !isFilled) {
                this.candidates.add(applicant);
                System.out.println("applicant has been hired");
            }
        }
    }

    void reject(Applicant applicant) {
        this.rejectedApplicants.add(applicant);
        this.rejectedFromRound.add(applicant);
        applicant.updateStatus(this.jobPosting, "Rejected");
    }

    private boolean advanceRound() {
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

    ArrayList<Applicant> getRecommendList() {
        ArrayList<Applicant> appsForNextRound = new ArrayList<>();
        if (this.advanceRound()) {
            for (Applicant a : candidates) {
                appsForNextRound.add(a);
            }
        }
        return appsForNextRound;
    }

    void getListFromHR(ArrayList<Applicant> applicants) {
        if (!this.isFinalRound()) {
            this.approvedApplicants = applicants;
            this.candidates = new ArrayList<>();
            this.rejectedFromRound = new ArrayList<>();
            this.appsDistributed = false;
            roundNum += 1;
            for (Applicant a : candidates) {
                a.updateStatus(jobPosting, hiringStage.get(roundNum));
            }
            for (Interviewer interviewer : chosenInterviewers) {
                interviewer.clearInterviewees(jobPosting.getJob().getPosition());
            }
        } else {
            for (Applicant applicant : applicants) {
                applicant.updateStatus(jobPosting, "Hired");
            }
        }
    }
}