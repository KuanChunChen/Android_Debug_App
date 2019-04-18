package utility.castles.getfile;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static utility.castles.getfile.Define.Define.d_FILE_NOT_EXIST;
import static utility.castles.getfile.Define.Define.d_OK;

public class TransFileFromUSB {


    public String getfile (String filepath ,String outFileName){

        String strRespone=null;


        FileInputStream instream = null;
        FileOutputStream outstream = null;


        try {
            File infile = new File(filepath);
            File outfile =new File(Environment.getExternalStorageDirectory().getAbsolutePath() + outFileName);



            if (infile.exists()) {


                int length;
                instream = new FileInputStream(infile);
                outstream= new FileOutputStream(outfile);
                byte[] buffer = new byte[1024000];


                while ((length = instream.read(buffer)) > 0){
                    outstream.write(buffer, 0, length);
                }


                strRespone = d_OK;
            } else {

                strRespone = d_FILE_NOT_EXIST;

            }




        } catch (Exception e) {
            e.printStackTrace();
            strRespone =e.toString();


        }

        return strRespone;





    }


    public String getStoragePath(Context context, boolean isUsb){

        String path="";

        if(Build.VERSION.SDK_INT<23) return path;

        //取得儲存器列表
        StorageManager mStorageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
        Class<?> volumeInfoClazz=null;
        Class<?> diskInfoClaszz=null;

        try {
            volumeInfoClazz = Class.forName("android.os.storage.VolumeInfo");
            diskInfoClaszz = Class.forName("android.os.storage.DiskInfo");

            Method StorageManager_getVolumes=Class.forName("android.os.storage.StorageManager").getMethod("getVolumes");
            Method VolumeInfo_GetDisk = volumeInfoClazz.getMethod("getDisk");
            Method VolumeInfo_GetPath = volumeInfoClazz.getMethod("getPath");
            Method DiskInfo_IsUsb = diskInfoClaszz.getMethod("isUsb");
            Method DiskInfo_IsSd = diskInfoClaszz.getMethod("isSd");


            List<Object> List_VolumeInfo = (List<Object>) StorageManager_getVolumes.invoke(mStorageManager);
            for(int i=0; i<List_VolumeInfo.size(); i++){
                Object volumeInfo = List_VolumeInfo.get(i);
                Object diskInfo = VolumeInfo_GetDisk.invoke(volumeInfo);


                if(diskInfo==null)continue;

                boolean sd= (boolean) DiskInfo_IsSd.invoke(diskInfo);
                boolean usb= (boolean) DiskInfo_IsUsb.invoke(diskInfo);

                File file= (File) VolumeInfo_GetPath.invoke(volumeInfo);
                // Logger.d("diskinfo="+file.getAbsolutePath()+"; is_usb="+usb+";  is_sd="+sd);

                if(isUsb == usb){//usb
                    if(file!=null)
                        path=file.getAbsolutePath();
                    break;
                }else if(!isUsb == sd){//sd
                    if(file!=null)
                        path=file.getAbsolutePath();
                    break;
                }

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();

        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();

        }
        return path;
    }


}
