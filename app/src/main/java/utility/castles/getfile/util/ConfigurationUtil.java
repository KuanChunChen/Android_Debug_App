package utility.castles.getfile.util;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import CTOS.CtCtms;

import static CTOS.CtCtms.CONFIG_COMMUNICATE_TCP;
import static CTOS.CtCtms.CONFIG_COMMUNICATE_TLS;
import static CTOS.CtCtms.CONFIG_TERMINAL_SN;

public class ConfigurationUtil {
    private static CtCtms mCtCtms = new CtCtms();

    public static String setTlsConfig(){
        String strTlsConfig = "{\n" +
                "\t\"Host_IP\": \"ctms-nttd.castlestech.com\",\n" +
                "    \"Host_Port\": 443,\n" +
                "\t\"Verify_Peer\": true\n" +
                "  }\n";

        Log.d("mystring", strTlsConfig);
        mCtCtms.setConfig(CONFIG_COMMUNICATE_TLS, strTlsConfig);
        return strTlsConfig;
    }

    public static String setTlsConfig(String strIP,String strPort){
        String strTlsConfig = "{\n" +
                "\t\"Host_IP\": " + "\"" + strIP + "\"" + ",\n" +
                "\"Host_Port\": "  + strPort +  ",\n" +
                "\t\"Verify_Peer\": true\n" +
                "  }\n";

        Log.d("mystring", strTlsConfig);
        mCtCtms.setConfig(CONFIG_COMMUNICATE_TLS, strTlsConfig);
        return strTlsConfig;
    }
    public static String setTcpConfig(){
        String strTcpConfig = " {\n" +
                "\t\"Host_IP\": \"192.120.98.177\",\n" +
                "\"Host_Port\": 7188,\n" +
                "\t\"Transmission_Size\": 62780,\n" +
                "\t\"Connect_Timeout\": 25000,\n" +
                "\"Tx_Timeout\": 30000,\n" +
                "\"Rx_Timeout\": 30000\n" +
                "}\n";

        Log.d("mystring", strTcpConfig);
        mCtCtms.setConfig(CONFIG_COMMUNICATE_TCP, strTcpConfig);
        return strTcpConfig;
    }

    public static String setTcpConfig(String strIP,String strPort){
        String strTcpConfig = " {\n" +
                "\t\"Host_IP\": " + "\"" + strIP + "\"" + ",\n" +
                "\"Host_Port\": "  + strPort  + ",\n" +
                "\t\"Transmission_Size\": 62780,\n" +
                "\t\"Connect_Timeout\": 25000,\n" +
                "\"Tx_Timeout\": 30000,\n" +
                "\"Rx_Timeout\": 30000\n" +
                "}\n";

        Log.d("mystring", strTcpConfig);
        mCtCtms.setConfig(CONFIG_COMMUNICATE_TCP, strTcpConfig);
        return strTcpConfig;
    }

    public static void setCM_Mode(boolean boCheck){
        if(boCheck){
            mCtCtms.setCM_Mode((byte) 0x02);
        }
        else{
            mCtCtms.setCM_Mode((byte) 0x01);
        }
    }

    public static void setCTMS_Enable(boolean boCheck){
        if(boCheck){
            mCtCtms.setCTMSEnable((byte) 1);
        }
        else{
            mCtCtms.setCTMSEnable((byte) 0);
        }
    }

    public static String getAllConfig(){
        return mCtCtms.getAllConfig();

    }

    public static void SetAllConfiguration() {
        mCtCtms.setConfig(CONFIG_COMMUNICATE_TCP, " {\n" +
                "\t\"Host_IP\": " + "\"" + "3000000000000000" + "\"" + ",\n" + "  }\n");
    }


    public static void setTermianlConfig() {

        JSONObject jobTest;
        try {
            jobTest = new JSONObject(mCtCtms.getAllConfig());
            jobTest.put("Serial_Number", "3000000000000000");
            mCtCtms.setAllConfig(jobTest.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static String getConfiguration(){
        StringBuilder stringBuilder = null;
        stringBuilder.append(mCtCtms.getConfig(CONFIG_COMMUNICATE_TCP));
        stringBuilder.append(mCtCtms.getConfig(CONFIG_COMMUNICATE_TLS));
        stringBuilder.append(mCtCtms.getConfig(CONFIG_TERMINAL_SN));
        return stringBuilder.toString();
    }

    public static void updateNow(){
        mCtCtms.UpdateImmediately();
    }
}
