package MVC;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AppCreationController {
    @FXML
    private Button btnCreate;

    @FXML
    private Button btnExit;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPass;

    private static SystemAdmin systemAdmin = new SystemAdmin();
    public void setSystemAdmin(SystemAdmin s){
        this.systemAdmin = s;
    }

    @FXML
    public void create(ActionEvent event) throws Exception{
        Applicant tempApp = new Applicant(txtUsername.getText(), txtPass.getText());
        //this.checkLogin(tempApp);
        if (systemAdmin.getUserManager().addUser(tempApp)) {
            Parent creation = FXMLLoader.load(getClass().getResource("AppLoginUI.fxml"));
            Scene tableViewScene = new Scene(creation);

            Stage window = (Stage) btnCreate.getScene().getWindow();
            AppLoginController app = new AppLoginController();
            app.setSystemAdmin(this.systemAdmin);
            window.setScene(tableViewScene);
            window.show();
        }
    }

    @FXML
    public void exit(ActionEvent event) throws Exception{
        Parent creation = FXMLLoader.load(getClass().getResource("AppLoginUI.fxml"));
        Scene tableViewScene = new Scene(creation);

        Stage window = (Stage) btnExit.getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
}
