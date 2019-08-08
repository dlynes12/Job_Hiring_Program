import javafx.scene.control.DatePicker;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Observable;

public class TimeKeeper extends Observable {
    DatePicker today;
    JobAccess jobManager = new JobAccess();

    public void updateTime(DatePicker datePicker, Collection<ArrayList<JobPosting>> jobPostings) {
        today = datePicker;
        checkCloseDates(jobPostings);
        setChanged(); // marks that the object has changed
        notifyObservers();

    }

    void checkCloseDates(Collection<ArrayList<JobPosting>> jobPostingsList){
        for (ArrayList<JobPosting> jobPosting: jobPostingsList){
            for (JobPosting job : jobPosting){
            if (job.getDateClosed().compareTo(Date.from(today.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))<0){
                jobManager.closeJob(job, job.getCompany());
            }}
        }
    }
}
