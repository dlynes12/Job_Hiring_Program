import java.util.ArrayList;
import java.util.Date;

public class UserAccess {
    public ArrayList<User> users = new ArrayList();
    Storage st = new Storage();


    // LOGIN FUNCTIONS----------------------------------------------------------------------
    boolean addUser(User user) {
        boolean add = false;
        if (this.getUser(user.getUsername()) == null && !user.getUsername().trim().isEmpty()) {
            this.users.add(user);
            add = true;
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

