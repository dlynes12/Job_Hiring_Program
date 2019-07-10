import java.util.ArrayList;
import java.util.Date;

public class JobPosting{

    private Date datePosted;
    private Date dateClosed;
    private String position;
    protected ArrayList<Applicant> applicants = new ArrayList<>();

    public JobPosting(Date datePosted, Date dateClosed, String position) {
        this.datePosted = datePosted;
        this.dateClosed = dateClosed;
        this.position = position;
    }

    public Date getDatePosted() { return this.datePosted; }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    public Date getDateClosed() {
        return this.dateClosed;
    }

    public void setDateClosed(Date dateClosed) {
        this.dateClosed = dateClosed;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    //HR needs to have access to this information as well.
    public void addApplicant (Applicant applicant){
        this.applicants.add(applicant);
    }

    //TODO create the display window for HR Coordinator to view this list and able to click on the user
    public String viewApplicants(){
        String listOfApplicants = "";
        for (Applicant applicant: this.applicants){
            listOfApplicants = listOfApplicants + applicant.getUsername() + ",";
        }
        String result = listOfApplicants.substring(0, listOfApplicants.length()-1); //takes off the last comma
        return result;
    }


}
