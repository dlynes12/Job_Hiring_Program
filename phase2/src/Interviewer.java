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
    private Map<Applicant,String> applicantsList = new HashMap<>();

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

    //TODO when someone gets hired, please change the 'filled' boolean for the jobPosting to TRUE

    void addToList(Applicant applicant, String position){
        applicantsList.put(applicant,position);
    }

    ArrayList<Applicant> getInterviewees() {
        ArrayList<Applicant> listOfInterviewees = new ArrayList<>();
        for (Applicant a: applicantsList.keySet())  {
            listOfInterviewees.add(a);
        }
        return listOfInterviewees;
    }

    //GUI CONTROLS FOR INTERVIEWS

    void getInterviewPane(Stage stage, User loggedUser, JobAccess jobManager, Scene loginPage, UserAccess userManager) {
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

            getInterviewees.setOnAction((ActionEvent ev) -> {
                // get the Applicant list for each job
                String choice = (String) dropdown.getValue();
                String[] listOfApp = jobManager.getJob(choice).viewApplicants(this).split(",");
                if (listOfApp.length != 0 && !isNullOrEmpty(listOfApp[0])){
                    Label chooseApp = new Label("Choose an Applicant:");
                    interviewerSelectionPane.add(chooseApp, 1, 0);
                    ListView<String> scrollListApps = new ListView<>();
                    ObservableList<String> listApps= FXCollections.observableArrayList();
                    scrollListApps.setItems(listApps);
                    scrollListApps.setPrefSize(100.00,70.00);
                    listApps.addAll(Arrays.asList(listOfApp));
                    interviewerSelectionPane.add(scrollListApps, 1, 1);

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

            intPortalScene.getChildren().addAll(interviewerPlacement);
        }
    }
    public String toString(){
        return "{I," + this.getUsername() + "," + this.getPassword() +"}";
    }
}