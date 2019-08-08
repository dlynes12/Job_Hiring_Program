import java.util.ArrayList;
import java.util.Date;

public class JobPosting implements java.io.Serializable {

    private int numHires;
    private Job job;
    private Date datePosted;
    private Date dateClosed;
    private Company company;
    private ArrayList<Interviewer> chosenInterviewers;

    private InterviewManager hiringProcessor;

    private ArrayList<Applicant> applicants = new ArrayList<>();
    private ArrayList<String> listOfStages = new ArrayList<>();

    public JobPosting(Job job, Date datePosted, Date dateClosed, ArrayList<Interviewer> chosenInterviewers, int numPositions) {
        this.job = job;
        this.datePosted = datePosted;
        this.dateClosed = dateClosed;
        this.chosenInterviewers = chosenInterviewers;
        this.numHires = numPositions;
        this.listOfStages = job.getListOfStages();
    }

    int getNumHires() {
        return numHires;
    }

    public Job getJob() {
        return this.job;
    }

    public void setCompany(Company company){
        this.company = company;
        System.out.println("company set to jobposting:" + this.getJob().getPosition() + ", " + this.getCompany().getCompanyName());
    }

    public Company getCompany() {
        return this.company;
    }

    Date getDateClosed() {
        return this.dateClosed;
    }

    void addApplicant(Applicant applicant) {
        if (!this.applicants.contains(applicant)) {
            this.applicants.add(applicant);
        }
    }

    void removeApplicant(Applicant applicant) {
        if (this.applicants.contains(applicant)) {
            this.applicants.remove(applicant);
        }
    }

    String viewApplicants(Interviewer interviewer, String position) { //view all Applicants still in the hiring process
        StringBuilder listOfApplicants = new StringBuilder();
        String result;
        for (Applicant applicant : interviewer.getInterviewees(position)) {
            listOfApplicants.append(applicant.getUsername()).append(",");
        }
        if (listOfApplicants.length() == 0) {
            result = listOfApplicants.toString();
        } else {
            result = listOfApplicants.substring(0, listOfApplicants.length() - 1);
        }
        return result;
    }

    String viewAllApplicants() {
        StringBuilder listOfApplicants = new StringBuilder();
        String result;
        for (Applicant applicant : this.applicants) {
            listOfApplicants.append(applicant.getUsername()).append(",");
        }
        if (listOfApplicants.length() == 0) {
            result = listOfApplicants.toString();
        } else {
            result = listOfApplicants.substring(0, listOfApplicants.length() - 1);
        }
        return result;
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