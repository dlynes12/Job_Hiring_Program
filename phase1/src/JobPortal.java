import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class JobPortal extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Group root =  new Group();
        stage.setTitle("Job Application Portal");
        stage.setScene(new Scene(root, 600, 600));
        stage.show();
        Button button= new Button("Login in");
        TextField txtfield = new TextField("Enter username please");
        txtfield.setLayoutY(240);
        txtfield.setLayoutX(220);
        button.setLayoutY(300);
        button.setLayoutX(250);

        button.setOnAction((ActionEvent e) -> {
            System.out.println("clicked");
        });
        root.getChildren().add(button);
        root.getChildren().add(txtfield);
        // View + Controller
    }

    public static void main(String[] args) {
        launch(args);
    }
}


