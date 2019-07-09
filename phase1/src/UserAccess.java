import java.util.ArrayList;

public class UserAccess {
    public ArrayList<User> users = new ArrayList();


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


    public boolean isLogIn(String username, String password){
        boolean result = false;
        for (int i=0; i < users.size();i++){
            String listUsername = users.get(i).getUsername();
            if (listUsername.equals(username)){
                if (users.get(i).getPassword().equals(password)){
                    result = true;
                }
            }
        }
        return result;
    }


    public User LogInUser(String username, String password){
        User result = null;
        for (int i=0; i < users.size();i++){
            if (users.get(i).getUsername().equals(username)){
                if (users.get(i).getPassword().equals(password)){
                    result = users.get(i);
                }
            }
        }
        return result;
    }

}

