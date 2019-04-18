package utility.castles.getfile.Model;

/**
 * Editor:Willy Chen
 * Date: 20190306
 * Feature:Termnal information structure.
 * ***/
import android.os.Build;

import com.google.gson.annotations.SerializedName;

public class TerminalInfomation {

    //sim/phone 相關
    @SerializedName("carrierName")
    public String strCarrierName;
    @SerializedName("phoneNumber")
    public String strPhoneNumber;
    @SerializedName("IMEI")
    public String strIMEI;
    @SerializedName("IMSI")
    public String strIMSI;
    @SerializedName("roamingStatus")
    public String strRoamingStatus;
    @SerializedName("country")
    public String strCountry;
    @SerializedName("operatorNumber")
    public String strOperatorNumber;
    @SerializedName("operatorCompany")
    public String strOperatorCompany;
    @SerializedName("networkType")
    public String strNetworkType;
    @SerializedName("phoneCommnunicateType")
    public String strPhoneCommnunicateType;
    @SerializedName("simCardNumber")
    public String strSimCardNumber;

    //build系列
    @SerializedName("boardName")
    public String board;// 主機版名稱
    @SerializedName("brandName")
    public String brand;// 品牌名稱
    @SerializedName("CpuAndAbi")
    public String cpu;// CPU + ABI
    @SerializedName("deviceID")
    public String strDeviceID;
    @SerializedName("display")
    public String display;// 版本號碼
    @SerializedName("fingerprint")
    public String fingerprint;// 設備識別碼
    @SerializedName("host")
    public String host;// HOST
    @SerializedName("versionID")
    public String id;// 版本號碼
    @SerializedName("manufacturer")
    public String manufacturer;// 製造商
    @SerializedName("deviceModel")
    public String model;// 模組號碼
    @SerializedName("productName")
    public String product;// 產品名稱
    @SerializedName("devicesTags")
    public String tags;// 設備描述
    @SerializedName("deviceType")
    public String type;// 設備類別
    @SerializedName("deviceUser")
    public String user;// USER
    @SerializedName("budidTime")
    public long strBudidTime;//系統BUILD時間
    @SerializedName("versionRelease")
    public String strVersionRelease;//系統BUILD時間
    @SerializedName("versionSDK")
    public String strVersionSDK;//系統BUILD時間




    //Wifi 相關
    @SerializedName("wifiMacAddress")
    public String wifiMacAddress;// MAC ADDRESS
    @SerializedName("wifiSSID")
    public String wifiSSID;// MAC ADDRESS
    @SerializedName("wifiBSSID")
    public String wifiBSSID;// MAC ADDRESS

    //bluetooth相關
    @SerializedName("bluetoothAddress")
    public String btAddress;// bluetooth ADDRESS 藍芽的設備碼
    //Android ID
    @SerializedName("android")
    public String strAndroid;// android 他是在設備出廠後第一次啟動時產生。
    //UUID
    @SerializedName("UUID")
    public String strUUID;// android 他是在設備出廠後第一次啟動時產生。
    //UUID
    @SerializedName("displayPixel")
    public String strDisplayPixel;// 螢幕解析度

    //底層資料
    @SerializedName("terminalID")
    public String strFSN;// 螢幕解析度
    @SerializedName("terminalDevice")
    public String strDeviceModel;// 螢幕解析度

    //位置
    @SerializedName("gpsLocation")
    public String strGpsLocation;// Gps location


}
