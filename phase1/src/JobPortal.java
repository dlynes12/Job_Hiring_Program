import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.util.Random;

public class JobPortal extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ApplicationModel applicationModel = new ApplicationModel();
        Applicant applicant = new Applicant("", "");
        //JobPosting jobPosting = new JobPosting();
        Group loginScene = new Group();
        stage.setTitle("Job Application Portal");
        stage.setScene(new Scene(loginScene, 600, 600));
        stage.show();
        Button log_in = new Button("Log in");
        Button applicantButton = new Button("Applicant");
        Button hRButton = new Button("HR");
        Button interviewerButton = new Button("Interviewer");
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
        gridPane.setHgap(10);

        StackPane box = new StackPane();
        box.getChildren().addAll(gridPane);
        loginScene.getChildren().addAll(box);



        UserAccess user = new UserAccess(username.getText(), password.getText());
        log_in.setOnAction((ActionEvent e) -> { if(user.LogInUser(user.getUsername(),user.getPassword())) {
            Group applicantPortalScene = new Group();
            stage.setScene(new Scene(applicantPortalScene, 600, 600));
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

        }});


        // View + Controller
    }

    public static void main(String[] args) {
        launch(args);
    }
}


