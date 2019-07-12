import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Writer {

    static String fileName = "";

    public Writer(String fName) {
        this.fileName = fName;
    }

    public static void main(String[]args){

        try{
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            String s;
            while((s = br.readLine()) != "*"){
                System.out.println(s + "\n");
            }
            br.close();
        }catch (IOException e){
            System.out.println("File not found.");
        }
    }

//    public void getFile(String name){
//        fileName = name;//testing commit
//    }
}
