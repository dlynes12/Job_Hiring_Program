import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

public class Applicant extends User {
    private HashMap<JobPosting, String> jobsApplied = new HashMap<>();
    private HashMap<String, ArrayList> userDocs = new HashMap<>();
    Storage store = new Storage();

    public Applicant(String username, String password) {
        super(username, password);
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public Date getDateCreated() {
        return super.getDateCreated();
    }

    public String getJobs() {
        String s = "";
        if (!this.jobsApplied.isEmpty()) {
            for (JobPosting job : this.jobsApplied.keySet()) {
                s = s + job.getPosition() + "\n";
            }
        } else {
            s = s + "Applicant has not applied for a job";
        }
        return s;
    }

    public String getJobHistory(){
        String history = "";
        if (!this.jobsApplied.isEmpty()){
            Date today = new Date();
            Instant now = Instant.now();
            today.from(now);
            for (JobPosting job: this.jobsApplied.keySet()){
                if (today.after(job.getDateClosed())){
                    history = history + job.getPosition() + " - CLOSED: " + job.getDateClosed() + "\n";
                }
                else {
                    history = history + job.getPosition() + "- OPEN\n";
                }
            }
        }
        return history;
    }

    public void getDocs(String username) throws IOException, ClassNotFoundException {
        store.readDocFile(username + "docs.bin");
    }

    public void setDocsHash(User u, Boolean a, Boolean b, String s) throws IOException {
        ArrayList<Boolean> docs = new ArrayList<>();
        docs.add(a);
        docs.add(b);
        userDocs.put(u.getUsername(),docs);

        store.writeDocFile(u,s);
    }

    public void applyToJob(JobPosting jobPosting) {
        this.jobsApplied.put(jobPosting, "Submitted Resume/CV");
        jobPosting.addApplicant(this);
    }

    //TODO: change how get JobStatus works - to just individually retrieve the status.
    public String getJobStatus(JobPosting jobPosting) {

//        String s = "";
//
//        if (!this.jobsApplied.isEmpty()) {
//            for (JobPosting key : this.jobsApplied.keySet()) {
//                s = s + key.getPosition() + ": " + this.jobsApplied.get(key) + "\n";
//            }
//        } else {
//            s = s + "Applicant has not applied for a job";
//        }
//
//        return s;

        String status = this.jobsApplied.get(jobPosting);
        return status;
    }

    public void updateStatus(JobPosting job, String status) {
        this.jobsApplied.put(job, status);
    }

    public String getInfo(){
        return "Applicant Username: " + this.getUsername() + '\n' +
                "\n Date Created: " + this.getDateCreated() + "\n" +
                "\nJobs Applied To:\n" + this.getJobHistory();
    }

    @Override
    public String toString() {
        return "{A," + this.getJobs() + "," + this.getUsername() + "," + this.getPassword() + "}";
    }

    public void applicantGUISetUp(Stage stage, User loggedUser, JobAccess jobManager, Scene loginPage){
        if (loggedUser.getClass() == Applicant.class) {

            Group applicantPortalScene = new Group();
            Scene applicantPage = new Scene(applicantPortalScene, 600, 600);
            stage.setScene(applicantPage);

            GridPane applicantSelectionPane = new GridPane();
            StackPane resumeUpload = new StackPane();
            TextField resume = new TextField();
            Label labelFileUpload = new Label("Submit your resume");
            Label labelEnterResume = new Label("Enter your resume");

            Button getResume = new Button("Submit your resume");
            Button applyJob = new Button("Apply to jobs");
            Button getFile = new Button("Open your resume from file");
            Button viewHistory = new Button("Account History");
            Button viewJobStatuses = new Button("Job Statuses");
            Button logout = new Button("Logout");

            applicantSelectionPane.add(logout, 4, 9);
            applicantSelectionPane.add(getFile, 4, 2);
            applicantSelectionPane.add(getResume, 4, 4);
            applicantSelectionPane.add(labelFileUpload, 2, 2);
            applicantSelectionPane.add(resume, 4, 3);
            applicantSelectionPane.add(labelEnterResume, 2, 4);
            applicantSelectionPane.add(applyJob, 4, 6);
            applicantSelectionPane.add(viewHistory, 4, 7);
            applicantSelectionPane.add(viewJobStatuses, 4, 8);
            applicantSelectionPane.setHgap(20);
            applicantSelectionPane.setVgap(20);

            getFile.setOnAction((ActionEvent openFile) -> {
                FileChooser fileChooser = new FileChooser();
                //fileChooser.setInitialDirectory();
                fileChooser.setTitle("Open Resume File");
                fileChooser.setInitialFileName(loggedUser.getUsername());
                File file = fileChooser.showOpenDialog(stage);
                System.out.println(file);
            });
            getResume.setOnAction((ActionEvent eve) -> {
                String resumeText = resume.getText();
                try {
                    store.writeDocFile(loggedUser, resumeText);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            applyJob.setOnAction((ActionEvent apply) -> {
                Integer i = 0;
                Group jobPortalScene = new Group();
                stage.setScene(new Scene(jobPortalScene, 600, 600));
                Button returnApp = new Button("Back");
                Button applyButton = new Button("Apply");
                GridPane jobViewer = new GridPane();

                ToggleGroup radioSet = new ToggleGroup(); // allows only one radio button to be selected at a time
                for (JobPosting jP : jobManager.ViewJobs()) {
                    RadioButton radioButton = new RadioButton(jP.getPosition());
                    radioButton.setToggleGroup(radioSet);
                    jobViewer.add(radioButton, 0, i + 1);
                    i++;
                }

                jobViewer.add(applyButton, 4, 5);
                jobViewer.add(returnApp, 4, 6);
                jobViewer.setHgap(10);
                jobViewer.setVgap(5);
                jobPortalScene.getChildren().add(jobViewer);
                stage.show();

                applyButton.setOnAction((ActionEvent event) -> {
                    //System.out.println(radioSet.getSelectedToggle());
                    Applicant a = (Applicant) loggedUser;
                    String selectedRadio = (((RadioButton) radioSet.getSelectedToggle()).getText());
                    Button back = new Button("Back");
                    //jobManager.getJob(selectedRadio).addApplicant(a);
                    a.applyToJob(jobManager.getJob(selectedRadio));

                    back.setOnAction((ActionEvent goBack) -> {
                        stage.setScene(applicantPage);
                    });
                });

                returnApp.setOnAction((ActionEvent ex) -> {
                    stage.setScene(applicantPage);
                });

            });

            logout.setOnAction((ActionEvent ex) -> stage.setScene(loginPage));
            //Account History page
            viewHistory.setOnAction((ActionEvent history) -> {
                Group historyViewerScene = new Group();
                Scene historyPage = new Scene(historyViewerScene, 600, 600);
                stage.setScene(historyPage);

//todo: add a way for applicants to withdraw their applications after job has closed but before someone has been hired
// use withdrawApp() method from InterviewManager class to withdraw a candidates application from a job
// maybe use a dropdown menu to select the job, then that populates the job status and the ability to withdraw the application

                Label accountInfo = new Label(((Applicant)loggedUser).getInfo());
                Button returnButton = new Button("Back");
                GridPane historyPane = new GridPane();

                historyPane.add(accountInfo, 10, 0);
                historyPane.add(returnButton, 10, 14);
                historyPane.setHgap(20);
                historyPane.setVgap(5);

                historyViewerScene.getChildren().addAll(historyPane);

                returnButton.setOnAction((ActionEvent goBack) -> {
                    stage.setScene(applicantPage);
                });
            });


            viewJobStatuses.setOnAction((ActionEvent jobStatuses) -> {
                Group jobStatusViewerScene = new Group();
                Scene jobStatusPage = new Scene(jobStatusViewerScene, 600, 600);
                stage.setScene(jobStatusPage);

                Integer i = 1;
                Label jobStatusesLabel = new Label("Jobs Applied For:");
                //Label allJobStatuses = new Label(((Applicant) loggedUser).getJobStatus());
                Button returnJS = new Button("Back");
                GridPane jobStatusPane = new GridPane();
                ToggleGroup jobRadioSet = new ToggleGroup();

                for (JobPosting job: this.jobsApplied.keySet()){
                    RadioButton jobRadioButton = new RadioButton(job.getPosition() + " - "+ this.getJobStatus(job));
                    jobRadioButton.setToggleGroup(jobRadioSet);
                    jobStatusPane.add(jobRadioButton, 10, i + 1);
                    i++;
                }

                jobStatusPane.add(jobStatusesLabel, 10, 0);
                //jobStatusPane.add(allJobStatuses, 10, 4);
                jobStatusPane.add(returnJS, 10, 14);
                jobStatusPane.setHgap(20);
                jobStatusPane.setVgap(5);

                jobStatusViewerScene.getChildren().addAll(jobStatusPane);

                returnJS.setOnAction((ActionEvent goBack) -> {
                    stage.setScene(applicantPage);
                });

            });

            resumeUpload.getChildren().addAll(applicantSelectionPane);
            applicantPortalScene.getChildren().addAll(resumeUpload);
        }// what happens if they are not an applicant but have a login;
        //send message wrong user type?

    }
}