import java.util.*;

public class JobAccess implements Observer {


    Date today;
    private ArrayList<JobPosting> jobPostings = new ArrayList<>();
    private ArrayList<JobPosting> closedJobs = new ArrayList<>();

    @Override
    public void update(Observable o, Object arg) {
        String expiredPositions = "";
        for (JobPosting job: jobPostings){
            if (job.getDateClosed().before(today)){
                expiredPositions = expiredPositions + job.getPosition() + ",";
            }
        }
        if (!expiredPositions.equals("")){
            String strJobs = expiredPositions.substring(0,expiredPositions.length()-1);
            String[] strListJobs = strJobs.split(",");
            for (String str: strListJobs){
                this.removeJob(str);
            }
        }
    }

    public void retrieveTime(Date date){
        today = date;
    }

    //Manage Open Jobs Function-------------------------------------------------------------------
    public boolean addJob(Date dateClosed, String position, int rounds, String company, ArrayList<String> listOfInterv, ArrayList<Interviewer> chosenInterviewers) {
        JobPosting job = new JobPosting(today, dateClosed, position, rounds, company, listOfInterv,chosenInterviewers);
        boolean add = false;
        if (this.getJob(position) == null && !position.trim().isEmpty()) {
            this.jobPostings.add(job);
            add = true;
            //automatically close the job once it has closed
//            Timer jobTimer = new Timer();
//            TimerTask closeJob = new TimerTask() {
//                @Override
//                public void run() {
//                    removeJob(position);
//                }
//            };
//            jobTimer.schedule(closeJob, dateClosed);
        }
        System.out.println("Job created:" + add);
        return add;
    }

    public JobPosting getJob(String jobTitle) {
        JobPosting result = null;
        for (int i = 0; i < jobPostings.size(); i++) {
            if (jobPostings.get(i).getPosition().equals(jobTitle)) {
                result = jobPostings.get(i);
            }
        }
        return result;

    }

    public JobPosting getClosedJob(String jobTitle) {
        JobPosting result = null;
        for (int i = 0; i < closedJobs.size(); i++) {
            if (closedJobs.get(i).getPosition().equals(jobTitle)) {
                result = closedJobs.get(i);
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
            this.closedJobs.add(this.getJob(position));
            this.jobPostings.remove(this.getJob(position));
            this.getClosedJob(position).startInterviewProcess();
            remove = true;
        }
        return remove;
    }

    public ArrayList<JobPosting> ViewJobs() {return jobPostings;} // see all the job postings available

    public ArrayList<JobPosting> viewClosedJobs(){return closedJobs;}
}