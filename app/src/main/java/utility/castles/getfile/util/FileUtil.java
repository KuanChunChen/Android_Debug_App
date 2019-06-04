package utility.castles.getfile.util;

import android.util.Log;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    public static boolean makeFile(){

        File makeFile = new File("");

        if(!makeFile.exists()) {
            try {
                makeFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(makeFile.exists()){
            Log.d("test", "File exist!");
            return true;

        }else{
            Log.d("test", "File not exist!");
            return false;
        }

    }
}
