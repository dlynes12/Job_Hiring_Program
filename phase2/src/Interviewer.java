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

    public void recommend(Applicant applicant, JobPosting job){
        job.getCandidatePool().nextRound(applicant);
    }

    public void decline(Applicant applicant, JobPosting job){
        job.getCandidatePool().removeFromPool(applicant);
    }

    public void addToList(Applicant applicant, String position){
        applicantsList.put(applicant,position);
    }

    public void getInterviewees() {

    }


    //GUI CONTROLS FOR INTERVIEWS

    public void getInterviewPane(Stage stage, User loggedUser, JobAccess jobManager, Scene loginPage) {
        Group intPortalScene = new Group();
        Scene scene = new Scene(intPortalScene, 600, 600);
        stage.setScene(scene);
        if (loggedUser.getClass() == Interviewer.class) {
            GridPane interviewerSelectionPane = new GridPane();

            String welcomeMessage = "Welcome to the Interviewer page, " + loggedUser.getUsername();
            Label welcomeLabel = new Label(welcomeMessage);
            Label chooseJobLab = new Label("Choose a job:");
            ComboBox dropdown = new ComboBox();
            for (JobPosting jobPosting : jobManager.ViewJobs()) {
                dropdown.getItems().add(jobPosting.getPosition());
            }

            Button getInterviewees = new Button("Get Interviewees");
            Button logout = new Button("Logout");

            GridPane chooseJobPane = new GridPane();
            chooseJobPane.add(welcomeLabel,1,0);
            chooseJobPane.add(chooseJobLab,1,2);
            chooseJobPane.add(dropdown,2,2);
            chooseJobPane.add(getInterviewees,3,2);
            chooseJobPane.setHgap(20);
            chooseJobPane.setVgap(5);
            BorderPane interviewerPlacement = new BorderPane();
            interviewerPlacement.setTop(chooseJobPane);
            interviewerPlacement.setBottom(interviewerSelectionPane);
            interviewerSelectionPane.add(logout, 1, 3);
            interviewerSelectionPane.setHgap(20);
            interviewerSelectionPane.setVgap(20);

            getInterviewees.setOnAction((ActionEvent ev) -> {
                // get the Applicant list for each job
                Integer i = 0;
                String choice = (String) dropdown.getValue();
                String[] listOfApp = jobManager.getJob(choice).viewApplicants().split(",");
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
                    Button approve = new Button("Approve");
                    Button decline = new Button("Decline");
                    interviewerSelectionPane.add(approve, 3, 1);
                    interviewerSelectionPane.add(decline, 3, 2);


//                            Integer i = 0;
//                            for (JobPosting jP : jobManager.ViewJobs()) {
//                                RadioButton radioButton = new RadioButton(jP.getPosition());
//                                interviewerSelectionPane.add(radioButton, 0, i + 1);
//                                i++;
                }
            });


            logout.setOnAction((ActionEvent ex) -> stage.setScene(loginPage));

            //getInterviewees.setOnAction((ActionEvent click) -> ((Interviewer)loggedUser).getInterviewees());

            //approve.setOnAction((ActionEvent click) -> ((Interviewer)loggedUser).recommend());

            //decline.setOnAction((ActionEvent click) -> ((Interviewer)loggedUser).decline());


            intPortalScene.getChildren().addAll(interviewerPlacement);

        }

    }
    public String toString(){
        return "{I," + this.getUsername() + "," + this.getPassword() +"}";

    }
}

