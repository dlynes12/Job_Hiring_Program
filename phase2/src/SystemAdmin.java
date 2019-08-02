import java.sql.Time;

public class SystemAdmin {

    private JobAccess jobManager;
    private UserAccess userManager;
    private TimeKeeper timeKeeper;

     public SystemAdmin(){
        this.jobManager = new JobAccess();
        this.userManager = new UserAccess();
    }

    protected JobAccess getJobManager(){
         return this.jobManager;
    }

    protected UserAccess getUserManager(){
         return this.userManager;
    }

    protected TimeKeeper getTimeKeeper() { return this.timeKeeper; }

}
