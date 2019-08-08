import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

public class Applicant extends User {

    private HashMap<JobPosting, String> jobsApplied = new HashMap<>();
    public File docs;
    transient Storage store = new Storage();

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

    private String getJobs() {
        StringBuilder s = new StringBuilder();
        if (!this.jobsApplied.isEmpty()) {
            for (JobPosting jobPosting : this.jobsApplied.keySet()) {
                s.append(jobPosting.getJob().getPosition()).append("\n");
            }
        } else {
            s.append("Applicant has not applied for a job");
        }
        return s.toString();
    }

    private String getJobHistory() {
        StringBuilder history = new StringBuilder();
        if (!this.jobsApplied.isEmpty()) {
            Date today = new Date();
            Instant now = Instant.now();
            today.from(now);
            for (JobPosting jobPosting : this.jobsApplied.keySet()) {
                if (today.after(jobPosting.getDateClosed())) {
                    history.append(jobPosting.getJob().getPosition()).append(" - CLOSED: ").append(jobPosting.getDateClosed()).append("\n");
                } else {
                    history.append(jobPosting.getJob().getPosition()).append("- OPEN\n");
                }
            }
        }
        return history.toString();
    }

    File getDocs() {
        System.out.println("Got DOCS");
        return docs;
    }

    public void setDocs(File docs) {
        System.out.println("Docs Set");
        this.docs = docs;
    }

    private void applyToJob(JobPosting jobPosting) {
        this.jobsApplied.put(jobPosting, "Submitted Resume/CV");
        jobPosting.addApplicant(this);
    }

    private String getJobStatus(JobPosting jobPosting) {
        return this.jobsApplied.get(jobPosting);
    }

    void updateStatus(JobPosting job, String status) {
        this.jobsApplied.put(job, status);
    }

    private void withdrawApplication(JobPosting jobPosting) {
        jobPosting.removeApplicant(this);
        this.updateStatus(jobPosting, "Application withdrawn");
    }

    String getInfo() {
        return "Applicant Username: " + this.getUsername() + '\n' +
                "\n Date Created: " + this.getDateCreated() + "\n" +
                "\nJobs Applied To:\n" + this.getJobHistory();
    }

    @Override
    public String toString() {
        return "{A," + this.getJobs() + "," + this.getUsername() + "," + this.getPassword() + "}";
    }

    void applicantGUISetUp(Stage stage, User loggedUser, SystemAdmin systemAdmin, Scene loginPage) {
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
                fileChooser.setTitle("Open Resume File");
                fileChooser.setInitialFileName(loggedUser.getUsername());
                File file = fileChooser.showOpenDialog(stage);
                ((Applicant) loggedUser).setDocs(file);

                getResume.setOnAction((ActionEvent eve) -> {
                    try {
                        System.out.println(loggedUser);
                        store.saveDocs(loggedUser);
                    } catch (IOException | NullPointerException e) {
                        e.printStackTrace();
                    }
                });
            });

            getResume.setOnAction((ActionEvent eve) -> {
                try {
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(((Applicant) loggedUser).getDocs())));
                    out.println(resume.getText());
                } catch (IOException | NullPointerException ioe) {
                    ioe.printStackTrace();
                }
                try {
                    store.saveDocs(loggedUser);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            applyJob.setOnAction((ActionEvent apply) -> {
                Group jobPortalScene = new Group();
                stage.setScene(new Scene(jobPortalScene, 600, 600));
                Button returnApp = new Button("Back");
                Button applyButton = new Button("Apply");
                Button select = new Button("Select");
                GridPane jobViewer = new GridPane();
                ListView jobList = new ListView();
                ComboBox filter = new ComboBox(FXCollections.observableArrayList("fullTime", "partTime", "allJobs"));
                jobViewer.add(select, 12, 1);
                jobViewer.add(filter, 10, 0);
                jobViewer.add(applyButton, 4, 5);
                jobViewer.add(returnApp, 4, 6);
                jobViewer.setHgap(10);
                jobViewer.setVgap(5);
                jobPortalScene.getChildren().add(jobViewer);
                stage.show();

                returnApp.setOnAction((ActionEvent ex) -> stage.setScene(applicantPage));

                applyButton.setOnAction((ActionEvent ex) -> systemAdmin.getAlert("tag").showAndWait());

                select.setOnAction((ActionEvent ex) -> {
                    ListView<String> lst = new ListView<>();
                    ArrayList<String> tempArray = systemAdmin.getJobManager().sort((String) filter.getValue());
                    jobList.setItems(FXCollections.observableArrayList(tempArray));
                    lst.setItems(FXCollections.observableArrayList(tempArray));
                    jobViewer.add(lst, 0, 0);

                    applyButton.setOnAction((ActionEvent event) -> {
                        try {
                            Applicant a = (Applicant) loggedUser;
                            Button back = new Button("Back");
                            //TODO: accommodate for company

                            String jpTemp = lst.getSelectionModel().getSelectedItem();
                            String[] jpSplit = jpTemp.split(" - Company: ");
                            String position = jpSplit[0];
                            Company comp = systemAdmin.getCompany(jpSplit[1]);

                            a.applyToJob(systemAdmin.getJobManager().getJobPosting(position, comp));

                            back.setOnAction((ActionEvent goBack) -> stage.setScene(applicantPage));
                        } catch (NullPointerException e2) {
                            systemAdmin.getAlert("apply").showAndWait();
                        }
                    });
                });
            });

            logout.setOnAction((ActionEvent ex) -> stage.setScene(loginPage));

            viewHistory.setOnAction((ActionEvent history) -> {
                Group historyViewerScene = new Group();
                Scene historyPage = new Scene(historyViewerScene, 600, 600);
                stage.setScene(historyPage);
                Label accountInfo = new Label(((Applicant) loggedUser).getInfo());
                Button returnButton = new Button("Back");
                GridPane historyPane = new GridPane();
                historyPane.add(accountInfo, 10, 0);
                historyPane.add(returnButton, 10, 14);
                historyPane.setHgap(20);
                historyPane.setVgap(5);

                returnButton.setOnAction((ActionEvent goBack) -> stage.setScene(applicantPage));

                historyViewerScene.getChildren().addAll(historyPane);
            });

            viewJobStatuses.setOnAction((ActionEvent jobStatuses) -> {
                Group jobStatusViewerScene = new Group();
                Scene jobStatusPage = new Scene(jobStatusViewerScene, 600, 600);
                stage.setScene(jobStatusPage);
                int i = 1;
                Label jobStatusesLabel = new Label("Jobs Applied For:");
                Button returnJS = new Button("Back");
                Button withdraw = new Button("Withdraw Application");
                GridPane jobStatusPane = new GridPane();
                ToggleGroup jobRadioSet = new ToggleGroup();

                for (JobPosting jobPosting : this.jobsApplied.keySet()) {
                    //RadioButton jobRadioButton = new RadioButton(jobPosting.getJob().getPosition() + " - " + this.getJobStatus(jobPosting));
                    RadioButton jobRadioButton = new RadioButton(jobPosting.getJob().getPosition() + "," +
                            jobPosting.getJob().getCompany().getCompanyName() + "," + this.getJobStatus(jobPosting));
                    jobRadioButton.setToggleGroup(jobRadioSet);
                    jobStatusPane.add(jobRadioButton, 10, i + 1);
                    i++;
                }
                jobStatusPane.add(jobStatusesLabel, 10, 0);
                jobStatusPane.add(withdraw, 10, 13);
                jobStatusPane.add(returnJS, 10, 14);
                jobStatusPane.setHgap(20);
                jobStatusPane.setVgap(5);

                jobStatusViewerScene.getChildren().addAll(jobStatusPane);

                withdraw.setOnAction((ActionEvent withdrawApp) -> {
                    RadioButton selectedToggle = (RadioButton) jobRadioSet.getSelectedToggle();
                    String selectedToggleText = selectedToggle.getText();
                    String[] textSplit = selectedToggleText.split(","); //splits the job name from it's status
                    String jobPosition = textSplit[0];
                    Company comp = systemAdmin.getCompany(textSplit[1]);
                    try{
                        JobPosting jobPosting = systemAdmin.getJobManager().getJobPosting(jobPosition, comp);
                        this.withdrawApplication(jobPosting);
                    }
                    catch (Exception closedJobException){
                        JobPosting jobPosting = systemAdmin.getJobManager().getClosedJob(jobPosition, comp);
                        jobPosting.getHiringProcessor().withdrawApp(this);
                    }

                });
                returnJS.setOnAction((ActionEvent goBack) -> stage.setScene(applicantPage));
            });
            resumeUpload.getChildren().addAll(applicantSelectionPane);
            applicantPortalScene.getChildren().addAll(resumeUpload);
        }
    }
}