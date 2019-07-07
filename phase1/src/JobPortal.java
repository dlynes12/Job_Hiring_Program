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

public class JobPortal extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ApplicationModel applicationModel = new ApplicationModel();
        Applicant applicant = new Applicant("", "");
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
                    if (tempApp.addUser(tempApp)){
                        stage.setScene(LoginPage);
                    }
                }else if ((RadioButton) RadioSet.getSelectedToggle() == RadioHR){
                    HR_Coordinator tempHR = new HR_Coordinator(NewUserField.getText(), NewPassField.getText());
                    if (tempHR.addUser(tempHR)){
                        stage.setScene(LoginPage);
                    }
                }else if ((RadioButton) RadioSet.getSelectedToggle() == RadioInt){
                    Interviewer tempInt = new Interviewer(NewUserField.getText(), NewPassField.getText());
                        if (tempInt.addUser(tempInt)){
                            stage.setScene(LoginPage);
                        }

                }
            } );

        });

        UserAccess user = new UserAccess(null, null);
        log_in.setOnAction((ActionEvent e) -> {
            UserAccess LoggedUser = user.LogInUser(user.getUsername(),user.getPassword());
            if(LoggedUser != null) {
                if (LoginRadio.getSelectedToggle() == applicantButton){
                    Group applicantPortalScene = new Group();
                    stage.setScene(new Scene(applicantPortalScene, 600, 600));
                    if (Applicant.class == LoggedUser.getClass()){
                        GridPane applicantSelectionPane = new GridPane();
                        Button getResume = new Button("Submit your resume");
                        Button applyJob = new Button("Apply to jobs");
                        Button getFile = new Button("Open your resume from file");
                        TextField resume = new TextField();
                        getFile.setOnAction((ActionEvent openFile) -> {
                            FileChooser fileChooser = new FileChooser();
                            //fileChooser.setInitialDirectory();
                            fileChooser.setTitle("Open Resume File");
                            fileChooser.setInitialFileName(user.getUsername());
                            File file = fileChooser.showOpenDialog(stage);
                            System.out.println(file);

                        });
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
                    stage.setScene(LoginPage);
                }else if (LoginRadio.getSelectedToggle() == hRButton){
                    Group HRPortalScene = new Group();
                    stage.setScene(new Scene(HRPortalScene, 600, 600));
                    if (HR_Coordinator.class == LoggedUser.getClass()){
                        Label TestLab0 = new Label("HR Page");

                        HRPortalScene.getChildren().addAll(TestLab0);
                    }// what happens if they are not an HR Coordinator but have a login;
                    //send message wrong user type?
                    stage.setScene(LoginPage);
                }else if (LoginRadio.getSelectedToggle() == interviewerButton){
                    Group intPortalScene = new Group();
                    stage.setScene(new Scene(intPortalScene, 600, 600));
                    if (Interviewer.class == LoggedUser.getClass()){
                        Label TestLab1 = new Label("Interviwer Page");

                        intPortalScene.getChildren().addAll(TestLab1);
                    }// what happens if they are not an interviewer but have a login;
                    //send message wrong user type?
                    stage.setScene(LoginPage);
                }

            /*GridPane applicantSelectionPane = new GridPane();
            Button getResume = new Button("Submit your resume");
            Button applyJob = new Button("Apply to jobs");
            Button getFile = new Button("Open your resume from file");
            TextField resume = new TextField();
            getFile.setOnAction((ActionEvent openFile) -> {
                FileChooser fileChooser = new FileChooser();
                //fileChooser.setInitialDirectory();
                fileChooser.setTitle("Open Resume File");
                fileChooser.setInitialFileName(user.getUsername());
                File file = fileChooser.showOpenDialog(stage);
                System.out.println(file);

            });
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
*/
        }});


        // View + Controller
    }

    public static void main(String[] args) {
        launch(args);
    }
}


