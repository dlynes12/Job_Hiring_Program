import java.io.*;

public class Storage {

//    String username;
//
//    String directory = System.getProperty("user.home");
//
//    String fileName = username + ".txt";
//
//    String text = null;
//
//    String absolutePath = directory + File.separator + fileName;
//
//    public void writeFile(String fName, String data){
//        try {
//            FileWriter fw = new FileWriter(fName, true);
//            BufferedWriter bw = new BufferedWriter(fw);
//            bw.write(data);
//        } catch (IOException e) {
//
//            e.printStackTrace();
//
//        }
//    }
//
//    public void readFile(String fName){
//        try{
//            FileReader fr = new FileReader(fName);
//            BufferedReader br = new BufferedReader(fr);
//
//            String s;
//            while((s = br.readLine()) != "*" || (s = br.readLine()) != null){
//                System.out.println(s);
//            }
//            br.close();
//        }catch (IOException e){
//            System.out.println("File not found.");
//        }
//    }
    public static void main(String[] args) {

    }
    public static void writeToFile(User u) throws IOException{
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream((u.getUsername()+ ".bin")));
        objectOutputStream.writeObject(u);
    }
    public static void readFile(String fName) throws IOException, ClassNotFoundException{
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream((fName+ ".bin")));
        User u = (User) objectInputStream.readObject();
        System.out.println(u);
    }
}