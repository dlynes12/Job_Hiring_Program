import java.util.ArrayList;

public class UserAccess {

    String userName = null;
    String passWord = null;
    ArrayList users = new ArrayList();


    UserAccess(ArrayList users){
        this.users = users;
    }

    void addUser(Object user){
        this.users.add(user);
    }

}
