import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Applicant extends UserAccess {

    String directory = System.getProperty("user.home");
    String fileName = userName + ".txt";
    String absolutePath = directory + File.separator + fileName;


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

    public void applyToJob(){


    }


    public void getJobStatus(){

    }

    public void getHistory(){

    }

}
