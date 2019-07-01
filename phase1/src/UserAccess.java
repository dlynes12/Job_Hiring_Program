import java.util.ArrayList;

public class UserAccess {
    ArrayList<User> users = new ArrayList();


    UserAccess(ArrayList users){
        this.users = users;
    }

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
        /*null result means a user is not logged in
        * the return of a user object means logged in*/
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
