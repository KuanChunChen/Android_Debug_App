package utility.castles.getfile.Controller;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

import CTOS.CtSystem;
import CTOS.CtSystemException;
import utility.castles.getfile.Model.TerminalInfomation;

public class GetTerminalInfo {

    private LocationManager myLocationManager;

    public TerminalInfomation getGsonInformation(Context mContext){

        TerminalInfomation terminalInfomationReturn = new TerminalInfomation();
        TelephonyManager telManager = ((TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE));

        terminalInfomationReturn.strCarrierName = telManager.getSimOperatorName();

        // 手機號碼
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        terminalInfomationReturn.strPhoneNumber = telManager.getLine1Number();
        // 手機 IMEI
        terminalInfomationReturn.strIMEI = telManager.getDeviceId();
        // 手機 IMSI
        terminalInfomationReturn.strIMSI = telManager.getSubscriberId();
        // 手機漫遊狀態
        terminalInfomationReturn.strRoamingStatus = telManager.isNetworkRoaming() ? "漫遊中" : "非漫遊";
        // 電信網路國別
        terminalInfomationReturn.strCountry = telManager.getNetworkCountryIso();
        // 電信公司代號
        terminalInfomationReturn.strOperatorNumber = telManager.getNetworkOperator();
        // 電信公司名稱
        terminalInfomationReturn.strOperatorCompany = telManager.getNetworkOperatorName();
        // 行動網路類型
        String[] networkTypeArray = {"UNKNOWN", "GPRS", "EDGE", "UMTS", "CDMA", "EVDO 0", "EVDO A", "1xRTT", "HSDPA", "HSUPA", "HSPA"};
        terminalInfomationReturn.strNetworkType = networkTypeArray[telManager.getNetworkType()];
        // 行動通訊類型
        String[] phoneTypeArray = {"NONE", "GSM", "CDMA"};
        terminalInfomationReturn.strPhoneCommnunicateType = phoneTypeArray[telManager.getPhoneType()];
        //sim卡號碼
        terminalInfomationReturn.strSimCardNumber = telManager.getSimSerialNumber();

        terminalInfomationReturn.board = Build.BOARD;// 主機版名稱
        terminalInfomationReturn.brand = Build.BRAND;// 品牌名稱
        terminalInfomationReturn.cpu = Build.CPU_ABI;// CPU + ABI
        terminalInfomationReturn.strDeviceID = Build.DEVICE;// 設備名稱
        terminalInfomationReturn.display = Build.DISPLAY;// 版本號碼
        terminalInfomationReturn.fingerprint = Build.FINGERPRINT;// 設備識別碼
        terminalInfomationReturn.host = Build.HOST;// HOST
        terminalInfomationReturn.id = Build.ID;// 版本號碼
        terminalInfomationReturn.manufacturer = Build.MANUFACTURER;// 製造商
        terminalInfomationReturn.model = Build.MODEL;// 模組號碼
        terminalInfomationReturn.product = Build.PRODUCT;// 產品名稱
        terminalInfomationReturn.tags = Build.TAGS;// 設備描述
        terminalInfomationReturn.type = Build.TYPE;// 設備類別
        terminalInfomationReturn.user = Build.USER;// USER
        terminalInfomationReturn.strBudidTime = Build.TIME;//系統build時間

        terminalInfomationReturn.strVersionRelease = Build.VERSION.RELEASE	;//Release version
        terminalInfomationReturn.strVersionSDK = Build.VERSION.SDK;//sdk version

        //wifi
        WifiManager mWifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        terminalInfomationReturn.wifiMacAddress = mWifiManager.getConnectionInfo().getMacAddress();//wifi mac address
        terminalInfomationReturn.wifiSSID = mWifiManager.getConnectionInfo().getSSID(); //wifi id
        terminalInfomationReturn.wifiBSSID = mWifiManager.getConnectionInfo().getBSSID(); // wifi mac ip


        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        terminalInfomationReturn.btAddress = (btAdapter != null) ? btAdapter.getAddress() : "";

        terminalInfomationReturn.strAndroid = Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);

        terminalInfomationReturn.strUUID = UUID.randomUUID().toString();

        //螢幕解析度

        terminalInfomationReturn.strDeviceModel = command("getprop", "ro.oem.device");
        terminalInfomationReturn.strFSN = GetFactoryNumber();

        //gps位置
        LocationManager locationManager;
        String context = Context.LOCATION_SERVICE;
        locationManager = (LocationManager)mContext.getSystemService(context);
        String provider = LocationManager.GPS_PROVIDER;
        Location location = locationManager.getLastKnownLocation(provider);
        terminalInfomationReturn.strGpsLocation = updateWithNewLocation(location);

        return terminalInfomationReturn;



}



    private String GetFactoryNumber() {

        byte[] bArrFSN = new byte[16];
        CtSystem mCtSystem = new CtSystem();
        String str_fsn = "";
        try {
            bArrFSN = mCtSystem.getFactorySN();
            str_fsn = String.format("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c", bArrFSN[0], bArrFSN[1],
                    bArrFSN[2], bArrFSN[3], bArrFSN[4], bArrFSN[5], bArrFSN[6], bArrFSN[7], bArrFSN[8], bArrFSN[9], bArrFSN[10],
                    bArrFSN[11], bArrFSN[12], bArrFSN[13], bArrFSN[14]);
        } catch (CtSystemException e) {
            e.printStackTrace();
        }

        return str_fsn;
    }

    public String command(String command, String command1) {
        String result = "";
        String line;
        String[] cmd = new String[]{command, command1};
        String workdirectory = "/system/bin/";
        try {
            ProcessBuilder bulider = new ProcessBuilder(cmd);
            bulider.directory(new File(workdirectory));
            bulider.redirectErrorStream(true);
            Process process = bulider.start();
            InputStream in = process.getInputStream();
            InputStreamReader isrout = new InputStreamReader(in);
            BufferedReader brout = new BufferedReader(isrout, 8 * 1024);

            while ((line = brout.readLine()) != null) {
                result += line;
            }
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private String updateWithNewLocation(Location location) {
        String latLongString;

        if (location != null){
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            latLongString = "Lat:" + lat + "\nLong:" + lng;
        }else{
            latLongString = "No Location";
        }

        return latLongString;
    }


}
