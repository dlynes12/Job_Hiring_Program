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
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class JobPortal extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        SystemAdmin systemAdmin = new SystemAdmin();
        Group accessMenuScene = new Group();
        Storage store = new Storage();
        List<String> list;
        try {
            list = store.readList("Users");
            System.out.println(list);
            for (Object o : list) {
                systemAdmin.getUserManager().addUser((User) o);
            }
        } catch (ClassNotFoundException | IOException ex) {
            System.out.println(ex.getMessage());
        }
        stage.setTitle("Job Portal");
        Scene accessMenuPage = new Scene(accessMenuScene, 500, 200);
        stage.setScene(accessMenuPage);
        stage.show();
        Button applicantAccessButton = new Button("Applicant Access");
        Button companyAccessButton = new Button("Company Access");
        Label accessTypeLabel = new Label("Welcome to Job Portal, please select access type.");
        Label dateLabel = new Label("Today's Date");
        DatePicker datePicker = new DatePicker();
        TimeKeeper timeKeeper = new TimeKeeper();
        GridPane accessMenuGridPane = new GridPane();
        timeKeeper.addObserver(systemAdmin.getJobManager());
        systemAdmin.setTimeKeeper(timeKeeper);
        accessMenuGridPane.add(accessTypeLabel, 2, 0);
        accessMenuGridPane.add(applicantAccessButton, 2, 1);
        accessMenuGridPane.add(companyAccessButton, 3, 1);
        accessMenuGridPane.add(dateLabel, 2, 2);
        accessMenuGridPane.add(datePicker, 2, 3);
        accessMenuGridPane.setHgap(10);
        accessMenuGridPane.setVgap(10);
        StackPane box1 = new StackPane();
        box1.getChildren().addAll(accessMenuGridPane);
        accessMenuScene.getChildren().addAll(box1);

        applicantAccessButton.setOnAction((ActionEvent applicantLoginEvent) -> {

            Group applicantLoginGroup = new Group();
            Scene applicantLoginScene = new Scene(applicantLoginGroup, 400, 400);
            stage.setScene(applicantLoginScene);
            stage.setTitle("Job Portal - Applicant Access");
            Label applicantUsernameLabel = new Label("Username:");
            Label applicantPasswordLabel = new Label("Password:");
            TextField appUsernameTextField = new TextField();
            TextField appPasswordTextField = new TextField();
            Button applicantLogInButton = new Button("Log In");
            Button createApplicantButton = new Button("Create new user");
            Button exitAppLoginButton = new Button("EXIT");
            GridPane applicantLoginPane = new GridPane();
            applicantLoginPane.add(applicantUsernameLabel, 2, 1);
            applicantLoginPane.add(appUsernameTextField, 2, 2);
            applicantLoginPane.add(applicantPasswordLabel, 2, 3);
            applicantLoginPane.add(appPasswordTextField, 2, 4);
            applicantLoginPane.add(applicantLogInButton, 2, 5);
            applicantLoginPane.add(createApplicantButton, 2, 6);
            applicantLoginPane.add(exitAppLoginButton, 2, 7);

            applicantLoginPane.setHgap(20);
            applicantLoginPane.setVgap(10);

            applicantLoginGroup.getChildren().addAll(applicantLoginPane);

            exitAppLoginButton.setOnAction((ActionEvent exitAppLoginEvent) -> stage.setScene(accessMenuPage));

            createApplicantButton.setOnAction((ActionEvent createApplicantEvent) -> {

                Group createApplicantGroup = new Group();
                Scene createApplicantScene = new Scene(createApplicantGroup, 400, 400);
                stage.setScene(createApplicantScene);
                Button createApplicant = new Button("Create");
                Button exitAppCreate = new Button("EXIT");
                Label newAppUserLabel = new Label("Choose a Username");
                Label newAppPassLabel = new Label("Enter a Password");
                TextField newAppUserField = new TextField();
                TextField newAppPassField = new TextField();
                GridPane createApplicantPane = new GridPane();

                createApplicantPane.add(newAppUserLabel, 2, 1);
                createApplicantPane.add(newAppUserField, 2, 2);
                createApplicantPane.add(newAppPassLabel, 2, 3);
                createApplicantPane.add(newAppPassField, 2, 4);
                createApplicantPane.add(createApplicant, 2, 5);
                createApplicantPane.add(exitAppCreate, 2, 6);
                createApplicantPane.setHgap(20);
                createApplicantPane.setVgap(10);

                createApplicantGroup.getChildren().addAll(createApplicantPane);

                exitAppCreate.setOnAction((ActionEvent exitAppCreateEvent) -> stage.setScene(applicantLoginScene));

                createApplicant.setOnAction((ActionEvent processApplicant) -> {
                    Applicant newApp = new Applicant(newAppUserField.getText(), newAppPassField.getText());
                    if (systemAdmin.getUserManager().addUser(newApp)) {
                        stage.setScene(applicantLoginScene);
                    } else {
                        systemAdmin.getAlert("create").showAndWait();
                    }

                    try {
                        //store.writeUserList(userManager.users);
                        store.writeList(systemAdmin.getUserManager().users, "Users");
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                });

            });

            applicantLogInButton.setOnAction((ActionEvent logInAppEvent) -> {
                if (datePicker.getValue() != null) {
                    Date today = Date.from(datePicker.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                    systemAdmin.getJobManager().retrieveTime(today);
                    timeKeeper.updateTime(datePicker);
                    String UName = appUsernameTextField.getText();
                    String Pass = appPasswordTextField.getText();
                    try {
                        User loggedUser = systemAdmin.getUserManager().login(UName, Pass);// the user that is actually logged in
                        if (loggedUser != null) {
                            ((Applicant) loggedUser).applicantGUISetUp(stage, loggedUser, systemAdmin, applicantLoginScene);
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
            });
        });


        companyAccessButton.setOnAction((ActionEvent compAccessEvent) -> {

            Group companyLogInGroup = new Group();
            Scene companyLoginScene = new Scene(companyLogInGroup, 700, 700);
            stage.setScene(companyLoginScene);
            stage.setTitle("Job Portal - Company Access");

            Button companySelectionButton = new Button(" Select Company");
            Button createCompanyButton = new Button("Create new company");
            Button exitCompAccessButton = new Button("Exit");
            ComboBox<String> companyDropdown = new ComboBox<>();
            GridPane companyLoginPane = new GridPane();

            companyLoginPane.setVgap(10);
            companyLoginPane.setHgap(20);
            companyLoginPane.add(companySelectionButton, 3, 1);
            companyLoginPane.add(companyDropdown, 2, 1);
            companyLoginPane.add(createCompanyButton, 2, 2);
            companyLoginPane.add(exitCompAccessButton, 2, 3);

            for (Company company : systemAdmin.getListOfCompanies()) {
                companyDropdown.getItems().add(company.getCompanyName());
            }

            companyLogInGroup.getChildren().addAll(companyLoginPane);

            exitCompAccessButton.setOnAction((ActionEvent exitCompAccessEvent) -> {
                stage.setScene(accessMenuPage);
            });

            createCompanyButton.setOnAction((ActionEvent createCompanyEvent) -> {

                Group registerCompanyGroup = new Group();
                Scene registerCompanyScene = new Scene(registerCompanyGroup, 300, 300);
                stage.setScene(registerCompanyScene);
                stage.setTitle("Job Portal - Company Registration");

                Label compNameLabel = new Label("Company name:");
                TextField compNameTextField = new TextField();
                Button registerButton = new Button("Register");
                Button registrationExit = new Button("Exit");
                GridPane companyRegisterPane = new GridPane();

                companyRegisterPane.add(compNameLabel, 2, 1);
                companyRegisterPane.add(compNameTextField, 2, 2);
                companyRegisterPane.add(registerButton, 2, 3);
                companyRegisterPane.add(registrationExit, 2, 4);
                companyRegisterPane.setHgap(20);
                companyRegisterPane.setVgap(10);

                registerCompanyGroup.getChildren().addAll(companyRegisterPane);

                registerButton.setOnAction((ActionEvent registerCompEvent) -> {
                    Company company = new Company(compNameTextField.getText());
                    systemAdmin.addCompany(company);
                    stage.setScene(accessMenuPage);

                });

                registrationExit.setOnAction((ActionEvent registerExitEvent) -> {
                    stage.setScene(companyLoginScene);
                });

            });

            companySelectionButton.setOnAction((ActionEvent companySelectionEvent) -> {

                String selectedCompany = companyDropdown.getValue();
                Company loggedCompany = systemAdmin.getCompany(selectedCompany);
                System.out.println(selectedCompany + " - selected "); //test
                System.out.println(loggedCompany.getCompanyName() + " - logged");

                Group compUserLoginGroup = new Group();
                Scene compUserLoginScene = new Scene(compUserLoginGroup, 600, 600);
                stage.setScene(compUserLoginScene);
                stage.setTitle("Job Portal - " + loggedCompany.getCompanyName());

                Label compUsernameLabel = new Label("Username:");
                Label compPasswordLabel = new Label("Password:");
                TextField compUsernameTextField = new TextField();
                TextField compPasswordTextField = new TextField();
                Button compLogInButton = new Button("Log In");
                Button createCompUserButton = new Button("Create User");
                Button exitCompLoginButton = new Button("EXIT");
                RadioButton hrRadioButton = new RadioButton("HR Coordinator");
                RadioButton intRadioButton = new RadioButton("Interviewer");

                ToggleGroup loginRadio = new ToggleGroup();
                hrRadioButton.setToggleGroup(loginRadio);
                intRadioButton.setToggleGroup(loginRadio);

                GridPane compUserLoginGrid = new GridPane();

                compUserLoginGrid.add(compUsernameLabel, 2, 1);
                compUserLoginGrid.add(compUsernameTextField, 2, 2);
                compUserLoginGrid.add(compPasswordLabel, 2, 3);
                compUserLoginGrid.add(compPasswordTextField, 2, 4);
                compUserLoginGrid.add(hrRadioButton, 2, 5);
                compUserLoginGrid.add(intRadioButton, 3, 5);
                compUserLoginGrid.add(compLogInButton, 2, 6);
                compUserLoginGrid.add(createCompUserButton, 3, 6);
                compUserLoginGrid.add(exitCompLoginButton, 2, 7);
                compUserLoginGrid.setVgap(10);
                compUserLoginGrid.setHgap(20);

                compUserLoginGroup.getChildren().addAll(compUserLoginGrid);

                //ADDING A NEW COMPANY USER
                createCompUserButton.setOnAction((ActionEvent newCompUserEvent) -> {
                    Group createNewUser = new Group();
                    stage.setScene(new Scene(createNewUser, 600, 200));
                    Button create = new Button("Create");
                    Label createUserLab = new Label("Choose a Username");
                    Label createPassLab = new Label("Enter a Password");
                    Label choice = new Label("Please choose the type of account you want:");
                    TextField newUserField = new TextField();
                    TextField newPassField = new TextField();
                    RadioButton radioHR = new RadioButton("HR Coordinator");
                    RadioButton radioInt = new RadioButton("Interviewer");
                    Button exit = new Button("EXIT");
                    ToggleGroup radioSet = new ToggleGroup();
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

                    exit.setOnAction((ActionEvent ex) -> stage.setScene(companyLoginScene));

                    create.setOnAction((ActionEvent ProcessUser) -> {
                        if (radioSet.getSelectedToggle() == radioHR) {
                            HR_Coordinator newHR = new HR_Coordinator(newUserField.getText(), newPassField.getText());
                            newHR.setCompany(loggedCompany);
                            if (systemAdmin.getUserManager().addUser(newHR)) {
                                systemAdmin.getUserManager().addHRCoordinator(newHR);
                                stage.setScene(companyLoginScene);
                            } else {
                                systemAdmin.getAlert("create").showAndWait();
                            }
                        } else if (radioSet.getSelectedToggle() == radioInt) {
                            Interviewer newInt = new Interviewer(newUserField.getText(), newPassField.getText());
                            newInt.setCompany(loggedCompany);
                            if (systemAdmin.getUserManager().addUser(newInt)) {
                                systemAdmin.getUserManager().addInterviewer(newInt);
                                stage.setScene(companyLoginScene);
                            } else {
                                systemAdmin.getAlert("create").showAndWait();
                            }
                        } else {
                            systemAdmin.getAlert("create").showAndWait();
                        }
                        try {
                            //store.writeUserList(userManager.users);
                            store.writeList(systemAdmin.getUserManager().users, "Users");
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                    });
                });

                //USER LOG-IN
                compLogInButton.setOnAction((ActionEvent compLoginEvent) -> {
                    if (datePicker.getValue() != null) {
                        Date today = Date.from(datePicker.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                        systemAdmin.getJobManager().retrieveTime(today);
                        timeKeeper.updateTime(datePicker);
                        String UName = compUsernameTextField.getText();
                        String Pass = compPasswordTextField.getText();
                        try {
                            User loggedUser = systemAdmin.getUserManager().login(UName, Pass);// the user that is actually logged in
                            if (loggedUser != null) { //TODO: create alert if user is logging in with the wrong company
                                if (loginRadio.getSelectedToggle() == hrRadioButton &&
                                        ((HR_Coordinator)loggedUser).getCompany().equals(loggedCompany)) {
                                    ((HR_Coordinator) loggedUser).HRGUISetUp(stage, loggedUser, loggedCompany, systemAdmin, compUserLoginScene);
                                } else if (loginRadio.getSelectedToggle() == intRadioButton &&
                                        ((Interviewer)loggedUser).getCompany().equals(loggedCompany)) {
                                    ((Interviewer)loggedUser).interviewerGUISetUp(stage, loggedUser, loggedCompany, systemAdmin, compUserLoginScene);
                                } else {
                                    stage.setScene(compUserLoginScene);
                                }

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
                });

                exitCompLoginButton.setOnAction((ActionEvent exitCompUserLogin) -> {
                    stage.setScene(accessMenuPage);
                });
            });
        });
    }

    // View + Controller

    //todo: serialize list of interviewers in UserAccess
    public static void main(String[] args) {
        launch(args);
    }
}