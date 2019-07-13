import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class JobPortal extends Application {

    // SIGN-IN PAGE
    @Override

    //TODO: Before handing in please erase any dead code you see and fix all naming conventions <3

    public void start(Stage stage) throws Exception {
        UserAccess userManager = new UserAccess();
        JobAccess jobManager = new JobAccess();
        Storage storage = new Storage();
        Group loginScene = new Group();
        
        stage.setTitle("Job Application Portal");
        Scene loginPage = new Scene(loginScene, 600, 200);
        stage.setScene(loginPage);
        stage.show();
        Button log_in = new Button("Login");
        Button new_user = new Button("New User");
        RadioButton applicantButton = new RadioButton("Applicant");
        RadioButton hRButton = new RadioButton("HR");
        RadioButton interviewerButton = new RadioButton("Interviewer");
        ToggleGroup loginRadio = new ToggleGroup();
        applicantButton.setToggleGroup(loginRadio);
        hRButton.setToggleGroup(loginRadio);
        interviewerButton.setToggleGroup(loginRadio);
        Label labelUsername = new Label("Username");
        Label labelPassword = new Label("Password");
        GridPane gridPane = new GridPane();
        TextField username = new TextField();
        TextField password = new TextField();

        gridPane.add(labelUsername, 2, 0);
        gridPane.add(labelPassword, 2, 2);
        gridPane.add(username, 4, 0);
        gridPane.add(password, 4, 2);
        gridPane.add(applicantButton, 2, 7);
        gridPane.add(hRButton, 3, 7);
        gridPane.add(interviewerButton, 4, 7);
        gridPane.add(log_in, 7, 4);
        gridPane.add(new_user, 9, 4);
        gridPane.setHgap(10);

        StackPane box = new StackPane();
        box.getChildren().addAll(gridPane);
        loginScene.getChildren().addAll(box);

        //CREATE A USER PAGE
        new_user.setOnAction((ActionEvent AddUser) -> {
            Group createNewUser = new Group();
            stage.setScene(new Scene(createNewUser, 600, 600));
            Button create = new Button("Create");
            Label createUserLab = new Label("Choose a Username");
            Label createPassLab = new Label("Enter a Password");
            Label choice = new Label("Please choose the type of account you want:");
            TextField newUserField = new TextField();
            TextField newPassField = new TextField();
            RadioButton radioApp = new RadioButton("Applicant");
            RadioButton radioHR = new RadioButton("HR Coordinator");
            RadioButton radioInt = new RadioButton("Interviewer");
            Button exit = new Button("EXIT");
            ToggleGroup radioSet = new ToggleGroup(); // allows only one radio button to be selected at a time
            radioApp.setToggleGroup(radioSet);
            radioHR.setToggleGroup(radioSet);
            radioInt.setToggleGroup(radioSet);
            GridPane userInfo = new GridPane();
            GridPane choicePane = new GridPane();
            GridPane grid = new GridPane();

            userInfo.add(exit, 8, 4);
            userInfo.add(createUserLab, 2, 0);
            userInfo.add(createPassLab, 2, 2);
            userInfo.add(newUserField, 4, 0);
            userInfo.add(newPassField, 4, 2);
            choicePane.add(choice, 2, 0);
            grid.add(radioApp, 2, 0);
            grid.add(radioHR, 4, 0);
            grid.add(radioInt, 6, 0);
            grid.add(create, 7, 2);

            BorderPane placement = new BorderPane();
            userInfo.setHgap(20);
            userInfo.setVgap(5);
            choicePane.setHgap(20);
            grid.setHgap(20);
            placement.setTop(userInfo);
            placement.setCenter(choicePane);
            placement.setBottom(grid);
            createNewUser.getChildren().addAll(placement);

            exit.setOnAction((ActionEvent ex) -> stage.setScene(loginPage));

            create.setOnAction((ActionEvent ProcessUser) -> {
                if (radioSet.getSelectedToggle() == radioApp) {
                    Applicant tempApp = new Applicant(newUserField.getText(), newPassField.getText());
                    if (userManager.addUser(tempApp)) {
                        stage.setScene(loginPage);
                    }
                } else if (radioSet.getSelectedToggle() == radioHR) {
                    HR_Coordinator tempHR = new HR_Coordinator(newUserField.getText(), newPassField.getText());
                    if (userManager.addUser(tempHR)) {
                        stage.setScene(loginPage);
                    }
                } else if (radioSet.getSelectedToggle() == radioInt) {
                    Interviewer tempInt = new Interviewer(newUserField.getText(), newPassField.getText());
                    if (userManager.addUser(tempInt)) {
                        stage.setScene(loginPage);
                    }
                }
            });

        });

        log_in.setOnAction((ActionEvent e) -> {
            String UName = username.getText();
            String Pass = password.getText();
            User loggedUser = userManager.login(UName, Pass); // the user that is actually logged in
            if (loggedUser != null) {
                if (loginRadio.getSelectedToggle() == applicantButton) {
                    Group applicantPortalScene = new Group();
                    Scene applicantPage = new Scene(applicantPortalScene, 600, 600);
                    stage.setScene(applicantPage);
                    if (loggedUser.getClass() == Applicant.class) {
                        GridPane applicantSelectionPane = new GridPane();

                        //TODO: Add a display for resume

                        Button getResume = new Button("Submit your resume");
                        Button applyJob = new Button("Apply to jobs");
                        Button getFile = new Button("Open your resume from file");
                        Button viewHistory = new Button("Account History");
                        Button viewJobStatuses = new Button("Job Statuses");
                        Button logout = new Button("Logout");

                        TextField resume = new TextField();
                        getFile.setOnAction((ActionEvent openFile) -> {
                            FileChooser fileChooser = new FileChooser();
                            //fileChooser.setInitialDirectory();
                            fileChooser.setTitle("Open Resume File");
                            fileChooser.setInitialFileName(loggedUser.getUsername());
                            File file = fileChooser.showOpenDialog(stage);
                            System.out.println(file);
                        });
                        getResume.setOnAction((ActionEvent eve) -> {
                            String resumeText = resume.getText();
                            storage.writeFile(loggedUser.getUsername(), resumeText);
                        });

                        Label labelFileUpload = new Label("Submit your resume");
                        Label labelEnterResume = new Label("Enter your resume");

                        applicantSelectionPane.add(logout, 4, 9);
                        applicantSelectionPane.add(getFile, 4, 2);
                        applicantSelectionPane.add(getResume, 4, 4);
                        applicantSelectionPane.add(labelFileUpload, 2, 2);
                        applicantSelectionPane.add(resume, 4, 3);
                        applicantSelectionPane.add(labelEnterResume, 2, 4);
                        applicantSelectionPane.add(applyJob, 4, 6);
                        applicantSelectionPane.add(viewHistory, 4, 7);
                        applicantSelectionPane.add(viewJobStatuses, 4, 8);


                        StackPane resumeUpload = new StackPane();
                        applicantSelectionPane.setHgap(20);
                        applicantSelectionPane.setVgap(20);

                        logout.setOnAction((ActionEvent ex) -> stage.setScene(loginPage));

                        applyJob.setOnAction((ActionEvent apply) -> {
                            Integer i = 0;
                            Group jobPortalScene = new Group();
                            stage.setScene(new Scene(jobPortalScene, 600, 600));
                            Button returnApp = new Button("Back");
                            Button applyButton = new Button("Apply");
                            GridPane jobViewer = new GridPane();

                            ToggleGroup radioSet = new ToggleGroup(); // allows only one radio button to be selected at a time
                            for (JobPosting jP : jobManager.ViewJobs()) {
                                RadioButton radioButton = new RadioButton(jP.getPosition());
                                radioButton.setToggleGroup(radioSet);
                                jobViewer.add(radioButton, 0, i + 1);
                                i++;
                            }

                            jobViewer.add(applyButton, 4, 5);
                            jobViewer.add(returnApp, 4, 6);

                            jobViewer.setHgap(10);
                            jobViewer.setVgap(5);
                            jobPortalScene.getChildren().add(jobViewer);
                            stage.show();


                            applyButton.setOnAction((ActionEvent event) -> {
                                //System.out.println(radioSet.getSelectedToggle());
                                Applicant a = (Applicant) loggedUser;
                                String selectedRadio = (((RadioButton) radioSet.getSelectedToggle()).getText());
                                //jobManager.getJob(selectedRadio).addApplicant(a);
                                a.applyToJob(jobManager.getJob(selectedRadio));
                                stage.setScene(loginPage);
                            });

                            returnApp.setOnAction((ActionEvent ex) -> {
                                stage.setScene(applicantPage);
                            });

                        });

                        //Account History page
                        viewHistory.setOnAction((ActionEvent history) -> {
                            Group historyViewerScene = new Group();
                            Scene historyPage = new Scene(historyViewerScene, 600, 600);
                            stage.setScene(historyPage);

                            Label accountInfo = new Label(((Applicant)loggedUser).getInfo());
                            Button returnButton = new Button("Back");
                            GridPane historyPane = new GridPane();

                            historyPane.add(accountInfo, 10, 0);
                            historyPane.add(returnButton, 10, 14);

                            historyPane.setHgap(20);
                            historyPane.setVgap(5);

                            historyViewerScene.getChildren().addAll(historyPane);

                            returnButton.setOnAction((ActionEvent goBack) -> {
                                stage.setScene(applicantPage);
                            });

                        });

                        viewJobStatuses.setOnAction((ActionEvent jobStatuses) -> {
                            Group jobStatusViewerScene = new Group();
                            Scene jobStatusPage = new Scene(jobStatusViewerScene, 600, 600);
                            stage.setScene(jobStatusPage);


                            Label jobStatusesLabel = new Label("Jobs Applied For:");
                            Label allJobStatuses = new Label(((Applicant) loggedUser).getJobStatus());
                            Button returnJS = new Button("Back");
                            GridPane jobStatusPane = new GridPane();

                            jobStatusPane.add(jobStatusesLabel, 10, 3);
                            jobStatusPane.add(allJobStatuses, 10, 4);
                            jobStatusPane.add(returnJS, 10, 14);

                            jobStatusPane.setHgap(20);
                            jobStatusPane.setVgap(5);

                            jobStatusViewerScene.getChildren().addAll(jobStatusPane);

                            returnJS.setOnAction((ActionEvent goBack) -> {
                                stage.setScene(applicantPage);
                            });

                        });


                        resumeUpload.getChildren().addAll(applicantSelectionPane);
                        applicantPortalScene.getChildren().addAll(resumeUpload);
                    }// what happens if they are not an applicant but have a login;
                    //send message wrong user type?
                    else {
                        stage.setScene(loginPage);
                    }
                } else if (loginRadio.getSelectedToggle() == hRButton) {
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
                                jobManager.addJob(today, closeDate, position, 0);
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
                } else if (loginRadio.getSelectedToggle() == interviewerButton) { ///////////////////////////////////////
                    Group intPortalScene = new Group();
                    stage.setScene(new Scene(intPortalScene, 600, 600));
                    if (loggedUser.getClass() == Interviewer.class) {
                        GridPane interviewerSelectionPane = new GridPane();

                        String welcomeMessage = "Welcome to the Interviewer page, " + loggedUser.getUsername();
                        Label welcomeLabel = new Label(welcomeMessage);

                        Button getInterviewees = new Button("Get Interviewees");
                        Button approve = new Button("Approve");
                        Button decline = new Button("Decline");
                        Button logout = new Button("Logout");

                        interviewerSelectionPane.add(logout, 1, 3);
                        interviewerSelectionPane.add(getInterviewees, 1, 1);
                        interviewerSelectionPane.add(approve, 5, 1);
                        interviewerSelectionPane.add(decline, 5, 2);
                        interviewerSelectionPane.add(welcomeLabel, 1, 0);

                        interviewerSelectionPane.setHgap(20);
                        interviewerSelectionPane.setVgap(20);

                        getInterviewees.setOnAction((ActionEvent ev) -> {
                            Integer i = 0;
                            for (JobPosting jP : jobManager.ViewJobs()) {
                                RadioButton radioButton = new RadioButton(jP.getPosition());
                                interviewerSelectionPane.add(radioButton, 0, i + 1);
                                i++;
                            }

                        });

                        logout.setOnAction((ActionEvent ex) -> stage.setScene(loginPage));

                        //getInterviewees.setOnAction((ActionEvent click) -> ((Interviewer)loggedUser).getInterviewees());

                        //approve.setOnAction((ActionEvent click) -> ((Interviewer)loggedUser).recommend());

                        //decline.setOnAction((ActionEvent click) -> ((Interviewer)loggedUser).decline());


                        intPortalScene.getChildren().addAll(interviewerSelectionPane);

                    }

                    else {
                        stage.setScene(loginPage);
                    }
                }
            }
        });
        // View + Controller
    }

    public static void main(String[] args) {
        launch(args);
    }
}


