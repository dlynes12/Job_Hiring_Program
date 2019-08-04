import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class JobPosting{


    //TODO we need to have an attribute of how many people we are hiring

    private Date datePosted;
    private Date dateClosed;
    private String position;
    private String company;
    private String tag;
    private InterviewManager HiringProcessor;
    private ArrayList<Interviewer> chosenInterviewers;
    private Boolean filled = false;
    private int interviewRounds;
    private ArrayList<Applicant> applicants = new ArrayList<>();
    private ArrayList<String> listOfStages = new ArrayList<>();


    public JobPosting(Date datePosted, Date dateClosed, String position, int interviewRounds, String company, ArrayList<String> stagesOfInterv, ArrayList<Interviewer> activeInterviewers) {
        this.datePosted = datePosted;
        this.dateClosed = dateClosed;
        this.position = position;
        this.interviewRounds = interviewRounds;
        this.company = company;
        this.listOfStages = stagesOfInterv;
        this.chosenInterviewers = activeInterviewers;
    }

    public Date getDatePosted() { return this.datePosted; }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    Date getDateClosed() {
        return this.dateClosed;
    }

    public void setDateClosed(Date dateClosed) { this.dateClosed = dateClosed; }

    String getPosition() {
        return this.position;
    }

    public Boolean isFilled() { return this.filled; }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTag(){return this.tag;}



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

    //TODO: make this consider the date - applicants can only withdraw before the closing date.
    public void removeApplicant(Applicant applicant){
        if (this.applicants.contains(applicant)){
            this.applicants.remove(applicant);
        }
    }

    public String viewApplicants(Interviewer interviewer){ //view all Applicants still in the hiring process
        StringBuilder listOfApplicants = new StringBuilder();
        String result;
        Date today = new Date();
        Instant now = Instant.now();
        today.from(now);
        if (today.before(this.getDateClosed())){
            for (Applicant applicant: this.applicants){  //list before was this.applicants
                listOfApplicants.append(applicant.getUsername()).append(",");
            }
            if (listOfApplicants.length() ==0){result = listOfApplicants.toString();}
            else {result = listOfApplicants.substring(0, listOfApplicants.length()-1);} //takes off the last comma
        }else{
            for (Applicant applicant: interviewer.getInterviewees()){
                listOfApplicants.append(applicant.getUsername()).append(",");
            }
            if (listOfApplicants.length() ==0){result = listOfApplicants.toString();}
            else {result = listOfApplicants.substring(0, listOfApplicants.length()-1);} //takes off the last comma
        }
        return result;
    }

    String viewAllApplicants(){
        StringBuilder listOfApplicants = new StringBuilder();
        String result;
        for (Applicant applicant: this.applicants){  //list before was this.applicants
            listOfApplicants.append(applicant.getUsername()).append(",");
        }
        if (listOfApplicants.length() ==0){result = listOfApplicants.toString();}
        else {result = listOfApplicants.substring(0, listOfApplicants.length()-1);} //takes off the last comma
        return result;
    }

    boolean startInterviewProcess(){
        boolean start = false;
        if (!this.applicants.isEmpty()){
            Date today = new Date();
            Instant now = Instant.now();
            today.from(now);
            if (today.after(dateClosed)){
                HiringProcessor = new InterviewManager(applicants, this, this.listOfStages, chosenInterviewers);
                start = true;
            }
        }
        return start;
    }

    InterviewManager getHiringProcessor() {return HiringProcessor;}
}