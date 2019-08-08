package MVC;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;

public class AppLoginController {
    private Storage store = new Storage();
    private static SystemAdmin systemAdmin = new SystemAdmin();
    private static User loggedUser;
    public void setSystemAdmin(SystemAdmin s){
        this.systemAdmin = s;
    }
    private TimeKeeper timeKeeper = new TimeKeeper();

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnLogin;

    @FXML
    private DatePicker dpLogin;

    @FXML
    private PasswordField txtPass;

    @FXML
    private TextField txtUsername;

    @FXML
    public void loadMain(ActionEvent event) throws Exception{
        Parent creation = FXMLLoader.load(getClass().getResource("MainUI.fxml"));
        Scene tableViewScene = new Scene(creation);

        Stage window = (Stage) btnBack.getScene().getWindow();
        window.hide();
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    public void loadCreation(ActionEvent event) throws Exception {
        Parent creation = FXMLLoader.load(getClass().getResource("AppCreationUI.fxml"));
        Scene tableViewScene = new Scene(creation);

        Stage window = (Stage) btnRegister.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AppCreationUI.fxml"));
        AppCreationController app = new AppCreationController();
        app.setSystemAdmin(this.systemAdmin);
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    public void login(ActionEvent event) throws Exception {
        if (dpLogin.getValue() != null) {
            Date today = Date.from(dpLogin.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            systemAdmin.getJobManager().retrieveTime(today);
            timeKeeper.updateTime(dpLogin);
            String UName = txtUsername.getText();
            String Pass = txtPass.getText();
            try {
                // the user that is actually logged in
                loggedUser = this.systemAdmin.getUserManager().login(UName, Pass);
                if (loggedUser != null) {
                    Parent creation = FXMLLoader.load(getClass().getResource("ApplicantUI.fxml"));
                    Scene tableViewScene = new Scene(creation);

                    Stage window = (Stage) btnLogin.getScene().getWindow();
                    ApplicantController app = new ApplicantController();
                    app.setSystemAdmin(this.systemAdmin);
                    app.setLoggedUser(this.loggedUser);
                    window.hide();
                    window.setScene(tableViewScene);
                    window.show();
                    try {
                        store.writeToFile(loggedUser);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                } else {
                    systemAdmin.getAlert("login2").showAndWait();
                }
            } catch (NullPointerException e1) {
                systemAdmin.getAlert("login1").showAndWait();
            }
        } else {
            systemAdmin.getAlert("date").showAndWait();
        }
    }
}
