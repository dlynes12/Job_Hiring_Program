import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class JobPortal extends Application {

    // SIGN-IN PAGE
    @Override
    public void start(Stage stage) throws Exception {


        SystemAdmin systemAdmin = new SystemAdmin();
        Group loginScene = new Group();
        Storage store = new Storage();
        List<String> list;

        try{
              list = store.readUserList();
              System.out.println(list);
              for(Object o: list){
                  systemAdmin.getUserManager().addUser((User)o);
                  //userManager.addUser((User)o);
              }
        }catch(ClassNotFoundException|IOException ex){
            System.out.println(ex.getMessage());
        }
        Alert alertLogin = new Alert(Alert.AlertType.WARNING);
        alertLogin.setTitle("Password/Username Not Found");
        alertLogin.setHeaderText("Do not leave any of the fields empty.");
        alertLogin.setContentText("Please Try Again");

        Alert alertLogin2 = new Alert(Alert.AlertType.WARNING);
        alertLogin2.setTitle("Password/Username Not Found");
        alertLogin2.setHeaderText("Incorrect Password");
        alertLogin2.setContentText("Please Try Again");


        Alert alertDate = new Alert(Alert.AlertType.WARNING);
        alertDate.setTitle("Invalid date input");
        alertDate.setHeaderText("Do not leave any of the fields empty.");
        alertDate.setContentText("Please Try Again");

        Alert alertCreate = new Alert(Alert.AlertType.WARNING);
        alertCreate.setTitle("Invalid input or user already exists");
        alertCreate.setHeaderText("Do not leave any of the fields empty.");
        alertCreate.setContentText("Please Try Again");

        stage.setTitle("Job Application Portal");
        Scene loginPage = new Scene(loginScene, 600, 200);
        stage.setScene(loginPage);
        stage.show();
        Button log_in = new Button("Login");
        Button new_user = new Button("New User");
        RadioButton applicantButton = new RadioButton("Applicant");
        RadioButton hRButton = new RadioButton("HR");
        RadioButton interviewerButton = new RadioButton("Interviewer");
        ToggleGroup loginRadio = new ToggleGroup();
        applicantButton.setToggleGroup(loginRadio);
        hRButton.setToggleGroup(loginRadio);
        interviewerButton.setToggleGroup(loginRadio);
        Label labelUsername = new Label("Username");
        Label labelPassword = new Label("Password");
        Label labelDate = new Label("Todays Date");
        GridPane gridPane = new GridPane();
        TextField username = new TextField();
        TextField password = new TextField();
        DatePicker datePicker = new DatePicker();
        TimeKeeper timeKeeper = new TimeKeeper();
        timeKeeper.addObserver(systemAdmin.getJobManager());

        gridPane.add(labelUsername, 2, 0);
        gridPane.add(labelPassword, 2, 2);
        gridPane.add(labelDate,2 , 4);
        gridPane.add(username, 4, 0);
        gridPane.add(password, 4, 2);
        gridPane.add(applicantButton, 2, 7);
        gridPane.add(hRButton, 3, 7);
        gridPane.add(interviewerButton, 4, 7);
        gridPane.add(log_in, 7, 5);
        gridPane.add(new_user, 9, 5);
        gridPane.add(datePicker,4,4);
        gridPane.setHgap(10);
       // Date closeDate = Date.from(datePicker.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        StackPane box = new StackPane();
        box.getChildren().addAll(gridPane);
        loginScene.getChildren().addAll(box);

        //CREATE A USER PAGE
        new_user.setOnAction((ActionEvent AddUser) -> {
            Group createNewUser = new Group();
            stage.setScene(new Scene(createNewUser, 600, 200));
            Button create = new Button("Create");
            Label createUserLab = new Label("Choose a Username");
            Label createPassLab = new Label("Enter a Password");
            Label choice = new Label("Please choose the type of account you want:");
            TextField newUserField = new TextField();
            TextField newPassField = new TextField();
            RadioButton radioApp = new RadioButton("Applicant");
            RadioButton radioHR = new RadioButton("HR Coordinator");
            RadioButton radioInt = new RadioButton("Interviewer");
            Button exit = new Button("EXIT");
            ToggleGroup radioSet = new ToggleGroup(); // allows only one radio button to be selected at a time
            radioApp.setToggleGroup(radioSet);
            radioHR.setToggleGroup(radioSet);
            radioInt.setToggleGroup(radioSet);
            GridPane userInfo = new GridPane();
            GridPane choicePane = new GridPane();
            GridPane grid = new GridPane();

            userInfo.add(exit, 8, 4);
            userInfo.add(createUserLab, 2, 0);
            userInfo.add(createPassLab, 2, 2);
            userInfo.add(newUserField, 4, 0);
            userInfo.add(newPassField, 4, 2);
            choicePane.add(choice, 2, 0);
            grid.add(radioApp, 2, 0);
            grid.add(radioHR, 4, 0);
            grid.add(radioInt, 6, 0);
            grid.add(create, 7, 2);

            BorderPane placement = new BorderPane();
            userInfo.setHgap(20);
            userInfo.setVgap(5);
            choicePane.setHgap(20);
            grid.setHgap(20);
            placement.setTop(userInfo);
            placement.setCenter(choicePane);
            placement.setBottom(grid);
            createNewUser.getChildren().addAll(placement);

            exit.setOnAction((ActionEvent ex) -> stage.setScene(loginPage));

            create.setOnAction((ActionEvent ProcessUser) -> {
                if (radioSet.getSelectedToggle() == radioApp) {
                    Applicant tempApp = new Applicant(newUserField.getText(), newPassField.getText());
                    //if (userManager.addUser(tempApp)) {
                    if (systemAdmin.getUserManager().addUser(tempApp)){
                        stage.setScene(loginPage);
                    }else {
                        alertCreate.showAndWait();
                    }
                } else if (radioSet.getSelectedToggle() == radioHR) {
                    HR_Coordinator tempHR = new HR_Coordinator(newUserField.getText(), newPassField.getText());
                    if (systemAdmin.getUserManager().addUser(tempHR)){
                    //if (userManager.addUser(tempHR)) {
                        stage.setScene(loginPage);
                    }else {
                        alertCreate.showAndWait();
                    }
                } else if (radioSet.getSelectedToggle() == radioInt) {
                    Interviewer tempInt = new Interviewer(newUserField.getText(), newPassField.getText());
                    if (systemAdmin.getUserManager().addUser(tempInt)){
                    //if (userManager.addUser(tempInt)) {
                        systemAdmin.getUserManager().addInterviewer(tempInt);
                        //userManager.addInterviewer(tempInt);
                        stage.setScene(loginPage);
                    }else {
                        alertCreate.showAndWait();
                    }
                }else {
                    alertCreate.showAndWait();
                }
                try{
                    //store.writeUserList(userManager.users);
                    store.writeUserList(systemAdmin.getUserManager().users);
                }catch(IOException ex){
                    System.out.println(ex.getMessage());
                }
            });
        });

        log_in.setOnAction((ActionEvent e) -> {
            if (datePicker.getValue() != null){
                Date today = Date.from(datePicker.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                systemAdmin.getJobManager().retrieveTime(today);
                timeKeeper.updateTime(datePicker);
                String UName = username.getText();
                String Pass = password.getText();
                try {
                    //User loggedUser = userManager.login(UName, Pass); // the user that is actually logged in
                    User loggedUser = systemAdmin.getUserManager().login(UName, Pass);
                    if (loggedUser != null) {
                        if (loginRadio.getSelectedToggle() == applicantButton) {
                            ((Applicant) loggedUser).applicantGUISetUp(stage, loggedUser, systemAdmin, loginPage);
                            //((Applicant) loggedUser).applicantGUISetUp(stage, loggedUser, jobManager, loginPage);

                        } else if (loginRadio.getSelectedToggle() == hRButton) {
                            ((HR_Coordinator) loggedUser).HRGUISetUp(stage, loggedUser, systemAdmin, loginPage);
                            //((HR_Coordinator) loggedUser).HRGUISetUp(stage, loggedUser, jobManager, loginPage, userManager);
                        } else if (loginRadio.getSelectedToggle() == interviewerButton) {
                            ((Interviewer) loggedUser).getInterviewPane(stage,loggedUser, systemAdmin, loginPage);
                            //((Interviewer) loggedUser).getInterviewPane(stage, loggedUser, jobManager, loginPage, userManager);
                        } else {
                            stage.setScene(loginPage);
                        }

                        try{
                            store.writeToFile(loggedUser);
                        }catch(IOException ex){
                            System.out.println(ex.getMessage());
                        }
                    }else{alertLogin2.showAndWait();}
                }catch (NullPointerException e1){
                    alertLogin.showAndWait();
                }
            }else {alertDate.showAndWait();}

        });
    }

    // View + Controller

    //todo: serialize list of interviewers in UserAccess
    public static void main(String[] args) {
        launch(args);
    }

}