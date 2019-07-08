
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Applicant {

    private String username;
    private String password;

    String directory = System.getProperty("user.home");
    String fileName = username + ".txt";
    String absolutePath = directory + File.separator + fileName;

    public Applicant(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
