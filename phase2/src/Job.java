import java.util.ArrayList;

public class Job {

    private String position;
    private String tag;
    private Company company;
    private int interviewRounds;
    private ArrayList<String> stagesOfInterview;

    public Job(String position, Company company, String tag, int interviewRounds, ArrayList<String> stagesOfInterview) {
        this.position = position;
        this.tag = tag;
        this.interviewRounds = interviewRounds;
        this.stagesOfInterview = stagesOfInterview;
        this.company = company;
    }

    String getPosition() {
        return this.position;
    }

    Company getCompany(){ return this.company; }

    void setTag(String tag) {
        this.tag = tag;
    }

    String getTag() {
        return this.tag;
    }

    ArrayList<String> getListOfStages() {
        return this.stagesOfInterview;
    }
}