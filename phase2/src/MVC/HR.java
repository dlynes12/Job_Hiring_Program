package MVC;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class HR extends User {

    private Company company;

    HR(String Username, String Password) {
        super(Username, Password);
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String toString() {
        return "{H," + this.getUsername() + "," + this.getPassword() + "}";
    }
}