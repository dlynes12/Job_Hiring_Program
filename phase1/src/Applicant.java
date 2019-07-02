import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Applicant extends User{

    private String username;
    private String password;

    String directory = System.getProperty("user.home");
    String fileName = username + ".txt";
    String absolutePath = directory + File.separator + fileName;

    public Applicant(String username, String password){
        super(username, password);

    }

    @Override
    public boolean login(User user) {
        return false;
    }



    FileWriter fileWriter;

    {
        try {
            fileWriter = new FileWriter(absolutePath);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    } {
        String fileContent = "This is a sample text.";
        try {
            fileWriter.write(fileContent);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
        // exception handling



    public void getDocs(){

    }

    public void applyToJob(JobPosting jobPosting){
        //jobPosting.addApplicant();


    }


    public void getJobStatus(){

    }

    public void getHistory(){

    }

}
