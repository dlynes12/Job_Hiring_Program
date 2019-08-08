import java.io.Serializable;
import java.util.ArrayList;

public class Company implements Serializable {

    private ArrayList<String> listCompLocations = new ArrayList<>();
    private String companyName;

    Company(String companyName, ArrayList<String>locations, SystemAdmin systemAdmin){
        this.companyName = companyName;
        this.listCompLocations = locations;
        systemAdmin.getUserManager().initiateCompany(this);
        systemAdmin.getJobManager().initializeCompany(this);
    }

    String getCompanyName(){
        return this.companyName;
    }

    public ArrayList<String> getLocations(){return this.listCompLocations;}

}
