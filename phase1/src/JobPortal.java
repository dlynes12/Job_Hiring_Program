import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

        password.setLayoutY(270);
        password.setLayoutX(220);
        username.setLayoutY(240);
        button.setLayoutY(300);
        username.setLayoutX(220);
        button.setLayoutX(280);
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
            Button getResume = new Button("Submit your resume");
            Button getFile = new Button("Open your resume from file");
            StackPane resumeUpload = new StackPane();
            resumeUpload.getChildren().addAll(getFile, getResume);

            JobPortalScene.getChildren().addAll(resumeUpload);

        }});


        // View + Controller
    }

    public static void main(String[] args) {
        launch(args);
    }
}


