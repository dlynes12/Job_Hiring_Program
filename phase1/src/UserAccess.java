import java.util.ArrayList;

public class UserAccess {

    String userName = null;
    String passWord = null;
    ArrayList Users = new ArrayList();


    UserAccess(ArrayList users){
        this.Users = users;
    }

    void addUser(Object user){
        this.Users.add(user);
    }

}
