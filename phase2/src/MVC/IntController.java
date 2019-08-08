package MVC;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class IntController {
    @FXML
    private Button btnInterviewApp;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnInterviewees;

    @FXML
    private Button btnApprove;

    @FXML
    private Button btnDecline;

    @FXML
    private ListView<?> lstJobs;

    public void loadCandidates(ActionEvent event) throws Exception{

    }

    public void logout(ActionEvent event) throws Exception{
        Parent creation = FXMLLoader.load(getClass().getResource("MainUI.fxml"));
        Scene tableViewScene = new Scene(creation);

        Stage window = (Stage) btnLogout.getScene().getWindow();
        window.close();
        window.setScene(tableViewScene);
        window.show();
    }
}
