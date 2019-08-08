package MVC;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {
    private static SystemAdmin systemAdmin = new SystemAdmin();

    @FXML
    private Button btnApp;

    @FXML
    private Button btnCompany;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainUI.fxml"));
        primaryStage.setTitle("Job Portal System");
        primaryStage.setScene(new Scene(root, 500, 270));
        primaryStage.show();
    }

    @FXML
    public void loadApp(ActionEvent event) throws Exception{
        Parent creation = FXMLLoader.load(getClass().getResource("AppLoginUI.fxml"));
        Scene tableViewScene = new Scene(creation);

        Stage window = (Stage) btnApp.getScene().getWindow();
        AppLoginController alc = new AppLoginController();
        alc.setSystemAdmin(systemAdmin);
        window.hide();
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    public void loadCompany(ActionEvent event) throws Exception{
        Parent creation = FXMLLoader.load(getClass().getResource("CompSelectionUI.fxml"));
        Scene tableViewScene = new Scene(creation);

        Stage window = (Stage) btnCompany.getScene().getWindow();
        CompSelController csc = new CompSelController();
        csc.setSystemAdmin(systemAdmin);
        window.hide();
        window.setScene(tableViewScene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
