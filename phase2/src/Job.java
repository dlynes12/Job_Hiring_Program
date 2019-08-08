import java.util.ArrayList;

public class Job {

    private String position;
    private String tag;
    private int interviewRounds;
    private ArrayList<String> stagesOfInterview;

    public Job(String position, String tag, int interviewRounds, ArrayList<String> stagesOfInterview) {
        this.position = position;
        this.tag = tag;
        this.interviewRounds = interviewRounds;
        this.stagesOfInterview = stagesOfInterview;
    }

    String getPosition() {
        return this.position;
    }

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