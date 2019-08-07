import javafx.scene.control.DatePicker;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

public class TimeKeeper extends Observable {
    DatePicker today;
    JobAccess jobManager = new JobAccess();

    public void updateTime(DatePicker datePicker, ArrayList<JobPosting> jobPostings) {
        today = datePicker;
        checkCloseDates(jobPostings);
        setChanged(); // marks that the object has changed
        notifyObservers();

    }

    void checkCloseDates(ArrayList<JobPosting> jobPostings){
        for (JobPosting job: jobPostings){
            if (job.getDateClosed().compareTo(Date.from(today.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))<0){
                jobManager.closeJob(job);
            }
        }
    }
}
