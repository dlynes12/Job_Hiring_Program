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
                    Applicant applicant = new Applicant(UName, Pass);

                    applicant.applicantGUISetUp(stage, loggedUser, jobManager, loginPage, storage);
                } else {
                    stage.setScene(loginPage);
                }
            } else if (loginRadio.getSelectedToggle() == hRButton) {

            } else if (loginRadio.getSelectedToggle() == interviewerButton) { ///////////////////////////////////////
                Group intPortalScene = new Group();
                stage.setScene(new Scene(intPortalScene, 600, 600));
                if (loggedUser.getClass() == Interviewer.class) {
                    GridPane interviewerSelectionPane = new GridPane();

                    String welcomeMessage = "Welcome to the Interviewer page, " + loggedUser.getUsername();
                    Label welcomeLabel = new Label(welcomeMessage);
                    Label chooseJobLab = new Label("Choose a job:");
                    ComboBox dropdown = new ComboBox();
                    for (JobPosting jobPosting : jobManager.ViewJobs()) {
                        dropdown.getItems().add(jobPosting.getPosition());
                    }

                    Button getInterviewees = new Button("Get Interviewees");
                    Button logout = new Button("Logout");

                    GridPane chooseJobPane = new GridPane();
                    chooseJobPane.add(welcomeLabel, 1, 0);
                    chooseJobPane.add(chooseJobLab, 1, 2);
                    chooseJobPane.add(dropdown, 2, 2);
                    chooseJobPane.add(getInterviewees, 3, 2);
                    chooseJobPane.setHgap(20);
                    chooseJobPane.setVgap(5);
                    BorderPane interviewerPlacement = new BorderPane();
                    interviewerPlacement.setTop(chooseJobPane);
                    interviewerPlacement.setBottom(interviewerSelectionPane);
                    interviewerSelectionPane.add(logout, 1, 3);
                    interviewerSelectionPane.setHgap(20);
                    interviewerSelectionPane.setVgap(20);


                    getInterviewees.setOnAction((ActionEvent ev) -> {
                        // get the Applicant list for each job
                        Integer i = 0;
                        String choice = (String) dropdown.getValue();
                        String[] listOfApp = jobManager.getJob(choice).viewApplicants().split(",");
                        if (listOfApp.length != 0 && listOfApp[0] != "") {
                            ToggleGroup radioSet = new ToggleGroup(); // allows only one radio button to be selected at a time
                            Label chooseApp = new Label("Choose an Applicant:");
                            interviewerSelectionPane.add(chooseApp, 1, i);
                            for (String app : listOfApp) {
                                RadioButton radioButton = new RadioButton(app);
                                radioButton.setToggleGroup(radioSet);
                                interviewerSelectionPane.add(radioButton, 1, i + 1);
                                i++;
                            }
                            Button approve = new Button("Approve");
                            Button decline = new Button("Decline");
                            interviewerSelectionPane.add(approve, 3, 1);
                            interviewerSelectionPane.add(decline, 3, 2);


//                            Integer i = 0;
//                            for (JobPosting jP : jobManager.ViewJobs()) {
//                                RadioButton radioButton = new RadioButton(jP.getPosition());
//                                interviewerSelectionPane.add(radioButton, 0, i + 1);
//                                i++;
                        }
                    });

                    logout.setOnAction((ActionEvent ex) -> stage.setScene(loginPage));

                    //getInterviewees.setOnAction((ActionEvent click) -> ((Interviewer)loggedUser).getInterviewees());

                    //approve.setOnAction((ActionEvent click) -> ((Interviewer)loggedUser).recommend());

                    //decline.setOnAction((ActionEvent click) -> ((Interviewer)loggedUser).decline());


                    intPortalScene.getChildren().addAll(interviewerPlacement);

                } else {
                    stage.setScene(loginPage);
                }
            }
        });
    }

    // View + Controller

    public static void main(String[] args) {
        launch(args);
    }
}


