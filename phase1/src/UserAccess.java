import java.util.ArrayList;
import java.util.Date;

public class UserAccess {
    public ArrayList<User> users = new ArrayList();
    private ArrayList<JobPosting>JobPostings = new ArrayList<>();

// LOGIN FUNCTIONS----------------------------------------------------------------------
    boolean addUser(User user){
        boolean add = true;
        for (int i=0; i < users.size();i++){
            if (user.getUsername().equals(users.get(i).getUsername())){
                add = false;
            }
        }
        if (add == true) {this.users.add(user);}
        return add;
    }


    public JobPosting getJob(String jobTitle){
        JobPosting result = null;
        for (int i=0; i < JobPostings.size();i++){
            if (JobPostings.get(i).getPosition().equals(jobTitle)){
                result = JobPostings.get(i);

            }
        }
        return result;
    }


    public User LogInUser(String username, String password){
        User result = null;
        for (int i=0; i < users.size();i++){
            if (users.get(i).getUsername().equals(username)){
                if (users.get(i).getPassword().equals(password)){
                    result = users.get(i);
                }
            }
        }
        return result;
    }

 //Manage Jobs Function-------------------------------------------------------------------
    public boolean addJob(Date datePosted, Date dateClosed, String position, int rounds){
        JobPosting job = new JobPosting(datePosted,dateClosed,position,rounds);
        boolean add = false;
        boolean inList = false;
        for (int i=0; i < JobPostings.size();i++){
            if (JobPostings.get(i).getPosition().equals(job.getPosition())){
                inList = true;
            }
        }
        if (inList == false){
            this.JobPostings.add(job);
            add = true;
        }
        return add;
    }


    public boolean removeJob (String position) {
        //check if the position is in our job postings
        boolean remove = false;
        int index = -1;
        for (int i = 0; i < JobPostings.size(); i++){
            if (JobPostings.get(i).getPosition().equals(position)){index = i;}
        }
        //if so remove the position from the available list of jobs
        if (index != -1){
            JobPostings.remove(JobPostings.get(index));
            remove = true;
        }
        return remove;
    } //assuming a job cannot have two postings (i.e. if you need two people you simply hire from one posting)
    /*or should this delete a josting once it has expired*/


    public JobPosting seeJob (String position) {
        JobPosting job = null;
        //check if the position is in our job postings
        int index = -1;
        for (int i = 0; i < JobPostings.size(); i++){
            if (JobPostings.get(i).getPosition().equals(position)){index = i;}
        }
        //if so return that job
        if (index != -1){job = JobPostings.get(index);}
        return job;
    } //take in s string of some sort and return the specific job


    public ArrayList<JobPosting> ViewJobs(){
        return JobPostings;
    } // see all the job postings available

}

