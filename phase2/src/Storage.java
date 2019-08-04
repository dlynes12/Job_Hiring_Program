import java.io.*;
import java.util.List;

public class Storage {

    public Storage() {
    }

    public final void writeList(List a, String name) throws IOException, NullPointerException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream((name + ".bin")));
        objectOutputStream.writeObject(a);
        System.out.println("Yo" + a);

    }
    public List readList(String name) throws IOException, ClassNotFoundException{
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream((name + ".bin")));
        List l = (List) objectInputStream.readObject();
        System.out.println(l);
        return l;
    }

    public final void writeToFile(User u) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream((u.getUsername()+ ".bin")));
        objectOutputStream.writeObject(u);
    }
    public final void readFile(String uName) throws IOException, ClassNotFoundException{
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream((uName+ ".bin")));
        User u = (User) objectInputStream.readObject();
        System.out.println(u);
    }

    public final void writeDocFile(User u, String s) throws java.io.IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream((u.getUsername()+ "docs.bin")));
        objectOutputStream.writeObject(s);
    }

    public final void readDocFile(String uName) throws IOException, ClassNotFoundException{
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream((uName+ ".bin")));
        String s = (String) objectInputStream.readObject();
        System.out.println(s);
    }

}
