import java.util.ArrayList;

public class UserAccess {
    private  String password;
    private  String username;
    static ArrayList<UserAccess> users = new ArrayList();


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    UserAccess(String username, String password) {
        this.username = username;
        this.password = password;
    }

    boolean addUser(UserAccess user){
        boolean add = true;
        for (int i=0; i < users.size();i++){
            if (user.getUsername() == users.get(i).getUsername()){
                add = false;
            }
        }
        if (add == true) {this.users.add(user);}
        return add;
    }

    public UserAccess LogInUser(String username, String password){
        /*null result means a user is not logged in
        * the return of a user object means logged in*/
        UserAccess result = null;
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
