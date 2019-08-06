import java.util.ArrayList;

public class Job {

    private String position;
    private String company;
    private String tag;
    private int interviewRounds;
    private ArrayList<String> stagesOfInterview;

    public Job(String position, String company, String tag, int interviewRounds, ArrayList<String> stagesOfInterview){
        this.position = position;
        this.company = company;
        this.tag = tag;
        this.interviewRounds = interviewRounds;
        this.stagesOfInterview = stagesOfInterview;
    }

    String getPosition() {
        return this.position;
    }

    void setTag(String tag){this.tag = tag;}

    String getCompany() {
        return this.company;
    }

    String getTag() {
        return this.tag;
    }

    int getInterviewRounds() {
        return this.interviewRounds;
    }

    ArrayList<String> getListOfStages() {
        return this.stagesOfInterview;
    }
}