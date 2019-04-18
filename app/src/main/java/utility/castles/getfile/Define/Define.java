package utility.castles.getfile.Define;

import android.os.Environment;

public class Define {



    //file 相關
    public final static String d_FILE_NAME="CTMSDebugLog";
    public final static String d_DIR_PATH="/data/CTMS";
    public final static String d_FILE_TYPE=".log";
    public final static String d_ENVIROMENT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();


    //transfer USB file相關
    public final static String d_IN_USB_APK="/APK/app-debug.apk";
    public final static String d_OUT_APK="/app-debug.apk";
    public final static String d_OUT_OTA="/OTA.apk";
    public final static String d_OUT_SMF="/SMF.apk";

    public final static String d_OK="001";
    public final static String d_FILE_NOT_EXIST="002";
    public final static String d_GETFIEL_EXCEPTION="003";

    public final static String d_CTMS_SERVICE_PACKAGE = "android.ctms_service.CTMS_Service";



    //for test json file
    public final static String d_FILE_TEST_STR ="testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+
            "testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+
            "testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+
            "testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+
            "testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+
            "testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+
            "testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+
            "testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+
            "testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+
            "testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+
            "testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+
            "testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+
            "testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+
            "testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n"+"testeeeeettse\n";


    public static final String d_JSON_CTMS_CONFIG_REWRITE_A ="{\n" +
            "  \"Serial_Number\": \"0000000000000999\",\n" +
            "  \"Compatible_Flag\": 0,\n" +
            "  \"Retry_Count\": 2,\n" +
            "  \"Download_Configuration\": 510,\n" +
            "  \"CM_Mode\": 1,\n" +
            "  \"TCP\": {\n" +
            "\t\"Host_IP\": \"192.120.98.178\",\n" +
            "    \"Host_Port\": 80,\n" +
            "\t\"Transmission_Size\": 62780,\n" +
            "\t\"Connect_Timeout\": 25000,\n" +
            "    \"Tx_Timeout\": 30000,\n" +
            "    \"Rx_Timeout\": 30000\n" +
            "  },\n" +
            "  \"TLS\": {\n" +
            "\t\"Host_IP\": \"ctms-sg-pos.castlestech.com\",\n" +
            "    \"Host_Port\": 443,\n" +
            "\t\"Verify_Peer\": true\n" +
            "  },\n" +
            "  \"USB\": {\n" +
            "\t\"Host_IP\": \"0.0.0.0\",\n" +
            "    \"Host_Port\": 80\n" +
            "  },\n" +
            "  \"NAC\": {\n" +
            "    \"Protocol\": \"60\",\n" +
            "    \"BlockSize\": 1024,\n" +
            "    \"SourceAddr\": \"AAAA\",\n" +
            "    \"DestAddr\": \"BBBB\",\n" +
            "    \"LenType\": 1,\n" +
            "    \"AddLenFlag\": 0\n" +
            "  }\n" +
            "}";

 }
