import java.util.ArrayList;

public class UserAccess implements java.io.Serializable {
    public ArrayList<User> users = new ArrayList();
    private ArrayList<Interviewer> employedInterviewers = new ArrayList<>();
    Storage store = new Storage();

    // LOGIN FUNCTIONS----------------------------------------------------------------------
    boolean addUser(User user) {
        boolean added = false;
        if (checkInput(user.getUsername(), user.getPassword())) {
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
        return employedInterviewers;
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

    private Boolean checkInput(String username, String password) {
        return username.matches(".*[\\S]+.*") && password.matches(".*[\\S]+.*");
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