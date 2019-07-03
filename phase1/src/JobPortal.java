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

        Group loginScene =  new Group();
        stage.setTitle("Job Application Portal");
        stage.setScene(new Scene(loginScene, 600, 600));
        stage.show();
        Button button= new Button("Log in");
        Label labelUsername = new Label("Username");
        Label labelPassword = new Label("Password");
        GridPane gridPane = new GridPane();
        TextField username = new TextField();
        TextField password = new TextField();

        gridPane.add(labelUsername, 2 ,0);
        gridPane.add(labelPassword, 2 ,2);
        gridPane.add(username,4,0);
        gridPane.add(password,4,2);
        gridPane.add(button,4,4);
        gridPane.setHgap(40);


        StackPane box = new StackPane();
        box.getChildren().addAll(gridPane);
        loginScene.getChildren().addAll(box);
        User user = new User(username.getText(), password.getText()) {
            @Override
            public boolean login(User user) {
                return  true;
            }

        };
        button.setOnAction((ActionEvent e) -> { if(user.login(user)) {
            Group JobPortalScene = new Group();
            stage.setScene(new Scene(JobPortalScene, 600, 600));
            GridPane applicatnResumeUploadButtons = new GridPane();
            Button getResume = new Button("Submit your resume");
            Button getFile = new Button("Open your resume from file");
            TextField resume = new TextField();
            getFile.setOnAction((ActionEvent openFile) -> {
                FileChooser fileChooser = new FileChooser();
                //fileChooser.setInitialDirectory();
                fileChooser.setTitle("Open Resume File");
                fileChooser.setInitialFileName(user.getUsername());
                File file = fileChooser.showOpenDialog(stage);
                System.out.println(file);
                File filename = fileChooser.showSaveDialog(stage);

            });
            Label labelFileUpload = new Label("Submit Your resume");
            Label labelEnterResume = new Label("Enter your resume");
            applicatnResumeUploadButtons.add(getFile,2,2);
            applicatnResumeUploadButtons.add(getResume,2,4);
            applicatnResumeUploadButtons.add(labelFileUpload,0,2);
            applicatnResumeUploadButtons.add(resume,2,3);
            applicatnResumeUploadButtons.add(labelEnterResume,0,4);
            StackPane resumeUpload = new StackPane();
            applicatnResumeUploadButtons.setHgap(20);
            applicatnResumeUploadButtons.setVgap(20);
            resumeUpload.getChildren().addAll(applicatnResumeUploadButtons);


            JobPortalScene.getChildren().addAll(resumeUpload);

        }});


        // View + Controller
    }

    public static void main(String[] args) {
        launch(args);
    }
}


