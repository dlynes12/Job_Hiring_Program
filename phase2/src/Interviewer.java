import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import java.util.*;

public class Interviewer extends User {
    private Map<String, ArrayList<Applicant>> applicantsList = new HashMap<>();
    private Company company;

    public Interviewer(String username, String password) {
        super(username, password);
    }

    void addToList(Applicant applicant, String position) {
        if (applicantsList.get(position) == null) {
            ArrayList<Applicant> updateList = new ArrayList<>();
            updateList.add(applicant);
            applicantsList.put(position, updateList);
        } else {
            ArrayList<Applicant> updateList = applicantsList.get(position);
            updateList.add(applicant);
            applicantsList.put(position, updateList);
        }
    }

    ArrayList<Applicant> getInterviewees(String position) {
        ArrayList<Applicant> result = this.applicantsList.get(position);
        return result;
    }

    public void clearInterviewees(String position) {
        ArrayList<Applicant> clearedList = new ArrayList<>();
        applicantsList.put(position, clearedList);
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Company getCompany() {
        return this.company;
    }

    void interviewerGUISetUp(Stage stage, User loggedUser, Company loggedCompany, SystemAdmin systemAdmin, Scene loginPage) {
        if (loggedUser.getClass() == Interviewer.class) {
            Group intPortalScene = new Group();
            Scene scene = new Scene(intPortalScene, 600, 600);
            stage.setScene(scene);
            String welcomeMessage = "Welcome to the Interviewer page, " + loggedUser.getUsername();
            Label welcomeLabel = new Label(welcomeMessage);
            Label chooseJobLab = new Label("Choose a job:");
            ComboBox dropdown = new ComboBox();
            GridPane interviewerSelectionPane = new GridPane();
            GridPane chooseJobPane = new GridPane();
            BorderPane interviewerPlacement = new BorderPane();
            Button getInterviewees = new Button("Get Interviewees");
            Button logout = new Button("Logout");
            Button approve = new Button("Approve");
            Button decline = new Button("Decline");
            interviewerSelectionPane.add(approve, 3, 1);
            interviewerSelectionPane.add(decline, 4, 1);
            interviewerSelectionPane.add(logout, 1, 3);
            interviewerSelectionPane.setHgap(20);
            interviewerSelectionPane.setVgap(20);
            interviewerPlacement.setTop(chooseJobPane);
            interviewerPlacement.setBottom(interviewerSelectionPane);
            chooseJobPane.add(welcomeLabel, 1, 0);
            chooseJobPane.add(chooseJobLab, 1, 2);
            chooseJobPane.add(dropdown, 2, 2);
            chooseJobPane.add(getInterviewees, 3, 2);
            chooseJobPane.setHgap(20);
            chooseJobPane.setVgap(5);

            for (String jobPosting : applicantsList.keySet()){
                dropdown.getItems().add(jobPosting);
            }

            getInterviewees.setOnAction((ActionEvent ev) -> {
                String choice = (String) dropdown.getValue();
                String[] listOfApp = systemAdmin.getJobManager().getClosedJob(choice, loggedCompany).viewApplicants(this, choice).split(",");
                if (listOfApp.length != 0 && !isNullOrEmpty(listOfApp[0])) {
                    Label chooseApp = new Label("Choose an Applicant:");
                    interviewerSelectionPane.add(chooseApp, 1, 0);
                    ListView<String> scrollListApps = new ListView<>();
                    ObservableList<String> listApps = FXCollections.observableArrayList();
                    scrollListApps.setItems(listApps);
                    scrollListApps.setPrefSize(100.00, 70.00);
                    for (String app : listOfApp) {
                        listApps.add(app);
                    }
                    interviewerSelectionPane.add(scrollListApps, 1, 1);

                    approve.setOnAction((ActionEvent click) -> {
                        String selectedApplicant = scrollListApps.getSelectionModel().getSelectedItem();
                        if (selectedApplicant.isEmpty()) {
                            systemAdmin.getAlert("create").showAndWait();
                        } else {
                            Applicant appObj = (Applicant) systemAdmin.getUserManager().getUser(selectedApplicant);
                            systemAdmin.getJobManager().getClosedJob(choice, loggedCompany).getHiringProcessor().nextRound(appObj);
                        }
                    });
                    decline.setOnAction((ActionEvent click) -> {
                        String selectedApplicant = scrollListApps.getSelectionModel().getSelectedItem();
                        Applicant appObj = (Applicant) systemAdmin.getUserManager().getUser(selectedApplicant);
                        systemAdmin.getJobManager().getClosedJob(choice, loggedCompany).getHiringProcessor().reject(appObj);
                    });
                }
            });

            logout.setOnAction((ActionEvent ex) -> stage.setScene(loginPage));

            intPortalScene.getChildren().addAll(interviewerPlacement);
        }
    }

    public String toString() {
        return "{I," + this.getUsername() + "," + this.getPassword() + "," + this.company.getCompanyName() + "}";
    }
}