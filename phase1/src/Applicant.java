import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Applicant extends User {

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //do i still need these ??
    private String username;
    private String password;

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private static ArrayList<Applicant> applicants = new ArrayList<>();


    String directory = System.getProperty("user.home");
    String fileName = username + ".txt";
    String absolutePath = directory + File.separator + fileName;

    public Applicant(String username, String password) {
        super(username, password);

    }


    //FileWriter

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
    // exception handling


    public boolean login(String username, String password) {

        Applicant temp = new Applicant(username, password);
        for (Applicant a : applicants) {
            // if there is an existing applicant user and if their user names and passwords match
            if (temp.getUsername().equals(a.getUsername()) && temp.getPassword().equals(a.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public void signUp(String username, String password) {
        Applicant newApplicant = new Applicant(username, password);
        applicants.add(newApplicant);

    }


    public void getDocs() {

    }

    public void applyToJob(JobPosting jobPosting) {
        jobPosting.addApplicant(this);
    }


    public void getJobStatus() {

    }

    public void getHistory() {

    }

}
