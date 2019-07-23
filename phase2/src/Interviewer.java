import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import java.util.*;

public class Interviewer extends User {
    Map<Applicant,String> applicantsList = new HashMap<>();

    public Interviewer(String username, String password){
        super(username,password);
    }

//    public void recommend(Applicant applicant, JobPosting job){
//        job.getCandidatePool().nextRound(applicant);
//    }
//
//    public void decline(Applicant applicant, JobPosting job){
//        job.getCandidatePool().removeFromPool(applicant);
//    }

    public void addToList(Applicant applicant, String position){
        applicantsList.put(applicant,position);
    }

    public ArrayList<Applicant> getInterviewees() {
        ArrayList<Applicant> listOfInterviewees = new ArrayList<>();
        for (Applicant a: applicantsList.keySet()){
            listOfInterviewees.add(a);
        }
        return listOfInterviewees;
    }


    //GUI CONTROLS FOR INTERVIEWS

    public void getInterviewPane(Stage stage, User loggedUser, JobAccess jobManager, Scene loginPage, UserAccess userManager) {
        if (loggedUser.getClass() == Interviewer.class) {

            Group intPortalScene = new Group();
            Scene scene = new Scene(intPortalScene, 600, 600);
            stage.setScene(scene);

            String welcomeMessage = "Welcome to the Interviewer page, " + loggedUser.getUsername();
            Label welcomeLabel = new Label(welcomeMessage);
            Label chooseJobLab = new Label("Choose a job:");
            ComboBox dropdown = new ComboBox();
            GridPane interviewerSelectionPane = new GridPane();
            GridPane chooseJobPane = new GridPane();
            BorderPane interviewerPlacement = new BorderPane();

            Button getInterviewees = new Button("Get Interviewees");
            Button logout = new Button("Logout");
            Button approve = new Button("Approve");
            Button decline = new Button("Decline");

            interviewerSelectionPane.add(approve, 3, 1);
            interviewerSelectionPane.add(decline, 4, 1);
            interviewerSelectionPane.add(logout, 1, 3);
            interviewerSelectionPane.setHgap(20);
            interviewerSelectionPane.setVgap(20);
            interviewerPlacement.setTop(chooseJobPane);
            interviewerPlacement.setBottom(interviewerSelectionPane);
            chooseJobPane.add(welcomeLabel,1,0);
            chooseJobPane.add(chooseJobLab,1,2);
            chooseJobPane.add(dropdown,2,2);
            chooseJobPane.add(getInterviewees,3,2);
            chooseJobPane.setHgap(20);
            chooseJobPane.setVgap(5);

            for (JobPosting jobPosting : jobManager.ViewJobs()) {
                dropdown.getItems().add(jobPosting.getPosition());
            }

            /*getInterviewees.setOnAction((ActionEvent ev) -> {
                // get the Applicant list for each job
                Integer i = 0;
                String choice = (String) dropdown.getValue();
                String[] listOfApp = jobManager.getJob(choice).viewApplicants(this).split(",");
                if (listOfApp.length != 0 && listOfApp[0] != "") {
                    ToggleGroup radioSet = new ToggleGroup(); // allows only one radio button to be selected at a time
                    Label chooseApp = new Label("Choose an Applicant:");
                    interviewerSelectionPane.add(chooseApp, 1, i);
                    for (String app : listOfApp) {
                        RadioButton radioButton = new RadioButton(app);
                        radioButton.setToggleGroup(radioSet);
                        interviewerSelectionPane.add(radioButton, 1, i + 1);
                        i++;
                    }
                    //todo: look at applyJob in Applicant GUI method
                    approve.setOnAction((ActionEvent click) -> {
                        RadioButton selectedRadio = (RadioButton)radioSet.getSelectedToggle();
                        String selectedApplicant = selectedRadio.getText();
                        Applicant appObj = (Applicant) userManager.getUser(selectedApplicant);
                        jobManager.getJob(choice).getHiringProcessor().nextRound(appObj);
                        selectedRadio.setStyle("-fx-selected-color: green; -fx-unselected-color: green;"); // changes the colour of a button to red once
                    });                                                                         //they have been recommended
                    decline.setOnAction((ActionEvent click) -> {
                        RadioButton selectedRadio = (RadioButton)radioSet.getSelectedToggle();
                        String selectedApplicant = selectedRadio.getText();
                        Applicant appObj = (Applicant) userManager.getUser(selectedApplicant);
                        jobManager.getJob(choice).getHiringProcessor().reject(appObj);
                        selectedRadio.setStyle("-fx-selected-color: red; -fx-unselected-color: red;");
                    });
                }
            });*/
            getInterviewees.setOnAction((ActionEvent ev) -> {
                // get the Applicant list for each job
                Integer i = 0;
                String choice = (String) dropdown.getValue();
                String[] listOfApp = jobManager.getJob(choice).viewApplicants(this).split(",");
                if (listOfApp.length != 0 && listOfApp[0] != ""){
                    Label chooseApp = new Label("Choose an Applicant:");
                    interviewerSelectionPane.add(chooseApp, 1, i);
                    ListView<String> scrollListApps = new ListView<>();
                    ObservableList<String> listApps= FXCollections.observableArrayList();
                    scrollListApps.setItems(listApps);
                    scrollListApps.setPrefSize(100.00,70.00);
                    for (String app : listOfApp) {
                        listApps.add(app);
                    }
                    interviewerSelectionPane.add(scrollListApps, 1, i + 1);
                    //todo: look at applyJob in Applicant GUI method
                    approve.setOnAction((ActionEvent click) -> {
                        String selectedApplicant = scrollListApps.getSelectionModel().getSelectedItem();
                        Applicant appObj = (Applicant) userManager.getUser(selectedApplicant);
                        jobManager.getJob(choice).getHiringProcessor().nextRound(appObj);
                        // we can delete the applicant from the applicantList after a person has been
                        // recommended or declined; then check if the applicantList is empty to advance
                        // the round
                    });                                                                         //they have been recommended
                    decline.setOnAction((ActionEvent click) -> {
                        String selectedApplicant = scrollListApps.getSelectionModel().getSelectedItem();
                        Applicant appObj = (Applicant) userManager.getUser(selectedApplicant);
                        jobManager.getJob(choice).getHiringProcessor().reject(appObj);
                    });
                }
            });

            logout.setOnAction((ActionEvent ex) -> stage.setScene(loginPage));



            //decline.setOnAction((ActionEvent click) -> ((Interviewer)loggedUser).decline());

            intPortalScene.getChildren().addAll(interviewerPlacement);
        }
    }
    public String toString(){
        return "{I," + this.getUsername() + "," + this.getPassword() +"}";
    }
}

