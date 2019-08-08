package MVC;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class CompCreationController {
    @FXML
    private Button btnCreate;

    @FXML
    private Button btnExit;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPass;

    @FXML
    private ToggleGroup userType;

    @FXML
    private RadioButton radioHR;

    @FXML
    private RadioButton radioInt;

    private static SystemAdmin systemAdmin = new SystemAdmin();
    public void setSystemAdmin(SystemAdmin s){
        this.systemAdmin = s;
    }

    @FXML
    public void create(ActionEvent event) throws Exception{
        if (userType.getSelectedToggle() == radioHR) {
            HR tempHR = new HR(txtUsername.getText(), txtPass.getText());
            if (systemAdmin.getUserManager().addUser(tempHR)) {
                Parent creation = FXMLLoader.load(getClass().getResource("CompLoginUI.fxml"));
                Scene tableViewScene = new Scene(creation);

                Stage window = (Stage) btnCreate.getScene().getWindow();
                CompLoginController clc = new CompLoginController();
                clc.setSystemAdmin(this.systemAdmin);
                window.setScene(tableViewScene);
                window.show();
            }
        } else if (userType.getSelectedToggle() == radioInt) {
            Interviewer tempInt = new Interviewer(txtUsername.getText(), txtPass.getText());
            if (systemAdmin.getUserManager().addUser(tempInt)) {
                Parent creation = FXMLLoader.load(getClass().getResource("CompLoginUI.fxml"));
                Scene tableViewScene = new Scene(creation);

                Stage window = (Stage) btnCreate.getScene().getWindow();
                CompLoginController clc = new CompLoginController();
                clc.setSystemAdmin(this.systemAdmin);
                window.setScene(tableViewScene);
                window.show();
            }
        }

    }

    @FXML
    public void exit(ActionEvent event) throws Exception{
        Parent creation = FXMLLoader.load(getClass().getResource("CompLoginUI.fxml"));
        Scene tableViewScene = new Scene(creation);

        Stage window = (Stage) btnExit.getScene().getWindow();
        //window.hide();
        window.setScene(tableViewScene);
        window.show();
    }
}
