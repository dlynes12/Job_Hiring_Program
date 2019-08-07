import java.util.ArrayList;
import java.util.HashMap;

public class UserAccess implements java.io.Serializable {
    public ArrayList<User> users = new ArrayList();
    private ArrayList<Interviewer> employedInterviewers = new ArrayList<>();
    private HashMap<Company, ArrayList<Interviewer>> interviewers = new HashMap<>();
    private HashMap<Company, ArrayList<HR_Coordinator>> hRcoordinatots = new HashMap<>();
    Storage store = new Storage();

    // LOGIN FUNCTIONS----------------------------------------------------------------------
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

    ArrayList<Interviewer> getListInterviewers(Company company) {
        return this.interviewers.get(company);
    }

    public void setEmployedInterviewers(ArrayList<Interviewer> employedInterviewers) {
        this.employedInterviewers = employedInterviewers;
    }

    //TODO: make this accommodate for Company

    boolean addInterviewer(Interviewer interviewer) {
        boolean add = true;
        for (Interviewer account : employedInterviewers) {
            if (account.getUsername().equals(interviewer.getUsername())) {
                add = false;
            }
        }
        if (add) {
            this.employedInterviewers.add(interviewer);
        }
        return add;
    }

//    private Boolean checkInput(String username, String password) {
//        return username.matches(".*[\\S]+.*") && password.matches(".*[\\S]+.*");
//    }

    private boolean validInput(String input){
        return input.matches(".*[\\S]+.*");
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