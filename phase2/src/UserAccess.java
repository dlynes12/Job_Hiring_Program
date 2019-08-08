import java.util.ArrayList;
import java.util.HashMap;

public class UserAccess implements java.io.Serializable {
    protected ArrayList<User> users = new ArrayList<>();
    private ArrayList<Interviewer> employedInterviewers = new ArrayList<>();
    private HashMap<Company, ArrayList<Interviewer>> interviewers = new HashMap<>();
    private HashMap<Company, ArrayList<HR_Coordinator>> hrCoordinators = new HashMap<>();



    boolean addUser(User user) {
        boolean added = false;
        if (validInput(user.getUsername()) && validInput(user.getPassword())) {
            for (User account : users) {
                if (account.getUsername().equals(user.getUsername())) {
                    added = true;
                }
            }
            if (!added) {
                this.users.add(user);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    User getUser(String username) {
        User result = null;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                result = users.get(i);
            }
        }
        return result;
    }

    User login(String username, String password) {
        User user = this.getUser(username);
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    ArrayList<Interviewer> getListInterviewers() {
        return this.employedInterviewers;
    }

    //TODO: make this accommodate for Company

    boolean addInterviewer(Interviewer interviewer){
        boolean add = true;
        String companyName = interviewer.getCompany().getCompanyName();
        Company interviewerCompany = interviewer.getCompany();

        if(this.companyCheck(companyName, this.interviewers)){ //if company exists in hashmap
            for (Interviewer account : this.interviewers.get(interviewerCompany)){
                if(account.getUsername().equals(interviewer.getUsername())){
                    add = false;
                }
            }
            if (add){
                this.interviewers.get(interviewerCompany).add(interviewer);
            }
        }
        else{
            ArrayList<Interviewer> newList = new ArrayList<>();
            newList.add(interviewer);
            this.interviewers.put(interviewerCompany, newList);
        }
            //TODO: delete all these printlns lol
            String str = "";
            for (Company company : this.interviewers.keySet()){
                str = str + company.getCompanyName() + ", ";
            }
            System.out.println(str);
            String test = "";
            for (Interviewer i : this.interviewers.get(interviewerCompany)){
                test = test + i.getUsername() + ", ";
            }
            System.out.println(test);
        return add;
    }

    boolean addHRCoordinator(HR_Coordinator hrCoordinator){
        boolean add = true;
        String companyName = hrCoordinator.getCompany().getCompanyName();
        if (this.companyCheck(companyName, this.hrCoordinators)){ //if company exists in the hashmap
            for (HR_Coordinator account : this.hrCoordinators.get(hrCoordinator.getCompany())){
                if(account.getUsername().equals(hrCoordinator.getUsername())){
                    add = false;
                }
            }
            if (add) {
                this.hrCoordinators.get(hrCoordinator.getCompany()).add(hrCoordinator);
            }
        }
        else{
            ArrayList<HR_Coordinator> newList = new ArrayList<>();
            newList.add(hrCoordinator);
            this.hrCoordinators.put(hrCoordinator.getCompany(), newList);
        }

        //TODO: delete these:
        String str = "";
        for (Company company : this.hrCoordinators.keySet()){
            str = str + company.getCompanyName() + ", ";
        }
        System.out.println(str);
        String test = "";
        for (HR_Coordinator i : this.hrCoordinators.get(hrCoordinator.getCompany())){
            test = test + i.getUsername() + ", ";
        }
        System.out.println(test);

        return add;
    }

    private boolean validInput(String input){
        return input.matches(".*[\\S]+.*");
    }

    private boolean companyCheck(String companyName, HashMap map){
        boolean exists = false;
        for (Object object: map.keySet()){
            if (((Company) object).getCompanyName().equals(companyName)){
                exists = true;
            }
        }
        return exists;
    }

//    public String listUses(ArrayList<User> a){
//        String list = "";
//        for(User u: a){
//            list = list + u.getUsername()
//        }
//    }

    ArrayList<User> viewUsers() {
        return users;
    }

}