public class SystemAdmin {

    private JobAccess jobManager;
    private UserAccess userManager;

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

}
