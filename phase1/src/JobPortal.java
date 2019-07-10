import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.time.Instant;
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
        Scene LoginPage = new Scene(loginScene, 600, 600);
        stage.setScene(LoginPage);
        stage.show();
        Button log_in = new Button("Log in");
        Button new_user = new Button("New User");
        RadioButton applicantButton = new RadioButton("Applicant");
        RadioButton hRButton = new RadioButton("HR");
        RadioButton interviewerButton = new RadioButton("Interviewer");
        ToggleGroup LoginRadio = new ToggleGroup();
        applicantButton.setToggleGroup(LoginRadio);
        hRButton.setToggleGroup(LoginRadio);
        interviewerButton.setToggleGroup(LoginRadio);
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
            Group CreateNewUser = new Group();
            stage.setScene(new Scene(CreateNewUser, 600, 600));
            Button create = new Button("Create");
            Label createUserLab = new Label("Choose a Username");
            Label createPassLab = new Label("Enter a Password");
            Label choice = new Label("Please choose the type of account you want:");
            TextField NewUserField = new TextField();
            TextField NewPassField = new TextField();
            RadioButton RadioApp = new RadioButton("Applicant");
            RadioButton RadioHR = new RadioButton("HR Coordinator");
            RadioButton RadioInt = new RadioButton("Interviewer");
            ToggleGroup RadioSet = new ToggleGroup(); // allows only one radio button to be selected at a time
            RadioApp.setToggleGroup(RadioSet);
            RadioHR.setToggleGroup(RadioSet);
            RadioInt.setToggleGroup(RadioSet);
            GridPane UserInfo = new GridPane();
            GridPane choicePane = new GridPane();
            GridPane grid = new GridPane();
            UserInfo.add(createUserLab,2,0);
            UserInfo.add(createPassLab,2,2);
            UserInfo.add(NewUserField,4,0);
            UserInfo.add(NewPassField,4,2);
            choicePane.add(choice,2,0);
            grid.add(RadioApp,2,0);
            grid.add(RadioHR,4,0);
            grid.add(RadioInt,6,0);
            grid.add(create,7,2);

            BorderPane placement = new BorderPane();
            UserInfo.setHgap(20);
            UserInfo.setVgap(5);
            choicePane.setHgap(20);
            grid.setHgap(20);
            placement.setTop(UserInfo);
            placement.setCenter(choicePane);
            placement.setBottom(grid);
            CreateNewUser.getChildren().addAll(placement);

            create.setOnAction((ActionEvent ProcessUser) ->{
                if ((RadioButton) RadioSet.getSelectedToggle() == RadioApp){
                    Applicant tempApp = new Applicant(NewUserField.getText(), NewPassField.getText());
                    if (userManager.addUser(tempApp)){
                        stage.setScene(LoginPage);
                    }
                }else if (RadioSet.getSelectedToggle() == RadioHR){
                    HR_Coordinator tempHR = new HR_Coordinator(NewUserField.getText(), NewPassField.getText());
                    if (userManager.addUser(tempHR)){
                        stage.setScene(LoginPage);
                    }
                }else if ((RadioButton) RadioSet.getSelectedToggle() == RadioInt){
                    Interviewer tempInt = new Interviewer(NewUserField.getText(), NewPassField.getText());
                    if (userManager.addUser(tempInt)){
                        stage.setScene(LoginPage);
                    }

                }
            } );

        });


        log_in.setOnAction((ActionEvent e) -> {
            String UName = username.getText();
            String Pass = password.getText();
            User LoggedUser = userManager.LogInUser(UName,Pass); // the user that is actually logged in
            if(LoggedUser!= null) {
                if (LoginRadio.getSelectedToggle() == applicantButton){
                    Group applicantPortalScene = new Group();
                    stage.setScene(new Scene(applicantPortalScene, 600, 600));
                    if (LoggedUser.getClass() == Applicant.class){
                        GridPane applicantSelectionPane = new GridPane();
                        Button getResume = new Button("Submit your resume");
                        Button applyJob = new Button("Apply to jobs");
                        Button getFile = new Button("Open your resume from file");
                        Button exit = new Button("EXIT");
                        applicantSelectionPane.add(exit,8,4);
                        TextField resume = new TextField();
                        getFile.setOnAction((ActionEvent openFile) -> {
                            FileChooser fileChooser = new FileChooser();
                            //fileChooser.setInitialDirectory();
                            fileChooser.setTitle("Open Resume File");
                            fileChooser.setInitialFileName(LoggedUser.getUsername());
                            File file = fileChooser.showOpenDialog(stage);
                            System.out.println(file);

                        });

                        exit.setOnAction((ActionEvent ex) -> stage.setScene(LoginPage));

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


                        Label labelFileUpload = new Label("Submit Your resume");
                        Label labelEnterResume = new Label("Enter your resume");
                        applicantSelectionPane.add(getFile,2,2);
                        applicantSelectionPane.add(getResume,2,4);
                        applicantSelectionPane.add(labelFileUpload,0,2);
                        applicantSelectionPane.add(resume,2,3);
                        applicantSelectionPane.add(labelEnterResume,0,4);
                        applicantSelectionPane.add(applyJob,2,6);
                        StackPane resumeUpload = new StackPane();
                        applicantSelectionPane.setHgap(20);
                        applicantSelectionPane.setVgap(20);
                        resumeUpload.getChildren().addAll(applicantSelectionPane);


                        applicantPortalScene.getChildren().addAll(resumeUpload);
                    }// what happens if they are not an applicant but have a login;
                    //send message wrong user type?
                    else{stage.setScene(LoginPage);}
                }else if (LoginRadio.getSelectedToggle() == hRButton){
                    Group HRPortalScene = new Group();
                    Scene HRBasePage = new Scene(HRPortalScene, 450, 250);
                    stage.setScene(HRBasePage);
                    if (LoggedUser.getClass() == HR_Coordinator.class){
                        String WelcomeMessage = "Welcome to the Human Resource Page: " + LoggedUser.getUsername();
                        Label welcomeLabel = new Label(WelcomeMessage);
                        Label Actions = new Label("What do you want to do? Please select an option below:");
                        Button AddJObs = new Button("ADD A JOB");
                        Button ViewOpenJobs = new Button("VIEW ALL OPEN JOBS");
                        Button Exit = new Button("EXIT");
                        GridPane MessageGrid = new GridPane();
                        GridPane ButtonGrid = new GridPane();
                        MessageGrid.add(welcomeLabel,1,0);
                        MessageGrid.add(Actions,1,2);
                        ButtonGrid.add(AddJObs,1,0);
                        ButtonGrid.add(ViewOpenJobs, 2,0);
                        ButtonGrid.add(Exit, 4,2);

                        BorderPane HRPlacement = new BorderPane();
                        MessageGrid.setHgap(20);
                        MessageGrid.setVgap(5);
                        ButtonGrid.setHgap(20);
                        ButtonGrid.setVgap(5);
                        HRPlacement.setTop(MessageGrid);
                        HRPlacement.setBottom(ButtonGrid);


                        HRPortalScene.getChildren().addAll(HRPlacement);

                        Exit.setOnAction((ActionEvent exit) -> {
                            stage.setScene(LoginPage);
                        });

                        AddJObs.setOnAction((ActionEvent addjob) -> {
                            Group CreateJobs = new Group();
                            Scene CreateJobsPage = new Scene(CreateJobs, 600, 600);
                            stage.setScene(CreateJobsPage);
                            // get today's date
                            Date today = new Date();
                            Instant now = Instant.now();
                            today.from(now);
                            //-------------
                            //Date closingDate = new Date(int year,int month,int day);
                            Label ClosingMessage = new Label("Please enter a date you would like this Posting to close:");
                            Label YearLabel = new Label("Year");
                            Label MonthLabel = new Label("Month");
                            Label DayLabel = new Label("Day");
                            Label PositionLabel = new Label("What position are we creating?");
                            TextField YearField = new TextField();
                            YearField.setPromptText("Please enter a number in format: YYYY");
                            YearField.setPrefWidth(300);
                            TextField MonthField = new TextField();
                            MonthField.setPromptText("Please enter a number between 1 and 12");
                            TextField DayField = new TextField();
                            DayField.setPromptText("Please enter a number in format: dd");
                            TextField PositionField = new TextField();
                            Button CreateNewPost = new Button("CREATE JOB");
                            GridPane CMessageGrid = new GridPane();
                            GridPane DateGrid = new GridPane();
                            GridPane PositionGrid = new GridPane();
                            CMessageGrid.add(ClosingMessage,1,0);
                            CMessageGrid.setHgap(20);
                            CMessageGrid.setVgap(5);
                            DateGrid.add(YearLabel,1,0);
                            DateGrid.add(YearField,2,0);
                            DateGrid.add(MonthLabel,1,2);
                            DateGrid.add(MonthField,2,2);
                            DateGrid.add(DayLabel,1,4);
                            DateGrid.add(DayField,2,4);
                            DateGrid.setHgap(20);
                            DateGrid.setVgap(5);
                            PositionGrid.add(PositionLabel,1,0);
                            PositionGrid.add(PositionField,2,0);
                            PositionGrid.add(CreateNewPost,4,2);
                            PositionGrid.setHgap(20);
                            PositionGrid.setVgap(5);

                            BorderPane CreateJobPlacement =new BorderPane();
                            CreateJobPlacement.setTop(CMessageGrid);
                            CreateJobPlacement.setCenter(DateGrid);
                            CreateJobPlacement.setBottom(PositionGrid);

                            CreateJobs.getChildren().addAll(CreateJobPlacement);

                            CreateNewPost.setOnAction((ActionEvent CreateJob) ->{
                                int year = Integer.parseInt(YearField.getText());
                                int month = Integer.parseInt(MonthField.getText());
                                int day = Integer.parseInt(DayField.getText());
                                Date CloseDate = new Date(year,month,day);
                                String position = PositionField.getText();
                                ((HR_Coordinator) LoggedUser).addJob(today,CloseDate,position);
                                stage.setScene(HRBasePage);
                            });





                            // where we create job postings
                        } );
                    }// what happens if they are not an HR Coordinator but have a login;
                    //send message wrong user type?
                    else {stage.setScene(LoginPage);}
                }else if (LoginRadio.getSelectedToggle() == interviewerButton){ ///////////////////////////////////////
                    Group intPortalScene = new Group();
                    stage.setScene(new Scene(intPortalScene, 600, 600));
                    if (LoggedUser.getClass() == Interviewer.class){
                        GridPane interviewerSelectionPane = new GridPane();
                        //Label TestLab1 = new Label("Interviewer Page");
                        Button addApplicant = new Button("Add Applicant");
                        Button getInterviewies = new Button("Get Interviewies");
                        Button approve = new Button("Approve");
                        Button decline = new Button("Decline");
                        Button exit = new Button("EXIT");
                        interviewerSelectionPane.add(exit,8,4);
                        interviewerSelectionPane.add(addApplicant, 1,2);
                        interviewerSelectionPane.add(getInterviewies,1,1);
                        interviewerSelectionPane.add(approve,5,1);
                        interviewerSelectionPane.add(decline,5,2);

                        interviewerSelectionPane.setHgap(20);
                        interviewerSelectionPane.setVgap(20);

                        exit.setOnAction((ActionEvent ex) -> stage.setScene(LoginPage));

                        intPortalScene.getChildren().addAll(interviewerSelectionPane);

                    }// what happens if they are not an interviewer but have a login;
                    //send message wrong user type?
                    else{stage.setScene(LoginPage);}
                }


        }});


        // View + Controller
    }

    public static void main(String[] args) {
        launch(args);
    }
}


