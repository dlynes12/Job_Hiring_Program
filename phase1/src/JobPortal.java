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
import java.util.Date;

public class JobPortal extends Application {

    // SIGNIN PAGE
    @Override
    public void start(Stage stage) throws Exception {
        UserAccess userManager = new UserAccess();
        ApplicationModel applicationModel = new ApplicationModel();
        //JobPosting jobPosting = new JobPosting();
        Group loginScene = new Group();
        stage.setTitle("Job Application Portal");
        Scene loginPage = new Scene(loginScene, 600, 600);
        stage.setScene(loginPage);
        stage.show();
        Button log_in = new Button("Log in");
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
        gridPane.add(new_user,9,4);
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

            userInfo.add(exit,8,4);
            userInfo.add(createUserLab,2,0);
            userInfo.add(createPassLab,2,2);
            userInfo.add(newUserField,4,0);
            userInfo.add(newPassField,4,2);
            choicePane.add(choice,2,0);
            grid.add(radioApp,2,0);
            grid.add(radioHR,4,0);
            grid.add(radioInt,6,0);
            grid.add(create,7,2);

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

            create.setOnAction((ActionEvent ProcessUser) ->{
                if ((RadioButton) radioSet.getSelectedToggle() == radioApp){
                    Applicant tempApp = new Applicant(newUserField.getText(), newPassField.getText());
                    if (userManager.addUser(tempApp)){
                        stage.setScene(loginPage);
                    }
                }else if (radioSet.getSelectedToggle() == radioHR){
                    HR_Coordinator tempHR = new HR_Coordinator(newUserField.getText(), newPassField.getText());
                    if (userManager.addUser(tempHR)){
                        stage.setScene(loginPage);
                    }
                }else if ((RadioButton) radioSet.getSelectedToggle() == radioInt){
                    Interviewer tempInt = new Interviewer(newUserField.getText(), newPassField.getText());
                    if (userManager.addUser(tempInt)){
                        stage.setScene(loginPage);
                    }
                }
            } );

        });

        //TODO: Fix all the naming conventions for the variables

        log_in.setOnAction((ActionEvent e) -> {
            String UName = username.getText();
            String Pass = password.getText();
            User loggedUser = userManager.LogInUser(UName,Pass); // the user that is actually logged in
            if(loggedUser!= null) {
                if (loginRadio.getSelectedToggle() == applicantButton){
                    Group applicantPortalScene = new Group();
                    stage.setScene(new Scene(applicantPortalScene, 600, 600));
                    if (loggedUser.getClass() == Applicant.class){
                        GridPane applicantSelectionPane = new GridPane();

                        Button getResume = new Button("Submit your resume");
                        Button applyJob = new Button("Apply to jobs");
                        Button getFile = new Button("Open your resume from file");
                        Button exit = new Button("EXIT");

                        TextField resume = new TextField();
                        getFile.setOnAction((ActionEvent openFile) -> {
                            FileChooser fileChooser = new FileChooser();
                            //fileChooser.setInitialDirectory();
                            fileChooser.setTitle("Open Resume File");
                            fileChooser.setInitialFileName(loggedUser.getUsername());
                            File file = fileChooser.showOpenDialog(stage);
                            System.out.println(file);
                        });

                        Label labelFileUpload = new Label("Submit Your resume");
                        Label labelEnterResume = new Label("Enter your resume");

                        applicantSelectionPane.add(exit,8,4);
                        applicantSelectionPane.add(getFile,2,2);
                        applicantSelectionPane.add(getResume,2,4);
                        applicantSelectionPane.add(labelFileUpload,0,2);
                        applicantSelectionPane.add(resume,2,3);
                        applicantSelectionPane.add(labelEnterResume,0,4);
                        applicantSelectionPane.add(applyJob,2,6);

                        StackPane resumeUpload = new StackPane();
                        applicantSelectionPane.setHgap(20);
                        applicantSelectionPane.setVgap(20);

                        exit.setOnAction((ActionEvent ex) -> stage.setScene(loginPage));

                        applyJob.setOnAction((ActionEvent apply) -> {
                            Group jobPortalScene = new Group();
                            stage.setScene(new Scene(jobPortalScene,800,800));
                            //for (JobPosting jP: jobPosting.jobpostings){
                            //      Text txt+i.toString = new Text(jb.toString);
                            //      Button btn+i.toString();
                            //
                            //      i++;
                            // }

                        });

                        resumeUpload.getChildren().addAll(applicantSelectionPane);
                        applicantPortalScene.getChildren().addAll(resumeUpload);
                    }// what happens if they are not an applicant but have a login;
                    //send message wrong user type?
                    else{stage.setScene(loginPage);}
                }else if (loginRadio.getSelectedToggle() == hRButton){
                    Group HRPortalScene = new Group();
                    Scene HRBasePage = new Scene(HRPortalScene, 450, 250);
                    stage.setScene(HRBasePage);
                    if (loggedUser.getClass() == HR_Coordinator.class){
                        String welcomeMessage = "Welcome to the Human Resource Page: " + loggedUser.getUsername();
                        Label welcomeLabel = new Label(welcomeMessage);
                        Label actions = new Label("What do you want to do? Please select an option below:");
                        Button addJobs = new Button("ADD A JOB");
                        Button viewOpenJobs = new Button("VIEW ALL OPEN JOBS");
                        Button exit = new Button("EXIT");
                        GridPane messageGrid = new GridPane();
                        GridPane buttonGrid = new GridPane();

                        messageGrid.add(welcomeLabel,1,0);
                        messageGrid.add(actions,1,2);
                        buttonGrid.add(addJobs,1,0);
                        buttonGrid.add(viewOpenJobs, 2,0);
                        buttonGrid.add(exit, 4,2);

                        BorderPane HRPlacement = new BorderPane();
                        messageGrid.setHgap(20);
                        messageGrid.setVgap(5);
                        buttonGrid.setHgap(20);
                        buttonGrid.setVgap(5);
                        HRPlacement.setTop(messageGrid);
                        HRPlacement.setBottom(buttonGrid);

                        HRPortalScene.getChildren().addAll(HRPlacement);

                        exit.setOnAction((ActionEvent ex) -> {
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
                            today.from(now);
                            //-------------
                            //Date closingDate = new Date(int year,int month,int day);
                            DatePicker datePicker = new DatePicker();
                            Label closingMessage = new Label("Closing Date:");
                            Label positionLabel = new Label("What position are we creating?");
                            TextField positionField = new TextField();
                            Button createNewPost = new Button("CREATE JOB");
                            GridPane cMessageGrid = new GridPane();
                            GridPane dateGrid = new GridPane();
                            GridPane positionGrid = new GridPane();

                            cMessageGrid.add(closingMessage,1,0);
                            dateGrid.add(closingMessage,1,0);
                            dateGrid.add(datePicker,2,0);
                            positionGrid.add(positionLabel,1,0);
                            positionGrid.add(positionField,2,0);
                            positionGrid.add(createNewPost,4,2);

                            cMessageGrid.setHgap(20);
                            cMessageGrid.setVgap(5);
                            dateGrid.setHgap(20);
                            dateGrid.setVgap(5);
                            positionGrid.setHgap(20);
                            positionGrid.setVgap(5);

                            BorderPane CreateJobPlacement =new BorderPane();

                            CreateJobPlacement.setCenter(dateGrid);
                            CreateJobPlacement.setBottom(positionGrid);

                            createJobs.getChildren().addAll(CreateJobPlacement);

                            createNewPost.setOnAction((ActionEvent CreateJob) ->{
                                LocalDate year = datePicker.getValue();
                                Date closeDate = null;
                                try {
                                    closeDate = new SimpleDateFormat("yyyy-MM-dd").parse(datePicker.toString());
                                } catch (ParseException ex) {
                                    ex.printStackTrace();
                                }
                                String position = positionField.getText();
                                userManager.addJob(today,closeDate,position);
                                stage.setScene(HRBasePage);

                            });

                            // where we create job postings
                        } );

                        viewOpenJobs.setOnAction((ActionEvent viewJob) ->{
                            Group HRViewJobs = new Group();
                            Scene createJobsPage = new Scene(HRViewJobs, 600, 600);
                            stage.setScene(createJobsPage);
                            ComboBox Dropdown = new ComboBox();
                            for (JobPosting jobPosting: userManager.ViewJobs()){
                                Dropdown.getItems().add(jobPosting.getPosition());
                            }
                            Button ApplicantButton = new Button("SEE APPLICANTS");
                            Button Exit = new Button("EXIT");
                            GridPane ViewJobsGrid = new GridPane();

                            ViewJobsGrid.add(Dropdown,1,0);
                            ViewJobsGrid.add(ApplicantButton,2,0);
                            ViewJobsGrid.add(Exit,3,0);

                            ViewJobsGrid.setHgap(20);
                            ViewJobsGrid.setVgap(5);
                            HRViewJobs.getChildren().add(ViewJobsGrid);

                            Exit.setOnAction((ActionEvent exitPage) -> {
                                stage.setScene(HRBasePage);
                            });


                        });
                    }// what happens if they are not an HR Coordinator but have a login;
                    //send message wrong user type?
                    else {stage.setScene(loginPage);}
                }else if (loginRadio.getSelectedToggle() == interviewerButton){ ///////////////////////////////////////
                    Group intPortalScene = new Group();
                    stage.setScene(new Scene(intPortalScene, 600, 600));
                    if (loggedUser.getClass() == Interviewer.class){
                        GridPane interviewerSelectionPane = new GridPane();

                        String welcomeMessage = "Welcome to the Interviewer Page: " + loggedUser.getUsername();
                        Label welcomeLabel = new Label(welcomeMessage);

                        Button addApplicant = new Button("Add Applicant");
                        Button getInterviewees = new Button("Get Interviewees");
                        Button approve = new Button("Approve");
                        Button decline = new Button("Decline");
                        Button exit = new Button("EXIT");

                        interviewerSelectionPane.add(exit,8,4);
                        interviewerSelectionPane.add(addApplicant, 1,2);
                        interviewerSelectionPane.add(getInterviewees,1,1);
                        interviewerSelectionPane.add(approve,5,1);
                        interviewerSelectionPane.add(decline,5,2);
                        interviewerSelectionPane.add(welcomeLabel ,1,0);

                        interviewerSelectionPane.setHgap(20);
                        interviewerSelectionPane.setVgap(20);

                        exit.setOnAction((ActionEvent ex) -> stage.setScene(loginPage));
                        intPortalScene.getChildren().addAll(interviewerSelectionPane);
                        getInterviewees.setOnAction((ActionEvent click) -> {
                            ((Interviewer)loggedUser).getInterviewees();
                        });

                    }// what happens if they are not an interviewer but have a login;
                    //send message wrong user type?
                    else{stage.setScene(loginPage);}
                }
        }});
        // View + Controller
    }
    public static void main(String[] args) {
        launch(args);
    }
}


