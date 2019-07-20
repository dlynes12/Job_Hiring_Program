import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

// One HR Person hires for their own section of the company
public class HR_Coordinator extends User{


    public HR_Coordinator(String Username,String Password){
        super(Username,Password);
    }

    public void matchItoA(Interviewer interviewer, Applicant applicant, JobPosting jobPosting){
        // loop thru list of applicants,
        // call addPool()
        // interviewer.addApplicant(applicant);
    }

    void HRGUISetUp(Stage stage, User loggedUser, JobAccess jobManager, Scene loginPage, Storage storage, UserAccess userManager)
    {
        Group HRPortalScene = new Group();
        Scene HRBasePage = new Scene(HRPortalScene, 450, 250);
        stage.setScene(HRBasePage);
        if (loggedUser.getClass() == HR_Coordinator.class) {
            String welcomeMessage = "Welcome to the Human Resources page, " + loggedUser.getUsername();
            Label welcomeLabel = new Label(welcomeMessage);
            Label actions = new Label("What do you want to do? Please select an option below:");
            Button addJobs = new Button("Add a job");
            Button viewOpenJobs = new Button("View all open jobs"); // for a specific job, view the applicants
            Button viewAllApps = new Button("View all applicants"); // for a specific applicant, view the jobs they applied for
            Button logout = new Button("Logout");
            GridPane messageGrid = new GridPane();
            GridPane buttonGrid = new GridPane();

            messageGrid.add(welcomeLabel, 1, 0);
            messageGrid.add(actions, 1, 2);
            buttonGrid.add(addJobs, 1, 0);
            buttonGrid.add(viewOpenJobs, 2, 0);
            buttonGrid.add(viewAllApps, 1, 2);
            buttonGrid.add(logout, 2, 2);

            BorderPane HRPlacement = new BorderPane();
            messageGrid.setHgap(20);
            messageGrid.setVgap(5);
            buttonGrid.setHgap(20);
            buttonGrid.setVgap(5);
            HRPlacement.setTop(messageGrid);
            HRPlacement.setBottom(buttonGrid);

            HRPortalScene.getChildren().addAll(HRPlacement);

            logout.setOnAction((ActionEvent ex) -> {
                stage.setScene(loginPage);
            });

            //Job Creation page -- where we create job postings
            addJobs.setOnAction((ActionEvent addJob) -> {
                Group createJobs = new Group();
                Scene createJobsPage = new Scene(createJobs, 600, 600);
                stage.setScene(createJobsPage);
                // get today's date
                Date today = new Date();
                Instant now = Instant.now();
                Date.from(now);

                //-------------
                //Date closingDate = new Date(int year,int month,int day);
                DatePicker datePicker = new DatePicker();
                Label closingMessage = new Label("Closing Date:");
                Label positionLabel = new Label("What position are we creating?");
                TextField positionField = new TextField();
                TextField companyField = new TextField();
                Button createNewPost = new Button("Create job");
                Button returnAddJ = new Button("Back");
                GridPane cMessageGrid = new GridPane();
                GridPane dateGrid = new GridPane();
                GridPane positionGrid = new GridPane();

                cMessageGrid.add(closingMessage, 1, 0);
                dateGrid.add(closingMessage, 1, 0);
                dateGrid.add(datePicker, 2, 0);
                positionGrid.add(positionLabel, 1, 0);
                positionGrid.add(positionField, 2, 0);
                positionGrid.add(companyField, 3,0);
                positionGrid.add(createNewPost, 1, 2);
                positionGrid.add(returnAddJ, 1, 4);

                cMessageGrid.setHgap(20);
                cMessageGrid.setVgap(5);
                dateGrid.setHgap(20);
                dateGrid.setVgap(5);
                positionGrid.setHgap(20);
                positionGrid.setVgap(5);

                BorderPane CreateJobPlacement = new BorderPane();

                CreateJobPlacement.setCenter(dateGrid);
                CreateJobPlacement.setBottom(positionGrid);

                createJobs.getChildren().addAll(CreateJobPlacement);

                createNewPost.setOnAction((ActionEvent CreateJob) -> {
                    LocalDate year = datePicker.getValue();
                    Date closeDate = null;
                    closeDate = Date.from(datePicker.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                    String position = positionField.getText();
                    String company = companyField.getText();
                    jobManager.addJob(today, closeDate, position, 0, company);
                    stage.setScene(HRBasePage);

                });

                returnAddJ.setOnAction((ActionEvent exitPage) -> {
                    stage.setScene(HRBasePage);
                });
            });

            viewOpenJobs.setOnAction((ActionEvent viewJob) -> {
                Group HRViewJobs = new Group();
                Scene createJobsPage = new Scene(HRViewJobs, 600, 600);
                stage.setScene(createJobsPage);
                Label ChooseJob = new Label("Choose a job:");
                ComboBox dropdown = new ComboBox();
                for (JobPosting jobPosting : jobManager.ViewJobs()) {
                    dropdown.getItems().add(jobPosting.getPosition());
                }
                Button ApplicantButton = new Button("See applicants");
                Button returnViewJ = new Button("Back");
                GridPane ViewJobsGrid = new GridPane();

                ViewJobsGrid.add(ChooseJob, 1, 0);
                ViewJobsGrid.add(dropdown, 2, 0);
                ViewJobsGrid.add(ApplicantButton, 3, 0);
                ViewJobsGrid.add(returnViewJ, 4, 0);

                ViewJobsGrid.setHgap(20);
                ViewJobsGrid.setVgap(5);
                BorderPane ViewJobsPlacement = new BorderPane();
                ViewJobsPlacement.setTop(ViewJobsGrid);
                HRViewJobs.getChildren().add(ViewJobsPlacement);

                returnViewJ.setOnAction((ActionEvent exitPage) -> {
                    stage.setScene(HRBasePage);
                });

                ApplicantButton.setOnAction((ActionEvent seeApps) -> {
                    Integer i = 0;
                    String choice = (String) dropdown.getValue();
                    String[] listOfApp = jobManager.getJob(choice).viewApplicants().split(",");
                    if (listOfApp.length != 0 && listOfApp[0] != "") {
                        GridPane appViewer = new GridPane();
                        ToggleGroup radioSet = new ToggleGroup(); // allows only one radio button to be selected at a time
                        Label chooseApp = new Label("Choose an Applicant:");
                        appViewer.add(chooseApp, 1, i);
                        for (String app : listOfApp) {
                            RadioButton radioButton = new RadioButton(app);
                            radioButton.setToggleGroup(radioSet);
                            appViewer.add(radioButton, 1, i + 1);
                            i++;
                        }
                        Button viewButton = new Button("VIEW INFO");
                        appViewer.add(viewButton, 3, i + 1);
                        appViewer.setHgap(20);
                        appViewer.setVgap(5);
                        ViewJobsPlacement.setBottom(appViewer);

                        viewButton.setOnAction((ActionEvent ViewAppDocs) -> {
                            // see the specific applicant's documents
                        });
                    }
                });
            });

            viewAllApps.setOnAction((ActionEvent viewAllApplicants) -> {
                Group viewApps = new Group();
                Scene createViewAppsPage = new Scene(viewApps, 600, 600);
                stage.setScene(createViewAppsPage);
                ComboBox dropApp = new ComboBox();
                for (User user : userManager.viewUsers()) {
                    if (user instanceof Applicant) {
                        dropApp.getItems().add(user.getUsername());
                    }
                }
                Label appInfo = new Label("");
                Button returnViewApp = new Button("Back");
                Button viewButton = new Button("View applicants");
                GridPane ViewAppsGrid = new GridPane();

                ViewAppsGrid.add(dropApp, 10, 0);
                ViewAppsGrid.add(viewButton, 10, 1);
                ViewAppsGrid.add(appInfo, 10, 2);
                ViewAppsGrid.add(returnViewApp, 10, 15);

                ViewAppsGrid.setHgap(20);
                ViewAppsGrid.setVgap(5);
                BorderPane ViewJobsPlacement = new BorderPane();
                ViewJobsPlacement.setTop(ViewAppsGrid);
                viewApps.getChildren().add(ViewJobsPlacement);

                returnViewApp.setOnAction((ActionEvent exitPage) -> {
                    stage.setScene(HRBasePage);
                });

                viewButton.setOnAction((ActionEvent seeApps) -> {
                    appInfo.setText(((Applicant)(userManager.getUser((String) dropApp.getValue()))).getInfo());
                    //appInfo.setText(((Applicant)userManager.getUser((String)dropApp.getValue())).getJobs());

                });
            });
        }// what happens if they are not an HR Coordinator but have a login;
        //send message wrong user type?
        else {
            stage.setScene(loginPage);
        }
    }

    public String toString(){
        return "*********** \n" + this.getUsername() + "\n" + this.getPassword() + "\n" + "***********";

    }


}
