import java.sql.Time;
import java.util.HashMap;

public class SystemAdmin {

    private JobAccess jobManager;
    private UserAccess userManager;
    private HashMap<Company, HR_Coordinator> hRCoordinators;
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

    protected HR_Coordinator getCompanyHR(Company company){ return this.hRCoordinators.get(company); }

    public void addCompanyHR(Company company, HR_Coordinator hrCoordinator){
         this.hRCoordinators.put(company, hrCoordinator);
    }

    protected TimeKeeper getTimeKeeper() { return this.timeKeeper; }

}
