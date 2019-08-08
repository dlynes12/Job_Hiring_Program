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

import java.time.ZoneId;
import java.util.Date;

public class CompLoginController {
    private static SystemAdmin systemAdmin = new SystemAdmin();
    private static User loggedUser;
    public void setSystemAdmin(SystemAdmin s){
        this.systemAdmin = s;
    }
    private TimeKeeper timeKeeper = new TimeKeeper();

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField txtPass;

    @FXML
    private TextField txtUsername;

    @FXML
    private DatePicker dpLogin;

    @FXML
    private Button btnBack;

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
        Parent creation = FXMLLoader.load(getClass().getResource("CompCreationUI.fxml"));
        Scene tableViewScene = new Scene(creation);

        Stage window = (Stage) btnRegister.getScene().getWindow();
        window.hide();
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    public void login(ActionEvent event) throws Exception {
        loggedUser = this.systemAdmin.getUserManager().login(txtUsername.getText(), txtPass.getText());
        if (loggedUser != null && dpLogin.getValue() != null) {
            Date today = Date.from(dpLogin.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            systemAdmin.getJobManager().retrieveTime(today);
            timeKeeper.updateTime(dpLogin);

            if (loggedUser.getClass() == HR.class) {
                Parent creation = FXMLLoader.load(getClass().getResource("HRUI.fxml"));
                Scene tableViewScene = new Scene(creation);

                Stage window = (Stage) btnLogin.getScene().getWindow();
                HRController hrc = new HRController();
                hrc.setSystemAdmin(systemAdmin);
                window.setScene(tableViewScene);
                window.show();
            } else if (loggedUser.getClass() == Interviewer.class) {
                Parent creation = FXMLLoader.load(getClass().getResource("InterviewerUI.fxml"));
                Scene tableViewScene = new Scene(creation);

                Stage window = (Stage) btnLogin.getScene().getWindow();
                window.setScene(tableViewScene);
                window.show();
            }
        }
    }
}
