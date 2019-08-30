package utility.castles.getfile.Define;

import android.os.Environment;

import utility.castles.getfile.App;
import utility.castles.getfile.util.FileUtil;

public class Define {


    //
    //
    public final static String PATH_SME_AME = FileUtil.getDefaultFolder(App.getContext()) + "/ExtraModuleList";
    //
    public final static String ID_BOOTSULD = "ID_BOOTSULD";
    public final static String ID_CRYPTO_HAL = "ID_CRYPTO_HAL";
    public final static String ID_LINUX_KERNEL = "ID_LINUX_KERNEL";
    public final static String ID_SECURITY_KO = "ID_SECURITY_KO";
    public final static String ID_SYSUPD_KO = "ID_SYSUPD_KO";

    public final static String ID_KMS = "ID_KMS";
    public final static String ID_CADRV_KO = "ID_CADRV_KO";
    public final static String ID_CAUSB_KO = "ID_CAUSB_KO";
    public final static String ID_LIBCAUART_SO = "ID_LIBCAUART_SO";
    public final static String ID_LIBCAUSBH_SO = "ID_LIBCAUSBH_SO";
    public final static String ID_LIBCAMODEM_SO = "ID_LIBCAMODEM_SO";
    public final static String ID_LIBCAETHERNET_SO = "ID_LIBCAETHERNET_SO";
    public final static String ID_LIBCAFONT_SO = "ID_LIBCAFONT_SO";
    public final static String ID_LIBCALCD_SO = "ID_LIBCALCD_SO";
    public final static String ID_LIBCAPRT_SO = "ID_LIBCAPRT_SO =";
    public final static String ID_LIBCARTC_SO = "ID_LIBCARTC_SO";
    public final static String ID_LIBCAULDPM_SO = "ID_LIBCAULDPM_SO";
    public final static String ID_LIBCAPMODEM_SO = "ID_LIBCAPMODEM_SO";
    public final static String ID_LIBCAGSM_SO = "ID_LIBCAGSM_SO";
    public final static String ID_LIBCAEMVL2_SO = "ID_LIBCAEMVL2_SO";
    public final static String ID_LIBCAKMS_SO = "ID_LIBCAKMS_SO";
    public final static String ID_LIBCAFS_SO = "ID_LIBCAFS_SO";
    public final static String ID_LIBCABARCODE_SO = "ID_LIBCABARCODE_SO";
    public final static String ID_CRADLE_MP = "ID_CRADLE_MP";
    public final static String ID_LIBTLS_SO = "ID_LIBTLS_SO";
    public final static String ID_LIBCLVW_SO = "ID_LIBCLVW_SO";
    public final static String ID_LIBCTOSAPI_SO = "ID_LIBCTOSAPI_SO";
    public final static String ID_SAM_KO = "ID_SAM_KO";
    public final static String ID_CLVWM_MP = "ID_CLVWM_MP";
    public final static String ID_ROOTFS = "ID_ROOTFS";
    public final static String ID_BIOS = "ID_BIOS";
    public final static String ID_CIF_KO = "ID_CIF_KO";
    public final static String ID_CLDRV_KO = "ID_CLDRV_KO";
    public final static String ID_TMS = "ID_TMS";
    public final static String ID_ULDPM = "ID_ULDPM";
    public final static String ID_EMV_SO = "ID_EMV_SO";
    public final static String ID_EMVCL_SO = "ID_EMVCL_SO";


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
