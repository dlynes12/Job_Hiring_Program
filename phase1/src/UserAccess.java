import java.util.ArrayList;
import java.util.Date;

public class UserAccess {
    public ArrayList<User> users = new ArrayList();


    // LOGIN FUNCTIONS----------------------------------------------------------------------
    boolean addUser(User user) {
        boolean add = false;
        if (this.getUser(user.getUsername(), user.getPassword()) == null) {
            this.users.add(user);
            add = true;

        }
        return add;
    }

    public User getUser(String username, String password) {
        User result = null;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                if (users.get(i).getPassword().equals(password)) {
                    result = users.get(i);
                }
            }
        }
        return result;
    }



}

