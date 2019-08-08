package MVC;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CompSelController implements Initializable {
    private static SystemAdmin systemAdmin = new SystemAdmin();
    private static User loggedUser;
    public void setSystemAdmin(SystemAdmin s){
        this.systemAdmin = s;
    }

    public void initialize(URL location, ResourceBundle resources){
        for (Company company : this.systemAdmin.getListOfCompanies()) {
            lstComps.getItems().add(company.getCompanyName());
        }
    }

    @FXML
    private ListView<String> lstComps;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSelect;

    @FXML
    private Label lblCompany;

    @FXML
    private Button btnCreateComp;

    @FXML
    private TextField txtComp;

    @FXML
    void createComp(ActionEvent event) throws Exception{
        Company company = new Company(txtComp.getText());
        this.systemAdmin.addCompany(company);
        Parent creation = FXMLLoader.load(getClass().getResource("MainUI.fxml"));
        Scene tableViewScene = new Scene(creation);

        Stage window = (Stage) btnCreateComp.getScene().getWindow();
        window.hide();
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    void selectComp(ActionEvent event) throws Exception {
        if (lstComps.getSelectionModel().getSelectedItem()!=null){
            Company loggedCompany = this.systemAdmin.getCompany(lstComps.getSelectionModel().getSelectedItem());
            Parent creation = FXMLLoader.load(getClass().getResource("CompLoginUI.fxml"));
            Scene tableViewScene = new Scene(creation);

            Stage window = (Stage) btnSelect.getScene().getWindow();
            CompLoginController clc = new CompLoginController();
            clc.setSystemAdmin(systemAdmin);
            window.hide();
            window.setScene(tableViewScene);
            window.show();
        }
    }

    @FXML
    void loadMain(ActionEvent event) throws Exception{
        Parent creation = FXMLLoader.load(getClass().getResource("MainUI.fxml"));
        Scene tableViewScene = new Scene(creation);

        Stage window = (Stage) btnBack.getScene().getWindow();
        window.hide();
        window.setScene(tableViewScene);
        window.show();
    }
}
