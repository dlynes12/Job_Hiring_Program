package MVC;

import java.util.ArrayList;
import java.util.Date;

public class JobPosting {

    //TODO we need to have an attribute of how many people we are hiring
    private int numHires;
    private Job job;
    private Date datePosted;
    private Date dateClosed;
    private Company company;
    private ArrayList<Interviewer> chosenInterviewers;

    private InterviewManager hiringProcessor;

    private ArrayList<Applicant> applicants = new ArrayList<>();
    private ArrayList<String> listOfStages = new ArrayList<>();

    //TODO make sure the interview process accommodates Company class

    public JobPosting(Job job, Date datePosted, Date dateClosed, ArrayList<Interviewer> chosenInterviewers, int numPositions) {
        this.job = job;
        this.datePosted = datePosted;
        this.dateClosed = dateClosed;
        this.chosenInterviewers = chosenInterviewers;
        this.numHires = numPositions;
        this.listOfStages = job.getListOfStages();
    }

    public int getNumHires() {return numHires;}

    public Job getJob() {
        return this.job;
    }

    public Company getCompany() {
        return this.company;
    }

    public Date getDatePosted() {
        return this.datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    Date getDateClosed() {
        return this.dateClosed;
    }

    public void setDateClosed(Date dateClosed) {
        this.dateClosed = dateClosed;
    }

    //HR needs to have access to this information as well.
    public void addApplicant(Applicant applicant) {
        if (!this.applicants.contains(applicant)) {
            this.applicants.add(applicant);
        }
    }

    //TODO: make this consider the date - applicants can only withdraw before the closing date.
    public void removeApplicant(Applicant applicant) {
        if (this.applicants.contains(applicant)) {
            this.applicants.remove(applicant);
        }
    }

    // todo: fix the list of applicants the interviewer sees
    public String viewApplicants(Interviewer interviewer, String position) { //view all Applicants still in the hiring process
        StringBuilder listOfApplicants = new StringBuilder();
        String result;
        for (Applicant applicant : interviewer.getInterviewees(position)) {
            listOfApplicants.append(applicant.getUsername()).append(",");
        }
        if (listOfApplicants.length() == 0) {
            result = listOfApplicants.toString();
        } else {
            result = listOfApplicants.substring(0, listOfApplicants.length() - 1);
        } //takes off the last comma
        return result;
    }

    String viewAllApplicants() {
        StringBuilder listOfApplicants = new StringBuilder();
        String result;
        for (Applicant applicant : this.applicants) {  //list before was this.applicants
            listOfApplicants.append(applicant.getUsername()).append(",");
        }
        if (listOfApplicants.length() == 0) {
            result = listOfApplicants.toString();
        } else {
            result = listOfApplicants.substring(0, listOfApplicants.length() - 1);
        } //takes off the last comma
        return result;
    }

    public void setListOfStages(ArrayList<String> listOfStages) {
        this.listOfStages = listOfStages;
    }

    boolean startInterviewProcess() {
        boolean start = false;
        if (!this.applicants.isEmpty()) {
            hiringProcessor = new InterviewManager(applicants, this, this.listOfStages, chosenInterviewers);
            for (String str : listOfStages) {
                hiringProcessor.addStage(str);
            }
            start = true;
        }
        return start;
    }

    InterviewManager getHiringProcessor() {
        return hiringProcessor;
    }
}