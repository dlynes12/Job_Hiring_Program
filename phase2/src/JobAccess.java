import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class JobAccess{

    private ArrayList<JobPosting> JobPostings = new ArrayList<>();
    private ArrayList<JobPosting> closedJobs = new ArrayList<>();
    //Manage Open Jobs Function-------------------------------------------------------------------
    public boolean addJob(Date datePosted, Date dateClosed, String position, int rounds, String company) {
        JobPosting job = new JobPosting(datePosted, dateClosed, position, rounds, company);
        boolean add = false;
        if (this.getJob(position) == null && !position.trim().isEmpty()) {
            this.JobPostings.add(job);
            add = true;
            //automatically close the job once it has closed
            Timer jobTimer = new Timer();
            TimerTask closeJob = new TimerTask() {
                @Override
                public void run() {
                    removeJob(position);
                }
            };
            jobTimer.schedule(closeJob, dateClosed);
        }
        return add;
    }

    public JobPosting getJob(String jobTitle) {
        JobPosting result = null;
        for (int i = 0; i < JobPostings.size(); i++) {
            if (JobPostings.get(i).getPosition().equals(jobTitle)) {
                result = JobPostings.get(i);

            }
        }
        return result;
    }

    public boolean removeJob(String position) {
        //check if the position is in our job postings
        //todo: automate the start of the hiring process
        //todo: find a way to display closed jobs
        boolean remove = false;
        if (this.getJob(position) != null) {
            this.JobPostings.remove(this.getJob(position));
            this.closedJobs.add(this.getJob(position));
            this.getJob(position).startInterviewProcess();
            remove = true;
        }
        return remove;


    }

    public ArrayList<JobPosting> ViewJobs() {
        return JobPostings;
    } // see all the job postings available



}
