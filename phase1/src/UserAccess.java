import java.util.ArrayList;

public class UserAccess {
    static ArrayList<User> users = new ArrayList();


    boolean addUser(User user){
        boolean add = true;
        for (int i=0; i < users.size();i++){
            if (user.getUsername() == users.get(i).getUsername()){
                add = false;
            }
        }
        if (add == true) {this.users.add(user);}
        return add;
    }


    public User LogInUser(String username, String password){
        User result = null;
        for (int i=0; i < users.size();i++){
            if (users.get(i).getUsername() == username){
                if (users.get(i).getPassword()== password){
                    result = users.get(i);
                }
            }
        }
        return result;
    }
}

