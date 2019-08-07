import java.util.*;

public class JobAccess implements Observer {

    Date today;
    private ArrayList<JobPosting> jobPostings = new ArrayList<>();
    private ArrayList<JobPosting> closedJobs = new ArrayList<>();

    @Override
    public void update(Observable o, Object arg) {
        String expiredPositions = "";
        for (JobPosting jobPosting : jobPostings) {
            if (jobPosting.getDateClosed().before(today)) {
                expiredPositions = expiredPositions + jobPosting.getJob().getPosition() + ",";
            }
        }
        if (!expiredPositions.equals("")) {
            String strJobs = expiredPositions.substring(0, expiredPositions.length() - 1);
            String[] strListJobs = strJobs.split(",");
            for (String str : strListJobs) {
                this.removeJobPosting(str);
            }
        }
    }

    void retrieveTime(Date date) {
        today = date;
    }

    //Manage Open Jobs Function-------------------------------------------------------------------
    void addJobPosting(Job job, Date dateClosed, ArrayList<Interviewer> chosenInterviewers, int numHires) {
        JobPosting jobposting = new JobPosting(job, today, dateClosed, chosenInterviewers, numHires);
        boolean added = false;
        if (this.getJobPosting(job.getPosition()) == null && validInput(job.getPosition())) {
            this.jobPostings.add(jobposting);
            added = true;
        }
        System.out.println("Job created:" + added);
    }

    JobPosting getJobPosting(String jobTitle) {
        JobPosting result = null;
        for (int i = 0; i < jobPostings.size(); i++) {
            if (jobPostings.get(i).getJob().getPosition().equals(jobTitle)) {
                result = jobPostings.get(i);
            }
        }
        return result;
    }

    JobPosting getClosedJob(String jobTitle) {
        JobPosting result = null;
        for (int i = 0; i < closedJobs.size(); i++) {
            if (closedJobs.get(i).getJob().getPosition().equals(jobTitle)) {
                result = closedJobs.get(i);
            }
        }
        return result;
    }

    private boolean removeJobPosting(String position) {
        //check if the position is in our job postings
        //todo: automate the start of the hiring process
        //todo: find a way to display closed jobs
        boolean remove = false;
        if (this.getJobPosting(position) != null) {
            this.closedJobs.add(this.getJobPosting(position));
            this.jobPostings.remove(this.getJobPosting(position));
            this.getClosedJob(position).startInterviewProcess();
            remove = true;
        }
        return remove;
    }

    ArrayList<String> sort(String s) {
        ArrayList<String> al = new ArrayList<>();
        for (JobPosting j : this.jobPostings) {
            if (s.equals("allJobs")) {
                al.add(j.getJob().getPosition());

            } else if (j.getJob().getTag().equals(s)) {
                al.add(j.getJob().getPosition());
            }
        }
        return al;
    }

    private boolean validInput(String input){
        return input.matches(".*[\\S]+.*");
    }

    ArrayList<JobPosting> ViewJobs() {
        return jobPostings;
    } // see all the job postings available

    ArrayList<JobPosting> viewClosedJobs() {
        return closedJobs;
    }
}