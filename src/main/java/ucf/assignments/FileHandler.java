package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Andrew Miller
 */

import javafx.collections.ObservableList;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    // prompt file using javafx FileChooser
    // return path of said file (string)
    public static String promptFile(){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files","*.txt"));
        File f = fc.showOpenDialog(null);
        if(f != null)
            return f.getAbsolutePath();
        return "";
    }

    /*
     * try:
     *   initialize writer
     *   loop through data
     *      write line to file
     *   close writer
     * catch exception:
     *   print error
     */
    public static void writeToFile(ObservableList<item> list,String path){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            for(int i=0; i<list.size(); i++){
                bw.write(list.get(i).toString()+"\n");
            }
            bw.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }

    /*
     *  initialize String ArrayList
     *  try:
     *     initialize reader
     *     initialize lineString
     *     loop through lines
     *         add String to String Arraylist
     *     close reader
     *  catch exception:
     *      return empty ArrayList
     *
     *  return String ArrayList
     */
    public static ArrayList<String> readFromFile(String path){
        ArrayList<String> ret = new ArrayList<>();
        try{
            BufferedReader br =  new BufferedReader(new FileReader(path));
            String line = br.readLine();
            while(line != null){
                ret.add(line);
                line = br.readLine();
            }
            br.close();
        } catch(IOException e){
            return new ArrayList<>();
        }
        return ret;
    }
}
