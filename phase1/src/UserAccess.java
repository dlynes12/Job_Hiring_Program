import java.util.ArrayList;
import java.util.Date;

public class UserAccess {
    public ArrayList<User> users = new ArrayList();
    private ArrayList<JobPosting> JobPostings = new ArrayList<>();

    // LOGIN FUNCTIONS----------------------------------------------------------------------
    boolean addUser(User user) {
        boolean add = false;
        if (this.getUser(user.getUsername(), user.getPassword()) == null) {
            this.users.add(user);
            add = true;

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


    public User getUser(String username, String password) {
        User result = null;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                if (users.get(i).getPassword().equals(password)) {
                    result = users.get(i);
                }
            }
        }
        return result;
    }

    //Manage Jobs Function-------------------------------------------------------------------
    public boolean addJob(Date datePosted, Date dateClosed, String position, int rounds) {
        JobPosting job = new JobPosting(datePosted, dateClosed, position, rounds);
        boolean add = false;
        if (this.getJob(position) == null) {
            this.JobPostings.add(job);
            add = true;

        }
        return add;
    }


    public boolean removeJob(String position) {
        //check if the position is in our job postings
        boolean remove = false;
        if (this.getJob(position) != null) {
            this.JobPostings.remove(this.getJob(position));
            remove = true;

        }
        return remove;


    } //assuming a job cannot have two postings (i.e. if you need two people you simply hire from one posting)
    /*or should this delete a josting once it has expired*/


    public ArrayList<JobPosting> ViewJobs() {
        return JobPostings;
    } // see all the job postings available

}

