package com.fyp.wsn.Entity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Nadith Premaratne on 26/05/2017.
 */
public class Documentation  {


    private String file_path;
    public Documentation(String FILENAME) {
        file_path=FILENAME;

    }

    public void doc(String Base,String sensor,String from_what,String to_which){
        this.Write("Connect "+Base+"'s " +from_what+" pin to "+sensor+"'s "+to_which+" pin\n");
    }




    private void Write(String content){
        BufferedWriter bw = null;
        FileWriter fw = null;
        try{

            File file = new File(this.file_path);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // true = append file
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);

            bw.write(content);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            try {

                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }
        }
    }
}
