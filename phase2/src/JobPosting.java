import javafx.collections.ObservableList;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class JobPosting{

    private Date datePosted;
    private Date dateClosed;
    private String position;
    private String company;
    private InterviewManager HiringProcessor;
    private Boolean filled = false;

    //private HiringPool candidatePool;
    private int interviewRounds;
    protected ArrayList<Applicant> applicants = new ArrayList<>();
    ArrayList<String> listOfStages = new ArrayList<>();

    public JobPosting(Date datePosted, Date dateClosed, String position, int interviewRounds, String company, ArrayList<String> stagesOfInterv) {
        this.datePosted = datePosted;
        this.dateClosed = dateClosed;
        this.position = position;
        this.interviewRounds = interviewRounds;
        this.company = company;
        this.listOfStages = stagesOfInterv;
    }

    public Date getDatePosted() { return this.datePosted; }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    public Date getDateClosed() {
        return this.dateClosed;
    }

    public void setDateClosed(Date dateClosed) { this.dateClosed = dateClosed; }

    public String getPosition() {
        return this.position;
    }

    public Boolean isFilled() { return this.filled; }

    public void setPosition(String position) {
        this.position = position;
    }

//    public HiringPool getCandidatePool(){
//        return this.candidatePool;
//    }

    //HR needs to have access to this information as well.
    public void addApplicant (Applicant applicant){
        if(!this.applicants.contains(applicant)){
            this.applicants.add(applicant);
            //this.candidatePool.addToPool(applicant,0);
        }
    }

    public String viewApplicants(Interviewer interviewer){ //view all Applicants still in the hiring process
        String listOfApplicants = "";
        String result;
        Date today = new Date();
        Instant now = Instant.now();
        today.from(now);
        if (today.before(this.getDateClosed())){
            for (Applicant applicant: this.applicants){  //list before was this.applicants
                listOfApplicants = listOfApplicants + applicant.getUsername() + ",";
            }
            if (listOfApplicants.length() ==0){result = listOfApplicants;}
            else {result = listOfApplicants.substring(0, listOfApplicants.length()-1);} //takes off the last comma
        }else{
            for (Applicant applicant: interviewer.getInterviewees()){
                listOfApplicants = listOfApplicants + applicant.getUsername() + ",";
            }
            if (listOfApplicants.length() ==0){result = listOfApplicants;}
            else {result = listOfApplicants.substring(0, listOfApplicants.length()-1);} //takes off the last comma
        }
        return result;
    }

    public String viewAllApplicants(){
        String listOfApplicants = "";
        String result;
        for (Applicant applicant: this.applicants){  //list before was this.applicants
            listOfApplicants = listOfApplicants + applicant.getUsername() + ",";
        }
        if (listOfApplicants.length() ==0){result = listOfApplicants;}
        else {result = listOfApplicants.substring(0, listOfApplicants.length()-1);} //takes off the last comma
        return result;
    }

    public boolean startInterviewProcess(){
        boolean start = false;
        if (!this.applicants.isEmpty()){
            Date today = new Date();
            Instant now = Instant.now();
            today.from(now);
            if (today.after(dateClosed)){
                HiringProcessor = new InterviewManager(applicants, this, this.listOfStages);
                start = true;
            }
        }
        return start;
    }

    public InterviewManager getHiringProcessor() {return HiringProcessor;}
}
