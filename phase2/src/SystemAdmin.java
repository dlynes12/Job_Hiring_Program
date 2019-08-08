import javafx.scene.control.Alert;
import java.util.ArrayList;

public class SystemAdmin implements java.io.Serializable {

    private JobAccess jobManager;
    private UserAccess userManager;
    private ArrayList<Company> allCompanies = new ArrayList<>();
    private TimeKeeper timeKeeper;

    SystemAdmin() {
        this.jobManager = new JobAccess();
        this.userManager = new UserAccess();
    }

    JobAccess getJobManager() {
        return this.jobManager;
    }

    UserAccess getUserManager() {
        return this.userManager;
    }

    protected HR_Coordinator getCompanyHR(Company company) {
        if (this.allCompanies.contains(company)) {
            return company.getHrCoordinator();
        } else {
            return null;
        }
    }

    ArrayList<Company> getListOfCompanies() {
        return this.allCompanies;
    }

    void setTimeKeeper(TimeKeeper timeKeeper) {
        this.timeKeeper = timeKeeper;
    }

    void addCompany(Company company) {
        if (this.getCompany(company.getCompanyName()) == null && !company.getCompanyName().trim().isEmpty()) {
            this.allCompanies.add(company);
            System.out.println("added the company to list");
        }
    }

    Company getCompany(String companyName) {
        Company company = null;
        for (Company c : this.allCompanies) {
            if (c.getCompanyName().equals(companyName)) {
                company = c;
            }
        }
        return company;
    }

    Alert getAlert(String alertType) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        if (alertType.equals("login1")) {
            alert.setTitle("Password/Username Not Found");
            alert.setHeaderText("Username Not Found.");
            alert.setContentText("Please Try Again");
        } else if (alertType.equals("login2")) {
            alert.setTitle("Password/Username Not Found");
            alert.setHeaderText("Incorrect Password");
            alert.setContentText("Please Try Again");
        } else if (alertType.equals("date")) {
            alert.setTitle("Invalid input");
            alert.setHeaderText("Invalid date input");
            alert.setContentText("Please Try Again");
        } else if (alertType.equals("create")) {
            alert.setTitle("Invalid input or user already exists");
            alert.setHeaderText("Do not leave any of the fields empty");
            alert.setContentText("Please Try Again");
        } else if (alertType.equals("tag")) {
            alert.setTitle("No tag selected");
            alert.setHeaderText("Select full-time or part-time");
            alert.setContentText("Please Try Again");
        } else if (alertType.equals("job")) {
            alert.setTitle("Invalid input");
            alert.setHeaderText("Do not leave any of the fields empty");
            alert.setContentText("Please Try Again");
        } else if (alertType.equals("apply")) {
            alert.setTitle("Unexpected Input");
            alert.setHeaderText("Job not Selected");
            alert.setContentText("Please Try Again");
        } else if (alertType.equals("integer")) {
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Enter number of applicants");
            alert.setContentText("Please Try Again");
        }
        else if(alertType.equals("add interviewer")){
            alert.setTitle("Unexpected Input");
            alert.setHeaderText("Interviewer not selected");
            alert.setContentText("Please Try Again");
        }
        return alert;
    }
}