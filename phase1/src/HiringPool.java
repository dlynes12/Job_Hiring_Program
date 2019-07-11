import java.util.ArrayList;
import java.util.HashMap;
public class HiringPool {

    //private ArrayList<Applicant> candidates = new ArrayList<>();
    private HashMap<Applicant, Integer> candidates = new HashMap<>();
    private ArrayList<Applicant> rejectedApplicants = new ArrayList<>();

    public HiringPool(){}

    //TODO: FOR HR COODRINATOR TO POPULATE

    public void addToPool(Applicant applicant, int round){
        this.candidates.put(applicant, round);
    }

    public void hire(Applicant applicant, JobPosting job){
    }


}
