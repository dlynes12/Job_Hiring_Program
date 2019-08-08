import java.io.*;
import java.util.List;

public class Storage {

    Storage() {
    }

    public final void writeList(List a, String name) throws IOException, NullPointerException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream((name + ".bin")));
        objectOutputStream.writeObject(a);
        System.out.println("Yo" + a);
    }

    public List readList(String name) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream((name + ".bin")));
        List l = (List) objectInputStream.readObject();
        System.out.println(l);
        return l;
    }

    public final void writeToFile(User u) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream((u.getUsername() + ".bin")));
        objectOutputStream.writeObject(u);
    }

    public final void writeDocFile(User u) throws java.io.IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream((u.getUsername() + "docs.bin")));
        objectOutputStream.writeObject(((Applicant) u).getDocs());
    }

    public final File readDocFile(String uName) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream((uName + "docs.bin")));
        File f = (File) objectInputStream.readObject();
        return f;
    }

    public File getDocs(String username) throws IOException, ClassNotFoundException {
        return readDocFile(username);
    }

    public void saveDocs(User user) throws IOException, NullPointerException {
        writeDocFile(user);
    }
}