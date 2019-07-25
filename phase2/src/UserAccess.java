import java.util.ArrayList;
import java.util.Date;

public class UserAccess {
    public ArrayList<User> users = new ArrayList();


    // LOGIN FUNCTIONS----------------------------------------------------------------------
    boolean addUser(User user) {
        boolean add = true;
        if (user.getUsername().matches(".*[\\S]+") && user.getPassword().matches(".*[\\S]+")) {
            for (User account: users){
                if ( account.getUsername().equals(user.getUsername())){add = false;}
            }
            if (add){this.users.add(user);}
        }
//        st.writeFile("userData.txt", );
        return add;
    }

    public User getUser(String username) {
        User result = null;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                result = users.get(i);
            }
        }
        return result;
    }

    public User login(String username, String password){
        User temp = this.getUser(username);
        if (!temp.getPassword().equals(password)){temp = null;}
        return temp;
    }
//todo: fix getListInterviewer by making a list of Interviewers and adding to it once an Interviewer is created
    public ArrayList<Interviewer> getListInterviewers(){ /// buggy doesnt work
        ArrayList<Interviewer> ListInt = new ArrayList<>();
        for (User user: users){
            if (user.getClass().isInstance(Interviewer.class)){  /// Interviewers ae being returned as type User so code isn't running
                ListInt.add(((Interviewer) user));
            }
        }
        return ListInt;
    }

//    public String listUses(ArrayList<User> a){
//        String list = "";
//        for(User u: a){
//            list = list + u.getUsername()
//        }
//    }

    public ArrayList<User> viewUsers() {
        return users;
    }

}

