package MVC;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class HRController implements Initializable {
    private static SystemAdmin systemAdmin = new SystemAdmin();
    private static User loggedUser;
    public void setSystemAdmin(SystemAdmin s){
        this.systemAdmin = s;
    }
    private TimeKeeper timeKeeper = new TimeKeeper();

    @FXML
    private Button btnAddJob;

    @FXML
    private Button btnManageJobs;

    @FXML
    private Button btnViewApps;

    @FXML
    private Button btnLogout;

    @FXML
    private StackPane stkpn;

    @FXML
    private AnchorPane pnViewApps;

    @FXML
    private ListView<String> lstAppData;

    @FXML
    private Button btnAppData;

    @FXML
    private Label lblAppData;

    @FXML
    private AnchorPane pnManageJobs;

    @FXML
    private Button btnViewApplied;

    @FXML
    private Button btnDistribute;

    @FXML
    private ListView<String> lstJobs;

    @FXML
    private Label lblChooseJob;

    @FXML
    private ListView<?> lstApplied;

    @FXML
    private AnchorPane pnAddJob;

    @FXML
    private Button btnAddStage;

    @FXML
    private TextField txtPosition;

    @FXML
    private TextField txtCompany;

    @FXML
    private TextField txtIntStage;

    @FXML
    private Button btnAddInt;

    @FXML
    private ComboBox<String> cmbInt;

    @FXML
    private ListView<String> lstIntStage;

    @FXML
    private Button btnCreateJob;

    @FXML
    private ListView<String> lstInterviewers;

    @FXML
    private DatePicker dpClose;

    @FXML
    private TextField txtNumPositions;

    private ObservableList<String> listStages = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle rb) {
        //TODO make this accommodate for Company
//        for (Interviewer interviewer : MainController.systemAdmin.getUserManager().getListInterviewers()) {
//            //listInterviewers.add(interviewer.getUsername());
//            cmbInt.getItems().add(interviewer.getUsername());
//        }
//
//        for (User user : MainController.systemAdmin.getUserManager().viewUsers()) {
//            if (user instanceof Applicant) {
//                lstAppData.getItems().add(user.getUsername());
//            }
//        }
    }

    public void loadCreateJob(ActionEvent event) throws Exception{
        pnAddJob.setVisible(true);
        pnManageJobs.setVisible(false);
        pnViewApps.setVisible(false);
        //ObservableList<String> chosenInterviewers = FXCollections.observableArrayList();
    }

    public void createJob(ActionEvent event) throws Exception{
        if (!listStages.isEmpty()){
            Date closeDate = Date.from(dpClose.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            String position = txtPosition.getText();
            String company = txtCompany.getText();
            int numPositions = Integer.parseInt(txtNumPositions.getText());
            ArrayList<String> listIntStages = new ArrayList<>();
            Job job = new Job(position, company, "tag", 0, listIntStages);

            for (String str: listStages){
                listIntStages.add(str);
            }

            ObservableList<String> decidedListOfInt = FXCollections.observableArrayList();
            ArrayList<Interviewer> chosenInterviewers = new ArrayList<>();
//            for (String str : decidedListOfInt) {
//                chosenInterviewers.add((Interviewer) MainController.systemAdmin.getUserManager().getUser(str));
//            }
//
//            MainController.systemAdmin.getJobManager().addJobPosting(job, closeDate, chosenInterviewers, numPositions);//---------------------------------------------
//            System.out.println("Added");
        }
    }

    public void addIntStage(ActionEvent event) throws Exception{
        String strISF = txtIntStage.getText();
        if (strISF != null && !strISF.isEmpty()) {
            listStages.add(strISF);
            lstIntStage.setItems(listStages);
        }
    }

    public void addInterviewer(ActionEvent event) throws Exception{
        ObservableList<String> decidedInterviewers = FXCollections.observableArrayList();
        String interviewerUsername = cmbInt.getValue();
        if (interviewerUsername != null && !interviewerUsername.isEmpty()) {
            boolean add = true;
            for (String str : decidedInterviewers) {
                if (str.equals(interviewerUsername)) {
                    add = false;
                }
            }
            if (add) {
                decidedInterviewers.add(interviewerUsername);
                lstInterviewers.setItems(decidedInterviewers);
            }
        }
    }

    public void manageJobs(ActionEvent event) throws Exception{
        pnManageJobs.setVisible(true);
        pnAddJob.setVisible(false);
        pnViewApps.setVisible(false);
        ObservableList<String> listJobs = FXCollections.observableArrayList();
//        for (JobPosting jobPosting : MainController.systemAdmin.getJobManager().viewClosedJobs()) {  //was ViewJobs before
//            System.out.println("p0");
//            listJobs.add(jobPosting.getJob().getPosition());
//            System.out.println(listJobs.add(jobPosting.getJob().getPosition()));
//        }
        lstJobs.setItems(listJobs);
    }

    public void viewApps(ActionEvent event) throws Exception{
        pnViewApps.setVisible(true);
        pnManageJobs.setVisible(false);
        pnAddJob.setVisible(false);
        //lstJobs.getItems().removeAll();
        //lstJobs.setItems();
    }

    public void viewInfo(ActionEvent event) throws Exception{
        //lblAppData.setText(((Applicant)(MainController.systemAdmin.getUserManager().getUser(lstAppData.getSelectionModel().getSelectedItem()))).getInfo());
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
