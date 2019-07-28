import java.io.*;
import java.util.List;

public class Storage {

    public Storage() {
    }

    public static void writeUserList(List a) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(("users.bin")));
        objectOutputStream.writeObject(a);
    }
    public List readUserList() throws IOException, ClassNotFoundException{
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(("users.bin")));
        List l = (List) objectInputStream.readObject();
        return l;
    }

    public static void writeToFile(User u) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream((u.getUsername()+ ".bin")));
        objectOutputStream.writeObject(u);
    }
    public static void readFile(String uName) throws IOException, ClassNotFoundException{
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream((uName+ ".bin")));
        User u = (User) objectInputStream.readObject();
        System.out.println(u);
    }

    public static void writeDocFile(User u, String s) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream((u.getUsername()+ "docs.bin")));
        objectOutputStream.writeObject(s);
    }

    public static void readDocFile(String uName) throws IOException, ClassNotFoundException{
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream((uName+ ".bin")));
        String s = (String) objectInputStream.readObject();
        System.out.println(s);
    }


}
