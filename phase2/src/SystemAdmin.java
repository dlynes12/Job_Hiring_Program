import javafx.scene.control.Alert;
import java.util.ArrayList;

public class SystemAdmin implements java.io.Serializable {

    private JobAccess jobManager;
    private UserAccess userManager;
    private ArrayList<Company> companies;
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

        if (this.companies.contains(company)) {
            return company.getHrCoordinator();
        } else {
            return null;
        }
    }

    void setTimeKeeper(TimeKeeper timeKeeper) {
        this.timeKeeper = timeKeeper;
    }

    protected TimeKeeper getTimeKeeper() {
        return this.timeKeeper;
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
            alert.setTitle("Invalid date input");
            alert.setHeaderText("Do not leave any of the fields empty");
            alert.setContentText("Please Try Again");
        } else if (alertType.equals("create")) {
            alert.setTitle("Invalid input or user already exists");
            alert.setHeaderText("Do not leave any of the fields empty");
            alert.setContentText("Please Try Again");
        } else if (alertType.equals("tag")) {
            alert.setTitle("No tag selected");
            alert.setHeaderText("Select select a Tag");
            alert.setContentText("Please Try Again");
        } else if (alertType.equals("job")) {
            alert.setTitle("Invalid input");
            alert.setHeaderText("Do not leave any of the fields empty");
            alert.setContentText("Please Try Again");
        } else if (alertType.equals("apply")) {
            alert.setTitle("Unexpected Input");
            alert.setHeaderText("Job not Selected");
            alert.setContentText("Please Try Again");
        }
        return alert;
    }
}