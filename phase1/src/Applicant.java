import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Applicant extends User{
    private HashMap<String, String> jobsApplied = new HashMap<>();
    protected static ArrayList<Applicant> allApplicants = new ArrayList<>();

    String username = this.getUsername();
    String directory = System.getProperty("user.home");
    String fileName = username + ".txt";
    String absolutePath = directory + File.separator + fileName;

    public Applicant(String username, String password) {
        super(username,password);
    }

    FileWriter fileWriter;

    {
        try {
            fileWriter = new FileWriter(absolutePath);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    {
        String fileContent = "This is a sample text.";
        try {
            fileWriter.write(fileContent);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    //TODO: File writer and reader stuff needs to be implemented to access applicant Documents.
    public void getDocs() {

    }

    public void applyToJob(JobPosting jobPosting) {

        jobPosting.addApplicant(this);
    }

    public String getJobStatus(JobPosting job) {

        return this.jobsApplied.get(job.getPosition());
    }

    public void updateStatus(JobPosting job, String status){

        this.jobsApplied.put(job.getPosition(), status);
    }

    public void getHistory() {
    }

}
