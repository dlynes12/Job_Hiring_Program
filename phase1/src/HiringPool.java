import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HiringPool {

    //private ArrayList<Applicant> candidates = new ArrayList<>();
    private Map<Applicant, Integer> candidates = new HashMap();
    private ArrayList<Applicant> rejectedApplicants = new ArrayList<>();

    public HiringPool(){}

    //TODO: FOR HR COORDRINATOR TO POPULATE

    public void addToPool(Applicant applicant, int round){
        this.candidates.put(applicant, round);
    }

    public void nextRound(Applicant applicant) {
        this.candidates.put(applicant, this.candidates.get(applicant) + 1);
    }

    public void removeFromPool(Applicant applicant){
        this.candidates.remove(applicant);
        this.rejectedApplicants.add(applicant);
    }

    public void hire(Applicant applicant, JobPosting job){
    }


}
