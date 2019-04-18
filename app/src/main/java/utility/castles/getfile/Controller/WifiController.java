package utility.castles.getfile.Controller;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import utility.castles.getfile.MainActivity;

public class WifiController {

    private WifiManager mWifiManager;

    public WifiController(Context mContext){
        mWifiManager= (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
    }

    public void getWifi(){

        if(mWifiManager.isWifiEnabled())
        {
            //重新掃描Wi-Fi資訊
            mWifiManager.startScan();
            //偵測周圍的Wi-Fi環境(因為會有很多組Wi-Fi，所以型態為List)
            List<ScanResult> mWifiScanResultList = mWifiManager.getScanResults();
            //手機內已存的Wi-Fi資訊(因為會有很多組Wi-Fi，所以型態為List)
            List<WifiConfiguration> mWifiConfigurationList = mWifiManager.getConfiguredNetworks();
            //目前已連線的Wi-Fi資訊
            WifiInfo mWifiInfo = mWifiManager.getConnectionInfo();

            for(int i = 0 ; i < mWifiScanResultList.size() ; i++ )
            {
                //手機目前周圍的Wi-Fi環境
                String SSID = mWifiScanResultList.get(i).SSID ;//名稱
                int LEVEL = mWifiScanResultList.get(i).level;//強度
                Log.d("test", "SSID : " + SSID + "\n\r" +"Level : " + LEVEL);
            }

        }
        else
        {
            //把Wi-Fi開啟
            mWifiManager.setWifiEnabled(true);
//            Toast.makeText(this, "Wi-Fi開啟中", Toast.LENGTH_SHORT).show();
        }


    }
}
