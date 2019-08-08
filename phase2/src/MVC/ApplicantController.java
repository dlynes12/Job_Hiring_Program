package MVC;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ApplicantController {
    @FXML
    private Button btnSubmit;

    @FXML
    private Button btnApplyToJobs;

    @FXML
    private Button btnJobStat;

    @FXML
    private Button btnAccountHist;

    @FXML
    private Button btnLogout;

    @FXML
    private AnchorPane pnSubmitResume;

    @FXML
    private AnchorPane pnApply;

    @FXML
    private ListView<?> lstJobs;

    @FXML
    private Button btnApply;

    @FXML
    private AnchorPane pnJobStatus;

    @FXML
    private Label lblJobsApplied;

    @FXML
    private ListView<String> lstJobsApplied;

    @FXML
    private Button btnWithdraw;

    @FXML
    private AnchorPane pnAcntHist;

    @FXML
    private Label lblAcntHist;

    @FXML
    private RadioButton rdPartTime;

    @FXML
    private RadioButton rdFullTime;

    @FXML
    private RadioButton rdAll;

    @FXML
    private Label lblFilter;

    @FXML
    private ToggleGroup tgFilter;


    static User loggedUser;
    SystemAdmin systemAdmin;

    public void setLoggedUser(User l){
        loggedUser = l;
    }
    public void setSystemAdmin(SystemAdmin s){
        systemAdmin = s;
    }


    public void submitResume(ActionEvent event) throws Exception{
        pnSubmitResume.setVisible(true);
        pnAcntHist.setVisible(false);
        pnApply.setVisible(false);
        pnJobStatus.setVisible(false);
    }

    public void applyToJob(ActionEvent event) throws Exception{
        pnSubmitResume.setVisible(false);
        pnAcntHist.setVisible(false);
        pnApply.setVisible(true);
        pnJobStatus.setVisible(false);
    }

    public void viewJobStatus(ActionEvent event) throws Exception{
        pnSubmitResume.setVisible(false);
        pnAcntHist.setVisible(false);
        pnApply.setVisible(false);
        pnJobStatus.setVisible(true);

        for (JobPosting jobPosting : ((Applicant)loggedUser).getJobsApplied().keySet()) {
            lstJobsApplied.getItems().add(jobPosting.getJob().getPosition() + " - " + ((Applicant)loggedUser).getJobStatus(jobPosting));
        }
    }

    public void accountHistory(ActionEvent event) throws Exception{
        pnSubmitResume.setVisible(false);
        pnAcntHist.setVisible(true);
        pnApply.setVisible(false);
        pnJobStatus.setVisible(false);
        lblAcntHist.setText(((Applicant)this.loggedUser).getInfo());
    }

    public void logout(ActionEvent event) throws  Exception{
        Parent creation = FXMLLoader.load(getClass().getResource("MainUI.fxml"));
        Scene tableViewScene = new Scene(creation);

        // Instead of retrieving the stage by the event's source, you can do it by one of your FXML component.
        Stage window = (Stage) btnLogout.getScene().getWindow();
        window.close();
        window.setScene(tableViewScene);
        window.show();

        //((Stage) btnExit.getScene().getWindow()).close();
        //Stage stage = (Stage) exit.getScene().getWindow();
        // do what you have to do
        //stage.close();
    }
}
