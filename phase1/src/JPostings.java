import java.util.ArrayList;

public class JPostings {
    private ArrayList<JobPosting>JobPostings = new ArrayList<>();


    public void addJob(JobPosting job){ this.JobPostings.add(job);}


    public void removeJob (String position) {
        //check if the position is in our job postings
        int index = -1;
        for (int i = 0; i < JobPostings.size(); i++){
            if (JobPostings.get(i).getPosition() == position){index = i;}
        }
        //if so remove the position from the available list of jobs
        if (index != -1){JobPostings.remove(JobPostings.get(index));}
    } //assuming a job cannot have two postings (i.e. if you need two people you simply hire from one posting)
    /*or should this delete a josting once it has expired*/


    public JobPosting seeJob (String position) {
        JobPosting job = null;
        //check if the position is in our job postings
        int index = -1;
        for (int i = 0; i < JobPostings.size(); i++){
            if (JobPostings.get(i).getPosition() == position){index = i;}
        }
        //if so return that job
        if (index != -1){job = JobPostings.get(index);}
        return job;
    } //take in s string of some sort and return the specific job


    public String ViewJobs(){
        String positions = "";
        for (int i = 0; i < JobPostings.size(); i++){
            positions = positions + JobPostings.get(i).getPosition()+",";
        }
        String result = positions.substring(0,positions.length()-1); // take off the last comma(,) off the resulting string
        return result;
    } // see all the job postings available
}
