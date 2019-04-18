package utility.castles.getfile;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class USBActivity extends AppCompatActivity {

    private static final String TAG = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.usblayout);
        Button usb_btn_return=findViewById(R.id.usb_btn1);
        Button usb_btn_get_APK=findViewById(R.id.usb_btn2);
        Button usb_btn_get_OTA=findViewById(R.id.usb_btn3);
        Button usb_btn_get_SMF=findViewById(R.id.usb_btn4);
        //Button usb_btn_get_ALL=findViewById(R.id.usb_btn5);
        final TextView tv1 =findViewById(R.id.usb_text1);
        final TextView tv2 =findViewById(R.id.usb_text2);
        final TextView tv4 =findViewById(R.id.usb_text4);
        final EditText ed1 =findViewById(R.id.usb_et1);
        String strUSBpath;
        tv4.setText("＂使用說明＂\n"+
                "在USB中建立對應資料夾\n"+
                    "如：/APK 或 /SMF 或 /OTA\n"+
                    "在對應資料夾加入對應檔\n"+
                    "輸入要轉移的檔案加副檔名\n"+
                    "按下對應按鈕即可轉移");

        usb_btn_return.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

        usb_btn_get_APK.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                String strAPK = getStoragePath(USBActivity.this,true);
                 if(strAPK!=""){
                    Toast.makeText(getApplicationContext(), "USB_path: "+strAPK, Toast.LENGTH_SHORT).show();
                    getfile(strAPK + File.separator +"APK"+ File.separator + ed1.getText(), File.separator +String.valueOf(ed1.getText()));
                    strAPK = null;


                }else{
                    Toast.makeText(getApplicationContext(), "can not get USB location !", Toast.LENGTH_SHORT).show();
                    tv1.setText("");
                    tv2.setText("");
                }




            }
        });
        usb_btn_get_OTA.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                String strOTA = getStoragePath(USBActivity.this,true);
                if(strOTA!=""){
                    Toast.makeText(getApplicationContext(), "USB_path: "+strOTA, Toast.LENGTH_SHORT).show();
                    getfile(strOTA + File.separator +"OTA"+ File.separator + ed1.getText(), File.separator +String.valueOf(ed1.getText()));
                    strOTA = null;


                }else{
                    Toast.makeText(getApplicationContext(), "can not get USB location !", Toast.LENGTH_SHORT).show();
                    tv1.setText("");
                    tv2.setText("");
                }

            }
        });
        usb_btn_get_SMF.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                String strOTA = getStoragePath(USBActivity.this,true);
                if(strOTA!=""){
                    Toast.makeText(getApplicationContext(), "USB_path: "+strOTA, Toast.LENGTH_SHORT).show();
                    getfile(strOTA + File.separator +"SMF"+ File.separator + ed1.getText(), File.separator +String.valueOf(ed1.getText()));
                    strOTA = null;


                }else{
                    Toast.makeText(getApplicationContext(), "can not get USB location !", Toast.LENGTH_SHORT).show();
                    tv1.setText("");
                    tv2.setText("");
                }

            }
        });
      /*  usb_btn_get_ALL.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
               // getfile("/sdcard/DCIM/Camera/IMG_20180912_015605.jpg","/235432.jpg");
                String strALL = getStoragePath(USBActivity.this,true);
                if(strALL!=""){
                    //Toast.makeText(getApplicationContext(), "USB_path: "+strALL, Toast.LENGTH_LONG).show();
                    //getfile(strALL + File.separator +"SMF"+ File.separator +ed1.getText(),d_OUT_SMF);
                    //strALL = null;


                }else{
                    Toast.makeText(getApplicationContext(), "can not get USB location !", Toast.LENGTH_SHORT).show();
                    tv1.setText("");
                    tv2.setText("");
                }
            }
        });*/




    }
    void getfile (String filepath ,String outFileName){
        TextView tvStatus = findViewById(R.id.usb_text1);
        TextView tvFileCheck = findViewById(R.id.usb_text2);
        EditText et2 =findViewById(R.id.usb_et1);


        FileInputStream instream = null;
        FileOutputStream outstream = null;


        try {
            File infile = new File(filepath);
            File outfile =new File(Environment.getExternalStorageDirectory().getAbsolutePath() + outFileName);



            if (infile.exists()) {
                Toast.makeText(getApplicationContext(), "File Exist", Toast.LENGTH_SHORT).show();
                tvFileCheck.setText(filepath+"\n"+"File Exist");

                int length;
                instream = new FileInputStream(infile);
                outstream= new FileOutputStream(outfile);
                byte[] buffer = new byte[1024000];


                while ((length = instream.read(buffer)) > 0){
                    outstream.write(buffer, 0, length);
                }

                tvStatus.setText(" Success transfer file to ："+ Environment.getExternalStorageDirectory().getAbsolutePath() + outFileName);

            } else {
                Toast.makeText(getApplicationContext(), filepath + "is not Exist", Toast.LENGTH_SHORT).show();
                tvFileCheck.setText(filepath + " is not Exist");
                tvStatus.setText("");



            }




        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            tvStatus.setText(e.toString());

        }






    }

    void read() {
        TextView teet = findViewById(R.id.usb_text1);
        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        HashMap<String, UsbDevice> deviceList = manager.getDeviceList();
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
        StringBuffer sb = new StringBuffer();
        while (deviceIterator.hasNext()) {
            UsbDevice device = deviceIterator.next();
            String Model = device.getDeviceName();
             teet.setText(Model + "/s1e/030.apk");

            try {
                File file = new File(Model + "/s1e/030.txt");
                if (file.exists())
                    Toast.makeText(getApplicationContext(), "Exist", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(), "Not Exist", Toast.LENGTH_LONG).show();

                BufferedReader br = new BufferedReader(new FileReader(Model + "/s1e/030.txt"));
                String sCurrentLine;

                while ((sCurrentLine = br.readLine()) != null) {
                    teet.append(sCurrentLine + "\n");
                }

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                teet.setText(e.toString());
            }
        }
    }


    private String getStoragePath(Context context, boolean isUsb){

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
   ///////


    private static final String ACTION_USB_PERMISSION = "com.template.USB_PERMISSION";//可自定义
    private static UsbManager mUsbManager;
    private BroadcastReceiver mUsbPermissionActionReceiver;
    //获取USB权限
    private void tryGetUsbPermission(){
        final TextView tvvv=findViewById(R.id.usb_text2);
        final TextView tvvvv=findViewById(R.id.usb_text3);


        mUsbPermissionActionReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (ACTION_USB_PERMISSION.equals(action)) {
                    context.unregisterReceiver(this);//解注册
                    synchronized (this) {
                        UsbDevice usbDevice = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                        if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                            if(null != usbDevice){
                                Log.e(TAG,usbDevice.getDeviceName()+"已获取USB权限");
                                tvvv.setText("已获取USB权限");
                            }
                        }
                        else {
                            //user choose NO for your previously popup window asking for grant perssion for this usb device
                            Log.e(TAG,String.valueOf("USB权限已被拒绝，Permission denied for device" + usbDevice));
                            tvvv.setText("USB权限已被拒绝");

                        }
                    }

                }
            }
        };



        mUsbManager = (UsbManager) getSystemService(Context.USB_SERVICE);

        IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);

        if(mUsbPermissionActionReceiver != null) {
            registerReceiver(mUsbPermissionActionReceiver, filter);
        }

        PendingIntent mPermissionIntent = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0);

        boolean has_idcard_usb = false;
        for (final UsbDevice usbDevice : mUsbManager.getDeviceList().values()) {

            if(usbDevice.getVendorId() == 1024 && usbDevice.getProductId() == 50010)//身份证设备USB
            {
                has_idcard_usb = true;
                Log.e(TAG,usbDevice.getDeviceName()+"已找到身份证USB");
                tvvvv.setText("已找到身份证USB");

                if(mUsbManager.hasPermission(usbDevice)){
                    Log.e(TAG,usbDevice.getDeviceName()+"已获取过USB权限");
                    tvvvv.setText("已获取过USB权限");

                }else{
                    Log.e(TAG,usbDevice.getDeviceName()+"请求获取USB权限");
                    mUsbManager.requestPermission(usbDevice, mPermissionIntent);
                    tvvvv.setText("请求获取USB权限");

                }
            }

        }

        if(!has_idcard_usb)
        {
            Log.e(TAG,"未找到身份证USB");
            tvvv.setText("未找到身份证USB");

        }

    }









}
