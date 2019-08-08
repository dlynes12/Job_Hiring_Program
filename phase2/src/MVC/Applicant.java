package MVC;

import javafx.collections.FXCollections;
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
import java.util.Date;
import java.util.HashMap;

public class Applicant extends User {
    private HashMap<JobPosting, String> jobsApplied = new HashMap<>();
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

    public void getDocs(String username) throws IOException, ClassNotFoundException {
        store.readDocFile(username + "docs.bin");
    }
//    public void setDocsHash(User u, Boolean a, Boolean b, String s) throws IOException {
//        ArrayList<Boolean> docs = new ArrayList<>();
//        docs.add(a);
//        docs.add(b);
//        userDocs.put(u.getUsername(),docs);
//
//        store.writeDocFile(u,s);
//    }

    private void applyToJob(JobPosting jobPosting) {
        this.jobsApplied.put(jobPosting, "Submitted Resume/CV");
        jobPosting.addApplicant(this);
    }

    public String getJobStatus(JobPosting jobPosting) {
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

    public HashMap<JobPosting, String> getJobsApplied() {
        return this.jobsApplied;
    }

}