import java.io.*;


public class Storage {

    String username;

    String directory = System.getProperty("user.home");

    String fileName = username + ".txt";

    String text = null;

    String absolutePath = directory + File.separator + fileName;

    public void main(String[]args) {

        try {
            FileWriter fw = new FileWriter(fileName);
            PrintWriter pw = new PrintWriter(fw);

            pw.println(text);
            pw.println("*");

            pw.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public void getString (String s){
        this.text = s;
    }



}