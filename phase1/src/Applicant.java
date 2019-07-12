import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Applicant extends User {
    private HashMap<String, String> jobsApplied = new HashMap<>();

    String username = this.getUsername();

    public Applicant(String username, String password) {
        super(username, password);

    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    public String getJobs() {

        String s = " ";

        if (!this.jobsApplied.isEmpty()){
            for (String key : this.jobsApplied.keySet()) {
                s = s + key + ",";
            }
        } else {s = s + "You have not applied for a job";}

        return s;
    }

    //TODO: File writer and reader stuff needs to be implemented to access applicant Documents.
    public void getDocs(String username) {
        Writer wr = new Writer(username + ".txt");
    }

    public void applyToJob(JobPosting jobPosting) {
        jobPosting.addApplicant(this);
        this.jobsApplied.put(jobPosting.getPosition(), "Submitted Resume/CV");
    }

    public String getJobStatus(JobPosting job) {
        return this.jobsApplied.get(job.getPosition());
    }

    public void updateStatus(JobPosting job, String status) {

        this.jobsApplied.put(job.getPosition(), status);
    }

    public void getHistory() {
    }

    @Override
    public String toString() {
        return "Applicant Username:" + username + '\n' +
                "Jobs Applied To:" + this.getJobs();
    }
}
