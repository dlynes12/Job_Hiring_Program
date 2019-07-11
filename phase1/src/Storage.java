import java.io.File;

import java.io.FileWriter;

import java.io.IOException;

import java.io.OutputStreamWriter;



public class Storage {

    String username;

    String directory = System.getProperty("user.home");

    String fileName = username + ".txt";

    String absolutePath = directory + File.separator + fileName;

    FileWriter fw;

    File f = null;



    public Storage(String uName) {

        this.username = uName;

        this.fileName = this.username + ".txt";

    }



    public void main(String[]args) {

        try {

            fw = new FileWriter(absolutePath);

        } catch (IOException e) {

            e.printStackTrace();

        }

    }



    public void write(String s){

        try {

            fw.write(s);

        } catch (IOException e) {

            e.printStackTrace();

        }



    }



    public void read (){



    }



}