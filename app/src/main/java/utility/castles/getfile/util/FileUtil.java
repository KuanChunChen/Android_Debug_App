package utility.castles.getfile.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;

import static utility.castles.getfile.Define.Define.d_DIR_PATH;
import static utility.castles.getfile.Define.Define.d_FILE_TYPE;

public class FileUtil {





    public static String getDefaultFolder(Context mContext) {
        Log.d("getDefaultFolder", String.valueOf(mContext.getFilesDir()));
        return mContext.getFilesDir().getPath();
    }

    public static File[] FileFilter(File folder) {
        Log.d("Folder", folder.toString());
        FilenameFilter mediafilefilter = new FilenameFilter(){
            private String[] strarrFilter = {".prm",".json",".txt"};
            @Override
            public boolean accept(File dir, String filename) {
                for(int i = 0; i< strarrFilter.length ; i++){
                    if(filename.indexOf(strarrFilter[i]) != -1)return true;
                }
                return false;
            }};

        File[] list = folder.listFiles(mediafilefilter);
        for (int i = 0; i < list.length; i++) {
            Log.d("FileTest", list[i].toString());

        }

        return list;
    }
    public static boolean isDirectoryExist(String strPath){
        File mFile = new File(strPath);
        if (mFile.exists()){
            return true;
        }else{
            return false;
        }
    }
    public static boolean deleteAllFile(File folder) {
        boolean success = folder.delete();
        if (success) {
            Log.d("deleteAllFile", "del all success");
            return true;
        } else {
            Log.d("deleteAllFile", "del all fail");
            return false;
        }
    }
    public static boolean makeDefaultFile(String strMakeDir,String strFile){
        File makeFile = new File(strMakeDir + "/" + strFile);
        try {
            makeFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(makeFile.exists()){
            Log.d("test", "File exist!");
            return true;
        }else{
            Log.d("test", "File not exist!");
            return false;
        }
    }

    public static String writeFile(String strPath, String fileName, String strWrite) {
        String strRespone = "";
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_REMOVED)) {
            try {

                String savePath = strPath + File.separator + fileName;

                File file = new File(savePath);

                if (!file.exists()) {
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(strWrite);
                bw.close();
                strRespone = "Success";
            } catch (IOException e) {
                e.printStackTrace();

                strRespone = "Fail!";
            } catch (Exception e) {
                e.printStackTrace();
                strRespone = e.toString();

            }
        } else {
            strRespone = "NO SD CARD!";


        }

        return strRespone;
    }

    public static String readFile(String strFilePath,String strFileName) {

        BufferedReader buffReader = null;
        String strRespone = "";

        try {
            StringBuffer strBuffOutput = new StringBuffer();

            String savePath = strFilePath + File.separator + strFileName ;
            Log.d("SAVEPATH", savePath + "|");
            buffReader = new BufferedReader(new FileReader(savePath));
            String line = "";
            while ((line = buffReader.readLine()) != null) {
                strBuffOutput.append(line + "\n");


                if (strBuffOutput.capacity() > 102400) {
                    strRespone += strBuffOutput.toString();
                    strBuffOutput.setLength(0);
                }
            }
            strRespone += strBuffOutput.toString();
            buffReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d(e.toString(), e.toString());
            strRespone = "FileNotFoundException";
        } catch (IOException e) {
            e.printStackTrace();
            strRespone = "IOExecption";

        }
        return strRespone;
    }
}
